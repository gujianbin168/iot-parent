package com.chint.datapool.cloud.common.intercept;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.chint.datapool.cloud.common.base.BaseModel;
import com.chint.datapool.cloud.common.entity.Page;
import com.chint.datapool.cloud.common.util.DateUtil;
 
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMapping.Builder;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.JdbcType;


/**
 * Mybatis查询拦截器，提供数据库对应的分页支持
 * 
 */

@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class,Integer.class}) })
public class PageInterceptor implements Interceptor {

    /**
     * 拦截后要执行的方法
     */
    public Object intercept(Invocation invocation) throws Throwable {
        
        // Mybatis只有在建立RoutingStatementHandler的时候是通过Interceptor的plugin方法进行包裹的，
        // 所以我们这里拦截到的目标对象肯定是RoutingStatementHandler对象。
        RoutingStatementHandler handler = (RoutingStatementHandler) invocation
                .getTarget();
        
        // 通过反射获取到当前RoutingStatementHandler对象的delegate属性
        StatementHandler delegate = (StatementHandler) ReflectUtil
                .getFieldValue(handler, "delegate");
        
        // 获取到当前StatementHandler的 boundSql，
        // 这里不管是调用 handler.getBoundSql()还是直接调用delegate.getBoundSql()结果是一样的
        // RoutingStatementHandler实现的所有StatementHandler接口方法里面都是调用的delegate对应的方法。
        BoundSql boundSql = delegate.getBoundSql();
        String sql = boundSql.getSql();

        // 拿到当前绑定Sql的参数对象，就是我们在调用对应的Mapper映射语句时所传入的参数对象
        Object paramObj = boundSql.getParameterObject();
        if (paramObj instanceof HashMap) {
        	HashMap<String, Object> paramArg = (HashMap<String, Object>)paramObj;
        	if(paramArg.size() > 1) {
        		for(Map.Entry<String, Object> paramEntry: paramArg.entrySet()) {
        			Object param =  paramEntry.getValue();
        			if(param instanceof Page) {
        				Page page = (Page)param;
        				// 通过反射获取delegate父类BaseStatementHandler的mappedStatement属性
                        MappedStatement mappedStatement = (MappedStatement) ReflectUtil
                                .getFieldValue(delegate, "mappedStatement");
                        
                        // 拦截到的prepare方法参数是一个Connection对象
                        Connection connection = (Connection) invocation.getArgs()[0];
                        
                        // 给当前的page参数对象设置总记录数
                        this.setTotalRecord(page, mappedStatement, connection, boundSql);
                        
                        // 获取分页Sql语句
                        String pageSql = this.getPageSql(page, sql);
                        
                        // 利用反射设置当前BoundSql对应的sql属性为我们建立好的分页Sql语句
                        ReflectUtil.setFieldValue(boundSql, "sql", pageSql);
                        
                        break;
        			}
        		}
        	}
        }else if (paramObj instanceof BaseModel) {
        	BaseModel param = (BaseModel)paramObj;
        	String nowData = DateUtil.getCurrentDate(DateUtil.YYMMDDHHMMSS);
        	//设置参数列表
        	List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        	//插入的情况
        	if(sql.startsWith("insert")) {
        		param.setCreateDateTime(nowData);
        		param.setLastUpdateTime(nowData);
        		String insertSql = getInsertOrUpdateSql(true, sql);
        		// 利用反射设置当前BoundSql对应的sql属性为 存在创建时间、更新时间的sql
                ReflectUtil.setFieldValue(boundSql, "sql", insertSql);
                //创建新增日期参数
                Configuration configuration = (Configuration) ReflectUtil.getFieldValue(parameterMappings.get(0),"configuration");
                Builder pCreateDateTime = new Builder(configuration, "createDateTime", String.class);
                pCreateDateTime.jdbcType(JdbcType.TIMESTAMP);
                ParameterMapping pmCreateDateTime =  pCreateDateTime.build();
                parameterMappings.add(pmCreateDateTime);
                //创建修改日期参数
                Builder pUpdateDateTime = new Builder(configuration, "lastUpdateTime", String.class);
                pUpdateDateTime.jdbcType(JdbcType.TIMESTAMP);
                ParameterMapping pmUpdateDateTime =  pUpdateDateTime.build();
                parameterMappings.add(pmUpdateDateTime);
        	}else if(sql.startsWith("update")) {
        		param.setLastUpdateTime(nowData);
        		String updateSql = getInsertOrUpdateSql(false, sql);
        		// 利用反射设置当前BoundSql对应的sql属性为 存在更新时间的sql
                ReflectUtil.setFieldValue(boundSql, "sql", updateSql);
                Configuration configuration = (Configuration) ReflectUtil.getFieldValue(parameterMappings.get(0),"configuration");
                //创建修改日期参数
                Builder pUpdateDateTime = new Builder(configuration, "lastUpdateTime", String.class);
                pUpdateDateTime.jdbcType(JdbcType.TIMESTAMP);
                ParameterMapping pmUpdateDateTime =  pUpdateDateTime.build();
                int whereIndex = updateSql.indexOf("where");
                if (whereIndex != -1) { //有where條件
                	String whereSql = updateSql.substring(whereIndex);
	           			int index = counter(whereSql, '?');
	           			if (index > 0) { //如果where替换符参数个数大于0 在指定位置进行插入
	           				 parameterMappings.add(parameterMappings.size()-index, pmUpdateDateTime);
	   					}else { //没有替换符参数直接在末尾插入
	   						parameterMappings.add(pmUpdateDateTime);
	   					}
				}else { //沒有where條件
					parameterMappings.add(pmUpdateDateTime);
				}
        	}
        }
        return invocation.proceed();
    }
    
    /**统计一个指定字符串中指定字符出现的次数
     * @param s 指定字符串
     * @param c 指定字符
     * @return 出现次数
     */
	private static int counter(String s, char c) {
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == c) {
				count++;
			}
		}
		return count;
	}
    
    
    /**
     * 给当前的参数对象page设置总记录数 
     * @param page Mapper映射语句对应的参数对象
     * @param mappedStatement Mapper映射语句
     * @param connection 当前的数据库连接
     */
    private void setTotalRecord(Page page, MappedStatement mappedStatement,
            Connection connection, BoundSql boundSql) {
     
        // 获取到我们自己写在Mapper映射语句中对应的Sql语句
        String sql = boundSql.getSql();
        
        // 通过查询Sql语句获取到对应的计算总记录数的sql语句
        String countSql = this.getCountSql(sql);

        // 通过BoundSql获取对应的参数映射
        List<ParameterMapping> parameterMappings = boundSql
                .getParameterMappings();
        
        // 利用Configuration、查询记录数的Sql语句countSql、
        // 参数映射关系parameterMappings和参数对象page建立查询记录数对应的BoundSql对象。
        BoundSql countBoundSql = new BoundSql(
                mappedStatement.getConfiguration(), countSql,
                parameterMappings, boundSql.getParameterObject());
        
        // 通过mappedStatement、参数对象page和BoundSql对象countBoundSql建立一个用于设定参数的ParameterHandler对象 
        ParameterHandler parameterHandler = new DefaultParameterHandler(
                mappedStatement, boundSql.getParameterObject(), countBoundSql);

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = connection.prepareStatement(countSql);
            parameterHandler.setParameters(pstmt);

            // 执行获取总记录数的Sql语句和获取结果
            rs = pstmt.executeQuery();
            int totalRecord = 0;
            while (rs.next()) {
                totalRecord += rs.getInt(1);
            }
            // 给当前的参数page对象设置总记录数 
            page.setTotalRecord(totalRecord);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 根据原Sql语句获取对应的查询总记录数的Sql语句
     * @param sql 原Sql语句
     * @return 查询总记录数的Sql语句
     */
    private String getCountSql(String sql) {
        sql = "select count(*) from (" + sql + ") pInter";
        return sql;
        
    }

    /**
     * 获取分页查询Sql语句
     * @param page 分页对象
     * @param sql 原sql语句
     * @return 分页查询Sql语句
     */
    private String getPageSql(Page page, String sql) {
        StringBuffer sqlBuffer = new StringBuffer(sql);

	    int offset = (page.getPageNo() - 1) * page.getPageSize();
	    sqlBuffer.append(" limit ").append(offset).append(",")
	            .append(page.getPageSize());
        
        return sqlBuffer.toString();
    }

	 /**
	  * 获取新增、修改sql
	 * @param flag true:新增 false:修改
	 * @param sql 原sql
	 * @return 新sql
	 */
    private String getInsertOrUpdateSql(boolean flag,String sql) {
        StringBuffer sqlBuffer = new StringBuffer(sql);
    	if (flag) { //insert
            sqlBuffer.insert(sqlBuffer.indexOf(")"), ", create_date_time, last_update_time");
            sqlBuffer.insert(sqlBuffer.lastIndexOf(")"), ", ?, ?");
		}else { //update
			int whereIndex = sqlBuffer.indexOf("where");
			if (whereIndex != -1) { //有where条件则在where条件前插入
				sqlBuffer.insert(sqlBuffer.indexOf("where"), ", last_update_time = ?  ");
			}else {//没有where条件 则在末尾拼接更新日期
				sqlBuffer.append(", last_update_time = ? ");
			}
		}
    	return sqlBuffer.toString();
    }
    
    /**
     * 拦截器对应的封装原始对象的方法
     */
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 设置注册拦截器时设定的属性
     */
    public void setProperties(Properties properties) {
    }

    /**
     * 利用反射进行操作的一个工具类
     */
    private static class ReflectUtil {

        /**
         * 利用反射获取指定对象的指定属性
         * 
         * @param obj
         *            目标对象
         * @param fieldName
         *            目标属性
         * @return 目标属性的值
         */
        public static Object getFieldValue(Object obj, String fieldName) {
            Object result = null;
            Field field = ReflectUtil.getField(obj, fieldName);
            if (field != null) {
                field.setAccessible(true);
                try {
                    result = field.get(obj);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return result;
        }

        /**
         * 利用反射获取指定对象里面的指定属性
         * 
         * @param obj
         *            目标对象
         * @param fieldName
         *            目标属性
         * @return 目标字段
         */
        private static Field getField(Object obj, String fieldName) {
            Field field = null;
            for (Class<?> clazz = obj.getClass(); clazz != Object.class; clazz = clazz
                    .getSuperclass()) {
                try {
                    field = clazz.getDeclaredField(fieldName);
                    break;
                } catch (NoSuchFieldException e) {
                    // 这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。
                }
            }
            return field;
        }

        /**
         * 利用反射设置指定对象的指定属性为指定的值
         * 
         * @param obj
         *            目标对象
         * @param fieldName
         *            目标属性
         * @param fieldValue
         *            目标值
         */
        public static void setFieldValue(Object obj, String fieldName,
                Object fieldValue) {
            Field field = ReflectUtil.getField(obj, fieldName);
            if (field != null) {
                field.setAccessible(true);
                try {
                    field.set(obj, fieldValue);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
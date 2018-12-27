package com.chint.datapool.cloud.common.api.annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.gson.Gson;
/**
 * @Title: SensitiveInfoUtils.java
 * @Copyright: Copyright (c) 2018
 * @Description: <br>
 *               敏感信息屏蔽工具<br>
  */
public final class SensitiveInfoSerialize {
    /**
     * 获取脱敏json串 <注意：递归引用会导致java.lang.StackOverflowError>
     * 
     * @param javaBean
     * @return
     */
    public static String getJson(Object javaBean) {
        String json = null;
        if (null != javaBean) {
            Class<? extends Object> raw = javaBean.getClass();
            try {
                if (raw.isInterface())
                    return json;
                Gson g = new Gson();
                Object clone = g.fromJson(g.toJson(javaBean, javaBean.getClass()), javaBean.getClass());
                Set<Integer> referenceCounter = new HashSet<Integer>();
                SensitiveInfoSerialize.replace(SensitiveInfoSerialize.findAllField(raw), clone, referenceCounter);
                json = JSON.toJSONString(clone, 
                		//SerializerFeature.PrettyFormat,
    					//SerializerFeature.WriteMapNullValue,
    					//SerializerFeature.WriteNullListAsEmpty,
    					//SerializerFeature.WriteNullBooleanAsFalse,
    					SerializerFeature.DisableCircularReferenceDetect);
                referenceCounter.clear();
                referenceCounter = null;
            } catch (Throwable e) {
 
            }
        }
        return json;
    }
 
    private static Field[] findAllField(Class<?> clazz) {
        Field[] fileds = clazz.getDeclaredFields();
        while (null != clazz.getSuperclass() && !Object.class.equals(clazz.getSuperclass())) {
            fileds = (Field[]) ArrayUtils.addAll(fileds, clazz.getSuperclass().getDeclaredFields());
            clazz = clazz.getSuperclass();
        }
        return fileds;
    }
    private static void replace(Field[] fields, Object javaBean, Set<Integer> referenceCounter) throws IllegalArgumentException, IllegalAccessException {
        if (null != fields && fields.length > 0) {
            for (Field field : fields) {
                field.setAccessible(true);
                if (null != field && null != javaBean) {
                    Object value = field.get(javaBean);
                    if (null != value) {
                        Class<?> type = value.getClass();
                        // 1.处理子属性，包括集合中的
                        if (type.isArray()) {
                            int len = Array.getLength(value);
                            for (int i = 0; i < len; i++) {
                                Object arrayObject = Array.get(value, i);
                                SensitiveInfoSerialize.replace(SensitiveInfoSerialize.findAllField(arrayObject.getClass()), arrayObject, referenceCounter);
                            }
                        } else if (value instanceof Collection<?>) {
                            Collection<?> c = (Collection<?>) value;
                            Iterator<?> it = c.iterator();
                            while (it.hasNext()) {
                                Object collectionObj = it.next();
                                SensitiveInfoSerialize.replace(SensitiveInfoSerialize.findAllField(collectionObj.getClass()), collectionObj, referenceCounter);
                            }
                        } else if (value instanceof Map<?, ?>) {
                            Map<?, ?> m = (Map<?, ?>) value;
                            Set<?> set = m.entrySet();
                            for (Object o : set) {
                                Entry<?, ?> entry = (Entry<?, ?>) o;
                                Object mapVal = entry.getValue();
                                SensitiveInfoSerialize.replace(SensitiveInfoSerialize.findAllField(mapVal.getClass()), mapVal, referenceCounter);
                            }
                        } else if (!type.isPrimitive()
                                   && !StringUtils.startsWith(type.getPackage().getName(), "javax.")
                                   && !StringUtils.startsWith(type.getPackage().getName(), "java.")
                                   && !StringUtils.startsWith(field.getType().getName(), "javax.")
                                   && !StringUtils.startsWith(field.getName(), "java.")
                                   && referenceCounter.add(value.hashCode())) {
                            SensitiveInfoSerialize.replace(SensitiveInfoSerialize.findAllField(type), value, referenceCounter);
                        }
                    }
                    // 2. 处理自身的属性
                    SensitiveInfo annotation = field.getAnnotation(SensitiveInfo.class);
                    if (field.getType().equals(String.class) && null != annotation) {
                        String valueStr = (String) value;
                        if (StringUtils.isNotBlank(valueStr)) {
                            switch (annotation.value()) {
	                            case NONE: {
	                                field.set(javaBean, SensitiveInfoUtils.none(valueStr));
	                                break;
	                            }                            
                                case CHINESE_NAME: {
                                    field.set(javaBean, SensitiveInfoUtils.chineseName(valueStr));
                                    break;
                                }
                                case ID_CARD: {
                                    field.set(javaBean, SensitiveInfoUtils.idCardNum(valueStr));
                                    break;
                                }
                                case FIXED_PHONE: {
                                    field.set(javaBean, SensitiveInfoUtils.fixedPhone(valueStr));
                                    break;
                                }
                                case MOBILE_PHONE: {
                                    field.set(javaBean, SensitiveInfoUtils.mobilePhone(valueStr));
                                    break;
                                }
                                case ADDRESS: {
                                    field.set(javaBean, SensitiveInfoUtils.address(valueStr, 4));
                                    break;
                                }
                                case EMAIL: {
                                    field.set(javaBean, SensitiveInfoUtils.email(valueStr));
                                    break;
                                }
                                case BANK_CARD: {
                                    field.set(javaBean, SensitiveInfoUtils.bankCard(valueStr));
                                    break;
                                }
                                case CNAPS_CODE: {
                                    field.set(javaBean, SensitiveInfoUtils.cnapsCode(valueStr));
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
   
//----------------------------------------------------------------------------------------------
    public static Method [] findAllMethod(Class<?> clazz){
        Method [] methods= clazz.getMethods();
        return methods;
    }
}
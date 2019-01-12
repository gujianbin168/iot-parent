package com.chint.datapool.cloud.common.token;

/**
 * 
 * @ClassName:  ConstantKit   
 * @Description:TODO(这里用一句话描述这个类的作用)   
 * @author: wusheng
 * @date:   2019年1月12日 上午9:12:37   
 *
 */
public final class ConstantKit {

    /**
     * 设置删除标志为真
     */
    public static final Integer DEL_FLAG_TRUE = 1;

    /**
     * 设置删除标志为假
     */
    public static final Integer DEL_FLAG_FALSE = 0;

    /**
     * redis存储token设置的过期时间
     */
    public static final Integer TOKEN_EXPIRE_TIME = 60 * 2;

    /**
     * 设置可以重置token过期时间的时间界限
     */
    public static final Integer TOKEN_RESET_TIME = 1000 * 100;


}

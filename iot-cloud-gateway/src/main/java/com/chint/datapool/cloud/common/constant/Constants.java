package com.chint.datapool.cloud.common.constant;

/**
 * 常量定义
 * 
 * @author gujianbin
 */
public class Constants {
	/** 空字符串 */
	public static final String EMPTY = "";
	/** 全角空格 */
	public static final String FULL_BLANK = "　";
	/** 半角空格 */
	public static final String HALF_BLANK = " ";
	/** code 字符串 */
	public static final String CODE = "code";
	/** cookie key */
	public static final String COOKIE_TOKEN_KEY = "USER_TOKEN";
	/** 默认页数 */
	public static final Integer PAGE_NO = 1;
	/** 默认件数 */
	public static final Integer PAGE_SIZE = 30;
	
	 /**设置删除标志为真*/
    public static final Integer DEL_FLAG_TRUE = 1;
    /** 设置删除标志为假*/
    public static final Integer DEL_FLAG_FALSE = 0;
    /**redis存储token设置的过期时间*/
    public static final Integer TOKEN_EXPIRE_TIME = 60 * 2;
    /**设置可以重置token过期时间的时间界限*/
    public static final Integer TOKEN_RESET_TIME = 1000 * 100;


	/** 返回code */
	public class ResultCode {
		/** 成功 */
		public static final String SUCCESS = "200";
		/** 需要客户确认的成功数据 */
		public static final String SUCCESS_CONFIRM = "210";
		/** 登录异常（用户角色验证，token认证） */
		public static final String LOGING_ERR = "202";
		/** 权限异常 （访问url权限） */
		public static final String AUTH_ERR = "203";
		/** 业务异常（业务抛出异常） */
		public static final String BUSS_ERR = "204";
		/** 系统异常 （响应超时等） */
		public static final String SYS_ERR = "205";
		/** 登录异常（区分二维码引导） */
		public static final String LOGING_QRCODE_ERR = "206";
		/** 参数异常 */
		public static final String PARAM_ERR = "209";
	}

	/**
	 * redis Key 头文字
	 */
	public class REDIS_KEY_HEAD {
		/** 用户信息 */
		public static final String USER_INFO = "chint:main:userinfo";
		/** 经销商信息 */
		public static final String DEALER_INFO = "chint:main:dealerinfo";
		/** 微信TOKEN */
		public static final String WECHAT_TOKEN = "chint:defination:wechat:accountstoken";
		/** 企业微信TOKEN */
		public static final String WECHAT_CORPTOKEN = "chint:defination:wechat:corptoken";
		/** m_code表缓存redis的key的前缀 */
		public static final String M_CODE_KEY = "chint:main:mcode:";
		/** 验证码校验码 */
		public static final String CAPTCHA_CODE = "chint:interface:captchacode:";
	}
}

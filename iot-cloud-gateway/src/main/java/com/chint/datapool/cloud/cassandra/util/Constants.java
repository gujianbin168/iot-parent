package com.chint.datapool.cloud.cassandra.util;

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
	
	/** 产业公司名 */
	//量测技术研究院
	public static final String COMPANY_LCJS = "lcjs";
    //泰杰赛智能科技公司
	public static final String COMPANY_TJS = "tjs";
    //上海新华研发中心
	public static final String COMPANY_XH = "xh";
    //正泰中自研发中心
	public static final String COMPANY_ZZ = "zz";
    //新能源电站运维事业部
	public static final String COMPANY_XNY = "xny";

	
	
	
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

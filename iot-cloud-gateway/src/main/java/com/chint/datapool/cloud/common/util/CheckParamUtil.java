package com.chint.datapool.cloud.common.util;

import java.util.regex.Pattern;

public class CheckParamUtil {
	
	
	/**
	 * 是否是手机号
	 * @param phone
	 * @return
	 */
	public static boolean isPhone(String phone) {
		Pattern p = Pattern.compile("^[1][3,4,5,6,7,8,9][0-9]{9}$");
		return p.matcher(phone).matches();
	}
	
	public static boolean isNotPhone(String phone) {
		return !isPhone(phone);
	}
	
	/**
	 * 是否是qq号
	 * @param qq
	 * @return
	 */
	public static boolean isQQ(String qq) {
		Pattern p = Pattern.compile("^[0-9]{5,11}$");
		return p.matcher(qq).matches();
	}
	public static boolean isNotQQ(String qq) {
		return !isQQ(qq);
	}

	/**
	 * 是否是微信号码
	 * @param wxNo
	 * @return
	 */
	public static boolean isWxNo(String wxNo) {
		Pattern p = Pattern.compile("^[a-zA-Z][a-zA-Z0-9-_]{5,19}$");
		return p.matcher(wxNo).matches();
	}
	public static boolean isNotWxNo(String wxNo) {
		return !isWxNo(wxNo);
	}
	
}

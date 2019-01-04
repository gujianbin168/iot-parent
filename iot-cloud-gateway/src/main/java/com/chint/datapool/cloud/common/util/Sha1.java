package com.chint.datapool.cloud.common.util;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;

public class Sha1 {
	public static String sha1(String source) {
		return Hex.encodeHexString(DigestUtils.getSha1Digest().digest(StringUtils.getBytesUtf8(source)));
	}
	
/*    //==============================Test=============================================
    *//** 测试 http://tool.oschina.net/encrypt?type=2 *//*
    public static void main(String[] args) {
    	System.out.println(sha1("123456"));
    }*/
}

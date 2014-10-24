package com.hx.dmcp.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import com.hx.dmcp.constant.SystemConstant;

/**
 * @author krisjin
 */
public class EncryptUtils {

	/**
	 * 【邮箱+密码】=密文
	 * 
	 * @param password
	 * @param email
	 * @return
	 */
	public static String getEncrytPassword(String password, String email) {
		if (StringUtils.isBlank(password) || StringUtils.isBlank(email) || StringUtils.isBlank(email)) {

		}
		return DigestUtils.md5Hex(password + email).toLowerCase();
	}

	/**
	 * 单一字符串MD5加密
	 * @param str
	 * @return
	 */
	public static String MD5(String str) {
		return DigestUtils.md5Hex(str).toLowerCase();
	}

	public static String getFaceUrl(String email) {
		return SystemConstant.FACE_URL + "/" + EncryptUtils.MD5(email) + ".jpg";
	}
}

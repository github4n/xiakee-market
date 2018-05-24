package com.xiakee.service.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.xiakee.service.utils.aes.AesEncryptor;
import com.xiakee.service.utils.aes.Encryptor;


public class EncryptorUtil {
	private static final Log log = LogFactory.getLog(EncryptorUtil.class);
    /**
     * 数据加密的key，柏威接口提供的加密密钥
     */
    private static final String AES_ENCRYPT_KEY = "sjzy#=abc@#123*=";
	private static final Encryptor ENCRYPTOR = new AesEncryptor(AES_ENCRYPT_KEY);
	
	public static String encryptorByKey(String key,String value){
		if(StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)){
			Encryptor encryptor = new AesEncryptor(key);
			try {
				value = encryptor.encrypt(value);
			} catch (Exception e) {
				log.debug("===============加密失败=================" + value);
			}
		}
		return value;
	}
	
	public static String decryptByKey(String key,String value){
		if(StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)){
			Encryptor encryptor = new AesEncryptor(key);
			try {
				value = encryptor.decrypt(value);
			} catch (Exception e) {
				log.debug("===============解密失败=================" + value);
			}
		}
		return value;
	}
	
	public static String encryptor(String value){
		try {
			value = ENCRYPTOR.encrypt(value);
		} catch (Exception e) {
			log.debug("===============加密失败=================" + value);
		}
		return value;
	}
	
	public static String decrypt(String value){
		try {
			value = ENCRYPTOR.decrypt(value);
		} catch (Exception e) {
			log.debug("===============解密失败=================" + value);
		}
		return value;
	}
	
	public static void main(String[] args) {
		System.out.println(encryptor("811003"));
//		System.out.println(decrypt("Nx3Yky7qvIeOey3onb+IFg=="));
	}
}

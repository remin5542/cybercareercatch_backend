package com.ccc.common.service;

import java.util.HashMap;
import java.util.Random;

import com.ccc.common.config.PropertyConfig;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

public class SmsService {

	private static final String API_KEY = PropertyConfig.get("coolsms.api_key");
	private static final String API_SECRET = PropertyConfig.get("coolsms.api_secret");
	private static final String FROM_NUMBER = PropertyConfig.get("coolsms.from_number");

	public String sendVerificationSms(String to) throws CoolsmsException {
		Message coolsms = new Message(API_KEY, API_SECRET);
		String verificationCode = generateVerificationCode();

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("to", to);
		params.put("from", FROM_NUMBER);
		params.put("type", "SMS");
		params.put("text", "[CyberCareerCatch] 인증번호는 [" + verificationCode + "] 입니다.");

		Object result = coolsms.send(params);
		System.out.println(String.valueOf(result));

		return verificationCode;
	}

	private String generateVerificationCode() {
		Random random = new Random();
		StringBuilder code = new StringBuilder();

		for (int i = 0; i < 6; i++) {
			code.append(random.nextInt(10));
		}
		return code.toString();
	}
}
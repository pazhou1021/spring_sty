package com.chinasofti.etc.hiq.po;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Language {
	public static String ServerIP;
	public static String language;
	
	// login
	public static String online;
	public static String hiding;
	public static String leave;
	public static String dnd;
	public static String offline;
	public static String accountTip;
	public static String pwdTip;
	public static String registerAccount;
	public static String password;
	public static String forgotPassword;
	public static String rememberPassword;
	public static String autoLogin;
	public static String login;
	public static String err;
	public static String userNameOrPasswordMistake;
	public static String configurationFileNotFound;
	public static String serverConnectionFailed;
	public static String programError;
	public static String accountIllegal;
	
	static {
		// 读取配置文件信息
		File file = new File("HiQConfig.ini");
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(file));
			language = properties.getProperty("language");
			ServerIP = properties.getProperty("ServerIP");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 读取语言包
		File file2 = new File("languages/" + language);
		System.out.println("languages/" + language);
		Properties properties2 = new Properties();
		try {
			properties2.load(new FileInputStream(file2));
			online = properties2.getProperty("online");
			hiding = properties2.getProperty("hiding");
			leave = properties2.getProperty("leave");
			dnd = properties2.getProperty("dnd");
			offline = properties2.getProperty("offline");
			accountTip = properties2.getProperty("accountTip");
			pwdTip = properties2.getProperty("pwdTip");
			registerAccount = properties2.getProperty("registerAccount");
			password = properties2.getProperty("password");
			forgotPassword = properties2.getProperty("forgotPassword");
			rememberPassword = properties2.getProperty("rememberPassword");
			autoLogin = properties2.getProperty("autoLogin");
			login = properties2.getProperty("login");
			err = properties2.getProperty("err");
			userNameOrPasswordMistake = properties2.getProperty("userNameOrPasswordMistake");
			configurationFileNotFound = properties2.getProperty("configurationFileNotFound");
			serverConnectionFailed = properties2.getProperty("serverConnectionFailed");
			programError = properties2.getProperty("programError");
			accountIllegal = properties2.getProperty("accountIllegal");
			
			if (language.equals("zh.lng")) {
				online = new String(online.getBytes("ISO8859-1"));
				hiding = new String(hiding.getBytes("ISO8859-1"));
				leave = new String(leave.getBytes("ISO8859-1"));
				dnd = new String(dnd.getBytes("ISO8859-1"));
				offline = new String(offline.getBytes("ISO8859-1"));
				accountTip = new String(accountTip.getBytes("ISO8859-1"));
				pwdTip = new String(pwdTip.getBytes("ISO8859-1"));
				registerAccount = new String(registerAccount.getBytes("ISO8859-1"));
				password = new String(password.getBytes("ISO8859-1"));
				forgotPassword = new String(forgotPassword.getBytes("ISO8859-1"));
				rememberPassword = new String(rememberPassword.getBytes("ISO8859-1"));
				autoLogin = new String(autoLogin.getBytes("ISO8859-1"));
				login = new String(login.getBytes("ISO8859-1"));
				err = new String(err.getBytes("ISO8859-1"));
				userNameOrPasswordMistake = new String(userNameOrPasswordMistake.getBytes("ISO8859-1"));
				configurationFileNotFound = new String(configurationFileNotFound.getBytes("ISO8859-1"));
				serverConnectionFailed = new String(serverConnectionFailed.getBytes("ISO8859-1"));
				programError = new String(programError.getBytes("ISO8859-1"));
				accountIllegal = new String(accountIllegal.getBytes("ISO8859-1"));
				
			}
			System.out.println(online);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

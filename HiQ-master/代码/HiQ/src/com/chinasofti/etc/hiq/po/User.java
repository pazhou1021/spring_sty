package com.chinasofti.etc.hiq.po;

import java.io.Serializable;
import java.sql.Date;


@SuppressWarnings("serial")
public class User implements Serializable{
	// Ù–‘
	private int userId;
	private int userQQ;
	private String userPassword;
	private String userImage;
	private String userNikName;
	private int userSex;
	private String userAddress;
	private int userAge;
	private String userBirthday;
	private String userEmail;
	private Date userRgDate;
	private int userState;
	private String userIP;
	private int userPort;
	private String userSpeak;
	
	
	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getUserQQ() {
		return userQQ;
	}


	public void setUserQQ(int userQQ) {
		this.userQQ = userQQ;
	}


	public String getUserPassword() {
		return userPassword;
	}


	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}


	public String getUserImage() {
		return userImage;
	}


	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}


	public String getUserNikName() {
		return userNikName;
	}


	public void setUserNikName(String userNikName) {
		this.userNikName = userNikName;
	}


	public int getUserSex() {
		return userSex;
	}


	public void setUserSex(int userSex) {
		this.userSex = userSex;
	}


	public String getUserAddress() {
		return userAddress;
	}


	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}


	public int getUserAge() {
		return userAge;
	}


	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}


	public String getUserBirthday() {
		return userBirthday;
	}


	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public Date getUserRgDate() {
		return userRgDate;
	}


	public void setUserRgDate(Date userRgDate) {
		this.userRgDate = userRgDate;
	}


	public int getUserState() {
		return userState;
	}


	public void setUserState(int userState) {
		this.userState = userState;
	}


	public String getUserIP() {
		return userIP;
	}


	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}


	public int getUserPort() {
		return userPort;
	}


	public void setUserPort(int userPort) {
		this.userPort = userPort;
	}


	public String getUserSpeak() {
		return userSpeak;
	}


	public void setUserSpeak(String userSpeak) {
		this.userSpeak = userSpeak;
	}
    

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

    
	public User(int userQQ, String userPassword, String userImage,
			String userNikName, int userSex, String userAddress, int userAge,
			String userBirthday, String userEmail, Date userRgDate,
			int userState, String userIP, int userPort, String userSpeak) {
		super();
		this.userQQ = userQQ;
		this.userPassword = userPassword;
		this.userImage = userImage;
		this.userNikName = userNikName;
		this.userSex = userSex;
		this.userAddress = userAddress;
		this.userAge = userAge;
		this.userBirthday = userBirthday;
		this.userEmail = userEmail;
		this.userRgDate = userRgDate;
		this.userState = userState;
		this.userIP = userIP;
		this.userPort = userPort;
		this.userSpeak = userSpeak;
	}


	public User(int userId, int userQQ, String userPassword, String userImage,
			String userNikName, int userSex, String userAddress, int userAge,
			String userBirthday, String userEmail, Date userRgDate,
			int userState, String userIP, int userPort, String userSpeak) {
		super();
		this.userId = userId;
		this.userQQ = userQQ;
		this.userPassword = userPassword;
		this.userImage = userImage;
		this.userNikName = userNikName;
		this.userSex = userSex;
		this.userAddress = userAddress;
		this.userAge = userAge;
		this.userBirthday = userBirthday;
		this.userEmail = userEmail;
		this.userRgDate = userRgDate;
		this.userState = userState;
		this.userIP = userIP;
		this.userPort = userPort;
		this.userSpeak = userSpeak;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.userNikName;
	}
	
}
	

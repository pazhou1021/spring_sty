package com.chinasofti.etc.hiq.biz.bizimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.chinasofti.etc.hiq.biz.UserBiz;
import com.chinasofti.etc.hiq.dao.UserDao;
import com.chinasofti.etc.hiq.po.User;

public class UserBizImpl implements UserBiz {

	private static UserDao userDao = null;
	static{
		File file = new File("f:\\myconfig.properties");
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String DaoString = properties.getProperty("tUserDao");
		try {
			userDao = (UserDao)Class.forName(DaoString).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int findMaxUserId() {
		// TODO Auto-generated method stub
		return userDao.findMaxUserId();
	}
	
	public int findMaxUserQQ() {
		// TODO Auto-generated method stub
		return userDao.findMaxUserQQ()	;
	}
	
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return userDao.findAllUser();
	}

	
	public User findUserById(int userId) {
		// TODO Auto-generated method stub
		return userDao.findUserById(userId);
	}

	
	public User findUserByQQ(int userQQ) {
		// TODO Auto-generated method stub
		return userDao.findUserByQQ(userQQ);
	}

	
	public User findUserByNikName(String userNikName) {
		// TODO Auto-generated method stub
		return userDao.findUserByNikName(userNikName);
	}

	
	public int insertUser(User user) {
		// TODO Auto-generated method stub
		return userDao.insertUser(user);
	}

	
	public int deleteUserById(int userId) {
		// TODO Auto-generated method stub
		return userDao.deleteUserById(userId);
	}

	
	public int deleteUserByQQ(String userQQ) {
		// TODO Auto-generated method stub
		return userDao.deleteUserByQQ(userQQ);
	}

	
	public int deleteUserByNikName(String userNikName) {
		// TODO Auto-generated method stub
		return userDao.deleteUserByNikName(userNikName);
	}

	
	public int upateUserByUserId(int userId, User newUser) {
		// TODO Auto-generated method stub
		return userDao.upateUserByUserId(userId, newUser);
	}

	
	public int updateUserByUserQQ(int userQQ, User newUser) {
		// TODO Auto-generated method stub
		return userDao.updateUserByUserQQ(userQQ, newUser);
	}

	
	public int updateUserByNikName(String userNikName, User newUser) {
		// TODO Auto-generated method stub
		return userDao.updateUserByNikName(userNikName, newUser);
	}

	public List<User> findUserWith(Object... params) {
		// TODO Auto-generated method stub
		return userDao.findUserWith(params);
	}

	

}

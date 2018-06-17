package com.chinasofti.etc.hiq.biz.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.chinasofti.etc.hiq.biz.UserBiz;
import com.chinasofti.etc.hiq.dao.UserDao;
import com.chinasofti.etc.hiq.dao.impl.UserDaoImpl;
import com.chinasofti.etc.hiq.po.User;

public class UserBizImpl implements UserBiz {
	private UserDao userDao;
	

	public UserBizImpl() throws FileNotFoundException, IOException {
		super();
		// TODO Auto-generated constructor stub
		userDao = new UserDaoImpl();
	}

	@Override
	public User isValidUser(int userQQ, String userPassword, int userState) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return userDao.isValidUser(userQQ, userPassword, userState);
	}

	@Override
	public Map<String, List<User>> findAllUsers() throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return userDao.findAllUsers();
	}
}

package com.chinasofti.etc.hiq.dao;

import java.util.List;

import com.chinasofti.etc.hiq.po.User;

public interface UserDao {
	//²é£º
	int findMaxUserId();
	int findMaxUserQQ();
	List<User> findAllUser();
	User findUserById(int userId);
	User findUserByQQ(int userQQ);
	User findUserByNikName(String userNikName);
	
	List<User> findUserWith(Object ... params);
	//Ôö£º
	int insertUser(User user);
	//É¾
	int deleteUserById(int userId);
	int deleteUserByQQ(String userQQ);
	int deleteUserByNikName(String userNikName);
	//¸Ä£º
	int upateUserByUserId(int userId,User newUser);
	int updateUserByUserQQ(int userQQ,User newUser);
	int updateUserByNikName(String userNikName,User newUser);
}

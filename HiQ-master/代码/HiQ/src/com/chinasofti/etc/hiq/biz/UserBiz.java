package com.chinasofti.etc.hiq.biz;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.chinasofti.etc.hiq.po.User;

public interface UserBiz {
	// 判断是否是合法用户
	User isValidUser (int userQQ, String userPassword, int userState) throws IOException, ClassNotFoundException;
	// 获取所有用户列表
	Map<String, List<User>> findAllUsers() throws IOException, ClassNotFoundException;
}

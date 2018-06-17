package com.chinasofti.etc.hiq.dao;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.chinasofti.etc.hiq.po.User;

public interface UserDao {
	// �ж��Ƿ��ǺϷ��û�
	User isValidUser (int userQQ, String userPassword, int userState) throws IOException, ClassNotFoundException;
	// ��ȡ�����б�
	Map<String, List<User>> findAllUsers() throws IOException, ClassNotFoundException;
}

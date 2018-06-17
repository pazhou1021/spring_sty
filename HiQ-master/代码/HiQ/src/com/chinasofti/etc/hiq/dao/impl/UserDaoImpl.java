package com.chinasofti.etc.hiq.dao.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

import com.chinasofti.etc.hiq.dao.UserDao;
import com.chinasofti.etc.hiq.po.User;

public class UserDaoImpl implements UserDao {
	private ObjectInputStream objectInputStream = null;
	private ObjectOutputStream objectOutputStream = null;
	private TCPConnect tcpConnect = null;
	
	/**
	 * 构造函数
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public UserDaoImpl() throws FileNotFoundException, IOException {
		super();
		if (TCPConnect.objectInputStream == null) {
			tcpConnect = new TCPConnect();
		}
	}

	/**
	 * 判断是否是合法用户
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@Override
	public User isValidUser(int userQQ, String userPassword, int userState) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		User userMy = null;
		User userSend = new User(userQQ, userPassword, null, null, 0, null, 0, null, null, null, userState, null, 6400, null);
		boolean result = false;
		// 封装对象输出流，向服务器发送本人登陆消息
		objectOutputStream = new ObjectOutputStream(tcpConnect.getOut());
		TCPConnect.objectOutputStream = objectOutputStream;
		objectOutputStream.writeObject(userSend);
		// 接收服务器返回结果
		objectInputStream = new ObjectInputStream(tcpConnect.getIn());
		TCPConnect.objectInputStream = objectInputStream;
		userMy = (User) objectInputStream.readObject();
		if (userMy != null) {
			result = true;
			System.out.println("登陆成功,QQ:" + userMy.getUserQQ() + "UserIP:"+ userMy.getUserIP()
					+ "UserPort:" + userMy.getUserPort());
		}
		System.out.println("result = " + result);
		return userMy;
	}

	/**
	 * 得到所有的好友列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, List<User>> findAllUsers() throws IOException, ClassNotFoundException {
		// 接收所有好友
		Map<String, List<User>> usersMap = null;
		usersMap = (Map<String, List<User>>) TCPConnect.objectInputStream.readObject();
		return usersMap;
	}
}

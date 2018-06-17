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
	 * ���캯��
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
	 * �ж��Ƿ��ǺϷ��û�
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@Override
	public User isValidUser(int userQQ, String userPassword, int userState) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		User userMy = null;
		User userSend = new User(userQQ, userPassword, null, null, 0, null, 0, null, null, null, userState, null, 6400, null);
		boolean result = false;
		// ��װ���������������������ͱ��˵�½��Ϣ
		objectOutputStream = new ObjectOutputStream(tcpConnect.getOut());
		TCPConnect.objectOutputStream = objectOutputStream;
		objectOutputStream.writeObject(userSend);
		// ���շ��������ؽ��
		objectInputStream = new ObjectInputStream(tcpConnect.getIn());
		TCPConnect.objectInputStream = objectInputStream;
		userMy = (User) objectInputStream.readObject();
		if (userMy != null) {
			result = true;
			System.out.println("��½�ɹ�,QQ:" + userMy.getUserQQ() + "UserIP:"+ userMy.getUserIP()
					+ "UserPort:" + userMy.getUserPort());
		}
		System.out.println("result = " + result);
		return userMy;
	}

	/**
	 * �õ����еĺ����б�
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, List<User>> findAllUsers() throws IOException, ClassNotFoundException {
		// �������к���
		Map<String, List<User>> usersMap = null;
		usersMap = (Map<String, List<User>>) TCPConnect.objectInputStream.readObject();
		return usersMap;
	}
}

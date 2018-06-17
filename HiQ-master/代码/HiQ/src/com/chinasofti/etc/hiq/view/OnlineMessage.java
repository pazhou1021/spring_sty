package com.chinasofti.etc.hiq.view;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.chinasofti.etc.hiq.po.User;

public class OnlineMessage implements Runnable {
	private Map<String, List<User>> usersMap = null; // ���к����б�
	private User userMy;
	
	/**
	 * ���캯��
	 */
	public OnlineMessage() {
		super();
	}

	public OnlineMessage(Map<String, List<User>> usersMap) {
		super();
		this.usersMap = usersMap;
	}

	public OnlineMessage(Map<String, List<User>> usersMap, User userMy) {
		super();
		this.usersMap = usersMap;
		this.userMy = userMy;
	}
	/**
	 * ���Ե�get��set����
	 * @return
	 */
	public Map<String, List<User>> getUsersMap() {
		return usersMap;
	}

	public void setUsersMap(Map<String, List<User>> usersMap) {
		this.usersMap = usersMap;
	}

	public User getUserMy() {
		return userMy;
	}

	public void setUserMy(User userMy) {
		this.userMy = userMy;
	}

	/**
	 * �̺߳����������к��ѷ���������Ϣ
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		DatagramSocket datagramSocket = null;
		DatagramPacket datagramPacket = null;
		try {
			datagramSocket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Set<String> groupSet = usersMap.keySet();
		Iterator<String> groupIterator = groupSet.iterator();
		String groupName;
		List<User> users;
		Iterator<User> userIterator;
		User user;
		int PORT = 6400;
		String userIP = null;
		String sendMsg = null;
		byte[] sendData;
		while(groupIterator.hasNext()) { // �������к���
			groupName = groupIterator.next();
			users = usersMap.get(groupName);
			userIterator = users.iterator();
			while (userIterator.hasNext()) {
				user = userIterator.next();
				userIP = user.getUserIP();
				sendMsg = "online/" + userMy.getUserQQ() + "/" + userMy.getUserNikName();
				try {
					if (user.getUserIP() != null) {
						// ����ѷ���������Ϣ
						sendData = sendMsg.getBytes("utf-8");
						datagramPacket = new DatagramPacket(sendData,
								sendData.length, InetAddress.getByName(userIP),
								PORT);
						datagramSocket.send(datagramPacket);
						System.out.println("��" + userIP + PORT + "������������");
					}
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}
	}
}

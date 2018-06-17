package com.chinasofti.etc.hiq.view;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

import com.chinasofti.etc.hiq.po.User;

public class UDPReceive implements Runnable {
	private int PORT = 6400;
	private DatagramSocket datagramSocket;
	private DatagramPacket datagramPacket;
	private User userMy;
	private Map<Integer, Chat> chatList = new HashMap<Integer, Chat>();
	private Map<String, List<User>> usersMap = null;
	/**
	 * 构造函数，获取个人信息、所有好友信息和聊天列表
	 * @param userMy
	 * @param chatList
	 */
	public UDPReceive(User userMy, Map<Integer, Chat> chatList, Map<String, List<User>> usersMap) {
		super();
		this.userMy = userMy;
		// 获取消息池
		this.chatList = chatList;
		this.usersMap = usersMap;
		// 步骤1：实例化DatagramSocekt对象
		try {
			datagramSocket = new DatagramSocket(PORT);
			System.out.println("UDP准备开始接受数据……");
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 *UDP接收信息方法 
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			String[] userMsg;
			String msgType;
			while (true) {
				// 步骤2：实例化datagramPacket对象
				byte[] buf = new byte[512]; // 创建一个字节数组，用于接收字节流数据
				datagramPacket = new DatagramPacket(buf, buf.length); // 接受数据
				// 步骤3：使用DatagramSocekt对象的receive()方法打开服务器端的接受监听
				datagramSocket.receive(datagramPacket);
				// 步骤4：将数据打印显示在控制台
				String str = new String(datagramPacket.getData(),"utf-8");
				System.out.println("接收到消息"+str.trim());
				userMsg = str.trim().split("/");
				msgType = userMsg[0];
				// 分析消息类型
				if (msgType.equals("info")) {
					System.out.println("接收到来自" + datagramPacket.getAddress()+ "PORT:" + datagramPacket.getPort() + "的消息"+ str.trim());
					if (userMsg.length >= 4) {
						Integer key = Integer.parseInt(userMsg[1]);
						Chat chatDialog;
						if (chatList.containsKey(key)) {
							chatDialog = chatList.get(key);
							System.out.println("存在");
						} else {
							System.out.println("IP"
									+ datagramSocket.getInetAddress() + " "
									+ datagramSocket.getPort());
							chatDialog = new Chat(userMy, key, userMsg[2],
									datagramPacket.getAddress().toString().substring(1), chatList);
							chatList.put(key, chatDialog);
							System.out.println("不存在");
							System.out.println(chatDialog);
						}
						Date date = new Date();
						SimpleDateFormat matter = new SimpleDateFormat(
								"y年 M月d日H时m分s秒");
						String time = matter.format(date);
						// 将消息发送人得昵称和发送的信息添加到聊天框
						chatDialog.appendReceiveText(userMsg[2] + " "
								+ time + "\n" + userMsg[3], null);
					}
				}
				else if (msgType.equals("online")) {
					// 接收到用户上线消息
					new TipWindow("上线通知", userMsg[2] + "上线");
					// 更新上线好友的IP
					boolean find = false;
					Set<String> set = usersMap.keySet();
					Iterator<String> iterator = set.iterator();
					while (iterator.hasNext()) {
						String groupName = iterator.next();
						List<User> users = usersMap.get(groupName);
						Iterator<User> iterator2 = users.iterator();
						while (iterator2.hasNext()) {
							User user = iterator2.next();
							if (user.getUserQQ() == Integer.parseInt(userMsg[1])) {
								user.setUserIP(datagramPacket.getAddress().toString().substring(1));
								find = true;
								break;
							}
						}
						if (find) {
							break;
						}
					}
					System.out.println("上线通知" + userMsg[2]);
				} else if (msgType.equals("offline")) {
					// 接收到用户下线消息
					new TipWindow("下线通知", userMsg[2] + "下线");
					System.out.println("下线通知" + userMsg[1]);
				} else if (msgType.startsWith("来自服务器")) {
					JOptionPane.showMessageDialog(null, msgType);
				} else if (msgType.equals("shake")) {
					Integer key = Integer.parseInt(userMsg[1]);
					Chat chatDialog;
					if (chatList.containsKey(key)) {
						chatDialog = chatList.get(key);
						System.out.println("存在");
					} else {
						chatDialog = new Chat(userMy, key, userMsg[2],
								datagramPacket.getAddress().toString().substring(1), chatList);
						chatList.put(key, chatDialog);
					}
					new ShakeWindow(chatDialog);
				} else if (msgType.equals("sendfile")) {
					Integer key = Integer.parseInt(userMsg[1]);
					Chat chatDialog;
					if (chatList.containsKey(key)) {
						chatDialog = chatList.get(key);
						System.out.println("存在");
					} else {
						chatDialog = new Chat(userMy, key, userMsg[2],
								datagramPacket.getAddress().toString().substring(1), chatList);
						chatList.put(key, chatDialog);
					}
					int returnVal = JOptionPane.showConfirmDialog(null, "是否接收文件" + userMsg[3], "提示", JOptionPane.YES_NO_OPTION);
					new RecvFile(returnVal, datagramPacket.getAddress().toString().substring(1), userMsg[3]);
				}
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

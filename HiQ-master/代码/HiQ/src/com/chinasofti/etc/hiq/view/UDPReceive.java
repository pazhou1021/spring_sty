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
	 * ���캯������ȡ������Ϣ�����к�����Ϣ�������б�
	 * @param userMy
	 * @param chatList
	 */
	public UDPReceive(User userMy, Map<Integer, Chat> chatList, Map<String, List<User>> usersMap) {
		super();
		this.userMy = userMy;
		// ��ȡ��Ϣ��
		this.chatList = chatList;
		this.usersMap = usersMap;
		// ����1��ʵ����DatagramSocekt����
		try {
			datagramSocket = new DatagramSocket(PORT);
			System.out.println("UDP׼����ʼ�������ݡ���");
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 *UDP������Ϣ���� 
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			String[] userMsg;
			String msgType;
			while (true) {
				// ����2��ʵ����datagramPacket����
				byte[] buf = new byte[512]; // ����һ���ֽ����飬���ڽ����ֽ�������
				datagramPacket = new DatagramPacket(buf, buf.length); // ��������
				// ����3��ʹ��DatagramSocekt�����receive()�����򿪷������˵Ľ��ܼ���
				datagramSocket.receive(datagramPacket);
				// ����4�������ݴ�ӡ��ʾ�ڿ���̨
				String str = new String(datagramPacket.getData(),"utf-8");
				System.out.println("���յ���Ϣ"+str.trim());
				userMsg = str.trim().split("/");
				msgType = userMsg[0];
				// ������Ϣ����
				if (msgType.equals("info")) {
					System.out.println("���յ�����" + datagramPacket.getAddress()+ "PORT:" + datagramPacket.getPort() + "����Ϣ"+ str.trim());
					if (userMsg.length >= 4) {
						Integer key = Integer.parseInt(userMsg[1]);
						Chat chatDialog;
						if (chatList.containsKey(key)) {
							chatDialog = chatList.get(key);
							System.out.println("����");
						} else {
							System.out.println("IP"
									+ datagramSocket.getInetAddress() + " "
									+ datagramSocket.getPort());
							chatDialog = new Chat(userMy, key, userMsg[2],
									datagramPacket.getAddress().toString().substring(1), chatList);
							chatList.put(key, chatDialog);
							System.out.println("������");
							System.out.println(chatDialog);
						}
						Date date = new Date();
						SimpleDateFormat matter = new SimpleDateFormat(
								"y�� M��d��Hʱm��s��");
						String time = matter.format(date);
						// ����Ϣ�����˵��ǳƺͷ��͵���Ϣ��ӵ������
						chatDialog.appendReceiveText(userMsg[2] + " "
								+ time + "\n" + userMsg[3], null);
					}
				}
				else if (msgType.equals("online")) {
					// ���յ��û�������Ϣ
					new TipWindow("����֪ͨ", userMsg[2] + "����");
					// �������ߺ��ѵ�IP
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
					System.out.println("����֪ͨ" + userMsg[2]);
				} else if (msgType.equals("offline")) {
					// ���յ��û�������Ϣ
					new TipWindow("����֪ͨ", userMsg[2] + "����");
					System.out.println("����֪ͨ" + userMsg[1]);
				} else if (msgType.startsWith("���Է�����")) {
					JOptionPane.showMessageDialog(null, msgType);
				} else if (msgType.equals("shake")) {
					Integer key = Integer.parseInt(userMsg[1]);
					Chat chatDialog;
					if (chatList.containsKey(key)) {
						chatDialog = chatList.get(key);
						System.out.println("����");
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
						System.out.println("����");
					} else {
						chatDialog = new Chat(userMy, key, userMsg[2],
								datagramPacket.getAddress().toString().substring(1), chatList);
						chatList.put(key, chatDialog);
					}
					int returnVal = JOptionPane.showConfirmDialog(null, "�Ƿ�����ļ�" + userMsg[3], "��ʾ", JOptionPane.YES_NO_OPTION);
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

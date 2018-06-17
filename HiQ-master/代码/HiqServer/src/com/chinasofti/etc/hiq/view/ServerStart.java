package com.chinasofti.etc.hiq.view;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


import javax.swing.JButton;

import javax.swing.JFrame;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

import com.chinasofti.etc.hiq.biz.InitDataBaseBiz;
import com.chinasofti.etc.hiq.biz.UserBiz;
import com.chinasofti.etc.hiq.biz.bizimpl.InitDataBaseBizImpl;
import com.chinasofti.etc.hiq.biz.bizimpl.UserBizImpl;
import com.chinasofti.etc.hiq.po.User;






@SuppressWarnings("serial")
public class ServerStart extends JFrame {
	
    
    //
	private JButton btnDeleteUser,btnStartServer,btnCloseServer,btnSendMessage,btnrefreshUsers;
	private JPanel panel;
	private JScrollPane sp1,sp2,sendSp,receiveSp;
	private JTable table1,table2;
	private JTextPane receiveText,sendText;
	private JSeparator jsprReSd1,jsprReSd2;
	StartServerThread startServerThread;
//	private Server server;
	
	public ServerStart(){
		Init();
	}
	private void Init(){
		
		//�����ˢ���¼����ó�������ֹ����ˢ�´���
		sp2 = new JScrollPane();
		sp1 = new JScrollPane();
		
		this.repaint();
		//���ñ���
		this.setTitle("��������"); 
		//��С
		this.setSize(new Dimension(800,600));
		//������ʾ
		this.setLocationRelativeTo(this);
		//�ر�Ĭ�ϲ���
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//���ÿ����Ƿ����
		this.setResizable(false);
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//�������ĵĲ��ָ�ʽ
		this.panel = (JPanel)this.getContentPane();
		this.panel.setLayout(null);
		
		//������ť,������λ��
		this.btnDeleteUser = new JButton("ɾ���û�");
		this.btnDeleteUser.setBounds(10,530, 100, 30);
		
		
	    //����ˢ�������û��İ�ť
		this.btnrefreshUsers = new JButton("ˢ��");
		this.btnrefreshUsers.setBounds(150,530, 100, 30);
		//����
		this.btnrefreshUsers.addActionListener(new refreshUsersListener());
		
		
		this.btnStartServer = new JButton("����������");
		this.btnStartServer.setBounds(500,530,100,30);
		//����������ť�Ӽ���
		this.btnStartServer.addActionListener(new startServerListener());
		
		
		this.btnCloseServer = new JButton("�رշ�����");
		this.btnCloseServer.setBounds(690,530,100,30);
		//���رհ�ť���ü����¼�
		this.btnCloseServer.addActionListener(new closeServerListener());
		
		
		//�������水ť
		this.btnSendMessage = new JButton("��������");
		this.btnSendMessage.addActionListener(new sendActionListener());
		this.btnSendMessage.setBounds(690,450,100,30);
		
		
		//���͹���������
		    //���տ�
		this.receiveText = new JTextPane();
		
		receiveText.setInheritsPopupMenu(true);
		receiveText.setBounds(500, 10, 280, 300);
		receiveText.setVerifyInputWhenFocusTarget(false);
		receiveText.setDragEnabled(true);
		receiveText.setMargin(new Insets(0, 0, 0, 0));
		receiveText.setEditable(false);
		
		receiveSp = new JScrollPane();
		receiveSp.getViewport().add(receiveText);
		receiveSp.setBounds(500, 10, 280, 300);
		panel.add(receiveSp);
		

		//�߿�	
		this.jsprReSd1 = new JSeparator();
		this.jsprReSd1.setBounds(500, 310, 280, 10);
		
		//�߿�	
		this.jsprReSd2 = new JSeparator();
		this.jsprReSd2.setBounds(500, 350, 280, 10);
		
		    //���Ϳ�
		this.sendText = new JTextPane();
		this.sendText.setBounds(500, 350, 280, 100);
		
		this.sendText.addKeyListener(new KeyAdapter());
		
		
		sendSp = new JScrollPane();
		sendSp.getViewport().add(sendText);
		sendSp.setBounds(500, 350, 280, 100);
		
		panel.add(sendSp);
		
		this.panel.add(this.btnDeleteUser);
		this.panel.add(this.btnStartServer);
		this.panel.add(this.btnCloseServer);
		this.panel.add(this.btnSendMessage);
		this.panel.add(btnrefreshUsers);
		
		this.panel.add(jsprReSd1);
		this.panel.add(jsprReSd2);
		

	}
	class KeyAdapter implements KeyListener{

	
		public void keyPressed(KeyEvent e) {	
			if(e.getKeyCode() == KeyEvent.VK_ENTER){	
					String sendInfo = getSendInfo();	//��ȡ��Ϣ�����ı�
					if (sendInfo == null){			
						new IOException().printStackTrace();		
						return;
					}
					//�������û���������*****************************
					try {
						String sendMessage = "���Է������Ĺ���:\n" + sendInfo;
						byte[] sendData = null;
						try {
							sendData = sendMessage.getBytes("utf-8");
						} catch (UnsupportedEncodingException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						Iterator<User> iterator = Server.users.iterator();
						while (iterator.hasNext()){
							User user = iterator.next();
							DatagramSocket datagramSocket = new DatagramSocket();
							DatagramPacket datagramPacket = null;
							try {
								datagramPacket = new DatagramPacket(sendData,sendData.length,InetAddress.getByName(user.getUserIP()), 6400);
							} catch (UnknownHostException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try {
								datagramSocket.send(datagramPacket);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						//System.out.println(userMy.getUserQQ() + "��" + userQQ + " " + userIP + "������Ϣ" + sendMsg);
						
					} catch (SocketException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//�������û���������**************end***************
					
					
					appendReceiveText(sendInfo + "\n", null);	//׷�ӷ�����Ϣ
					sendText.setText(null);
					sendText.requestFocus();
				}
			}

		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		
	}
	//�������������¼������¼���ʵ��
	class  startServerListener implements ActionListener{

		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getActionCommand().equals("����������")){
				//��ʼ�����ݿ�
				System.out.println("��ʼ�����ݿ�.....");
				InitDataBaseBiz initDataBaseBiz = new InitDataBaseBizImpl();
				initDataBaseBiz.InitDataBase();
				//����������
				startServerThread = new StartServerThread();
				startServerThread.start();
				System.out.println("-***********����������*************\n\n\n");
			}
		}
	}
	//*******************************end********************
	
	//�رշ������ļ����¼���ʵ��
	class closeServerListener implements ActionListener{
		
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//�رշ�����
			System.out.println("�رշ���������");
			startServerThread.stop();
		}
		
	}
	//**************************end***************
	
	
	//ˢ�������û����¼�ʵ��
	class refreshUsersListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("ˢ����������");
			//*******************************************************
			UserBiz userBiz = new UserBizImpl();
	        List<User> allUsers = userBiz.findAllUser();
	            
	        //��������ע���û�
	        String[] cols2 = new String[]{"�����û�"};
	        Object[][] data2 = new Object[100][1];
	        Iterator<User> it = allUsers.iterator();
	        int i = 0;
	        while (it.hasNext()){
	           User user = it.next();
			   System.out.println(">>>>>>" + user.getUserNikName());
			   data2[i][0] = (Object)user.getUserNikName().toString();
			   i++;
	        }
			
			
			//������ߺ���
	        String[] cols1 = new String[]{"�����û�"};
			Set<User> users = Server.users;
			System.out.println("NININIkan:" + users);
			Object[][] data1 = new Object[100][1];
			if (users != null){
				System.out.println("***************&&&%&%^%^$%^$^&*");
				Iterator<User> iterator = users.iterator();
				i = 0;
				while (iterator.hasNext()){
					User user = iterator.next();
					System.out.println("���ߺ���>>>>>>" + user.getUserNikName());
					data1[i][0] = (Object)user.getUserNikName().toString();
					i++;
				}
				int rows = users.size();
	            System.out.println("��С��" + rows);
			}
			else{
				System.out.println("�����û�Ϊ0");
			}

				//repaint();
				//ʵ�������ؼ�,����˳�� 1:���� 2:����
				table1 = new JTable(data1,cols1);
				table2 = new JTable(data2,cols2);
				table1.setEnabled(false);
				table2.setEnabled(false);
//				table1.setBounds(0, 0, 250, 500);
//				table2.setBounds(250, 250, 250, 500);
				//�����п�
				table1.setRowHeight(30);
				table2.setRowHeight(30);
				//����sp��λ��
				
				sp1.setBounds(0, 0, 250, 500);
				sp2.setBounds(250, 0, 250, 500);
				//�����ؼ���ӵ�ScollPanel�����
				sp1.getViewport().add(table1);
				sp2.getViewport().add(table2);
					
				//container.add(sp);
				
					
				panel.add(sp1);
				panel.add(sp2);
				//����һ������ʼ������
				repaint();
			
			
		}
				
	}
		
	
	//*******************end***********
	
	//�������������¼�����*********
	class sendActionListener implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			String sendInfo = getSendInfo();	//��ȡ��Ϣ�����ı�
			System.out.println("���͹�����ϢΪ��ϢΪ��" + sendInfo);
			if (sendInfo == null){
				new IOException().printStackTrace();
				return;
			}
			//�������û���������*****************************
			try {
				String sendMessage = "���Է������Ĺ���:\n" + sendInfo;
				byte[] sendData = null;
				try {
					sendData = sendMessage.getBytes("utf-8");
				} catch (UnsupportedEncodingException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				Iterator<User> iterator = Server.users.iterator();
				while (iterator.hasNext()){
					User user = iterator.next();
					DatagramSocket datagramSocket = new DatagramSocket();
					DatagramPacket datagramPacket = null;
					try {
						datagramPacket = new DatagramPacket(sendData,sendData.length,InetAddress.getByName(user.getUserIP()), 6400);
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						datagramSocket.send(datagramPacket);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				//System.out.println(userMy.getUserQQ() + "��" + userQQ + " " + userIP + "������Ϣ" + sendMsg);
				
			} catch (SocketException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//�������û���������**************end***************
			
			
			appendReceiveText(sendInfo + "\n", null);	//׷�ӷ�����Ϣ
			sendText.setText(null);
			sendText.requestFocus();
		}
	}
	public void appendReceiveText(String sendInfo, Color color) {
		Style style = receiveText.addStyle("title", null);
		if (color != null) {
			StyleConstants.setForeground(style, color);
		} else {
			StyleConstants.setForeground(style, Color.BLACK);
		}
		receiveText.setEditable(true);
		receiveText.setCaretPosition(receiveText.getDocument().getLength());
		
		receiveText.replaceSelection(sendInfo + "\n");
		receiveText.setEditable(false);
	}
	public JTextPane getReceiveText() {
		return receiveText;
	}
	public String getSendInfo() {
		String sendInfo = "";
		Document doc = sendText.getDocument();
		try {
			sendInfo = doc.getText(0, doc.getLength());
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		if (sendInfo.equals("")) {
			JOptionPane.showMessageDialog(ServerStart.this, "���ܷ��Ϳ���Ϣ");
			return null;
		}
		return sendInfo;
	}
	//�������������¼�����*************end****************

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ServerStart serverStart = new ServerStart();
		serverStart.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}

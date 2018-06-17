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
		
		//这里从刷新事件里拿出来，防止出现刷新错误
		sp2 = new JScrollPane();
		sp1 = new JScrollPane();
		
		this.repaint();
		//设置标题
		this.setTitle("服务器端"); 
		//大小
		this.setSize(new Dimension(800,600));
		//居中显示
		this.setLocationRelativeTo(this);
		//关闭默认操作
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//设置框体是否可以
		this.setResizable(false);
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//设置面板的的布局格式
		this.panel = (JPanel)this.getContentPane();
		this.panel.setLayout(null);
		
		//声明按钮,并设置位置
		this.btnDeleteUser = new JButton("删除用户");
		this.btnDeleteUser.setBounds(10,530, 100, 30);
		
		
	    //声明刷新在线用户的按钮
		this.btnrefreshUsers = new JButton("刷新");
		this.btnrefreshUsers.setBounds(150,530, 100, 30);
		//监听
		this.btnrefreshUsers.addActionListener(new refreshUsersListener());
		
		
		this.btnStartServer = new JButton("启动服务器");
		this.btnStartServer.setBounds(500,530,100,30);
		//给启动服务按钮加监听
		this.btnStartServer.addActionListener(new startServerListener());
		
		
		this.btnCloseServer = new JButton("关闭服务器");
		this.btnCloseServer.setBounds(690,530,100,30);
		//给关闭按钮设置监听事件
		this.btnCloseServer.addActionListener(new closeServerListener());
		
		
		//发布公告按钮
		this.btnSendMessage = new JButton("发布公告");
		this.btnSendMessage.addActionListener(new sendActionListener());
		this.btnSendMessage.setBounds(690,450,100,30);
		
		
		//发送公布栏设置
		    //接收框
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
		

		//边框	
		this.jsprReSd1 = new JSeparator();
		this.jsprReSd1.setBounds(500, 310, 280, 10);
		
		//边框	
		this.jsprReSd2 = new JSeparator();
		this.jsprReSd2.setBounds(500, 350, 280, 10);
		
		    //发送框
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
					String sendInfo = getSendInfo();	//获取信息发送文本
					if (sendInfo == null){			
						new IOException().printStackTrace();		
						return;
					}
					//向所有用户发布公告*****************************
					try {
						String sendMessage = "来自服务器的公告:\n" + sendInfo;
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
						//System.out.println(userMy.getUserQQ() + "向" + userQQ + " " + userIP + "发送消息" + sendMsg);
						
					} catch (SocketException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//向所有用户发布公告**************end***************
					
					
					appendReceiveText(sendInfo + "\n", null);	//追加发送信息
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
	//启动服务器的事件监听事件的实现
	class  startServerListener implements ActionListener{

		
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getActionCommand().equals("启动服务器")){
				//初始化数据库
				System.out.println("初始化数据库.....");
				InitDataBaseBiz initDataBaseBiz = new InitDataBaseBizImpl();
				initDataBaseBiz.InitDataBase();
				//启动服务器
				startServerThread = new StartServerThread();
				startServerThread.start();
				System.out.println("-***********服务器启动*************\n\n\n");
			}
		}
	}
	//*******************************end********************
	
	//关闭服务器的监听事件的实现
	class closeServerListener implements ActionListener{
		
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//关闭服务器
			System.out.println("关闭服务器启动");
			startServerThread.stop();
		}
		
	}
	//**************************end***************
	
	
	//刷新在线用户的事件实现
	class refreshUsersListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("刷新啦。。。");
			//*******************************************************
			UserBiz userBiz = new UserBizImpl();
	        List<User> allUsers = userBiz.findAllUser();
	            
	        //查找所有注册用户
	        String[] cols2 = new String[]{"所有用户"};
	        Object[][] data2 = new Object[100][1];
	        Iterator<User> it = allUsers.iterator();
	        int i = 0;
	        while (it.hasNext()){
	           User user = it.next();
			   System.out.println(">>>>>>" + user.getUserNikName());
			   data2[i][0] = (Object)user.getUserNikName().toString();
			   i++;
	        }
			
			
			//获得在线好友
	        String[] cols1 = new String[]{"在线用户"};
			Set<User> users = Server.users;
			System.out.println("NININIkan:" + users);
			Object[][] data1 = new Object[100][1];
			if (users != null){
				System.out.println("***************&&&%&%^%^$%^$^&*");
				Iterator<User> iterator = users.iterator();
				i = 0;
				while (iterator.hasNext()){
					User user = iterator.next();
					System.out.println("在线好友>>>>>>" + user.getUserNikName());
					data1[i][0] = (Object)user.getUserNikName().toString();
					i++;
				}
				int rows = users.size();
	            System.out.println("大小：" + rows);
			}
			else{
				System.out.println("在线用户为0");
			}

				//repaint();
				//实例化表格控件,参数顺序 1:内容 2:标题
				table1 = new JTable(data1,cols1);
				table2 = new JTable(data2,cols2);
				table1.setEnabled(false);
				table2.setEnabled(false);
//				table1.setBounds(0, 0, 250, 500);
//				table2.setBounds(250, 250, 250, 500);
				//设置行宽
				table1.setRowHeight(30);
				table2.setRowHeight(30);
				//设置sp的位置
				
				sp1.setBounds(0, 0, 250, 500);
				sp2.setBounds(250, 0, 250, 500);
				//将表格控件添加到ScollPanel面板中
				sp1.getViewport().add(table1);
				sp2.getViewport().add(table2);
					
				//container.add(sp);
				
					
				panel.add(sp1);
				panel.add(sp2);
				//创建一个表格初始化方法
				repaint();
			
			
		}
				
	}
		
	
	//*******************end***********
	
	//点击发布公告的事件监听*********
	class sendActionListener implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			String sendInfo = getSendInfo();	//获取信息发送文本
			System.out.println("发送公告消息为消息为：" + sendInfo);
			if (sendInfo == null){
				new IOException().printStackTrace();
				return;
			}
			//向所有用户发布公告*****************************
			try {
				String sendMessage = "来自服务器的公告:\n" + sendInfo;
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
				//System.out.println(userMy.getUserQQ() + "向" + userQQ + " " + userIP + "发送消息" + sendMsg);
				
			} catch (SocketException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//向所有用户发布公告**************end***************
			
			
			appendReceiveText(sendInfo + "\n", null);	//追加发送信息
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
			JOptionPane.showMessageDialog(ServerStart.this, "不能发送空信息");
			return null;
		}
		return sendInfo;
	}
	//点击发布公告的事件监听*************end****************

	
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

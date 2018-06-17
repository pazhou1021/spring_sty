package com.chinasofti.etc.hiq.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.Timer;


import com.chinasofti.etc.hiq.biz.FriendsRsBiz;
import com.chinasofti.etc.hiq.biz.UserBiz;
import com.chinasofti.etc.hiq.biz.bizimpl.FriendsRsBizImpl;
import com.chinasofti.etc.hiq.biz.bizimpl.UserBizImpl;
import com.chinasofti.etc.hiq.po.FriendsRs;
import com.chinasofti.etc.hiq.po.MessagePackge;
import com.chinasofti.etc.hiq.po.User;




public class Server {
	//存放来自客户端的socket与客户端发来的用于通信的port
    public static Set<User> users = new HashSet<User>();//方便显示在线好友时的调用
	private int serverPORT = 9997;
	private ExecutorService pool = Executors.newCachedThreadPool();
	private InputStream in;
	private OutputStream out;
	
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;
	
	private UserBiz userBiz = new UserBizImpl();
	private FriendsRsBiz friendsRsBiz = new FriendsRsBizImpl();
	private static ServerSocket serverSocket;
	
	
	//set get
	 public Set<User> getUsers() {
		 System.out.println("D大小：" + users.size());
		return users;
	}
	
	public ServerSocket getServerSocket() {
		return serverSocket;
	}
	
	
	private synchronized User getResult(Socket socket){
		User user = null;
		try {
			//获得该用户socket的输入输出
//			in = socket.getInputStream();
//			out = socket.getOutputStream();
			
//			ObjectInputStream objectInputStream = new ObjectInputStream(in);
			//ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);//注意后面要构造pw所以这里千万不能用
			
			try {
				//接收来自客户端User
				System.out.println("接受来自客户端的用户：");
				User user1 = (User)objectInputStream.readObject();
				
				System.out.println("登陆用户的 QQ + 密码：" + user1.getUserQQ() + "  " + user1.getUserPassword() + ">>>");
				//在数据库中查看是否存在
				user = userBiz.findUserByQQ(user1.getUserQQ());
				//System.out.println("数据库中存在的用户:" + user.getUserId() + user.getUserQQ() + user.getUesrEmail());
				//如果用户存在并且密码符合要求
				if(user != null && user.getUserPassword().trim().equals(user1.getUserPassword().trim())){
					//设置该用户的IP Port 登陆状态
					user.setUserState(user1.getUserState());
					
					
					//获取客户端的IP
					String strUserIP = socket.getInetAddress().toString();
					String userIP = strUserIP.substring(1,strUserIP.length());
					user.setUserIP(userIP);
//					//获取客户端的PORT
//					int userPort = user1.getUserPort();
//					user.setUserPort(userPort);
				}//否则
				else{
					user = null;
				}
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
	public Server(){
		try {
			
			serverSocket = new ServerSocket(serverPORT);
			Socket socket = new Socket();
			while (true){
				
				//等待接收来自客户端的链接
				System.out.println("等待接收来自客户端的链接");
				socket = serverSocket.accept();
				System.out.println("用户建立连接");
				
				//创建输入输出流
				in = socket.getInputStream();
				out = socket.getOutputStream();
				objectInputStream = new ObjectInputStream(in);
				objectOutputStream = new ObjectOutputStream(out);
				
//		        //初始化上线时的时间
//				Date dTime = new Date();
//				SimpleDateFormat format = new SimpleDateFormat("H");
//				agoTime = format.format(dTime);
				
				//查看数据库是否存在该用户
				User user = getResult(socket);
				
				//获得输入输出
//				in = socket.getInputStream();
//				out = socket.getOutputStream();
				
//				InputStreamReader inReader = new InputStreamReader(in);
//				BufferedReader br = new BufferedReader(inReader);
//				PrintWriter pw = new PrintWriter(out,true);
				
//				ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
								
				//非法用户
				if (user == null){
					System.out.println("非法合法用户");
//					pw.println("0");//向客户端发送0表示用户非法
					objectOutputStream.writeObject(null);
					socket.close();//关闭该链接
				}//合法用户
				else{
					System.out.println("合法用户" + "  " + user.getUserNikName());
					//纯out发送
//					String toStr = "1";
//					byte[] send = toStr.getBytes();
//					System.out.println("发送的消息" + send.toString() + "\n\n");
//					out.write(send);
					//直接把用户发过去
					objectOutputStream.writeObject(user);
					
//					pw.print("1");//向客户端发送1表示用户合法
					//加入在线用户中
					
					users.add(user);
					System.out.println("\n\n\n已将用户加入在线好友中  共有几个好友在线" + users.size() + "\n\n");
					//创建线程
					ClientThread clientThread = new ClientThread();
					//初始化user 与 socket
					clientThread.setUser(user);
					clientThread.setSocket(socket);
					clientThread.setObjectInputStream(objectInputStream);
					clientThread.setObjectOutputStream(objectOutputStream);
					//加入线程池
					pool.execute(clientThread);
				}
				
//				pw.close(); br.close();
//				inReader.close();
//				in.close(); out.close();
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block;
			e.printStackTrace();
		}
	}
	
	
	public class ClientThread implements Runnable{
		private Timer time;
		private User user = new User();//当前用户
		private Socket socket;
		private ObjectInputStream objectInputStream;
		private ObjectOutputStream objectOutputStream;
//		private InputStream in;
//		private OutputStream out;
		
		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Socket getSocket() {
			return socket;
		}

		public void setSocket(Socket socket) {
			this.socket = socket;
		}
		
        public ObjectInputStream getObjectInputStream() {
			return objectInputStream;
		}

		public void setObjectInputStream(ObjectInputStream objectInputStream) {
			this.objectInputStream = objectInputStream;
		}

		public ObjectOutputStream getObjectOutputStream() {
			return objectOutputStream;
		}

		public void setObjectOutputStream(ObjectOutputStream objectOutputStream) {
			this.objectOutputStream = objectOutputStream;
		}

		//如果在特定时间内没有给服务器发送消息，就说明其下线
		class closeThread implements ActionListener{

			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//检测到此用户一下线，首先在在线好友里表中删除该好友
				Iterator<User> iterator = users.iterator();
				while (iterator.hasNext()){
					User user1 = iterator.next();
					if (user1.getUserQQ() == user.getUserQQ()){
						iterator.remove();
						break;
					}
				}
				//然后关闭线程
				System.out.println("此用户已经下线，结束线程....");
				Thread.currentThread().stop();
			}
			
		}
		@SuppressWarnings("deprecation")
		public void run() {
			// TODO Auto-generated method stub
			
			//初始化定时器
			time = new Timer(6*60*1000, new closeThread());
			time.start();
			
			
			//通过当前用户的QQ获得其分组 + 好友
			Map<String,List<User>> friends = new HashMap<String, List<User>>();
			friends = friendsRsBiz.findFriendsByUserQQ(user.getUserQQ());
			//遍历分组，以及当连入数据库的用户获得每个用户的状态
			Set<String> groupNames =  friends.keySet();
			Iterator<String> iterator = groupNames.iterator();
			//第一层遍历分组        
			while (iterator.hasNext()){
				String groupName = iterator.next();
				//得到每个分组的List<User>
				Iterator<User> it = friends.get(groupName).iterator();
				//第二层遍历List
				while (it.hasNext()){
					User user1 = it.next();//得到该层好友
					//第三层遍历已连接到数据库的用户
					Iterator<User> it2 = users.iterator();
					boolean flag = false;//标志已连接到数据库中的用户中是否存在该用户
					while (it2.hasNext()){
						User user2 = it2.next();
						//如果在已连接到数据库的用户中存在的话，就将该用户的状态给他
						if (user1.getUserQQ() == user2.getUserQQ()){
							//更新Map用户的状态
							user1.setUserState(user2.getUserState());
							//端口
							user1.setUserPort(user2.getUserPort());
							//IP
							user1.setUserIP(user2.getUserIP());
							
							flag = true;//存在
							break;
						}
					}
					//不存在    //离线状态
					if (!flag){
						user1.setUserState(4);
					}
				}
			}
		    try {
		    	//获得输出流
//				 out = socket.getOutputStream();
//				ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
				//将Map<分组，用户列表>发给客户端
				objectOutputStream.writeObject(friends);
			    
				
//				in = socket.getInputStream();
//				ObjectInputStream objectInputStream = new ObjectInputStream(in);
				//*************接收心跳包**************
				while (true){
					try {
						
						MessagePackge messagePackge = (MessagePackge)objectInputStream.readObject();
						
						System.out.println("来自用户的包：" + messagePackge.getMessageType() + "   " + messagePackge.getMessage());
						System.out.println("************重启定时器,该用户还在线****************");
						time.restart();//在特定的时间内接收到了消息，重新计数
						
						if (messagePackge.getMessageType() == 1){
							continue;
						}
						
						//如果类型是0表示关闭线程
						if (messagePackge.getMessageType() == 0){
							System.out.println("用户自动退出Hiq。。。。");
							//将该好友从在线好友中移除
							Iterator<User> iterator2 = users.iterator();
							while (iterator2.hasNext()){
								User user1 = iterator2.next();
								if (user1.getUserQQ() == user.getUserQQ()){
									iterator2.remove();
									break;
								}
							}
							Thread.currentThread().stop();
							break;
						}//如果类型3表示按照QQ号超找好友
						else if(messagePackge.getMessageType()==2){
							String userQQ = messagePackge.getMessage();
							User user = userBiz.findUserByQQ(Integer.parseInt(userQQ));
							objectOutputStream.writeObject(user);//将查找到的好友发送给用户
						}//按照QQ号添加好友
						else if (messagePackge.getMessageType() == 22){
							//获得要添加的好友的QQ号
							String userQQ = messagePackge.getMessage().trim();
							//
							User user22 = userBiz.findUserByQQ(Integer.parseInt(userQQ));
							//创建好友关系，
							FriendsRs friendsRs1 = new FriendsRs(0,user.getUserQQ(),user22.getUserId(),"同学");
							FriendsRs friendsRs2 = new FriendsRs(0,user22.getUserQQ(),user.getUserId(),"同学");
							//插入好友
							friendsRsBiz.insertFriendsRs(friendsRs1);
							friendsRsBiz.insertFriendsRs(friendsRs2);	
						}//类型3表示按照用户昵称查找好友
						else if (messagePackge.getMessageType() == 3){
							String userNikName = messagePackge.getMessage().trim();
							User user = userBiz.findUserByNikName(userNikName);
							objectOutputStream.writeObject(user);//将查找到得好友发送给用户
						}//44根据好友添加
						else if (messagePackge.getMessageType() == 33){
							//获得要添加的好友的昵称
							String userNikName = messagePackge.getMessage().trim();
							//
							User user22 = userBiz.findUserByNikName(userNikName);
							//创建好友关系，
							FriendsRs friendsRs1 = new FriendsRs(0,user.getUserQQ(),user22.getUserId(),"同学");
							FriendsRs friendsRs2 = new FriendsRs(0,user22.getUserQQ(),user.getUserId(),"同学");
							//插入好友
							friendsRsBiz.insertFriendsRs(friendsRs1);
							friendsRsBiz.insertFriendsRs(friendsRs2);
						}
						//类型4表示条件查找
						else if (messagePackge.getMessageType() == 4){
							List<User> findUsers = new ArrayList<User>();
							String[] mesString = messagePackge.getMessage().split(";");
							int size = mesString.length;
							if (size == 1) findUsers = userBiz.findUserWith(mesString[0]);
							else if (size == 2) findUsers = userBiz.findUserWith(mesString[0],mesString[1]);
							else if (size == 3) findUsers = userBiz.findUserWith(mesString[0],mesString[1],mesString[2]);
							
							//随机选取一个用户
							int size1 = findUsers.size();
							System.out.println("查找个数" + size1);
							//生成一个1-size1的用户
							int select = ((int)(Math.random()*1000))%(size1 + 1) + 1;
							System.out.println("选择的用户" + select);
							int i = 1;
							Iterator<User> iterator2 = findUsers.iterator();
							while (iterator2.hasNext()){
								User user = iterator2.next();
								if (i == select){
									objectOutputStream.writeObject(user);//将查找到得好友发送给用户
									System.out.println("已发送>>>>");
									break;
								}
								else{
									i++;
								}
							}
							
						}
						//*****************************************************
						
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				//***************end*************
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	} 
//	//关闭服务器的函数
//	public void closeServer(){
//		try {
//			System.out.println("************开始关闭**************\n\n\n");
//			in.close();
//			out.close();
//			pool.shutdown();
//			serverSocket.close();
//			//清空在线好友
//			users.clear();
//			System.out.println("*************已关闭服务器*********");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
}

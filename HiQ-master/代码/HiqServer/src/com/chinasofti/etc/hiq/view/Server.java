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
	//������Կͻ��˵�socket��ͻ��˷���������ͨ�ŵ�port
    public static Set<User> users = new HashSet<User>();//������ʾ���ߺ���ʱ�ĵ���
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
		 System.out.println("D��С��" + users.size());
		return users;
	}
	
	public ServerSocket getServerSocket() {
		return serverSocket;
	}
	
	
	private synchronized User getResult(Socket socket){
		User user = null;
		try {
			//��ø��û�socket���������
//			in = socket.getInputStream();
//			out = socket.getOutputStream();
			
//			ObjectInputStream objectInputStream = new ObjectInputStream(in);
			//ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);//ע�����Ҫ����pw��������ǧ������
			
			try {
				//�������Կͻ���User
				System.out.println("�������Կͻ��˵��û���");
				User user1 = (User)objectInputStream.readObject();
				
				System.out.println("��½�û��� QQ + ���룺" + user1.getUserQQ() + "  " + user1.getUserPassword() + ">>>");
				//�����ݿ��в鿴�Ƿ����
				user = userBiz.findUserByQQ(user1.getUserQQ());
				//System.out.println("���ݿ��д��ڵ��û�:" + user.getUserId() + user.getUserQQ() + user.getUesrEmail());
				//����û����ڲ����������Ҫ��
				if(user != null && user.getUserPassword().trim().equals(user1.getUserPassword().trim())){
					//���ø��û���IP Port ��½״̬
					user.setUserState(user1.getUserState());
					
					
					//��ȡ�ͻ��˵�IP
					String strUserIP = socket.getInetAddress().toString();
					String userIP = strUserIP.substring(1,strUserIP.length());
					user.setUserIP(userIP);
//					//��ȡ�ͻ��˵�PORT
//					int userPort = user1.getUserPort();
//					user.setUserPort(userPort);
				}//����
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
				
				//�ȴ��������Կͻ��˵�����
				System.out.println("�ȴ��������Կͻ��˵�����");
				socket = serverSocket.accept();
				System.out.println("�û���������");
				
				//�������������
				in = socket.getInputStream();
				out = socket.getOutputStream();
				objectInputStream = new ObjectInputStream(in);
				objectOutputStream = new ObjectOutputStream(out);
				
//		        //��ʼ������ʱ��ʱ��
//				Date dTime = new Date();
//				SimpleDateFormat format = new SimpleDateFormat("H");
//				agoTime = format.format(dTime);
				
				//�鿴���ݿ��Ƿ���ڸ��û�
				User user = getResult(socket);
				
				//����������
//				in = socket.getInputStream();
//				out = socket.getOutputStream();
				
//				InputStreamReader inReader = new InputStreamReader(in);
//				BufferedReader br = new BufferedReader(inReader);
//				PrintWriter pw = new PrintWriter(out,true);
				
//				ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
								
				//�Ƿ��û�
				if (user == null){
					System.out.println("�Ƿ��Ϸ��û�");
//					pw.println("0");//��ͻ��˷���0��ʾ�û��Ƿ�
					objectOutputStream.writeObject(null);
					socket.close();//�رո�����
				}//�Ϸ��û�
				else{
					System.out.println("�Ϸ��û�" + "  " + user.getUserNikName());
					//��out����
//					String toStr = "1";
//					byte[] send = toStr.getBytes();
//					System.out.println("���͵���Ϣ" + send.toString() + "\n\n");
//					out.write(send);
					//ֱ�Ӱ��û�����ȥ
					objectOutputStream.writeObject(user);
					
//					pw.print("1");//��ͻ��˷���1��ʾ�û��Ϸ�
					//���������û���
					
					users.add(user);
					System.out.println("\n\n\n�ѽ��û��������ߺ�����  ���м�����������" + users.size() + "\n\n");
					//�����߳�
					ClientThread clientThread = new ClientThread();
					//��ʼ��user �� socket
					clientThread.setUser(user);
					clientThread.setSocket(socket);
					clientThread.setObjectInputStream(objectInputStream);
					clientThread.setObjectOutputStream(objectOutputStream);
					//�����̳߳�
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
		private User user = new User();//��ǰ�û�
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

		//������ض�ʱ����û�и�������������Ϣ����˵��������
		class closeThread implements ActionListener{

			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//��⵽���û�һ���ߣ����������ߺ��������ɾ���ú���
				Iterator<User> iterator = users.iterator();
				while (iterator.hasNext()){
					User user1 = iterator.next();
					if (user1.getUserQQ() == user.getUserQQ()){
						iterator.remove();
						break;
					}
				}
				//Ȼ��ر��߳�
				System.out.println("���û��Ѿ����ߣ������߳�....");
				Thread.currentThread().stop();
			}
			
		}
		@SuppressWarnings("deprecation")
		public void run() {
			// TODO Auto-generated method stub
			
			//��ʼ����ʱ��
			time = new Timer(6*60*1000, new closeThread());
			time.start();
			
			
			//ͨ����ǰ�û���QQ�������� + ����
			Map<String,List<User>> friends = new HashMap<String, List<User>>();
			friends = friendsRsBiz.findFriendsByUserQQ(user.getUserQQ());
			//�������飬�Լ����������ݿ���û����ÿ���û���״̬
			Set<String> groupNames =  friends.keySet();
			Iterator<String> iterator = groupNames.iterator();
			//��һ���������        
			while (iterator.hasNext()){
				String groupName = iterator.next();
				//�õ�ÿ�������List<User>
				Iterator<User> it = friends.get(groupName).iterator();
				//�ڶ������List
				while (it.hasNext()){
					User user1 = it.next();//�õ��ò����
					//��������������ӵ����ݿ���û�
					Iterator<User> it2 = users.iterator();
					boolean flag = false;//��־�����ӵ����ݿ��е��û����Ƿ���ڸ��û�
					while (it2.hasNext()){
						User user2 = it2.next();
						//����������ӵ����ݿ���û��д��ڵĻ����ͽ����û���״̬����
						if (user1.getUserQQ() == user2.getUserQQ()){
							//����Map�û���״̬
							user1.setUserState(user2.getUserState());
							//�˿�
							user1.setUserPort(user2.getUserPort());
							//IP
							user1.setUserIP(user2.getUserIP());
							
							flag = true;//����
							break;
						}
					}
					//������    //����״̬
					if (!flag){
						user1.setUserState(4);
					}
				}
			}
		    try {
		    	//��������
//				 out = socket.getOutputStream();
//				ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
				//��Map<���飬�û��б�>�����ͻ���
				objectOutputStream.writeObject(friends);
			    
				
//				in = socket.getInputStream();
//				ObjectInputStream objectInputStream = new ObjectInputStream(in);
				//*************����������**************
				while (true){
					try {
						
						MessagePackge messagePackge = (MessagePackge)objectInputStream.readObject();
						
						System.out.println("�����û��İ���" + messagePackge.getMessageType() + "   " + messagePackge.getMessage());
						System.out.println("************������ʱ��,���û�������****************");
						time.restart();//���ض���ʱ���ڽ��յ�����Ϣ�����¼���
						
						if (messagePackge.getMessageType() == 1){
							continue;
						}
						
						//���������0��ʾ�ر��߳�
						if (messagePackge.getMessageType() == 0){
							System.out.println("�û��Զ��˳�Hiq��������");
							//���ú��Ѵ����ߺ������Ƴ�
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
						}//�������3��ʾ����QQ�ų��Һ���
						else if(messagePackge.getMessageType()==2){
							String userQQ = messagePackge.getMessage();
							User user = userBiz.findUserByQQ(Integer.parseInt(userQQ));
							objectOutputStream.writeObject(user);//�����ҵ��ĺ��ѷ��͸��û�
						}//����QQ����Ӻ���
						else if (messagePackge.getMessageType() == 22){
							//���Ҫ��ӵĺ��ѵ�QQ��
							String userQQ = messagePackge.getMessage().trim();
							//
							User user22 = userBiz.findUserByQQ(Integer.parseInt(userQQ));
							//�������ѹ�ϵ��
							FriendsRs friendsRs1 = new FriendsRs(0,user.getUserQQ(),user22.getUserId(),"ͬѧ");
							FriendsRs friendsRs2 = new FriendsRs(0,user22.getUserQQ(),user.getUserId(),"ͬѧ");
							//�������
							friendsRsBiz.insertFriendsRs(friendsRs1);
							friendsRsBiz.insertFriendsRs(friendsRs2);	
						}//����3��ʾ�����û��ǳƲ��Һ���
						else if (messagePackge.getMessageType() == 3){
							String userNikName = messagePackge.getMessage().trim();
							User user = userBiz.findUserByNikName(userNikName);
							objectOutputStream.writeObject(user);//�����ҵ��ú��ѷ��͸��û�
						}//44���ݺ������
						else if (messagePackge.getMessageType() == 33){
							//���Ҫ��ӵĺ��ѵ��ǳ�
							String userNikName = messagePackge.getMessage().trim();
							//
							User user22 = userBiz.findUserByNikName(userNikName);
							//�������ѹ�ϵ��
							FriendsRs friendsRs1 = new FriendsRs(0,user.getUserQQ(),user22.getUserId(),"ͬѧ");
							FriendsRs friendsRs2 = new FriendsRs(0,user22.getUserQQ(),user.getUserId(),"ͬѧ");
							//�������
							friendsRsBiz.insertFriendsRs(friendsRs1);
							friendsRsBiz.insertFriendsRs(friendsRs2);
						}
						//����4��ʾ��������
						else if (messagePackge.getMessageType() == 4){
							List<User> findUsers = new ArrayList<User>();
							String[] mesString = messagePackge.getMessage().split(";");
							int size = mesString.length;
							if (size == 1) findUsers = userBiz.findUserWith(mesString[0]);
							else if (size == 2) findUsers = userBiz.findUserWith(mesString[0],mesString[1]);
							else if (size == 3) findUsers = userBiz.findUserWith(mesString[0],mesString[1],mesString[2]);
							
							//���ѡȡһ���û�
							int size1 = findUsers.size();
							System.out.println("���Ҹ���" + size1);
							//����һ��1-size1���û�
							int select = ((int)(Math.random()*1000))%(size1 + 1) + 1;
							System.out.println("ѡ����û�" + select);
							int i = 1;
							Iterator<User> iterator2 = findUsers.iterator();
							while (iterator2.hasNext()){
								User user = iterator2.next();
								if (i == select){
									objectOutputStream.writeObject(user);//�����ҵ��ú��ѷ��͸��û�
									System.out.println("�ѷ���>>>>");
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
//	//�رշ������ĺ���
//	public void closeServer(){
//		try {
//			System.out.println("************��ʼ�ر�**************\n\n\n");
//			in.close();
//			out.close();
//			pool.shutdown();
//			serverSocket.close();
//			//������ߺ���
//			users.clear();
//			System.out.println("*************�ѹرշ�����*********");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
}

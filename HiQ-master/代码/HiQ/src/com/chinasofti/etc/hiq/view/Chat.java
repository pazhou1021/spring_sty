package com.chinasofti.etc.hiq.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

import com.chinasofti.etc.hiq.po.User;


public class Chat extends JFrame implements ItemListener{
	
	private static final long serialVersionUID = -266077703729622905L;
	private int x = 0;
	private int y = 0;
	private int width = 518;
	private int height = 490;
	private ImageIcon bgImage,headImage,friendImage,myImage;
	private JLabel imgLabel,headPortrait,myILable,friendILable,friendName;
	private Container cp;
	private JTextPane receiveText,sendText;
	private JScrollPane scrollPane,scrollPane_1;
	private JButton sendButton,button,face,history,colorButton,shakeButton, fileButton;
	private JSeparator jSeparator,jSeparator_1;
	private  JComboBox list;
	private JTextArea text;
	private User userMy;
	private int PORT = 6400;
	private int userFromQQ;
	private String userFrom;
	private String userIP;
	private DatagramSocket datagramSocket;
	private DatagramPacket datagramPacket;
	private JToggleButton jbtClose;
	private JToggleButton jbtMin;
	private Point pressedPoint;
	private Map<Integer, Chat> chatList = null;
	
	public void setReceiveText(JTextPane receiveText) {
		this.receiveText = receiveText;
	}
	/**
	 * 构造函数
	 * @param userMy
	 * @param userQQ
	 * @param userIP
	 * @param chatList
	 */
	public Chat(User userMy, int userFromQQ, String userFrom, String userIP, Map<Integer, Chat> chatList) {
		super(userFrom);
		// TODO Auto-generated constructor stub
		this.userMy = userMy;
		this.userFromQQ = userFromQQ;
		this.userFrom = userFrom;
		this.userIP = userIP;
		this.chatList = chatList;
		init();
	}
	/**
	 * 初始化方法
	 */
	public void init(){
		

		// 初始化UDP套接字
		try {
			datagramSocket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 设置窗口关闭方式
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//居中显示
		this.setLocationRelativeTo(this);
		this.bgImage = new ImageIcon("images/aio.png");
		this.imgLabel = new JLabel(bgImage);
		// 注意这里是关键，将背景标签添加到JFram的LayeredPane面板里。
		getLayeredPane().add(this.imgLabel, new Integer(Integer.MIN_VALUE));
		this.imgLabel.setBounds(0, 0, this.bgImage.getIconWidth(), this.bgImage.getIconHeight());
		((JPanel)getContentPane()).setOpaque(false);
		// 设置布局方式
		this.cp = getContentPane();
		this.cp.setLayout(null);
		
		//关闭最大化和最小化
		this.setUndecorated(true);
		this.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		/*
		// 窗口显示形式
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}*/
		
		// 顶部关闭按钮
		jbtClose = new JToggleButton();
		jbtClose.setRolloverIcon(new ImageIcon("images/close_over.png"));// 设置获取焦点图片
		jbtClose.setRolloverSelectedIcon(new ImageIcon("images/close_over.png"));// 设置鼠标点击图片
		jbtClose.setIcon(new ImageIcon("images/close.png"));// 设置按钮正常显示的图片
		jbtClose.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				do_jbtClose_itemStateChanged(e);
			}
		});
		jbtClose.setContentAreaFilled(false);
		jbtClose.setBorderPainted(false);
		jbtClose.setFocusPainted(false);
		cp.add(jbtClose);
		jbtClose.setBounds(483, 0, 35, 20);
		
		// 最小化按钮
		jbtMin = new JToggleButton();
		jbtMin.setRolloverIcon(new ImageIcon("images/min_over.png"));// 设置获取焦点图片
		jbtMin.setRolloverSelectedIcon(new ImageIcon("images/min_over.png"));// 设置鼠标点击图片
		jbtMin.setIcon(new ImageIcon("images/min.png"));//  设置按钮正常显示的图片
		jbtMin.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				do_jbtMin_itemStateChanged(e);
			}
		});
		jbtMin.setContentAreaFilled(false);
		jbtMin.setBorderPainted(false);
		jbtMin.setFocusPainted(false);
		cp.add(jbtMin);
		jbtMin.setBounds(456, 0, 27, 20);
		
		//好友头像
		this.headImage = new ImageIcon("images/b.gif");
		this.headPortrait = new JLabel(this.headImage);
		this.cp.add(this.headPortrait);
		this.headPortrait.setBounds(8,18,50,50);
		//好友名字
		friendName = new JLabel(userFrom);
		this.cp.add(friendName);
		Font font = new Font("微软雅黑", 0, 20);
		friendName.setFont(font);
		friendName.setBounds(60, 18, 80, 20);
		
		///接收窗口
		receiveText = new JTextPane();
		receiveText.setBounds(8, 85, 380, 200);
		this.cp.add(receiveText);
		receiveText.setInheritsPopupMenu(true);
		receiveText.setVerifyInputWhenFocusTarget(false);
		receiveText.setDragEnabled(true);
		receiveText.setMargin(new Insets(0, 0, 0, 0));
		receiveText.setEditable(false);
		scrollPane = new JScrollPane();
		scrollPane.getViewport().add(receiveText);
		this.cp.add(scrollPane);
		scrollPane.setBounds(8, 85, 380, 200);
		getReceiveText().addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				scrollPane.getVerticalScrollBar().setValue(
						getReceiveText().getHeight());
			}
		});
		getReceiveText().setDoubleBuffered(true);
		

		//设置接收信息窗口不可编辑
		receiveText.setEditable(false);
		
		//分割线
		jSeparator = new JSeparator(0);
		jSeparator.setBounds(8, 285, 380, 2);
		this.cp.add(jSeparator);
		//好友的qq秀
		friendImage = new ImageIcon("images/girl.gif");
		friendILable = new JLabel(friendImage);
		this.cp.add(friendILable);
		friendILable.setBounds(390, 85, 142, 200);
		//自己的qq秀
		if (userMy.getUserSex() == 1) {
			myImage = new ImageIcon("images/my.jpg");
		} else if (userMy.getUserSex() == 0) {
			myImage = new ImageIcon("images/girl.jpg");
		}
		myILable = new JLabel(myImage);
		this.cp.add(myILable);
		myILable.setBounds(390, 310, 142, 210);
	
		//字体设置
		list = new JComboBox();
		text = new JTextArea();
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] fontName = ge.getAvailableFontFamilyNames();
		for(String str:fontName)
			list.addItem(str);
		list.addItemListener(this);
		list.setBounds(8, 287, 95, 30);
		cp.add(list);
		//表情设置
		
		face = new JButton(new ImageIcon("images/ToolbarFace.png"));
		face.setFocusPainted(false);
		this.cp.add(face);
		face.setBounds(108, 287, 30, 30);
		face.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				QQface f = new QQface();
				JOptionPane.showMessageDialog(Chat.this, f);	
			}
		});
		
		
		//颜色设置
		colorButton = new JButton(new ImageIcon("images/v009_24.png"));
		colorButton.setBounds(140, 287, 30, 30);
		this.cp.add(colorButton);
		colorButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Color newColor = JColorChooser.showDialog(Chat.this, "字体颜色设置", colorButton.getBackground());
				if(newColor != null){
					//colorButton.setBackground(newColor);
					sendText.setForeground(newColor);
				}
			}
		});
		
		
		// 发送窗口抖动
		shakeButton = new JButton(new ImageIcon("images/shake.png"));
		shakeButton.setBounds(170,287, 30, 30);
		this.cp.add(shakeButton);
		shakeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 发送窗口抖动消息
				String sendMsg = "shake/" + userMy.getUserQQ() + "/" + userMy.getUserNikName();
				try {
					byte[] sendData;
					sendData = sendMsg.getBytes("utf-8");
					datagramPacket = new DatagramPacket(sendData, sendData.length,
							InetAddress.getByName(userIP), PORT);
					datagramSocket.send(datagramPacket);
					System.out.println(userMy.getUserQQ() + "向" + userFrom + " " + userIP + "发送抖动");
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		// 发送文件
		fileButton = new JButton(new ImageIcon("images/ToolbarPicture.png"));
		fileButton.setBounds(200,287, 30, 30);
		this.cp.add(fileButton);
		fileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 发送文件传输消息
				JFileChooser fileChooser = new JFileChooser();
				int returnVal = fileChooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					String sendMsg = "sendfile/" + userMy.getUserQQ() + "/"
							+ userMy.getUserNikName() + "/" + file.getName();
					try {
						byte[] sendData;
						sendData = sendMsg.getBytes("utf-8");
						datagramPacket = new DatagramPacket(sendData,
								sendData.length, InetAddress.getByName(userIP),
								PORT);
						datagramSocket.send(datagramPacket);
						Thread thread = new Thread(new SendFile(file));
						if (thread.isAlive()) {
							JOptionPane.showMessageDialog(null, "其他文件正在传输中");
						} else {
							thread.start();
						}
						System.out.println(userMy.getUserQQ() + "向" + userFrom
								+ " " + userIP + "发送文件");
					} catch (UnsupportedEncodingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		
		//聊天记录按钮
		history = new JButton("聊天记录");
		history.setBounds(289, 285, 100, 30);
		this.cp.add(history);
		history.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {

				
			}
		});
		
		jSeparator_1 = new JSeparator(0);
		jSeparator_1.setBounds(8, 315, 380, 2);
		this.cp.add(jSeparator_1);
		//发送窗口	
		sendText = new JTextPane();
		sendText.setBounds(8, 316, 380, 130);	
		sendText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {	
				if(e.getKeyCode() == KeyEvent.VK_ENTER){	
						String sendInfo = getSendInfo();	//获取信息发送文本
						if (sendInfo == null){			
							new IOException().printStackTrace();		
							return;
						}
						Date date = new Date();
						SimpleDateFormat matter = new SimpleDateFormat("y年 M月d日H时m分s秒");
						String time = matter.format(date);
						appendReceiveText(userMy.getUserNikName() + " "+time+"\n"+sendInfo + "\n", null);	//追加发送信息
						// 使用UDP套接字将信息发送出去
						String sendMsg = "info/" + userMy.getUserQQ() + "/" + userMy.getUserNikName() + "/" + sendInfo;
						try {
							byte[] sendData;
							sendData = sendMsg.getBytes("utf-8");
							datagramPacket = new DatagramPacket(sendData, sendData.length,
									InetAddress.getByName(userIP), PORT);
							datagramSocket.send(datagramPacket);
							System.out.println(userMy.getUserQQ() + "向" + userFrom + " " + userIP + "发送消息" + sendMsg);
						} catch (UnsupportedEncodingException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (UnknownHostException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						sendText.setText(null);
						sendText.requestFocus();
				}	
			}
		});
		this.cp.add(sendText);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.getViewport().add(sendText);
		this.cp.add(scrollPane_1);
		scrollPane_1.setBounds(8, 313, 380, 135);
		//发送按钮
		sendButton = new JButton("发送");
		sendButton.setBounds(320, 450, 65, 30);
		this.cp.add(sendButton);
		sendButton.addActionListener(new sendActionListener());
		sendButton.setMnemonic(32);	
		//底部关闭按钮
		button = new JButton("关闭");
		button.setBounds(250, 450, 65, 30);
		this.cp.add(button);
		button.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				chatList.remove(userFromQQ);
				dispose();
			}
		});
		// 设置cp透明
		((JPanel) this.cp).setOpaque(false);
		// 获取当前屏幕的大小
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		x = (d.width - width) / 2;
		y = (d.height - height) / 2;
		// 设置登陆窗口的位置和大小
		setBounds(x, y, width, height);
		
		// 鼠标移动窗口
		cp.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				do_topPanel_mouseDragged(e);
			}
		});
		cp.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				do_topPanel_mousePressed(e);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		// 显示窗口
		setVisible(true);
	}
	
	protected void do_topPanel_mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		pressedPoint = e.getPoint();
	}
	
	protected void do_topPanel_mouseDragged(MouseEvent e) {      
        // TODO 自动生成的方法存根      
        Point point = e.getPoint();      
        Point locationPoint = getLocation();      
                     
        int x = locationPoint.x + point.x - pressedPoint.x;      
        int y = locationPoint.y + point.y - pressedPoint.y;      
                     
        setLocation(x, y); 
    }
	
	protected void do_jbtMin_itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		setExtendedState(ICONIFIED);
	}
	protected void do_jbtClose_itemStateChanged(MouseEvent e) {
		// TODO Auto-generated method stub
		chatList.remove(userFromQQ);
		dispose();
	}

	class sendActionListener implements ActionListener {
		public void actionPerformed(final ActionEvent e) {
			String sendInfo = getSendInfo();	//获取信息发送文本
			if (sendInfo == null){
				new IOException().printStackTrace();
				return;
			}
			Date date = new Date();
			SimpleDateFormat matter = new SimpleDateFormat("y年 M月d日H时m分s秒");
			String time = matter.format(date);
			appendReceiveText(userMy.getUserNikName() + " "+time+"\n"+sendInfo + "\n", null);	//追加发送信息
			// 使用UDP套接字将信息发送出去
			String sendMsg = "info/" + userMy.getUserQQ() + "/" + userMy.getUserNikName() + "/" + sendInfo;
			try {
				byte[] sendData;
				sendData = sendMsg.getBytes("utf-8");
				datagramPacket = new DatagramPacket(sendData, sendData.length,
						InetAddress.getByName(userIP), PORT);
				datagramSocket.send(datagramPacket);
				System.out.println(userMy.getUserQQ() + "向" + userFrom + " " + userIP + "发送消息" + sendMsg);
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
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
		receiveText.setFont(sendText.getFont());
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
			JOptionPane.showMessageDialog(Chat.this, "不能发送空信息。");
			return null;
		}
		return sendInfo;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		
		String fontname = (String)list.getSelectedItem();
		Font f = new Font(fontname,Font.BOLD,20);
		text.setFont(f);
		sendText.setFont(f);
	}
}

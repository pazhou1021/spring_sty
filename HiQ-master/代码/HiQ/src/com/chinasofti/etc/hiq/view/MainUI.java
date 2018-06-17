package com.chinasofti.etc.hiq.view;

import java.awt.AWTException;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.chinasofti.etc.hiq.biz.UserBiz;
import com.chinasofti.etc.hiq.biz.impl.UserBizImpl;
import com.chinasofti.etc.hiq.dao.impl.TCPConnect;
import com.chinasofti.etc.hiq.po.MessagePackge;
import com.chinasofti.etc.hiq.po.User;
/**
 * 
 * @author zc
 * @version 1.0
 *
 */
public class MainUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5699367530817133985L;
	private User userMy;
	private Map<String, List<User>> usersMap = null; // 所有好友列表
	private int x = 0;
	private int y = 0;
	private int width = 281;
	private int height = 700;
	private int margeTop = 10;
	private int margeRight = 10;
	private Point pressedPoint;
	private JLabel headPortrait = null;
	private JComboBox state = null;
	private JLabel nikeName = null;
	private JLabel signature = null;
	private JLabel title = null;
	private JToggleButton jbtClose = null;
	private JToggleButton jbtMin = null;
	private JToggleButton jbtClothes = null;
	private JLabel background= null;
	private ImageIcon bgImage;
	private JButton btnSerarch = null;
	private UDPReceive udpReceive; // 消息接收线程
	private HeartbeatPackage heartbeatPackage = null; // 发送心跳包线程
	private OnlineMessage onlineMessage = null; // 发送上线提醒线程
	private JTextField searchLJLabel = null;
	private Image icon;// 托盘图标
	private TrayIcon trayIcon;
	private SystemTray systemTray;// 系统托盘
	private PopupMenu pop = new PopupMenu(); // 构造一个右键弹出式菜单
	private MenuItem exit = new MenuItem("退出程序");
	private MenuItem show = new MenuItem("显示窗口");
	
	/**
	 * 构造函数
	 * @throws HeadlessException
	 */
	public MainUI(String title, User userMy) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
		this.userMy = userMy;
		Tray();
		// 从服务器端获取好友列表
		getFriendsList();
		// 显示窗口
		init();
	}
	
	public User getUserMy() {
		return userMy;
	}
	public void setUserMy(User userMy) {
		this.userMy = userMy;
	}
	
	public void init() {
		// 设置窗口关闭方式
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 获取当前屏幕的大小
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		x = d.width - width - margeRight;
		y = margeTop;
		// 设置窗口大小和位置
		setBounds(x, y, width, height);
		// 不使用系统标题栏和边框
		setUndecorated(true);
		
		// 设置背景图片
		bgImage = new ImageIcon("skins/skin_00.jpg");
		background = new JLabel(bgImage);
		// 注意这里是关键，将背景标签添加到JFram的LayeredPane面板里。
		getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, bgImage.getIconWidth(), bgImage.getIconHeight());
		
		// 设置布局方式
		Container cp = getContentPane();
		// 响应移动窗口事件
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
		cp.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				do_topPanel_mousePressed(e);
			}
		});
		// 设置布局方式为空
		cp.setLayout(null);
		// 注意这里，将内容面板设为透明。这样LayeredPane面板中的背景才能显示出来。
		((JPanel) cp).setOpaque(false);
		
		// 设置标题
		title = new JLabel(this.getTitle());
		cp.add(title);
		title.setBounds(10, 10, 100, 20);
		
		// 关闭按钮
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
		jbtClose.setBounds(245, 0, 35, 20);
		
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
		jbtMin.setBounds(215, 0, 27, 20);
		
		// 外观设置按钮
		jbtClothes = new JToggleButton();
		jbtClothes.setRolloverIcon(new ImageIcon("images/clothes_over.png"));// 设置获取焦点图片
		jbtClothes.setRolloverSelectedIcon(new ImageIcon("images/clothes_over.png"));// 设置鼠标点击图片
		jbtClothes.setIcon(new ImageIcon("images/clothes.png"));//  设置按钮正常显示的图片
		jbtClothes.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				do_jbtClothes_itemStateChanged(e);
			}
		});
		jbtClothes.setContentAreaFilled(false);
		jbtClothes.setBorderPainted(false);
		jbtClothes.setFocusPainted(false);
		cp.add(jbtClothes);
		jbtClothes.setBounds(188, 0, 27, 20);
		
		// 设置头像
		headPortrait = new JLabel(new ImageIcon("images/" + userMy.getUserImage()));
		cp.add(headPortrait);
		headPortrait.setBounds(7, 31, 62, 62);
		headPortrait.setToolTipText("个人资料");
		headPortrait.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getClickCount() >= 2) {
					new MyInformation("个人资料", userMy);
				}
			}	
		});
		
		// 设置用户状态
		state = new JComboBox();
		
		state.addItem("在线");
		state.addItem("隐身");
		state.addItem("离开");
		state.addItem("勿扰");
		state.addItem("离线");
		state.setSelectedIndex(userMy.getUserState());
		cp.add(state);
		state.setBounds(75, 31, 60, 20);
		
		// 设置用户昵称
		nikeName = new JLabel(userMy.getUserNikName());
		cp.add(nikeName);
		nikeName.setBounds(135, 35, 40, 12);
		
		// 设置个性签名
		signature = new JLabel(userMy.getUserSpeak());
		cp.add(signature);
		signature.setBounds(75, 35, 200, 62);
		
		// 搜索框
		searchLJLabel = new JTextField("请输入查找条件");
		cp.add(searchLJLabel);
		searchLJLabel.setBounds(0, 99, 280, 28);
		searchLJLabel.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				// 得到焦点时清空文字
				if (searchLJLabel.getText().equals("请输入查找条件")) {
					searchLJLabel.setText(null);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (searchLJLabel.getText().equals("")) {
					searchLJLabel.setText("请输入查找条件");
				}
			}
		});
		
		// TabbedPane
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panelMenu menu = new panelMenu(usersMap, userMy);
		cp.add(menu);
		menu.setBounds(0, 126, width, 512);
		
		btnSerarch = new JButton("好友查找");
		cp.add(btnSerarch);
		btnSerarch.setBounds(0, 652, 100, 30);
		btnSerarch.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				new Search();
			}
		});
		
		// 显示窗口
		setVisible(true);
		
		// 开启消息接收线程
		udpReceive = new UDPReceive(userMy, menu.getChatList(), usersMap);
		Thread recvThread = new Thread(udpReceive);
		recvThread.setDaemon(true); // 设置为守护线程
		recvThread.start();
		
		// 开启发送心跳包线程
		heartbeatPackage = new HeartbeatPackage();
		Thread sendHbPackageThread = new Thread(heartbeatPackage);
		sendHbPackageThread.setDaemon(true); // 设置为守护线程
		sendHbPackageThread.start();
		
		// 开启发送上线提醒线程
		onlineMessage = new OnlineMessage(usersMap, userMy);
		Thread sendOnlineMessage = new Thread(onlineMessage);
		sendOnlineMessage.start();
		
	}
	
	protected void Tray() {
		// icon初始化
		icon = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("icon.gif"));// 托盘图标显示的图片
		if (SystemTray.isSupported()) {
			systemTray = SystemTray.getSystemTray();// 获得系统托盘的实例
			trayIcon = new TrayIcon(icon, "HiQ 2014", pop);
			// wasw100
			pop.add(show);
			pop.add(exit);
			
			try {
				systemTray.add(trayIcon); // 设置托盘的图标
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
			addWindowListener(new WindowAdapter() {
				public void windowIconified(WindowEvent e) {
					dispose();// 窗口最小化时dispose该窗口
				}
			});

			trayIcon.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 1 && e.getButton() != MouseEvent.BUTTON3) {// 左击击托盘窗口再现，如果是双击就是e.getClickCount()
																						// ==
																						// 2
						setVisible(true);
						setExtendedState(JFrame.NORMAL);// 设置此 frame 的状态。
					}
				}
			});

			show.addActionListener(new ActionListener() { // 点击"显示窗口"菜单后将窗口显示出来

				public void actionPerformed(ActionEvent e) {
					// systemTray.remove(trayIcon); // 从系统的托盘实例中移除托盘图标
					setVisible(true); // 显示窗口
					setExtendedState(JFrame.NORMAL);
				}
			});
			exit.addActionListener(new ActionListener() { // 点击“退出演示”菜单后推出程序

				public void actionPerformed(ActionEvent e) {
					// 这里可以写执行退出时执行的操作
					do_jbtClose_itemStateChanged (null);
				}
			});
		}// 托盘图标部分结束
		setIconImage(icon);// 更改程序图标
	}
	/**
	 *  设置窗口外观
	 * @param e
	 */
	protected void do_jbtClothes_itemStateChanged(ItemEvent e) {
		// 设置背景图片
		getLayeredPane().remove(background);
		int num = (int)(Math.random() * 6);
		String skin = "skins/skin_0" + num + ".jpg";
		System.out.println(skin);
		bgImage = new ImageIcon(skin);
		background = new JLabel(bgImage);
		// 注意这里是关键，将背景标签添加到JFram的LayeredPane面板里。
		getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, bgImage.getIconWidth(), bgImage.getIconHeight());
		// 注意这里，将内容面板设为透明。这样LayeredPane面板中的背景才能显示出来。
		((JPanel) getContentPane()).setOpaque(false);
		repaint();
	}
	
	/**
	 *  最小化窗口
	 * @param e
	 */
	protected void do_jbtMin_itemStateChanged(ItemEvent e) {
		setExtendedState(ICONIFIED);
	}
	
	/**
	 * 关闭窗口
	 * @param e
	 */
	protected void do_jbtClose_itemStateChanged(MouseEvent e) {
		// 关闭窗口，退出程序
		int n = JOptionPane.showConfirmDialog(null, "确定退出？", "退出", JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
				
			try {
				// 向服务器发送下线消息
				ObjectOutputStream objectOutputStream = TCPConnect.objectOutputStream;
				MessagePackge messagePackge = new MessagePackge(0, null);
				objectOutputStream.writeObject(messagePackge);
				
				// 结束TCP连接线程
				if(TCPConnect.objectInputStream != null) {
					TCPConnect.objectInputStream.close();
				}
				if (TCPConnect.objectOutputStream != null) {
					TCPConnect.objectOutputStream.close();
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// 开启发送下线提醒线程
			OfflineMessage offlineMessage = new OfflineMessage(usersMap, userMy);
			Thread sendOfflineMessage = new Thread(offlineMessage);
			sendOfflineMessage.start();
			
			// 销毁窗口
			System.exit(0);
		}
	}
	
	/**
	 * 获取所有好友列表
	 */
	protected void getFriendsList() {
		
		try {
			// 获取所有好友列表
			UserBiz userBiz = new UserBizImpl();
			usersMap = userBiz.findAllUsers();
			
			System.out.println("开始接收好友列表");
			Set<String> set = usersMap.keySet();
			Iterator<String> iterator = set.iterator();
			while (iterator.hasNext()) {
				String groupName = iterator.next();
				System.out.println("groupName" + groupName);
				List<User> users = usersMap.get(groupName);
				Iterator<User> iterator2 = users.iterator();
				while(iterator2.hasNext()) {
					User user = iterator2.next();
					System.out.println("\t" + user.getUserQQ() 
							+ " " + user.getUserNikName()
							+ " " + user.getUserAddress()
							+ " " + user.getUserPort()
							+" " + user.getUserIP());
				}
			}
			System.out.println("好友列表接收成功");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "好友列表获取失败", "错误", JOptionPane.WARNING_MESSAGE);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "好友列表获取失败", "错误", JOptionPane.WARNING_MESSAGE);
		}
	}
	/**
	 * 鼠标点击消息
	 * @param e
	 */
	protected void do_topPanel_mousePressed(MouseEvent e) {
		// TODO Auto-generated catch block
		pressedPoint = e.getPoint();
	}
	/**
	 * 鼠标拖动消息
	 * @param e
	 */
	protected void do_topPanel_mouseDragged(MouseEvent e) {      
		// 得到鼠标当前位置和窗口当前位置
        Point point = e.getPoint();      
        Point locationPoint = getLocation();      
                     
        int x = locationPoint.x + point.x - pressedPoint.x;      
        int y = locationPoint.y + point.y - pressedPoint.y;      
                     
        setLocation(x, y);      
    }
}

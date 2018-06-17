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
	private Map<String, List<User>> usersMap = null; // ���к����б�
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
	private UDPReceive udpReceive; // ��Ϣ�����߳�
	private HeartbeatPackage heartbeatPackage = null; // �����������߳�
	private OnlineMessage onlineMessage = null; // �������������߳�
	private JTextField searchLJLabel = null;
	private Image icon;// ����ͼ��
	private TrayIcon trayIcon;
	private SystemTray systemTray;// ϵͳ����
	private PopupMenu pop = new PopupMenu(); // ����һ���Ҽ�����ʽ�˵�
	private MenuItem exit = new MenuItem("�˳�����");
	private MenuItem show = new MenuItem("��ʾ����");
	
	/**
	 * ���캯��
	 * @throws HeadlessException
	 */
	public MainUI(String title, User userMy) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
		this.userMy = userMy;
		Tray();
		// �ӷ������˻�ȡ�����б�
		getFriendsList();
		// ��ʾ����
		init();
	}
	
	public User getUserMy() {
		return userMy;
	}
	public void setUserMy(User userMy) {
		this.userMy = userMy;
	}
	
	public void init() {
		// ���ô��ڹرշ�ʽ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ��ȡ��ǰ��Ļ�Ĵ�С
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		x = d.width - width - margeRight;
		y = margeTop;
		// ���ô��ڴ�С��λ��
		setBounds(x, y, width, height);
		// ��ʹ��ϵͳ�������ͱ߿�
		setUndecorated(true);
		
		// ���ñ���ͼƬ
		bgImage = new ImageIcon("skins/skin_00.jpg");
		background = new JLabel(bgImage);
		// ע�������ǹؼ�����������ǩ��ӵ�JFram��LayeredPane����
		getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, bgImage.getIconWidth(), bgImage.getIconHeight());
		
		// ���ò��ַ�ʽ
		Container cp = getContentPane();
		// ��Ӧ�ƶ������¼�
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
		// ���ò��ַ�ʽΪ��
		cp.setLayout(null);
		// ע����������������Ϊ͸��������LayeredPane����еı���������ʾ������
		((JPanel) cp).setOpaque(false);
		
		// ���ñ���
		title = new JLabel(this.getTitle());
		cp.add(title);
		title.setBounds(10, 10, 100, 20);
		
		// �رհ�ť
		jbtClose = new JToggleButton();
		jbtClose.setRolloverIcon(new ImageIcon("images/close_over.png"));// ���û�ȡ����ͼƬ
		jbtClose.setRolloverSelectedIcon(new ImageIcon("images/close_over.png"));// ���������ͼƬ
		jbtClose.setIcon(new ImageIcon("images/close.png"));// ���ð�ť������ʾ��ͼƬ
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
		
		// ��С����ť
		jbtMin = new JToggleButton();
		jbtMin.setRolloverIcon(new ImageIcon("images/min_over.png"));// ���û�ȡ����ͼƬ
		jbtMin.setRolloverSelectedIcon(new ImageIcon("images/min_over.png"));// ���������ͼƬ
		jbtMin.setIcon(new ImageIcon("images/min.png"));//  ���ð�ť������ʾ��ͼƬ
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
		
		// ������ð�ť
		jbtClothes = new JToggleButton();
		jbtClothes.setRolloverIcon(new ImageIcon("images/clothes_over.png"));// ���û�ȡ����ͼƬ
		jbtClothes.setRolloverSelectedIcon(new ImageIcon("images/clothes_over.png"));// ���������ͼƬ
		jbtClothes.setIcon(new ImageIcon("images/clothes.png"));//  ���ð�ť������ʾ��ͼƬ
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
		
		// ����ͷ��
		headPortrait = new JLabel(new ImageIcon("images/" + userMy.getUserImage()));
		cp.add(headPortrait);
		headPortrait.setBounds(7, 31, 62, 62);
		headPortrait.setToolTipText("��������");
		headPortrait.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getClickCount() >= 2) {
					new MyInformation("��������", userMy);
				}
			}	
		});
		
		// �����û�״̬
		state = new JComboBox();
		
		state.addItem("����");
		state.addItem("����");
		state.addItem("�뿪");
		state.addItem("����");
		state.addItem("����");
		state.setSelectedIndex(userMy.getUserState());
		cp.add(state);
		state.setBounds(75, 31, 60, 20);
		
		// �����û��ǳ�
		nikeName = new JLabel(userMy.getUserNikName());
		cp.add(nikeName);
		nikeName.setBounds(135, 35, 40, 12);
		
		// ���ø���ǩ��
		signature = new JLabel(userMy.getUserSpeak());
		cp.add(signature);
		signature.setBounds(75, 35, 200, 62);
		
		// ������
		searchLJLabel = new JTextField("�������������");
		cp.add(searchLJLabel);
		searchLJLabel.setBounds(0, 99, 280, 28);
		searchLJLabel.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				// �õ�����ʱ�������
				if (searchLJLabel.getText().equals("�������������")) {
					searchLJLabel.setText(null);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				if (searchLJLabel.getText().equals("")) {
					searchLJLabel.setText("�������������");
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
		
		btnSerarch = new JButton("���Ѳ���");
		cp.add(btnSerarch);
		btnSerarch.setBounds(0, 652, 100, 30);
		btnSerarch.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				new Search();
			}
		});
		
		// ��ʾ����
		setVisible(true);
		
		// ������Ϣ�����߳�
		udpReceive = new UDPReceive(userMy, menu.getChatList(), usersMap);
		Thread recvThread = new Thread(udpReceive);
		recvThread.setDaemon(true); // ����Ϊ�ػ��߳�
		recvThread.start();
		
		// ���������������߳�
		heartbeatPackage = new HeartbeatPackage();
		Thread sendHbPackageThread = new Thread(heartbeatPackage);
		sendHbPackageThread.setDaemon(true); // ����Ϊ�ػ��߳�
		sendHbPackageThread.start();
		
		// �����������������߳�
		onlineMessage = new OnlineMessage(usersMap, userMy);
		Thread sendOnlineMessage = new Thread(onlineMessage);
		sendOnlineMessage.start();
		
	}
	
	protected void Tray() {
		// icon��ʼ��
		icon = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("icon.gif"));// ����ͼ����ʾ��ͼƬ
		if (SystemTray.isSupported()) {
			systemTray = SystemTray.getSystemTray();// ���ϵͳ���̵�ʵ��
			trayIcon = new TrayIcon(icon, "HiQ 2014", pop);
			// wasw100
			pop.add(show);
			pop.add(exit);
			
			try {
				systemTray.add(trayIcon); // �������̵�ͼ��
			} catch (AWTException e1) {
				e1.printStackTrace();
			}
			addWindowListener(new WindowAdapter() {
				public void windowIconified(WindowEvent e) {
					dispose();// ������С��ʱdispose�ô���
				}
			});

			trayIcon.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 1 && e.getButton() != MouseEvent.BUTTON3) {// ��������̴������֣������˫������e.getClickCount()
																						// ==
																						// 2
						setVisible(true);
						setExtendedState(JFrame.NORMAL);// ���ô� frame ��״̬��
					}
				}
			});

			show.addActionListener(new ActionListener() { // ���"��ʾ����"�˵��󽫴�����ʾ����

				public void actionPerformed(ActionEvent e) {
					// systemTray.remove(trayIcon); // ��ϵͳ������ʵ�����Ƴ�����ͼ��
					setVisible(true); // ��ʾ����
					setExtendedState(JFrame.NORMAL);
				}
			});
			exit.addActionListener(new ActionListener() { // ������˳���ʾ���˵����Ƴ�����

				public void actionPerformed(ActionEvent e) {
					// �������дִ���˳�ʱִ�еĲ���
					do_jbtClose_itemStateChanged (null);
				}
			});
		}// ����ͼ�겿�ֽ���
		setIconImage(icon);// ���ĳ���ͼ��
	}
	/**
	 *  ���ô������
	 * @param e
	 */
	protected void do_jbtClothes_itemStateChanged(ItemEvent e) {
		// ���ñ���ͼƬ
		getLayeredPane().remove(background);
		int num = (int)(Math.random() * 6);
		String skin = "skins/skin_0" + num + ".jpg";
		System.out.println(skin);
		bgImage = new ImageIcon(skin);
		background = new JLabel(bgImage);
		// ע�������ǹؼ�����������ǩ��ӵ�JFram��LayeredPane����
		getLayeredPane().add(background, new Integer(Integer.MIN_VALUE));
		background.setBounds(0, 0, bgImage.getIconWidth(), bgImage.getIconHeight());
		// ע����������������Ϊ͸��������LayeredPane����еı���������ʾ������
		((JPanel) getContentPane()).setOpaque(false);
		repaint();
	}
	
	/**
	 *  ��С������
	 * @param e
	 */
	protected void do_jbtMin_itemStateChanged(ItemEvent e) {
		setExtendedState(ICONIFIED);
	}
	
	/**
	 * �رմ���
	 * @param e
	 */
	protected void do_jbtClose_itemStateChanged(MouseEvent e) {
		// �رմ��ڣ��˳�����
		int n = JOptionPane.showConfirmDialog(null, "ȷ���˳���", "�˳�", JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
				
			try {
				// �����������������Ϣ
				ObjectOutputStream objectOutputStream = TCPConnect.objectOutputStream;
				MessagePackge messagePackge = new MessagePackge(0, null);
				objectOutputStream.writeObject(messagePackge);
				
				// ����TCP�����߳�
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
			// �����������������߳�
			OfflineMessage offlineMessage = new OfflineMessage(usersMap, userMy);
			Thread sendOfflineMessage = new Thread(offlineMessage);
			sendOfflineMessage.start();
			
			// ���ٴ���
			System.exit(0);
		}
	}
	
	/**
	 * ��ȡ���к����б�
	 */
	protected void getFriendsList() {
		
		try {
			// ��ȡ���к����б�
			UserBiz userBiz = new UserBizImpl();
			usersMap = userBiz.findAllUsers();
			
			System.out.println("��ʼ���պ����б�");
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
			System.out.println("�����б���ճɹ�");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "�����б��ȡʧ��", "����", JOptionPane.WARNING_MESSAGE);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "�����б��ȡʧ��", "����", JOptionPane.WARNING_MESSAGE);
		}
	}
	/**
	 * �������Ϣ
	 * @param e
	 */
	protected void do_topPanel_mousePressed(MouseEvent e) {
		// TODO Auto-generated catch block
		pressedPoint = e.getPoint();
	}
	/**
	 * ����϶���Ϣ
	 * @param e
	 */
	protected void do_topPanel_mouseDragged(MouseEvent e) {      
		// �õ���굱ǰλ�úʹ��ڵ�ǰλ��
        Point point = e.getPoint();      
        Point locationPoint = getLocation();      
                     
        int x = locationPoint.x + point.x - pressedPoint.x;      
        int y = locationPoint.y + point.y - pressedPoint.y;      
                     
        setLocation(x, y);      
    }
}

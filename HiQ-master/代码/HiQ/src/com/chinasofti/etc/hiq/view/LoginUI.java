package com.chinasofti.etc.hiq.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class LoginUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -266077703729622905L;
	public static boolean loginOk = false;

	private int x = 0;
	private int y = 0;
	private int width = 380;
	private int height = 294;
	private Point pressedPoint;
	private JTextField accout;
	private JPasswordField password;
	private JCheckBox rememberPassword;
	private JCheckBox automaticLogin;
	private JLabel register;
	@SuppressWarnings("unused")
	private JComboBox statusComboBox;
	
	private int ServerPort = 0;
	private String ServerIP = null;
	
	static {
		
	}

	/**
	 * @throws HeadlessException
	 */
	public LoginUI() throws HeadlessException {
		super();
		// TODO Auto-generated constructor stub
		init();
	}

	/**
	 * @param arg0
	 */
	public LoginUI(GraphicsConfiguration arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
		init();
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public LoginUI(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
		init();
	}

	/**
	 * @param arg0
	 * @throws HeadlessException
	 */
	public LoginUI(String arg0) throws HeadlessException {
		super(arg0);
		// TODO Auto-generated constructor stub
		init();
		getConfig();
	}

	public void init() {
		// ���ô��ڹرշ�ʽ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ���ñ���ͼƬ
		ImageIcon bgImage = new ImageIcon("images/img_background.png");
		JLabel imgLabel = new JLabel(bgImage);
		// ע�������ǹؼ�����������ǩ��ӵ�JFram��LayeredPane����
		getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0, 0, bgImage.getIconWidth(), bgImage.getIconHeight());
		// ���ò��ַ�ʽ
		Container cp = getContentPane();
		cp.setLayout(null);
		// ����ͷ��
		ImageIcon img_headPortrait = new ImageIcon("images/img_headPortrait.png");
		JLabel headPortrait = new JLabel(img_headPortrait);
		cp.add(headPortrait);
		headPortrait.setBounds(21, 144, 82, 82);
		// �˺������
		accout = new JTextField("QQ����/�ֻ�/����");
		cp.add(accout);
		accout.setBounds(113, 145, 188, 26);

		// ע������
		register = new JLabel("<html><font color=blue><u>" + "ע���˺�");
		cp.add(register);
		register.setBounds(313, 112, 78, 87);
		// ���������
		password = new JPasswordField("����");
		cp.add(password);
		password.setBounds(113, 179, 188, 26);
		password.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				password.setText("");
			}
		});
		// ��ס����
		rememberPassword = new JCheckBox();
		rememberPassword.setBounds(112, 212, 15, 15);
		cp.add(rememberPassword);
		rememberPassword.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				if (rememberPassword.isSelected() == false) {
					automaticLogin.setSelected(false);
				}
			}
		});
		// �Զ���¼
		automaticLogin = new JCheckBox();
		automaticLogin.setBounds(192, 212, 15, 15);
		cp.add(automaticLogin);
		automaticLogin.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				if (rememberPassword.isSelected() == false) {
					automaticLogin.setSelected(false);
				}
			}
		});
//		// ״̬
//		statusComboBox = new JComboBox();
//		statusComboBox.addItem("����");
//		statusComboBox.addItem("����");
//		statusComboBox.addItem("�뿪");
//		statusComboBox.addItem("����");
//		statusComboBox.addItem("����");
//		cp.add(statusComboBox);
//		statusComboBox.setBounds(100, 190, 60, 28);
		// �رհ�ť
		JToggleButton button_1 = new JToggleButton();
		button_1.setRolloverIcon(new ImageIcon("images/closeOverImage.png"));// ���û�ȡ����ͼƬ
		button_1.setRolloverSelectedIcon(new ImageIcon("images/closeOverImage.png"));// ���������ͼƬ
		button_1.setIcon(new ImageIcon("images/close.png"));//  ���ð�ť������ʾ��ͼƬ
		button_1.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				do_button_1_itemStateChanged(e);
			}
		});
		button_1.setContentAreaFilled(false);
		button_1.setBorderPainted(false);
		button_1.setFocusPainted(false);
		cp.add(button_1);
		button_1.setBounds(342, 0, 36, 18);
		// ��С����ť
		JToggleButton button_2 = new JToggleButton();
		button_2.setRolloverIcon(new ImageIcon("images/minOverImage.png"));
		button_2.setRolloverSelectedIcon(new ImageIcon("images/minOverImage.png"));
		button_2.setIcon(new ImageIcon("images/min.png"));
		button_2.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				do_button_2_itemStateChanged(e);
			}
		});
		button_2.setContentAreaFilled(false);
		button_2.setBorderPainted(false);
		button_2.setFocusPainted(false);
		cp.add(button_2);
		button_2.setBounds(314, 0, 36, 18);
		// ���ð�ť
		JToggleButton button_3 = new JToggleButton();
		button_3.setRolloverIcon(new ImageIcon("images/setOverImage.png"));
		button_3.setRolloverSelectedIcon(new ImageIcon("images/setOverImage.png"));
		button_3.setIcon(new ImageIcon("images/set.png"));
		button_3.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				do_button_3_itemStateChanged(e);
			}
		});
		button_3.setContentAreaFilled(false);
		button_3.setBorderPainted(false);
		button_3.setFocusPainted(false);
		cp.add(button_3);
		button_3.setBounds(288, 0, 36, 18);
		
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
		// ע����������������Ϊ͸��������LayeredPane����еı���������ʾ������
		((JPanel) cp).setOpaque(false);
		// ��ȡ��ǰ��Ļ�Ĵ�С
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		x = (d.width - width) / 2;
		y = (d.height - height) / 2;
		// ���õ�½���ڵ�λ�úʹ�С
		setBounds(x, y, width, height);
		// ��ʹ��ϵͳ�������ͱ߿�
		setUndecorated(true);
		// ��ʾ����
		setVisible(true);
	}
	
	protected void getConfig() {
		// TODO Auto-generated method stub
		File file = new File("HiQConfig.ini");
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(file));
			String strPort = properties.getProperty("ServerPort");
			ServerPort = Integer.parseInt(strPort);
			ServerIP = properties.getProperty("ServerIP");
			if (ServerIP == null && ServerPort == 0) {
				return;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showDialog("����", "�����ļ�δ�ҵ�");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			showDialog("����", "�ļ����쳣");
		}
	}
	
	protected void showDialog (String title, String msg) {
		JDialog dialog = new JDialog(this, title);
		JLabel text = new JLabel(msg);
		dialog.add(text);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 150;
		int height = 100;
		int x = (d.width - width) / 2;
		int y = (d.height - height) / 2;
		dialog.setBounds(x, y, width, height);
		dialog.setVisible(true);
	}

	protected void do_button_1_itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		dispose();
	}

	protected void do_button_2_itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		setExtendedState(ICONIFIED);
	}

	protected void do_button_3_itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		setLocation(0, 0);
	}
	
	protected void do_topPanel_mousePressed(MouseEvent e) {
		// TODO �Զ����ɵķ������
		pressedPoint = e.getPoint();
	}
	
	protected void do_topPanel_mouseDragged(MouseEvent e) {      
        // TODO �Զ����ɵķ������      
        Point point = e.getPoint();      
        Point locationPoint = getLocation();      
                     
        int x = locationPoint.x + point.x - pressedPoint.x;      
        int y = locationPoint.y + point.y - pressedPoint.y;      
                     
        setLocation(x, y); 
    }
	
	public static void main(String[] args) {
		new Login();
	}
}

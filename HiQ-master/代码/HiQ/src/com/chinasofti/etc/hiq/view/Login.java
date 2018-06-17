package com.chinasofti.etc.hiq.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.chinasofti.etc.hiq.biz.UserBiz;
import com.chinasofti.etc.hiq.biz.impl.UserBizImpl;
import com.chinasofti.etc.hiq.po.Language;
import com.chinasofti.etc.hiq.po.User;

/**
 * 
 * @author xlh
 * @version 1.0
 *
 */
public class Login extends JFrame {
	
	private static final long serialVersionUID = -266077703729622905L;
	private int x = 0;
	private int y = 0;
	private int width = 380;
	private int height = 298;
	private JButton login;
	private ImageIcon bgImage,headImage;
	private JLabel imgLabel,headPortrait,rPass,fPass;
	private Container cp;
	private JTextField accout;
	private JPasswordField password;
	private JCheckBox rememberPassword;
	private JCheckBox automaticLogin;
	private JComboBox statusComboBox;
	/**
	 * ���캯��
	 * @throws HeadlessException
	 */
	public Login() throws HeadlessException {
		super();
		init();
	}
	
	public Login(GraphicsConfiguration arg0) {
		super(arg0);		
		init();
	}
	
	public Login(String title, GraphicsConfiguration arg1) {
		super(title, arg1);		
		init();
	}

	public Login(String title) throws HeadlessException {
		super(title);	
		init();
	}
	/**
	 * ��ʼ������
	 */
	public void init() {
		// ���ô��ڹرշ�ʽ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//ȡ����󻯵�����
		this.setResizable(false);
		// ��ʹ��ϵͳ������
		this.setUndecorated(true);
		this.getRootPane().setWindowDecorationStyle(JRootPane.ERROR_DIALOG);
		// �������
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		// ���ñ���ͼƬ
		this.bgImage = new ImageIcon("images/skin1.png");
		this.imgLabel = new JLabel(bgImage);
		// ע�������ǹؼ�����������ǩ��ӵ�JFram��LayeredPane����
		getLayeredPane().add(this.imgLabel, new Integer(Integer.MIN_VALUE));
		this.imgLabel.setBounds(0, 0, this.bgImage.getIconWidth(), this.bgImage.getIconHeight());
		((JPanel)getContentPane()).setOpaque(true);
		// ���ò��ַ�ʽ
		this.cp = getContentPane();
		this.cp.setLayout(null);
		// ����ͷ��
		this.headImage = new ImageIcon("images/1.gif");
		this.headPortrait = new JLabel(this.headImage);	
		this.cp.add(this.headPortrait);
		this.headPortrait.setBounds(18, 118, 84, 83);
		((JPanel)getContentPane()).setOpaque(true);			
		// ����״̬
		JPanel jpstatus = new JPanel();
		jpstatus.add(new JLabel());
		statusComboBox = new JComboBox();
		statusComboBox.addItem(Language.online);
		statusComboBox.addItem(Language.hiding);
		statusComboBox.addItem(Language.leave);
		statusComboBox.addItem(Language.dnd);
		statusComboBox.addItem(Language.offline);
		jpstatus.add(statusComboBox);
		cp.add(jpstatus);
		jpstatus.setBounds(100, 190, 60, 28);	
		// �˺������
		this.accout = new JTextField(Language.accountTip, 12);
		this.cp.add(this.accout);
		this.accout.setBounds(112, 128, 190, 28);
		this.accout.addFocusListener(new FocusAdapter() {
			
			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				accout.setText("");
			}
		});	
		Hyperlink h = new Hyperlink(Language.registerAccount, "http://" + Language.ServerIP + ":8888/HiqServer/register.jsp");
		h.setBounds(313, 130, 70, 19);
		this.cp.add(h);	
		
		// ���������
		this.password = new JPasswordField(Language.password, 12);
		this.cp.add(this.password);
		this.password.setBounds(112, 163, 190, 28);
		this.password.setText(Language.password);		
		Hyperlink pass = new Hyperlink(Language.forgotPassword, "http://" + Language.ServerIP + ":8888/HiqServer/findPassword.jsp");
		pass.setBounds(313, 170, 70, 19);
		this.cp.add(pass);		
		this.password.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				// TODO Auto-generated method stub
				password.setText("");
			}
		});
		this.password.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					loginService();
				}
			}
		});
		// ��ס����
		rememberPassword = new JCheckBox();
		rememberPassword.setBounds(162, 202, 15, 15);
		cp.add(rememberPassword);
		this.rememberPassword.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if(rememberPassword.isSelected() == false){
					automaticLogin.setSelected(false);
				}
				
			}
		});
		this.rPass = new JLabel(Language.rememberPassword);
		this.rPass.setBounds(180, 202, 70, 15);
		this.cp.add(this.rPass);
		// �Զ���¼
		automaticLogin = new JCheckBox();
		automaticLogin.setBounds(242, 202, 15, 15);
		cp.add(automaticLogin);
		automaticLogin.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				if(automaticLogin.isSelected() == true){
					rememberPassword.setSelected(true);
				}
				if (rememberPassword.isSelected() == false) {
					automaticLogin.setSelected(false);
				}		
			}
		});
		this.fPass = new JLabel(Language.autoLogin);
		this.fPass.setBounds(260, 202, 70, 15);
		this.cp.add(fPass);
		// ��½��ť
		this.login = new JButton(Language.login);
		Font font = new Font("�����п�",0,24);
		this.login.setFont(font);
		this.login.setBackground(Color.pink);
		this.setBounds(118, 240, 170, 30);
		this.cp.add(login);
		this.login.setBounds(101, 228, 181, 35);
		this.login.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// ��½������
				loginService();
			}
		});

		// ע����������������Ϊ͸��������LayeredPane����еı���������ʾ������
		((JPanel) this.cp).setOpaque(false);
		// ��ȡ��ǰ��Ļ�Ĵ�С
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		x = (d.width - width) / 2;
		y = (d.height - height) / 2;
		// ���õ�½���ڵ�λ�úʹ�С
		setBounds(x, y, width, height);	
		// ��ʾ����
		setVisible(true);
	}
	/**
	 * ��½������
	 */
	protected void loginService() {
		try {
			UserBiz userBiz = new UserBizImpl();
			int userQQ = Integer.parseInt( accout.getText().trim());
			@SuppressWarnings("deprecation")
			String userPassword = password.getText().trim();
			int userState = statusComboBox.getSelectedIndex();
			// ��֤�û��ĺϷ���
			User user = userBiz.isValidUser(userQQ, userPassword, userState);
			if (user != null) {
				System.out.println("Login�û��Ϸ�" + userQQ + " " + userPassword);
				new MainUI("HiQ 2014", user);
			} else {
				JOptionPane.showMessageDialog(null, Language.userNameOrPasswordMistake, Language.err, JOptionPane.WARNING_MESSAGE);
			}
		} catch (FileNotFoundException e1) {
			// δ�ҵ����������ļ�
//			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, Language.configurationFileNotFound, Language.err, JOptionPane.WARNING_MESSAGE);
		} catch (IOException e1) {
			// ���������Ӵ���
//			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, Language.serverConnectionFailed, Language.err, JOptionPane.WARNING_MESSAGE);
		} catch (ClassNotFoundException e1) {
			// �����е���δ�ҵ�
//			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, Language.programError, Language.err, JOptionPane.WARNING_MESSAGE);
		} catch (NumberFormatException e2) {
			// ������˺Ų�������
			JOptionPane.showMessageDialog(null, Language.accountIllegal, Language.err, JOptionPane.WARNING_MESSAGE);
		}
		dispose();
	}

	public static void main(String[] args) {
		new Language();
		new Login("HiQ 2014");
	}
}

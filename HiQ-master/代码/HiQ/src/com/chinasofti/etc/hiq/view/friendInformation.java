package com.chinasofti.etc.hiq.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRootPane;
import javax.swing.JTabbedPane;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.chinasofti.etc.hiq.po.User;

public class friendInformation extends JFrame {
	private static final long serialVersionUID = -266077703729622905L;
	private int x = 0;
	private int y = 0;
	private int width = 461;
	private int height = 671;
	private Container cp;
	private ImageIcon bgImage,headImage,imag,centerImage;
	private JLabel bg,headPortrait,name,userId,separtot,sex,age,address,cL;
	private JButton change;
	private JLabel person,note,homeLand,add,phoneNumber,email,username,school;

	private User userMy;
	private JToggleButton jbtClose;
	/**
	 * ���캯������ȡ����ʾ��������
	 * @param arg0
	 * @param userMy
	 * @throws HeadlessException
	 */
	public friendInformation(String arg0, User userMy) throws HeadlessException {
		super(arg0);
		this.userMy = userMy;
		init();
	}

	public void init(){
		// ���ô��ڹرշ�ʽ
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//������ʾ
		this.setLocationRelativeTo(this);
		
		//ȡ�������С��������
		this.setUndecorated(true);
		this.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		//����
		this.bgImage = new ImageIcon("images/main.jpg");
		this.bg = new JLabel(this.bgImage);
		getLayeredPane().add(this.bg,new Integer(Integer.MIN_VALUE));
		this.bg.setBounds(0, 0, this.bgImage.getIconWidth(), 265);
		((JPanel)getContentPane()).setOpaque(false);
		this.cp = getContentPane();
		this.cp.setLayout(null);
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
		jbtClose.setBounds(width - 35, 0, 35, 20);
		//ͷ��
		this.headImage = new ImageIcon("images/" + userMy.getUserImage());
		this.headPortrait = new JLabel(this.headImage);	
		this.cp.add(this.headPortrait);
		this.headPortrait.setBounds(10, 93, 77, 77);
		//����ͷ��ť
		change = new JButton("����ͷ��");
		Font font1 = new Font("����",0,12);
		change.setFont(font1);
		this.change.setBounds(10, 175, 80, 25);
		this.cp.add(change);
		//��ȡ����ʾ���ݿ����Ϣ
		name =new JLabel(userMy.getUserNikName());
		Font font = new Font("����",0,24);
		name.setForeground(Color.white);
		this.name.setFont(font);
		this.cp.add(this.name);
		this.name.setBounds(100, 93, 200, 30);
		
		userId = new JLabel(userMy.getUserQQ() + "");
		this.userId.setBounds(180, 105, 100, 20);
		cp.add(userId);
		userId.setForeground(Color.white);
		
		sex = new JLabel(userMy.getUserSex() > 0 ? "��" : "Ů");
		this.sex.setBounds(110, 150, 16, 15);
		cp.add(sex);
		sex.setForeground(Color.white);
		
		age = new JLabel(userMy.getUserAge() +  "��");
		this.age.setBounds(130, 150, 40, 15);
		this.cp.add(age);
		age.setForeground(Color.white);
		
		address = new JLabel(userMy.getUserAddress());
		this.address.setBounds(110, 175, 200, 15);
		this.cp.add(address);
		address.setForeground(Color.white);
		//�ָ���
		imag = new ImageIcon("images/spry.png");
		separtot = new JLabel(imag);
		this.cp.add(separtot);
		this.separtot.setBounds(0, 200, 461, 4);
		//���ѵ���ϸ����
		final JTabbedPane tablePane = new JTabbedPane(JTabbedPane.TOP);
		tablePane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				int selectedIndex = tablePane.getSelectedIndex();
				tablePane.getTitleAt(selectedIndex);	
			}
		});
		tablePane.setBounds(0, 205, width, 390);
		this.cp.add(tablePane);
		final JPanel tabPane1 = new JPanel();
		tabPane1.setLayout(null);
		tablePane.addTab("   ��ϸ����   ", tabPane1);
		tabPane1.setLayout(null);
		tabPane1.setSize(461, 400);
		
		//�ײ��ı���ͼƬ
		centerImage = new ImageIcon("images/main.jpg");
		this.cL = new JLabel(centerImage);
		getLayeredPane().add(this.cL, new Integer(Integer.MIN_VALUE));
		this.cL.setBounds(0,600, this.centerImage.getIconWidth(), this.centerImage.getIconHeight());
		((JPanel)getContentPane()).setOpaque(false);
		this.cp = getContentPane();
		
		//��ϸ����
		person = new JLabel("����˵����	" + userMy.getUserSpeak());
		this.person.setBounds(15, 10, 400, 20);

		tabPane1.add(person);
		note = new JLabel("��        �ˣ�  ��  25��  ����ʮ�ģ�ũ�����գ�  ����  Ħ����   O��Ѫ");
		this.note.setBounds(15, 50, 400, 20);
	
		tabPane1.add(note);
		
		homeLand = new JLabel("��        �磺	" + userMy.getUserAddress());
		this.homeLand.setBounds(15, 90, 400, 20);
		
		tabPane1.add(homeLand);		
		
		add = new JLabel("��  ��  �أ� �й�   ���   ��������");
		this.add.setBounds(15, 130, 400, 20);
		
		tabPane1.add(add);
		phoneNumber = new JLabel("��        ���� 18766968888");
		this.phoneNumber.setBounds(15, 170, 400, 20);
		tabPane1.add(phoneNumber);
		
		email = new JLabel("��        �䣺 " + userMy.getUserEmail());
		this.email.setBounds(15, 210, 400, 20);
		tabPane1.add(email);
		
		username = new JLabel("��        ����	" + userMy.getUserNikName());
		this.username.setBounds(15, 250, 400, 20);
		tabPane1.add(username);
		
		school = new JLabel("ѧ        У�� ɽ������ѧ");
		this.school.setBounds(15, 290, 400, 20);
		tabPane1.add(school);
		
		// ע����������������Ϊ͸��������LayeredPane����еı���������ʾ������
		//((JPanel) this.cp).setOpaque(false);
		// ��ȡ��ǰ��Ļ�Ĵ�С
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		x = (d.width - width) / 2;
		y = (d.height - height) / 2;
		// ���õ�½���ڵ�λ�úʹ�С
		setBounds(x, y, width, height);
		
		// ��ʾ����
		setVisible(true);
	}

	protected void do_jbtClose_itemStateChanged(MouseEvent e) {
		// TODO Auto-generated method stub
		dispose();
	}
}

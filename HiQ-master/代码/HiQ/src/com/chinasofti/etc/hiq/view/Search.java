package com.chinasofti.etc.hiq.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.chinasofti.etc.hiq.dao.impl.TCPConnect;
import com.chinasofti.etc.hiq.po.MessagePackge;
import com.chinasofti.etc.hiq.po.User;


@SuppressWarnings("serial")
public class Search extends JFrame implements ActionListener {
	JButton b1;
	private JTextField textA;
	private JTextField textB;
	private JLabel labelNickName;
	private JComboBox boxAge ;
	private JComboBox boxSex;
	private JTextField textNickName;

	public Search() {
		super();
		setTitle("����");
		setBounds(270, 210, 730, 290);

		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// ����ѡ��Ĳ��ַ�ʽ��
		tabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);

		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int selectedIndex = tabbedPane.getSelectedIndex(); // ���ѡ�е�ѡ�����
				String title = tabbedPane.getTitleAt(selectedIndex); // ���ѡ���ǩ
				System.out.println(title);
			}
		});

		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		// ѡ�A
		ImageIcon imageIcon = new ImageIcon("images/����.jpg");
		textA = new JTextField(30);
		final JPanel tabPanelA = new JPanel();
		JLabel labelSearch1 = new JLabel();
		ImageIcon imageIcon2 = new ImageIcon("images/����2.gif");
		labelSearch1.setIcon(imageIcon2);
		labelSearch1.setBounds(300, 20, 300, 120);
		JLabel labelA = new JLabel();
		JButton b1 = new JButton("����");// ��ѯ��ť
		b1.addActionListener(this);
		b1.setActionCommand("result1");
		tabbedPane.addTab("��ȷ����", imageIcon, tabPanelA, "��ȷ����");
		tabbedPane.setBackgroundAt(0, Color.WHITE);
		// ע����������������Ϊ͸��������LayeredPane����еı���������ʾ������
		((JPanel) tabPanelA).setOpaque(false);
		tabPanelA.setLayout(null);
		labelA.setBounds(140, 142, 120, 40);
		textA.setBounds(260, 150, 200, 25);
		b1.setBounds(470, 150, 80, 25);
		labelA.setFont(new Font("����", Font.BOLD, 15));
		labelA.setText("�������û�ID��");
		tabPanelA.add(labelA);
		tabPanelA.add(textA);
		tabPanelA.add(b1);
		tabPanelA.add(labelSearch1);

		b1.setBackground(Color.getHSBColor(146, 131, 57));

		// ѡ�B
		textB = new JTextField(30);
		final JPanel tabPanelB = new JPanel();
		JLabel labelB = new JLabel();
		JLabel labelSearch2 = new JLabel();
		ImageIcon imageIcon3 = new ImageIcon("images/����2.gif");
		labelSearch2.setIcon(imageIcon3);
		labelSearch2.setBounds(300, 20, 300, 120);
		JButton b2 = new JButton("����");// ��ѯ��ť
		tabbedPane.addTab("�û�������", imageIcon, tabPanelB, "�û�������");
		tabbedPane.setBackgroundAt(1, Color.WHITE);
		tabPanelB.setLayout(null);
		labelB.setBounds(140, 142, 120, 40);
		textB.setBounds(260, 150, 200, 25);
		b2.setBounds(470, 150, 80, 25);
		labelB.setFont(new Font("����", Font.BOLD, 15));
		labelB.setText("�������û�����");
		tabPanelB.add(labelB);
		tabPanelB.add(textB);
		tabPanelB.add(b2);
		tabPanelB.add(labelSearch2);
		tabPanelB.setBackground(Color.getHSBColor(146, 130, 57));
		b2.setBackground(Color.getHSBColor(146, 131, 57));
		b2.addActionListener(this);
		b2.setActionCommand("result2");
		// ѡ�C
		final JPanel tabPanelC = new JPanel();
		tabbedPane.addTab("��������", imageIcon, tabPanelC, "��������");
		tabbedPane.setBackgroundAt(2, Color.WHITE);
		labelNickName = new JLabel();
		JLabel labelSex = new JLabel();
		JLabel labelAge = new JLabel();
		JLabel labelAnimalSign = new JLabel();
		JLabel labelBloodType = new JLabel();
		JLabel labelHometown = new JLabel();
		JLabel labelLocation = new JLabel();
		JButton b3 = new JButton("����");// ��ѯ��ť
		textNickName = new JTextField(50);
		JTextField textHometown = new JTextField(15);
		JTextField textLocation = new JTextField(15);
		labelNickName.setText("�ǳ�:");
		labelSex.setText("�Ա�:");
		labelAge.setText("����:");
		labelAnimalSign.setText("��Ф:");
		labelBloodType.setText("Ѫ��:");
		labelHometown.setText("����:");
		labelLocation.setText("���ڵ�:");
		tabPanelC.setLayout(null);

		ImageIcon bgImageB = new ImageIcon("images/�߿�.gif");
		JLabel imgLabelB = new JLabel(bgImageB);
		// ע�������ǹؼ�����������ǩ��ӵ�JFram��LayeredPane����
		getLayeredPane().add(imgLabelB, new Integer(Integer.MIN_VALUE));
		imgLabelB.setBounds(-10, -300, 800, 600);

		labelNickName.setBounds(70, 35, 50, 40); // �����ǳƼ����ı���
		labelNickName.setFont(new Font("����", Font.PLAIN, 13));
		textNickName.setBounds(120, 43, 446, 23);
		labelAge.setBounds(70, 80, 50, 40); // �������估��������
		labelAge.setFont(new Font("����", Font.PLAIN, 13));
		String[] age = { "����", "16-22��", "23-30��", "31-40��", "40������", };
		boxAge = new JComboBox(age);
		boxAge.setBounds(120, 88, 100, 23);
		labelSex.setBounds(240, 80, 50, 40); // �����Ա���������
		labelSex.setFont(new Font("����", Font.PLAIN, 13));
		String[] sex = { "����", "Ů",  "��"};
		boxSex = new JComboBox(sex);
		boxSex.setBounds(290, 88, 100, 23);
		labelLocation.setBounds(410, 80, 50, 40); // �������ڵؼ����ı���
		labelLocation.setFont(new Font("����", Font.PLAIN, 13));
		textLocation.setBounds(465, 88, 100, 23);
		labelAnimalSign.setBounds(70, 125, 50, 40); // ������Ф����������
		labelAnimalSign.setFont(new Font("����", Font.PLAIN, 13));
		String[] animalsign = { "����", "��", "ţ", "��", "��", "��", "��", "��", "��",
				"��", "��", "��", "��" };
		JComboBox boxAnimalSign = new JComboBox(animalsign);
		boxAnimalSign.setBounds(120, 133, 100, 23);
		labelBloodType.setBounds(240, 125, 50, 40); // ����Ѫ�ͼ���������
		labelBloodType.setFont(new Font("����", Font.PLAIN, 13));
		String[] bloodtype = { "����", "A��Ѫ", "B��Ѫ", "O��Ѫ", "AB��Ѫ", "����Ѫ��" };
		JComboBox boxBloodType = new JComboBox(bloodtype);
		boxBloodType.setBounds(290, 133, 100, 23);
		labelHometown.setBounds(410, 125, 50, 40); // ���ֹ��缰���ı���
		labelHometown.setFont(new Font("����", Font.PLAIN, 13));
		textHometown.setBounds(465, 133, 100, 23);
		b3.setBounds(600, 97, 80, 25);
		tabPanelC.add(labelNickName);
		tabPanelC.add(labelSex);
		tabPanelC.add(labelAge);
		tabPanelC.add(labelAnimalSign);
		tabPanelC.add(labelBloodType);
		tabPanelC.add(labelHometown);
		tabPanelC.add(labelLocation);
		tabPanelC.add(textNickName);
		tabPanelC.add(boxSex);
		tabPanelC.add(boxAge);
		tabPanelC.add(boxAnimalSign);
		tabPanelC.add(boxBloodType);
		tabPanelC.add(textHometown);
		tabPanelC.add(textLocation);
		
		tabPanelC.add(b3);
		tabPanelC.add(imgLabelB);
		b3.setBackground(Color.getHSBColor(146, 131, 57));
		b3.addActionListener(this);
		b3.setActionCommand("result3");
		// tabLabelC.setText("��ѯC");
		tabbedPane.setSelectedIndex(0); // ����Ĭ��ѡ�е�
		// tabbedPane.setEnabledAt(0,false); //��������0����岻����
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// ��QQ�Ų���
		if (e.getActionCommand() == "result1") {
			try {
				ObjectOutputStream objectOutputStream = TCPConnect.objectOutputStream;
				MessagePackge messagePackge = new MessagePackge(2, textA.getText());
				objectOutputStream.writeObject(messagePackge);
				System.out.println(">>>>>" + "�����ѷ���");
				ObjectInputStream objectInputStream = TCPConnect.objectInputStream;
				User user = (User) objectInputStream.readObject();
				new SearchResult(user);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
			
		}
		if (e.getActionCommand() == "result2") {
			// ���ǳƲ���
			try {
				ObjectOutputStream objectOutputStream = TCPConnect.objectOutputStream;
				MessagePackge messagePackge = new MessagePackge(3,
						textB.getText());
				objectOutputStream.writeObject(messagePackge);
				System.out.println(">>>>>" + "�����ѷ���");
				ObjectInputStream objectInputStream = TCPConnect.objectInputStream;
				User user = (User) objectInputStream.readObject();
				new SearchResult(user);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getActionCommand() == "result3") {
			 // ��������
			try {
				ObjectOutputStream objectOutputStream = TCPConnect.objectOutputStream;
				MessagePackge messagePackge = new MessagePackge(4,
						textNickName.getText() + ";" + (boxSex.getSelectedIndex()-1));
				objectOutputStream.writeObject(messagePackge);
				System.out.println(">>>>>" + "�����ѷ���");
				ObjectInputStream objectInputStream = TCPConnect.objectInputStream;
				User user = (User) objectInputStream.readObject();
				new SearchResult(user);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}

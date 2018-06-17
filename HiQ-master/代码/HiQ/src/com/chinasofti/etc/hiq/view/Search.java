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
		setTitle("查找");
		setBounds(270, 210, 730, 290);

		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		// 设置选项卡的布局方式。
		tabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);

		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int selectedIndex = tabbedPane.getSelectedIndex(); // 获得选中的选项卡索引
				String title = tabbedPane.getTitleAt(selectedIndex); // 获得选项卡标签
				System.out.println(title);
			}
		});

		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		// 选项卡A
		ImageIcon imageIcon = new ImageIcon("images/搜索.jpg");
		textA = new JTextField(30);
		final JPanel tabPanelA = new JPanel();
		JLabel labelSearch1 = new JLabel();
		ImageIcon imageIcon2 = new ImageIcon("images/查找2.gif");
		labelSearch1.setIcon(imageIcon2);
		labelSearch1.setBounds(300, 20, 300, 120);
		JLabel labelA = new JLabel();
		JButton b1 = new JButton("查找");// 查询按钮
		b1.addActionListener(this);
		b1.setActionCommand("result1");
		tabbedPane.addTab("精确查找", imageIcon, tabPanelA, "精确查找");
		tabbedPane.setBackgroundAt(0, Color.WHITE);
		// 注意这里，将内容面板设为透明。这样LayeredPane面板中的背景才能显示出来。
		((JPanel) tabPanelA).setOpaque(false);
		tabPanelA.setLayout(null);
		labelA.setBounds(140, 142, 120, 40);
		textA.setBounds(260, 150, 200, 25);
		b1.setBounds(470, 150, 80, 25);
		labelA.setFont(new Font("宋体", Font.BOLD, 15));
		labelA.setText("请输入用户ID：");
		tabPanelA.add(labelA);
		tabPanelA.add(textA);
		tabPanelA.add(b1);
		tabPanelA.add(labelSearch1);

		b1.setBackground(Color.getHSBColor(146, 131, 57));

		// 选项卡B
		textB = new JTextField(30);
		final JPanel tabPanelB = new JPanel();
		JLabel labelB = new JLabel();
		JLabel labelSearch2 = new JLabel();
		ImageIcon imageIcon3 = new ImageIcon("images/查找2.gif");
		labelSearch2.setIcon(imageIcon3);
		labelSearch2.setBounds(300, 20, 300, 120);
		JButton b2 = new JButton("查找");// 查询按钮
		tabbedPane.addTab("用户名查找", imageIcon, tabPanelB, "用户名查找");
		tabbedPane.setBackgroundAt(1, Color.WHITE);
		tabPanelB.setLayout(null);
		labelB.setBounds(140, 142, 120, 40);
		textB.setBounds(260, 150, 200, 25);
		b2.setBounds(470, 150, 80, 25);
		labelB.setFont(new Font("宋体", Font.BOLD, 15));
		labelB.setText("请输入用户名：");
		tabPanelB.add(labelB);
		tabPanelB.add(textB);
		tabPanelB.add(b2);
		tabPanelB.add(labelSearch2);
		tabPanelB.setBackground(Color.getHSBColor(146, 130, 57));
		b2.setBackground(Color.getHSBColor(146, 131, 57));
		b2.addActionListener(this);
		b2.setActionCommand("result2");
		// 选项卡C
		final JPanel tabPanelC = new JPanel();
		tabbedPane.addTab("条件查找", imageIcon, tabPanelC, "条件查找");
		tabbedPane.setBackgroundAt(2, Color.WHITE);
		labelNickName = new JLabel();
		JLabel labelSex = new JLabel();
		JLabel labelAge = new JLabel();
		JLabel labelAnimalSign = new JLabel();
		JLabel labelBloodType = new JLabel();
		JLabel labelHometown = new JLabel();
		JLabel labelLocation = new JLabel();
		JButton b3 = new JButton("查找");// 查询按钮
		textNickName = new JTextField(50);
		JTextField textHometown = new JTextField(15);
		JTextField textLocation = new JTextField(15);
		labelNickName.setText("昵称:");
		labelSex.setText("性别:");
		labelAge.setText("年龄:");
		labelAnimalSign.setText("生肖:");
		labelBloodType.setText("血型:");
		labelHometown.setText("故乡:");
		labelLocation.setText("所在地:");
		tabPanelC.setLayout(null);

		ImageIcon bgImageB = new ImageIcon("images/边框.gif");
		JLabel imgLabelB = new JLabel(bgImageB);
		// 注意这里是关键，将背景标签添加到JFram的LayeredPane面板里。
		getLayeredPane().add(imgLabelB, new Integer(Integer.MIN_VALUE));
		imgLabelB.setBounds(-10, -300, 800, 600);

		labelNickName.setBounds(70, 35, 50, 40); // 布局昵称及其文本框
		labelNickName.setFont(new Font("宋体", Font.PLAIN, 13));
		textNickName.setBounds(120, 43, 446, 23);
		labelAge.setBounds(70, 80, 50, 40); // 布局年龄及其下拉框
		labelAge.setFont(new Font("宋体", Font.PLAIN, 13));
		String[] age = { "不限", "16-22岁", "23-30岁", "31-40岁", "40岁以上", };
		boxAge = new JComboBox(age);
		boxAge.setBounds(120, 88, 100, 23);
		labelSex.setBounds(240, 80, 50, 40); // 布局性别及其下拉框
		labelSex.setFont(new Font("宋体", Font.PLAIN, 13));
		String[] sex = { "不限", "女",  "男"};
		boxSex = new JComboBox(sex);
		boxSex.setBounds(290, 88, 100, 23);
		labelLocation.setBounds(410, 80, 50, 40); // 布局所在地及其文本框
		labelLocation.setFont(new Font("宋体", Font.PLAIN, 13));
		textLocation.setBounds(465, 88, 100, 23);
		labelAnimalSign.setBounds(70, 125, 50, 40); // 布局生肖及其下拉框
		labelAnimalSign.setFont(new Font("宋体", Font.PLAIN, 13));
		String[] animalsign = { "不限", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊",
				"猴", "鸡", "狗", "猪" };
		JComboBox boxAnimalSign = new JComboBox(animalsign);
		boxAnimalSign.setBounds(120, 133, 100, 23);
		labelBloodType.setBounds(240, 125, 50, 40); // 布局血型及其下拉框
		labelBloodType.setFont(new Font("宋体", Font.PLAIN, 13));
		String[] bloodtype = { "不限", "A型血", "B型血", "O型血", "AB型血", "其他血型" };
		JComboBox boxBloodType = new JComboBox(bloodtype);
		boxBloodType.setBounds(290, 133, 100, 23);
		labelHometown.setBounds(410, 125, 50, 40); // 布局故乡及其文本框
		labelHometown.setFont(new Font("宋体", Font.PLAIN, 13));
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
		// tabLabelC.setText("查询C");
		tabbedPane.setSelectedIndex(0); // 设置默认选中的
		// tabbedPane.setEnabledAt(0,false); //设置索引0的面板不可用
		setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// 按QQ号查找
		if (e.getActionCommand() == "result1") {
			try {
				ObjectOutputStream objectOutputStream = TCPConnect.objectOutputStream;
				MessagePackge messagePackge = new MessagePackge(2, textA.getText());
				objectOutputStream.writeObject(messagePackge);
				System.out.println(">>>>>" + "查找已发送");
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
			// 按昵称查找
			try {
				ObjectOutputStream objectOutputStream = TCPConnect.objectOutputStream;
				MessagePackge messagePackge = new MessagePackge(3,
						textB.getText());
				objectOutputStream.writeObject(messagePackge);
				System.out.println(">>>>>" + "查找已发送");
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
			 // 条件查找
			try {
				ObjectOutputStream objectOutputStream = TCPConnect.objectOutputStream;
				MessagePackge messagePackge = new MessagePackge(4,
						textNickName.getText() + ";" + (boxSex.getSelectedIndex()-1));
				objectOutputStream.writeObject(messagePackge);
				System.out.println(">>>>>" + "查找已发送");
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

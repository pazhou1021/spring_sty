package com.chinasofti.etc.hiq.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.chinasofti.etc.hiq.dao.impl.TCPConnect;
import com.chinasofti.etc.hiq.po.MessagePackge;
import com.chinasofti.etc.hiq.po.User;

@SuppressWarnings("serial")
public class AddFriend extends JFrame {

	public AddFriend(final User user) {
		super();
		setTitle("添加好友");
		setBounds(400, 200, 457, 358);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);

		JPanel addPanel = (JPanel) getContentPane();
		addPanel.setLayout(null);
		ImageIcon HeadImage = new ImageIcon("images/头像.gif");// 从数据库获取头像图片
		JLabel HeadPortrait = new JLabel();// 显示头像的标签
		HeadPortrait.setIcon(HeadImage);
		HeadPortrait.setBounds(35, 45, 62, 62);
		JLabel NickName = new JLabel();// 昵称
		NickName.setText(user.getUserNikName());
		NickName.setBounds(38, 123, 100, 32);
		NickName.setFont(new Font("宋体", Font.BOLD, 14));
		JLabel HiQNum = new JLabel();// QQ号
		HiQNum.setText(user.getUserQQ() + "");
		HiQNum.setBounds(35, 150, 100, 22);
		HiQNum.setFont(new Font("宋体", Font.BOLD, 13));
		JLabel Sex = new JLabel();// 性别
		Sex.setText("性别: " + (user.getUserSex() > 0 ? "男" : "女"));
		Sex.setBounds(38, 200, 100, 22);
		JLabel Age = new JLabel();// 年龄
		Age.setText("年龄:" + user.getUserAge() + "岁 ");
		Age.setBounds(38, 225, 100, 22);
		JLabel Location = new JLabel();// 所在地
		Location.setText("所在地:" + user.getUserAddress());
		Location.setBounds(38, 250, 100, 22);
		JLabel fenzu = new JLabel();// 分组
		fenzu.setText("分   组:");
		fenzu.setBounds(170, 60, 100, 23);
		String[] FriendDiv = { "我的好友" };// 分组下拉框
		JComboBox Friend = new JComboBox(FriendDiv);
		Friend.setBounds(220, 60, 150, 23);
		JButton Finish = new JButton("完成");// 完成按钮
		Finish.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// 确定添加
				try {
					ObjectOutputStream objectOutputStream = TCPConnect.objectOutputStream;
					MessagePackge messagePackge = new MessagePackge(22, user.getUserQQ() + "");
					objectOutputStream.writeObject(messagePackge);
					JOptionPane.showMessageDialog(null, "添加成功", "提示", JOptionPane.WARNING_MESSAGE);
					dispose();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		Finish.setBounds(290, 290, 67, 23);
		JButton Cancel = new JButton("取消");// 取消按钮
		Cancel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// 取消添加，关闭此窗口
				dispose();
			}
		});
		Cancel.setBounds(370, 290, 67, 23);
		addPanel.setBackground(Color.getHSBColor(5, 81, 99));
		addPanel.add(HeadPortrait);
		addPanel.add(NickName);
		addPanel.add(HiQNum);
		addPanel.add(Sex);
		addPanel.add(Age);
		addPanel.add(Location);
		addPanel.add(Friend);
		addPanel.add(fenzu);
		addPanel.add(Finish);
		addPanel.add(Cancel);
		setVisible(true);
	}
}

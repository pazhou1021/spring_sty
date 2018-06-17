package com.chinasofti.etc.hiq.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.chinasofti.etc.hiq.po.User;

@SuppressWarnings("serial")
public class SearchResult extends JFrame implements ActionListener {

	JButton addF;
	private User user;

	public SearchResult(User user) {
		super();
		this.user = user;
		if (user == null) {
			JOptionPane.showMessageDialog(null, "你查找的号码不存在", "提示",
					JOptionPane.WARNING_MESSAGE);
		} else {
			setTitle("查找结果");
			setBounds(320, 310, 630, 180);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setResizable(false);
			// 没有搜索到符合条件的用户

			// 找到相关用户
			JPanel resultPanel = (JPanel) getContentPane();
			resultPanel.setLayout(null);
			ImageIcon HeadImage = new ImageIcon("images/" + user.getUserImage());// 从数据库获取头像图片
			JLabel HeadPortrait = new JLabel();
			HeadPortrait.setIcon(HeadImage);
			HeadPortrait.setBounds(35, 35, 62, 62);

			resultPanel.setBackground(Color.getHSBColor(5, 81, 99));
			JLabel NickName = new JLabel();
			NickName.setText(user.getUserNikName() + "(" + user.getUserQQ()
					+ ")");// 昵称和QQ号
			NickName.setBounds(105, 38, 200, 22);
			NickName.setFont(new Font("宋体", Font.BOLD, 13));
			JLabel Information = new JLabel();// 基本信息
			Information.setText((user.getUserSex() > 0 ? "男" : "女") + " "
					+ user.getUserAge() + "岁" + user.getUserAddress());
			Information.setBounds(105, 70, 200, 22);
			ImageIcon Detail = new ImageIcon("images/详细资料图标.gif");
			JButton detail = new JButton();// 详细资料按钮
			detail.setToolTipText("查看个人资料");
			detail.setActionCommand("checkInformation");
			detail.setIcon(Detail);
			detail.setBounds(475, 44, 34, 25);
			ImageIcon Talk = new ImageIcon("images/打个招呼图标.gif");
			JButton talk = new JButton();// 对话按钮
			talk.setToolTipText("向他打招呼");
			talk.setIcon(Talk);
			talk.setBounds(525, 44, 34, 25);
			ImageIcon Add = new ImageIcon("images/添加好友图标.gif");
			JButton addF = new JButton();// 添加好友按钮
			addF.addActionListener(this);
			addF.setActionCommand("addF");
			addF.setToolTipText("加为好友");
			addF.setIcon(Add);
			addF.setBounds(575, 44, 34, 25);
			resultPanel.add(detail);
			resultPanel.add(talk);
			resultPanel.add(addF);
			resultPanel.add(Information);
			resultPanel.add(NickName);
			resultPanel.add(HeadPortrait);
			setVisible(true);
		}
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("addF")) {
			new AddFriend(user);
			dispose();
		} else if (e.getActionCommand().equals("checkInformation")) {
			new MyInformation("个人资料", user);
		}
	}
}

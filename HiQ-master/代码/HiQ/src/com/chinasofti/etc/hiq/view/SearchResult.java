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
			JOptionPane.showMessageDialog(null, "����ҵĺ��벻����", "��ʾ",
					JOptionPane.WARNING_MESSAGE);
		} else {
			setTitle("���ҽ��");
			setBounds(320, 310, 630, 180);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setResizable(false);
			// û�������������������û�

			// �ҵ�����û�
			JPanel resultPanel = (JPanel) getContentPane();
			resultPanel.setLayout(null);
			ImageIcon HeadImage = new ImageIcon("images/" + user.getUserImage());// �����ݿ��ȡͷ��ͼƬ
			JLabel HeadPortrait = new JLabel();
			HeadPortrait.setIcon(HeadImage);
			HeadPortrait.setBounds(35, 35, 62, 62);

			resultPanel.setBackground(Color.getHSBColor(5, 81, 99));
			JLabel NickName = new JLabel();
			NickName.setText(user.getUserNikName() + "(" + user.getUserQQ()
					+ ")");// �ǳƺ�QQ��
			NickName.setBounds(105, 38, 200, 22);
			NickName.setFont(new Font("����", Font.BOLD, 13));
			JLabel Information = new JLabel();// ������Ϣ
			Information.setText((user.getUserSex() > 0 ? "��" : "Ů") + " "
					+ user.getUserAge() + "��" + user.getUserAddress());
			Information.setBounds(105, 70, 200, 22);
			ImageIcon Detail = new ImageIcon("images/��ϸ����ͼ��.gif");
			JButton detail = new JButton();// ��ϸ���ϰ�ť
			detail.setToolTipText("�鿴��������");
			detail.setActionCommand("checkInformation");
			detail.setIcon(Detail);
			detail.setBounds(475, 44, 34, 25);
			ImageIcon Talk = new ImageIcon("images/����к�ͼ��.gif");
			JButton talk = new JButton();// �Ի���ť
			talk.setToolTipText("�������к�");
			talk.setIcon(Talk);
			talk.setBounds(525, 44, 34, 25);
			ImageIcon Add = new ImageIcon("images/��Ӻ���ͼ��.gif");
			JButton addF = new JButton();// ��Ӻ��Ѱ�ť
			addF.addActionListener(this);
			addF.setActionCommand("addF");
			addF.setToolTipText("��Ϊ����");
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
			new MyInformation("��������", user);
		}
	}
}

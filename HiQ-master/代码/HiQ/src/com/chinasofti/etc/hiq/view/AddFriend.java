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
		setTitle("��Ӻ���");
		setBounds(400, 200, 457, 358);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);

		JPanel addPanel = (JPanel) getContentPane();
		addPanel.setLayout(null);
		ImageIcon HeadImage = new ImageIcon("images/ͷ��.gif");// �����ݿ��ȡͷ��ͼƬ
		JLabel HeadPortrait = new JLabel();// ��ʾͷ��ı�ǩ
		HeadPortrait.setIcon(HeadImage);
		HeadPortrait.setBounds(35, 45, 62, 62);
		JLabel NickName = new JLabel();// �ǳ�
		NickName.setText(user.getUserNikName());
		NickName.setBounds(38, 123, 100, 32);
		NickName.setFont(new Font("����", Font.BOLD, 14));
		JLabel HiQNum = new JLabel();// QQ��
		HiQNum.setText(user.getUserQQ() + "");
		HiQNum.setBounds(35, 150, 100, 22);
		HiQNum.setFont(new Font("����", Font.BOLD, 13));
		JLabel Sex = new JLabel();// �Ա�
		Sex.setText("�Ա�: " + (user.getUserSex() > 0 ? "��" : "Ů"));
		Sex.setBounds(38, 200, 100, 22);
		JLabel Age = new JLabel();// ����
		Age.setText("����:" + user.getUserAge() + "�� ");
		Age.setBounds(38, 225, 100, 22);
		JLabel Location = new JLabel();// ���ڵ�
		Location.setText("���ڵ�:" + user.getUserAddress());
		Location.setBounds(38, 250, 100, 22);
		JLabel fenzu = new JLabel();// ����
		fenzu.setText("��   ��:");
		fenzu.setBounds(170, 60, 100, 23);
		String[] FriendDiv = { "�ҵĺ���" };// ����������
		JComboBox Friend = new JComboBox(FriendDiv);
		Friend.setBounds(220, 60, 150, 23);
		JButton Finish = new JButton("���");// ��ɰ�ť
		Finish.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// ȷ�����
				try {
					ObjectOutputStream objectOutputStream = TCPConnect.objectOutputStream;
					MessagePackge messagePackge = new MessagePackge(22, user.getUserQQ() + "");
					objectOutputStream.writeObject(messagePackge);
					JOptionPane.showMessageDialog(null, "��ӳɹ�", "��ʾ", JOptionPane.WARNING_MESSAGE);
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
		JButton Cancel = new JButton("ȡ��");// ȡ����ť
		Cancel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// ȡ����ӣ��رմ˴���
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

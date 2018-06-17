package com.chinasofti.etc.hiq.view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import com.chinasofti.etc.hiq.po.User;

@SuppressWarnings("serial")
public class panelMenu extends JTabbedPane implements ActionListener {

	private JPanel p1, p2, p3, p4;
	private JToolBar tb2, tb3, tb4;
	private JButton btnProductors, brnData, btnSet;
	// ������������ĺ��Ѽ���Ϣ�б�
	private Map<Integer, Chat> chatList = new HashMap<Integer, Chat>();

	public Map<Integer, Chat> getChatList() {
		return chatList;
	}

	public void setChatList(Map<Integer, Chat> chatList) {
		this.chatList = chatList;
	}

	public panelMenu(Map<String, List<User>> usersMap, final User userMy) {
		this.p1 = new JPanel();
		this.p1.setBackground(null);
//		this.p1.setLayout(new GridLayout(usersMap.size(), 1));
		this.p1.setLayout(new FlowLayout()); // ���ַ�ʽ
		Set<String> keySet = usersMap.keySet();
		Iterator<String> groupIterator = keySet.iterator();
		String groupName;
		DefaultMutableTreeNode groupRoot;
		JTree groupTree;
		List<User> subUsers;
		DefaultMutableTreeNode subType;
		Iterator<User> userIterator;
		User user;
		while(groupIterator.hasNext()) {
			// ��ȡÿ�����������
			groupName = groupIterator.next();
			// ����������ڵ�
			groupRoot = new DefaultMutableTreeNode(String.format("%-65s", groupName));
			// ��ȡÿ��ĳ�Ա
			subUsers = usersMap.get(groupName);
			userIterator = subUsers.iterator();
			// ����ȡ���ĳ�Ա���������
			while (userIterator.hasNext()) {
				user = userIterator.next();
				subType = new DefaultMutableTreeNode(user);
				groupRoot.add(subType);
			}
			groupTree = new JTree(groupRoot);
			groupTree.addMouseListener(new MouseAdapter() {
				// ��Ӧ���οؼ��ϵ��������Ϣ
				@Override
				public void mouseClicked(MouseEvent e) {
					// ��Ӧ���οؼ�����������˫���¼�
					if (e.getClickCount() >= 2
							&& e.getModifiers() == InputEvent.BUTTON1_MASK) {
						// ��ȡ�����οؼ�
						JTree groupRoot = (JTree) e.getSource();
						// ��ȡ����
						DefaultMutableTreeNode subType = (DefaultMutableTreeNode) groupRoot
								.getLastSelectedPathComponent();
						if (subType != null) {
							// ��ȡ���������Ϣ
							Object obj = subType.getUserObject();
							if (obj instanceof User) {// ���ѡ������User������������������ת��
								User userFrom = (User) obj;
								System.out.println("��"
										+ userFrom.getUserNikName() + "���� "
										+ "QQ:" + userFrom.getUserQQ() + "IP:"
										+ userFrom.getUserIP());
								// �жϸ����촰���Ƿ��Ѵ�

								if (chatList.containsKey(userFrom.getUserQQ()) == false) {

									System.out.println(userMy.getUserQQ()
											+ "��QQ" + userFrom.getUserQQ()
											+ "���´���");
//									new TipWindow(null);
									Chat chatDialog = new Chat(userMy, userFrom.getUserQQ(), userFrom.getUserNikName(), userFrom.getUserIP(), chatList);
									chatList.put(userFrom.getUserQQ(),chatDialog);
									System.out.println(chatList.size());
									Set<Integer> set = chatList.keySet();
									Iterator<Integer> iterator = set.iterator();
									while (iterator.hasNext()) {
										System.out.println("chatlist"+ iterator.next());
									}
								}
							}
						}
					}
					if (e.getModifiers() == InputEvent.BUTTON3_MASK) {
						// ��ȡ�����οؼ�
						JTree groupRoot = (JTree) e.getSource();
						// ��ȡ����
						DefaultMutableTreeNode subType = (DefaultMutableTreeNode)groupRoot.getLastSelectedPathComponent();
						// ��ȡ���������Ϣ
						if (subType != null) {
							Object obj = subType.getUserObject();
							if (obj instanceof User) {
								new friendInformation("��������", (User) obj);
							}
						}
					}
				}
			});
			this.p1.add(groupTree);		
		}

		this.p2 = new JPanel();
		this.p2.setBackground(null);
		this.p2.setLayout(new GridLayout(1, 1));
		ImageIcon icon2 = new ImageIcon("images/gif_47_107.gif");
		this.tb2 = new JToolBar();
		this.tb2.setBackground(null);
		for (int i = 0; i < 12; i++) {
			this.btnProductors = new JButton(icon2);
			this.btnProductors.setToolTipText("��Ӧ��" + (i + 1));
			this.btnProductors.addActionListener(this);
			this.tb2.add(btnProductors);
		}
		this.p2.add(this.tb2);

		this.p3 = new JPanel();
		this.p3.setBackground(null);
		this.p3.setLayout(new GridLayout(1, 1));
		ImageIcon icon3 = new ImageIcon("images/gif_47_027.gif");
		this.tb3 = new JToolBar();
		this.tb3.setBackground(null);
		for (int i = 0; i < 8; i++) {
			this.brnData = new JButton(icon3);
			this.brnData.setToolTipText("���ݱ�" + (i + 1));
			this.brnData.addActionListener(this);
			this.tb3.add(brnData);
		}
		this.p3.add(this.tb3);

		this.p4 = new JPanel();
		this.p4.setBackground(null);
		this.p4.setLayout(new GridLayout(1, 1));
		ImageIcon icon4 = new ImageIcon("images/gif_47_041.gif");
		this.tb4 = new JToolBar();
		this.tb4.setBackground(null);
		for (int i = 0; i < 10; i++) {
			this.btnSet = new JButton(icon4);
			this.btnSet.setToolTipText("����" + (i + 1));
			this.btnSet.addActionListener(this);
			this.tb4.add(btnSet);
		}
		this.p4.add(this.tb4);

		this.addTab("", new ImageIcon("images/tab_001.gif"), this.p1, "��ϵ��");
		this.addTab("", new ImageIcon("images/tab_002.gif"), this.p2, "Ⱥ");
		this.addTab("", new ImageIcon("images/tab_003.gif"), this.p3,"�����ϵ��");
		this.addTab("", new ImageIcon("images/tab_004.gif"), this.p4,"΢��");
		this.setBackground(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		if (e.getSource() instanceof JButton) {
//			JButton btn = (JButton) e.getSource();
//			if (btn.getToolTipText().substring(0,
//					btn.getToolTipText().length() - 1).equals("�û�")) {
//				int no = Integer.parseInt(btn.getToolTipText().substring(
//						btn.getToolTipText().length() - 1,
//						btn.getToolTipText().length()));
//				switch (no) {
//				case 1:
//					System.out.println("ѡ�����û�1");
//					break;
//				default:
//					break;
//				}
//			}
//			if (btn.getToolTipText().substring(0,
//					btn.getToolTipText().length() - 1).equals("��Ӧ��")) {
//				int no = Integer.parseInt(btn.getToolTipText().substring(
//						btn.getToolTipText().length() - 1,
//						btn.getToolTipText().length()));
//				switch (no) {
//				case 1:
//					System.out.println("ѡ���˹�Ӧ��1");
//					break;
//				default:
//					break;
//				}
//			}
//			if (btn.getToolTipText().substring(0,
//					btn.getToolTipText().length() - 1).equals("���ݱ�")) {
//				int no = Integer.parseInt(btn.getToolTipText().substring(
//						btn.getToolTipText().length() - 1,
//						btn.getToolTipText().length()));
//				switch (no) {
//				case 1:
//					System.out.println("ѡ�������ݱ�1");
//					break;
//				default:
//					break;
//				}
//			}
//			if (btn.getToolTipText().substring(0,
//					btn.getToolTipText().length() - 1).equals("����")) {
//				int no = Integer.parseInt(btn.getToolTipText().substring(
//						btn.getToolTipText().length() - 1,
//						btn.getToolTipText().length()));
//				switch (no) {
//				case 1:
//					System.out.println("ѡ��������1");
//					break;
//				default:
//					break;
//				}
//			}
//		}
//	}	
}
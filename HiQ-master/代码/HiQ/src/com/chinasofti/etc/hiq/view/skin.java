package com.chinasofti.etc.hiq.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


import javax.swing.JPanel;

public class skin extends JFrame implements MouseListener{
	private static final long serialVersionUID = -266077703729622905L;
	private int x = 0;
	private int y = 0;
	private int width = 560;
	private int height = 380;
	private Container cp;
	private ImageIcon bgImage,Image1,Image2,Image3,Image4,Image5,Image6,Image7,Image8;
	private JLabel bg,h1,h2,h3,h4,h5,h6,h7,h8,name;
	private JButton change;
	

	public skin() throws HeadlessException {
		super();
		
		init();
	}
	
	public skin(GraphicsConfiguration arg0) {
		super(arg0);
		
		init();
	}
	
	public skin(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		
		init();
	}

	public skin(String arg0) throws HeadlessException {
		super(arg0);
		
		init();
	}

	public void init(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//取消最大化的设置
		this.setLocationRelativeTo(this);
		//居中显示
		this.setResizable(false);
		
		
		//背景
		this.bgImage = new ImageIcon("images/bg.png");
		this.bg = new JLabel(this.bgImage);
		getLayeredPane().add(this.bg,new Integer(Integer.MIN_VALUE));
		this.bg.setBounds(0, 0, this.bgImage.getIconWidth(), 500);
		((JPanel)getContentPane()).setOpaque(false);
		this.cp = getContentPane();
		this.cp.setLayout(null);
		
		name =new JLabel("请选择喜欢的皮肤！");
		Font font = new Font("微软雅黑",0,15);
		this.name.setFont(font);
		
		this.cp.add(this.name);
		this.name.setBounds(10, 10, 200, 15);
		
		this.Image1 = new ImageIcon("images/co110HZ20400-7.jpg");
		this.h1 = new JLabel(this.Image1);
		this.h1.setToolTipText("images/co110HZ20400-7.jpg");
		this.h1.addMouseListener(this);
		
		this.cp.add(this.h1);
		this.h1.setBounds(10, 40, 125, 125);
		
		this.Image2 = new ImageIcon("images/co110HZ20400-7.jpg");
		this.h2 = new JLabel(this.Image2);
		this.h2.setToolTipText("images/co110HZ20400-7.jpg");
		this.h2.addMouseListener(this);
		
		this.cp.add(this.h2);
		this.h2.setBounds(140, 40, 125, 125);
		
		this.Image3 = new ImageIcon("images/co110HZ20400-7.jpg");
		this.h3 = new JLabel(this.Image3);
		this.h3.setToolTipText("images/co110HZ20400-7.jpg");
		this.h3.addMouseListener(this);
		
		this.cp.add(this.h3);
		this.h3.setBounds(267, 40, 125, 125);
		
		this.Image4 = new ImageIcon("images/co110HZ20400-7.jpg");
		this.h4 = new JLabel(this.Image4);
		this.h4.setToolTipText("images/co110HZ20400-7.jpg");
		this.h4.addMouseListener(this);
		
		this.cp.add(this.h4);
		this.h4.setBounds(394, 40, 125, 125);
		
		
		this.Image5 = new ImageIcon("images/co110HZ20400-7.jpg");
		this.h5 = new JLabel(this.Image5);
		this.h5.setToolTipText("images/co110HZ20400-7.jpg");
		this.h5.addMouseListener(this);
		
		this.cp.add(this.h5);
		this.h5.setBounds(10, 180, 125, 125);
		
		this.Image6 = new ImageIcon("images/co110HZ20400-7.jpg");
		this.h6 = new JLabel(this.Image6);
		this.h6.setToolTipText("images/co110HZ20400-7.jpg");
		this.h6.addMouseListener(this);
		
		this.cp.add(this.h6);
		this.h6.setBounds(140, 180, 125, 125);
		
		this.Image7 = new ImageIcon("images/co110HZ20400-7.jpg");
		this.h7 = new JLabel(this.Image7);
		this.h7.setToolTipText("images/co110HZ20400-7.jpg");
		this.h7.addMouseListener(this);
		
		this.cp.add(this.h7);
		this.h7.setBounds(267, 180, 125, 125);
		
		this.Image8 = new ImageIcon("images/co110HZ20400-7.jpg");
		this.h8 = new JLabel(this.Image8);
		this.h8.setToolTipText("images/co110HZ20400-7.jpg");
		this.h8.addMouseListener(this);
		
		this.cp.add(this.h8);
		this.h8.setBounds(394, 180, 125, 125);
		
		change = new JButton("确认");
		Font font1 = new Font("微软雅黑",Font.BOLD,10);
		change.setFont(font1);
		this.change.setBounds(400, 330, 80, 20);
		this.cp.add(change);
		
		
		
		// 注意这里，将内容面板设为透明。这样LayeredPane面板中的背景才能显示出来。
		//((JPanel) this.cp).setOpaque(false);
		// 获取当前屏幕的大小
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		x = (d.width - width) / 2;
		y = (d.height - height) / 2;
		// 设置登陆窗口的位置和大小
		setBounds(x, y, width, height);
		
		// 显示窗口
		setVisible(true);
	}
	public static void main(String[] args) {
		new skin();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		JLabel skin = null;
		String skinName = null;
		if (obj instanceof JLabel) {
			skin = (JLabel)obj;
			skinName  =skin.getToolTipText();
			System.out.println(skinName);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

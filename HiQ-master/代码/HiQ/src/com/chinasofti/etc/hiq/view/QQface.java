package com.chinasofti.etc.hiq.view;


import java.awt.Container;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;


public class QQface extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6128646569764409043L;
	private int x = 0;
	private int y = 0;
	private int width = 460;
	private int height = 300;
	private Container cp;
	private JMenuBar menuBar;
	private JMenu menu,menu2;
	private JMenuItem item1,item2,item3;
	private JButton choose;
	private ImageIcon image1,image2,image3,image4,image5,image6,image7,image8,image9,image10,image11,image12,image13,image14,image15,image16;
	private JLabel lable1,lable2,lable3,lable4,lable5,lable6,lable7,lable8,lable9,lable10,lable11,lable12,lable13,lable14,lable15,lable16;
	
	
	
	public QQface() throws HeadlessException {
		super();
		
		init();
	}
	
	public QQface(GraphicsConfiguration arg0) {
		super(arg0);
		
		init();
	}
	
	public QQface(String arg0, GraphicsConfiguration arg1) {
		super(arg0, arg1);
		
		init();
	}

	public QQface(String arg0) throws HeadlessException {
		super(arg0);
		
		init();
	}

	public void init(){
		

		// 设置窗口关闭方式
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//居中显示
		this.setLocationRelativeTo(this);
		this.setResizable(false);
		

		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		// 设置布局方式
		this.cp = getContentPane();
		this.cp.setLayout(null);
		
		//菜单栏
		menuBar = new JMenuBar();
		menu = new JMenu("添加");
		menu2 = new JMenu("设置");
		item1 = new JMenuItem("使用表情快捷键");
		item2 = new JMenuItem("导入导出表情包");
		item3 = new JMenuItem("反馈意见");
		menu2.add(item1);
		menu2.add(item2);
		menu2.add(item3);
		menuBar.add(menu);
		menuBar.add(menu2);
		setJMenuBar(menuBar);
		menuBar.setBounds(0,0,100, 30);
	
		//表情
		image1 = new ImageIcon("images/QQface/e112.gif");
		lable1 = new JLabel(image1);
		lable1.setBounds(10, 0, 30, 30);
		this.cp.add(lable1);
		
		
		image2 = new ImageIcon("images/QQface/e100.gif");
		lable2 = new JLabel(image2);
		lable2.setBounds(40, 0, 30, 30);
		this.cp.add(lable2);
		
		image3 = new ImageIcon("images/QQface/e101.gif");
		lable3 = new JLabel(image3);
		lable3.setBounds(70, 0, 30, 30);
		this.cp.add(lable3);
		
		image4 = new ImageIcon("images/QQface/e102.gif");
		lable4 = new JLabel(image4);
		lable4.setBounds(100, 0, 30, 30);
		this.cp.add(lable4);
		
		image5 = new ImageIcon("images/QQface/e103.gif");
		lable5 = new JLabel(image5);
		lable5.setBounds(130, 0, 30, 30);
		this.cp.add(lable5);
		
		image6 = new ImageIcon("images/QQface/e104.gif");
		lable6 = new JLabel(image6);
		lable6.setBounds(160, 0, 30, 30);
		this.cp.add(lable6);
		
		image7 = new ImageIcon("images/QQface/e105.gif");
		lable7 = new JLabel(image7);
		lable7.setBounds(190, 0, 30, 30);
		this.cp.add(lable7);
		
		image8 = new ImageIcon("images/QQface/e106.gif");
		lable8 = new JLabel(image8);
		lable8.setBounds(220, 0, 30, 30);
		this.cp.add(lable8);
		
		image9 = new ImageIcon("images/QQface/e107.gif");
		lable9 = new JLabel(image9);
		lable9.setBounds(250, 0, 30, 30);
		this.cp.add(lable9);
		
		image10 = new ImageIcon("images/QQface/e108.gif");
		lable10 = new JLabel(image10);
		lable10.setBounds(280, 0, 30, 30);
		this.cp.add(lable10);
		
		image11 = new ImageIcon("images/QQface/e109.gif");
		lable11 = new JLabel(image11);
		lable11.setBounds(310, 0, 30, 30);
		this.cp.add(lable11);
		
		image12 = new ImageIcon("images/QQface/e110.gif");
		lable12 = new JLabel(image12);
		lable12.setBounds(340, 0, 30, 30);
		this.cp.add(lable12);
		
		image13 = new ImageIcon("images/QQface/e111.gif");
		lable13 = new JLabel(image13);
		lable13.setBounds(370, 0, 30, 30);
		this.cp.add(lable13);
		
		image14 = new ImageIcon("images/QQface/e112.gif");
		lable14 = new JLabel(image14);
		lable14.setBounds(400, 0, 30, 30);
		this.cp.add(lable14);
		
		image15 = new ImageIcon("images/QQface/e113.gif");
		lable15 = new JLabel(image15);
		lable15.setBounds(10, 30, 30, 30);
		this.cp.add(lable15);
		
		image16 = new ImageIcon("images/QQface/e114.gif");
		lable16 = new JLabel(image16);
		lable16.setBounds(40, 30, 30, 30);
		this.cp.add(lable16);


	
		
		//确定按钮
		choose = new JButton("确定");
		choose.setBounds(370, 210, 60, 30);
		this.cp.add(choose);
		
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
	

}

class MouseItem implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		//ImageIcon image = new ImageIcon();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}
	
}

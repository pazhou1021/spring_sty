package com.chinasofti.etc.hiq.view;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class TipWindow extends JFrame implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5901652564033166278L;
	
	private int x = 0;
	private int y = 0;
	private int width = 200;
	private int height =150;
	private JLabel textJLabel = null;

	public TipWindow(String msg) throws HeadlessException {
		super();
		System.out.println("begin");
		// TODO Auto-generated constructor stub
		init(msg);
	}

	public TipWindow(String title, String msg) throws HeadlessException {
		super(title);
		System.out.println("begin");
		// TODO Auto-generated constructor stub
		init(msg);
	}
	
	protected void init(String msg) {
		System.out.println("begin");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// 获取当前屏幕的大小
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		x = d.width - width;
		y = d.height;
		textJLabel = new  JLabel(msg);
		getContentPane().add(textJLabel);
		new Thread(this).start();
		setVisible(true);
	}

	@Override
	public void run() {
		// 动态显示提醒框
		try {
			for (int i = 0; i < height; i++) {
				setBounds(x, y - i, width, height);
				Thread.sleep(1);
			}
			Thread.sleep(3000);
			dispose();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new TipWindow("", "");
	}
}

package com.chinasofti.etc.hiq.view;

import java.awt.Dimension;
import java.awt.Toolkit;

public class ShakeWindow implements Runnable{
	private Chat chat;

	public ShakeWindow(Chat chat) {
		super();
		this.chat = chat;
		new Thread(this).run();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// 获取当前屏幕的大小
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 518;
		int height = 513;
		int p = 20;
		int x = (d.width - width) / 2;
		int y = (d.height - height) / 2;
		
		try {
			for (int i = 0; i < 8; i++) {
				chat.setBounds(x - p, y - p, width, height);
				Thread.sleep(50);
				chat.setBounds(x + p, y - p, width, height);
				Thread.sleep(50);
				chat.setBounds(x + p, y + p, width, height);
				Thread.sleep(50);
				chat.setBounds(x - p, y - p, width, height);
				Thread.sleep(50);
				chat.setBounds(x, y, width, height);
				Thread.sleep(50);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}

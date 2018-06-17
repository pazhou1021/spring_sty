package com.chinasofti.etc.hiq.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class ttt extends Thread {
	boolean flag = true;
	int cnt = 0;
	Timer time;
	class T implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			time.restart();
			System.out.println("LLLLL");
			System.out.println(">>>" + cnt);
			cnt++;
			if (cnt == 10){
				time.stop();
			}
			
		}
		
	}
	public void run(){
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		time = new Timer(500, new T());
		time.start();
		
		//time.restart();
		while (flag){
			try {
				sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			time.restart();
		}
	}
}

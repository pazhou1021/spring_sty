package com.chinasofti.etc.hiq.view;

import java.io.IOException;
import java.io.ObjectOutput;

import com.chinasofti.etc.hiq.dao.impl.TCPConnect;
import com.chinasofti.etc.hiq.po.MessagePackge;

public class HeartbeatPackage implements Runnable {
	private ObjectOutput objectOutput = null;

	public HeartbeatPackage() {
		super();
		// 得到与服务器的TCP连接
		objectOutput = TCPConnect.objectOutputStream;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		MessagePackge messagePackge = null;
		while (true) {
			try {
				// 发送心跳包，5分钟一个
				messagePackge = new MessagePackge(1, null);
				objectOutput.writeObject(messagePackge);
				Thread.sleep(5 * 60 * 1000);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}

}

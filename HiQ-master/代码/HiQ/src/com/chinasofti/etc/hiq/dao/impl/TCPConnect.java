package com.chinasofti.etc.hiq.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Properties;

public class TCPConnect {
	private int ServerPort = 0;
	private String ServerIP = null;
	private Socket mySocket = null;
	private InputStream in = null;
	private OutputStream out = null;
	
	public static ObjectInputStream objectInputStream = null;
	public static ObjectOutputStream objectOutputStream = null;
	
		
	public int getServerPort() {
		return ServerPort;
	}

	public void setServerPort(int serverPort) {
		ServerPort = serverPort;
	}

	public String getServerIP() {
		return ServerIP;
	}

	public void setServerIP(String serverIP) {
		ServerIP = serverIP;
	}

	public Socket getMySocket() {
		return mySocket;
	}

	public void setMySocket(Socket mySocket) {
		this.mySocket = mySocket;
	}

	public InputStream getIn() {
		return in;
	}

	public void setIn(InputStream in) {
		this.in = in;
	}

	public OutputStream getOut() {
		return out;
	}

	public void setOut(OutputStream out) {
		this.out = out;
	}

	public ObjectInputStream getObjectInputStream() {
		return objectInputStream;
	}

	@SuppressWarnings("static-access")
	public void setObjectInputStream(ObjectInputStream objectInputStream) {
		this.objectInputStream = objectInputStream;
	}

	public ObjectOutputStream getObjectOutputStream() {
		return objectOutputStream;
	}

	@SuppressWarnings("static-access")
	public void setObjectOutputStream(ObjectOutputStream objectOutputStream) {
		this.objectOutputStream = objectOutputStream;
	}

	public TCPConnect() throws FileNotFoundException, IOException {
		super();
		// ��ʼ�����������TCP����
		init();
	}

	protected void init() throws FileNotFoundException, IOException {
		// ��ȡ�����ļ���Ϣ
		File file = new File("HiQConfig.ini");
		Properties properties = new Properties();
		properties.load(new FileInputStream(file));
		String strPort = properties.getProperty("ServerPort");
		ServerPort = Integer.parseInt(strPort);
		ServerIP = properties.getProperty("ServerIP");
		if (ServerIP == null && ServerPort == 0) {
			// ���δ�ܳɹ���÷�������ַ�Ͷ˿����׳�һ���쳣
			throw new IOException();
		}
		// ����TCP����
		mySocket = new Socket(ServerIP, ServerPort);
		in = mySocket.getInputStream();
		out = mySocket.getOutputStream();
	}
}

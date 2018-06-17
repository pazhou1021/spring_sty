package com.chinasofti.etc.hiq.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class SendFile implements Runnable{
	private File file;
	private ServerSocket serverSocket;
	private Socket socket;
	private InputStream in;
	private OutputStream out;
	private byte[] bs = new byte[1024];
	private int len = 0;
	
	public SendFile(File file) {
		super();
		this.file = file;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			serverSocket = new ServerSocket(8000);
			socket = serverSocket.accept();
			in = socket.getInputStream();
			len = in.read(bs);
			String result = new String(bs, 0, len);
			if (result.equals("1")) {
				FileInputStream fileInputStream = new FileInputStream(this.file);
				out = socket.getOutputStream();
				while ((len = fileInputStream.read(bs)) != -1) {
					out.write(bs, 0, len);
				}
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
				if (socket != null) {
					socket.close();
				}
				if (serverSocket != null) {
					serverSocket.close();
				}
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			} else if (result.equals("0")) {
				if (in != null) {
					in.close();
				}
				if (socket != null) {
					socket.close();
				}
				if (serverSocket != null) {
					serverSocket.close();
				}
			}
			System.out.println("re=" + result);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

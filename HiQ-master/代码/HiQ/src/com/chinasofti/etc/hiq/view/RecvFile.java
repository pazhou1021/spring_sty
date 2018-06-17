package com.chinasofti.etc.hiq.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class RecvFile implements Runnable {
	private Socket socket;
	private InputStream in;
	private OutputStream out;
	private byte[] bs = new byte[1024];
	private int len = 0;
	private String fileName;

	public RecvFile(int returnVal, String IP, String fileName) {
		super();
		// TODO Auto-generated constructor stub
		this.fileName = fileName;
		try {
			socket = new Socket(IP, 8000);
			out = socket.getOutputStream();
			if (returnVal == JOptionPane.YES_OPTION) {
				new Thread(this).start();
			} else {
				out.write("0".getBytes());
				if (out != null) {
					out.close();
				}
				if (socket != null) {
					socket.close();
				}
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int returnVal = fileChooser.showSaveDialog(null);	
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				out.write("1".getBytes());
				in = socket.getInputStream();
				File file = new File(fileChooser.getSelectedFile()+ "\\" + fileName);
				System.out.println(file);
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				while ((len = in.read(bs)) != -1) {
					fileOutputStream.write(bs, 0, len);
				}
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
				if (socket != null) {
					socket.close();
				}
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
			} else {
				out.write("0".getBytes());
				if (out != null) {
					out.close();
				}
				if (socket != null) {
					socket.close();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

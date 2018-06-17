package com.chinasofti.etc.hiq.view;

public class StartServerThread extends Thread {
	private Server server;
	
	public Server getServer() {
		return server;
	}

	public void run(){
		server = new Server();
	}

}

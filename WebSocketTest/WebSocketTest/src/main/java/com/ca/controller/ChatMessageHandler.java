package com.ca.controller;


import java.io.IOException;  
import java.util.ArrayList;  
import org.apache.log4j.Logger;  
import org.springframework.web.socket.CloseStatus;  
import org.springframework.web.socket.TextMessage;  
import org.springframework.web.socket.WebSocketSession;  
import org.springframework.web.socket.handler.TextWebSocketHandler;  

public class ChatMessageHandler extends TextWebSocketHandler {  
	  
    private static final ArrayList<WebSocketSession> users;// ���������������⣬�����Map���洢��key��userid  
    private static Logger logger = Logger.getLogger(ChatMessageHandler.class);  
  
    static {  
        users = new ArrayList<WebSocketSession>();  
    }  
  
    /** 
     * ���ӳɹ�ʱ�򣬻ᴥ��UI��onopen���� 
     */  
    @Override  
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {  
        System.out.println("connect to the websocket success......");  
        users.add(session);  
        // ����ʵ���Լ�ҵ�񣬱��磬���û���¼�󣬻��������Ϣ���͸��û�  
        // TextMessage returnMessage = new TextMessage("�㽫�յ�������");  
        // session.sendMessage(returnMessage);  
    }  
  
    /** 
     * ��UI����js����websocket.send()ʱ�򣬻���ø÷��� 
     */  
    @Override  
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {  
        sendMessageToUsers(message);  
        //super.handleTextMessage(session, message);  
    }  
  
    /** 
     * ��ĳ���û�������Ϣ 
     * 
     * @param userName 
     * @param message 
     */  
    public void sendMessageToUser(String userName, TextMessage message) {  
        for (WebSocketSession user : users) {  
            if (user.getAttributes().get(Constants.WEBSOCKET_USERNAME).equals(userName)) {  
                try {  
                    if (user.isOpen()) {  
                        user.sendMessage(message);  
                    }  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
                break;  
            }  
        }  
    }  
  
    /** 
     * �����������û�������Ϣ 
     * 
     * @param message 
     */  
    public void sendMessageToUsers(TextMessage message) {  
        for (WebSocketSession user : users) {  
            try {  
                if (user.isOpen()) {  
                    user.sendMessage(message);  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    @Override  
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {  
        if (session.isOpen()) {  
            session.close();  
        }  
        logger.debug("websocket connection closed......");  
        users.remove(session);  
    }  
  
    @Override  
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {  
        logger.debug("websocket connection closed......");  
        users.remove(session);  
    }  
  
    @Override  
    public boolean supportsPartialMessages() {  
        return false;  
    }  
  
}  

package com.ca.socket.test;

import java.io.IOException;  
import java.util.ArrayList;
import java.util.List;
import java.util.Map;  
import java.util.concurrent.ConcurrentHashMap;  

import javax.websocket.*;  
import javax.websocket.server.PathParam;  
import javax.websocket.server.ServerEndpoint;

import com.ca.entity.Message;
import com.ca.entity.UserInfo;

import net.sf.json.JSONObject;

@ServerEndpoint("/websocket/{username}") 
public class Websocket {
	private static int onlineCount = 0;  
    private static Map<UserInfo, Websocket> clients = new ConcurrentHashMap<UserInfo, Websocket>();  
    private Session session;  
    private UserInfo user; 
    private static int id = 0;
      
    @OnOpen  
    public void onOpen(@PathParam("username") String username, Session session) throws IOException {  
  
    	user=new UserInfo(id+1,username);
    	List<Message> list=new ArrayList<Message>();
    	user.setMegs(list);
    	
        this.session = session;  
          
        addOnlineCount();  
        clients.put(user, this);  
        System.out.println("已连接");  
    }  
  
    @OnClose  
    public void onClose() throws IOException {  
        clients.remove(user);  
        subOnlineCount();  
    }  
  
    @OnMessage  
    public void onMessage(String message) throws IOException {  
  
        JSONObject jsonTo = JSONObject.fromObject(message);  
          
        if (!jsonTo.get("To").equals("All")){
        	Message mes=new Message();
        	mes.setFromName(user.getName());
        	mes.setMessageText(jsonTo.get("massege").toString());
        	mes.setToName(jsonTo.get("To").toString());
        	user.getMegs().add(mes);
            sendMessageTo(jsonTo.get("massege").toString(), jsonTo.get("To").toString());  
        }else{  
            sendMessageAll("给所有人");  
        }  
    }  
  
    @OnError  
    public void onError(Session session, Throwable error) {  
        error.printStackTrace();  
    }  
  
    public void sendMessageTo(String message, String To) throws IOException {  
        for (Websocket item : clients.values()) { 
            if (item.user.getName().equals(To) )  
                item.session.getAsyncRemote().sendText(message+","+user.getName());  
        }  
    }  
      
    public void sendMessageAll(String message) throws IOException {  
        for (Websocket item : clients.values()) {  
            item.session.getAsyncRemote().sendText(message);  
        }  
    }  
      
      
  
    public static synchronized int getOnlineCount() {  
        return onlineCount;  
    }  
  
    public static synchronized void addOnlineCount() {  
    	Websocket.onlineCount++;  
    }  
  
    public static synchronized void subOnlineCount() {  
    	Websocket.onlineCount--;  
    }  
  
    public static synchronized Map<UserInfo, Websocket> getClients() {  
        return clients;  
    }
}

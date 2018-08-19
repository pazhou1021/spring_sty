package com.ca.controller;


import java.util.Map;  
import org.apache.shiro.SecurityUtils;  
import org.springframework.http.server.ServerHttpRequest;  
import org.springframework.http.server.ServerHttpResponse;  
import org.springframework.web.socket.WebSocketHandler;  
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;  

public class ChatHandshakeInterceptor extends HttpSessionHandshakeInterceptor {  
	  
    @Override  
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,  
            Map<String, Object> attributes) throws Exception {  
        System.out.println("Before Handshake");  
        /* 
         * if (request instanceof ServletServerHttpRequest) { 
         * ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) 
         * request; HttpSession session = 
         * servletRequest.getServletRequest().getSession(false); if (session != 
         * null) { //ʹ��userName����WebSocketHandler���Ա㶨������Ϣ String userName = 
         * (String) session.getAttribute(Constants.SESSION_USERNAME); if 
         * (userName==null) { userName="default-system"; } 
         * attributes.put(Constants.WEBSOCKET_USERNAME,userName); 
         *  
         * } } 
         */  
  
        //ʹ��userName����WebSocketHandler���Ա㶨������Ϣ(ʹ��shiro��ȡsession,����ʹ������ķ�ʽ)  
        String userName = (String) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USERNAME);  
        if (userName == null) {  
            userName = "default-system";  
        }  
        attributes.put(Constants.WEBSOCKET_USERNAME, userName);  
  
        return super.beforeHandshake(request, response, wsHandler, attributes);  
    }  
  
    @Override  
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,  
            Exception ex) {  
        System.out.println("After Handshake");  
        super.afterHandshake(request, response, wsHandler, ex);  
    }  
  
}  

package com.chinasofti.etc.hiq.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chinasofti.etc.hiq.biz.UserBiz;
import com.chinasofti.etc.hiq.biz.bizimpl.UserBizImpl;
import com.chinasofti.etc.hiq.po.User;

public class RegisterServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RegisterServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		UserBiz userBiz = new UserBizImpl();
		
		int userQQ = userBiz.findMaxUserQQ() + 1;
		//获得注册用户信息
		String strUserNikName = request.getParameter("userNikName");
		String userNikName = new String(strUserNikName.getBytes("ISO-8859-1"),"utf-8");
		
		String userPassword = request.getParameter("userPassword");
		
		
		String strUserSex = request.getParameter("userSex");
		int userSex;
		if (strUserSex.equals("1")){
			userSex = Integer.parseInt(strUserSex.trim());
		}
		else{
			userSex = 0;
		}
		
		String strUserAddress = request.getParameter("userAddress");
		String userAddress = new String(strUserAddress.getBytes("ISO-8859-1"),"utf-8");
		
		String strUserAge = request.getParameter("userAge");
		int userAge = Integer.parseInt(strUserAge);
		
		String strUserBirthday = request.getParameter("userBirthday");
		String userBirthday = new String(strUserBirthday.getBytes("ISO-8859-1"),"utf-8");
		
		String strUserEmail = request.getParameter("userEmail");
		String userEmail = new String(strUserEmail.getBytes("ISO-8859-1"),"utf-8");
		String strUserSpeak = request.getParameter("userSpeak");
		String userSpeak = new String(strUserSpeak.getBytes("ISO-8859-1"),"utf-8");
		
		//获得注册日期
		java.util.Date date = new java.util.Date();
		java.sql.Date userRgDate = new java.sql.Date(date.getTime());
		
		
		System.out.println("用户：" + userNikName + " " + userPassword + "  " + userAddress);
		
		User user = new User();
		
		user.setUserQQ(userQQ);
		user.setUserPassword(userPassword);
		user.setUserNikName(userNikName); 
		user.setUserSex(userSex);
		user.setUserAddress(userAddress);
		user.setUserAge(userAge);
		user.setUserBirthday(userBirthday);
		user.setUserEmail(userEmail);
		user.setUserRgDate(userRgDate);
		user.setUserSpeak(userSpeak);
		
		
		//将申请到的QQ号放入session
		HttpSession session = request.getSession();
		session.setAttribute("userQQ", userQQ);
		//将申请的用户加入数据库
		userBiz.insertUser(user);
		response.sendRedirect("showQQ.jsp");
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}

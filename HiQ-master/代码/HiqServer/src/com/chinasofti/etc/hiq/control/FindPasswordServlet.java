package com.chinasofti.etc.hiq.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.chinasofti.etc.hiq.biz.UserBiz;
import com.chinasofti.etc.hiq.biz.bizimpl.UserBizImpl;
import com.chinasofti.etc.hiq.po.User;

public class FindPasswordServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public FindPasswordServlet() {
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

		//HIQ号
		 String strUserQQ = request.getParameter("userQQ");
		 int userQQ = Integer.parseInt(strUserQQ.trim());
		 //用户生日
		 String strUserBirthday = request.getParameter("userBirthday");
		 String userBirthday = new String(strUserBirthday.getBytes("ISO-8859-1"),"utf-8");
			
		 //用户Email
		String strUserEmail = request.getParameter("userEmail");
		String userEmail = new String(strUserEmail.getBytes("ISO-8859-1"),"utf-8");
		//用户性别
		 String strUserSex = request.getParameter("userSex");
			int userSex;
			if (strUserSex.equals("1")){
				userSex = Integer.parseInt(strUserSex.trim());
			}
			else{
				userSex = 0;
			}
		//根据所填HiQ号查找出该用户
		 UserBiz userBiz = new UserBizImpl();
		 User user = userBiz.findUserByQQ(userQQ);
		 //存放最后查找结果
		 String resultMessage = null;
		 
		 if (user != null){
			 //如果所填信息全部正确
			 if (user.getUserBirthday().equals(userBirthday) && user.getUserEmail().equals(userEmail)
					 && user.getUserSex() == userSex){
				 
				 resultMessage =  "您的HiQ号为" + user.getUserQQ() + "请按妥善保管好您的HiQ以免再次丢失，你行";
			 }
			 else{
				 resultMessage = "该用户存在 但所填信息不符，请确认后重新填写";
			 }
		 }
		 else{
			 resultMessage = "该用户不存在，请确认后重新填写";
		 }
		 //放入session
		 HttpSession session = request.getSession();
		 session.setAttribute("resultMessage", resultMessage);
		 System.out.println("*********** " + resultMessage);
		 response.sendRedirect("findResult.jsp");
		 
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

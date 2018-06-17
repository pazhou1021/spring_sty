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

		//HIQ��
		 String strUserQQ = request.getParameter("userQQ");
		 int userQQ = Integer.parseInt(strUserQQ.trim());
		 //�û�����
		 String strUserBirthday = request.getParameter("userBirthday");
		 String userBirthday = new String(strUserBirthday.getBytes("ISO-8859-1"),"utf-8");
			
		 //�û�Email
		String strUserEmail = request.getParameter("userEmail");
		String userEmail = new String(strUserEmail.getBytes("ISO-8859-1"),"utf-8");
		//�û��Ա�
		 String strUserSex = request.getParameter("userSex");
			int userSex;
			if (strUserSex.equals("1")){
				userSex = Integer.parseInt(strUserSex.trim());
			}
			else{
				userSex = 0;
			}
		//��������HiQ�Ų��ҳ����û�
		 UserBiz userBiz = new UserBizImpl();
		 User user = userBiz.findUserByQQ(userQQ);
		 //��������ҽ��
		 String resultMessage = null;
		 
		 if (user != null){
			 //���������Ϣȫ����ȷ
			 if (user.getUserBirthday().equals(userBirthday) && user.getUserEmail().equals(userEmail)
					 && user.getUserSex() == userSex){
				 
				 resultMessage =  "����HiQ��Ϊ" + user.getUserQQ() + "�밴���Ʊ��ܺ�����HiQ�����ٴζ�ʧ������";
			 }
			 else{
				 resultMessage = "���û����� ��������Ϣ��������ȷ�Ϻ�������д";
			 }
		 }
		 else{
			 resultMessage = "���û������ڣ���ȷ�Ϻ�������д";
		 }
		 //����session
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

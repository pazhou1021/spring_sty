package com.chinasofti.etc.hiq.dao.orcalimpl;

import java.sql.Connection;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.chinasofti.etc.hiq.dao.BaseDao;



public class BaseDaoImpl implements BaseDao{
	protected Connection con = null;
	protected ResultSet rs = null;
	protected PreparedStatement pstmt = null;
	protected int result = 0;
	
	static {
		try {
			//System.out.println(">>>>>>>>");
			Class.forName("oracle.jdbc.OracleDriver"); 
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getConn () {
		
		try {
			System.out.println(">>>>>>>>>");
			con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl","scott","tiger");
			System.out.println("************");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeAll() {
		try {
			if (pstmt != null && pstmt.isClosed()) {
				pstmt.close();
			}
			if (rs != null && rs.isClosed()) {
				rs.close();
			}
			if (con != null && con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doQuery (String sql, Object...params ) {
		try {
			pstmt = con.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					pstmt.setObject(i+1, params[i]);
				}
			}
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doOperate(String sql, Object...params) {
		try {
			pstmt = con.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					pstmt.setObject(i+1, params[i]);
				}
			}
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean existTable(String tableName){
		DatabaseMetaData meta;
		//DatabaseMetaData meta;
		try {
			meta = (DatabaseMetaData) con.getMetaData();
			ResultSet rs = meta.getTables(null, null, tableName, null);
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void doInit() {
		// TODO Auto-generated method stub
		// 初始化表Users
		getConn();
		String sql = null;
		if (!existTable("Users")) {
			// 初始化表Users
			sql = "create table Users(userId int primary key identity(1,1),userQQ int, userPassword varchar(50), userImage varchar(20), userNikName varchar(20), userSex int,userAddress varchar(50),userAge int,userBirthday varchar(50),uesrEmail varchar(50),userRgDate Date,userSpeak varchar(100))";
			doOperate(sql);
			sql = "insert into Users values(123456, '1', '123456.png', '郭宝星',1,'山东德州',18,'1990-11-14','gbx@163.com','1999-11-2','我相信蜗行，哈哈')";
			doOperate(sql);
			sql = "insert into Users values(123457, '1', '123457.png', '郑闯',1,'山东枣庄',25,'1990-11-14','zc@163.com','1999-11-2','有志者事竟成')";
			doOperate(sql);
			sql = "insert into Users values(123458, '1', '123458.png', '朱庆伟',1,'黑龙江齐齐哈尔',29,'1990-11-19','zqw@163.com','1999-11-2','你是我的玫瑰你是我的花，你是我的爱人是我的牵挂')";
			doOperate(sql);
			sql = "insert into Users values(123459, '1', '123459.png', '袁佑',1,'山东青岛',29,'1990-11-19','yy@163.com','1999-11-2','你是我的玫瑰你是我的花，你是我的爱人是我的牵挂')";
			doOperate(sql);
			sql = "insert into Users values(123460, '1', '123460.png', '徐利红',0,'山东聊城',29,'1990-11-19','xlh@163.com','1999-11-2','勇勇，你行')";
			doOperate(sql);
			System.out.println("====初始化表Users成功===");
		}else {
			System.out.println("*******Users表已存在********");
		}
		//初始化FriendsRs
		
		if (!existTable("FriendsRss")){
			sql = "create table FriendsRss(friendsRsId int primary key identity(1,1),userQQ int,friendId int,groupName varchar(50))";
			doOperate(sql);
			//123456    1
			sql = "insert into FriendsRss values(123456,2,'高中同学')";
			doOperate(sql);
			sql = "insert into FriendsRss values(123456,3, '大学同学')";
			doOperate(sql);
			sql = "insert into FriendsRss values(123456,4, '大学同学')";
			doOperate(sql);
			sql = "insert into FriendsRss values(123456,5,'高中同学')";
			doOperate(sql);
			//123457    2
			sql = "insert into FriendsRss values(123457,1, '知己')";
			doOperate(sql); 
			sql = "insert into FriendsRss values(123457,3, '基友')";
			doOperate(sql);
			sql = "insert into FriendsRss values(123457,4, '知己')";
			doOperate(sql);
			sql = "insert into FriendsRss values(123457,5, '基友')";
			doOperate(sql);
			
			
			//123458   3
			sql = "insert into FriendsRss values(123458,1, '高中同学')";
			doOperate(sql);
			sql = "insert into FriendsRss values(123458,2, '高中同学')";
			doOperate(sql);
			sql = "insert into FriendsRss values(123458,4, '知己')";
			doOperate(sql);
			sql = "insert into FriendsRss values(12,123458,5, '基友')";
			
			
			//123459   4
			sql = "insert into FriendsRss values(123459,1, '高中同学')";
			doOperate(sql);
			sql = "insert into FriendsRss values(123459,2, '高中同学')";
			doOperate(sql);
			sql = "insert into FriendsRss values(123459,3, '朋友')";
			doOperate(sql);
			sql = "insert into FriendsRss values(123459,5, '基友')";
			
			
			//123460  5
			sql = "insert into FriendsRss values(123460,1, '高中同学')";
			doOperate(sql);
			sql = "insert into FriendsRss values(123460,2, '高中同学')";
			doOperate(sql);
			sql = "insert into FriendsRss values(123460,3, '朋友')";
			doOperate(sql);
			sql = "insert into FriendsRss values(123460,4, '基友')";
			
			System.out.println("====初始化表FriendsRss成功===");
		}
		else{
			System.out.println("********FriendsRss表已存在*******");
		}
		closeAll();
		
	}
}

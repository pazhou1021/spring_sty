package com.chinasofti.etc.hiq.dao.mysqlimpl;

import java.sql.Connection;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class BaseDao {
	protected Connection con = null;
	protected ResultSet rs = null;
	protected PreparedStatement pstmt = null;
	protected int result = 0;
	
	//ֻ����һ��
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//�����ݿ⽨������
	public void getConn(){
		String url = "jdbc:mysql://localhost:3306/myqq?user=root&password=1&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false";
		
		try {
			con = DriverManager.getConnection(url);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//�ر�����
	public void closeAll(){

		try {
			if (rs != null && !rs.isClosed()) rs.close();
			if (pstmt != null && !pstmt.isClosed()) pstmt.close();
			if (rs != null && !rs.isClosed()) rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//����ѯ����
	public void  doQuery(String sql,Object ... params){
		try{
			pstmt = con.prepareStatement(sql);
			if (params != null){
				for (int i = 0; i < params.length; ++i){
					pstmt.setObject(i + 1,params[i]);
				}
			}
			rs = pstmt.executeQuery();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//�����²���
	public void doOperate(String sql,Object ... params){
		try {
			pstmt = con.prepareStatement(sql);
			if (params != null){
				for (int i = 0; i < params.length; ++i){
					pstmt.setObject(i + 1, params[i]);
				}
			}
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	boolean existTable(String tableName){
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
		// ��ʼ����Users
		getConn();
		String sql = null;
		if (!existTable("Users")) {
			// ��ʼ����Users
			sql = "create table Users(userId int primary key auto_increment,userQQ int, userPassword varchar(50), userImage varchar(20), userNikName varchar(20), userSex int,userAddress varchar(50),userAge int,userBirthday varchar(50),uesrEmail varchar(50),userRgDate Date,userSpeak varchar(100))";
			doOperate(sql);
			sql = "insert into Users values(1, 123456, '1', '123456.png', '������',1,'ɽ������',18,'1990-11-14','gbx@163.com','1999-11-2','���������У�����')";
			doOperate(sql);
			sql = "insert into Users values(2, 123457, '1', '123457.png', '֣��',1,'ɽ����ׯ',25,'1990-11-14','zc@163.com','1999-11-2','��־���¾���')";
			doOperate(sql);
			sql = "insert into Users values(3, 123458, '1', '123458.png', '����ΰ',1,'�������������',29,'1990-11-19','zqw@163.com','1999-11-2','�����ҵ�õ�������ҵĻ��������ҵİ������ҵ�ǣ��')";
			doOperate(sql);
			sql = "insert into Users values(4, 123459, '1', '123459.png', 'Ԭ��',1,'ɽ���ൺ',29,'1990-11-19','yy@163.com','1999-11-2','�����ҵ�õ�������ҵĻ��������ҵİ������ҵ�ǣ��')";
			doOperate(sql);
			sql = "insert into Users values(5, 123460, '1', '123460.png', '������',0,'ɽ���ĳ�',29,'1990-11-19','xlh@163.com','1999-11-2','���£�����')";
			doOperate(sql);
			System.out.println("====��ʼ����Users�ɹ�===");
		}else {
			System.out.println("*******Users���Ѵ���********");
		}
		//��ʼ��FriendsRs
		
		if (!existTable("FriendsRss")){
			sql = "create table FriendsRss(friendsRsId int primary key auto_increment,userQQ int,friendId int,groupName varchar(50))";
			doOperate(sql);
			//123456    1
			sql = "insert into FriendsRss values(1,123456,2,'����ͬѧ')";
			doOperate(sql);
			sql = "insert into FriendsRss values(2,123456,3, '��ѧͬѧ')";
			doOperate(sql);
			sql = "insert into FriendsRss values(3,123456,4, '��ѧͬѧ')";
			doOperate(sql);
			sql = "insert into FriendsRss values(4,123456,5,'����ͬѧ')";
			doOperate(sql);
			//123457    2
			sql = "insert into FriendsRss values(5,123457,1, '֪��')";
			doOperate(sql); 
			sql = "insert into FriendsRss values(6,123457,3, '����')";
			doOperate(sql);
			sql = "insert into FriendsRss values(7,123457,4, '֪��')";
			doOperate(sql);
			sql = "insert into FriendsRss values(8,123457,5, '����')";
			doOperate(sql);
			
			
			//123458   3
			sql = "insert into FriendsRss values(9,123458,1, '����ͬѧ')";
			doOperate(sql);
			sql = "insert into FriendsRss values(10,123458,2, '����ͬѧ')";
			doOperate(sql);
			sql = "insert into FriendsRss values(11,123458,4, '֪��')";
			doOperate(sql);
			sql = "insert into FriendsRss values(12,123458,5, '����')";
			
			
			//123459   4
			sql = "insert into FriendsRss values(13,123459,1, '����ͬѧ')";
			doOperate(sql);
			sql = "insert into FriendsRss values(14,123459,2, '����ͬѧ')";
			doOperate(sql);
			sql = "insert into FriendsRss values(15,123459,3, '����')";
			doOperate(sql);
			sql = "insert into FriendsRss values(16,123459,5, '����')";
			
			
			//123460  5
			sql = "insert into FriendsRss values(17,123460,1, '����ͬѧ')";
			doOperate(sql);
			sql = "insert into FriendsRss values(18,123460,2, '����ͬѧ')";
			doOperate(sql);
			sql = "insert into FriendsRss values(19,123460,3, '����')";
			doOperate(sql);
			sql = "insert into FriendsRss values(20,123460,4, '����')";
			
			System.out.println("====��ʼ����FriendsRss�ɹ�===");
		}
		else{
			System.out.println(">>>>>>>>*******");
			System.out.println("********FriendsRss���Ѵ���*******");
		}
		closeAll();
		
	}
}

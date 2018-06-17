package com.chinasofti.etc.hiq.dao.sqlserverimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chinasofti.etc.hiq.dao.UserDao;
import com.chinasofti.etc.hiq.po.User;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {

	//查找出用户的最大的ID
	public int findMaxUserId() {
		// TODO Auto-generated method stub
		getConn();
		String sql = "select Max(userId) from Users";
		doQuery(sql);
		try {
			if (rs.next()){
				result = rs.getInt(1); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeAll();
		return result;
	}
	//查找出最大的QQ号
	public int findMaxUserQQ() {
		// TODO Auto-generated method stub
		getConn();
		String sql = "select Max(userQQ) from Users";
		doQuery(sql);
		try {
			if (rs.next()){
				result = rs.getInt(1); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeAll();
		return result;
	}
	//查看所有已注册用户

	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<User>();
		getConn();
		String sql = "select * from Users";
		doQuery(sql);
		try {
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt(1));
				user.setUserQQ(rs.getInt(2));
				user.setUserPassword(rs.getString(3));
				user.setUserImage(rs.getString(4));
				user.setUserNikName(rs.getString(5));
				user.setUserSex(rs.getShort(6));
				user.setUserAddress(rs.getString(7));
				user.setUserAge(rs.getInt(8));
				user.setUserBirthday(rs.getString(9));
				user.setUserEmail(rs.getString(10));
				user.setUserRgDate(rs.getDate(11));
				user.setUserSpeak(rs.getString(12));
				//加入
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	//条件查询
	public List<User> findUserWith(Object... params) {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<User>();
		String sql = "";
		getConn();
		int size = params.length;
		if(size == 0) return null;
		else if (size == 1){
			sql = "select * from Users where userNikName = ?";
			doQuery(sql, params[0]);
		}
		else if (size == 2){
			sql = "select * from Users where userNikName = ? and userSex=?";
			doQuery(sql,params[0], params[1]);
		}
		else if (size == 3){
			sql = "select * from Users where userNikName = ? and userAge=? and userSex = ?";
			doQuery(sql, params[0],params[1],params[2]);
		}
	
		try {
			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt(1));
				user.setUserQQ(rs.getInt(2));
				user.setUserPassword(rs.getString(3));
				user.setUserImage(rs.getString(4));
				user.setUserNikName(rs.getString(5));
				user.setUserSex(rs.getShort(6));
				user.setUserAddress(rs.getString(7));
				user.setUserAge(rs.getInt(8));
				user.setUserBirthday(rs.getString(9));
				user.setUserEmail(rs.getString(10));
				user.setUserRgDate(rs.getDate(11));
				user.setUserSpeak(rs.getString(12));
				//加入
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}
	
    //根据用户ID查找用户
	
	public User findUserById(int userId) {
		// TODO Auto-generated method stub
		User user = null;
		getConn();
		String sql = "select * from Users where UserId = ?";
		doQuery(sql, userId);
		try {
			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getInt(1));
				user.setUserQQ(rs.getInt(2));
				user.setUserPassword(rs.getString(3));
				user.setUserImage(rs.getString(4));
				user.setUserNikName(rs.getString(5));
				user.setUserSex(rs.getShort(6));
				user.setUserAddress(rs.getString(7));
				user.setUserAge(rs.getInt(8));
				user.setUserBirthday(rs.getString(9));
				user.setUserEmail(rs.getString(10));
				user.setUserRgDate(rs.getDate(11));
				user.setUserSpeak(rs.getString(12));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeAll();
		return user;
	}
    //通过QQ号查找用户
	
	public User findUserByQQ(int userQQ) {
		// TODO Auto-generated method stub
		User user = null;
		getConn();
		String sql = "select * from Users where userQQ = ?";
		doQuery(sql, userQQ);
		try {
			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getInt(1));
				user.setUserQQ(rs.getInt(2));
				user.setUserPassword(rs.getString(3));
				user.setUserImage(rs.getString(4));
				user.setUserNikName(rs.getString(5));
				user.setUserSex(rs.getShort(6));
				user.setUserAddress(rs.getString(7));
				user.setUserAge(rs.getInt(8));
				user.setUserBirthday(rs.getString(9));
				user.setUserEmail(rs.getString(10));
				user.setUserRgDate(rs.getDate(11));
				user.setUserSpeak(rs.getString(12));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeAll();
		return user;
	}
    //通过昵称得到用户

	public User findUserByNikName(String userNikName) {
		// TODO Auto-generated method stub
		User user = null;
		getConn();
		String sql = "select * from Users where userNikName = ?";
		doQuery(sql, userNikName);
		try {
			if (rs.next()) {
				user = new User();
				user.setUserId(rs.getInt(1));
				user.setUserQQ(rs.getInt(2));
				user.setUserPassword(rs.getString(3));
				user.setUserImage(rs.getString(4));
				user.setUserNikName(rs.getString(5));
				user.setUserSex(rs.getShort(6));
				user.setUserAddress(rs.getString(7));
				user.setUserAge(rs.getInt(8));
				user.setUserBirthday(rs.getString(9));
				user.setUserEmail(rs.getString(10));
				user.setUserRgDate(rs.getDate(11));
				user.setUserSpeak(rs.getString(12));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeAll();
		return user;
	}
    
	//项数据中添加用户
	
	public int insertUser(User user) {
		// TODO Auto-generated method stub
		getConn();
		String sql = "insert into Users values(?,?,?,?,?,?,?,?,?,?,?)";
		
		doOperate(sql, user.getUserQQ(),user.getUserPassword(),user.getUserImage(),user.getUserNikName(),
				user.getUserSex(),user.getUserAddress(),user.getUserAge(),user.getUserBirthday(),
				user.getUserEmail(),user.getUserRgDate(),user.getUserSpeak());
		closeAll();
		return result;
	}
    //通过用户ID删除用户
	
	public int deleteUserById(int userId) {
		// TODO Auto-generated method stub
		getConn();
		String sql = "delete from Users where userId = ?";
		doOperate(sql, userId);
		closeAll();
		return result;
	}
	//通过用户QQ号删除用户
	
	public int deleteUserByQQ(String userQQ) {
		// TODO Auto-generated method stub
		getConn();
		String sql = "delete from Users where userQQ = ?";
		doOperate(sql, userQQ);
		closeAll();
		return result;
	}
	//通过用户昵称删除用户
	
	public int deleteUserByNikName(String userNikName) {
		// TODO Auto-generated method stub
		getConn();
		String sql = "delete from Users where userNikName = ?";
		doOperate(sql, userNikName);
		closeAll();
		return result;
	}
    //通过用户ID更新用户
	
	public int upateUserByUserId(int userId, User newUser) {
		// TODO Auto-generated method stub
	
		getConn();
		String sql = "update Users set userQQ = ?,userPassword=?,userImage=?,userNikName=?,userSex=?,userAddress=?,userAge=?,userBirthday=?,userEmail=?,userRgDate=?,userSpeak=? where userId = ?";
		doOperate(sql,newUser.getUserQQ(),newUser.getUserPassword(),newUser.getUserImage(),newUser.getUserNikName(),
				newUser.getUserSex(),newUser.getUserAddress(),newUser.getUserAge(),newUser.getUserBirthday(),newUser.getUserEmail(),
				newUser.getUserRgDate(),newUser.getUserSpeak());
		closeAll();
		return result;
	}
	//通过用户QQ更新用户
	
	public int updateUserByUserQQ(int userQQ, User newUser) {
		// TODO Auto-generated method stub
		getConn();
		String sql = "update Users set userQQ = ?,userPassword=?,userImage=?,userNikName=?,userSex=?,userAddress=?,userAge=?,userBirthday=?,userEmail=?,userRgDate=?,userSpeak=? where userQQ = ?";
		doOperate(sql, newUser.getUserQQ(),newUser.getUserPassword(),newUser.getUserImage(),newUser.getUserNikName(),
				newUser.getUserSex(),newUser.getUserAddress(),newUser.getUserAge(),newUser.getUserBirthday(),newUser.getUserEmail(),
				newUser.getUserRgDate(),newUser.getUserSpeak(),userQQ);
		closeAll();
		return result;
	}
	//通过用户昵称更新用户
	
	public int updateUserByNikName(String userNikName, User newUser) {
		// TODO Auto-generated method stub
		getConn();
		String sql = "update Users set userQQ = ?,userPassword=?,userImage=?,userNikName=?,userSex=?,userAddress=?,userAge=?,userBirthday=?,userEmail=?,userRgDate=?,userSpeak=? where userNikName = ?";
		doOperate(sql, newUser.getUserQQ(),newUser.getUserPassword(),newUser.getUserImage(),newUser.getUserNikName(),
				newUser.getUserSex(),newUser.getUserAddress(),newUser.getUserAge(),newUser.getUserBirthday(),newUser.getUserEmail(),
				newUser.getUserRgDate(),newUser.getUserSpeak(),userNikName);
		closeAll();
		return result;
	}
	
	
}

package com.chinasofti.etc.hiq.dao.mysqlimpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.chinasofti.etc.hiq.dao.FriendsRsDao;
import com.chinasofti.etc.hiq.dao.UserDao;
import com.chinasofti.etc.hiq.po.FriendsRs;
import com.chinasofti.etc.hiq.po.User;

public class FriendsRsDaoImpl extends BaseDaoImpl implements FriendsRsDao {

	//查找最大ID
	public int findMaxFriendsRsId() {
		// TODO Auto-generated method stub
		getConn();
		String sql = "select Max(friendsRsId) from FriendsRss";
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
	//按照用户QQ号查找好友
	public Map<String, List<User>> findFriendsByUserQQ(int userQQ) {
		// TODO Auto-generated method stub
		UserDao userDao = new UserDaoImpl();
		//返回Map<好友分组,属于该分组的好友列表>
		Map<String, List<User>> userToFriends = new HashMap<String, List<User>>();
		getConn();
		//测试
		System.out.println("要查找的用户的QQ为："  + userQQ);
		
		String sql = "select * from FriendsRss where userQQ = ?";
		doQuery(sql, userQQ);
		//好友关系
		List<FriendsRs> friendsRss = new ArrayList<FriendsRs>();
		
		try {
			//获得userId的FriendsRs
			while (rs.next()){
				FriendsRs friendsRs = new FriendsRs();
				friendsRs.setFriendsRsId(rs.getInt(1));
				friendsRs.setUserQQ(rs.getInt(2));
				friendsRs.setFriendId(rs.getInt(3));
				friendsRs.setGroupName(rs.getString(4));
				//加入
				friendsRss.add(friendsRs);
			}
			Iterator<FriendsRs> iterator = friendsRss.iterator();
			//遍历FriendsRs得到每个分组有多少好友
			while (iterator.hasNext()){
				FriendsRs friendsRs = iterator.next();
				
				System.out.println("测试：" +  friendsRs.getUserQQ() + " " + friendsRs.getFriendId() + "  " + friendsRs.getGroupName());
				
				//获得好友的Id
				int friendId = friendsRs.getFriendId();
				//通过好友的Id查找好友
				User friend = userDao.findUserById(friendId);
				//如果改组已经存在
				if (userToFriends.containsKey(friendsRs.getGroupName())){
					userToFriends.get(friendsRs.getGroupName()).add(friend);
				}//该分组不存在
				else{
					//先建立好友列表
					List<User> friends = new ArrayList<User>();
					friends.add(friend);
					//然后放入
					userToFriends.put(friendsRs.getGroupName(), friends);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeAll();
		return userToFriends;
	}
//	@Override
//	public Map<String, List<User>> findFriendsByUserId(int userId) {
//		// TODO Auto-generated method stub
//		UserDao userDao = new UserDaoImpl();
//		
//		Map<String, List<User>> userToFriends = new HashMap<String, List<User>>();
//		getConn();
//		String sql = "select * from FriendsRss where userId = ?";
//		doQuery(sql, userId);
//		List<FriendsRs> friendsRss = new ArrayList<FriendsRs>();
//		
//		try {
//			//获得userId的FriendsRs
//			while (rs.next()){
//				FriendsRs friendsRs = new FriendsRs();
//				friendsRs.setFriendsRsId(rs.getInt(1));
//				friendsRs.setUserId(rs.getInt(2));
//				friendsRs.setFriendId(rs.getInt(3));
//				friendsRs.setGroupName(rs.getString(4));
//				//加入
//				friendsRss.add(friendsRs);
//			}
//			Iterator<FriendsRs> iterator = friendsRss.iterator();
//			//遍历FriendsRs得到每个分组有多少好友
//			while (iterator.hasNext()){
//				FriendsRs friendsRs = iterator.next();
//				//获得好友的Id
//				int friendId = friendsRs.getFriendId();
//				//通过好友的Id查找好友
//				User friend = userDao.findUserById(friendId);
//				//如果改组已经存在
//				if (userToFriends.containsKey(friendsRs.getGroupName())){
//					userToFriends.get(friendsRs.getGroupName()).add(friend);
//				}//该分组不存在
//				else{
//					//先建立好友列表
//					List<User> friends = new ArrayList<User>();
//					friends.add(friend);
//					//然后放入
//					userToFriends.put(friendsRs.getGroupName(), friends);
//				}
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		closeAll();
//		return userToFriends;
//	}

	//插入用户好友关系
	public int insertFriendsRs(FriendsRs friendsRs) {
		// TODO Auto-generated method stub
		getConn();
		String sql = "insert FriendsRss values(?,?,?,?)";
		int maxId = this.findMaxFriendsRsId();
		doOperate(sql, maxId + 1,friendsRs.getUserQQ(),friendsRs.getFriendId(),friendsRs.getGroupName());
		closeAll();
		return result;
	}
	//根据用户QQ删除该用户的的好友关系
	public int deletFriendsRsByUserQQ(int userQQ) {
		// TODO Auto-generated method stub
		getConn();
		String sql = "delete from FriendsRss where userQQ = ?";
		doOperate(sql, userQQ);
		closeAll();
		return result;
	}

	//删除某个用户的好友
	public int deleteFriendsRsByFriendId(int userQQ, int friendId) {
		// TODO Auto-generated method stub
		getConn();
		String sql = "delete from FriendsRss where userQQ = ?，friendId = ?";
		doOperate(sql,userQQ,friendId);
		closeAll();
		return result;
	}
    //更新好友的分组
	public int updateFriendsRsByfFriendId(int userQQ, int friendId,FriendsRs newFriendsRs) {
		// TODO Auto-generated method stub
		getConn();
		String sql = "update FriendsRss set friendsRsId = ?,userQQ = ?，friendId = ?,groupName = ? where userId = ?，friendId = ?";
		doOperate(sql, newFriendsRs.getFriendsRsId(),newFriendsRs.getUserQQ(),newFriendsRs.getFriendId(),newFriendsRs.getGroupName(),userQQ,friendId);
		closeAll();
		return result;
	}

	

}

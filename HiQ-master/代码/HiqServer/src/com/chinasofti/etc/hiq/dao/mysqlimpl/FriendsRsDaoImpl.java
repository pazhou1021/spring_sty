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

	//�������ID
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
	//�����û�QQ�Ų��Һ���
	public Map<String, List<User>> findFriendsByUserQQ(int userQQ) {
		// TODO Auto-generated method stub
		UserDao userDao = new UserDaoImpl();
		//����Map<���ѷ���,���ڸ÷���ĺ����б�>
		Map<String, List<User>> userToFriends = new HashMap<String, List<User>>();
		getConn();
		//����
		System.out.println("Ҫ���ҵ��û���QQΪ��"  + userQQ);
		
		String sql = "select * from FriendsRss where userQQ = ?";
		doQuery(sql, userQQ);
		//���ѹ�ϵ
		List<FriendsRs> friendsRss = new ArrayList<FriendsRs>();
		
		try {
			//���userId��FriendsRs
			while (rs.next()){
				FriendsRs friendsRs = new FriendsRs();
				friendsRs.setFriendsRsId(rs.getInt(1));
				friendsRs.setUserQQ(rs.getInt(2));
				friendsRs.setFriendId(rs.getInt(3));
				friendsRs.setGroupName(rs.getString(4));
				//����
				friendsRss.add(friendsRs);
			}
			Iterator<FriendsRs> iterator = friendsRss.iterator();
			//����FriendsRs�õ�ÿ�������ж��ٺ���
			while (iterator.hasNext()){
				FriendsRs friendsRs = iterator.next();
				
				System.out.println("���ԣ�" +  friendsRs.getUserQQ() + " " + friendsRs.getFriendId() + "  " + friendsRs.getGroupName());
				
				//��ú��ѵ�Id
				int friendId = friendsRs.getFriendId();
				//ͨ�����ѵ�Id���Һ���
				User friend = userDao.findUserById(friendId);
				//��������Ѿ�����
				if (userToFriends.containsKey(friendsRs.getGroupName())){
					userToFriends.get(friendsRs.getGroupName()).add(friend);
				}//�÷��鲻����
				else{
					//�Ƚ��������б�
					List<User> friends = new ArrayList<User>();
					friends.add(friend);
					//Ȼ�����
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
//			//���userId��FriendsRs
//			while (rs.next()){
//				FriendsRs friendsRs = new FriendsRs();
//				friendsRs.setFriendsRsId(rs.getInt(1));
//				friendsRs.setUserId(rs.getInt(2));
//				friendsRs.setFriendId(rs.getInt(3));
//				friendsRs.setGroupName(rs.getString(4));
//				//����
//				friendsRss.add(friendsRs);
//			}
//			Iterator<FriendsRs> iterator = friendsRss.iterator();
//			//����FriendsRs�õ�ÿ�������ж��ٺ���
//			while (iterator.hasNext()){
//				FriendsRs friendsRs = iterator.next();
//				//��ú��ѵ�Id
//				int friendId = friendsRs.getFriendId();
//				//ͨ�����ѵ�Id���Һ���
//				User friend = userDao.findUserById(friendId);
//				//��������Ѿ�����
//				if (userToFriends.containsKey(friendsRs.getGroupName())){
//					userToFriends.get(friendsRs.getGroupName()).add(friend);
//				}//�÷��鲻����
//				else{
//					//�Ƚ��������б�
//					List<User> friends = new ArrayList<User>();
//					friends.add(friend);
//					//Ȼ�����
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

	//�����û����ѹ�ϵ
	public int insertFriendsRs(FriendsRs friendsRs) {
		// TODO Auto-generated method stub
		getConn();
		String sql = "insert FriendsRss values(?,?,?,?)";
		int maxId = this.findMaxFriendsRsId();
		doOperate(sql, maxId + 1,friendsRs.getUserQQ(),friendsRs.getFriendId(),friendsRs.getGroupName());
		closeAll();
		return result;
	}
	//�����û�QQɾ�����û��ĵĺ��ѹ�ϵ
	public int deletFriendsRsByUserQQ(int userQQ) {
		// TODO Auto-generated method stub
		getConn();
		String sql = "delete from FriendsRss where userQQ = ?";
		doOperate(sql, userQQ);
		closeAll();
		return result;
	}

	//ɾ��ĳ���û��ĺ���
	public int deleteFriendsRsByFriendId(int userQQ, int friendId) {
		// TODO Auto-generated method stub
		getConn();
		String sql = "delete from FriendsRss where userQQ = ?��friendId = ?";
		doOperate(sql,userQQ,friendId);
		closeAll();
		return result;
	}
    //���º��ѵķ���
	public int updateFriendsRsByfFriendId(int userQQ, int friendId,FriendsRs newFriendsRs) {
		// TODO Auto-generated method stub
		getConn();
		String sql = "update FriendsRss set friendsRsId = ?,userQQ = ?��friendId = ?,groupName = ? where userId = ?��friendId = ?";
		doOperate(sql, newFriendsRs.getFriendsRsId(),newFriendsRs.getUserQQ(),newFriendsRs.getFriendId(),newFriendsRs.getGroupName(),userQQ,friendId);
		closeAll();
		return result;
	}

	

}

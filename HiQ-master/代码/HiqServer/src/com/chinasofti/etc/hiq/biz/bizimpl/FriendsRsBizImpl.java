package com.chinasofti.etc.hiq.biz.bizimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.chinasofti.etc.hiq.biz.FriendsRsBiz;
import com.chinasofti.etc.hiq.dao.FriendsRsDao;
import com.chinasofti.etc.hiq.po.FriendsRs;
import com.chinasofti.etc.hiq.po.User;

public class FriendsRsBizImpl implements FriendsRsBiz {

	private static FriendsRsDao friendsRsDao = null;
	static{
		File file = new File("f:\\myconfig.properties");
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String DaoString = properties.getProperty("tFriendsRsDao");
		try {
			friendsRsDao = (FriendsRsDao)Class.forName(DaoString).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int findMaxFriendsRsId() {
		// TODO Auto-generated method stub
		return friendsRsDao.findMaxFriendsRsId();
	}


	
	public int insertFriendsRs(FriendsRs friendsRs) {
		// TODO Auto-generated method stub
		return friendsRsDao.insertFriendsRs(friendsRs);
	}

	
	public int deletFriendsRsByUserQQ(int userQQ) {
		// TODO Auto-generated method stub
		return friendsRsDao.deletFriendsRsByUserQQ(userQQ);
	}

	
	public int deleteFriendsRsByFriendId(int userQQ, int friendId) {
		// TODO Auto-generated method stub
		return friendsRsDao.deleteFriendsRsByFriendId(userQQ, friendId);
	}

	
	public int updateFriendsRsByfFriendId(int userId, int friendId,
			FriendsRs newFriendsRs) {
		// TODO Auto-generated method stub
		return friendsRsDao.updateFriendsRsByfFriendId(userId, friendId, newFriendsRs);
	}


	
	public Map<String, List<User>> findFriendsByUserQQ(int userQQ) {
		// TODO Auto-generated method stub
		return friendsRsDao.findFriendsByUserQQ(userQQ);
	}


}

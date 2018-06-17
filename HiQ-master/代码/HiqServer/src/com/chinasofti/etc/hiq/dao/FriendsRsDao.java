package com.chinasofti.etc.hiq.dao;

import java.util.List;
import java.util.Map;

import com.chinasofti.etc.hiq.po.FriendsRs;
import com.chinasofti.etc.hiq.po.User;

public interface FriendsRsDao {
	    //�飺
		int findMaxFriendsRsId();
		Map<String,List<User>> findFriendsByUserQQ(int userQQ); 
		//����
		int insertFriendsRs(FriendsRs friendsRs);
		//ɾ
		int deletFriendsRsByUserQQ(int userQQ);
		//ɾ���û�user������
		int deleteFriendsRsByFriendId(int userQQ,int friendId);
		//�ģ�
		int updateFriendsRsByfFriendId(int userQQ,int friendId,FriendsRs newFriendsRs);
		
}

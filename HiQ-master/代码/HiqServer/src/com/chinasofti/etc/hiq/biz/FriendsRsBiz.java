package com.chinasofti.etc.hiq.biz;

import java.util.List;
import java.util.Map;

import com.chinasofti.etc.hiq.po.FriendsRs;
import com.chinasofti.etc.hiq.po.User;

public interface FriendsRsBiz {
	//查：
	int findMaxFriendsRsId();
	Map<String,List<User>> findFriendsByUserQQ(int userQQ); 
	//增：
	int insertFriendsRs(FriendsRs friendsRs);
	//删
	int deletFriendsRsByUserQQ(int userQQ);
	//删除用户user的朋友
	int deleteFriendsRsByFriendId(int userQQ,int friendId);
	//改：
	int updateFriendsRsByfFriendId(int userQQ,int friendId,FriendsRs newFriendsRs);

}

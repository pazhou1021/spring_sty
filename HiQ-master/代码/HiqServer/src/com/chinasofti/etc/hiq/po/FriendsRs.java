package com.chinasofti.etc.hiq.po;

public class FriendsRs {
	private int friendsRsId;
	private int userQQ;
	private int friendId;
	private String groupName;
	//
	public int getFriendsRsId() {
		return friendsRsId;
	}
	public void setFriendsRsId(int friendsRsId) {
		this.friendsRsId = friendsRsId;
	}
	public int getUserQQ() {
		return userQQ;
	}
	public void setUserQQ(int userQQ) {
		this.userQQ = userQQ;
	}
	public int getFriendId() {
		return friendId;
	}
	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public FriendsRs() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FriendsRs(int friendsRsId, int userQQ, int friendId, String groupName) {
		super();
		this.friendsRsId = friendsRsId;
		this.userQQ = userQQ;
		this.friendId = friendId;
		this.groupName = groupName;
	}
	
	
}

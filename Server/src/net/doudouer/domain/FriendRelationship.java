package net.doudouer.domain;

import java.io.Serializable;

public class FriendRelationship implements Serializable{

	private static final long serialVersionUID = -208459066226769689L;
	
	private Long userID;
	private Long friendID;
	
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public Long getFriendID() {
		return friendID;
	}
	public void setFriendID(Long friendID) {
		this.friendID = friendID;
	}
}

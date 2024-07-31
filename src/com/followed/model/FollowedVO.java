package com.followed.model;

import java.io.Serializable;

public class FollowedVO implements Serializable{
	private Integer AIfollowedID;
	private Integer memberId;
	private Integer followedId;
	
	public Integer getAIFollowedID() {
		return AIfollowedID;
	}
	public void setFollowedID(Integer AIfollowedID) {
		this.AIfollowedID = AIfollowedID;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getFollowedId() {
		return followedId;
	}
	public void setFollowedId(Integer followedId) {
		this.followedId = followedId;
	}
}

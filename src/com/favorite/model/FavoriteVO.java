package com.favorite.model;

import java.io.Serializable;

public class FavoriteVO implements Serializable{
	private Integer favId;
	private Integer memberId;
	private Integer campId;
	
	public Integer getFavId() {
		return favId;
	}
	public void setFavId(Integer favId) {
		this.favId = favId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getCampId() {
		return campId;
	}
	public void setCampId(Integer campId) {
		this.campId = campId;
	}
	
	
}

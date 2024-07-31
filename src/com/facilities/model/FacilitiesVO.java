package com.facilities.model;

import java.io.Serializable;

public class FacilitiesVO implements Serializable{
	
	@Override
	public String toString() {
		return "FacilitiesVO [facilitiesId=" + facilitiesId + ", campId=" + campId + ", bbq=" + bbq + ", wifi=" + wifi
				+ ", nosmoke=" + nosmoke + ", pets=" + pets + "]";
	}
	private Integer facilitiesId;
	private Integer campId;
	private Integer bbq;
	private Integer wifi;
	private Integer nosmoke;
	private Integer pets;
	
	public Integer getFacilitiesId() {
		return facilitiesId;
	}
	public void setFacilitiesId(Integer facilitiesId) {
		this.facilitiesId = facilitiesId;
	}
	public Integer getCampId() {
		return campId;
	}
	public void setCampId(Integer campId) {
		this.campId = campId;
	}
	public Integer getBbq() {
		return bbq;
	}
	public void setBbq(Integer bbq) {
		this.bbq = bbq;
	}
	public Integer getWifi() {
		return wifi;
	}
	public void setWifi(Integer wifi) {
		this.wifi = wifi;
	}
	public Integer getNosmoke() {
		return nosmoke;
	}
	public void setNosmoke(Integer nosmoke) {
		this.nosmoke = nosmoke;
	}
	public Integer getPets() {
		return pets;
	}
	public void setPets(Integer pets) {
		this.pets = pets;
	}

}

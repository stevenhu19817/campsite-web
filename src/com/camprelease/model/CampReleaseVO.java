package com.camprelease.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

public class CampReleaseVO implements Serializable {
	
	private Integer campId;
	private String campName;
	private String location;
	private Double latitude;
	private Double longtitude;
	private String campDescription;
	private Integer campPrice;
	private Timestamp listedTime;
	private byte[] picture1;
	private byte[] picture2;
	private byte[] picture3;
	private byte[] picture4;
	private byte[] picture5;
	private Integer memberId;
	private Integer campLimit;
//	private java.sql.Time openTime;
//	private java.sql.Time closeTime;	
	
	public Integer getCampId() {
		return campId;
	}
	public void setCampId(Integer campId) {
		this.campId = campId;
	}
	public String getCampName() {
		return campName;
	}
	public void setCampName(String campName) {
		this.campName = campName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongtitude() {
		return longtitude;
	}
	public void setLongtitude(Double longtitude) {
		this.longtitude = longtitude;
	}
	public String getCampDescription() {
		return campDescription;
	}
	public void setCampDescription(String campDescription) {
		this.campDescription = campDescription;
	}
	public Integer getCampPrice() {
		return campPrice;
	}
	public void setCampPrice(Integer campPrice) {
		this.campPrice = campPrice;
	}
	public Timestamp getListedTime() {
		return listedTime;
	}
	public void setListedTime(Timestamp listedTime) {
		this.listedTime = listedTime;
	}
	public byte[] getPicture1() {
		return picture1;
	}
	public void setPicture1(byte[] picture1) {
		this.picture1 = picture1;
	}
	
	public byte[] getPicture2() {
		return picture2;
	}
	public void setPicture2(byte[] picture2) {
		this.picture2 = picture2;
	}
	public byte[] getPicture3() {
		return picture3;
	}
	public void setPicture3(byte[] picture3) {
		this.picture3 = picture3;
	}
	public byte[] getPicture4() {
		return picture4;
	}
	public void setPicture4(byte[] picture4) {
		this.picture4 = picture4;
	}
	public byte[] getPicture5() {
		return picture5;
	}
	public void setPicture5(byte[] picture5) {
		this.picture5 = picture5;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getCampLimit() {
		return campLimit;
	}
	public void setCampLimit(Integer campLimit) {
		this.campLimit = campLimit;
	}
	@Override
	public String toString() {
		return "CampReleaseVO [campId=" + campId + ", campName=" + campName + ", location=" + location + ", latitude="
				+ latitude + ", longtitude=" + longtitude + ", campDescription=" + campDescription + ", campPrice="
				+ campPrice + ", listedTime=" + listedTime + ", picture1=" + Arrays.toString(picture1) + ", picture2="
				+ Arrays.toString(picture2) + ", picture3=" + Arrays.toString(picture3) + ", picture4="
				+ Arrays.toString(picture4) + ", picture5=" + Arrays.toString(picture5) + ", memberId=" + memberId
				+ ", campLimit=" + campLimit + "]";
	}
}

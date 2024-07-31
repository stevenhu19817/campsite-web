package com.campAlert.model;
import java.io.Serializable;
import java.util.Arrays;
public class CampAlertVO implements Serializable{
private Integer alertId;
private Integer memberId;
private Integer campId;
private String reportTime;
private String  content;
private byte[]  picture1;
private byte[]  picture2;
private byte[]  picture3;
private Integer reportStatus;
private Integer handeler;
public CampAlertVO() {
	super();
}
public Integer getAlertId() {
	return alertId;
}
public void setAlertId(Integer alertId) {
	this.alertId = alertId;
}
public Integer getMemberId() {
	return memberId;
}
public void setMemberId(Integer memberId) {
	this.memberId =memberId;
}
public Integer getCampId() {
	return campId;
}
public void setCampId(Integer campId) {
	this.campId = campId;
}
public String getReportTime() {
	return reportTime;
}
public void setReportTime(String reportTime) {
	this.reportTime = reportTime;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
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
public Integer getReportStatus() {
	return reportStatus;
}
public void setReportStatus(Integer reportStatus) {
	this.reportStatus = reportStatus;
}
public Integer getHandeler() {
	return handeler;
}
public void setHandeler(Integer handeler) {
	this.handeler = handeler;
}
@Override
public String toString() {
	return "CampAlertDAO [alertId=" + alertId + ", memberId=" + memberId + ", campId=" + campId + ", reportTime="
			+ reportTime + ", content=" + content + ", picture1=" + Arrays.toString(picture1) + ", picture2="
			+ Arrays.toString(picture2) + ", picture3=" + Arrays.toString(picture3) + ", reportStatus=" + reportStatus
			+ ", handeler=" + handeler + "]";
}

}
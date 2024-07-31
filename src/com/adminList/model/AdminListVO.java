package com.adminList.model;
import java.io.Serializable;
public class AdminListVO implements Serializable{
private Integer adminId;
private String adminPwd;
private String adminName;
public AdminListVO() {
	super();
	
}
public AdminListVO(Integer adminId, String adminPwd, String adminName) {
	super();
	this.adminId=adminId;
	this.adminPwd=adminPwd;
	this.adminName=adminName;
	

}
public Integer getAdminId() {
	return adminId;
}
public void setAdminId(Integer adminId) {
	this.adminId = adminId;
}
public String getAdminPwd() {
	return adminPwd;
}
public void setAdminPwd(String adminPwd) {
	this.adminPwd = adminPwd;
}
public String getAdminName() {
	return adminName;
}
public void setAdminName(String adminName) {
	this.adminName = adminName;
}
@Override
public String toString() {
	return "AdminListVO [adminId=" + adminId + ", adminPwd=" + adminPwd + ", adminName=" + adminName + "]";
}

}
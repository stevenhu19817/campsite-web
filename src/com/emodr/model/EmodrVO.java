package com.emodr.model;

import java.sql.Date;

public class EmodrVO implements java.io.Serializable {
	private Integer emodr_id;// 商城訂單編號
	private Integer member_id;// 買方會員編號
	private Date emodr_date;// 訂單日期
	private String receipient;// 收貨人
	private String addr;// 收貨地址
	private String mobile;// 收貨電話
	private Double totalprice;// 總價
	private Boolean emodr_status; // 訂單狀態

	public EmodrVO() {
		super();
	}

	public Integer getEmodr_id() {
		return emodr_id;
	}

	public void setEmodr_id(Integer emodr_id) {
		this.emodr_id = emodr_id;
	}

	public Integer getMember_id() {
		return member_id;
	}

	public void setMember_id(Integer member_id) {
		this.member_id = member_id;
	}

	public Date getEmodr_date() {
		return emodr_date;
	}

	public void setEmodr_date(Date emodr_date) {
		this.emodr_date = emodr_date;
	}

	public String getReceipient() {
		return receipient;
	}

	public void setReceipient(String receipient) {
		this.receipient = receipient;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(Double totalprice) {
		this.totalprice = totalprice;
	}

	public Boolean getEmodr_status() {
		return emodr_status;
	}

	public void setEmodr_status(Boolean emodr_status) {
		this.emodr_status = emodr_status;
	}

	


	
}

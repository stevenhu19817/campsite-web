package com.camporder.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;

public class CampOrderVO implements Serializable {
	private Integer campOrderId;
	private Integer campId;
	private Integer memberId;
	private Integer guestNumber;
	private Date checkInDate;
	private Date checkOutDate;
	private Timestamp orderDate;
	private Timestamp paymentDeadline;
	private String orderStatus;
	private Integer orderTotal;
	private String comment;
	private byte[] picture1;
	private byte[] picture2;
	private byte[] picture3;
	
	public CampOrderVO() {
	}

	public CampOrderVO(Integer campOrderId, Integer campId, Integer memberId, Integer guestNumber, Date checkInDate,
			Date checkOutDate, Timestamp orderDate, Timestamp paymentDeadline, String orderStatus, Integer orderTotal,
			String comment, byte[] picture1, byte[] picture2, byte[] picture3) {
		this.campOrderId = campOrderId;
		this.campId = campId;
		this.memberId = memberId;
		this.guestNumber = guestNumber;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.orderDate = orderDate;
		this.paymentDeadline = paymentDeadline;
		this.orderStatus = orderStatus;
		this.orderTotal = orderTotal;
		this.comment = comment;
		this.picture1 = picture1;
		this.picture2 = picture2;
		this.picture3 = picture3;
	}

	public Integer getCampOrderId() {
		return campOrderId;
	}

	public void setCampOrderId(Integer campOrderId) {
		this.campOrderId = campOrderId;
	}

	public Integer getCampId() {
		return campId;
	}

	public void setCampId(Integer campId) {
		this.campId = campId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getGuestNumber() {
		return guestNumber;
	}

	public void setGuestNumber(Integer guestNumber) {
		this.guestNumber = guestNumber;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public Timestamp getPaymentDeadline() {
		return paymentDeadline;
	}

	public void setPaymentDeadline(Timestamp paymentDeadline) {
		this.paymentDeadline = paymentDeadline;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(Integer orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	@Override
	public String toString() {
		return "Camp_OrderVO [campOrderId=" + campOrderId + ", campId=" + campId + ", memberId=" + memberId
				+ ", guestNumber=" + guestNumber + ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate
				+ ", orderDate=" + orderDate + ", paymentDeadline=" + paymentDeadline + ", orderStatus=" + orderStatus
				+ ", orderTotal=" + orderTotal + ", comment=" + comment + ", picture1=" + Arrays.toString(picture1)
				+ ", picture2=" + Arrays.toString(picture2) + ", picture3=" + Arrays.toString(picture3) + "]";
	}

}

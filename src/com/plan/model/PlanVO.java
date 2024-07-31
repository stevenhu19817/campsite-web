package com.plan.model;

import java.io.Serializable;

public class PlanVO implements Serializable {
	private Integer planId;
	private Integer campId;
	private String planName;
	private String planOutline;
	private Integer planGuestLimit;
	private Integer planAgeLimit;
	private Integer planPrice;
	
	public PlanVO() {
	}

	public PlanVO(Integer planId, Integer campId, String planName, Integer planGuestLimit, Integer planAgeLimit,
			Integer planPrice) {
		this.planId = planId;
		this.campId = campId;
		this.planName = planName;
		this.planGuestLimit = planGuestLimit;
		this.planAgeLimit = planAgeLimit;
		this.planPrice = planPrice;
	}

	public String getPlanOutline() {
		return planOutline;
	}

	public void setPlanOutline(String planOutline) {
		this.planOutline = planOutline;
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public Integer getCampId() {
		return campId;
	}

	public void setCampId(Integer campId) {
		this.campId = campId;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public Integer getPlanGuestLimit() {
		return planGuestLimit;
	}

	public void setPlanGuestLimit(Integer planGuestLimit) {
		this.planGuestLimit = planGuestLimit;
	}

	public Integer getPlanAgeLimit() {
		return planAgeLimit;
	}

	public void setPlanAgeLimit(Integer planAgeLimit) {
		this.planAgeLimit = planAgeLimit;
	}

	public Integer getPlanPrice() {
		return planPrice;
	}

	public void setPlanPrice(Integer planPrice) {
		this.planPrice = planPrice;
	}

	@Override
	public String toString() {
		return "PlanVO [planId=" + planId + ", campId=" + campId + ", planName=" + planName + ", planOutline="
				+ planOutline + ", planGuestLimit=" + planGuestLimit + ", planAgeLimit=" + planAgeLimit + ", planPrice="
				+ planPrice + "]";
	}

	
}



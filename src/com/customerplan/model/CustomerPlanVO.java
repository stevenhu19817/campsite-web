package com.customerplan.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class CustomerPlanVO implements Serializable {
	@JsonIgnoreProperties(ignoreUnknown = true)
	private Integer campOrderId;
	private Integer planId;
	private Integer planGuestNumber;
	@JsonIgnoreProperties(ignoreUnknown = true)
	private String planBatch;
	private Integer planOrderPrice;

	public CustomerPlanVO() {
	}

	public CustomerPlanVO(Integer campOrderId, Integer planId, Integer planGuestNumber, String planBatch,
			Integer planOrderPrice) {
		this.campOrderId = campOrderId;
		this.planId = planId;
		this.planGuestNumber = planGuestNumber;
		this.planBatch = planBatch;
		this.planOrderPrice = planOrderPrice;
	}

	public Integer getCampOrderId() {
		return campOrderId;
	}

	public void setCampOrderId(Integer campOrderId) {
		this.campOrderId = campOrderId;
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public Integer getPlanGuestNumber() {
		return planGuestNumber;
	}

	public void setPlanGuestNumber(Integer planGuestNumber) {
		this.planGuestNumber = planGuestNumber;
	}

	public String getPlanBatch() {
		return planBatch;
	}

	public void setPlanBatch(String planBatch) {
		this.planBatch = planBatch;
	}

	public Integer getPlanOrderPrice() {
		return planOrderPrice;
	}

	public void setPlanOrderPrice(Integer planOrderPrice) {
		this.planOrderPrice = planOrderPrice;
	}

	@Override
	public String toString() {
		return "Customer_PlanVO [campOrderId=" + campOrderId + ", planId=" + planId + ", planGuestNumber="
				+ planGuestNumber + ", planBatch=" + planBatch + ", planOrderPrice=" + planOrderPrice + "]";
	}

}

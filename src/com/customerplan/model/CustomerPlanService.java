package com.customerplan.model;

import java.util.List;

public class CustomerPlanService {

	public CustomerPlanDAO_interface dao;

	public CustomerPlanService() {
		dao = new CustomerPlanDAO();
	}

	public CustomerPlanVO addCustomerPlan(Integer campOrderId, Integer planId, Integer planGuestNumber, String planBatch,
			Integer planOrderPrice) {

		CustomerPlanVO customerPlanVO = new CustomerPlanVO();

		customerPlanVO.setCampOrderId(campOrderId);
		customerPlanVO.setPlanId(planId);
		customerPlanVO.setPlanBatch(planBatch);
		customerPlanVO.setPlanGuestNumber(planGuestNumber);
		customerPlanVO.setPlanOrderPrice(planOrderPrice);
		dao.add(customerPlanVO);

		return customerPlanVO;
	}

	public CustomerPlanVO updateCustomerPlan(Integer planGuestNumber, String planBatch,
			Integer planOrderPrice, Integer campOrderId, Integer planId) {

		CustomerPlanVO customerPlanVO = new CustomerPlanVO();

		customerPlanVO.setPlanGuestNumber(planGuestNumber);
		customerPlanVO.setPlanBatch(planBatch);
		customerPlanVO.setPlanOrderPrice(planOrderPrice);
		customerPlanVO.setCampOrderId(campOrderId);
		customerPlanVO.setPlanId(planId);
		dao.update(customerPlanVO);

		return customerPlanVO;
	}

	public void deleteCustomerPlan(Integer campOrderId, Integer planId) {
		dao.delete(campOrderId, planId);
	}

	public CustomerPlanVO getOneCustomerPlan(Integer campOrderId, Integer planId) {
		return dao.findbyPrimaryKey(campOrderId, planId);
	}

	public List<CustomerPlanVO> getAll() {
		return dao.getAll();
	}
}

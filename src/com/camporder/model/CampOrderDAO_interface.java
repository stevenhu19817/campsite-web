package com.camporder.model;

import java.util.List;

import com.customerplan.model.CustomerPlanVO;

public interface CampOrderDAO_interface {
	void add(CampOrderVO campOrderVO);
	void update(CampOrderVO campOrderVO);
	void delete(Integer campOrderId);
	CampOrderVO findbyPrimaryKey(Integer campOrderId);
	List<CampOrderVO> findbyCampId(Integer campId);
	List<CampOrderVO> getAll();
	public void insertWithPlans(CampOrderVO campOrderVO , List<CustomerPlanVO> list) throws ClassNotFoundException;
	void updateOrder(CampOrderVO campOrderVO);
	void updateComment(String comment, Integer orderId);
}

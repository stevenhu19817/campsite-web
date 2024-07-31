package com.customerplan.model;

import java.sql.Connection;
import java.util.List;

public interface CustomerPlanDAO_interface {
	void add(CustomerPlanVO customerPlanVO);
	void update(CustomerPlanVO customerPlanVO);
	void delete(Integer campOrderId, Integer planId);
	CustomerPlanVO findbyPrimaryKey(Integer campOrderId, Integer planId);
	List<CustomerPlanVO> getAll();
	public void insert2 (CustomerPlanVO customerPlanVO , Connection con);
}

package com.customerplan.model;

import java.util.List;

public class CustomerPlanTest {
	public static void main(String[] args) {
		
		// 新增
		/*CustomerPlanVO customerPlanVO = new CustomerPlanVO();
		
		customerPlanVO.setCampOrderId(1002);
		customerPlanVO.setPlanId(201);
		customerPlanVO.setPlanGuestNumber(5);
		customerPlanVO.setPlanBatch("14:00-15:00");
		customerPlanVO.setPlanOrderPrice(500);
		
		CustomerPlanDAO ctpn = new CustomerPlanDAO();
		ctpn.add(customerPlanVO);*/
		
		//==============================================================
		
		// 修改
		/*CustomerPlanVO customerPlanVO = new CustomerPlanVO();
		
		customerPlanVO.setPlanGuestNumber(4);
		customerPlanVO.setPlanBatch("13:00-15:00");
		customerPlanVO.setPlanOrderPrice(500);
		customerPlanVO.setCampOrderId(1002);
		customerPlanVO.setPlanId(201);
	
		CustomerPlanDAO ctpn = new CustomerPlanDAO();
		ctpn.update(customerPlanVO);*/
		
		//==============================================================
		
		// 刪除
		/*CustomerPlanDAO_interface ctpn = new CustomerPlanDAO();
		ctpn.delete(1002, 201);*/
		
		//==============================================================
		
		// 查詢單筆
		/*CustomerPlanDAO_interface ctpn = new CustomerPlanDAO();
		CustomerPlanVO customerPlanVO = ctpn.findbyPrimaryKey(1001, 101);
		
		System.out.println(customerPlanVO);*/
		
		//==============================================================
		
		// 查詢多筆
		/*CustomerPlanDAO_interface ctpn = new CustomerPlanDAO();
		List<CustomerPlanVO> customerPlanList = ctpn.getAll();
		
		for(CustomerPlanVO customerPlanVO : customerPlanList) {
			System.out.println(customerPlanVO);
		}*/
	}
}

package com.plan.model;

import java.util.List;

public class PlanTest {
	public static void main(String args[]) {
		
		// 新增
		/*PlanVO planVO = new PlanVO();
		
		planVO.setPlanId(103);
		planVO.setCampId(5001);
		planVO.setPlanName("烤肉");
		planVO.setPlanGuestLimit(30);
		planVO.setPlanAgeLimit(0);
		planVO.setPlanPrice(1000);
		
		PlanDAO_interface plan = new PlanDAO();
		plan.add(planVO);*/
		
		//==============================================================
		
		// 修改
		/*PlanVO planVO = new PlanVO();
		
		planVO.setPlanName("溯溪");
		planVO.setPlanGuestLimit(12);
		planVO.setPlanAgeLimit(65);
		planVO.setPlanPrice(1500);
		planVO.setPlanId(103);
		
		PlanDAO_interface plan = new PlanDAO();
		plan.update(planVO);*/
		
		//==============================================================
		
		// 刪除
		/*PlanDAO_interface plan = new PlanDAO();
		plan.delete(103);*/
		
		//==============================================================
		
		// 查詢單筆
		/*PlanDAO_interface plan = new PlanDAO();
		PlanVO planVO = plan.findbyPrimaryKey(101);
		
		System.out.println(planVO);*/
		
		//==============================================================
		
		// 查詢多筆
		/*PlanDAO_interface plan = new PlanDAO();
		List<PlanVO> planList = plan.getAll();
		
		for(PlanVO planVO : planList) {
			System.out.println(planVO);
		}*/
	}
}

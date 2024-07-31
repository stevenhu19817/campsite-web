package com.plan.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlanService {

	private PlanDAO_interface dao;

	public PlanService() {
		dao = new PlanDAO();
	}

	public PlanVO addPlan(Integer campId, String planName, Integer planGuestLimit, Integer planAgeLimit,
			Integer planPrice, String planOutline) {

		PlanVO planVO = new PlanVO();

//		planVO.setPlanId(planId);
		planVO.setCampId(campId);
		planVO.setPlanName(planName);
		planVO.setPlanGuestLimit(planGuestLimit);
		planVO.setPlanAgeLimit(planAgeLimit);
		planVO.setPlanPrice(planPrice);
		planVO.setPlanOutline(planOutline);
		dao.add(planVO);

		return planVO;
	}

	public PlanVO updatePlan(Integer campId, String planName, Integer planGuestLimit, Integer planAgeLimit, Integer planPrice,String planOutline,
			Integer planId) {

		PlanVO planVO = new PlanVO();

		planVO.setCampId(campId);
		planVO.setPlanName(planName);
		planVO.setPlanGuestLimit(planGuestLimit);
		planVO.setPlanAgeLimit(planAgeLimit);
		planVO.setPlanPrice(planPrice);
		planVO.setPlanOutline(planOutline);
		planVO.setPlanId(planId);
		dao.update(planVO);

		return planVO;
	}

	public void deletePlan(Integer planId) {
		dao.delete(planId);
	}

	public ArrayList<PlanVO> getPlans(Integer campId) {
		return dao.findbyPrimaryKey(campId);
	}
	
	public PlanVO getOnePlanByPlanId(Integer planId) {
		List<PlanVO> list = dao.getAll();
		List<PlanVO> planVO = list.stream()
				.filter(e -> e.getPlanId().equals(planId))
				.collect(Collectors.toList());
		return planVO.get(0);
	}
	public List<PlanVO> getAll() {
		return dao.getAll();
	}

	
	
	public List<PlanVO> getByCampId(Integer campId) {
		return dao.findbyCampId(campId);
	}
	
	public void deletebyCampId(Integer campId) {
		dao.deletebyCampId(campId);
	};
	
	public ArrayList<PlanVO> getCampId(Integer campId) {
		return dao.getCampId(campId);
	}
}

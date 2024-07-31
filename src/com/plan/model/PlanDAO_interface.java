package com.plan.model;

import java.util.ArrayList;
import java.util.List;

public interface PlanDAO_interface {
	void add(PlanVO planVO);
	void update(PlanVO planVO);
	void delete(Integer planId);
	ArrayList<PlanVO> findbyPrimaryKey(Integer planId);
	List<PlanVO> getAll();
	
	
	List<PlanVO> findbyCampId(Integer campId);
	void deletebyCampId(Integer campId);
	ArrayList<PlanVO> getCampId(Integer campId);
}

package com.facilities.model;

import java.util.ArrayList;
import java.util.List;


public interface FacilitiesDAO_interface {
	
	void insert(FacilitiesVO facilitiesVO);
	void update(FacilitiesVO facilitiesVO);
	void delete(Integer facilitiesId);
    FacilitiesVO findbyPrimaryKey(Integer facilitiesId);
	List<FacilitiesVO> getAll();
	
	List<FacilitiesVO> findbyCampId(Integer campId);
	void deletebyCampId(Integer campId);
	FacilitiesVO getCampId(Integer campId);
	ArrayList<FacilitiesVO> getCamp(Integer campId);
	void facilitiesInsertWithCampId(FacilitiesVO facilitiesVO, java.sql.Connection con);
}

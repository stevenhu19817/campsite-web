package com.camprelease.model;

import java.util.ArrayList;
import java.util.List;

public interface CampReleaseDAO_interface {
	
	public void insert(CampReleaseVO campreleaseVO);
    public void update(CampReleaseVO campreleaseVO);
	public void delete(Integer campId);
    public CampReleaseVO findByPrimaryKey(Integer campId);
    public List<CampReleaseVO> getAll();
    ArrayList<CampReleaseVO> findbyPrimaryKey(Integer campId);
//    public void insertCamp(CampReleaseVO campreleaseVO, List<FacilitiesVO> facilitiesList);
}

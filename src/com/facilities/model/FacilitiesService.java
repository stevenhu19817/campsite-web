package com.facilities.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FacilitiesService {
	
	private FacilitiesDAO_interface dao;
	
	public FacilitiesService() {
		dao = new FacilitiesDAO();
	}
	
	public FacilitiesVO addFacilities(Integer campId, Integer bbq, Integer wifi, Integer nosmoke, Integer pets) {
		
		FacilitiesVO facilitiesVO = new FacilitiesVO();
		facilitiesVO.setCampId(campId);
		facilitiesVO.setBbq(bbq);
		facilitiesVO.setWifi(wifi);
		facilitiesVO.setNosmoke(nosmoke);
		facilitiesVO.setPets(pets);
		
		dao.insert(facilitiesVO);
		return facilitiesVO;
	}
	
	public FacilitiesVO updateFacilities(Integer campId, Integer bbq, Integer wifi, Integer nosmoke, Integer pets, Integer facilitiesId) {

		FacilitiesVO facilitiesVO = new FacilitiesVO();

		facilitiesVO.setCampId(campId);
		facilitiesVO.setBbq(bbq);
		facilitiesVO.setWifi(wifi);
		facilitiesVO.setNosmoke(nosmoke);
		facilitiesVO.setPets(pets);
		facilitiesVO.setFacilitiesId(facilitiesId);
		dao.update(facilitiesVO);

		return facilitiesVO;
	}
	public void deleteFacilities(Integer facilitiesId) {
		dao.delete(facilitiesId);
	}
	
	public FacilitiesVO getOneFacilities(Integer facilitiesId) {
		return dao.findbyPrimaryKey(facilitiesId);
	}
	
	public List<FacilitiesVO> getAll(){
		return dao.getAll();
	};
	
	public List<FacilitiesVO> getByCampId(Integer campId) {
		return dao.findbyCampId(campId);
	}
	
	public void deletebyCampId(Integer campId) {
		dao.deletebyCampId(campId);
	};
	
	public FacilitiesVO getCampId(Integer campId) {
		return dao.getCampId(campId);
	}
	
	public ArrayList<FacilitiesVO> getCamp(Integer campId) {
		return dao.getCamp(campId);
	}
	public FacilitiesVO getOneFacByFacilitiesId(Integer facilitiesId) {
		List<FacilitiesVO> list = dao.getAll();
		List<FacilitiesVO> facilitiesVO = list.stream()
				.filter(e -> e.getFacilitiesId().equals(facilitiesId))
				.collect(Collectors.toList());
		return facilitiesVO.get(0);
	}
}

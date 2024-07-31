package com.camprelease.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CampReleaseService {
	
	private CampReleaseDAO_interface dao;

	public CampReleaseService() {
		// TODO Auto-generated constructor stub
		dao = new CampReleaseDAO();
	}

	public CampReleaseVO addCampRelease(Integer memberId, String campName, String location, Double latitude, Double longtitude,
			String campDescription, Integer campPrice, Integer campLimit, Timestamp listedTime, byte[] picture1, byte[] picture2, byte[] picture3, byte[] picture4, byte[] picture5) {

		CampReleaseVO campreleaseVO = new CampReleaseVO();

		campreleaseVO.setCampName(campName);
		campreleaseVO.setLocation(location);
		campreleaseVO.setLatitude(latitude);
		campreleaseVO.setLongtitude(longtitude);
		campreleaseVO.setCampDescription(campDescription);
		campreleaseVO.setCampPrice(campPrice);
		campreleaseVO.setCampLimit(campLimit);
		campreleaseVO.setListedTime(listedTime);
		campreleaseVO.setPicture1(picture1);
		campreleaseVO.setPicture2(picture2);
		campreleaseVO.setPicture3(picture3);
		campreleaseVO.setPicture4(picture4);
		campreleaseVO.setPicture5(picture5);
		campreleaseVO.setMemberId(memberId);
//		campreleaseVO.setOpenTime(openTime);
//		campreleaseVO.setCloseTime(closeTime);
		

		dao.insert(campreleaseVO);
		
		return campreleaseVO;
	}

	public CampReleaseVO updateCampRelease(Integer memberId, String campName, String location, Double latitude, Double longtitude,
			String campDescription, Integer campPrice, Integer campLimit, Timestamp listedTime, byte[] picture1, byte[] picture2, byte[] picture3, byte[] picture4, byte[] picture5, Integer campId) {

		CampReleaseVO campreleaseVO = new CampReleaseVO();

		campreleaseVO.setMemberId(memberId);
		campreleaseVO.setCampName(campName);
		campreleaseVO.setLocation(location);
		campreleaseVO.setLatitude(latitude);
		campreleaseVO.setLongtitude(longtitude);
		campreleaseVO.setCampDescription(campDescription);
		campreleaseVO.setCampPrice(campPrice);
		campreleaseVO.setCampLimit(campLimit);
		campreleaseVO.setListedTime(listedTime);
		campreleaseVO.setPicture1(picture1);
		campreleaseVO.setPicture2(picture2);
		campreleaseVO.setPicture3(picture3);
		campreleaseVO.setPicture4(picture4);
		campreleaseVO.setPicture5(picture5);
//		campreleaseVO.setOpenTime(openTime);
//		campreleaseVO.setCloseTime(closeTime);
		campreleaseVO.setCampId(campId);
		
		dao.update(campreleaseVO);
		
		return campreleaseVO;
	}


	public void deleteCampRelease(Integer campId) {
		dao.delete(campId);
	}

	public CampReleaseVO getOneCampRelease(Integer campId) {
		return dao.findByPrimaryKey(campId);
	}
	
	public List<CampReleaseVO> getAll() {
		return dao.getAll();
	}
	
	public ArrayList<CampReleaseVO> getCamp(Integer campId) {
		return dao.findbyPrimaryKey(campId);
	}
	
	
	public CampReleaseVO getOneCamprelease(Integer campId) {
		List<CampReleaseVO> list = dao.getAll();
		List<CampReleaseVO> campReleaseVO = list.stream()
				.filter(e -> e.getCampId().equals(campId))
				.collect(Collectors.toList());
		return campReleaseVO.get(0);
	}
	
	public List<CampReleaseVO> getAllforMember(Integer memberId){
		List<CampReleaseVO> list = dao.getAll();
		List<CampReleaseVO> newList = list.stream()
				.filter(e -> e.getMemberId().equals(memberId))
				.collect(Collectors.toList());
		return newList;
	}
}

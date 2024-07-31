package com.campAlert.model;

import com.campAlert.model.*;
import com.campsite.model.CampsiteDAO;
import com.campsite.model.CampsiteDAO_Interface;
import com.campsite.model.CampsiteVO;

import java.util.List;

public class CampAlertService {
	private CampAlertDao_interface dao;
	private CampsiteDAO_Interface dao1;
		
	

	public CampAlertService() {
		dao = new CampAlertDAO();
		dao1=new CampsiteDAO();
	}

	public CampAlertVO insertcCampAlertVO(Integer memberId, Integer campId, String report_Time,
			String content, byte[] picture1, byte[] picture2, byte[] picture3, Integer report_Status,
			Integer handeler) {

		CampAlertVO campalert = new CampAlertVO();
		campalert.setMemberId(memberId);
		campalert.setCampId(campId);
		campalert.setReportTime(report_Time);
		campalert.setContent(content);
		campalert.setPicture1(picture1);
		campalert.setPicture2(picture2);
		campalert.setPicture3(picture3);
		campalert.setReportStatus(report_Status);
		campalert.setHandeler(handeler);
		dao.insert(campalert);

		return campalert;
	}

	public CampAlertVO updateCampAlertVO(Integer alertId, Integer memberId, Integer campId, String report_Time,
			String content, byte[] picture1, byte[] picture2, byte[] picture3, Integer report_Status,
			Integer handeler) {
		CampAlertVO campalert = new CampAlertVO();
		campalert.setAlertId(alertId);
		campalert.setMemberId(memberId);
		campalert.setCampId(campId);
		campalert.setReportTime(report_Time);
		campalert.setContent(content);
		campalert.setPicture1(picture1);
		campalert.setPicture2(picture2);
		campalert.setPicture3(picture3);
		campalert.setReportStatus(report_Status);
		campalert.setHandeler(handeler);
		dao.update(campalert);

		return campalert;
	}

	public void deleteCampAlertDAO(Integer alterId) {
		dao.delete(alterId);
	}

	public CampAlertVO getOneEmp(Integer alterId) {
		return dao.findByPrimaryKey(alterId);
	}

	public List<CampAlertVO> getAll() {
		return dao.getALL();
	}

	public CampAlertVO updateStatus(Integer alertId, Integer campId) {
		CampsiteVO campsiteVO = dao1.findbyPrimaryKey(campId);
		dao1.updateForOne(campsiteVO);
		CampAlertVO campAlertVO = dao.findByPrimaryKey(alertId);
		dao.updateStatus(campAlertVO);
		return campAlertVO;
	}

}
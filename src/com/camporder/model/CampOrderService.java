package com.camporder.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import com.camprelease.model.CampReleaseVO;
import com.customerplan.model.CustomerPlanVO;

public class CampOrderService {

	private CampOrderDAO_interface dao;

	public CampOrderService() {
		dao = new CampOrderDAO();
	}

	public CampOrderVO addCampOrder(Integer campId, Integer memberId, Integer guestNumber, Date checkInDate,
			Date checkOutDate, Timestamp orderDate, Timestamp paymentDeadline, String orderStatus, Integer orderTotal,
			String comment, byte[] picture1, byte[] picture2, byte[] picture3) {

		CampOrderVO campOrderVO = new CampOrderVO();

		campOrderVO.setCampId(campId);
		campOrderVO.setMemberId(memberId);
		campOrderVO.setGuestNumber(guestNumber);
		campOrderVO.setCheckInDate(checkInDate);
		campOrderVO.setCheckOutDate(checkOutDate);
		campOrderVO.setOrderDate(orderDate);
		campOrderVO.setPaymentDeadline(paymentDeadline);
		campOrderVO.setOrderStatus(orderStatus);
		campOrderVO.setOrderTotal(orderTotal);
		campOrderVO.setComment(comment);
		campOrderVO.setPicture1(picture1);
		campOrderVO.setPicture2(picture2);
		campOrderVO.setPicture3(picture3);
		dao.add(campOrderVO);

		return campOrderVO;
	}

	public CampOrderVO updateCampOrder(Integer memberId, Integer guestNumber, Date checkInDate, Date checkOutDate,
			Timestamp orderDate, Timestamp paymentDeadline, String orderStatus, Integer orderTotal, String comment,
			byte[] picture1, byte[] picture2, byte[] picture3, Integer campOrderId) {

		CampOrderVO campOrderVO = new CampOrderVO();

		campOrderVO.setMemberId(memberId);
		campOrderVO.setGuestNumber(guestNumber);
		campOrderVO.setCheckInDate(checkInDate);
		campOrderVO.setCheckOutDate(checkOutDate);
		campOrderVO.setOrderDate(orderDate);
		campOrderVO.setPaymentDeadline(paymentDeadline);
		campOrderVO.setOrderStatus(orderStatus);
		campOrderVO.setOrderTotal(orderTotal);
		campOrderVO.setComment(comment);
		campOrderVO.setPicture1(picture1);
		campOrderVO.setPicture2(picture2);
		campOrderVO.setPicture3(picture3);
		campOrderVO.setCampOrderId(campOrderId);
		dao.update(campOrderVO);

		return campOrderVO;
	}

	public void deleteCampOrder(Integer campOrderId) {
		dao.delete(campOrderId);
	}

	public CampOrderVO getOneCampOrder(Integer campOrderId) {
		return dao.findbyPrimaryKey(campOrderId);
	}

	public List<CampOrderVO> getOneCampsiteCampOrderVO(Integer campId) {
		return dao.findbyCampId(campId);
	}
	
	public List<CampOrderVO> getAll() {
		return dao.getAll();
	}
	
	public List<CampOrderVO> getByMemberId(Integer memberId) {
		List<CampOrderVO> list = dao.getAll(); 
		List<CampOrderVO> newList = list.stream()
				.filter(e -> e.getMemberId().equals(memberId))
				.collect(Collectors.toList());
		return newList;
	}

	public CampOrderVO insertWithPlan(Integer campId, Integer memberId, Integer guestNumber, Date checkInDate,
			Date checkOutDate, Timestamp orderDate, Timestamp paymentDeadline, String orderStatus, Integer orderTotal,
			String comment, byte[] picture1, byte[] picture2, byte[] picture3, List<CustomerPlanVO> list) {
		CampOrderVO campOrderVO = new CampOrderVO();
		orderStatus = "0";
		campOrderVO.setCampId(campId);
		campOrderVO.setMemberId(memberId);
		campOrderVO.setGuestNumber(guestNumber);
		campOrderVO.setCheckInDate(checkInDate);
		campOrderVO.setCheckOutDate(checkOutDate);
		campOrderVO.setOrderDate(orderDate);
		campOrderVO.setPaymentDeadline(paymentDeadline);
		campOrderVO.setOrderStatus(orderStatus);
		campOrderVO.setOrderTotal(orderTotal);
		campOrderVO.setComment(comment);
		campOrderVO.setPicture1(picture1);
		campOrderVO.setPicture2(picture2);
		campOrderVO.setPicture3(picture3);
		try {
			dao.insertWithPlans(campOrderVO, list);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return campOrderVO;
	}
	
	public CampOrderVO updateOrder(Integer campId,Integer memberId, Integer guestNumber, Date checkInDate,
			Date checkOutDate, Timestamp orderDate, Timestamp paymentDeadline, String orderStatus, Integer orderTotal,
			Integer campOrderId) {
		
		CampOrderVO campOrderVO = new CampOrderVO();
		campOrderVO.setCampId(campId);
		campOrderVO.setMemberId(memberId);
		campOrderVO.setGuestNumber(guestNumber);
		campOrderVO.setCheckInDate(checkInDate);
		campOrderVO.setCheckOutDate(checkOutDate);
		campOrderVO.setOrderDate(orderDate);
		campOrderVO.setPaymentDeadline(paymentDeadline);
		campOrderVO.setOrderStatus(orderStatus);
		campOrderVO.setOrderTotal(orderTotal);
		campOrderVO.setCampOrderId(campOrderId);
		dao.updateOrder(campOrderVO);
		
		return campOrderVO;
	}
	
	public CampOrderVO addComment(String comment, Integer orderId) {
		dao.updateComment(comment, orderId);
		return dao.findbyPrimaryKey(orderId);
	}
	
}

package com.camporder.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class CampOrderTest {
	public static void main(String args[]) throws IOException {
		
		// 新增
		/*CampOrderVO campOrderVO = new CampOrderVO();

		campOrderVO.setCampId(5001);
		campOrderVO.setMemberId(4);
		campOrderVO.setGuestNumber(5);
		
		Date checkInDate = getDate("2021-10-10");
		campOrderVO.setCheckInDate(checkInDate);
		
		Date checkOutDate = getDate("2021-10-11");
		campOrderVO.setCheckOutDate(checkOutDate);

		Timestamp orderDate = getTimestamp("2021-12-19 20:35:46");
		campOrderVO.setOrderDate(orderDate);
		
		Timestamp paymentDeadline = getTimestamp("2021-12-22 20:35:46");
		campOrderVO.setPaymentDeadline(paymentDeadline);
		
		campOrderVO.setOrderStatus("已付款");
		campOrderVO.setOrderTotal(5000);
		campOrderVO.setComment("露營好讚4");

		byte[] picPicture1 = getPictureByteArray("items/picture1.jpg");

		campOrderVO.setPicture1(picPicture1);

		CampOrderDAO_interface cpod = new CampOrderDAO();
		cpod.add(campOrderVO);*/
		
		//==============================================================
		
		// 修改
		/*CampOrderVO campOrderVO = new CampOrderVO();
		
		campOrderVO.setMemberId(4);
		campOrderVO.setGuestNumber(7);
		
		Date checkInDate = getDate("2021-10-13");
		campOrderVO.setCheckInDate(checkInDate);
		
		Date checkOutDate = getDate("2021-10-14");
		campOrderVO.setCheckOutDate(checkInDate);

		Timestamp orderDate = getTimestamp("2021-09-19 20:35:46");
		campOrderVO.setOrderDate(orderDate);
		
		Timestamp paymentDeadline = getTimestamp("2021-09-22 20:35:46");
		campOrderVO.setPaymentDeadline(paymentDeadline);
		
		campOrderVO.setOrderStatus("未付款");
		campOrderVO.setOrderTotal(5250);
		campOrderVO.setComment("露營好讚4");

		byte[] picPicture1 = getPictureByteArray("items/picture2.jpg");

		campOrderVO.setPicture1(picPicture1);
		
		campOrderVO.setCampOrderId(1004);
		
		CampOrderDAO_interface cpod = new CampOrderDAO();
		cpod.update(campOrderVO);*/
		
		//==============================================================
		
		// 刪除
		/*CampOrderDAO_interface cpod = new CampOrderDAO();
		cpod.delete(1004);*/
		
		//==============================================================
		
		// 查詢單筆
		/*CampOrderDAO_interface cpod = new CampOrderDAO();
		CampOrderVO campOrderVO = cpod.findbyPrimaryKey(1002);
		
		System.out.println(campOrderVO);*/
		
		//==============================================================
		
		// 查詢多筆
		/*CampOrderDAO_interface cpod = new CampOrderDAO();
		List<CampOrderVO> campOrderList = cpod.getAll();
		
		for (CampOrderVO campOrderVO : campOrderList) {
			System.out.println(campOrderVO);
		}*/
	}
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	
	public static Date getDate(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		return sqlDate;
	}
	
	public static Timestamp getTimestamp(String tsStr) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		try {
			ts = Timestamp.valueOf(tsStr);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return ts;
	}
	
}

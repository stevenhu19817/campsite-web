package com.campsitetentstatus.model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class CampsiteTentStatusTest {
	public static void main(String[] args) {
		
		// 新增
//		CampsiteTentStatusVO campsiteTentStatusVO = new CampsiteTentStatusVO();
//		
//		campsiteTentStatusVO.setCampId(5002);
//		
//		Date campOpeningTime = getDate("2021-09-07");
//		campsiteTentStatusVO.setCampOpeningTime(campOpeningTime);
//		
//		campsiteTentStatusVO.setEmptyCampLeft(60);
//		
//		CampsiteTentStatusDAO_interface cpts = new CampsiteTentStatusDAO();
//		cpts.add(campsiteTentStatusVO);
		
		//==============================================================
		
		// 修改
		/*CampsiteTentStatusVO campsiteTentStatusVO = new CampsiteTentStatusVO();
		
		campsiteTentStatusVO.setEmptyCampLeft(44);
		campsiteTentStatusVO.setCampId(5002);
		
		Date campOpeningTime = getDate("2021-09-07");
		campsiteTentStatusVO.setCampOpeningTime(campOpeningTime);
		
		CampsiteTentStatusDAO_interface cpts = new CampsiteTentStatusDAO();
		cpts.update(campsiteTentStatusVO);*/
		
		//==============================================================
		
		// 刪除
		/*CampsiteTentStatusVO campsiteTentStatusVO = new CampsiteTentStatusVO();

		Date campOpeningTime = getDate("2021-09-07");
		
		CampsiteTentStatusDAO_interface cpts = new CampsiteTentStatusDAO();
		cpts.delete(5002, campOpeningTime);*/
		
		//==============================================================
		
		// 查詢單筆
		CampsiteTentStatusDAO_interface cpts = new CampsiteTentStatusDAO();
		Date campOpeningTime = getDate("2021-09-07");
		CampsiteTentStatusVO campsiteTentStatusVO = cpts.findbyPrimaryKey(5001, campOpeningTime);
		
		System.out.println(campsiteTentStatusVO);
		
		//==============================================================
		
		// 查詢多筆
		/*CampsiteTentStatusDAO_interface cpts = new CampsiteTentStatusDAO();
		List<CampsiteTentStatusVO> campsiteTentStatusList = cpts.getAll();
		
		for(CampsiteTentStatusVO campsiteTentStatusVO : campsiteTentStatusList) {
			System.out.println(campsiteTentStatusVO);
		}*/
		
		//==============================================================
		
		// 查詢多筆
		/*CampsiteTentStatusDAO_interface cpts = new CampsiteTentStatusDAO();
		List<CampsiteTentStatusVO> campsiteTentStatusList = cpts.getAllOfOne(5001);

		for(CampsiteTentStatusVO campsiteTentStatusVO : campsiteTentStatusList) {
			System.out.println(campsiteTentStatusVO);
		}*/
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
}

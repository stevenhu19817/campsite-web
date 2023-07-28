package com.campsite.model;

import java.util.List;
import java.sql.Timestamp;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class CampsiteTest {
	public static void main(String args[]) throws IOException {
		
		// 新增
		/*CampsiteVO campsiteVO = new CampsiteVO();
		
		campsiteVO.setMemberId(4);
		campsiteVO.setCampName("Tibame");
		campsiteVO.setLocation("南京復興");
		campsiteVO.setLatitude(24.444);
		campsiteVO.setLongtitude(120.444);
		campsiteVO.setCampDescription("Tibame");
		campsiteVO.setCampPrice(4444);
		campsiteVO.setCampLimit(444);
		
		Timestamp listedTime = getTimestamp("2021-09-11 01:02:23");
		campsiteVO.setListedTime(listedTime);
		
		campsiteVO.setSiteState(1);
		campsiteVO.setLovedCount(444);
		campsiteVO.setReportedCount(0);
	
		byte[] picLicense = getPictureByteArray("items/license.jpg");
		campsiteVO.setCampLicense(picLicense);
		
		byte[] picPicture1 = getPictureByteArray("items/picture1.jpg");
		campsiteVO.setPicture1(picPicture1);
		
		CampsiteDAO_Interface cpst = new CampsiteDAO();
		cpst.add(campsiteVO);*/
		
		//==============================================================
		
		// 修改
		/*CampsiteVO campsiteVO = new CampsiteVO();
		
		campsiteVO.setMemberId(1);
		campsiteVO.setCampName("Tibame1");
		campsiteVO.setLocation("南京復興");
		campsiteVO.setLatitude(24.4445);
		campsiteVO.setLongtitude(120.4445);
		campsiteVO.setCampDescription("Tibame1");
		campsiteVO.setCampPrice(44445);
		campsiteVO.setCampLimit(4445);
		
		Timestamp listedTime = getTimestamp("2021-09-12 01:02:23");
		campsiteVO.setListedTime(listedTime);
		
		campsiteVO.setSiteState(15);
		campsiteVO.setLovedCount(4445);
		campsiteVO.setReportedCount(5);

		byte[] picLicense = getPictureByteArray("items/license.jpg");
		campsiteVO.setCampLicense(picLicense);
		
		byte[] picPicture1 = getPictureByteArray("items/picture2.jpg");	
		campsiteVO.setPicture1(picPicture1);
		
		campsiteVO.setCampId(5005);
		
		CampsiteDAO_Interface cpst = new CampsiteDAO();
		cpst.update(campsiteVO);*/
		
		//==============================================================
		
		// 刪除
		/*CampsiteDAO_Interface cpst = new CampsiteDAO();
		cpst.delete(5004);*/
		
		//==============================================================
		
		// 查詢單筆
		/*CampsiteDAO_Interface cpst = new CampsiteDAO();
		CampsiteVO campsiteVO = cpst.findbyPrimaryKey(5003);
		
		System.out.println(campsiteVO);*/

		//==============================================================
		
		// 查詢多筆
		/*CampsiteDAO_Interface cpst = new CampsiteDAO();
		List<CampsiteVO> campsiteList = cpst.getAll();
				  
		for (CampsiteVO campsiteVO : campsiteList) {
			System.out.println(campsiteVO); 
		}*/
		
		//==============================================================
		
		// 查詢多筆
		CampsiteDAO_Interface cpst = new CampsiteDAO();
		List<CampsiteVO> campsiteList = cpst.getSearchCampsite("Tibame");
				  
		for (CampsiteVO campsiteVO : campsiteList) {
			System.out.println(campsiteVO); 
		}
	}
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
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

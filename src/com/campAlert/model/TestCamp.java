package com.campAlert.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import com.campAlert.model.*;
public class TestCamp {
public static void main(String[] args) throws IOException {

	CampAlertDao_interface dao = new CampAlertDAO();
	String s1[] = {"6","7","8"};
	//新增
	String path = "C:\\Users\\Tibame_T14\\Desktop\\images";
	File f = new File(path);
	String s[] = f.list();
	
	int k = 0;
	
	CampAlertVO vo = new CampAlertVO();
	vo.setMemberId(1);
	vo.setCampId(5001);
	vo.setReportTime("20211001");
	vo.setContent("檢舉內容");
	vo.setReportStatus(0);
	vo.setHandeler(1002);
for (int i = 0; i < s.length; i++) {
	vo.setPicture1(getPictureByteArray(path + "\\" + s[7]));
	vo.setPicture2(getPictureByteArray(path + "\\" + s[4]));
	vo.setPicture3(getPictureByteArray(path + "\\" + s[6]));
		}
	dao.insert(vo);
	k++;
	}


	// 修改
//	Camp_Alert camp_alert1 = new Camp_Alert();
//	byte[] PICTURE1 = getPictureByteArray("C:\\Users\\Tibame_T14\\Desktop\\images\\200x1280.png");
//	byte[] PICTURE2 = getPictureByteArray("C:\\Users\\Tibame_T14\\Desktop\\images\\200x1280.png");
//	byte[] PICTURE3 = getPictureByteArray("C:\\Users\\Tibame_T14\\Desktop\\images\\200x1280.png");
////
//	camp_alert1.setALERT_ID(1);
//	camp_alert1.setMEMBER_ID(123456213);
//	camp_alert1.setCAMP_ID(new Integer(29));
//	camp_alert1.setREPORT_TIME("19960329");
//	camp_alert1.setCONTENT("檢舉內容123");
//	camp_alert1.setPICTURE1(PICTURE1);
//	camp_alert1.setPICTURE2(PICTURE2);
//	camp_alert1.setPICTURE3(PICTURE3);
//	camp_alert1.setREPORT_STATUS(1);
//	camp_alert1.setHANDELER(1);
////
//	dao.update(camp_alert1);
	
	// 刪除
//  dao.delete(2);
	
	// 查詢		
//	Camp_Alert camp_alert = dao.findByPrimaryKey(2);
//	System.out.print(camp_alert.getALERT_ID() + ",");
//	System.out.print(camp_alert.getMEMBER_ID() + ",");
//	System.out.print(camp_alert.getCAMP_ID() + ",");
//	System.out.print(camp_alert.getREPORT_TIME() + ",");
//	System.out.print(camp_alert.getCONTENT() + ",");
//	System.out.print(camp_alert.getREPORT_STATUS() + ",");
//	System.out.print(camp_alert.getHANDELER() + ",");
//	System.out.println("---------------------");
	
						

//}



public static byte[] getPictureByteArray(String path) throws IOException {
	FileInputStream fis = new FileInputStream(path);
	byte[] buffer = new byte[fis.available()];
	fis.read(buffer);
	fis.close();
	return buffer;
}
}

package com.campsite.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.campsite.model.CampsiteService;
import com.campsite.model.CampsiteVO;
import com.campsite.model.StringToSQLDate;
import com.campsitetentstatus.model.CampsiteTentStatusService;

@MultipartConfig
public class CampsiteServlet  extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("campId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入營地編號");
				}

				Integer campId = null;
				try {
					campId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("營地編號格式不正確");
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/campsite/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				CampsiteService campsiteSvc = new CampsiteService();
				CampsiteVO campsiteVO = campsiteSvc.getOneCampsite(campId);
				if (campsiteVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/campsite/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("campsiteVO", campsiteVO); // 資料庫取出的campsiteVO物件,存入req
				String url = "/campsite/listOneCampsite.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneCampsite.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/campsite/select_page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("listCampsites_ByCompositeQuery".equals(action)) { // 來自search_campsite.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				/*************************** 1.將輸入資料轉為Map **********************************/
				// 採用Map<String,String[]> getParameterMap()的方法
				// 注意:an immutable java.util.Map
				Map<String, String[]> map = req.getParameterMap();
				Set<String> keys = map.keySet();
				for (String key : keys) {
					String value = map.get(key)[0];
					System.out.println(key + " : " + value);
				}
				/*************************** 2.開始複合查詢 ***************************************/
				CampsiteService campsiteSvc = new CampsiteService();
				List<CampsiteVO> list = campsiteSvc.getAll(map);
				System.out.println("Servlet list= " + list);
				
				String str = req.getParameter("EMPTY_CAMP_LEFT");
				Integer customerNum = null;
				try {
					customerNum = new Integer(str);
				} catch (Exception e) {
					customerNum = 0;
				}
				
				String value = req.getParameter("CAMP_OPENING_TIME");
				java.sql.Date strDate;
				java.sql.Date endDate;
				try {
					String strDateString = value.substring(0, 10);
					String endDateString = value.substring(13);
					strDate = StringToSQLDate.convertToSQLDate(strDateString);
					endDate = StringToSQLDate.convertToSQLDate(endDateString);
				} catch (Exception e) {
					strDate = null;
					endDate = null;
				}
				
				CampsiteTentStatusService campsiteTentStatusSvc = new CampsiteTentStatusService();
				List<CampsiteVO> filterList = new ArrayList<CampsiteVO>();
				
				for (CampsiteVO campsiteVO : list) {
					if(campsiteVO.getSiteState() == 1) {
						if (strDate != null && endDate != null) {
							System.out.println("first");
							if (campsiteTentStatusSvc.isTentAvailiblewithGuestNumberandTimeRange(campsiteVO.getCampId(), customerNum, strDate, endDate)) {
								filterList.add(campsiteVO);
							}
						}  else if (!"".equals(str)) {
							System.out.println("second");
							if(campsiteTentStatusSvc.getUnavailibleDatewithGuestNumberOnly(campsiteVO.getCampId(), customerNum).isEmpty() && campsiteSvc.getOneCampsite(campsiteVO.getCampId()).getCampLimit() >= customerNum) {
								filterList.add(campsiteVO);
							}
						} if("".equals(str) && "".equals(value)) {
							filterList.add(campsiteVO);
						}
					}
				}
				System.out.println("Servlet filterList " + filterList);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listExist", "empty");
				req.setAttribute("campsiteList", filterList); // 資料庫取出的filterList物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/campsite/search_campsite.jsp"); // 成功轉交search_campsite.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/campsite/search_campsite.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getReserveCampsite".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("campId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入營地編號");
				}

				Integer campId = null;
				try {
					campId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("營地編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/campsite/search_campsite.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				CampsiteService campsiteSvc = new CampsiteService();
				CampsiteVO campsiteVO = campsiteSvc.getOneCampsite(campId);
				if (campsiteVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/campsite/search_campsite.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				List<Integer> picturesNum = new ArrayList<>();
				if (campsiteVO.getPicture2() != null) {
					picturesNum.add(2);
				}
				if (campsiteVO.getPicture3() != null) {
					picturesNum.add(3);
				}
				if (campsiteVO.getPicture4() != null) {
					picturesNum.add(4);
				}
				if (campsiteVO.getPicture5() != null) {
					picturesNum.add(5);
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("campsiteVO", campsiteVO); // 資料庫取出的campsiteVO物件,存入req
				req.setAttribute("picturesNum", picturesNum);
				String url = "/campsite/reserve_campsite.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneCampsite.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/campsite/search_campsite.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
				Integer memberId = new Integer(req.getParameter("memberId").trim());

				String campName = req.getParameter("campName");
				String campNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,15}$";
				if (campName == null || campName.trim().length() == 0) {
					errorMsgs.add("營地名稱: 請勿空白");
				} else if (!campName.trim().matches(campNameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("營地名稱: 只能是中、英文字母、數字, 且長度必需在2到15之間");
				}

				String location = req.getParameter("location");
				String locationReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{10,25}$";
				if (location == null || location.trim().length() == 0) {
					errorMsgs.add("地址: 請勿空白");
				} else if (!location.trim().matches(locationReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("地址: 只能是中、英文字母、數字, 且長度必需在10到25之間");
				}

				Double latitude = null;
				try {
					latitude = new Double(req.getParameter("latitude").trim());
				} catch (NumberFormatException e) {
					latitude = 0.0;
					errorMsgs.add("經度請填數字.");
				}

				Double longtitude = null;
				try {
					longtitude = new Double(req.getParameter("longtitude").trim());
				} catch (NumberFormatException e) {
					longtitude = 0.0;
					errorMsgs.add("緯度請填數字.");
				}

				String campDescription = req.getParameter("campDescription").trim();
				if (campDescription == null || campDescription.trim().length() == 0) {
					errorMsgs.add("營地說明請勿空白");
				}

				Integer campPrice = null;
				try {
					campPrice = new Integer(req.getParameter("campPrice").trim());
				} catch (NumberFormatException e) {
					campPrice = 0;
					errorMsgs.add("營地價格請填正整數.");
				}

				Integer campLimit = null;
				try {
					campLimit = new Integer(req.getParameter("campLimit").trim());
				} catch (NumberFormatException e) {
					campLimit = 0;
					errorMsgs.add("人數上限請填正整數.");
				}

				java.sql.Timestamp listedTime = null;
				try {
					listedTime = java.sql.Timestamp.valueOf(req.getParameter("listedTime").trim());
				} catch (IllegalArgumentException e) {
					listedTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Integer siteState = new Integer(req.getParameter("siteState").trim());

				Integer lovedCount = null;
				try {
					lovedCount = new Integer(req.getParameter("lovedCount").trim());
				} catch (NumberFormatException e) {
					lovedCount = 0;
					errorMsgs.add("喜歡人數請填正整數.");
				}

				Integer reportedCount = null;
				try {
					reportedCount = new Integer(req.getParameter("reportedCount").trim());
				} catch (NumberFormatException e) {
					reportedCount = 0;
					errorMsgs.add("檢舉人數請填正整數.");
				}

				InputStream in = req.getPart("campLicense").getInputStream();
				byte[] campLicense = null;
				if (in.available() != 0) {
					campLicense = new byte[in.available()];
					in.read(campLicense);
					in.close();
				} else {
					errorMsgs.add("請上傳營業執照");
				}

				InputStream in1 = req.getPart("picture1").getInputStream();
				byte[] picture1 = null;
				if (in1.available() != 0) {
					picture1 = new byte[in1.available()];
					in1.read(picture1);
					in1.close();
				} else {
					picture1 = null;
				}

				byte[] picture2 = null;
				byte[] picture3 = null;
				byte[] picture4 = null;
				byte[] picture5 = null;

				CampsiteVO campsiteVO = new CampsiteVO();
				campsiteVO.setMemberId(memberId);
				campsiteVO.setCampName(campName);
				campsiteVO.setLocation(location);
				campsiteVO.setLatitude(latitude);
				campsiteVO.setLongtitude(longtitude);
				campsiteVO.setCampDescription(campDescription);
				campsiteVO.setCampPrice(campPrice);
				campsiteVO.setCampLimit(campLimit);
				campsiteVO.setListedTime(listedTime);
				campsiteVO.setSiteState(siteState);
				campsiteVO.setLovedCount(lovedCount);
				campsiteVO.setReportedCount(reportedCount);
				campsiteVO.setCampLicense(campLicense); // :(
				campsiteVO.setPicture1(picture1); // :(

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("campsiteVO", campsiteVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/campsite/addCampsite.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 ***************************************/
				CampsiteService campsiteSvc = new CampsiteService();
				campsiteVO = campsiteSvc.addCampsite(memberId, campName, location, latitude, longtitude,
						campDescription, campPrice, campLimit, listedTime, siteState, lovedCount, reportedCount,
						campLicense, picture1, picture2, picture3, picture4, picture5);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/campsite/listAllCampsite_byDAO.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/campsite/addCampsite.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllCampsite.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer campId = new Integer(req.getParameter("campId"));

				/*************************** 2.開始查詢資料 ****************************************/
				CampsiteService campsiteSvc = new CampsiteService();
				CampsiteVO campsiteVO = campsiteSvc.getOneCampsite(campId);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("campsiteVO", campsiteVO); // 資料庫取出的campsiteVO物件,存入req
				String url = "/campsite/update_campsite_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_campsite_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/campsite/listAllCampsite_byDAO.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_campsite_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer campId = new Integer(req.getParameter("campId").trim());

				Integer memberId = new Integer(req.getParameter("memberId").trim());

				String campName = req.getParameter("campName");
				String campNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{2,15}$";
				if (campName == null || campName.trim().length() == 0) {
					errorMsgs.add("營地名稱: 請勿空白");
				} else if (!campName.trim().matches(campNameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("營地名稱: 只能是中、英文字母、數字, 且長度必需在2到15之間");
				}

				String location = req.getParameter("location");
				String locationReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{10,25}$";
				if (location == null || location.trim().length() == 0) {
					errorMsgs.add("地址: 請勿空白");
				} else if (!location.trim().matches(locationReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("地址: 只能是中、英文字母、數字, 且長度必需在10到25之間");
				}

				Double latitude = null;
				try {
					latitude = new Double(req.getParameter("latitude").trim());
				} catch (NumberFormatException e) {
					latitude = 0.0;
					errorMsgs.add("經度請填數字.");
				}

				Double longtitude = null;
				try {
					longtitude = new Double(req.getParameter("longtitude").trim());
				} catch (NumberFormatException e) {
					longtitude = 0.0;
					errorMsgs.add("緯度請填數字.");
				}

				String campDescription = req.getParameter("campDescription").trim();
				if (campDescription == null || campDescription.trim().length() == 0) {
					errorMsgs.add("營地說明請勿空白");
				}

				Integer campPrice = null;
				try {
					campPrice = new Integer(req.getParameter("campPrice").trim());
				} catch (NumberFormatException e) {
					campPrice = 0;
					errorMsgs.add("營地價格請填正整數.");
				}

				Integer campLimit = null;
				try {
					campLimit = new Integer(req.getParameter("campLimit").trim());
				} catch (NumberFormatException e) {
					campLimit = 0;
					errorMsgs.add("人數上限請填正整數.");
				}

				java.sql.Timestamp listedTime = null;
				try {
					listedTime = java.sql.Timestamp.valueOf(req.getParameter("listedTime").trim());
				} catch (IllegalArgumentException e) {
					listedTime = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				Integer siteState = new Integer(req.getParameter("siteState").trim());

				Integer lovedCount = null;
				try {
					lovedCount = new Integer(req.getParameter("lovedCount").trim());
				} catch (NumberFormatException e) {
					lovedCount = 0;
					errorMsgs.add("喜歡人數請填正整數.");
				}

				Integer reportedCount = null;
				try {
					reportedCount = new Integer(req.getParameter("reportedCount").trim());
				} catch (NumberFormatException e) {
					reportedCount = 0;
					errorMsgs.add("檢舉人數請填正整數.");
				}

				InputStream in = req.getPart("campLicense").getInputStream();
				byte[] campLicense = null;
				if (in.available() != 0) {
					campLicense = new byte[in.available()];
					in.read(campLicense);
					in.close();
				} else {
					errorMsgs.add("請上傳營業執照");
				}

				InputStream in1 = req.getPart("picture1").getInputStream();
				byte[] picture1 = null;
				if (in1.available() != 0) {
					picture1 = new byte[in1.available()];
					in1.read(picture1);
					in1.close();
				} else {
					CampsiteService campsiteSvc = new CampsiteService();
					picture1 = campsiteSvc.getOneCampsite(campId).getPicture1();
				}

				byte[] picture2 = null;
				byte[] picture3 = null;
				byte[] picture4 = null;
				byte[] picture5 = null;

				CampsiteVO campsiteVO = new CampsiteVO();
				campsiteVO.setCampId(campId);
				campsiteVO.setMemberId(memberId);
				campsiteVO.setCampName(campName);
				campsiteVO.setLocation(location);
				campsiteVO.setLatitude(latitude);
				campsiteVO.setLongtitude(longtitude);
				campsiteVO.setCampDescription(campDescription);
				campsiteVO.setCampPrice(campPrice);
				campsiteVO.setCampLimit(campLimit);
				campsiteVO.setListedTime(listedTime);
				campsiteVO.setSiteState(siteState);
				campsiteVO.setLovedCount(lovedCount);
				campsiteVO.setReportedCount(reportedCount);
				campsiteVO.setCampLicense(campLicense); // :(
				campsiteVO.setPicture1(picture1); // :(

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("campsiteVO", campsiteVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/campsite/update_campsite_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始修改資料 *****************************************/
				CampsiteService campsiteSvc = new CampsiteService();
				campsiteVO = campsiteSvc.updateCampsite(memberId, campName, location, latitude, longtitude,
						campDescription, campPrice, campLimit, listedTime, siteState, lovedCount, reportedCount,
						campLicense, picture1, picture2, picture3, picture4, picture5, campId);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("campsiteVO", campsiteVO); // 資料庫update成功後,正確的的campsiteVO物件,存入req
				String url = "/campsite/listOneCampsite.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneCampsite.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/campsite/update_campsite_input.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete".equals(action)) { // 來自listAllCampsite.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer campId = new Integer(req.getParameter("campId"));

				/*************************** 2.開始刪除資料 ***************************************/
				CampsiteService campsiteSvc = new CampsiteService();
				campsiteSvc.deleteCampsite(campId);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/campsite/listAllCampsite_byDAO.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除營地失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/campsite/listAllCampsite_byDAO.jsp");
				failureView.forward(req, res);
			}
		}
	}
	
}
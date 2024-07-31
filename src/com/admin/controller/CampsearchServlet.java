package com.admin.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adminList.model.AdminListVO;
import com.adminList.model.AdminService;
import com.campAlert.model.CampAlertService;
import com.campAlert.model.CampAlertVO;
import com.campsite.model.*;
import com.campAlert.model.*;

@MultipartConfig
public class CampsearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		List<String> errorMsgs = new LinkedList<String>();
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String campid= req.getParameter("campId");
				if (campid== null || (campid.trim()).length() == 0) {
					errorMsgs.add("請輸入營地編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backendLogin/camp.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer camp = null;
				try {
					camp = new Integer(campid);
				} catch (Exception e) {
					errorMsgs.add("管理員編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backendLogin/camp.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				CampsiteService campSvc = new CampsiteService();
				CampsiteVO campsiteVO = campSvc.getOneCampsite(camp);
				if (campsiteVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backendLogin/camp.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("campsiteVO",campsiteVO); // 資料庫取出的empVO物件,存入req
				String url = "/backendLogin/camp-listone.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backendLogin/camp.jsp");
				failureView.forward(req, res);
			}
		}	
		if ("getOne_For_Update".equals(action)) { // 來自listAllCampsite.jsp的請求
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
				String url = "/backendLogin/updateCamplist.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_campsite_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backendLogin/camp.jsp");
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) { // 來自update_campsite_input.jsp的請求
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer campId = new Integer(req.getParameter("campId").trim());
				Integer memberId = new Integer(req.getParameter("memberId").trim());
				System.out.println(memberId);
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
					System.out.println(latitude);
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
//				java.sql.Date listedTime = null;
//				java.sql.Timestamp listedTimeTS = null;
//				try {
//					listedTime = java.sql.Date.valueOf(req.getParameter("listedTime").trim());
//					listedTimeTS = new java.sql.Timestamp(listedTime.getTime());
//				} catch (IllegalArgumentException e) {
//					listedTimeTS = new java.sql.Timestamp(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期!");
//				}

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
				
				CampsiteService cs = new CampsiteService();
				CampsiteVO oldvo = cs.getOneCampsite(campId);

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
				campsiteVO.setCampLicense(campLicense);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("campsiteVO", campsiteVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backendLogin/updateCamplist.jsp");
					failureView.forward(req, res);
					return; //程式中斷	
				}
			
				/*************************** 2.開始修改資料 *****************************************/
				CampsiteService campsiteSvc = new CampsiteService();
				campsiteVO = campsiteSvc.updateCampsite(memberId, campName, location, latitude,
						longtitude, campDescription, campPrice, campLimit, listedTime,
						siteState, lovedCount, reportedCount, campLicense, oldvo.getPicture1(),
						oldvo.getPicture2(), oldvo.getPicture3(), oldvo.getPicture4(), oldvo.getPicture5(), campId);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("campsiteVO", campsiteVO); // 資料庫update成功後,正確的的campsiteVO物件,存入req
				System.out.println(campsiteVO);
				System.out.println(campsiteVO);
				String url = "/backendLogin/camp-listone.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneCampsite.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backendLogin/updateCamplist.jsp");
				failureView.forward(req, res);
			}
		}
		
//更新檢舉		
		if ("addOne".equals(action)) { 
		req.setAttribute("errorMsgs", errorMsgs);

		String i =req.getParameter("campId");
//		CampsiteService campsiteService = new CampsiteService();
//		CampsiteVO campsiteVO=campsiteService.getOneCampsite(new Integer(i));
//		campsiteService.updateForOne(campsiteVO);
		
		String a = req.getParameter("alertId");
		CampAlertService campAlertService= new CampAlertService();
		CampAlertVO campAlertVO=campAlertService.updateStatus(new Integer(a), new Integer(i));
		
		
		req.setAttribute("campsiteVO", campAlertVO); // 資料庫update成功後,正確的的campsiteVO物件,存入req
		String url1 = "/backendLogin/alert.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url1); // 修改成功後,轉交listOneCampsite.jsp
		successView.forward(req, res);
		}
	}
}

package com.camporder.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.naming.java.javaURLContextFactory;

import com.adminList.model.AdminListVO;
import com.adminList.model.AdminService;
import com.campAlert.model.CampAlertService;
import com.camporder.model.CampOrderService;
import com.camporder.model.CampOrderVO;
import com.campsite.model.*;
import com.mysql.cj.x.protobuf.MysqlxCrud.Update;

public class CampOrderBackendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String xxx = req.getParameter("xxx");
		List<String> errorMsgs = new LinkedList<String>();
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String campOrderId = req.getParameter("campOrderId");
				if (campOrderId == null || (campOrderId.trim()).length() == 0) {
					errorMsgs.add("請輸入露營訂單編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backendLogin/campOrder.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer campOrder = null;
				try {
					campOrder = new Integer(campOrderId);
				} catch (Exception e) {
					errorMsgs.add("管理員編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backendLogin/campOrder.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始查詢資料 *****************************************/
				CampOrderService coSvc = new CampOrderService();
				CampOrderVO campOrderVO = coSvc.getOneCampOrder(campOrder);
				if (campOrderVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backendLogin/campOrder.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("campOrderVO", campOrderVO);
				String url = "/backendLogin/campOrder-listone.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backendLogin/campOrder.jsp");
				failureView.forward(req, res);
			}
		}
		if ("getOne_For_Update".equals(action)) {

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer campOrderId = new Integer(req.getParameter("campOrderId"));

				/*************************** 2.開始查詢資料 ****************************************/
				CampOrderService campOrderService = new CampOrderService();
				CampOrderVO campOrderVO = campOrderService.getOneCampOrder(campOrderId);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("campOrderVO", campOrderVO); // 資料庫取出的campsiteVO物件,存入req
				String url = "/backendLogin/updateCampOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_campsite_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backendLogin/campOrder.jsp");
				failureView.forward(req, res);
			}
		}
		if ("Update".equals(action)) {
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				Integer campOrderId = new Integer(req.getParameter("campOrderId"));
				Integer campId = new Integer(req.getParameter("campId"));
				System.out.println(campId);
				Integer memberId = new Integer(req.getParameter("memberId"));
				Integer guestNumber = null;
				try {
					guestNumber = new Integer(req.getParameter("guestNumber").trim());
				} catch (NumberFormatException e) {
					guestNumber = 0;
					errorMsgs.add("預定人數請填正整數.");
				}
				java.sql.Date checkInDate = null;
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date d = null;
				try {
					d = format.parse(req.getParameter("checkInDate").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入入住日期!");
					e.printStackTrace();
				}
				checkInDate = new java.sql.Date(d.getTime());
				java.sql.Date checkOutDate = null;
				java.util.Date c = null;
				try {
					c = format.parse(req.getParameter("checkOutDate").trim());
				} catch (IllegalArgumentException e) {
					errorMsgs.add("請輸入退房日期!");
					e.printStackTrace();
				}
				checkOutDate = new java.sql.Date(c.getTime());
				Timestamp orderDate = java.sql.Timestamp.valueOf(req.getParameter("orderDate").trim());
				Timestamp paymentDeadline = java.sql.Timestamp.valueOf(req.getParameter("paymentDeadline").trim());
				Integer orderTotal = null;
				try {
					orderTotal = new Integer(req.getParameter("orderTotal").trim());
				} catch (NumberFormatException e) {
					orderTotal = 0;
					errorMsgs.add("總金額請填入正整數.");
				}
				System.out.println("here");
				String orderStatus = req.getParameter("orderStatus");
				CampOrderVO campOrderVO = new CampOrderVO();
				campOrderVO.setCampOrderId(campOrderId);
				campOrderVO.setCampId(campId);
				campOrderVO.setMemberId(memberId);
				campOrderVO.setGuestNumber(guestNumber);
				campOrderVO.setCheckInDate(checkInDate);
				campOrderVO.setCheckOutDate(checkOutDate);
				campOrderVO.setOrderDate(orderDate);
				campOrderVO.setPaymentDeadline(paymentDeadline);
				campOrderVO.setOrderTotal(orderTotal);
				campOrderVO.setOrderStatus(orderStatus);
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("campOrderVO", campOrderVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/backendLogin/updateCampOrder.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始修改資料 *****************************************/
				CampOrderService campOrderSvc = new CampOrderService();
				campOrderVO = campOrderSvc.updateOrder(campId,memberId, guestNumber, checkInDate, checkOutDate, orderDate,
						paymentDeadline, orderStatus, orderTotal,campOrderId);
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("campOrderVO", campOrderVO); // 資料庫update成功後,正確的的campsiteVO物件,存入req
				System.out.println(campOrderVO);
				String url = "/backendLogin/campOrder-listone.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneCampsite.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backendLogin/updateCampOrder.jsp");
				failureView.forward(req, res);
			}
		}
		if ("delete".equals(action)) {
			Integer campOrderId = new Integer(req.getParameter("campOrderId"));
			CampOrderService campOrderSvc = new CampOrderService();
			campOrderSvc.deleteCampOrder(campOrderId);
			String url = "/backendLogin/campOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

	}
}

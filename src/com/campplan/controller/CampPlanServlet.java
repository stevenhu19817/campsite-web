package com.campplan.controller;

import java.io.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.plan.model.*;

@MultipartConfig
public class CampPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);

		if ("getOnePlan_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				String str = req.getParameter("campId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/Select_Page.jsp");
					failureView.forward(req, res);
					return;
				}

				Integer campId = null;
				try {
					campId = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("編號格式有錯誤");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/Select_Page.jsp");
					failureView.forward(req, res);
					return;
				}

				PlanService planSvc = new PlanService();
				ArrayList<PlanVO> list = planSvc.getPlans(campId);
				if (list == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/Select_Page.jsp");
					failureView.forward(req, res);
					return;
				}

				req.setAttribute("list", list);

				String url = "/camprelease/listOnePlan.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/Select_Page.jsp");
				failureView.forward(req, res);
			}
		}

		if ("getOnePlan_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				Integer planId = new Integer(req.getParameter("planId"));

				PlanService planSvc = new PlanService();
				PlanVO planVO = planSvc.getOnePlanByPlanId(planId);
				req.setAttribute("planVO", planVO);
				String url = "/camprelease/updatePlan.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/listOnePlan.jsp");
				failureView.forward(req, res);
			}
		}

		if ("plan_update".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				Integer planId = new Integer(req.getParameter("planId").trim());

				Integer campId = new Integer(req.getParameter("campId").trim());

				String planName = req.getParameter("planName");
				String planNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (planName == null || planName.trim().length() == 0) {
					errorMsgs.add("名稱: 請勿空白");
				} else if (!planName.trim().matches(planNameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				Integer planGuestLimit = null;
				try {
					planGuestLimit = new Integer(req.getParameter("planGuestLimit").trim());
				} catch (NumberFormatException e) {
					planGuestLimit = 0;
					errorMsgs.add("請填數字.");
				}

				Integer planAgeLimit = null;
				try {
					planAgeLimit = new Integer(req.getParameter("planAgeLimit").trim());
				} catch (NumberFormatException e) {
					planAgeLimit = 0;
					errorMsgs.add("請填數字.");
				}

				Integer planPrice = null;
				try {
					planPrice = new Integer(req.getParameter("planPrice").trim());
				} catch (NumberFormatException e) {
					planPrice = 0;
					errorMsgs.add("價錢請填數字");
				}

				String planOutline = req.getParameter("planOutline").trim();
				if (planOutline == null || planOutline.trim().length() == 0) {
					errorMsgs.add("介紹請填寫");
				} 

				PlanVO planVO = new PlanVO();
				planVO.setPlanId(planId);
				planVO.setCampId(campId);
				planVO.setPlanName(planName);
				planVO.setPlanGuestLimit(planGuestLimit);
				planVO.setPlanAgeLimit(planAgeLimit);
				planVO.setPlanPrice(planPrice);
				planVO.setPlanOutline(planOutline);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("planVO", planVO);

					RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/updatePlan.jsp");
					failureView.forward(req, res);
					return;
				}

				PlanService planSvc = new PlanService();
				planVO = planSvc.updatePlan(campId, planName, planGuestLimit, planAgeLimit, planPrice, planOutline, planId);
				PlanService ps = new PlanService();
				ArrayList<PlanVO> list = ps.getPlans(campId);
				req.setAttribute("list", list);
				String url = "/camprelease/listOnePlan.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗");
				RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/updatePlan.jsp");
				failureView.forward(req, res);
			}
		}

		if ("delete_plan".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				Integer campId = new Integer(req.getParameter("campId"));

				PlanService planSvc = new PlanService();
				planSvc.deletebyCampId(campId);

				String url = "/camprelease/listOnePlan.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/listOnePlan.jsp");
				failureView.forward(req, res);
			}
		}

		if ("go_to_addPlan".equals(action)) {

//			try {
				Integer campId = new Integer(req.getParameter("campId"));
				System.out.println(campId);
				req.setAttribute("campId", campId);
				String url = "/camprelease/addPlan.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

//			} catch (Exception e) {
//				errorMsgs.add("刪除資料失敗:" + e.getMessage());
//				System.out.println("失敗");
//				RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/Select_Page.jsp");
//				failureView.forward(req, res);
			}

//		}

		if ("insert_plan".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
//				Integer planId = new Integer(req.getParameter("planId").trim());
				
				String planName = req.getParameter("planName");
				String planNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (planName == null || planName.trim().length() == 0) {
					errorMsgs.add("名稱: 請勿空白");
				} else if(!planName.trim().matches(planNameReg)) { 
					errorMsgs.add("名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				Integer planGuestLimit = null;
				try {
					planGuestLimit = new Integer(req.getParameter("planGuestLimit").trim());
				} catch (NumberFormatException e) {
					planGuestLimit = 0;
					errorMsgs.add("請填數字.");
				}

				Integer planAgeLimit = null;
				try {
					planAgeLimit = new Integer(req.getParameter("planAgeLimit").trim());
				} catch (NumberFormatException e) {
					planAgeLimit = 0;
					errorMsgs.add("請填數字.");
				}

				Integer planPrice = null;
				try {
					planPrice = new Integer(req.getParameter("planPrice").trim());
				} catch (NumberFormatException e) {
					planPrice = 0;
					errorMsgs.add("價錢請填數字");
				}

				String planOutline = req.getParameter("planOutline").trim();
				if (planOutline == null || planOutline.trim().length() == 0) {
					errorMsgs.add("介紹請填寫");
				}
				Integer campId = new Integer(req.getParameter("campId").trim());
				
				PlanVO planVO = new PlanVO();
//				planVO.setPlanId(planId);
				planVO.setCampId(campId);
				planVO.setPlanName(planName);
				planVO.setPlanGuestLimit(planGuestLimit);
				planVO.setPlanAgeLimit(planAgeLimit);
				planVO.setPlanPrice(planPrice);
				planVO.setPlanOutline(planOutline);
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("planVO", planVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/addPlan.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				PlanService planSvc = new PlanService();
				planVO = planSvc.addPlan(campId, planName, planGuestLimit, planAgeLimit, planPrice, planOutline);
				List<PlanVO> list = planSvc.getByCampId(campId);
				req.setAttribute("planVOList", list);
//				req.setAttribute("planVO", planVO);

				/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
				String url = "/camprelease/listOnePlan.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/addPlan.jsp");
				failureView.forward(req, res);
			}
		}
	}
}

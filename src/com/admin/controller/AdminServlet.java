package com.admin.controller;

import java.util.*;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.log.SystemLogHandler;

import com.adminList.model.*;
import com.mysql.cj.x.protobuf.MysqlxCrud.Update;

//@WebServlet("/backendLogin/AdminServlet")
public class AdminServlet extends HttpServlet {
	public AdminServlet() {
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		List<String> errorMsgs = new LinkedList<String>();
		// 刪除功能
		if ("delete".equals(action)) {
			Integer id = new Integer(req.getParameter("adminId"));
			AdminService alertSvc = new AdminService();
			alertSvc.deleteAdminListDAO(id);
			String url = "/backendLogin/manager.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		// 新增功能
		if ("add".equals(action)) {
			req.setAttribute("errorMsgs", errorMsgs);
			Integer adminId = null;

			try {
				adminId = new Integer(req.getParameter("adminId").trim());
			} catch (NumberFormatException e) {
				adminId = 0;
				errorMsgs.add("編號請填寫四位數字.");
			}
			String adminPwd = req.getParameter("adminPwd");
			String adminPwdReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{6,10}$";
			if (adminPwd == null || adminPwd.trim().length() == 0) {
				errorMsgs.add("管理員密碼: 請勿空白");
			} else if (!adminPwd.trim().matches(adminPwdReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("管理員密碼: 只能是英文字母、數字和_ , 且長度必需在6到10之間");
			}
			String md5password = MD5Utils.md5(adminPwd);
			String adminName = req.getParameter("adminName").trim();
			if (adminName == null || adminName.trim().length() == 0) {
				errorMsgs.add("名字請勿空白");
			}
			AdminListVO AdminListVO = new AdminListVO();
			AdminListVO.setAdminId(adminId);
			AdminListVO.setAdminPwd(md5password);
			AdminListVO.setAdminName(adminName);
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("AdminListVO", AdminListVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/backendLogin/addAdmin.jsp");
				failureView.forward(req, res);
				return;
			}
			AdminService adminSvc = new AdminService();
			AdminListVO vo = adminSvc.addAdminListVO(adminId, md5password, adminName);
			req.setAttribute("AdminListVO", AdminListVO);
			String url = "/backendLogin/manager.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		// 修改功能
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer adminId = new Integer(req.getParameter("adminId"));

				/*************************** 2.開始查詢資料 ****************************************/
				AdminService adminSvc = new AdminService();
				AdminListVO adminListVO = adminSvc.getOneAdminList(adminId);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("adminListVO", adminListVO);
				String url = "/backendLogin/updateAdmin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backendLogin/manager.jsp");
				failureView.forward(req, res);
			}
		}
		if ("update".equals(action)) {
			req.setAttribute("errorMsgs", errorMsgs);
			Integer adminId = null;
			try {
				adminId = new Integer(req.getParameter("adminId").trim());

			} catch (NumberFormatException e) {
				adminId = 0;
				errorMsgs.add("編號請填寫四位數字.");
			}
			String adminPwd = req.getParameter("adminPwd");
			String adminPwdReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{6,10}$";
			if (adminPwd == null || adminPwd.trim().length() == 0) {
				errorMsgs.add("管理員密碼: 請勿空白");
			} else if (!adminPwd.trim().matches(adminPwdReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("管理員密碼: 只能是英文字母、數字和_ , 且長度必需在6到10之間");
			}
			String adminName = req.getParameter("adminName").trim();
			if (adminName == null || adminName.trim().length() == 0) {
				errorMsgs.add("名字請勿空白");
			}
			AdminListVO AdminListVO = new AdminListVO();
			AdminListVO.setAdminId(adminId);
			AdminListVO.setAdminPwd(adminPwd);
			AdminListVO.setAdminName(adminName);
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("adminListVO", AdminListVO);// 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/backendLogin/updateAdmin.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			AdminService empSvc = new AdminService();
			AdminListVO = empSvc.updateAdminListVO(adminId, adminPwd, adminName);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("adminListVO", AdminListVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/backendLogin/manager.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String admin= req.getParameter("adminId");
				if (admin== null || (admin.trim()).length() == 0) {
					errorMsgs.add("請輸入管理員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backendLogin/manager.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer adminId = null;
				try {
					adminId = new Integer(admin);
				} catch (Exception e) {
					errorMsgs.add("管理員編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backendLogin/manager.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				AdminService adminSvc = new AdminService();
				AdminListVO adminVO = adminSvc.getOneAdminList(adminId);
				if (adminVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backendLogin/manager.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("adminListVO",adminVO); // 資料庫取出的empVO物件,存入req
				String url = "/backendLogin/manager-listone.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backendLogin/manager.jsp");
				failureView.forward(req, res);
			}
		}	
	}
}

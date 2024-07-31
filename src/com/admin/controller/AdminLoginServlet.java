package com.admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.adminList.model.*;
import com.sun.org.apache.bcel.internal.generic.RETURN;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected boolean allowUser(Integer adminId, String adminPwd) {
		AdminService adminSvc = new AdminService();
		AdminListVO adminListVO = adminSvc.getOneAdminList(adminId);
		String md5password = MD5Utils.md5(adminPwd);
		if (adminListVO.getAdminPwd().equals(md5password)) {
			return true;
		} else {
			return false;
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		// 【取得使用者 帳號(account) 密碼(password)
		Integer adminId = null;
		try {
			adminId = new Integer(req.getParameter("adminId"));
		} catch (NumberFormatException e) {
			adminId = 0;
			errorMsgs.add("請填寫管理員編號");
		}
		// Send the use back to the form, if there were errors
		

		System.out.println("adminId = " + req.getParameter("adminId"));
		System.out.println("errorMsgs = " + errorMsgs);
		
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req.getRequestDispatcher("/backendLogin/backendLogin.jsp");
			failureView.forward(req, res);
			return;// 程式中斷
		}
		String adminPwd = req.getParameter("adminPwd");

		// 【檢查該帳號 , 密碼是否有效】
		if (!allowUser(adminId, adminPwd)) { // 【帳號 , 密碼無效時】
			AdminService adminService = new AdminService();
			AdminListVO adminListVO = adminService.getOneAdminList(adminId);
			if (adminListVO == null) {
				errorMsgs.add("查無編號或密碼");
			}

			if (adminListVO != null) {
				req.setAttribute("adminId", adminListVO.getAdminId());
				req.setAttribute("noPassword", "你所輸入的密碼錯誤。");
				String url = "/backendLogin/backendLogin.jsp";
				RequestDispatcher failedView = req.getRequestDispatcher(url);
				failedView.forward(req, res);
			} else {
				req.setAttribute("noAdminId", "你所輸入的管理員編號錯誤");
				req.setAttribute("noPassword", "你所輸入的密碼錯誤。");
				String url = "/backendLogin/backendLogin.jsp";
				RequestDispatcher failedView = req.getRequestDispatcher(url);
				failedView.forward(req, res);
			}
		} else { // 【帳號 , 密碼有效時, 才做以下工作】
			AdminService adminService = new AdminService();
			HttpSession session = req.getSession();
			session.setAttribute("admin", adminService.getOneAdminList(adminId).getAdminName()); // *工作1:
																									// 才在session內做已經登入過的標識
			session.setAttribute("adminid", adminService.getOneAdminList(adminId).getAdminId());
			try {
				String location = (String) session.getAttribute("location");
				if (location != null) {
					session.removeAttribute("location"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
					res.sendRedirect(location);
					return;
				}
			} catch (Exception ignored) {
			}
			res.sendRedirect(req.getContextPath() + "/backendLogin/home.jsp"); // *工作3:
																				// (-->如無來源網頁:則重導至login_success.jsp)
		}
	}

}

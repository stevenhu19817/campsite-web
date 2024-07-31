package com.members.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		try {
			String location = (String) session.getAttribute("location");
			if (location != null) {
				session.invalidate();// *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
				res.sendRedirect(location);
				return;
			}
		} catch (Exception ignored) {
		}
		session.invalidate();
		res.sendRedirect(req.getContextPath() + "/homepage/index.jsp"); // *工作3: (-->如無來源網頁:則重導至login_success.jsp)
	}
	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		HttpSession session = req.getSession();
//		String location = (String) session.getAttribute("location");
//		session.invalidate();
//		res.sendRedirect(location);
//	}
}

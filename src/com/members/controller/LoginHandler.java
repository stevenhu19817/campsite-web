package com.members.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.members.model.MemberService;
import com.members.model.MembersVO;

import at.favre.lib.crypto.bcrypt.BCrypt;

@WebServlet("/loginhandler")
public class LoginHandler extends HttpServlet {

	protected boolean allowUser(String email, String password) {
		MemberService memSvc = new MemberService();
		String bcryptHashString;
		try {
			bcryptHashString = memSvc.findByEmail(email).getPassword();
		} catch (Throwable e) {
			return false;
		}
		if(memSvc.findByPrimaryKey(memSvc.findByEmail(email).getMemberId()).getMemberStatus() == 1) {
			return false;
		}
		BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString);
		return result.verified;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");

		
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		
		if (!allowUser(email, password)) {
			MemberService memSvc = new MemberService();
			MembersVO membersVO = memSvc.findByEmail(email);
			String url = "/register_and_login/login.jsp";
			RequestDispatcher failedView = req.getRequestDispatcher(url);
			if (membersVO != null) {
				if(memSvc.findByPrimaryKey(memSvc.findByEmail(email).getMemberId()).getMemberStatus() == 1) {
					req.setAttribute("invalid", "¦¹±b¸¹¤w³Q°±Åv");
					failedView.forward(req, res);
					return;
				}
				req.setAttribute("email", membersVO.getEmail());
				req.setAttribute("noPassword", "±K½X¿ù»~¡C");
				failedView.forward(req, res);
				return;
			} else {
				req.setAttribute("noEmail", "±b¸¹¿ù»~");
				req.setAttribute("noPassword", "±K½X¿ù»~¡C");
				failedView.forward(req, res);
			}

		} else {
			
			MemberService memSvc = new MemberService();
			HttpSession session = req.getSession();
			session.setAttribute("account", memSvc.findByEmail(email).getName());
			session.setAttribute("id", memSvc.findByEmail(email).getMemberId());
			session.setAttribute("membership", memSvc.findByPrimaryKey(memSvc.findByEmail(email).getMemberId()).getMembership());
			try {
				String location = (String) session.getAttribute("location");
				if (location != null) {
					session.removeAttribute("location"); 
					res.sendRedirect(location);
					return;
				}
			} catch (Exception ignored) {
			}
			res.sendRedirect(req.getContextPath() + "/homepage/index.jsp");
		}

	}
}

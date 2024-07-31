package com.members.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.members.model.MemberService;

import at.favre.lib.crypto.bcrypt.BCrypt;

@WebServlet("/passwordReset.do")
public class passwordReset extends HttpServlet {
	private static final String SECRET = "0zy^=Q5&nZpw#Cm'+?&TdlaB0=DeiV*>/x:Pv.amM\"NE;m4k/Mm{Sb;Qx[hN9hP!";
	private static final String SUB = "passwordReset";
	private static final String ISSUER = "goCamping";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MemberService memSvc = new MemberService();
		String token = req.getParameter("token");
		System.out.println("new token :" + token);
		try {
			DecodedJWT jwt = JWT.decode(token);
			Integer id = new Integer(jwt.getKeyId());
			String subPassword = memSvc.findByPrimaryKey(id).getPassword().substring(7);
			Algorithm algorithm = Algorithm.HMAC256(SECRET);
			JWTVerifier verifier = JWT.require(algorithm)
					.withIssuer(ISSUER)
					.withSubject(SUB)
					.withClaim("password", subPassword)
					.build();
			jwt = verifier.verify(token);
			RequestDispatcher passed = req.getRequestDispatcher("/register_and_login/reset_password.jsp");
			passed.forward(req, res);
		} catch (Exception e) {
			req.setAttribute("invalid", "此連結已失效");
			RequestDispatcher failed = req.getRequestDispatcher("/register_and_login/reset_password.jsp");
			failed.forward(req, res);
		}
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		MemberService memSvc = new MemberService();
		// TODO Auto-generated method stub
		String token = req.getParameter("token");
		String password = req.getParameter("password");
		String passwordConfirm = req.getParameter("passwordConfirm");
		String passwordReg = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
		if (!password.trim().equals(passwordConfirm.trim())) {
			req.setAttribute("passwordDiff", "新密碼與確認密碼不同");
			RequestDispatcher failed = req.getRequestDispatcher("/register_and_login/reset_password.jsp");
			failed.forward(req, res);
		} else if (!password.trim().matches(passwordReg)) {
			req.setAttribute("pwordTooWeak", "密碼強度不足");
			RequestDispatcher failed = req.getRequestDispatcher("/register_and_login/reset_password.jsp");
			failed.forward(req, res);
		} else {
			try {
				DecodedJWT jwt = JWT.decode(token);
				Integer id = new Integer(jwt.getKeyId());
				String subPassword = memSvc.findByPrimaryKey(id).getPassword().substring(7);
				Algorithm algorithm = Algorithm.HMAC256(SECRET);
				JWTVerifier verifier = JWT.require(algorithm)
						.withIssuer(ISSUER)
						.withSubject(SUB)
						.withClaim("password", subPassword)
						.build();
				jwt = verifier.verify(token);
				String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
				memSvc.updatePassword(bcryptHashString, id);
				req.setAttribute("success", "已重設密碼，請重新登入");
				RequestDispatcher success = req.getRequestDispatcher("/register_and_login/reset_password.jsp");
				success.forward(req, res);
			} catch (Exception e) {
				req.setAttribute("invalid", "此連結已失效");
				RequestDispatcher failed = req.getRequestDispatcher("/register_and_login/reset_password.jsp");
				failed.forward(req, res);
			}
		}

	}
}

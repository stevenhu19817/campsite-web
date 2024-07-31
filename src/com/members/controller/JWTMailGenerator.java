package com.members.controller;

import java.io.IOException;
import java.util.Calendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.members.model.MemberService;
import com.members.model.MembersVO;

@WebServlet("/resetPassword.do")
public class JWTMailGenerator extends HttpServlet {
	static MemberService memSvc = new MemberService();
	private static final String SECRET = "0zy^=Q5&nZpw#Cm'+?&TdlaB0=DeiV*>/x:Pv.amM\"NE;m4k/Mm{Sb;Qx[hN9hP!";
	private static final String SUB = "passwordReset";
	private static final String ISSUER = "goCamping";
	protected boolean emailConfirm(String email) {
		MembersVO memVO = memSvc.findByEmail(email);
		if (memVO == null) {
			return false;
		} else {
			return true;
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req,res);
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String email = req.getParameter("email");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, 10);
		java.util.Date exp = cal.getTime();
		if (!emailConfirm(email)) {
			req.setAttribute("noEmail", "查無電子信箱");
			RequestDispatcher noEmail = req.getRequestDispatcher("/register_and_login/search_by_email.jsp");
			noEmail.forward(req, res);
		} else {
			MembersVO memVO = memSvc.findByEmail(email);
			String password = memVO.getPassword();
			try {
				Algorithm algorithm = Algorithm.HMAC256(SECRET);
				String token = JWT.create()
						.withKeyId(memVO.getMemberId().toString())
						.withIssuer(ISSUER)
						.withSubject(SUB)
						.withClaim("password", password.substring(7))
						.withExpiresAt(exp)
						.sign(algorithm);
				System.out.println(token);
				MailService mailService = new MailService();
				String content = "親愛的" + memVO.getName() + "您好:\n\t點擊以下連結以重設密碼:\n\n"+
				"http://localhost:8081"+req.getContextPath()+"/passwordReset.do?token="+token;
				mailService.sendMail(email, "重設密碼", content);
				req.setAttribute("success", "寄送成功!");
				RequestDispatcher success = req.getRequestDispatcher("/register_and_login/search_by_email.jsp");
				success.forward(req, res);
			} catch (JWTCreationException e) {
				// TODO: handle exception
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}

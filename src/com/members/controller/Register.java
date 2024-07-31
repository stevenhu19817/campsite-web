package com.members.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.members.model.MemberService;

import at.favre.lib.crypto.bcrypt.BCrypt;


public class Register extends HttpServlet {
	private static final String SECRET = "0zy^=Q5&nZpw#Cm'+?&TdlaB0=DeiV*>/x:Pv.amM\"NE;m4k/Mm{Sb;Qx[hN9hP!";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MemberService memSvc = new MemberService();
		String token = req.getParameter("token");
		DecodedJWT jwt = JWT.decode(token);
		String password = jwt.getClaim("password").asString();
		String name = jwt.getClaim("name").asString();
		String email = jwt.getSubject();
		if (memSvc.findByEmail(email)!=null) {
			req.setAttribute("exist", "此email已存在");
			RequestDispatcher success = req.getRequestDispatcher("/register_and_login/validate_result.jsp");
			success.forward(req, res);
			return;
		}
		String path = getServletContext().getRealPath("/img/avatar.jpg");
		try {
			Algorithm algorithm = Algorithm.HMAC256(SECRET);
			JWTVerifier verifier = JWT
					.require(algorithm)
					.build();
			jwt = verifier.verify(token);
			memSvc.addMember(name, password, email, getPictureByteArray(path));
			HttpSession session = req.getSession();
			session.setAttribute("account", name);
			session.setAttribute("id", memSvc.findByEmail(email).getMemberId());
			req.setAttribute("success", "開通成功");
			RequestDispatcher success = req.getRequestDispatcher("/register_and_login/validate_result.jsp");
			success.forward(req, res);
		} catch (Exception e) {
			req.setAttribute("invalid", "此連結已失效，請重新操作");
			RequestDispatcher failed = req.getRequestDispatcher("/register_and_login/validate_result.jsp");
			failed.forward(req, res);
		} 
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		String passwordConfirm = req.getParameter("passwordConfirm");
		String passwordReg = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
		String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
		String email = req.getParameter("email");
		MemberService memSvc = new MemberService();
		if (memSvc.findByEmail(email) != null) {
			req.setAttribute("emailRepeat", "此email已被註冊");
			req.setAttribute("name", name);
			RequestDispatcher failed = req.getRequestDispatcher("/register_and_login/register.jsp");
			failed.forward(req, res);
			return;
		} else if (!password.matches(passwordReg)) {
			req.setAttribute("pwordTooWeak", "密碼強度不足");
			req.setAttribute("name", name);
			req.setAttribute("email", email);
			RequestDispatcher failed = req.getRequestDispatcher("/register_and_login/register.jsp");
			failed.forward(req, res);
			return;
		} else if (!password.equals(passwordConfirm.trim())) {
			req.setAttribute("passwordDiff", "密碼與確認密碼不同");
			req.setAttribute("name", name);
			req.setAttribute("email", email);
			RequestDispatcher failed = req.getRequestDispatcher("/register_and_login/register.jsp");
			failed.forward(req, res);
			return;
		} else {
			try {
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MINUTE, 10);
				java.util.Date exp = cal.getTime();
				Algorithm algorithm = Algorithm.HMAC256(SECRET);
				String token = JWT.create()
						.withSubject(email)
						.withClaim("password", bcryptHashString)
						.withClaim("name", name)
						.withExpiresAt(exp)
						.sign(algorithm);
				MailService mailService = new MailService();
				String content = "親愛的" + name + "您好:\n\t點擊以下連結以開通帳號:\n\n"+
				"http://localhost:8081"+req.getContextPath()+"/account/register.do?token="+token;
				mailService.sendMail(email, "帳號開通", content);
				req.setAttribute("success", "註冊成功，請於10分鐘內至電子信箱開通帳號");
				RequestDispatcher success = req.getRequestDispatcher("/register_and_login/register.jsp");
				success.forward(req, res);
			} catch (JWTCreationException e) {
				// TODO: handle exception
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
}

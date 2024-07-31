package com.members.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import com.members.model.MemberService;
import com.members.model.MembersVO;

import at.favre.lib.crypto.bcrypt.BCrypt;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemberServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		final String action = req.getParameter("action");
		final String url = "/account/edit_profile.jsp";

		if ("update".equals(action)) {
			try {
				Integer id = new Integer(req.getParameter("id").trim());
				MemberService memSvc = new MemberService();
				MembersVO memVO = memSvc.findByPrimaryKey(id);

				InputStream in = req.getPart("photo").getInputStream();
				byte[] buf = null;
				if (in.available() != 0) {
					buf = new byte[in.available()];
					in.read(buf);
					in.close();
				} else {
					buf = memVO.getThumbnail();
				}

				String name = req.getParameter("name");
				if (name == null || name.trim().length() == 0) {
					name = memVO.getName();
				}
				String phone = req.getParameter("phone");
				if (phone == null || phone.trim().length() == 0) {
					phone = memVO.getPhone();
				}
				String address = req.getParameter("address");
				if (address == null || address.trim().length() == 0) {
					address = memVO.getAddress();
				}
				Integer membership = null;
				try {
					membership = new Integer(req.getParameter("membership"));
				} catch (Exception e) {
					membership = memVO.getMembership();
				}

				Integer memberStatus = null;
				try {
					memberStatus = new Integer(req.getParameter("memberStatus"));
				} catch (Exception e) {
					memberStatus = memVO.getMembership();
				}

				MembersVO updatedMemVO = memSvc.updateMembers(id, name, phone, membership, memberStatus, buf, address);
				req.setAttribute("memVO", updatedMemVO);
				HttpSession session = req.getSession();
				session.setAttribute("account", updatedMemVO.getName());
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Throwable e) {
				e.printStackTrace();
			}

		}

		if ("passwordUpdate".equals(action)) {
			RequestDispatcher View = req.getRequestDispatcher(url);
			MemberService memSvc = new MemberService();
			String currentPassword = req.getParameter("currentPword");
			String newPassword = req.getParameter("newPword");
			String confirmNewPword = req.getParameter("confirmNewPword");
			if (currentPassword.trim().length() == 0 || newPassword.trim().length() == 0
					|| confirmNewPword.trim().length() == 0) {
				req.setAttribute("invalid", "½Ð¿é¤J©Ò¦³Äæ¦ì");
				req.setAttribute("index", "2");
				View.forward(req, res);
				return;
			}
			HttpSession session = req.getSession();
			Integer id = (Integer) session.getAttribute("id");
			String bcryptHashString = memSvc.findByPrimaryKey(id).getPassword();
			BCrypt.Result result = BCrypt.verifyer().verify(currentPassword.toCharArray(), bcryptHashString);
			if (!result.verified) {
				req.setAttribute("wrongPword", "¥Ø«e±K½X¿é¤J¿ù»~");
				req.setAttribute("index", "2");
				View.forward(req, res);
				return;
			} else {
				String passwordReg = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
				if (!newPassword.matches(passwordReg)) {
					req.setAttribute("pwordTooWeak", "±K½X±j«×¤£¨¬");
					req.setAttribute("index", "2");
					View.forward(req, res);
					return;
				} else if (newPassword.equals(currentPassword)) {
					req.setAttribute("samePword", "½Ð¤Å³]¸m»P¥Ø«e±K½X¬Û¦P±K½X");
					req.setAttribute("index", "2");
					View.forward(req, res);
					return;
				} else if (!newPassword.equals(confirmNewPword)) {
					req.setAttribute("wrongPword", "½T»{±K½X»P·s±K½X¤£¦P");
					req.setAttribute("index", "2");
					View.forward(req, res);
					return;
				} else {
					String hashedPassword = BCrypt.withDefaults().hashToString(12, newPassword.toCharArray());
					memSvc.updatePassword(hashedPassword, id);
					req.setAttribute("success", "±K½X§ó·s¦¨¥\");
					req.setAttribute("index", "2");
					View.forward(req, res);
				}
			}
		}

	}

	// ï¿½ï¿½ï¿½Xï¿½Wï¿½Çªï¿½ï¿½É®×¦Wï¿½ï¿½ (ï¿½]ï¿½ï¿½APIï¿½ï¿½ï¿½ï¿½ï¿½ï¿½method,ï¿½Ò¥Hï¿½ï¿½ï¿½ï¿½ï¿½Û¦æ¼¶ï¿½g)
	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // ï¿½ï¿½ï¿½Õ¥ï¿½
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename); // ï¿½ï¿½ï¿½Õ¥ï¿½
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

}

package com.members.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.adminList.model.AdminListVO;
import com.adminList.model.AdminService;
import com.members.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class updateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		List<String> errorMsgs = new LinkedList<String>();
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer memberId = new Integer(req.getParameter("memberId"));

				/*************************** 2.開始查詢資料 ****************************************/
				MemberService memsvc = new MemberService();
				MembersVO membersVO = memsvc.findByPrimaryKey(memberId);
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("membersVO", membersVO);
				String url = "/backendLogin/updatemember.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backendLogin/member.jsp");
				failureView.forward(req, res);
			}
		}
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
					memberStatus = memVO.getMemberStatus();
				}
				System.out.println("狀態"+memberStatus);
				MembersVO updatedMemVO = memSvc.updateMembers(id, name, phone, membership, memberStatus, buf, address);
				req.setAttribute("memVO", updatedMemVO);
				HttpSession session = req.getSession();
				session.setAttribute("account", updatedMemVO.getName());
				String url = "/backendLogin/member.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Throwable e) {
				e.printStackTrace();
			}

		}
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String memid= req.getParameter("memberId");
				if (memid== null || (memid.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backendLogin/member.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				Integer mem = null;
				try {
					mem = new Integer(memid);
				} catch (Exception e) {
					errorMsgs.add("管理員編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backendLogin/member.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				MemberService memSvc = new MemberService();
				MembersVO membersVO = memSvc.findByPrimaryKey(mem);
				if (membersVO == null) {
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backendLogin/member.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("MembersVO",membersVO); // 資料庫取出的empVO物件,存入req
				String url = "/backendLogin/member-listone.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
				System.out.println("here4");

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backendLogin/member.jsp");
				failureView.forward(req, res);
			}
		}	

	}

}

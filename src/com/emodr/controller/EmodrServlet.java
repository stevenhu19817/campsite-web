package com.emodr.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.emodr.model.*;
import com.members.model.MemberService;
import com.members.model.MembersVO;

public class EmodrServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");// ���F�n�����Ӧ�select_page.jsp���ШD�Ѽ�

//��s�@�C���
		if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmodr.jsp���ШD(listAllEmodr.jsp�W���s����s)

			List<String> errorMsgs = new LinkedList<String>(); // �إߤ@��errorMsgs���X�ө���~�r��T��
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);// �]�w�ݩʱN���~�X�e�^

			try {
				/*************************** 1.�����ШD�Ѽ� ****************************************/
				Integer emodr_id = new Integer(req.getParameter("emodr_id"));// �]��req.getParameter("emodr_id")�ǹL�Ӫ��O�r��A�ҥH�n��Integer���غc�l�ରInteger

				/*************************** 2.�}�l�d�߸�� ****************************************/
				EmodrService emodrSvc = new EmodrService();
				EmodrVO emodrVO = emodrSvc.getOneEmodr(emodr_id);

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) ************/
				req.setAttribute("emodrVO", emodrVO); // ��Ʈw���X��emodrVO����,�s�Jreq
				String url = "/emodr/update_emodr_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emodr_input.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o�n�ק諸���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/emodr/listAllEmodr.jsp");
				failureView.forward(req, res);
			}
		}
//��s����
		if ("update".equals(action)) {// �Ӧ�update_emodr_input.jsp���ШD(update_emodr_input.jsp�W�� �e�X ���s)
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				Integer emodr_id = new Integer(req.getParameter("emodr_id").trim());
				Integer member_id = new Integer(req.getParameter("member_id").trim());

				java.sql.Date emodr_date = null;
				try {
					emodr_date = java.sql.Date.valueOf(req.getParameter("emodr_date").trim());
				} catch (IllegalArgumentException e) {
					emodr_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}

				String receipient = req.getParameter("receipient");
				String receipientReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$";
				if (receipient == null || receipient.trim().length() == 0) {
					errorMsgs.add("���f�H: �ФŪť�");
				} else if (!receipient.trim().matches(receipientReg)) { // �H�U�m�ߥ��h(�W)���ܦ�(regular-expression)
					errorMsgs.add("���f�H: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb1��10����");
				}

				String addr = req.getParameter("addr").trim();
				if (addr == null || addr.trim().length() == 0) {
					errorMsgs.add("���f�a�}�ФŪť�");
				}

				String mobile = req.getParameter("mobile").trim();
				if (mobile == null || mobile.trim().length() == 0) {
					errorMsgs.add("���f�q�ܽФŪť�");
				}

				Double totalprice = null;
				try {
					totalprice = new Double(req.getParameter("totalprice").trim());
				} catch (NumberFormatException e) {
					totalprice = 0.0;
					errorMsgs.add("�`���ж�Ʀr.");
				}

				Boolean emodr_status = null;
				try {
					emodr_status = new Boolean(req.getParameter("emodr_status").trim());
				} catch (Exception e) {
					errorMsgs.add("�q�檬�A�ж�1��0.");
				}

				EmodrVO emodrVO = new EmodrVO();
				emodrVO.setEmodr_id(emodr_id);
				emodrVO.setMember_id(member_id);
				emodrVO.setEmodr_date(emodr_date);
				emodrVO.setReceipient(receipient);
				emodrVO.setAddr(addr);
				emodrVO.setMobile(mobile);
				emodrVO.setTotalprice(totalprice);
				emodrVO.setEmodr_status(emodr_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("emodrVO", emodrVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/emodr/update_emodr_input.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�ק��� *****************************************/
				EmodrService emodrSvc = new EmodrService();
				emodrSvc.updateEmodr(emodr_id, member_id, emodr_date, receipient, addr, mobile, totalprice,
						emodr_status);

				/*************************** 3.�ק粒��,�ǳ����(Send the Success view) *************/
				req.setAttribute("emodrVO", emodrVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/emodr/listOneEmodr.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�ק��ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/emodr/update_emodr_input.jsp");
				failureView.forward(req, res);
			}
		}
//�R��(������ܬY�@�C���)		
		if ("delete".equals(action)) {// �Ӧ�listAllEmodr.jsp(listAllEmodr.jsp�W���R�����s)

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� ***************************************/
				Integer emodr_id = new Integer(req.getParameter("emodr_id"));

				/*************************** 2.�}�l�d�߸�� ***************************************/
				EmodrService emodrSvc = new EmodrService();
				List<EmodrVO> list = emodrSvc.notDisplay(emodr_id);
				req.setAttribute("list", list);

				/*************************** 3.�R������,�ǳ����(Send the Success view) ***********/
				String url = "/emodr/listWantEmodr.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add("�R����ƥ���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/emodr/listAllEmodr.jsp");// ���~�T�����^�e�X�R�����ӷ�����
				failureView.forward(req, res);

			}
		}

//�q�檺�s�W(�ʪ������b ���߭q�� �ϥΨ�o��)		�Ӧ�Checkout.jsp���ШD�Ѽ�
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/

				Integer member_id = new Integer(req.getParameter("member_id").trim());

				java.sql.Date emodr_date = null;
				try {
					emodr_date = new java.sql.Date(System.currentTimeMillis());
				} catch (IllegalArgumentException e) {
					emodr_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("�п�J���!");
				}

				String receipient = req.getParameter("receipient");
				String receipientReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (receipient == null || receipient.trim().length() == 0) {
					errorMsgs.add("���f�H: �ФŪť�");
				} else if (!receipient.trim().matches(receipientReg)) { // �H�U�m�ߥ��h(�W)���ܦ�(regular-expression)
					errorMsgs.add("���f�H: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��10����");
				}

				String addr = req.getParameter("addr").trim();
				String addrReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if (addr == null || addr.trim().length() == 0) {
					errorMsgs.add("���f�a�}�ФŪť�");
				} else if (!addr.trim().matches(addrReg)) { // �H�U�m�ߥ��h(�W)���ܦ�(regular-expression)
					errorMsgs.add("���f�a�}: �u��O���B�^��r���B�Ʀr�M_ , �B���ץ��ݦb2��20����");
				}

				String mobile = req.getParameter("mobile").trim();
				if (mobile == null || mobile.trim().length() == 0) {
					errorMsgs.add("���f�q�ܽФŪť�");
				}

				Double totalprice = null;
				try {
					 totalprice = new Double(req.getParameter("totalprice").trim());
				} catch (NumberFormatException e) {
					 totalprice = 0.0;
					errorMsgs.add("�`���ж�Ʀr.");
				}

				Boolean emodr_status = null;
				try {
					emodr_status = new Boolean(req.getParameter("emodr_status").trim());
				} catch (Exception e) {
					errorMsgs.add("�q�檬�A�ж�1��0.");
				}

				EmodrVO emodrVO = new EmodrVO();
				emodrVO.setMember_id(member_id);
				emodrVO.setEmodr_date(emodr_date);
				emodrVO.setReceipient(receipient);
				emodrVO.setAddr(addr);
				emodrVO.setMobile(mobile);
				emodrVO.setTotalprice(totalprice);
				emodrVO.setEmodr_status(emodr_status);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("emodrVO", emodrVO); // �t����J�榡���~��emodrVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/eshop/pages/Checkout.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}

				/*************************** 2.�}�l�s�W��� *****************************************/
				EmodrService emodrSvc = new EmodrService();
				emodrSvc.addEmodr(member_id, emodr_date, receipient, addr, mobile, totalprice, emodr_status);// �o������w�g�N��ưe�i��Ʈw

				/*************************** 3.�s�W����,�ǳ����(Send the Success view) *************/
				req.setAttribute("emodrVO", emodrVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/emodr/listAllEmodrForCart.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listAllEmodrCart.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�s�W��ƥ���:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/emodr/addEmodr.jsp");
//				failureView.forward(req, res);
			}
		}
// �ڪ��q�� �i�C�X�Ҧ����������U�檺�M��
		if ("showMyOders".equals(action)) {//
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 3.����,�ǳ����(Send the Success view) *************/
//				Integer memberId = new Integer(1);// !!!����new�{�����ը��ΡA�����W�u�ɭn���ѱ��o��!!!!
				HttpSession session=req.getSession();
				Integer memberId = (Integer) session.getAttribute("id");// �����W�u�ɽХ��}!!!���Ӧ�loginhandler.java��session.setAttribute("id",)
				req.setAttribute("member_id", memberId); 			
				MemberService memberSvc = new MemberService();
				MembersVO membersVO = memberSvc.findByPrimaryKey(memberId);
				req.setAttribute("memberName", membersVO.getName());
				String url = "/emodr/MyOrders.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���MyOrders.jsp
				successView.forward(req, res);
				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�Х��n�J�~��ݭq��!");
				System.out.println(errorMsgs);
						RequestDispatcher failureView = req.getRequestDispatcher("/eshop/pages/EShop.jsp");
						failureView.forward(req, res);
			}
		}

//���M		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�listAllEmodr.jsp���ШD(listAllEmodr.jsp�W�� �e�X ���s)

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.�����ШD�Ѽ� - ��J�榡�����~�B�z **********************/
				String str = req.getParameter("emodr_id");// req.getParameter�����Ӧ�from���椤input���� text��empno�A�|�O�r��
				if (str == null || (str.trim()).length() == 0) {// �S����J�s���άO�{���X�������r
					errorMsgs.add("�п�J�q��s��"); // �N���~�T���[�JerrorMsgs���X
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) { // �Y�����~�T��
					RequestDispatcher failureView = req.getRequestDispatcher("/emodr/listAllEmodr.jsp");
					failureView.forward(req, res); // �N�ШD�M�^������select_page.jsp
					return;// �{�����_
				}

				Integer emodr_id = null;// ���H�W���P�_���q�L�ɡA�ǳƶ}�l�N��J�q�r���ର�Ʀr���O
				try {
					emodr_id = new Integer(str);// �Υ]�˫��OInteger�N�r���ର�Ʀr���O
				} catch (Exception e) {
					errorMsgs.add("�q��s���榡�����T");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/emodr/listAllEmodr.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 2.�}�l�d�߸�� *****************************************/
				EmodrService emodrSvc = new EmodrService();// �إ�service�h�I�sDAO���Ʈw�����
				EmodrVO emodrVO = emodrSvc.getOneEmodr(emodr_id);
				if (emodrVO == null) {
					errorMsgs.add("�d�L���");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/emodr/listAllEmodr.jsp");
					failureView.forward(req, res);
					return;// �{�����_
				}

				/*************************** 3.�d�ߧ���,�ǳ����(Send the Success view) *************/
				req.setAttribute("emodrVO", emodrVO); // ��Ʈw���X��empVO����,�s�Jreq
				String url = "/emodr/listOneEmodr.jsp"; // ����O�o�blistOneEmp.jsp�̼grequest.getAttribute("empVO")�Ө��X����
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���� listOneEmodr.jsp
				successView.forward(req, res);

				/*************************** ��L�i�઺���~�B�z *************************************/
			} catch (Exception e) {
				errorMsgs.add("�L�k���o���:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/emodr/listAllEmodr.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
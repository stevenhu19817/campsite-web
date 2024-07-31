package com.Alert.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.campAlert.model.CampAlertDAO;
import com.campAlert.model.CampAlertService;
import com.campAlert.model.CampAlertVO;

//@WebServlet("/backendLogin/CampAlert.do")
@MultipartConfig
public class CampAlertServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		Integer id = Integer.parseInt(req.getParameter("Id"));
		Integer imgid = Integer.parseInt(req.getParameter("img"));
		CampAlertDAO dao = new CampAlertDAO();
		CampAlertVO vo = dao.findByPrimaryKey(id);

		try {
			switch (imgid) {
			case 1:
				out.write(vo.getPicture1());
				break;
			case 2:
				out.write(vo.getPicture2());
				break;
			default:
				out.write(vo.getPicture3());
			}

		} catch (Exception e) {
		}

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		List<String> errorMsgs = new LinkedList<String>();
		//刪除功能
		if ("delete".equals(action)) {
			Integer id = new Integer(req.getParameter("Id"));
			CampAlertService alertSvc = new CampAlertService();
			alertSvc.deleteCampAlertDAO(id);
			String url = "/backendLogin/alert.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			return;
		}
		if("insert".equals(action)) {
			Integer id = Integer.parseInt(req.getParameter("id"));
			Integer campId = Integer.parseInt(req.getParameter("campId"));
			String comment = req.getParameter("comment");
			Date reportTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String reportTimeString = formatter.format(reportTime);
			Integer status = 0;
			Integer handler = 1001;
			List<byte[]> picList = new ArrayList<>();

			try {
				List<Part> parts = (List<Part>)req.getParts();

				for (Part part : parts) {
					String fileName = part.getSubmittedFileName();
					System.out.println("file: " + fileName);
					if ((fileName != null && fileName.endsWith("jpg"))||(fileName != null && fileName.endsWith("png"))) {
						InputStream in1 = part.getInputStream();
						if (in1.available() != 0) {
							byte[] picture = new byte[in1.available()];
							in1.read(picture);
							in1.close();
							picList.add(picture);
						} else {
							
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			byte[] picture1;
			byte[] picture2;
			byte[] picture3;
			try {
				picture1 = picList.get(0);
			} catch (Exception e) {
				picture1 = null;
			}
			try {
				picture2 = picList.get(1);
			} catch (Exception e) {
				// TODO: handle exception
				picture2 = null;
			}
			try {
				picture3 = picList.get(2);
			} catch (Exception e) {
				// TODO: handle exception
				picture3 = null;
			}
			CampAlertService casvc = new CampAlertService();
			casvc.insertcCampAlertVO(id, campId, reportTimeString, comment, picture1, picture2, picture3, status, handler);
			req.setAttribute("success", "檢舉成功");
			RequestDispatcher success = req.getRequestDispatcher("/account/account_center.jsp");
			success.forward(req, res);
		}
		//查詢圖片功能
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String alertId = req.getParameter("alertId");
				if (alertId == null || (alertId.trim()).length() == 0) {
					errorMsgs.add("請輸入營地檢舉編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backendLogin/alert.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				Integer alert = null;
				try {
					alert= new Integer(alertId);
				} catch (Exception e) {
					errorMsgs.add("檢舉編號格式不正確");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backendLogin/alert.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始查詢資料 *****************************************/
				CampAlertService alertSvc = new CampAlertService();
				CampAlertVO campAlertVO = alertSvc.getOneEmp(alert);
				if (campAlertVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/backendLogin/alert.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("campAlertVO",campAlertVO);
				String url = "/backendLogin/campAlertListone.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/backendLogin/alert.jsp");
				failureView.forward(req, res);
			}
		}
	}
}

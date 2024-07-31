	package com.camporder.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.camporder.model.CampOrderDAO;
import com.camporder.model.CampOrderService;
import com.camporder.model.CampOrderVO;
import com.campsitetentstatus.model.CampsiteTentStatusService;
import com.customerplan.model.CustomerPlanVO;

@WebServlet("/campOrder.do")
public class CampOrderServlet extends HttpServlet {
	public static boolean isWithinRange(Date date, Date startDate, Date endDate) {
		return !(date.before(startDate) || date.after(endDate));
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String url = "/campsite/reserve_campsite.jsp";
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		// 建立營地訂單
		if ("book".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				// 取得入住時間
				String from = req.getParameter("from");
				java.sql.Date checkedIn = null;
				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date parsed = dateFormat.parse(from);
					checkedIn = new java.sql.Date(parsed.getTime());
				} catch (Exception e) {
					throw new Exception(e);
				}
				// 取得退房日期
				String to = req.getParameter("to");
				java.sql.Date checkedOut = null;
				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date parsed = dateFormat.parse(to);
					checkedOut = new java.sql.Date(parsed.getTime());
				} catch (Exception e) {
					// TODO: handle exception
					throw new Exception(e);
				}
				// 總人數
				String strHeadCount = req.getParameter("headCount");

				Integer headCount = null;
				try {
					headCount = new Integer(strHeadCount);
				} catch (Exception e) {
					errorMsgs.add("人數格式不正確");
				}

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return;// 程式中斷
				}
				// 總價錢
				Integer price = new Integer(req.getParameter("price"));
				// 會員ID
				Integer memberId = new Integer(req.getParameter("memberId"));
				// 營地ID
				Integer campId = new Integer(req.getParameter("campId"));
				// 下定時間
				String today = req.getParameter("orderDate");
				Timestamp orderDate = null;
				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					Date parsedDate = dateFormat.parse(today);
					orderDate = new java.sql.Timestamp(parsedDate.getTime());
				} catch (Exception e) {
					// TODO: handle exception
					throw new Exception(e);
				}
				// 付款期限
				String deadline = req.getParameter("deadline");
				Timestamp expired = null;
				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					Date parsedDate = dateFormat.parse(deadline);
					expired = new java.sql.Timestamp(parsedDate.getTime());
				} catch (Exception e) {
					// TODO: handle exception
					throw new Exception(e);
				}
				//檢查預訂期間剩餘空位
				CampsiteTentStatusService CTSSvc = new CampsiteTentStatusService();
				try {
					if (!CTSSvc.isTentAvailiblewithGuestNumberandTimeRange(campId, headCount, checkedIn, checkedOut)) {
						req.setAttribute("noSpace", "預訂期間無空位");
						RequestDispatcher failedView = req.getRequestDispatcher(url);
						failedView.forward(req, res);
						return;
					}
				} catch (ParseException | ServletException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				CampOrderService COSvc = new CampOrderService();
				// 檢查是否重複下訂
				List<CampOrderVO> orderList = COSvc.getByMemberId(memberId);
				for (CampOrderVO order : orderList) {
					java.sql.Date confirmDate = checkedIn;
					while (confirmDate.compareTo(checkedOut) <= 0) {
						Date usedCheckedInday = order.getCheckInDate();
						Date usedCheckedOutday = order.getCheckOutDate();
						if (isWithinRange(checkedIn, usedCheckedInday, usedCheckedOutday)) {
							RequestDispatcher repeat = req.getRequestDispatcher(url);
							req.setAttribute("repeat", "您在此段時間內已於相同營地下訂");
							repeat.forward(req, res);
							return;
						}
						Calendar c = Calendar.getInstance();
						c.setTime(confirmDate);
						c.add(Calendar.DATE, 1);
						confirmDate = new java.sql.Date(c.getTimeInMillis());
					}
				}
				// 將以上屬性新增至新的營地訂單物件
				CampOrderVO campOrderVO = new CampOrderVO();
				campOrderVO.setCampId(campId);
				campOrderVO.setGuestNumber(headCount);
				campOrderVO.setMemberId(memberId);
				campOrderVO.setCheckInDate(checkedIn);
				campOrderVO.setCheckOutDate(checkedOut);
				campOrderVO.setOrderDate(orderDate);
				campOrderVO.setPaymentDeadline(expired);
				campOrderVO.setOrderTotal(price);
				HttpSession session = req.getSession();
				session.setAttribute("campOrderVO", campOrderVO);
				res.sendRedirect("bookings/extra_flavour.jsp");
			} catch (Exception e) {
				req.setAttribute("missing", "請填入所有欄位");
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} 

		}

		// 嚙諍立訂嚙踝蕭
		if ("createOrder".equals(action)) {
			Integer finalPrice = new Integer(req.getParameter("finalPrice"));
			HttpSession session = req.getSession();
			CampOrderVO campOrderVO = (CampOrderVO) session.getAttribute("campOrderVO");
			List<CustomerPlanVO> list = (List<CustomerPlanVO>) session.getAttribute("planList");

			
			campOrderVO.setOrderTotal(finalPrice);
			CampOrderDAO dao = new CampOrderDAO();
			try {
				dao.insertWithPlans(campOrderVO, list);
				res.sendRedirect(req.getContextPath()+"/account/account_center.jsp");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if("addComment".equals(action)) {
			Integer orderId = new Integer(req.getParameter("orderid"));
			String comment = req.getParameter("comment");
			if (comment.trim() == "") {
				req.setAttribute("missing", "請輸入內容");
				RequestDispatcher missing = req.getRequestDispatcher("");
				missing.forward(req, res);
			}
			CampOrderService COSvc = new CampOrderService();
			COSvc.addComment(comment, orderId);
			RequestDispatcher success = req.getRequestDispatcher("/homepage/campsite.do?action=getReserveCampsite&campId=5001");
			success.forward(req, res);
			
		}

	}
}

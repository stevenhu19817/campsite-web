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
		String action = req.getParameter("action");// 為了要接收來自select_page.jsp的請求參數

//更新一列資料
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmodr.jsp的請求(listAllEmodr.jsp上的編輯按鈕)

			List<String> errorMsgs = new LinkedList<String>(); // 建立一個errorMsgs集合來放錯誤字串訊息
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);// 設定屬性將錯誤碼送回

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer emodr_id = new Integer(req.getParameter("emodr_id"));// 因為req.getParameter("emodr_id")傳過來的是字串，所以要用Integer的建構子轉為Integer

				/*************************** 2.開始查詢資料 ****************************************/
				EmodrService emodrSvc = new EmodrService();
				EmodrVO emodrVO = emodrSvc.getOneEmodr(emodr_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("emodrVO", emodrVO); // 資料庫取出的emodrVO物件,存入req
				String url = "/emodr/update_emodr_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emodr_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/emodr/listAllEmodr.jsp");
				failureView.forward(req, res);
			}
		}
//更新接續
		if ("update".equals(action)) {// 來自update_emodr_input.jsp的請求(update_emodr_input.jsp上的 送出 按鈕)
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer emodr_id = new Integer(req.getParameter("emodr_id").trim());
				Integer member_id = new Integer(req.getParameter("member_id").trim());

				java.sql.Date emodr_date = null;
				try {
					emodr_date = java.sql.Date.valueOf(req.getParameter("emodr_date").trim());
				} catch (IllegalArgumentException e) {
					emodr_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				String receipient = req.getParameter("receipient");
				String receipientReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{1,10}$";
				if (receipient == null || receipient.trim().length() == 0) {
					errorMsgs.add("收貨人: 請勿空白");
				} else if (!receipient.trim().matches(receipientReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("收貨人: 只能是中、英文字母、數字和_ , 且長度必需在1到10之間");
				}

				String addr = req.getParameter("addr").trim();
				if (addr == null || addr.trim().length() == 0) {
					errorMsgs.add("收貨地址請勿空白");
				}

				String mobile = req.getParameter("mobile").trim();
				if (mobile == null || mobile.trim().length() == 0) {
					errorMsgs.add("收貨電話請勿空白");
				}

				Double totalprice = null;
				try {
					totalprice = new Double(req.getParameter("totalprice").trim());
				} catch (NumberFormatException e) {
					totalprice = 0.0;
					errorMsgs.add("總價請填數字.");
				}

				Boolean emodr_status = null;
				try {
					emodr_status = new Boolean(req.getParameter("emodr_status").trim());
				} catch (Exception e) {
					errorMsgs.add("訂單狀態請填1或0.");
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
					req.setAttribute("emodrVO", emodrVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/emodr/update_emodr_input.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				EmodrService emodrSvc = new EmodrService();
				emodrSvc.updateEmodr(emodr_id, member_id, emodr_date, receipient, addr, mobile, totalprice,
						emodr_status);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("emodrVO", emodrVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/emodr/listOneEmodr.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/emodr/update_emodr_input.jsp");
				failureView.forward(req, res);
			}
		}
//刪除(等於不顯示某一列資料)		
		if ("delete".equals(action)) {// 來自listAllEmodr.jsp(listAllEmodr.jsp上的刪除按鈕)

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer emodr_id = new Integer(req.getParameter("emodr_id"));

				/*************************** 2.開始查詢資料 ***************************************/
				EmodrService emodrSvc = new EmodrService();
				List<EmodrVO> list = emodrSvc.notDisplay(emodr_id);
				req.setAttribute("list", list);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				String url = "/emodr/listWantEmodr.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/emodr/listAllEmodr.jsp");// 錯誤訊息轉交回送出刪除的來源網頁
				failureView.forward(req, res);

			}
		}

//訂單的新增(購物車結帳 成立訂單 使用到這塊)		來自Checkout.jsp的請求參數
		if ("insert".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				Integer member_id = new Integer(req.getParameter("member_id").trim());

				java.sql.Date emodr_date = null;
				try {
					emodr_date = new java.sql.Date(System.currentTimeMillis());
				} catch (IllegalArgumentException e) {
					emodr_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}

				String receipient = req.getParameter("receipient");
				String receipientReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (receipient == null || receipient.trim().length() == 0) {
					errorMsgs.add("收貨人: 請勿空白");
				} else if (!receipient.trim().matches(receipientReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("收貨人: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}

				String addr = req.getParameter("addr").trim();
				String addrReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,20}$";
				if (addr == null || addr.trim().length() == 0) {
					errorMsgs.add("收貨地址請勿空白");
				} else if (!addr.trim().matches(addrReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("收貨地址: 只能是中、英文字母、數字和_ , 且長度必需在2到20之間");
				}

				String mobile = req.getParameter("mobile").trim();
				if (mobile == null || mobile.trim().length() == 0) {
					errorMsgs.add("收貨電話請勿空白");
				}

				Double totalprice = null;
				try {
					 totalprice = new Double(req.getParameter("totalprice").trim());
				} catch (NumberFormatException e) {
					 totalprice = 0.0;
					errorMsgs.add("總價請填數字.");
				}

				Boolean emodr_status = null;
				try {
					emodr_status = new Boolean(req.getParameter("emodr_status").trim());
				} catch (Exception e) {
					errorMsgs.add("訂單狀態請填1或0.");
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
					req.setAttribute("emodrVO", emodrVO); // 含有輸入格式錯誤的emodrVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/eshop/pages/Checkout.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始新增資料 *****************************************/
				EmodrService emodrSvc = new EmodrService();
				emodrSvc.addEmodr(member_id, emodr_date, receipient, addr, mobile, totalprice, emodr_status);// 這行執行後已經將資料送進資料庫

				/*************************** 3.新增完成,準備轉交(Send the Success view) *************/
				req.setAttribute("emodrVO", emodrVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/emodr/listAllEmodrForCart.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listAllEmodrCart.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("新增資料失敗:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/emodr/addEmodr.jsp");
//				failureView.forward(req, res);
			}
		}
// 我的訂單 可列出所有有紀錄的下單的清單
		if ("showMyOders".equals(action)) {//
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 3.完成,準備轉交(Send the Success view) *************/
//				Integer memberId = new Integer(1);// !!!此行new程式為試車用，正式上線時要註解掉這行!!!!
				HttpSession session=req.getSession();
				Integer memberId = (Integer) session.getAttribute("id");// 正式上線時請打開!!!接來自loginhandler.java的session.setAttribute("id",)
				req.setAttribute("member_id", memberId); 			
				MemberService memberSvc = new MemberService();
				MembersVO membersVO = memberSvc.findByPrimaryKey(memberId);
				req.setAttribute("memberName", membersVO.getName());
				String url = "/emodr/MyOrders.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交MyOrders.jsp
				successView.forward(req, res);
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("請先登入才能看訂單!");
				System.out.println(errorMsgs);
						RequestDispatcher failureView = req.getRequestDispatcher("/eshop/pages/EShop.jsp");
						failureView.forward(req, res);
			}
		}

//收尋		
		if ("getOne_For_Display".equals(action)) { // 來自listAllEmodr.jsp的請求(listAllEmodr.jsp上的 送出 按鈕)

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("emodr_id");// req.getParameter接收來自from表單中input標籤 text的empno，會是字串
				if (str == null || (str.trim()).length() == 0) {// 沒有輸入編號或是程式碼中有錯字
					errorMsgs.add("請輸入訂單編號"); // 將錯誤訊息加入errorMsgs集合
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) { // 若有錯誤訊息
					RequestDispatcher failureView = req.getRequestDispatcher("/emodr/listAllEmodr.jsp");
					failureView.forward(req, res); // 將請求和回應轉交到select_page.jsp
					return;// 程式中斷
				}

				Integer emodr_id = null;// 當以上的判斷都通過時，準備開始將輸入從字串轉為數字型別
				try {
					emodr_id = new Integer(str);// 用包裝型別Integer將字串轉為數字型別
				} catch (Exception e) {
					errorMsgs.add("訂單編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/emodr/listAllEmodr.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				EmodrService emodrSvc = new EmodrService();// 建立service去呼叫DAO跟資料庫拿資料
				EmodrVO emodrVO = emodrSvc.getOneEmodr(emodr_id);
				if (emodrVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/emodr/listAllEmodr.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("emodrVO", emodrVO); // 資料庫取出的empVO物件,存入req
				String url = "/emodr/listOneEmodr.jsp"; // 之後記得在listOneEmp.jsp裡寫request.getAttribute("empVO")來取出物件
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交到 listOneEmodr.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/emodr/listAllEmodr.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
package com.campsitetentstatus.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.camprelease.model.CampReleaseService;
import com.camprelease.model.CampReleaseVO;
import com.campsite.model.CampsiteService;
import com.campsite.model.CampsiteVO;
import com.campsitetentstatus.model.CampsiteTentStatusService;
import com.campsitetentstatus.model.CampsiteTentStatusVO;

public class CampsiteTentStatusServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("getAllOfOne".equals(action)) {

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
                String str = req.getParameter("campId");
                if (str == null || (str.trim()).length() == 0) {
                    errorMsgs.add("請輸入營地編號");
                }

                Integer campId = null;
                try {
                    campId = new Integer(str);
                } catch (Exception e) {
                    errorMsgs.add("營地編號格式不正確");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/Select_Page.jsp");
                    failureView.forward(req, res);
                    return;// 程式中斷
                }


                /*************************** 2.開始查詢資料 *****************************************/
                CampsiteTentStatusService campsiteTentStatusSvc = new CampsiteTentStatusService();
                List<CampsiteTentStatusVO> list = campsiteTentStatusSvc.getAllCampStatusofOneCamp(campId);
                if (list == null) {
                    errorMsgs.add("查無資料");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/Select_Page.jsp");
                    failureView.forward(req, res);
                    return;// 程式中斷
                }

                /*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
                req.setAttribute("campsiteTentStatusList", list);
                String url = "/campsitetentstatus/listaddCampTent.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url);
                successView.forward(req, res);

                /*************************** 其他可能的錯誤處理 *************************************/
            } catch (Exception e) {
                errorMsgs.add("無法取得資料:" + e.getMessage());
                RequestDispatcher failureView = req.getRequestDispatcher("/camprelease/Select_Page.jsp");
                failureView.forward(req, res);
            }
        }

        if ("insert".equals(action)) {

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

                Integer campId = new Integer(req.getParameter("campId").trim()); // 要綁定只能新增自己的

                java.sql.Date campOpeningTime = null;
                try {
                    campOpeningTime = java.sql.Date.valueOf(req.getParameter("campOpeningTime").trim());
                } catch (IllegalArgumentException e) {
                    errorMsgs.add("請輸入日期!");
                }

                Integer emptyCampLeft = null;
                try {
                    emptyCampLeft = new Integer(req.getParameter("emptyCampLeft").trim());
                } catch (NumberFormatException e) {
                    errorMsgs.add("尚餘人數上限請填正整數.");
                }
                CampsiteTentStatusVO campsiteTentStatusVO = new CampsiteTentStatusVO();
                campsiteTentStatusVO.setCampId(campId);
                campsiteTentStatusVO.setCampOpeningTime(campOpeningTime);
                campsiteTentStatusVO.setEmptyCampLeft(emptyCampLeft);

                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    req.setAttribute("CampsiteTentStatusVO", campsiteTentStatusVO);
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/campsitetentstatus/addCampTent.jsp");
                    failureView.forward(req, res);
                    return;
                }

                /*************************** 2.開始新增資料 ***************************************/
                CampsiteTentStatusService campsiteTentStatusSvc = new CampsiteTentStatusService();
                campsiteTentStatusVO = campsiteTentStatusSvc.addCampsiteTentStatus(campId, campOpeningTime, emptyCampLeft);

                req.setAttribute("campsiteTentStatusVO", campsiteTentStatusVO);
                /*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
                String url = "/campsitetentstatus/listaddCampTent.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url);
                successView.forward(req, res);

                /*************************** 其他可能的錯誤處理 **********************************/
            } catch (Exception e) {
                errorMsgs.add(e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/campsitetentstatus/addCampTent.jsp");
                failureView.forward(req, res);
            }
        }

        if ("getOne_For_Update".equals(action)) {

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /*************************** 1.接收請求參數 ****************************************/
                Integer campId = new Integer(req.getParameter("campId"));

                /*************************** 2.開始查詢資料 ****************************************/
                CampsiteTentStatusService campsiteTentStatusSvc = new CampsiteTentStatusService();
                CampsiteTentStatusVO campsiteTentStatusVO = campsiteTentStatusSvc.getOneCampsiteTentByCampId(campId);
                /*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
                req.setAttribute("campsiteTentStatusVO", campsiteTentStatusVO);
                String url = "/campsitetentstatus/updateCampTent.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url);
                successView.forward(req, res);

                /*************************** 其他可能的錯誤處理 **********************************/
            } catch (Exception e) {
                errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/campsitetentstatus/listaddCampTent.jsp");
                failureView.forward(req, res);
            }
        }

        if ("update".equals(action)) {

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);
            try {
                /*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
                Integer campId = new Integer(req.getParameter("campId").trim()); // 要綁定只能新增自己的
                System.out.println("更新");
                System.out.println("campId: " + campId);

                java.sql.Date campOpeningTime = null;
                try {
                    campOpeningTime = java.sql.Date.valueOf(req.getParameter("campOpeningTime").trim());
                } catch (IllegalArgumentException e) {
                    errorMsgs.add("請輸入日期!");
                }
                System.out.println("time: " + campOpeningTime);

                Integer emptyCampLeft = null;
                try {
                    emptyCampLeft = new Integer(req.getParameter("emptyCampLeft").trim());
                } catch (NumberFormatException e) {
                    errorMsgs.add("尚餘人數上限請填正整數.");
                }
                System.out.println("空位: " + emptyCampLeft);

                CampsiteTentStatusVO campsiteTentStatusVO = new CampsiteTentStatusVO();
                campsiteTentStatusVO.setCampId(campId);
                campsiteTentStatusVO.setCampOpeningTime(campOpeningTime);
                campsiteTentStatusVO.setEmptyCampLeft(emptyCampLeft);

                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    req.setAttribute("campsiteTentStatusVO", campsiteTentStatusVO);
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/campsitetentstatus/updateCampTent.jsp");
                    failureView.forward(req, res);
                    return;
                }
                /*************************** 2.開始修改資料 *****************************************/
                CampsiteTentStatusService campsiteTentStatusSvc = new CampsiteTentStatusService();
                campsiteTentStatusVO = campsiteTentStatusSvc.updateCampsiteTentStatus(emptyCampLeft, campId,
                        campOpeningTime);

                /*************************** 3.修改完成,準備轉交(Send the Success view) *************/
                req.setAttribute("campsiteTentStatusVO", campsiteTentStatusVO);
                String url = "/campsitetentstatus/listupdateCampsTent.jsp"; // 沒有回到listOneCampsiteTentStatus是因為那邊是要取得List才能用
                RequestDispatcher successView = req.getRequestDispatcher(url);
                successView.forward(req, res);
                System.out.println("123");
                /*************************** 其他可能的錯誤處理 *************************************/
            } catch (Exception e) {
                errorMsgs.add("修改資料失敗:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/campsitetentstatus/updateCampTent.jsp");
                failureView.forward(req, res);
            }
        }

        if ("delete".equals(action)) { // 來自listAllCampsite.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /*************************** 1.接收請求參數 ***************************************/
                Integer campId = new Integer(req.getParameter("campId").trim()); // 要綁定只能新增自己的

                java.sql.Date campOpeningTime = null;
                try {
                    campOpeningTime = java.sql.Date.valueOf(req.getParameter("campOpeningTime").trim());
                } catch (IllegalArgumentException e) {
                    campOpeningTime = new java.sql.Date(System.currentTimeMillis());
                    errorMsgs.add("請輸入日期!");
                }

                /*************************** 2.開始刪除資料 ***************************************/
                CampsiteTentStatusService campsiteTentStatusSvc = new CampsiteTentStatusService();
                campsiteTentStatusSvc.deleteCampsiteTentStatus(campId, campOpeningTime);

                /*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
                String url = "/campsitetentstatus/listAllCampsiteTentStatus.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url);
                successView.forward(req, res);

                /*************************** 其他可能的錯誤處理 **********************************/
            } catch (Exception e) {
                errorMsgs.add("刪除營地失敗:" + e.getMessage());
                RequestDispatcher failureView = req.getRequestDispatcher("/campsitetentstatus/listAllCampsiteTentStatus.jsp");
                failureView.forward(req, res);
            }
        }

        if ("go_to_addCampTent".equals(action)) {

            Integer campId = new Integer(req.getParameter("campId"));
            System.out.println("轉交: " + campId);
            req.setAttribute("campId", campId);
            String url = "/campsitetentstatus/addCampTent.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);

        }
    }
}

package com.Product.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.Product.model.ProductService;
import com.Product.model.ProductVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024)
public class productServlet extends HttpServlet {

//	public void doGet(HttpServletRequest req, HttpServletResponse res) 
//				throws ServletException, IOException {
//		
//	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String s = req.getParameter("productno");
		System.out.println("action=" + action);

		if ("search".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String str = req.getParameter("pname");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入商品名稱");

				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/product/selectAll.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				
				String pname = req.getParameter("pname");
				System.out.println(pname);
				String pnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (pname == null || pname.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				}

//				Integer productno = null;
//				try {
//					productno = new Integer(str);
//				} catch (Exception e) {
//					errorMsgs.add("商品編號輸入不正確");
//				}
					
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/product/selectAll.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				

				ProductService proSvc = new ProductService();
//				ProductVO productVO = proSvc.getOneproduct(productno);
				ProductVO productVO = proSvc.getOneproductName(pname);
				if (productVO == null) {
					errorMsgs.add("查無資料");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/product/selectAll.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				req.setAttribute("productVO", productVO);
				String url = "/product/selectOneproduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 selectOneproduct.jsp
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product/selectAll.jsp");
				failureView.forward(req, res);

			}
		}
		
//		if("search".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			try {
//				String pname = req.getParameter("pname");
//				System.out.println(pname);
//				String pnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (pname == null || pname.trim().length() == 0) {
//					errorMsgs.add("商品名稱: 請勿空白");
//				}
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/product/selectAll.jsp");
//					failureView.forward(req, res);
//					return;// 程式中斷
//				}
//				
//				ProductService proSvc = new ProductService();
//				ProductVO productVO = proSvc.getOneproductName(pname);
//				
//				if (productVO == null) {
//					errorMsgs.add("查無資料");
//				}
//				if (!errorMsgs.isEmpty()) {
//					RequestDispatcher failureView = req.getRequestDispatcher("/product/selectAll.jsp");
//					failureView.forward(req, res);
//					return;// 程式中斷
//				}
//				
//				req.setAttribute("productVO", productVO);
//				String url = "/product/selectOneproduct.jsp";
//				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 selectOneproduct.jsp
//				successView.forward(req, res);
//				
//			} catch (Exception e) {
//				errorMsgs.add("無法取得資料:" + e.getMessage());
//				RequestDispatcher failureView = req.getRequestDispatcher("/product/selectAll.jsp");
//				failureView.forward(req, res);
//			}
//			
//			
//		}
//		

		if ("getforUpdate".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer productno = new Integer(req.getParameter("productno"));
				
				HttpSession session = req.getSession();
				ProductService proSvc = new ProductService();
				ProductVO productVO = proSvc.getOneproduct(productno);

				session.setAttribute("updateproduct", productVO);

				String url = "/product/update_product.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product/selectAll.jsp");
				failureView.forward(req, res);
			}

		}

		if ("update".equals(action)) {
//			System.out.println("hello");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				
				
				
				Integer productno = new Integer(req.getParameter("productno").trim());

				String pname = req.getParameter("pname");
				System.out.println(pname);
				String pnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (pname == null || pname.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				}

				Integer price = null;

				try {
					price = new Integer(req.getParameter("price").trim());
//			System.out.println(price);
				} catch (NumberFormatException e) {
					price = 0;
					errorMsgs.add("請輸入價格");
				}

				Integer inventory = null;
				try {
					inventory = new Integer(req.getParameter("inventory").trim());
			 System.out.println(inventory);
				} catch (NumberFormatException e) {
					inventory = 0;
					errorMsgs.add("請輸入數量");
				}

				Integer admin_id = null;
				try {
					admin_id = new Integer(req.getParameter("admin_id").trim());
//			System.out.println(admin_id);
				} catch (NumberFormatException e) {
					errorMsgs.add("上架者不能空");
				}

				Integer situation = null;
				try {
					situation = new Integer(req.getParameter("situation").trim());
//			 System.out.println(inventory);
				} catch (NumberFormatException e) {
					errorMsgs.add("狀態不能空");
				
				}

				String descript = req.getParameter("descript");
//			 System.out.println(descript);
				String descriptReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (descript == null || descript.trim().length() == 0) {
					errorMsgs.add("商品介紹: 請勿空白");
				}

				String psort = req.getParameter("psort");
				String psortReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (psort == null || pname.trim().length() == 0) {
					errorMsgs.add("商品類別: 請勿空白");
				}

				byte[] picture1 = null;
				byte[] picture2 = null;
				byte[] picture3 = null;

				try {
					Collection<Part> parts = req.getParts();

					Part part1 = req.getPart("img1");
					InputStream in1 = part1.getInputStream();
//			 System.out.println("part1="+part1);
//			 System.out.println("in1="+in1);
					if (in1.available() != 0) {
						in1.close();
						picture1 = new byte[in1.available()];
						in1.read(picture1);
						in1.close();

					} else {
						errorMsgs.add("圖片1:不能空");
					}

					Part part2 = req.getPart("img2");
					InputStream in2 = part2.getInputStream();
					if (in2.available() != 0) {
						in2.close();
						picture2 = new byte[in2.available()];
						in2.read(picture2);
						in2.close();
					} else {
						errorMsgs.add("圖片2:不能空");
					}

					Part part3 = req.getPart("img3");
					InputStream in3 = part3.getInputStream();
					if (in3.available() != 0) {
						in3.close();
						picture3 = new byte[in3.available()];
						in3.read(picture3);
						in3.close();
					} else {
						errorMsgs.add("圖片3:不能空");
//						ProductService proSvc = new ProductService();
//						picture3 = proSvc.getOneproduct(productno).getPicture3();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
//				ProductVO productVO = (ProductVO)req.getAttribute("productVO");
				
				ProductVO productVO = new ProductVO();
				productVO.setProductno(productno);
				productVO.setPname(pname);
				productVO.setPsort(psort);
				productVO.setPrice(price);
				productVO.setInventory(inventory);
				productVO.setAdmin_id(admin_id);
				productVO.setSituation(situation);
				productVO.setDescript(descript);
				
				if(picture1 != null) {
					productVO.setPicture1(picture1);
				}
				if(picture2 != null) {
					productVO.setPicture2(picture2);
				}
				if(picture3 != null) {
					productVO.setPicture3(picture3);
				}
				

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的productVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/product/update_product.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				ProductService proSvc = new ProductService();
				productVO = proSvc.updateProduct(productno, pname, psort, price, inventory, admin_id, situation,
						descript, picture1, picture2, picture3);

				req.setAttribute("productVO", productVO);
				String url = "/product/selectOneproduct.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product/update_product.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				String pname = req.getParameter("pname");
				String pnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (pname == null || pname.trim().length() == 0) {
					errorMsgs.add("商品名稱: 請勿空白");
				}

				Integer price = null;
				try {
					price = new Integer(req.getParameter("price").trim());
				} catch (NumberFormatException e) {
					price = 0;
					errorMsgs.add("請輸入價格");
				}

				String psort = req.getParameter("psort");
				String psortReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (psort == null || pname.trim().length() == 0) {
//					errorMsgs.add("商品類別: 請勿空白");
				}

				Integer inventory = null;
				try {
					inventory = new Integer(req.getParameter("inventory").trim());
				} catch (NumberFormatException e) {
					inventory = 0;
					errorMsgs.add("請輸入數量");
				}

				Integer admin_id = null;
				try {
					admin_id = new Integer(req.getParameter("admin_id").trim());
				} catch (NumberFormatException e) {
					errorMsgs.add("上架人員不能為空");
				}

				Integer situation = null;
				try {
					situation = new Integer(req.getParameter("situation").trim());
				} catch (NumberFormatException e) {
					inventory = 0;
					errorMsgs.add("狀態請輸入");
				}

				String descript = req.getParameter("descript");
				String descriptReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (descript == null || descript.trim().length() == 0) {
					errorMsgs.add("商品介紹: 請勿空白");
				}

				List<byte[]> picList = new ArrayList<>();

				try {
					Collection<Part> parts = req.getParts();

					for (Part part : parts) {
						String fileName = part.getSubmittedFileName();
						if (fileName != null && fileName.endsWith("jpg")) {
							InputStream in1 = part.getInputStream();
							if (in1.available() != 0) {
								byte[] picture = new byte[in1.available()];
								in1.read(picture);
								in1.close();
								picList.add(picture);
							} else {
								errorMsgs.add("圖片不能空");
							}
						}
					}

//			 Part part1 = req.getPart("img1");
//			 InputStream in1 = part1.getInputStream();
//			 System.out.println("part1="+part1);
//			 System.out.println("in1="+in1);
//			 System.out.println();
//			 if (in1.available() != 0) {
//				 in1.close();
//				 picture1 = new byte[in1.available()];
//				 in1.read(picture1);
//				 in1.close();
//							 
//			 } else {
//				 errorMsgs.add("圖片: 請勿空白");
//			 }

//			 Part part2 = req.getPart("img2");
//			 InputStream in2 = part2.getInputStream();
//			 if(in2.available() != 0) {
//				 in2.close();
//				 picture2 = new byte[in2.available()];
//				 in2.read(picture2);
//				 in2.close();
//				 
//			 } else {
//				 errorMsgs.add("圖片: 請勿空白");
//			 }
//			 
//			 Part part3 = req.getPart("img3");
//			 InputStream in3 = part3.getInputStream();
//			 if(in3.available() != 0) {
//				 in3.close();
//				 picture3 = new byte[in3.available()];
//				 in3.read(picture3);
//				 in3.close();
//				 				 
//			 } 
//			   else {
//				 errorMsgs.add("圖片: 請勿空白");
//			 }

				} catch (Exception e) {
					e.printStackTrace();
				}

//				System.out.println("Size = " + picList.size());

				byte[] picture1 = picList.get(0);
				byte[] picture2 = picList.get(1);
				byte[] picture3 = picList.get(2);

				ProductVO productVO = new ProductVO();
				productVO.setPname(pname);
				productVO.setPsort(psort);
				productVO.setPrice(price);
				productVO.setInventory(inventory);
				productVO.setAdmin_id(admin_id);
				productVO.setSituation(situation);
				productVO.setDescript(descript);
				productVO.setPicture1(picture1);
				productVO.setPicture2(picture2);
				productVO.setPicture3(picture3);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("productVO", productVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/product/selectAll.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料 *****************************************/
				ProductService proSvc = new ProductService();
				productVO = proSvc.addProduct(pname, psort, price, inventory, admin_id, situation, descript, picture1,
						picture2, picture3);

				String url = "/product/selectAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product/selectAll.jsp");
				failureView.forward(req, res);
				return;

			}
		}

		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			try {
				Integer productno = new Integer(req.getParameter("productno"));

				/*************************** 2.開始刪除資料 *****************************************/
				ProductService proSvc = new ProductService();
				proSvc.deleteProduct(productno);

				String url = "/product/selectAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				e.printStackTrace();
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/product/selectAll.jsp");
				failureView.forward(req, res);
			}

		}

	}

}

package com.eshop.controller;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.eshop.model.Merchandise;

public class ShoppingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();

		@SuppressWarnings("unchecked")
		List<Merchandise> buylist = (Vector<Merchandise>) session.getAttribute("shoppingcart");
		String action = req.getParameter("action");

		if (!"CHECKOUT".equals(action)) {

			// 刪除購物車中的商品
			if ("DELETE".equals(action)) {
				String del = req.getParameter("del");
				int d = Integer.parseInt(del);
				buylist.remove(d);

//				session.setAttribute("shoppingcart", buylist);
//				String url = "/eshop/pages/Cart.jsp";
////				String url = "/eshop/pages/EShop.jsp";
//				RequestDispatcher rd = req.getRequestDispatcher(url);
//				rd.forward(req, res);
//				return;
			}
			// 新增商品至購物車中
			else if ("ADD".equals(action)) {
				// 取得後來新增的商品
				Merchandise amerchandise = getMerchandise(req);

				if (buylist == null) {
					buylist = new Vector<Merchandise>();
					buylist.add(amerchandise);
				} else {
					if (buylist.contains(amerchandise)) {
						Merchandise innerMerchandise = buylist.get(buylist.indexOf(amerchandise));
						innerMerchandise.setQuantity(innerMerchandise.getQuantity() + amerchandise.getQuantity());
					} else {
						buylist.add(amerchandise);
					}
				}
			}

			session.setAttribute("shoppingcart", buylist);
			String url = "/eshop/pages/Cart.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}

		// 結帳，計算購物車書籍價錢總數
		else if ("CHECKOUT".equals(action)) {
			double total = 0;
			for (int i = 0; i < buylist.size(); i++) {
				Merchandise order = buylist.get(i);
				Double price = order.getPrice();
				Integer quantity = order.getQuantity();
				total += (price * quantity);
			}

			String amount = String.valueOf(total);
			//req.setAttribute("amount", amount);
			session.setAttribute("amount", amount);
			String url = "/eshop/pages/Checkout.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
		}
	}

	private Merchandise getMerchandise(HttpServletRequest req) { // 抓到從Eshop.jsp送過來的參數，將參數用setter建立物件的屬性值

		String name = req.getParameter("name");
		String price = req.getParameter("price");
		String quantity = req.getParameter("quantity");
		String pic = req.getParameter("pic");/// 這邊接的是網址要注意一下
		Merchandise merchandise = new Merchandise();
		merchandise.setName(name);
		merchandise.setPrice(new Double(price));
		merchandise.setPic(pic);
		merchandise.setQuantity((new Integer(quantity)).intValue());
		return merchandise;
	}
}
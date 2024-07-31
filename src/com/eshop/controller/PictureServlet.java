package com.eshop.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.Product.model.*;
import com.Product.model.ProductJDBCDAO;
import com.Product.model.ProductVO;

public class PictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ProductJDBCDAO dao = new ProductJDBCDAO();

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
//		ServletOutputStream out = res.getOutputStream();
		Integer id = Integer.parseInt(req.getParameter("id"));

		ProductService pSvc = new ProductService();
		ProductVO vo1 = pSvc.getOneproduct(id);
//REX  此段用來測試在ProductVO物件中的哪一個picture有值	=======
		List<byte[]> picList = new ArrayList<byte[]>();
		picList.add(vo1.getPicture1());
		picList.add(vo1.getPicture2());
		picList.add(vo1.getPicture3());
		int index = 0;
		ServletOutputStream out = res.getOutputStream();
		for (int i = 0; i < picList.size(); i++) {
			if (picList.get(i) != null) {
				index = i;
				out.write(picList.get(index));

			}
		}
	}

}

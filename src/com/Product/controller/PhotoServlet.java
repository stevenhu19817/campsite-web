package com.Product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Product.model.ProductJDBCDAO;
import com.Product.model.ProductVO;

@WebServlet("/PhotoServlet")
public class PhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ProductJDBCDAO dao = new ProductJDBCDAO();

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		Integer id = Integer.parseInt(req.getParameter("id"));
		Integer imgid = Integer.parseInt(req.getParameter("img"));

		ProductVO vo1 = dao.findByPrimaryKey(id);


		try {
			switch (imgid) {
			case 1:
				out.write(vo1.getPicture1());
				break;
			case 2:
				out.write(vo1.getPicture2());
				break;
			default:
				out.write(vo1.getPicture3());
			}

		} catch (Exception e) {

		}

	}

}

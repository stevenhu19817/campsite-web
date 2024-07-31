package com.camprelease.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.camprelease.model.CampReleaseDAO;
import com.camprelease.model.CampReleaseService;
import com.camprelease.model.CampReleaseVO;

@WebServlet("/CampReleasePhotoServlet")
public class CampReleasePhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	CampReleaseDAO dao = new CampReleaseDAO();

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		req.setCharacterEncoding("UTF-8");
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		Integer id = Integer.parseInt(req.getParameter("id"));
		Integer imgid = Integer.parseInt(req.getParameter("img"));
		
		
		
		CampReleaseVO vo1 = dao.findByPrimaryKey(id);
		try {
			switch(imgid) {
				case 1:
					out.write(vo1.getPicture1());
					break;
				case 2:
					out.write(vo1.getPicture2());
					break;
				case 3:
					out.write(vo1.getPicture3());
					break;
				case 4:
					out.write(vo1.getPicture4());
					break;
				default:
					out.write(vo1.getPicture5());
			}
						
		} catch (Exception e) {
			
		}
		
	}
//	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		String action = req.getParameter("action");
//
//		if ("delete".equals(action)) {
//			Integer id = new Integer(req.getParameter("campId"));
//			CampReleaseService campreleaseSvc = new CampReleaseService();
//			campreleaseSvc.deleteCampReleaseDAO(id);
//			String url = "/backendLogin/alert.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
//		}ƒ½
//	}

}
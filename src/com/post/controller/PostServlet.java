package com.post.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.post.model.PostService;
import com.post.model.PostVO;

public class PostServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("update".equals(action)) {
			try {
				Integer postid = new Integer(req.getParameter("postId").trim());
				String title = req.getParameter("title");
				String article = req.getParameter("article");
				
				PostVO postVO = new PostVO();
				postVO.setPostId(postid);
				postVO.setTitle(title);
				postVO.setArticle(article);
				
				PostService postSvc = new PostService();
				postSvc.updatePost(postid, title, article);
				
				res.sendRedirect("index.jsp");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		
		if("insert".equals(action)) {
			try {
				String article = req.getParameter("article");
				String title = req.getParameter("title");
				HttpSession session = req.getSession();
				Integer id = new Integer(session.getAttribute("id").toString());
				
				
				PostVO postVO = new PostVO();
				postVO.setArticle(article);
				postVO.setTitle(title);
				
				PostService postSvc = new PostService();
				postSvc.addPost(id, title, article);

				res.sendRedirect("index.jsp");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
		
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp
	
			try {
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer postId = new Integer(req.getParameter("postId"));
				
				/***************************2.�}�l�R�����***************************************/
				PostService postSvc = new PostService();
				postSvc.delete(postId);
				
				/***************************3.�R������,�ǳ����(Send the Success view)***********/								
				
				res.sendRedirect("index.jsp");
				/***************************��L�i�઺���~�B�z**********************************/
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}

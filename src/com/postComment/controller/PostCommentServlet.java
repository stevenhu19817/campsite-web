package com.postComment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.postComment.model.PostCommentService;

public class PostCommentServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req,res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("insert".equals(action)) {
			String comment = req.getParameter("comment");
			Integer postId = Integer.parseInt(req.getParameter("postId"));
			HttpSession session = req.getSession();
			Integer memId = Integer.parseInt(session.getAttribute("id").toString());
			
			PostCommentService commSvc = new PostCommentService();
			commSvc.addPostComment(postId, memId, comment);
			
			res.sendRedirect("post.jsp?postId=" + postId);
		}
	}
}

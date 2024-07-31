package com.post.model;

import java.util.List;

public class PostService {
	private PostDAO_Interface dao;

	public PostService() {
		dao = new PostDAO();
	}

	public PostVO addPost(Integer authorId, String title, String article) {
		PostVO postVO = new PostVO();
		postVO.setAuthorId(authorId);
		postVO.setTitle(title);
		postVO.setArticle(article);
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
		postVO.setCreated(timestamp);
		dao.insert(postVO);
		return postVO;
	}
	
	public PostVO updatePost(Integer postId, String title, String article) {
		PostVO postVO = new PostVO();
		postVO.setPostId(postId);
		postVO.setTitle(title);
		postVO.setArticle(article);
		dao.update(postVO);
		return postVO;
	}
	
	public void delete(Integer articleId) {
		dao.delete(articleId);
	}
	
	public PostVO findByPostId(Integer postId) {
		return dao.findByPostId(postId);
	}
	
    public List<PostVO> findByAuthor(Integer authorId){
    	return dao.findByAuthor(authorId);
    }
    
    public List<PostVO> getAll(){
    	return dao.getAll();
    }
}

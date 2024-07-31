package com.postComment.model;

import java.util.List;

public interface postCommentDAO_interface {
	public void insert (PostCommentVO commentVO);
	public void update (PostCommentVO commentVO);
	public void delete (Integer commentId);
	public List<PostCommentVO> findByPostId(Integer postId);
}

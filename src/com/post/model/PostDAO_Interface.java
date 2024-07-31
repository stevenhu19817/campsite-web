package com.post.model;

import java.util.List;

public interface PostDAO_Interface {
	public void insert(PostVO postVO);
    public void update(PostVO postVO);
    public void delete(Integer articleId);
    public List<PostVO> findByAuthor(Integer authorId);
    public PostVO findByPostId(Integer postId);
    public List<PostVO> getAll();
}

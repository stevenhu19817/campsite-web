package com.pic.model;

import java.util.List;

public interface PicDAO_Interface {
	public void insert(PicVO picVO);
	public void delete(Integer picId);
	public List<PicVO> getAllByPostId(Integer postId);
}

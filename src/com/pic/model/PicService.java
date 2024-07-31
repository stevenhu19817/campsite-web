package com.pic.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class PicService {
	private PicDAO_Interface dao;
	
	public PicService() {
		dao = new PicDAO();
	}
	
	public PicVO addPic(Integer postid, String pic) {
		PicVO picVO = new PicVO();
		picVO.setPostId(postid);
		try {
			picVO.setPic(getPictureByteArray(pic));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dao.insert(picVO);
		return picVO;
	}
	
	public void delete(Integer picId) {
		dao.delete(picId);
	};
	
	public List<PicVO> getAllByPostId(Integer postId){
		return dao.getAllByPostId(postId);
		
	};
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
}

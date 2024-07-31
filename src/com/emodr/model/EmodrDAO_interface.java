package com.emodr.model;

import java.util.*;

public interface EmodrDAO_interface {
	public void insert(EmodrVO emodrVO);

	public void update(EmodrVO emodrVO);

	public void delete(Integer emodrid);
	
	public List<EmodrVO> notDisplay(Integer emodrid);//此方法為不顯示不要的資料

	public EmodrVO findByPrimaryKey(Integer emodrid);

	public List<EmodrVO> getAll();
	// 萬用複合查詢(傳入參數型態Map)(回傳 List)
//	public List<EmodrVO> getAll(Map<String,String[]> map);
	
	public List<EmodrVO> findByFK(Integer memberid);//此發法為用FK memberid來查資料
	
}

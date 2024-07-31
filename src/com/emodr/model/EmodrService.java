package com.emodr.model;

import java.util.List;

public class EmodrService {

	private EmodrDAO_interface dao;

	public EmodrService() {
		dao = new EmodrDAO();
	}

	public EmodrVO addEmodr(Integer member_id, java.sql.Date emodr_date, String receipient, String addr, String mobile,
			Double totalprice, Boolean emodr_status) {

		EmodrVO emodrVO = new EmodrVO();

		emodrVO.setMember_id(member_id);
		emodrVO.setEmodr_date(emodr_date);
		emodrVO.setReceipient(receipient);
		emodrVO.setAddr(addr);
		emodrVO.setMobile(mobile);
		emodrVO.setTotalprice(totalprice);
		emodrVO.setEmodr_status(emodr_status);
		dao.insert(emodrVO);

		return emodrVO;
	}

	public EmodrVO updateEmodr(Integer emodr_id, Integer member_id, java.sql.Date emodr_date, String receipient,
			String addr, String mobile, Double totalprice, Boolean emodr_status) {

		EmodrVO emodrVO = new EmodrVO();

		emodrVO.setEmodr_id(emodr_id);
		emodrVO.setMember_id(member_id);
		emodrVO.setEmodr_date(emodr_date);
		emodrVO.setReceipient(receipient);
		emodrVO.setAddr(addr);
		emodrVO.setMobile(mobile);
		emodrVO.setTotalprice(totalprice);
		emodrVO.setEmodr_status(emodr_status);
		dao.update(emodrVO);

		return emodrVO;
	}

	public void deleteEmodr(Integer emodr_id) {
		dao.delete(emodr_id);
	}

	public EmodrVO getOneEmodr(Integer emodr_id) {
		return dao.findByPrimaryKey(emodr_id);
	}

	public List<EmodrVO> getAll() {
		return dao.getAll();
	}
	
	public List<EmodrVO> notDisplay(Integer emodr_id) {
		return dao.notDisplay(emodr_id);
	}
	
	public List<EmodrVO> getAllMyOrder(Integer memberid) {
		return dao.findByFK(memberid);
	}

}

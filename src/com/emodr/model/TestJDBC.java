package com.emodr.model;

import java.util.List;

public class TestJDBC {

	public static void main(String[] args) {

		EmodrJDBCDAO dao = new EmodrJDBCDAO();

		// 新增
		EmodrVO emodrVO1 = new EmodrVO();
		emodrVO1.setMember_id(100);
		emodrVO1.setEmodr_date(java.sql.Date.valueOf("2005-01-01"));
		emodrVO1.setReceipient("大吳");
		emodrVO1.setAddr("中市");
		emodrVO1.setMobile("123456789");
		emodrVO1.setTotalprice(new Double(500));
		emodrVO1.setEmodr_status(new Boolean("1"));
		dao.insert(emodrVO1);

		// 修改
		EmodrVO emodrVO2 = new EmodrVO();
		emodrVO2.setEmodr_id(14);
		emodrVO2.setMember_id(200);
		emodrVO2.setEmodr_date(java.sql.Date.valueOf("2005-02-02"));
		emodrVO2.setReceipient("小吳");
		emodrVO2.setAddr("北市");
		emodrVO2.setMobile("987654321");
		emodrVO2.setTotalprice(new Double(600));
		emodrVO2.setEmodr_status(new Boolean("0"));
		dao.update(emodrVO2);

		// 刪除
		dao.delete(23);

		// 查詢
		EmodrVO emodrVO3 = dao.findByPrimaryKey(1);
		System.out.print(emodrVO3.getEmodr_id() + ",");
		System.out.print(emodrVO3.getMember_id() + ",");
		System.out.print(emodrVO3.getEmodr_date() + ",");
		System.out.print(emodrVO3.getReceipient() + ",");
		System.out.print(emodrVO3.getAddr() + ",");
		System.out.print(emodrVO3.getMobile() + ",");
		System.out.print(emodrVO3.getTotalprice() + ",");
		System.out.println(emodrVO3.getEmodr_status());
		System.out.println("---------------------");

		// 查詢
		List<EmodrVO> list = dao.getAll();
		for (EmodrVO aEmodr : list) {
			System.out.print(aEmodr.getEmodr_id() + ",");
			System.out.print(aEmodr.getMember_id() + ",");
			System.out.print(aEmodr.getEmodr_date() + ",");
			System.out.print(aEmodr.getReceipient() + ",");
			System.out.print(aEmodr.getAddr() + ",");
			System.out.print(aEmodr.getMobile() + ",");
			System.out.print(aEmodr.getTotalprice() + ",");
			System.out.print(aEmodr.getEmodr_status());
			System.out.println();
		}
	}
}

package com.adminList.model;

import java.util.List;
import java.util.Scanner;

import com.adminList.model.AdminListVO;

import com.adminList.model.AdminListDAO;
import com.adminList.model.AdminListDao_interface;
public class AdminAdd {
	public static void main(String[] args) {
		AdminListDAO dao = new AdminListDAO();

		// ·s¼W
		AdminListVO VO = new AdminListVO();
		VO.setAdminId(7005);
		VO.setAdminPwd("jacky0229");
		VO.setAdminName("§d¨f°a");
		dao.add(VO);

	}
}
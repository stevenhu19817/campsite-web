package com.adminList.model;

import java.util.List;

import com.adminList.model.AdminListVO;
import com.adminList.model.AdminListDAO;
import com.adminList.model.AdminListDao_interface;

public class AdminService {
		private AdminListDao_interface dao;

		public AdminService() {
			dao = new AdminListDAO();
		}

		public AdminListVO addAdminListVO(Integer adminId, String adminPwd,String adminName) {

			AdminListVO vo = new AdminListVO();

			vo.setAdminId(adminId);
			vo.setAdminPwd(adminPwd);
			vo.setAdminName(adminName);
			dao.add(vo);

			return vo;
		}

		public AdminListVO updateAdminListVO(Integer adminId, String adminPwd,String adminName) {

			AdminListVO vo = new AdminListVO();

			vo.setAdminId(adminId);
			vo.setAdminPwd(adminPwd);
			vo.setAdminName(adminName);
			dao.update(vo);

			return vo;
		}

		public void deleteAdminListDAO(Integer adminId) {
			dao.delete(adminId);
		}

		public AdminListVO getOneAdminList(Integer adminId) {
			return dao.findByPrimaryKey(adminId);
		}

		public List<AdminListVO> getAll() {
			return dao.getALL();
		}
	}
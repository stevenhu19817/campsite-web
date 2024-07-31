package com.adminList.model;

import java.util.List;

public interface AdminListDao_interface {
	 
		void add(AdminListVO AdminListVO);
		void update(AdminListVO AdminListVO);
		void delete(int adminId);
		AdminListVO findByPrimaryKey(Integer adminId);
	    List<AdminListVO>getALL();


		
	}
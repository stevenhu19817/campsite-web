package com.campAlert.model;

import java.util.List;



public interface CampAlertDao_interface {
	void insert(CampAlertVO CampAlertDAO);
	void update(CampAlertVO CampAlertDAO);
	void delete(int alertId);
	CampAlertVO findByPrimaryKey(Integer alertId);
    List<CampAlertVO>getALL();
    void updateStatus(CampAlertVO campAlertVO);

	

	
	
}
package com.followed.model;

public class FollowedService {
	
	private FollowedDAO_interface dao;
	
	public FollowedService() {
		dao = new FollowedDAO();
	}
	
	public FollowedVO addFollowed (Integer memberId, Integer followedId) {
		
		FollowedVO followedVO = new FollowedVO();
		followedVO.setMemberId(memberId);
		followedVO.setFollowedID(followedId);
		dao.insert(followedVO);
		return followedVO;
	}
	
	public void delete(Integer followedMappigID) {
		dao.delete(followedMappigID);
	}
	
	
}

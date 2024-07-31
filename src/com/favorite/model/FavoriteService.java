package com.favorite.model;

public class FavoriteService {
	private FavoriteDAO_interface dao;

	public FavoriteService() {
		dao = new FavoriteDAO();
	}

	public void delete(Integer favId) {
		dao.delete(favId);
	}

	public FavoriteVO addFavorite(Integer memberId, Integer campId) {
		FavoriteVO favoriteVO = new FavoriteVO();
		favoriteVO.setMemberId(memberId);
		favoriteVO.setCampId(campId);
		dao.insert(favoriteVO);
		return favoriteVO;
	}
}

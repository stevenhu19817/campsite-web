package com.campsite.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface CampsiteDAO_Interface {
    void add(CampsiteVO campsiteVO);

    void update(CampsiteVO campsiteVO);

    void delete(Integer campId);

    CampsiteVO findbyPrimaryKey(Integer campId);

    List<CampsiteVO> getAll();

    List<CampsiteVO> getSearchCampsite(String campName);

    List<CampsiteVO> getAll(Map<String, String[]> map); // ½Æ¦X¬d¸ß

    void updateForOne(CampsiteVO campsiteVO);
}

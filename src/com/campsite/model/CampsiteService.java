package com.campsite.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class CampsiteService {

    private CampsiteDAO_Interface dao;

    public CampsiteService() {
        dao = new CampsiteDAO();
    }

    public CampsiteVO addCampsite(Integer memberId, String campName, String location, Double latitude,
                                  Double longtitude, String campDescription, Integer campPrice, Integer campLimit, Timestamp listedTime,
                                  Integer siteState, Integer lovedCount, Integer reportedCount, byte[] campLicense, byte[] picture1,
                                  byte[] picture2, byte[] picture3, byte[] picture4, byte[] picture5) {

        CampsiteVO campsiteVO = new CampsiteVO();

        campsiteVO.setMemberId(memberId);
        campsiteVO.setCampName(campName);
        campsiteVO.setLocation(location);
        campsiteVO.setLatitude(latitude);
        campsiteVO.setLongtitude(longtitude);
        campsiteVO.setCampDescription(campDescription);
        campsiteVO.setCampPrice(campPrice);
        campsiteVO.setCampLimit(campLimit);
        campsiteVO.setListedTime(listedTime);
        campsiteVO.setSiteState(siteState);
        campsiteVO.setLovedCount(lovedCount);
        campsiteVO.setReportedCount(reportedCount);
        campsiteVO.setCampLicense(campLicense);
        campsiteVO.setPicture1(picture1);
        campsiteVO.setPicture2(picture2);
        campsiteVO.setPicture3(picture3);
        campsiteVO.setPicture4(picture4);
        campsiteVO.setPicture5(picture5);
        dao.add(campsiteVO);

        return campsiteVO;
    }

    public CampsiteVO updateCampsite(Integer memberId, String campName, String location, Double latitude,
                                     Double longtitude, String campDescription, Integer campPrice, Integer campLimit, Timestamp listedTime,
                                     Integer siteState, Integer lovedCount, Integer reportedCount, byte[] campLicense, byte[] picture1,
                                     byte[] picture2, byte[] picture3, byte[] picture4, byte[] picture5, Integer campId) {

        CampsiteVO campsiteVO = new CampsiteVO();

        campsiteVO.setMemberId(memberId);
        campsiteVO.setCampName(campName);
        campsiteVO.setLocation(location);
        campsiteVO.setLatitude(latitude);
        campsiteVO.setLongtitude(longtitude);
        campsiteVO.setCampDescription(campDescription);
        campsiteVO.setCampPrice(campPrice);
        campsiteVO.setCampLimit(campLimit);
        campsiteVO.setListedTime(listedTime);
        campsiteVO.setSiteState(siteState);
        campsiteVO.setLovedCount(lovedCount);
        campsiteVO.setReportedCount(reportedCount);
        campsiteVO.setCampLicense(campLicense);
        campsiteVO.setPicture1(picture1);
        campsiteVO.setPicture2(picture2);
        campsiteVO.setPicture3(picture3);
        campsiteVO.setPicture4(picture4);
        campsiteVO.setPicture5(picture5);
        campsiteVO.setCampId(campId);
        dao.update(campsiteVO);

        return campsiteVO;
    }

    public void deleteCampsite(Integer campId) {
        dao.delete(campId);
    }

    public CampsiteVO getOneCampsite(Integer campId) {
        return dao.findbyPrimaryKey(campId);
    }

    public List<CampsiteVO> getSearchCampsite(String campName) {
        return dao.getSearchCampsite(campName);
    }

    public List<CampsiteVO> getAll() {
        return dao.getAll();
    }

    public List<CampsiteVO> getAll(Map<String, String[]> map) {
        return dao.getAll(map);
    }

    public void updateForOne(CampsiteVO campsiteVO) {
        dao.updateForOne(campsiteVO);
    }
}

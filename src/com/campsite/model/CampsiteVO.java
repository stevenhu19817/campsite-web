package com.campsite.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class CampsiteVO implements Serializable {
    private Integer campId;
    private Integer memberId;
    private String campName;
    private String location;
    private Double latitude;
    private Double longtitude;
    private String campDescription;
    private Integer campPrice;
    private Integer campLimit;
    private Timestamp listedTime;
    private Integer siteState;
    private Integer lovedCount;
    private Integer reportedCount;
    private byte[] campLicense;
    private byte[] picture1;
    private byte[] picture2;
    private byte[] picture3;
    private byte[] picture4;
    private byte[] picture5;
    private Date strDate;
    private Date endDate;

    public CampsiteVO() {
    }

    public CampsiteVO(Integer campId, Integer memberId, String campName, String location, Double latitude,
                      Double longtitude, String campDescription, Integer campPrice, Integer campLimit, Timestamp listedTime,
                      Integer siteState, Integer lovedCount, Integer reportedCount, byte[] campLicense, byte[] picture1,
                      byte[] picture2, byte[] picture3, byte[] picture4, byte[] picture5) {
        this.campId = campId;
        this.memberId = memberId;
        this.campName = campName;
        this.location = location;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.campDescription = campDescription;
        this.campPrice = campPrice;
        this.campLimit = campLimit;
        this.listedTime = listedTime;
        this.siteState = siteState;
        this.lovedCount = lovedCount;
        this.reportedCount = reportedCount;
        this.campLicense = campLicense;
        this.picture1 = picture1;
        this.picture2 = picture2;
        this.picture3 = picture3;
        this.picture4 = picture4;
        this.picture5 = picture5;
    }

    public Integer getCampId() {
        return campId;
    }

    public void setCampId(Integer campId) {
        this.campId = campId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Double longtitude) {
        this.longtitude = longtitude;
    }

    public String getCampDescription() {
        return campDescription;
    }

    public void setCampDescription(String campDescription) {
        this.campDescription = campDescription;
    }

    public Integer getCampPrice() {
        return campPrice;
    }

    public void setCampPrice(Integer campPrice) {
        this.campPrice = campPrice;
    }

    public Integer getCampLimit() {
        return campLimit;
    }

    public void setCampLimit(Integer campLimit) {
        this.campLimit = campLimit;
    }

    public Timestamp getListedTime() {
        return listedTime;
    }

    public void setListedTime(Timestamp listedTime) {
        this.listedTime = listedTime;
    }

    public Integer getSiteState() {
        return siteState;
    }

    public void setSiteState(Integer siteState) {
        this.siteState = siteState;
    }

    public Integer getLovedCount() {
        return lovedCount;
    }

    public void setLovedCount(Integer lovedCount) {
        this.lovedCount = lovedCount;
    }

    public Integer getReportedCount() {
        return reportedCount;
    }

    public void setReportedCount(Integer reportedCount) {
        this.reportedCount = reportedCount;
    }

    public byte[] getCampLicense() {
        return campLicense;
    }

    public void setCampLicense(byte[] campLicense) {
        this.campLicense = campLicense;
    }

    public byte[] getPicture1() {
        return picture1;
    }

    public void setPicture1(byte[] picture1) {
        this.picture1 = picture1;
    }

    public byte[] getPicture2() {
        return picture2;
    }

    public void setPicture2(byte[] picture2) {
        this.picture2 = picture2;
    }

    public byte[] getPicture3() {
        return picture3;
    }

    public void setPicture3(byte[] picture3) {
        this.picture3 = picture3;
    }

    public byte[] getPicture4() {
        return picture4;
    }

    public void setPicture4(byte[] picture4) {
        this.picture4 = picture4;
    }

    public byte[] getPicture5() {
        return picture5;
    }

    public void setPicture5(byte[] picture5) {
        this.picture5 = picture5;
    }

    public Date getStrDate() {
        return strDate;
    }

    public void setStrDate(Date strDate) {
        this.strDate = strDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "CampsiteVO [campId=" + campId + ", memberId=" + memberId + ", campName=" + campName + ", location="
                + location + ", latitude=" + latitude + ", longtitude=" + longtitude + ", campDescription="
                + campDescription + ", campPrice=" + campPrice + ", campLimit=" + campLimit + ", listedTime="
                + listedTime + ", siteState=" + siteState + ", lovedCount=" + lovedCount + ", reportedCount="
                + reportedCount + ", strDate=" + strDate + ", endDate=" + endDate + "]" + "\n";
    }

}

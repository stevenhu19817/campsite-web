package com.campsitetentstatus.model;

import java.io.Serializable;
import java.sql.Date;

public class CampsiteTentStatusVO implements Serializable {
    private Integer campId;
    private Date campOpeningTime;
    private Integer emptyCampLeft;

    public CampsiteTentStatusVO() {
    }

    public CampsiteTentStatusVO(Integer campId, Date campOpeningTime, Integer emptyCampLeft) {
        this.campId = campId;
        this.campOpeningTime = campOpeningTime;
        this.emptyCampLeft = emptyCampLeft;
    }

    public Integer getCampId() {
        return campId;
    }

    public void setCampId(Integer campId) {
        this.campId = campId;
    }

    public Date getCampOpeningTime() {
        return campOpeningTime;
    }

    public void setCampOpeningTime(Date campOpeningTime) {
        this.campOpeningTime = campOpeningTime;
    }

    public Integer getEmptyCampLeft() {
        return emptyCampLeft;
    }

    public void setEmptyCampLeft(Integer emptyCampLeft) {
        this.emptyCampLeft = emptyCampLeft;
    }

    @Override
    public String toString() {
        return "Campsite_Tent_StatusVO [campId=" + campId + ", campOpeningTime=" + campOpeningTime + ", emptyCampLeft="
                + emptyCampLeft + "]";
    }

}

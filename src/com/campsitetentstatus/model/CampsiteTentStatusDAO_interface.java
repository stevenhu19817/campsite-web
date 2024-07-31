package com.campsitetentstatus.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public interface CampsiteTentStatusDAO_interface {
    void add(CampsiteTentStatusVO campsiteTentStatusVO);

    void update(CampsiteTentStatusVO campsiteTentStatusVO);

    void delete(Integer campId, Date campOpeningTime);

    CampsiteTentStatusVO findbyPrimaryKey(Integer campId, Date campOpeningTime);

    List<CampsiteTentStatusVO> getAll();

    List<CampsiteTentStatusVO> getAllOfOne(Integer campId);

    CampsiteTentStatusVO getByCampId(Integer campId);
}

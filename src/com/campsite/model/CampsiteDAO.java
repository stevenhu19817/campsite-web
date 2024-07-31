package com.campsite.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Campsite;

public class CampsiteDAO implements CampsiteDAO_Interface {
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/gocamping?serverTimezone=Asia/Taipei";
    public static final String USER = "David";
    public static final String PASSWORD = "123456";
    public static final String INSERT_STMT = "INSERT INTO CAMPSITE (MEMBER_ID, CAMP_NAME, LOCATION, LATITUDE, "
            + "LONGTITUDE, CAMP_DESCRIPTION, CAMP_PRICE, CAMP_LIMIT, LISTED_TIME, "
            + "SITE_STATE, LOVED_COUNT, REPORTED_COUNT, CAMP_LICENSE, "
            + "PICTURE1, PICTURE2, PICTURE3, PICTURE4, PICTURE5) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_STMT = "UPDATE CAMPSITE SET MEMBER_ID = ?, CAMP_NAME = ? , LOCATION = ?, "
            + "LATITUDE = ?, LONGTITUDE = ?, CAMP_DESCRIPTION = ?, CAMP_PRICE = ?, "
            + "CAMP_LIMIT = ?, LISTED_TIME = ?, SITE_STATE = ?, LOVED_COUNT = ?, "
            + "REPORTED_COUNT = ?, CAMP_LICENSE = ?, PICTURE1 = ?, PICTURE2 = ?, "
            + "PICTURE3 = ?, PICTURE4 = ?, PICTURE5 = ? WHERE CAMP_ID = ?";
    public static final String DELETE_STMT = "DELETE FROM CAMPSITE WHERE CAMP_ID = ?";
    public static final String FIND_BY_PK = "SELECT CAMP_ID, MEMBER_ID, CAMP_NAME, LOCATION, LATITUDE, LONGTITUDE, "
            + "CAMP_DESCRIPTION, CAMP_PRICE, CAMP_LIMIT, LISTED_TIME, SITE_STATE, LOVED_COUNT, REPORTED_COUNT, "
            + "CAMP_LICENSE, PICTURE1, PICTURE2, PICTURE3, PICTURE4, PICTURE5 FROM CAMPSITE WHERE CAMP_ID = ?";
    public static final String GET_ALL = "SELECT CAMP_ID, MEMBER_ID, CAMP_NAME, LOCATION, LATITUDE, LONGTITUDE, "
            + "CAMP_DESCRIPTION, CAMP_PRICE, CAMP_LIMIT, LISTED_TIME, SITE_STATE, LOVED_COUNT, REPORTED_COUNT, "
            + "CAMP_LICENSE, PICTURE1, PICTURE2, PICTURE3, PICTURE4, PICTURE5 FROM CAMPSITE ORDER BY CAMP_ID";
    public static final String GET_SEARCH = "SELECT CAMP_ID, MEMBER_ID, CAMP_NAME, LOCATION, CAMP_DESCRIPTION, PICTURE1 "
            + "FROM CAMPSITE WHERE CAMP_NAME LIKE ? OR LOCATION LIKE ?";

    static { // 一個環境只需要載入一次驅動
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        }
    }

    @Override
    public void add(CampsiteVO campsiteVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setInt(1, campsiteVO.getMemberId());
            pstmt.setString(2, campsiteVO.getCampName());
            pstmt.setString(3, campsiteVO.getLocation());
            pstmt.setDouble(4, campsiteVO.getLatitude());
            pstmt.setDouble(5, campsiteVO.getLongtitude());
            pstmt.setString(6, campsiteVO.getCampDescription());
            pstmt.setInt(7, campsiteVO.getCampPrice());
            pstmt.setInt(8, campsiteVO.getCampLimit());
            pstmt.setTimestamp(9, campsiteVO.getListedTime());
            pstmt.setInt(10, campsiteVO.getSiteState());
            pstmt.setInt(11, campsiteVO.getLovedCount());
            pstmt.setInt(12, campsiteVO.getReportedCount());
            pstmt.setBytes(13, campsiteVO.getCampLicense());
            pstmt.setBytes(14, campsiteVO.getPicture1());
            pstmt.setBytes(15, campsiteVO.getPicture2());
            pstmt.setBytes(16, campsiteVO.getPicture3());
            pstmt.setBytes(17, campsiteVO.getPicture4());
            pstmt.setBytes(18, campsiteVO.getPicture5());

            pstmt.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(CampsiteVO campsiteVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(UPDATE_STMT);

            pstmt.setInt(1, campsiteVO.getMemberId());
            pstmt.setString(2, campsiteVO.getCampName());
            pstmt.setString(3, campsiteVO.getLocation());
            pstmt.setDouble(4, campsiteVO.getLatitude());
            pstmt.setDouble(5, campsiteVO.getLongtitude());
            pstmt.setString(6, campsiteVO.getCampDescription());
            pstmt.setInt(7, campsiteVO.getCampPrice());
            pstmt.setInt(8, campsiteVO.getCampLimit());
            pstmt.setTimestamp(9, campsiteVO.getListedTime());
            pstmt.setInt(10, campsiteVO.getSiteState());
            pstmt.setInt(11, campsiteVO.getLovedCount());
            pstmt.setInt(12, campsiteVO.getReportedCount());
            pstmt.setBytes(13, campsiteVO.getCampLicense());
            pstmt.setBytes(14, campsiteVO.getPicture1());
            pstmt.setBytes(15, campsiteVO.getPicture2());
            pstmt.setBytes(16, campsiteVO.getPicture3());
            pstmt.setBytes(17, campsiteVO.getPicture4());
            pstmt.setBytes(18, campsiteVO.getPicture5());
            pstmt.setInt(19, campsiteVO.getCampId());

            pstmt.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
    }

    @Override
    public void delete(Integer campId) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(DELETE_STMT);

            pstmt.setInt(1, campId);

            pstmt.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
    }

    @Override
    public CampsiteVO findbyPrimaryKey(Integer campId) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        CampsiteVO campsiteVO = null;

        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(FIND_BY_PK);

            pstmt.setInt(1, campId);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                campsiteVO = new CampsiteVO();
                campsiteVO.setCampId(campId);
                campsiteVO.setMemberId(rs.getInt("MEMBER_ID"));
                campsiteVO.setCampName(rs.getString("CAMP_NAME"));
                campsiteVO.setLocation(rs.getString("LOCATION"));
                campsiteVO.setLatitude(rs.getDouble("LATITUDE"));
                campsiteVO.setLongtitude(rs.getDouble("LONGTITUDE"));
                campsiteVO.setCampDescription(rs.getString("CAMP_DESCRIPTION"));
                campsiteVO.setCampPrice(rs.getInt("CAMP_PRICE"));
                campsiteVO.setCampLimit(rs.getInt("CAMP_LIMIT"));
                campsiteVO.setListedTime(rs.getTimestamp("LISTED_TIME"));
                campsiteVO.setSiteState(rs.getInt("SITE_STATE"));
                campsiteVO.setLovedCount(rs.getInt("LOVED_COUNT"));
                campsiteVO.setReportedCount(rs.getInt("REPORTED_COUNT"));
                campsiteVO.setCampLicense(rs.getBytes("CAMP_LICENSE"));
                campsiteVO.setPicture1(rs.getBytes("PICTURE1"));
                campsiteVO.setPicture2(rs.getBytes("PICTURE2"));
                campsiteVO.setPicture3(rs.getBytes("PICTURE3"));
                campsiteVO.setPicture4(rs.getBytes("PICTURE4"));
                campsiteVO.setPicture5(rs.getBytes("PICTURE5"));
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }

            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
        return campsiteVO;
    }

    @Override
    public List<CampsiteVO> getAll() {
        List<CampsiteVO> campsiteList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        CampsiteVO campsiteVO = null;

        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(GET_ALL);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                campsiteVO = new CampsiteVO();
                campsiteVO.setCampId(rs.getInt("CAMP_ID"));
                campsiteVO.setMemberId(rs.getInt("MEMBER_ID"));
                campsiteVO.setCampName(rs.getString("CAMP_NAME"));
                campsiteVO.setLocation(rs.getString("LOCATION"));
                campsiteVO.setLatitude(rs.getDouble("LATITUDE"));
                campsiteVO.setLongtitude(rs.getDouble("LONGTITUDE"));
                campsiteVO.setCampDescription(rs.getString("CAMP_DESCRIPTION"));
                campsiteVO.setCampPrice(rs.getInt("CAMP_PRICE"));
                campsiteVO.setCampLimit(rs.getInt("CAMP_LIMIT"));
                campsiteVO.setListedTime(rs.getTimestamp("LISTED_TIME"));
                campsiteVO.setSiteState(rs.getInt("SITE_STATE"));
                campsiteVO.setLovedCount(rs.getInt("LOVED_COUNT"));
                campsiteVO.setReportedCount(rs.getInt("REPORTED_COUNT"));
                campsiteVO.setCampLicense(rs.getBytes("CAMP_LICENSE"));
                campsiteVO.setPicture1(rs.getBytes("PICTURE1"));
                campsiteVO.setPicture2(rs.getBytes("PICTURE2"));
                campsiteVO.setPicture3(rs.getBytes("PICTURE3"));
                campsiteVO.setPicture4(rs.getBytes("PICTURE4"));
                campsiteVO.setPicture5(rs.getBytes("PICTURE5"));

                campsiteList.add(campsiteVO);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }

            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
        return campsiteList;
    }

    @Override
    public List<CampsiteVO> getSearchCampsite(String campName) {
        List<CampsiteVO> campsiteList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        CampsiteVO campsiteVO = null;

        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(GET_SEARCH);

            pstmt.setString(1, "%" + campName + "%");
            pstmt.setString(2, "%" + campName + "%");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                campsiteVO = new CampsiteVO();
                campsiteVO.setCampName(rs.getString("CAMP_NAME"));
                campsiteVO.setLocation(rs.getString("LOCATION"));
                campsiteVO.setCampDescription(rs.getString("CAMP_DESCRIPTION"));
                campsiteVO.setPicture1(rs.getBytes("PICTURE1"));
                campsiteVO.setCampId(rs.getInt("CAMP_ID"));
                campsiteVO.setMemberId(rs.getInt("MEMBER_ID"));

                campsiteList.add(campsiteVO);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }

            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
        return campsiteList;
    }


    @Override
    public List<CampsiteVO> getAll(Map<String, String[]> map) {
        List<CampsiteVO> list = new ArrayList<>();

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        CampsiteVO campsiteVO = null;

        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            String finalSQL = "SELECT DISTINCT c.CAMP_ID, MEMBER_ID, CAMP_NAME, LOCATION, "
                    + "CAMP_DESCRIPTION, CAMP_PRICE, SITE_STATE, PICTURE1 "
                    + "FROM CAMPSITE c LEFT JOIN CAMPSITE_TENT_STATUS cts on c.CAMP_ID = cts.CAMP_ID"
                    + jdbcUtil_CompositeQuery_Campsite.get_WhereCondition(map);

            pstmt = con.prepareStatement(finalSQL);
            System.out.println("●●finalSQL(by DAO) = " + finalSQL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                campsiteVO = new CampsiteVO();
                campsiteVO.setCampName(rs.getString("CAMP_NAME"));
                campsiteVO.setLocation(rs.getString("LOCATION"));
                campsiteVO.setCampDescription(rs.getString("CAMP_DESCRIPTION"));
                campsiteVO.setCampPrice(rs.getInt("CAMP_PRICE"));
                campsiteVO.setSiteState(rs.getInt("SITE_STATE"));
                campsiteVO.setPicture1(rs.getBytes("PICTURE1"));
                campsiteVO.setCampId(rs.getInt("CAMP_ID"));
                campsiteVO.setMemberId(rs.getInt("MEMBER_ID"));

                list.add(campsiteVO); // Store the row in the List
            }
            System.out.println("DAO list= " + list);

            // Handle any SQL errors
        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        return list;
    }

    public void updateForOne(CampsiteVO campsiteVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(UPDATE_STMT);

            pstmt.setInt(1, campsiteVO.getMemberId());
            pstmt.setString(2, campsiteVO.getCampName());
            pstmt.setString(3, campsiteVO.getLocation());
            pstmt.setDouble(4, campsiteVO.getLatitude());
            pstmt.setDouble(5, campsiteVO.getLongtitude());
            pstmt.setString(6, campsiteVO.getCampDescription());
            pstmt.setInt(7, campsiteVO.getCampPrice());
            pstmt.setInt(8, campsiteVO.getCampLimit());
            pstmt.setTimestamp(9, campsiteVO.getListedTime());
            pstmt.setInt(10, campsiteVO.getSiteState());
            pstmt.setInt(11, campsiteVO.getLovedCount());
            pstmt.setInt(12, (campsiteVO.getReportedCount() + 1));
            pstmt.setBytes(13, campsiteVO.getCampLicense());
            pstmt.setBytes(14, campsiteVO.getPicture1());
            pstmt.setBytes(15, campsiteVO.getPicture2());
            pstmt.setBytes(16, campsiteVO.getPicture3());
            pstmt.setBytes(17, campsiteVO.getPicture4());
            pstmt.setBytes(18, campsiteVO.getPicture5());
            pstmt.setInt(19, campsiteVO.getCampId());

            pstmt.executeUpdate();

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }
}

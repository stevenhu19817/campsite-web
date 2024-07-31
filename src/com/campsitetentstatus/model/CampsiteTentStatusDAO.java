package com.campsitetentstatus.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.customerplan.model.CustomerPlanVO;

public class CampsiteTentStatusDAO implements CampsiteTentStatusDAO_interface {

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/GoCamping?serverTimezone=Asia/Taipei";
    public static final String USER = "David";
    public static final String PASSWORD = "123456";
    public static final String INSERT_STMT = "INSERT INTO CAMPSITE_TENT_STATUS (CAMP_ID, CAMP_OPENING_TIME, "
            + "EMPTY_CAMP_LEFT) VALUES (?, ?, ?)";
    public static final String UPDATE_STMT = "UPDATE CAMPSITE_TENT_STATUS SET EMPTY_CAMP_LEFT = ? "
            + "WHERE CAMP_ID = ? && CAMP_OPENING_TIME = ?";
    public static final String DELETE_STMT = "DELETE FROM CAMPSITE_TENT_STATUS "
            + "WHERE CAMP_ID = ? && CAMP_OPENING_TIME = ?";
    public static final String FIND_BY_PK = "SELECT CAMP_ID, CAMP_OPENING_TIME, EMPTY_CAMP_LEFT "
            + "FROM CAMPSITE_TENT_STATUS WHERE CAMP_ID = ? && CAMP_OPENING_TIME = ?";
    public static final String GET_ALL = "SELECT CAMP_ID, CAMP_OPENING_TIME, EMPTY_CAMP_LEFT "
            + "FROM CAMPSITE_TENT_STATUS ORDER BY CAMP_ID && CAMP_OPENING_TIME";
    public static final String GET_ALL_OF_ONE = "SELECT CAMP_ID, CAMP_OPENING_TIME, EMPTY_CAMP_LEFT "
            + "FROM CAMPSITE_TENT_STATUS WHERE CAMP_ID = ?";

    static { // 一個環境只需要載入一次驅動
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        }
    }

    @Override
    public void add(CampsiteTentStatusVO campsiteTentStatusVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setInt(1, campsiteTentStatusVO.getCampId());
            pstmt.setDate(2, campsiteTentStatusVO.getCampOpeningTime());
            pstmt.setInt(3, campsiteTentStatusVO.getEmptyCampLeft());

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
    public void update(CampsiteTentStatusVO campsiteTentStatusVO) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(UPDATE_STMT);

            pstmt.setInt(1, campsiteTentStatusVO.getEmptyCampLeft());
            pstmt.setInt(2, campsiteTentStatusVO.getCampId());
            pstmt.setDate(3, campsiteTentStatusVO.getCampOpeningTime());

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
    public void delete(Integer campId, Date campOpeningTime) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(DELETE_STMT);

            pstmt.setInt(1, campId);
            pstmt.setDate(2, campOpeningTime);

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
    public CampsiteTentStatusVO findbyPrimaryKey(Integer campId, Date campOpeningTime) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        CampsiteTentStatusVO campsiteTentStatusVO = null;

        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(FIND_BY_PK);

            pstmt.setInt(1, campId);
            pstmt.setDate(2, campOpeningTime);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                campsiteTentStatusVO = new CampsiteTentStatusVO();
                campsiteTentStatusVO.setCampId(campId);
                campsiteTentStatusVO.setCampOpeningTime(campOpeningTime);
                campsiteTentStatusVO.setEmptyCampLeft(rs.getInt("EMPTY_CAMP_LEFT"));
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
        return campsiteTentStatusVO;
    }

    @Override
    public List<CampsiteTentStatusVO> getAll() {
        List<CampsiteTentStatusVO> campsiteTentStatusList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        CampsiteTentStatusVO campsiteTentStatusVO = null;

        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(GET_ALL);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                campsiteTentStatusVO = new CampsiteTentStatusVO();
                campsiteTentStatusVO.setCampId(rs.getInt("CAMP_ID"));
                campsiteTentStatusVO.setCampOpeningTime(rs.getDate("CAMP_OPENING_TIME"));
                campsiteTentStatusVO.setEmptyCampLeft(rs.getInt("EMPTY_CAMP_LEFT"));

                campsiteTentStatusList.add(campsiteTentStatusVO);
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
        return campsiteTentStatusList;
    }

    @Override
    public List<CampsiteTentStatusVO> getAllOfOne(Integer campId) {
        List<CampsiteTentStatusVO> campsiteTentStatusList = new ArrayList<>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        CampsiteTentStatusVO campsiteTentStatusVO = null;

        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(GET_ALL_OF_ONE);

            pstmt.setInt(1, campId);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                campsiteTentStatusVO = new CampsiteTentStatusVO();
                campsiteTentStatusVO.setCampId(campId);
                campsiteTentStatusVO.setCampOpeningTime(rs.getDate("CAMP_OPENING_TIME"));
                campsiteTentStatusVO.setEmptyCampLeft(rs.getInt("EMPTY_CAMP_LEFT"));

                campsiteTentStatusList.add(campsiteTentStatusVO);
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
        return campsiteTentStatusList;
    }

    @Override
    public CampsiteTentStatusVO getByCampId(Integer campId) {
        // TODO Auto-generated method stub
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        CampsiteTentStatusVO campsiteTentStatusVO = null;


        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            pstmt = con.prepareStatement(GET_ALL_OF_ONE);

            pstmt.setInt(1, campId);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                campsiteTentStatusVO = new CampsiteTentStatusVO();
                campsiteTentStatusVO.setCampId(campId);
                campsiteTentStatusVO.setCampOpeningTime(rs.getDate("CAMP_OPENING_TIME"));
                campsiteTentStatusVO.setEmptyCampLeft(rs.getInt("EMPTY_CAMP_LEFT"));
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
        return campsiteTentStatusVO;
    }
}

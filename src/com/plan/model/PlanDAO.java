package com.plan.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlanDAO implements PlanDAO_interface {

	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/GoCamping?serverTimezone=Asia/Taipei";
	public static final String USER = "David";
	public static final String PASSWORD = "123456";
	public static final String INSERT_STMT = "INSERT INTO PLAN (CAMP_ID, PLAN_NAME, PLAN_GUEST_LIMIT, "
			+ "PLAN_AGE_LIMIT, PLAN_PRICE,PlAN_OUTLINE) VALUES (?, ?, ?, ?, ?, ?)";
	public static final String UPDATE_STMT = "UPDATE PLAN SET CAMP_ID=?, PLAN_NAME = ?, PLAN_GUEST_LIMIT = ?, "
			+ "PLAN_AGE_LIMIT = ?, PLAN_PRICE = ?, PlAN_OUTLINE=? WHERE PLAN_ID = ?";
	public static final String DELETE_STMT = "DELETE FROM PLAN WHERE PLAN_ID = ?";
	public static final String FIND_BY_CAMP_ID = "SELECT PLAN_ID, CAMP_ID, PLAN_NAME, PlAN_OUTLINE, PLAN_GUEST_LIMIT, PLAN_AGE_LIMIT, "
			+ "PLAN_PRICE, PlAN_OUTLINE FROM PLAN WHERE CAMP_ID = ?";
	public static final String GET_ALL = "SELECT PLAN_ID, CAMP_ID, PLAN_NAME, PlAN_OUTLINE, PLAN_GUEST_LIMIT, PLAN_AGE_LIMIT, "
			+ "PLAN_PRICE, PlAN_OUTLINE FROM PLAN ORDER BY PLAN_ID";
	public final String GET_ONE_CAMPID = "SELECT PLAN_ID, CAMP_ID, PLAN_NAME, PLAN_GUEST_LIMIT, PLAN_AGE_LIMIT, PLAN_PRICE, PlAN_OUTLINE FROM PLAN WHERE CAMP_ID = ?";
	public final String DELETE_ONE_CAMPID = "DELETE FROM PLAN WHERE CAMP_ID = ?";
	
	static { 
		
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	
	@Override
	public void add(PlanVO planVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			
//			pstmt.setInt(1, planVO.getPlanId());
			pstmt.setInt(1, planVO.getCampId());
			pstmt.setString(2, planVO.getPlanName());
			pstmt.setInt(3, planVO.getPlanGuestLimit());
			pstmt.setInt(4, planVO.getPlanAgeLimit());
			pstmt.setInt(5, planVO.getPlanPrice());
			pstmt.setString(6, planVO.getPlanOutline());
			
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
	public void update(PlanVO planVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, planVO.getCampId());
			pstmt.setString(2, planVO.getPlanName());
			pstmt.setInt(3, planVO.getPlanGuestLimit());
			pstmt.setInt(4, planVO.getPlanAgeLimit());
			pstmt.setInt(5, planVO.getPlanPrice());
			pstmt.setString(6, planVO.getPlanOutline());
			pstmt.setInt(7, planVO.getPlanId());

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
	public void delete(Integer planId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, planId);

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
	public ArrayList<PlanVO> findbyPrimaryKey(Integer campId) {
		ArrayList<PlanVO> planList = new ArrayList<PlanVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PlanVO planVO = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_CAMP_ID);

			pstmt.setInt(1, campId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				planVO = new PlanVO();
				planVO.setPlanId(rs.getInt("PLAN_ID"));
				planVO.setCampId(campId);
				planVO.setPlanName(rs.getString("PLAN_NAME"));
				planVO.setPlanGuestLimit(rs.getInt("PLAN_GUEST_LIMIT"));
				planVO.setPlanAgeLimit(rs.getInt("PLAN_AGE_LIMIT"));
				planVO.setPlanPrice(rs.getInt("PLAN_PRICE"));
				planVO.setPlanOutline(rs.getString("PLAN_OUTLINE"));
				
				planList.add(planVO);
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
		return planList;
	}

	@Override
	public List<PlanVO> getAll() {
		List<PlanVO> planList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PlanVO planVO = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				planVO = new PlanVO();
				planVO.setPlanId(rs.getInt("PLAN_ID"));
				planVO.setCampId(rs.getInt("CAMP_ID"));
				planVO.setPlanName(rs.getString("PLAN_NAME"));
				planVO.setPlanGuestLimit(rs.getInt("PLAN_GUEST_LIMIT"));
				planVO.setPlanAgeLimit(rs.getInt("PLAN_AGE_LIMIT"));
				planVO.setPlanPrice(rs.getInt("PLAN_PRICE"));
				planVO.setPlanOutline(rs.getString("PLAN_OUTLINE"));
				
				planList.add(planVO);
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
		return planList;
	}

	@Override
	public List<PlanVO> findbyCampId(Integer campId) {
		// TODO Auto-generated method stub
		List<PlanVO> planlist = new ArrayList<PlanVO>();
		PlanVO planVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_CAMPID);
			
			pstmt.setInt(1, campId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				planVO = new PlanVO();
				planVO.setPlanId(rs.getInt("PLAN_ID"));
				planVO.setCampId(rs.getInt("CAMP_ID"));
				planVO.setPlanName(rs.getString("PLAN_NAME"));
				planVO.setPlanGuestLimit(rs.getInt("PLAN_GUEST_LIMIT"));
				planVO.setPlanAgeLimit(rs.getInt("PLAN_AGE_LIMIT"));
				planVO.setPlanPrice(rs.getInt("PLAN_PRICE"));
				planVO.setPlanOutline(rs.getString("PLAN_OUTLINE"));
				planlist.add(planVO);
			}
			// Handle any driver errors
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
		return planlist;
	}

	
	
	
	@Override
	public void deletebyCampId(Integer campId) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_ONE_CAMPID);
			
			pstmt.setInt(1, campId);
			
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public ArrayList<PlanVO> getCampId(Integer campId) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PlanVO planVO = new PlanVO();
		ArrayList<PlanVO> list = new ArrayList<PlanVO>();

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_CAMPID);

			pstmt.setInt(1, campId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				planVO = new PlanVO();
				planVO.setPlanId(rs.getInt("PLAN_ID"));
				planVO.setCampId(rs.getInt("CAMP_ID"));
				planVO.setPlanName(rs.getString("PLAN_NAME"));
				planVO.setPlanGuestLimit(rs.getInt("PLAN_GUEST_LIMIT"));
				planVO.setPlanAgeLimit(rs.getInt("PLAN_AGE_LIMIT"));
				planVO.setPlanPrice(rs.getInt("PLAN_PRICE"));
				planVO.setPlanOutline(rs.getString("PLAN_OUTLINE"));
				list.add(planVO);
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
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
	
}


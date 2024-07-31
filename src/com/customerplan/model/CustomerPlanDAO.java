package com.customerplan.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CustomerPlanDAO implements CustomerPlanDAO_interface{

	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/GoCamping?serverTimezone=Asia/Taipei";
	public static final String USER = "David";
	public static final String PASSWORD = "123456";
	public static final String INSERT_STMT = "INSERT INTO CUSTOMER_PLAN (CAMP_ORDER_ID, PLAN_ID, PLAN_GUEST_NUMBER, "
			+ "PLAN_BATCH, PLAN_ORDER_PRICE) VALUES (?, ?, ?, ?, ?)";
	public static final String UPDATE_STMT = "UPDATE CUSTOMER_PLAN SET PLAN_GUEST_NUMBER = ?, PLAN_BATCH = ?, "
			+ "PLAN_ORDER_PRICE = ? WHERE CAMP_ORDER_ID = ? && PLAN_ID = ?";
	public static final String DELETE_STMT = "DELETE FROM CUSTOMER_PLAN WHERE CAMP_ORDER_ID = ? && PLAN_ID = ?";
	public static final String FIND_BY_PK = "SELECT CAMP_ORDER_ID, PLAN_ID, PLAN_GUEST_NUMBER, PLAN_BATCH, "
			+ "PLAN_ORDER_PRICE FROM CUSTOMER_PLAN WHERE CAMP_ORDER_ID = ? && PLAN_ID = ?";
	public static final String GET_ALL = "SELECT CAMP_ORDER_ID, PLAN_ID, PLAN_GUEST_NUMBER, PLAN_BATCH, "
			+ "PLAN_ORDER_PRICE FROM CUSTOMER_PLAN ORDER BY CAMP_ORDER_ID && PLAN_ID";


	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void add(CustomerPlanVO customerPlanVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, customerPlanVO.getCampOrderId());
			pstmt.setInt(2, customerPlanVO.getPlanId());
			pstmt.setInt(3, customerPlanVO.getPlanGuestNumber());
			pstmt.setString(4, customerPlanVO.getPlanBatch());
			pstmt.setInt(5, customerPlanVO.getPlanOrderPrice());

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
	public void update(CustomerPlanVO customerPlanVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE_STMT);

			pstmt.setInt(1, customerPlanVO.getPlanGuestNumber());
			pstmt.setString(2, customerPlanVO.getPlanBatch());
			pstmt.setInt(3, customerPlanVO.getPlanOrderPrice());
			pstmt.setInt(4, customerPlanVO.getCampOrderId());
			pstmt.setInt(5, customerPlanVO.getPlanId());

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
	public void delete(Integer campOrderId, Integer planId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, campOrderId);
			pstmt.setInt(2, planId);

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
	public CustomerPlanVO findbyPrimaryKey(Integer campOrderId, Integer planId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CustomerPlanVO customerPlanVO = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(FIND_BY_PK);

			pstmt.setInt(1, campOrderId);
			pstmt.setInt(2, planId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				customerPlanVO = new CustomerPlanVO();
				customerPlanVO.setCampOrderId(campOrderId);
				customerPlanVO.setPlanId(planId);
				customerPlanVO.setPlanGuestNumber(rs.getInt("PLAN_GUEST_NUMBER"));
				customerPlanVO.setPlanBatch(rs.getString("PLAN_BATCH"));
				customerPlanVO.setPlanOrderPrice(rs.getInt("PLAN_ORDER_PRICE"));
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
		return customerPlanVO;
	}

	@Override
	public List<CustomerPlanVO> getAll() {
		List<CustomerPlanVO> customerPlanList = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CustomerPlanVO customerPlanVO = null;

		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				customerPlanVO = new CustomerPlanVO();
				customerPlanVO.setCampOrderId(rs.getInt("CAMP_ORDER_ID"));
				customerPlanVO.setPlanId(rs.getInt("PLAN_ID"));
				customerPlanVO.setPlanGuestNumber(rs.getInt("PLAN_GUEST_NUMBER"));
				customerPlanVO.setPlanBatch(rs.getString("PLAN_BATCH"));
				customerPlanVO.setPlanOrderPrice(rs.getInt("PLAN_ORDER_PRICE"));

				customerPlanList.add(customerPlanVO);
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
		return customerPlanList;
	}
	public void insert2 (CustomerPlanVO customerPlanVO , Connection con) {

		PreparedStatement pstmt = null;

		try {

     		pstmt = con.prepareStatement(INSERT_STMT);

     		pstmt.setInt(1, customerPlanVO.getCampOrderId());
			pstmt.setInt(2, customerPlanVO.getPlanId());
			pstmt.setInt(3, customerPlanVO.getPlanGuestNumber());
			pstmt.setString(4, customerPlanVO.getPlanBatch());
			pstmt.setInt(5, customerPlanVO.getPlanOrderPrice());

			Statement stmt=	con.createStatement();
			//stmt.executeUpdate("set auto_increment_offset=7001;"); //自增主鍵-初始值
			stmt.executeUpdate("set auto_increment_increment=1;");   //自增主鍵-遞增
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}

	}
}

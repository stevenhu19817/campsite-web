package com.adminList.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.ldap.Rdn;

import com.mysql.cj.jdbc.Driver;
import java.util.ArrayList;

public class AdminListDAO implements AdminListDao_interface{
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/GoCamping?serverTimezone=Asia/Taipei";
	public static final String USER = "David";
	public static final String PASSWORD = "123456";
	public static final String INSERT_STMT = "INSERT INTO Admin_List VALUES(?,?,?)";
	public static final String UPDATE_STMT = "UPDATE Admin_List SET admin_Pwd = ?, admin_Name = ? WHERE admin_Id = ?";
	public static final String DELETE_STMT = "DELETE FROM Admin_List WHERE admin_Id = ?";
	public static final String findByPrimaryKey = "SELECT * FROM Admin_List WHERE admin_Id =?";
	public static final String GET_ALL ="SELECT* FROM Admin_List ORDER BY admin_Id";
	static {// 驅動載入一次，下面不用再打
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	@Override
	public void add(AdminListVO AdminListVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);// 取得連線
			pstmt = con.prepareStatement(INSERT_STMT);// 送出SQL指令
			pstmt.setInt(1, AdminListVO.getAdminId());
			pstmt.setString(2, AdminListVO.getAdminPwd());
			pstmt.setString(3, AdminListVO.getAdminName());
			pstmt.executeUpdate();// 執行
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
	public void update(AdminListVO AdminListVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				con = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = con.prepareStatement(UPDATE_STMT);

				pstmt.setInt(3, AdminListVO.getAdminId());
				pstmt.setString(1, AdminListVO.getAdminPwd());
				pstmt.setString(2, AdminListVO.getAdminName());
			

				pstmt.executeUpdate();

				// Handle any driver errors
			} catch (SQLException se) {
				se.printStackTrace();
				// Clean up JDBC resources
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

	@Override
	public void delete(int adminId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, adminId);
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			se.printStackTrace();
			// Clean up JDBC resources
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

	@Override
	public AdminListVO findByPrimaryKey(Integer adminId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AdminListVO AdminListVO = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(findByPrimaryKey);
			pstmt.setInt(1, adminId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// 利用Dept Bean包裝著查詢出來的各個欄位值
				AdminListVO = new AdminListVO();
				AdminListVO.setAdminId(adminId);
				AdminListVO.setAdminPwd(rs.getString("admin_Pwd"));
				AdminListVO.setAdminName(rs.getString("admin_Name"));

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

		return AdminListVO;
	}


	@Override
	public List<AdminListVO> getALL() {
		List<AdminListVO> adminlistvo = new ArrayList<>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// 利用Dept Bean包裝著查詢出來的各個欄位值
				AdminListVO AdminListVO = new AdminListVO();
			    AdminListVO.setAdminId(rs.getInt("admin_Id"));
			    AdminListVO.setAdminPwd(rs.getString("admin_Pwd"));
			    AdminListVO.setAdminName(rs.getString("admin_Name"));
				//用集合容器收集包裝好的Dept bean
			    adminlistvo.add(AdminListVO);

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

		return adminlistvo;
	}

}

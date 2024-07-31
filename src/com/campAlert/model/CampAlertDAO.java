package com.campAlert.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CampAlertDAO implements CampAlertDao_interface {
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/GoCamping?serverTimezone=Asia/Taipei";
	public static final String USER = "David";
	public static final String PASSWORD = "123456";
	public static final String INSERT_STMT = "INSERT INTO camp_alert(member_Id, camp_Id,report_Time,content, picture1, picture2, picture3,report_Status,handeler) VALUES(?, ?, ?, ?, ?, ?, ?,?,?)";
	public static final String UPDATE = "UPDATE CAMP_ALERT SET member_Id=?,camp_Id=?, report_Time=?, content=?, picture1=?,picture2=?, picture3=?,report_Status=?,handeler=? WHERE alert_Id=? ";
	public static final String DELETE = "DELETE FROM CAMP_ALERT WHERE alert_Id=?";
	public static final String GET_ONE_STMT = "SELECT * FROM CAMP_ALERT WHERE alert_Id=?";
	private static final String GET_ALL_STMT = 
			"SELECT*FROM Camp_Alert ORDER BY alert_Id";
	
	static {
		try {
			Class.forName(DRIVER);			
		}catch(ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void insert(CampAlertVO DAO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			

			pstmt.setInt(1, DAO.getMemberId());
			pstmt.setInt(2, DAO.getCampId());
			pstmt.setString(3, DAO.getReportTime());
			pstmt.setString(4, DAO.getContent());
			pstmt.setBytes(5,DAO.getPicture1());
			pstmt.setBytes(6,DAO.getPicture2());
			pstmt.setBytes(7,DAO.getPicture3());
			pstmt.setInt(8, DAO.getReportStatus());
			pstmt.setInt(9, DAO.getHandeler());
			pstmt.executeUpdate();
						
			
			
		} catch(SQLException se) {
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
	public void update(CampAlertVO campalertvo) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			

			pstmt.setInt(10, campalertvo.getAlertId());
			pstmt.setInt(1, campalertvo.getMemberId());
			pstmt.setInt(2, campalertvo.getCampId());
			pstmt.setString(3, campalertvo.getReportTime());
			pstmt.setString(4, campalertvo.getContent());
			pstmt.setBytes(5,campalertvo.getPicture1());
			pstmt.setBytes(6,campalertvo.getPicture2());
			pstmt.setBytes(7,campalertvo.getPicture3());
			pstmt.setInt(8, campalertvo.getReportStatus());
			pstmt.setInt(9, campalertvo.getHandeler());
			
			pstmt.executeUpdate();
			
			
									
			
			
		} catch(SQLException se) {
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
	public void delete(int alertId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, alertId);
			
			pstmt.executeUpdate();
			
		} catch(SQLException se){
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
	public CampAlertVO findByPrimaryKey(Integer alertId) {
		CampAlertVO vo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, alertId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				vo = new CampAlertVO();
				vo.setAlertId(rs.getInt("alert_Id"));
				vo.setMemberId(rs.getInt("member_Id"));
				vo.setCampId(rs.getInt("camp_Id"));
				vo.setReportTime(rs.getString("report_Time"));
				vo.setContent(rs.getString("content"));
				vo.setPicture1(rs.getBytes("picture1"));
				vo.setPicture2(rs.getBytes("PICTURE2"));
				vo.setPicture3(rs.getBytes("picture3"));
				vo.setReportStatus(rs.getInt("report_Status"));
				vo.setHandeler(rs.getInt("handeler"));
			}
		} catch(SQLException se) {
			se.printStackTrace();			
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
	
		return vo;
	}

	@Override
	public List<CampAlertVO> getALL() {
		List<CampAlertVO> list = new ArrayList<CampAlertVO>();
		CampAlertVO vo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				vo = new CampAlertVO();
				vo.setAlertId(rs.getInt("alert_Id"));
				vo.setMemberId(rs.getInt("member_Id"));
				vo.setCampId(rs.getInt("camp_Id"));
				vo.setReportTime(rs.getString("report_Time"));
				vo.setContent(rs.getString("content"));
				vo.setPicture1(rs.getBytes("picture1"));
				vo.setPicture2(rs.getBytes("Picture2"));
				vo.setPicture3(rs.getBytes("picture3"));
				vo.setReportStatus(rs.getInt("report_Status"));
				vo.setHandeler(rs.getInt("handeler"));
				list.add(vo); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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


public static byte[] getPictureByteArray(String path) throws IOException {
	FileInputStream fis = new FileInputStream(path);
	byte[] buffer = new byte[fis.available()];
	fis.read(buffer);
	fis.close();
	return buffer;
}

@Override
public void updateStatus(CampAlertVO campAlertVO) {
	Connection con = null;
	PreparedStatement pstmt = null;
	
	try {
		con = DriverManager.getConnection(URL, USER, PASSWORD);
		pstmt = con.prepareStatement(UPDATE);
		

		pstmt.setInt(10, campAlertVO.getAlertId());
		pstmt.setInt(1, campAlertVO.getMemberId());
		pstmt.setInt(2, campAlertVO.getCampId());
		pstmt.setString(3, campAlertVO.getReportTime());
		pstmt.setString(4, campAlertVO.getContent());
		pstmt.setBytes(5,campAlertVO.getPicture1());
		pstmt.setBytes(6,campAlertVO.getPicture2());
		pstmt.setBytes(7,campAlertVO.getPicture3());
		pstmt.setInt(8, 1);
		pstmt.setInt(9, campAlertVO.getHandeler());
		
		pstmt.executeUpdate();
			
	} catch(SQLException se) {
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




}

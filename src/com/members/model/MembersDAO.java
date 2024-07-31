package com.members.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MembersDAO implements MembersDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/camping");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO members (name, password, email, thumbnail) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT member_id, name, phone, email, membership, member_status, thumbnail, address FROM members order by member_id";
	private static final String GET_ONE_STMT = "SELECT member_id, name, phone, email, membership, member_status, thumbnail, address, password FROM members where member_id = ?";
	private static final String GET_BY_EMAIL = "SELECT email, member_id, password, name FROM members where email = ?";
	private static final String UPDATE = "UPDATE members set name=?, phone=?, membership=?, member_status=?, thumbnail=?, address=? where member_id = ?";
	private static final String UPDATE_PASSWORD = "UPDATE members set password=? where member_id=?";

	@Override
	public void insert(MembersVO membersVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setString(1, membersVO.getName());
			pstmt.setString(2, membersVO.getPassword());
			pstmt.setString(3, membersVO.getEmail());
			pstmt.setBytes(4, membersVO.getThumbnail());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} if (con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void update(MembersVO membersVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, membersVO.getName());
			pstmt.setString(2, membersVO.getPhone());
			pstmt.setInt(3, membersVO.getMembership());
			pstmt.setInt(4, membersVO.getMemberStatus());
			pstmt.setBytes(5, membersVO.getThumbnail());
			pstmt.setString(6, membersVO.getAddress());
			pstmt.setInt(7, membersVO.getMemberId());
			
			pstmt.executeUpdate();
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} if (con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public MembersVO findByPrimaryKey(Integer memberId) {
		// TODO Auto-generated method stub
		MembersVO membersVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, memberId);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				membersVO = new MembersVO();
				membersVO.setAddress(rs.getString("address"));
				membersVO.setEmail(rs.getNString("email"));
				membersVO.setMemberId(rs.getInt("member_id"));
				membersVO.setMembership(rs.getInt("membership"));
				membersVO.setMemberStatus(rs.getInt("member_status"));
				membersVO.setName(rs.getString("name"));
				membersVO.setPhone(rs.getString("phone"));
				membersVO.setThumbnail(rs.getBytes("thumbnail"));
				membersVO.setPassword(rs.getString("password"));
				byte[] imagesBytes = rs.getBytes("thumbnail");
				if (imagesBytes != null) {
					String base64Img = Base64.getEncoder().encodeToString(imagesBytes);
					membersVO.setBase64Image(base64Img);
				}
				
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return membersVO;
	}
	
	@Override
	public MembersVO findByEmail(String email) {
		MembersVO membersVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_BY_EMAIL);
			
			pstmt.setString(1, email);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				membersVO = new MembersVO();
				membersVO.setMemberId(rs.getInt("member_id"));
				membersVO.setPassword(rs.getString("password"));
				membersVO.setName(rs.getString("name"));
				membersVO.setEmail(rs.getString("email"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return membersVO;
	}

	@Override
	public List<MembersVO> getAll() {
		// TODO Auto-generated method stub
		List<MembersVO> list = new ArrayList<MembersVO>();
		MembersVO memVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memVO = new MembersVO();
				memVO.setAddress(rs.getString("address"));
				memVO.setEmail(rs.getNString("email"));
				memVO.setMemberId(rs.getInt("member_id"));
				memVO.setMembership(rs.getInt("membership"));
				memVO.setMemberStatus(rs.getInt("member_status"));
				memVO.setName(rs.getString("name"));
				memVO.setPhone(rs.getString("phone"));
				byte[] imagesBytes = rs.getBytes("thumbnail");
				if (imagesBytes != null) {
					String base64Img = Base64.getEncoder().encodeToString(imagesBytes);
					memVO.setBase64Image(base64Img);
				}
				list.add(memVO);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	
	
	@Override
	public void updatePassword(MembersVO membersVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PASSWORD);
			
			pstmt.setString(1, membersVO.getPassword());
			pstmt.setInt(2, membersVO.getMemberId());
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} if (con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}


	
}

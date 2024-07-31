package com.Product.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;



public class ProductJDBCDAO implements Product_interface {
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/gocamping?serverTimezone=Asia/Taipei";
	public static final String USER = "David";
	public static final String PASSWORD = "123456";
	public static final String INSERT_STMT = "INSERT INTO commodity( product_name, product_sort, price, inventory, admin_id, situation, descript, picture1, picture2, picture3) VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String UPDATE = "UPDATE commodity SET product_name=?, product_sort=?, price=?, inventory=?, admin_id=?, situation=?, descript=?, picture1=?, picture2=?, picture3=? WHERE product_no=? ";
	public static final String DELETE = "DELETE FROM commodity WHERE product_no=?";
	public static final String GET_ONE_STMT = "SELECT * FROM commodity WHERE PRODUCT_no=?";
	public static final String GET_ALL_STMT = "SELECT * FROM commodity ORDER BY PRODUCT_no";
	public static final String GET_STMT = "SELECT * FROM commodity ORDER BY PRODUCT_no";
	public static final String GET_ONE_NAME = "SELECT * FROM commodity WHERE product_name like \"%\"" + "?" + "\"%\"";
	
	static {
		try {
			Class.forName(DRIVER);			
		}catch(ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}
	

	@Override
	public void insert(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);
			

			pstmt.setString(1, productVO.getPname());
			pstmt.setString(2, productVO.getPsort());
			pstmt.setInt(3, productVO.getPrice());
			pstmt.setInt(4, productVO.getInventory());
			pstmt.setInt(5,productVO.getAdmin_id());
			pstmt.setInt(6, productVO.getSituation());
			pstmt.setString(7, productVO.getDescript());
			
			
			
			pstmt.setBytes(8,productVO.getPicture1());
			pstmt.setBytes(9,productVO.getPicture2());
			pstmt.setBytes(10,productVO.getPicture3());
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
	public void update(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);
			
			
			pstmt.setString(1, productVO.getPname());
			pstmt.setString(2, productVO.getPsort());
			pstmt.setInt(3, productVO.getPrice());
			pstmt.setInt(4, productVO.getInventory());
			pstmt.setInt(5, productVO.getAdmin_id());
			pstmt.setInt(6, productVO.getSituation());
			pstmt.setString(7, productVO.getDescript());
			pstmt.setBytes(8,productVO.getPicture1());
			pstmt.setBytes(9,productVO.getPicture2());
			pstmt.setBytes(10, productVO.getPicture3());
			pstmt.setInt(11, productVO.getProductno());
			
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
	public void delete(Integer productno) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, productno);
			
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
	public ProductVO findByPrimaryKey(Integer productno) {
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, productno);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProductno(rs.getInt("product_no"));
				productVO.setPname(rs.getString("product_name"));
				productVO.setPsort(rs.getString("product_sort"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setInventory(rs.getInt("inventory"));
				productVO.setAdmin_id(rs.getInt("admin_id"));
				productVO.setSituation(rs.getInt("situation"));
				productVO.setDescript(rs.getString("descript"));
				productVO.setPicture1(rs.getBytes("picture1"));
				productVO.setPicture2(rs.getBytes("picture2"));
				productVO.setPicture3(rs.getBytes("picture3"));
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
	
		return productVO;
	}

	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO =null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productVO = new ProductVO();
				productVO.setAdmin_id(rs.getInt("admin_id"));
				productVO.setProductno(rs.getInt("product_no"));
				productVO.setPname(rs.getString("product_name"));
				productVO.setPsort(rs.getString("product_sort"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setInventory(rs.getInt("inventory"));
				productVO.setSituation(rs.getInt("situation"));
				productVO.setDescript(rs.getString("descript"));
				list.add(productVO);				
			}
			
		}  catch(SQLException se) {
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
		return list;
	}

	@Override
	public ProductVO findBypname(String pname) {
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_NAME);
			
			pstmt.setString(1, pname);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProductno(rs.getInt("product_no"));
				productVO.setPname(rs.getString("product_name"));
				productVO.setPsort(rs.getString("product_sort"));
				productVO.setPrice(rs.getInt("price"));
				productVO.setInventory(rs.getInt("inventory"));
				productVO.setAdmin_id(rs.getInt("admin_id"));
				productVO.setSituation(rs.getInt("situation"));
				productVO.setDescript(rs.getString("descript"));
				productVO.setPicture1(rs.getBytes("picture1"));
				productVO.setPicture2(rs.getBytes("picture2"));
				productVO.setPicture3(rs.getBytes("picture3"));
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
	
		return productVO;
	
	}

	
//	public static byte[] getPictureByteArray(String path) throws IOException {
//		FileInputStream fis = new FileInputStream(path);
//		byte[] buffer = new byte[fis.available()];
//		fis.read(buffer);
//		fis.close();
//		return buffer;
//	}

}

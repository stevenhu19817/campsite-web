package com.post.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PostDAO implements PostDAO_Interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/camping");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO post (author_id, title, article, created) VALUES (?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE post set title=?, article=?, created=? where post_id = ?";
	private static final String DELETE = "DELETE FROM post where post_id = ?";
	private static final String GET_ONE_BY_AUTHOR = "SELECT author_id, post_id, title, article, created FROM post where author_id= ? order by created desc";
	private static final String GET_ONE_BY_POSTID = "SELECT author_id, post_id, title, article, created FROM post where post_id= ?";
	private static final String GET_ALL_STMT = "SELECT author_id, post_id, title, article, created FROM post order by created desc";

	@Override
	public void insert(PostVO postVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, postVO.getAuthorId());
			pstmt.setString(2, postVO.getTitle());
			pstmt.setString(3, postVO.getArticle());
			pstmt.setTimestamp(4, timestamp);

			pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
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
	public void update(PostVO postVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;
		java.util.Date date = new java.util.Date();
		java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, postVO.getTitle());
			pstmt.setString(2, postVO.getArticle());
			pstmt.setTimestamp(3, timestamp);
			pstmt.setInt(4, postVO.getPostId());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (con != null) {
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
	public void delete(Integer articleId) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, articleId);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<PostVO> findByAuthor(Integer authorId) {
		List<PostVO> list = new ArrayList<PostVO>();
		PostVO post = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_AUTHOR);

			pstmt.setInt(1, authorId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				post = new PostVO();
				post.setPostId(rs.getInt("post_id"));
				post.setAuthorId(rs.getInt("author_id"));
				post.setTitle(rs.getString("title"));
				post.setArticle(rs.getString("article"));
				post.setCreated(rs.getTimestamp("created"));
				post.setPassed(getTimeAgo(rs.getTimestamp("created")));
				list.add(post);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		// TODO Auto-generated method stub
		return list;
	}

	public PostVO findByPostId(Integer postId) {
		PostVO post = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_POSTID);

			pstmt.setInt(1, postId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				post = new PostVO();
				post.setPostId(rs.getInt("post_id"));
				post.setAuthorId(rs.getInt("author_id"));
				post.setTitle(rs.getString("title"));
				post.setArticle(rs.getString("article"));
				post.setCreated(rs.getTimestamp("created"));
				post.setPassed(getTimeAgo(rs.getTimestamp("created")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		// TODO Auto-generated method stub
		return post;
	}

	@Override
	public List<PostVO> getAll() {
		List<PostVO> list = new ArrayList<PostVO>();
		PostVO postVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				postVO = new PostVO();
				postVO.setPostId(rs.getInt("post_id"));
				postVO.setAuthorId(rs.getInt("author_id"));
				postVO.setTitle(rs.getString("title"));
				postVO.setArticle(rs.getString("article"));
				postVO.setCreated(rs.getTimestamp("created"));
				postVO.setPassed(getTimeAgo(rs.getTimestamp("created")));
				list.add(postVO);
			}
		} catch (Exception e) {
			// TODO: handle exception
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

	public String getTimeAgo(Timestamp timestamp) {
		long created = timestamp.getTime();
		Date date = new Date();
		long current = date.getTime();
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(created);
		int calMonth = cal.get(Calendar.MONTH);
		int calDays = cal.get(Calendar.DATE);
		int calYears = cal.get(Calendar.YEAR);
		long diffInSec = (current - created) / 1000;
		long min = diffInSec / 60;
		long hrs = diffInSec / 3600;
		long days = diffInSec / 86400;
		long years = diffInSec / 31207680;
		if (diffInSec <= 60) {
			return diffInSec + "秒前";
		} else if (min <= 60) {
			return min + "分鐘前";
		} else if (hrs <= 24) {
			return hrs + "小時前";
		} else if (days <= 48) {
			return days + "天前";
		} else if (days > 48) {
			return calMonth + "月" + calDays + "日";
		} else {
			if (years >= 1) {
				return calYears + "年" + calMonth + "月" + calDays + "日";
			}
		}
		return null;
	}
}

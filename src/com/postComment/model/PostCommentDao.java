package com.postComment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.post.model.PostVO;

public class PostCommentDao implements postCommentDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/gocamping?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "123456";

	private static final String INSERT_STMT = "INSERT INTO post_comment (POST_ID, MEMBER_ID, CONTENT, TIMESTAMP) VALUES (?, ?, ?, ?)";
	private static final String UPDATE = "UPDATE post_comment set CONTENT=? where COMMENT_ID = ?";
	private static final String DELETE = "DELETE FROM post_comment where COMMENT_ID = ?";
	private static final String GET_ALL = "SELECT * FROM post_comment where post_id= ? order by TIMESTAMP desc";

	@Override
	public void insert(PostCommentVO commentVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			java.util.Date date = new java.util.Date();
			java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
			pstmt.setInt(1, commentVO.getPostId());
			pstmt.setInt(2, commentVO.getMemberId());
			pstmt.setString(3, commentVO.getContent());
			pstmt.setTimestamp(4, timestamp);

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void update(PostCommentVO commentVO) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, commentVO.getContent());
			pstmt.setInt(2, commentVO.getCommentId());

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void delete(Integer commentId) {
		// TODO Auto-generated method stub
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, commentId);

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public List<PostCommentVO> findByPostId(Integer postId) {
		// TODO Auto-generated method stub

		List<PostCommentVO> list = new ArrayList<PostCommentVO>();
		PostCommentVO pcVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL);
			
			pstmt.setInt(1, postId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				pcVO = new PostCommentVO();
				pcVO.setCommentId(rs.getInt("comment_id"));
				pcVO.setContent(rs.getString("content"));
				pcVO.setCreated(rs.getTimestamp("timestamp"));
				pcVO.setPassed(getTimeAgo(rs.getTimestamp("timestamp")));
				pcVO.setMemberId(rs.getInt("member_id"));
				pcVO.setPostId(rs.getInt("post_id"));
				list.add(pcVO);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		return list;
	}

	public static void main(String[] args) {
		PostCommentDao control = new PostCommentDao();

		PostCommentVO good = new PostCommentVO();
		good.setPostId(2);
		good.setMemberId(3);
		good.setContent("嚙篇");

		control.insert(good);

//		PostCommentVO change = new PostCommentVO();
//		change.setCommentId(1);
//		change.setContent("嚙踝蕭");
//		
//		control.update(change);

//		control.delete(1);
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

package com.lcomputerstudy.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lcomputerstudy.testmvc.database.DBConnection;
import com.lcomputerstudy.testmvc.vo.Board;
import com.lcomputerstudy.testmvc.vo.Comment;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.User;

public class BoardDAO {
	
	private static BoardDAO dao = null;
	private String decomment;
    
	private BoardDAO() {
		
	}
	
	public Board boardEdit(Board vo) {
		return vo;
		
		
	}

	public static BoardDAO getInstance() {
		if(dao == null) {
			dao = new BoardDAO();
		}
		return dao;
	}

	public ArrayList<Board> getBoards(Pagination pagination) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Board> blist = null;
		int pageNum = (pagination.getPage()-1)*Pagination.perPage;
		
		try {
			conn = DBConnection.getConnection();
			String query = new StringBuilder()
					.append("SELECT 		@ROWNUM := @ROWNUM - 1 AS ROWNUM,\n")
					.append("				ta.*\n")
					.append("FROM 			board ta,\n")
					.append("				(SELECT @rownum := (SELECT	COUNT(*)-?+1 FROM board ta)) tb\n")
					.append("ORDER BY 		ta.b_idx desc\n")
					.append("LIMIT			?, 3\n")
					.toString();
	       	pstmt = conn.prepareStatement(query);
	       	pstmt.setInt(1, pageNum);
	       	pstmt.setInt(2, pageNum);
	       	rs = pstmt.executeQuery();
	        blist = new ArrayList<Board>();

	        while(rs.next()){     
	        	Board board = new Board();
	        	board.setRownum(rs.getInt("ROWNUM"));
	        	board.setB_idx(rs.getInt("b_idx"));
       	       	board.setU_idx(rs.getInt("u_idx"));
       	       	board.setB_title(rs.getString("b_title"));
       	       	board.setB_content(rs.getString("b_content"));
       	       	board.setB_assistant(rs.getString("b_assistant"));
       	       	board.setB_writer(rs.getString("b_writer"));
       	       	board.setB_date(rs.getString("b_date"));
       	       	
       	       	blist.add(board);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				 if (rs != null) rs.close();
				 if (pstmt != null) pstmt.close();
				 if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return blist;
	}
	public void insertBoard(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
			String sql = "insert into board(b_title,b_content,b_assistant,b_writer,b_date,u_idx,b_group,b_order,b_depth) values(?,?,0,?,now(),?,0,1,0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getB_title());
			pstmt.setString(2, board.getB_content());
			pstmt.setString(3, board.getB_writer());
			pstmt.setInt(4, board.getU_idx());
			pstmt.executeUpdate();
			pstmt.close();
			
			sql = "update board set b_group=last_insert_id() where b_idx = last_insert_id()";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		
		} catch( Exception ex) {
			System.out.println("SQLException : "+ex.getMessage());
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public int getBoardsCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;

		try {
			conn = DBConnection.getConnection();
			String query = "SELECT COUNT(*) count FROM board ";
	       	pstmt = conn.prepareStatement(query);
	        rs = pstmt.executeQuery();
	        
	        while(rs.next()){     
	        	count = rs.getInt("count");
	        }
		} catch (Exception e) {
			
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	public Board getDetail(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBConnection.getConnection();
			String query = 	  "select *\n"
							+ "from board ta \n"
							+ "left join user tb on ta.u_idx = tb.u_idx \n"
							+ "where b_idx = ? \n";
	       	pstmt = conn.prepareStatement(query);
	       	pstmt.setInt(1, board.getB_idx());
	       	rs = pstmt.executeQuery();

	        while(rs.next()){     
       	       	board.setU_idx(rs.getInt("u_idx"));
       	       	board.setB_title(rs.getString("b_title"));
       	       	board.setB_content(rs.getString("b_content"));
       	       	board.setB_assistant(rs.getString("b_assistant"));
       	       	board.setB_writer(rs.getString("b_writer"));
       	       	board.setB_date(rs.getString("b_date"));
       	       	board.setB_group(rs.getInt("b_group"));
       	       	board.setB_order(rs.getInt("b_order"));
       	       	board.setB_depth(rs.getInt("b_depth"));
       	       	User user = new User();
       	       	user.setU_name(rs.getString("u_name"));
       	       	board.setUser(user);
	        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				 if (rs != null) rs.close();
				 if (pstmt != null) pstmt.close();
				 if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return board;
	}

	public void edit(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
			String sql = "update board set b_title=?, b_content=?, b_writer=? where b_idx =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getB_title());
			pstmt.setString(2, board.getB_content());
			pstmt.setString(3, board.getB_writer());
			pstmt.setInt(4, board.getB_idx());
			pstmt.executeUpdate();
		
		} catch( Exception ex) {
			System.out.println("SQLException : "+ex.getMessage());
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void delete(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
			String sql = "delete From board where b_idx =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getB_idx());
			pstmt.executeUpdate();
		
			
			
		} catch( Exception ex) {
			System.out.println("SQLException : "+ ex.getMessage());
			
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void reply(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
			String sql = "update board set b_order = b_order+1 where b_group = ? and b_order > ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getB_group());
			pstmt.setInt(2, board.getB_order());
			pstmt.executeUpdate();
			pstmt.close();
			
			sql = "insert into board (b_title, b_content, b_assistant, b_writer, b_date, b_group, b_order, b_depth, b_idx) values (?, ?, 0, ?, now(), ?, ?, ?, ?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getB_title());
			pstmt.setString(2, board.getB_content());
			pstmt.setString(3, board.getB_writer());
			pstmt.setInt(4, board.getB_group());
			pstmt.setInt(5, board.getB_order()+1);
			pstmt.setInt(6, board.getB_depth()+1);
			pstmt.setInt(7, board.getB_idx());
			pstmt.executeUpdate();
					
		} catch( Exception ex) {
			System.out.println("SQLException : "+ ex.getMessage());
		
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	public void comment(Comment comment) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		 
		try {
			conn = DBConnection.getConnection();
			String sql =  "select *\n"
					+ "from comment ta \n"
					+ "order by c_group desc, order asc \n"
					+ "update comment set c_order = c_order +1 where c_group >= ? c_order > ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getC_order());
			pstmt.setInt(2, comment.getC_group());
			pstmt.executeUpdate();
			pstmt.close();
			
			sql = "insert into comment (c_comment, c_date, c_order, c_group, c_depth, c_idx) values (?, ?, now(), ?, ?, ?, ?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comment.getC_comment());
			pstmt.setString(2, comment.getC_date());
			pstmt.setInt(3, comment.getC_order()+1);
			pstmt.setInt(4, comment.getC_group());
			pstmt.setInt(5, comment.getC_depth()+1);
			pstmt.setInt(6, comment.getC_idx());
			pstmt.executeUpdate();
				
		} catch( Exception ex) {
			System.out.println("SQLException : "+ ex.getMessage());
		
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	public List<Comment> getCommentList(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Comment> list = null; 
		 
		try {
			conn = DBConnection.getConnection();

			String sql = "select * from comment where b_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getB_idx());
			rs = pstmt.executeQuery();
	       
			list = new ArrayList<Comment>();
			
			while(rs.next()){     
		    	Comment comment = new Comment();
		       	comment.setC_idx(rs.getInt("c_idx"));
	       	   	comment.setB_idx(rs.getInt("b_idx"));
	       	   	comment.setC_comment(rs.getString("c_comment"));
	       	   	comment.setC_date(rs.getString("c_date"));
	       	   	comment.setC_order(rs.getInt("c_order"));
	       	   	comment.setC_group(rs.getInt("c_group"));
	       	   	comment.setC_depth(rs.getInt("c_depth"));
	       	         	  	
	       	   	list.add(comment);
			}
		} catch( Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	
}


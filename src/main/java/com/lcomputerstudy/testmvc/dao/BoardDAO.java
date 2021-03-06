package com.lcomputerstudy.testmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import com.lcomputerstudy.testmvc.database.DBConnection;
import com.lcomputerstudy.testmvc.vo.Board;
import com.lcomputerstudy.testmvc.vo.BoardFile;
import com.lcomputerstudy.testmvc.vo.Comment;

import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.Search;
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
		Search search = pagination.getSearch();
		
		String where = "";
		if (search != null && search.getSelectBox() != null && (!search.getSelectBox().equals("")) && search.getSearchText() != null && (!search.getSearchText().equals(""))) {
			where += "WHERE " + search.getSelectBox() + " LIKE ? ";
		}
		
		try {
			conn = DBConnection.getConnection();
			String query = new StringBuilder()
					.append("SELECT 		@ROWNUM := @ROWNUM - 1 AS ROWNUM,\n")
					.append("				ta.*,\n")
					.append("				tb.*\n")
					.append("FROM 			board ta\n")
					.append("LEFT JOIN		boardfile tb ON ta.b_idx = tb.b_idx \n")
					.append("INNER JOIN		(SELECT @rownum := (SELECT	COUNT(*)-?+1 FROM board ta)) tc ON 1=1\n")
					.append(where)
					.append("ORDER BY 		ta.b_idx desc\n")
					.append("LIMIT			?, 3\n")
					.toString();
	       	pstmt = conn.prepareStatement(query);
	       	if (search != null && search.getSelectBox() != null && (!search.getSelectBox().equals("")) && search.getSearchText() != null && (!search.getSearchText().equals(""))) {
	       		pstmt.setInt(1, pageNum);
	       		pstmt.setString(2, "%"+search.getSearchText()+"%");
	       		pstmt.setInt(3, pageNum);
	       	} else {
		       	pstmt.setInt(1, pageNum);
		       	pstmt.setInt(2, pageNum);
	       	}
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
       	       	BoardFile boardfile = new BoardFile();
       	       	boardfile.setFileName(rs.getString("bf_filename"));
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
	

	public int getBoardsCount(Pagination pagination) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		int pageNum = (pagination.getPage()-1)*Pagination.perPage;
		Search search = pagination.getSearch();
	
	
		
		String where = "";
		if (search != null && search.getSelectBox() != null && (!search.getSelectBox().equals("")) && search.getSearchText() != null && (!search.getSearchText().equals(""))) {
			where += "WHERE " + search.getSelectBox() + " LIKE ?";
		}
		try {
			conn = DBConnection.getConnection();
			String query = new StringBuilder()
					.append("SELECT COUNT(*) count FROM board ")
					.append(where)
					.toString();

			pstmt = conn.prepareStatement(query);
		 	if (search != null && search.getSelectBox() != null && (!search.getSelectBox().equals("")) && search.getSearchText() != null && (!search.getSearchText().equals(""))) {
	       		pstmt.setString(1, "%"+search.getSearchText()+"%");
		       
	       	} else {
	       		
	       	}
	        rs = pstmt.executeQuery();
	        
	        while(rs.next()){     
	        	count = rs.getInt("count");
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
		return count;
	}
	public void insertBoard(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
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
			pstmt.close();
			
			sql = "select last_insert_id() b_idx";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
	        
	        while(rs.next()){     
	        	board.setB_idx(rs.getInt("b_idx"));
	        }
			
		
		} catch( Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	public List<BoardFile> insertBoardFile(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<BoardFile> boardFiles = null; 
		 
		try {
			conn = DBConnection.getConnection();
			String values = "values";
			for (BoardFile bf : board.getBoardFiles()) {
				values += "(?,?)";
				pstmt = conn.prepareStatement(values);
				pstmt.setInt(1, board.getB_idx());
				pstmt.setString(2,  board.getFileName());
				pstmt.executeUpdate();
				pstmt.close();
			}
			

			String sql = "insert into boardfile(b_idx, bf_filename) " + values;
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			
			sql = "SELECT * from board ta left join boardfile tb ON ta.b_idx = tb.b_idx where ta.b_idx = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getB_idx());
			rs = pstmt.executeQuery();
			
			boardFiles = new ArrayList<BoardFile>();
			
			while(rs.next()){     
		    	BoardFile boardfile = new BoardFile();
		    	boardfile.setB_idx(rs.getInt("b_idx"));
		       	boardfile.setFileName(rs.getString("bf_filename"));
		       	boardfile.setBf_idx(rs.getInt("bf_idx"));
	       	         	  	
	       	   	boardFiles.add(boardfile);
			}
		} catch( Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return boardFiles;
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
       	       	board.setBf_filename(rs.getString("bf_filename"));
       	       	board.setB_group(rs.getInt("b_group"));
       	       	board.setB_order(rs.getInt("b_order"));
       	       	board.setB_depth(rs.getInt("b_depth"));
       	       	Comment comment = new Comment();
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
			 ex.printStackTrace();
		
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
						
			String sql = "insert into comment (c_comment, c_date, b_idx, c_group, c_order, c_depth) values (?, now(), ?, 0, 1, 0)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comment.getC_comment());
			pstmt.setInt(2, comment.getB_idx());
			pstmt.executeUpdate();
			pstmt.close();
			
			sql = "update comment set c_group = last_insert_id() where c_idx = last_insert_id()";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
			
		
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
	}

	public void commentreplyform(Comment comment) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		 
		try {
			conn = DBConnection.getConnection();
			
						
			String sql = "insert into comment (c_comment, b_idx, c_group, c_order, c_depth) values (?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comment.getC_comment());
			pstmt.setInt(2, comment.getB_idx());
			pstmt.setInt(3, comment.getC_group());
			pstmt.setInt(4, comment.getC_order()+1);
			pstmt.setInt(5, comment.getC_depth()+1);
			pstmt.executeUpdate();
			pstmt.close();	
			
		    sql = "update comment set c_order = c_order+1 where c_group = ? and c_order > ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getC_group());
			pstmt.setInt(2, comment.getC_order()+1);
			pstmt.executeUpdate();
					
						
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
	}
	public List<Comment> getCommentList(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<Comment> commentList = null; 
		 
		try {
			conn = DBConnection.getConnection();

			String sql = "select * from comment where b_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getB_idx());
			rs = pstmt.executeQuery();
			
	       
			commentList = new ArrayList<Comment>();
			
			while(rs.next()){     
		    	Comment comment = new Comment();
		    	comment.setB_idx(rs.getInt("b_idx"));
		       	comment.setC_idx(rs.getInt("c_idx"));
	       	   	comment.setC_comment(rs.getString("c_comment"));
	       	   	comment.setC_date(rs.getString("c_date"));
	       	   	comment.setC_group(rs.getInt("c_group"));
	       	   	comment.setC_order(rs.getInt("c_order"));
	       	   	comment.setC_depth(rs.getInt("c_depth"));
	       	         	  	
	       	   	commentList.add(comment);
			}
		} catch( Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return commentList;
	}
	public void commentUpdate(Comment comment) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
			String sql = "update comment set c_comment = ? where c_idx =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comment.getC_comment());
			pstmt.setInt(2, comment.getC_idx());
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
	public void commentDelete(Comment comment) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBConnection.getConnection();
			String sql = "delete From comment where c_idx =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getC_idx());
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
}


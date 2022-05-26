package com.lcomputerstudy.testmvc.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lcomputerstudy.testmvc.service.BoardService;
import com.lcomputerstudy.testmvc.service.UserService;
import com.lcomputerstudy.testmvc.vo.Board;
import com.lcomputerstudy.testmvc.vo.BoardFile;
import com.lcomputerstudy.testmvc.vo.Comment;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.Search;
import com.lcomputerstudy.testmvc.vo.User;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("*.do")
public class controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletRequest request;
	private String view;
	private Object setB_content;

	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8"); // test
		request.setCharacterEncoding("utf-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		String view = null;
		String pw = null;
		
	
	 						
		int boardcount = 0;
		int usercount = 0;
		int page = 1;
		int count = 0;
		
		Pagination pagination = null;
		Search search = null;
		
		String reqPage = request.getParameter("page");
		
		// upload
		String path = "C:\\Users\\l8-morning\\Documents\\work10\\lcomputerstudy\\src\\main\\webapp\\img";
		int size = 1024 * 1024 * 10;
		
		
		
		command = checkSession(request, response, command);

		
		HttpSession session = null;
		User user = new User();
		String idx;
		BoardService boardService = null;
		Comment comment = null;
		boolean isRedirected = false;
		int bIdx = 0;
		List<Comment> commentList = null;
		
	
		switch (command) {
			
					
			case "/user-list.do":
				
				if (reqPage != null) {
					page = Integer.parseInt(reqPage);
					
				}
				
				UserService userService = UserService.getInstance();
				usercount = userService.getUsersCount();
				
				pagination = new Pagination();
				pagination.setCount(usercount);
				pagination.setPage(page);
				pagination.init();
												
				ArrayList<User> list = userService.getUsers(pagination);
				
				request.setAttribute("list", list);
				request.setAttribute("pagination", pagination);
				
				view = "user/list";
				break;
				
				case "/level-insert-process.do":
									
				user.setU_idx(Integer.parseInt(request.getParameter("u_idx")));
				user.setU_level(request.getParameter("u_level"));
					
				userService = UserService.getInstance();
				userService.updateLevel(user);
				
				request.setAttribute("user", user);
						
				isRedirected = true;
				view = "user-list.do";
				break;
					
			
								
				case "/user-insert.do":
				view = "user/insert";
				break;
				
				
				case "/user-insert-process.do":
				
				user.setU_id(request.getParameter("login_id"));
				user.setU_pw(request.getParameter("login_password"));
				user.setU_name(request.getParameter("name"));
				user.setU_tel(request.getParameter("tel1") + "-" + request.getParameter("tel2") + "-" + request.getParameter("tel3"));
				user.setU_age(request.getParameter("age"));
				
				userService = UserService.getInstance();
				userService.insertUser(user);
				view = "user/insert-result";
				break;	
				
				case "/user-login.do":
				view = "user/login";
				break;
				
				
				
						
				case "/user-login-process.do":
					idx = request.getParameter("login_id");
					pw = request.getParameter("login_password");
					
					userService = UserService.getInstance();
					user = userService.loginUser(idx,pw);
								
					if(user != null) {
						session = request.getSession();
//						session.setAttribute("u_idx", user.getU_idx());
//						session.setAttribute("u_id", user.getU_id());
//						session.setAttribute("u_pw", user.getU_pw());
//						session.setAttribute("u_name", user.getU_name());
						session.setAttribute("user", user);

						view = "user/login-result";
					} else {
						view = "user/login-fail";
					}			
					break;
				
				case "/logout.do":
				
					session = request.getSession();
					session.invalidate();
					view = "user/login";
					break;
					
				case "/access-denied.do":
					view = "user/access-denied";
					break;
					
				case "/board-insert-title.do":
					view = "board/insert-title";
					break;
				
								
				case "/board-title-insert-process.do":
					MultipartRequest multi = null;
					String fileName = null;    
				    String originalFileName = null;
				    String name = null;
				    File file = null;
					
					session = request.getSession();
					user = (User)session.getAttribute("user");
					
					Board board = new Board();
					BoardFile boardfile = new BoardFile();
					List<BoardFile> boardFiles = new ArrayList<BoardFile>();
					try{
				        multi = new MultipartRequest(request, path, size, "UTF-8", new DefaultFileRenamePolicy());
				        
				        // 전송한 전체 파일이름들을 가져온다.
				        Enumeration files = multi.getFileNames();
				        while (files.hasMoreElements()) {
				        	name = (String)files.nextElement();
				        	fileName = multi.getFilesystemName(name);
				        	file = multi.getFile(name);
				        	
				        	BoardFile bf = new BoardFile();
				        	bf.setB_idx(bIdx);
				        	bf.setFileName(fileName);
				        	
				        	boardFiles.add(bf);
				        	
				        }
				        board.setBoardFiles(boardFiles);
				        boardService = BoardService.getInstance();
				        boardService.insertBoardFile(board);
				    }catch(Exception e){
				        e.printStackTrace();
				    }
					
					board.setB_title(multi.getParameter("b_title"));
					board.setB_content(multi.getParameter("b_content"));
					board.setB_assistant(multi.getParameter("b_assistant"));
					board.setB_writer(multi.getParameter("b_writer"));
					board.setB_date(multi.getParameter("b_date"));
					board.setU_idx(user.getU_idx());
				
					
					boardService = BoardService.getInstance();
					boardService.insertBoard(board);
					boardService.insertBoardFile(board);
					
				
					view = "board/insert-title-result";
					break;
								
				case "/board-blist.do":
					boardService = BoardService.getInstance();
					
					if (reqPage != null)
						page = Integer.parseInt(reqPage);
				
					search = new Search();	
					search.setSelectBox(request.getParameter("selectBox"));
					search.setSearchText(request.getParameter("searchText"));
					
					pagination = new Pagination();
					pagination.setSearch(search);
					pagination.setPage(page);
					pagination.setCount(boardService.getBoardsCount(pagination));
					pagination.init();
					
					ArrayList<Board> blist = boardService.getBoards(pagination);
									
					request.setAttribute("blist", blist);
					request.setAttribute("pagination", pagination);
					

					
					view = "board/blist";
					break;
				
				
				case "/board-Detail.do":
					idx = request.getParameter("login_id");
					pw = request.getParameter("login_password");
					session = request.getSession();
					user = (User)session.getAttribute("user");
			    		
					// 파라미터 받아옴
					board = new Board();
					board.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
					
					// 서비스 호출하여 데이터 결과 리턴 받음
					boardService = BoardService.getInstance();
					board = boardService.getDetail(board);
									
					request.setAttribute("board", board);
					
					view = "boardDetail";
					break;
				
				case "/commentProcess.do":
					board = new Board();
					board.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
					
					comment = new Comment();
					comment.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
					comment.setC_comment(request.getParameter("c_comment"));
					comment.setC_group(0);
					comment.setC_order(1);
					comment.setC_depth(0);
					boardService = BoardService.getInstance();
					//boardService.getCommentList(board);
					boardService.comment(comment);
					
									
					isRedirected = true;
					view = "board-Detail.do?b_idx="+comment.getB_idx();
					break;
					
				case "/commentReplyForm.do":
					bIdx = Integer.parseInt(request.getParameter("b_idx"));
					
					board = new Board();
					board.setB_idx(bIdx);
					
					comment = new Comment();
					comment.setB_idx(bIdx);
					comment.setC_comment(request.getParameter("c_comment"));
					comment.setC_group(Integer.parseInt(request.getParameter("c_group")));
					comment.setC_order(Integer.parseInt(request.getParameter("c_order")));
					comment.setC_depth(Integer.parseInt(request.getParameter("c_depth")));
					
					boardService = BoardService.getInstance();
					boardService.commentreplyform(comment);
					
					commentList = boardService.getCommentList(board);
					
					request.setAttribute("board", board);
					request.setAttribute("commentList", commentList);
					
					
					view = "board/comment-list";
					break;
					
				case "/commentUpdate.do":
										
					comment = new Comment();
					comment.setC_comment(request.getParameter("c_comment"));
					comment.setC_date(request.getParameter("c_date"));
					comment.setC_idx(Integer.parseInt(request.getParameter("c_idx")));
					
					boardService = BoardService.getInstance();
					boardService.commentUpdate(comment);
					
					board = new Board(); 
					board.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
					
					commentList = boardService.getCommentList(board);
					
					request.setAttribute("board", board);
					request.setAttribute("comment", comment);
					request.setAttribute("commentList", commentList);
					
					view = "board/comment-list";
					break;
					
				case "/commentDelete.do":
					
					comment = new Comment();
					comment.setC_idx(Integer.parseInt(request.getParameter("c_idx")));
									
					boardService = BoardService.getInstance();
					boardService.commentDelete(comment);
					
					board = new Board(); 
					board.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
					
					commentList = boardService.getCommentList(board);
					
					request.setAttribute("board", board);
					request.setAttribute("comment", comment);
					request.setAttribute("commentList", commentList);
					
					view = "board/comment-list";
					break;
				
				
				case "/boardEdit.do":
					board = new Board();
					board.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
					boardService = BoardService.getInstance();
					board = boardService.getDetail(board);
					//board = boardService.boardEdit(board);
					
					request.setAttribute("board", board);
					
					view = "boardEdit";
					break;
							
				case "/boardEditProcess.do":
					board = new Board();
					board.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
					board.setB_title(request.getParameter("edit_title"));
					board.setB_content(request.getParameter("edit_content"));
					board.setB_assistant(request.getParameter("edit_assistant"));
					board.setB_writer(request.getParameter("edit_writer"));
					board.setB_date(request.getParameter("edit_date"));
					boardService = BoardService.getInstance();
					
					boardService.edit(board);
					
					view = "boardEditProcess";
					break;
					
				case "/boardDelete.do":
					board = new Board();
					board.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
					boardService = BoardService.getInstance();
					boardService.delete(board);
					
					view = "boardDeleteresult";
					break;
				
				case "/reply.do":
					board = new Board();
					
					board.setB_group(Integer.parseInt(request.getParameter("b_group")));
					board.setB_order(Integer.parseInt(request.getParameter("b_order")));
					board.setB_depth(Integer.parseInt(request.getParameter("b_depth")));
					
					
					view = "reply";
					request.setAttribute("board", board);
				
					break;
					
				case "/replyProcess.do":
					board = new Board();
					
					board.setB_title(request.getParameter("b_title"));
					board.setB_content(request.getParameter("reply"));
					board.setB_assistant(request.getParameter("b_assistant"));
					board.setB_writer(request.getParameter("b_writer"));
					board.setB_date(request.getParameter("b_date"));
					board.setB_order(Integer.parseInt(request.getParameter("b_order")));
					board.setB_group(Integer.parseInt(request.getParameter("b_group")));
					board.setB_depth(Integer.parseInt(request.getParameter("b_depth")));
					
					
					boardService = BoardService.getInstance();
					boardService.reply(board);
					view = "replyProcess";
					break;
							
			
					
					
				
		}
		

		if (isRedirected) {
			response.sendRedirect(view);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher(view+".jsp");
			rd.forward(request, response);
		}
		
	}
	
	String checkSession(HttpServletRequest request, HttpServletResponse response, String command) {
		HttpSession session = request.getSession();
		
		String[] authList = {
				"/user-list.do"
				,"/user-insert.do"
				,"/user-insert-process.do"
				,"/user-detail.do"
				,"/user-edit.do"
				,"/user-edit-process.do"
				,"/logout.do"
				,"/board-insert-title.do"
				,"/board-insert-title-process.do"
				,"/level-insert-process.do"
			};
		
		for (String item : authList) {
			if (item.equals(command)) {
				if (session.getAttribute("user") == null) {
					command = "/access-denied.do";
				}
			}
		}
		return command;
	}


	
}




package com.lcomputerstudy.testmvc.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import com.lcomputerstudy.testmvc.vo.Comment;
import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.User;

@WebServlet("*.do")
public class controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ServletRequest request;
	private String view;
	private Object setB_content;

	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

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
		
		Pagination pagination = new Pagination();
		
		String reqPage = request.getParameter("page");
		
		
		
		command = checkSession(request, response, command);

		
		HttpSession session = null;
		
		String idx;
		BoardService boardService = null;
		Comment comment = null;
		boolean isRedirected = false;
		
		switch (command) {
			
					
			case "/user-list.do":
				
				if (reqPage != null) {
					page = Integer.parseInt(reqPage);
					
				}
				
				UserService userService = UserService.getInstance();
				usercount = userService.getUsersCount();
				
								
				pagination.setCount(usercount);
				pagination.setPage(page);
				pagination.init();
				
				ArrayList<User> list = userService.getUsers(pagination);
				
				request.setAttribute("list", list);
				request.setAttribute("pagination", pagination);
				
				view = "user/list";
				break;
				
				
				case "/user-insert.do":
				view = "user/insert";
				break;
				
				
				case "/user-insert-process.do":
				User user = new User();
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
					session = request.getSession();
					user = (User)session.getAttribute("user");
					
					Board board = new Board();
					
					board.setB_title(request.getParameter("title"));
					board.setB_content(request.getParameter("content"));
					board.setB_assistant(request.getParameter("assistant"));
					board.setB_writer(request.getParameter("writer"));
					board.setB_date(request.getParameter("date"));
					board.setU_idx(user.getU_idx());
					
					boardService = BoardService.getInstance();
					boardService.insertBoard(board);
					
					view = "board/insert-title-result";
					break;
				
				case "/board-blist.do":
					
					if (reqPage != null)
						page = Integer.parseInt(reqPage);
						
					boardService = BoardService.getInstance();
					count = boardService.getBoardsCount();
					
					pagination = new Pagination();
					pagination.setCount(count);
					pagination.setPage(page);
					pagination.init();
					ArrayList<Board> blist = boardService.getBoards(pagination);
					
					
					
					request.setAttribute("blist", blist);
					request.setAttribute("pagination", pagination);

					
					view = "board/blist";
					break;
					
				case "/board-Detail.do":
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
					
				case "/commentReplyProcess.do":
					board = new Board();
					comment = new Comment();
					
					comment.setB_idx(Integer.parseInt(request.getParameter("b_idx")));
					comment.setC_comment(request.getParameter("c_comment"));
					comment.setC_group(Integer.parseInt(request.getParameter("c_group")));
					comment.setC_order(Integer.parseInt(request.getParameter("c_order")));
					comment.setC_depth(Integer.parseInt(request.getParameter("c_depth")));
					boardService = BoardService.getInstance();
					boardService.commentreplyform(comment);
					isRedirected = true;
					view = "board-Detail.do?b_idx="+comment.getB_idx();
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
							
				case "/boardDetail.do":
					session = request.getSession();
					
					board = (Board)session.getAttribute("board");
					
					
					comment.setC_idx(Integer.parseInt(request.getParameter("c_idx")));
					comment.setC_comment(request.getParameter("c_comment"));
					comment.setC_date(request.getParameter("c_cdate"));
					comment.setC_order(Integer.parseInt(request.getParameter("c_order")));
					comment.setC_group(Integer.parseInt(request.getParameter("c_group")));
					comment.setC_depth(Integer.parseInt(request.getParameter("c_depth")));
					comment.setB_idx(board.getB_idx());
					
					boardService = BoardService.getInstance();
					//boardService.comment(comment);
					
					view = "boardDetail";
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




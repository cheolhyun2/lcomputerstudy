package com.lcomputerstudy.testmvc.service;

import java.util.ArrayList;
import java.util.List;

import com.lcomputerstudy.testmvc.dao.BoardDAO;
import com.lcomputerstudy.testmvc.dao.UserDAO;
import com.lcomputerstudy.testmvc.vo.Board;
import com.lcomputerstudy.testmvc.vo.BoardFile;
import com.lcomputerstudy.testmvc.vo.Comment;

import com.lcomputerstudy.testmvc.vo.Pagination;
import com.lcomputerstudy.testmvc.vo.User;

public class BoardService {
	
	private static BoardService service = null;
	private static BoardDAO dao = null;
    
	private BoardService() {
		
	}

	public static BoardService getInstance() {
		if(service == null) {
			service = new BoardService();
			dao = BoardDAO.getInstance();
		}
		return service;
	}

	public ArrayList<Board> getBoards(Pagination pagination) {
		return dao.getBoards(pagination);
	}
	public void insertBoard(Board board) {
		dao.insertBoard(board);
		List<BoardFile> boardfile = dao.insertBoardFile(board);
	}
	public int getBoardsCount(Pagination pagination) {
		return dao.getBoardsCount(pagination);
	}

	public Board getDetail(Board board) {
		List<Comment> commentList = dao.getCommentList(board);
		board = dao.getDetail(board);
		board.setCommentList(commentList);
		return board;
	}
	
	public Board boardEdit(Board vo) {
		return dao.boardEdit(vo);
	}

	public void edit(Board board) {
		dao.edit(board);
	}
	public void delete(Board board) {
		dao.delete(board);
	}
	public void reply(Board board) {
		dao.reply(board);
	}

	public void comment(Comment comment) {
		dao.comment(comment);
		
			
	}
	
	public void commentreplyform(Comment comment) {
		dao.commentreplyform(comment);
		// TODO Auto-generated method stub
		
	}
	
	public List<Comment> getCommentList(Board board) {
		List<Comment> commentList = dao.getCommentList(board);
		  return commentList;
	}

	public void commentUpdate(Comment comment) {
		dao.commentUpdate(comment);;
		// TODO Auto-generated method stub
		
	}

	public void commentDelete(Comment comment) {
		dao.commentDelete(comment);
		// TODO Auto-generated method stub
		
	}
	public List<BoardFile> insertBoardFile(Board board) {
		List<BoardFile> boardFiles = dao.insertBoardFile(board);
		return boardFiles;
	}





}

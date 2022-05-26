package com.lcomputerstudy.testmvc.vo;

import java.util.List;

public class Board {
	
	private int b_idx;
	private String b_title;
	private String b_content;
	private String b_assistant;
	private String b_writer;
	private String b_date;
	private String bf_filename;
	private int bf_idx;
	private String FileName;
	private int u_idx;
	private int rownum;
	private int b_group;
	
	private int b_order;
	private int b_depth;
	private User user;
	private List<Comment> commentList;
	private List<BoardFile> boardFiles;
	
	
	public String getFileName() {
		return FileName;
	}
	public void setFileName(String fileName) {
		FileName = fileName;
	}
	
	public List<BoardFile> getBoardFiles() {
		return boardFiles;
	}
	public void setBoardFiles(List<BoardFile> boardFiles) {
		this.boardFiles = boardFiles;
	}
	public int getB_group() {
		return b_group;
	}
	public void setB_group(int b_group) {
		this.b_group = b_group;
	}
	public int getB_order() {
		return b_order;
	}
	public void setB_order(int b_order) {
		this.b_order = b_order;
	}
	public int getB_depth() {
		return b_depth;
	}
	public void setB_depth(int b_depth) {
		this.b_depth = b_depth;
	}
	public int getRownum() {
		return rownum;
	}
	public int getU_idx() {
		return u_idx;
	}
	public void setU_idx(int u_idx) {
		this.u_idx = u_idx;
	}
	public int getB_idx() {
		return b_idx;
	}
	public void setB_idx(int b_idx) {
		this.b_idx = b_idx;
	}
	public String getB_title() {
		return b_title;
	}
	public void setB_title(String b_title) {
		this.b_title = b_title;
	}
	public String getB_content() {
		return b_content;
	}
	public void setB_content(String b_content) {
		this.b_content = b_content;
	}
	public String getB_assistant() {
		return b_assistant;
	}
	public void setB_assistant(String b_assistant) {
		this.b_assistant = b_assistant;
	}
	public String getB_writer() {
		return b_writer;
	}
	public void setB_writer(String b_writer) {
		this.b_writer = b_writer;
	}
	public String getB_date() {
		return b_date;
	}
	public void setB_date(String b_date) {
		this.b_date = b_date;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
		
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	
	public String getBf_filename() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setBf_filename(String string) {
		// TODO Auto-generated method stub
	}
	public int getBf_idx() {
		return bf_idx;
	}
	public void setBf_idx(int bf_idx) {
		this.bf_idx = bf_idx;
	}

	
}

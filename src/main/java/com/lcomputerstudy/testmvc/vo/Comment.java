package com.lcomputerstudy.testmvc.vo;

import java.util.Date;

public class Comment {
	
	private int b_idx;
	private int c_idx;
	private String c_comment;
	private String c_date;
	private int c_group;
	private int c_order;
	private int c_depth;
	private int cno;
	private String comment;
	private Date cdate;
	
	
	public int getC_idx() {
		return c_idx;
	}
	public void setC_idx(int c_idx) {
		this.c_idx = c_idx;
	}
	public int getB_idx() {
		return b_idx;
	}
	public void setB_idx(int b_idx) {
		this.b_idx = b_idx;
	}
	public String getC_comment() {
		return c_comment;
	}
	public void setC_comment(String c_comment) {
		this.c_comment = c_comment;
	}
	public String getC_date() {
		return c_date;
	}
	public void setC_date(String c_date) {
		this.c_date = c_date;
	}
	public int getC_group() {
		return c_group;
	}
	public void setC_group(int c_group) {
		this.c_group = c_group;
	}
	public int getC_order() {
		return c_order;
	}
	public void setC_order(int c_order) {
		this.c_order = c_order;
	}
	public int getC_depth() {
		return c_depth;
	}
	public void setC_depth(int c_depth) {
		this.c_depth = c_depth;
	}
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	
	
}
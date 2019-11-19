package com.sgmp.web.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class NoticeVO {
	private Integer no_rownum;
	private String no_bid;
	private String no_name;
	private String no_title;
	private String no_content;
	private Date no_date;
	private Integer no_hit;
	private String no_group;
	private String no_step;
	private String no_indent;

	public Integer getNo_rownum() {
		return no_rownum;
	}

	public void setNo_rownum(Integer no_rownum) {
		this.no_rownum = no_rownum;
	}

	public String getNo_bid() {
		return no_bid;
	}

	public void setNo_bid(String no_bid) {
		this.no_bid = no_bid;
	}

	public String getNo_name() {
		return no_name;
	}

	public void setNo_name(String no_name) {
		this.no_name = no_name;
	}

	public String getNo_title() {
		return no_title;
	}

	public void setNo_title(String no_title) {
		this.no_title = no_title;
	}

	public String getNo_content() {
		return no_content;
	}

	public void setNo_content(String no_content) {
		this.no_content = no_content;
	}

	public Date getNo_date() {
		return no_date;
	}

	public void setNo_date(Date no_date) {
		this.no_date = no_date;
	}

	public Integer getNo_hit() {
		return no_hit;
	}

	public void setNo_hit(Integer no_hit) {
		this.no_hit = no_hit;
	}

	public String getNo_group() {
		return no_group;
	}

	public void setNo_group(String no_group) {
		this.no_group = no_group;
	}

	public String getNo_step() {
		return no_step;
	}

	public void setNo_step(String no_step) {
		this.no_step = no_step;
	}

	public String getNo_indent() {
		return no_indent;
	}

	public void setNo_ident(String no_indent) {
		this.no_indent = no_indent;
	}

	@Override
	public String toString() {
		return "NoticeVO [no_rownum=" + no_rownum + ", no_bid=" + no_bid + ", no_name=" + no_name + ", no_title="
				+ no_title + ", no_content=" + no_content + ", no_date=" + no_date + ", no_hit=" + no_hit
				+ ", no_group=" + no_group + ", no_step=" + no_step + ", no_indent=" + no_indent + "]";
	}

}

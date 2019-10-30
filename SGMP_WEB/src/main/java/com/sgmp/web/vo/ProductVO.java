package com.sgmp.web.vo;

public class ProductVO {
	private String prod_id;
	private String prod_name;
	private String prod_price;
	private String prod_cnt;
	
	private String prod_pic;
	private String prod_main_category;
	private String prod_sub_category;
	private String prod_ssub_category;
	private String prod_date;
	private String prod_wearing_price;
	private String prod_cnt_min;
	private String prod_flag;
	private String prod_mod_date;
	private String prod_all;
	private String prod_margin;
	private String company_id;
	
	
	
	public String getProd_pic() {
		return prod_pic;
	}

	public void setProd_pic(String prod_pic) {
		this.prod_pic = prod_pic;
	}

	public String getProd_main_category() {
		return prod_main_category;
	}

	public void setProd_main_category(String prod_main_category) {
		this.prod_main_category = prod_main_category;
	}

	public String getProd_sub_category() {
		return prod_sub_category;
	}

	public void setProd_sub_category(String prod_sub_category) {
		this.prod_sub_category = prod_sub_category;
	}

	public String getProd_ssub_category() {
		return prod_ssub_category;
	}

	public void setProd_ssub_category(String prod_ssub_category) {
		this.prod_ssub_category = prod_ssub_category;
	}

	public String getProd_date() {
		return prod_date;
	}

	public void setProd_date(String prod_date) {
		this.prod_date = prod_date;
	}

	public String getProd_wearing_price() {
		return prod_wearing_price;
	}

	public void setProd_wearing_price(String prod_wearing_price) {
		this.prod_wearing_price = prod_wearing_price;
	}

	public String getProd_cnt_min() {
		return prod_cnt_min;
	}

	public void setProd_cnt_min(String prod_cnt_min) {
		this.prod_cnt_min = prod_cnt_min;
	}

	public String getProd_flag() {
		return prod_flag;
	}

	public void setProd_flag(String prod_flag) {
		this.prod_flag = prod_flag;
	}

	public String getProd_mod_date() {
		return prod_mod_date;
	}

	public void setProd_mod_date(String prod_mod_date) {
		this.prod_mod_date = prod_mod_date;
	}

	public String getProd_all() {
		return prod_all;
	}

	public void setProd_all(String prod_all) {
		this.prod_all = prod_all;
	}

	public String getProd_margin() {
		return prod_margin;
	}

	public void setProd_margin(String prod_margin) {
		this.prod_margin = prod_margin;
	}

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getProd_id() {
		return prod_id;
	}

	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}

	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public String getProd_price() {
		return prod_price;
	}

	public void setProd_price(String prod_price) {
		this.prod_price = prod_price;
	}

	public String getProd_cnt() {
		return prod_cnt;
	}

	public void setProd_cnt(String prod_cnt) {
		this.prod_cnt = prod_cnt;
	}

	@Override
	public String toString() {
		return "ProductVO [prod_id=" + prod_id + ", prod_name=" + prod_name + ", prod_price=" + prod_price
				+ ", prod_cnt=" + prod_cnt + ", prod_pic=" + prod_pic + ", prod_main_category=" + prod_main_category
				+ ", prod_sub_category=" + prod_sub_category + ", prod_ssub_category=" + prod_ssub_category
				+ ", prod_date=" + prod_date + ", prod_wearing_price=" + prod_wearing_price + ", prod_cnt_min="
				+ prod_cnt_min + ", prod_flag=" + prod_flag + ", prod_mod_date=" + prod_mod_date + ", prod_all="
				+ prod_all + ", prod_margin=" + prod_margin + ", company_id=" + company_id + "]";
	}


	
}

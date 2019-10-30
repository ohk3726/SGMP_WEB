package com.sgmp.web.vo;

public class OrderVO {
	private String prod_wearing_condition;
	private String prod_name;
	private String prod_wearing_price;
	private String prod_wearing_price_calc;
	private String prod_wearing_cnt;
	private String prod_root_cnt;
	private String prod_wearing_company_id;
	public String getProd_wearing_condition() {
		return prod_wearing_condition;
	}
	public void setProd_wearing_condition(String prod_wearing_condition) {
		this.prod_wearing_condition = prod_wearing_condition;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public String getProd_wearing_price() {
		return prod_wearing_price;
	}
	public void setProd_wearing_price(String prod_wearing_price) {
		this.prod_wearing_price = prod_wearing_price;
	}
	public String getProd_wearing_price_calc() {
		return prod_wearing_price_calc;
	}
	public void setProd_wearing_price_calc(String prod_wearing_price_calc) {
		this.prod_wearing_price_calc = prod_wearing_price_calc;
	}
	public String getProd_wearing_cnt() {
		return prod_wearing_cnt;
	}
	public void setProd_wearing_cnt(String prod_wearing_cnt) {
		this.prod_wearing_cnt = prod_wearing_cnt;
	}
	
	public String getProd_wearing_company_id() {
		return prod_wearing_company_id;
	}
	public void setProd_wearing_company_id(String prod_wearing_company_id) {
		this.prod_wearing_company_id = prod_wearing_company_id;
	}
	public String getProd_root_cnt() {
		return prod_root_cnt;
	}
	public void setProd_root_cnt(String prod_root_cnt) {
		this.prod_root_cnt = prod_root_cnt;
	}
	@Override
	public String toString() {
		return "OrderVO [prod_wearing_condition=" + prod_wearing_condition + ", prod_name=" + prod_name
				+ ", prod_wearing_price=" + prod_wearing_price + ", prod_wearing_price_calc=" + prod_wearing_price_calc
				+ ", prod_wearing_cnt=" + prod_wearing_cnt + ", prod_root_cnt=" + prod_root_cnt + ", prod_wearing_company_id="
				+ prod_wearing_company_id + "]";
	}
	
	
}

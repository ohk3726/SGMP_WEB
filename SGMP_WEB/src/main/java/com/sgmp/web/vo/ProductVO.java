package com.sgmp.web.vo;

public class ProductVO {
	private String prod_id;
	private String prod_name;
	private Integer prod_price;
	private Integer prod_cnt;
	
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

	public Integer getProd_price() {
		return prod_price;
	}

	public void setProd_price(Integer prod_price) {
		this.prod_price = prod_price;
	}

	public Integer getProd_cnt() {
		return prod_cnt;
	}

	public void setProd_cnt(Integer prod_cnt) {
		this.prod_cnt = prod_cnt;
	}

	@Override
	public String toString() {
		return "ProductVO [prod_id=" + prod_id + ", prod_name=" + prod_name + ", prod_price=" + prod_price
				+ ", prod_cnt=" + prod_cnt + "]";
	}

	
}

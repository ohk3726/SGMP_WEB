package com.sgmp.web.vo;

public class CustomerVO {
	private String CUST_ID;
	private String CUST_NAME;
	private String CUST_ADDRESS;
	private String CUST_DATE;

	public String getCUST_ID() {
		return CUST_ID;
	}

	public void setCUST_ID(String cUST_ID) {
		CUST_ID = cUST_ID;
	}

	public String getCUST_NAME() {
		return CUST_NAME;
	}

	public void setCUST_NAME(String cUST_NAME) {
		CUST_NAME = cUST_NAME;
	}

	public String getCUST_ADDRESS() {
		return CUST_ADDRESS;
	}

	public void setCUST_ADDRESS(String cUST_ADDRESS) {
		CUST_ADDRESS = cUST_ADDRESS;
	}

	public String getCUST_DATE() {
		return CUST_DATE;
	}

	public void setCUST_DATE(String cUST_DATE) {
		CUST_DATE = cUST_DATE;
	}

	@Override
	public String toString() {
		return "CustomerVO [CUST_ID=" + CUST_ID + ", CUST_NAME=" + CUST_NAME + ", CUST_ADDRESS=" + CUST_ADDRESS
				+ ", CUST_DATE=" + CUST_DATE + "]";
	}

}

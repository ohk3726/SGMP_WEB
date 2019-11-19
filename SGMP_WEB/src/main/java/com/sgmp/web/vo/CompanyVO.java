package com.sgmp.web.vo;

public class CompanyVO {

	private String COMPANY_NAME;
	private String COMPANY_ID;
	private String COMPANY_ADDRESS;
	private String COMPANY_NUMBER;
	
	
	
	
	public String getCOMPANY_ADDRESS() {
		return COMPANY_ADDRESS;
	}
	public void setCOMPANY_ADDRESS(String cOMPANY_ADDRESS) {
		COMPANY_ADDRESS = cOMPANY_ADDRESS;
	}
	public String getCOMPANY_NUMBER() {
		return COMPANY_NUMBER;
	}
	public void setCOMPANY_NUMBER(String cOMPANY_NUMBER) {
		COMPANY_NUMBER = cOMPANY_NUMBER;
	}
	public String getCOMPANY_NAME() {
		return COMPANY_NAME;
	}
	public void setCOMPANY_NAME(String cOMPANY_NAME) {
		COMPANY_NAME = cOMPANY_NAME;
	}
	public String getCOMPANY_ID() {
		return COMPANY_ID;
	}
	public void setCOMPANY_ID(String cOMPANY_ID) {
		COMPANY_ID = cOMPANY_ID;
	}
	@Override
	public String toString() {
		return "CompanyVO [COMPANY_NAME=" + COMPANY_NAME + ", COMPANY_ID=" + COMPANY_ID + ", COMPANY_ADDRESS="
				+ COMPANY_ADDRESS + ", COMPANY_NUMBER=" + COMPANY_NUMBER + "]";
	}
	
	
	
}

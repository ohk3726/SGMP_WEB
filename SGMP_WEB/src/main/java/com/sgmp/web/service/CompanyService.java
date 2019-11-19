package com.sgmp.web.service;

import java.util.List;

import com.sgmp.web.vo.CompanyVO;

public interface CompanyService {
	List<CompanyVO> selectCompanyList() throws Exception;
	List<CompanyVO> company_search(CompanyVO vo) throws Exception;
	int company_insert(CompanyVO vo) throws Exception;
	List<CompanyVO> company_modify_info(CompanyVO vo) throws Exception;
	int company_modify(CompanyVO vo) throws Exception;
	
}

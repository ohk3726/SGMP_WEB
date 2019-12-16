package com.sgmp.web.dao;

import java.util.List;
import java.util.Map;

import com.sgmp.web.vo.CompanyVO;

public interface CompanyDAO {
	List<CompanyVO> selectCompanyList() throws Exception;
	List<CompanyVO> company_search(CompanyVO vo) throws Exception;
	int company_insert(CompanyVO vo) throws Exception;
	List<CompanyVO> company_modify_info(CompanyVO vo) throws Exception;
	int company_modify(CompanyVO vo) throws Exception;
	int company_same_id(CompanyVO vo) throws Exception;
	List<Map> selectMap() throws Exception;

	
}

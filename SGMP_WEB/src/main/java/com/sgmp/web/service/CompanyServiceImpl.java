package com.sgmp.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgmp.web.dao.CompanyDAO;
import com.sgmp.web.vo.CompanyVO;

@Service("CompanyService")
@Repository
public class CompanyServiceImpl implements CompanyService{
	
	@Autowired
	CompanyDAO companymapper;
	
	@Override
	@Transactional
	public List selectCompanyList() throws Exception{
		return companymapper.selectCompanyList();
	}
	
	@Override
	@Transactional
	public int company_insert(CompanyVO vo) throws Exception{
		return companymapper.company_insert(vo);
	}
	
	@Override
	@Transactional
	public List<CompanyVO> company_search(CompanyVO vo) throws Exception{
		return companymapper.company_search(vo);
	}
	
	public List company_modify_info(CompanyVO vo) throws Exception{
		return companymapper.company_modify_info(vo);
	}
	public int company_modify(CompanyVO vo) throws Exception{
		return companymapper.company_modify(vo);
	}

	
}

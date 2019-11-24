package com.sgmp.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgmp.web.dao.MainDAO;
import com.sgmp.web.vo.ProductVO;

@Service("MainService")
@Repository
public class MainServiceImpl implements MainService{
	@Autowired
	MainDAO mainmapper;
	
	@Override
	@Transactional
	public List main_bbs() throws Exception{
		return mainmapper.main_bbs();
	}
	
	@Override
	@Transactional
	public List main_cnt_min(ProductVO vo) throws Exception{
		return mainmapper.main_cnt_min(vo);
	}
	
	@Override
	@Transactional
	public List main_top_list(ProductVO vo) throws Exception{
		return mainmapper.main_top_list(vo);
	}
	
	@Override
	@Transactional
	public List main_chart(ProductVO vo) throws Exception{
		return mainmapper.main_chart(vo);
	}
}

package com.sgmp.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgmp.web.dao.ChartDAO;
import com.sgmp.web.vo.OrderVO;
import com.sgmp.web.vo.ProductVO;

@Service("ChartService")
@Repository
public class ChartServiceImpl implements ChartService{
	@Autowired
	ChartDAO chartmapper;
	
	@Override
	@Transactional
	public List select_chart(ProductVO vo) throws Exception{
		return chartmapper.select_chart(vo);
	}
	
	@Override
	@Transactional
	public List select_category(ProductVO vo) throws Exception{
		return chartmapper.select_category(vo);
	}
	
	@Override
	@Transactional
	public List select_category_prod(ProductVO vo) throws Exception{
		return chartmapper.select_category_prod(vo);
	}
	
	@Override
	@Transactional
	public List p2p_output(OrderVO vo) throws Exception{
		return chartmapper.p2p_output(vo);
	}
	
	@Override
	@Transactional
	public List select_p2p_output_list(OrderVO vo) throws Exception{
		return chartmapper.select_p2p_output_list(vo);
	}
	
	@Override
	@Transactional
	public List p2p_input(OrderVO vo) throws Exception{
		return chartmapper.p2p_input(vo);
	}
	
	@Override
	@Transactional
	public List select_p2p_input_list(OrderVO vo) throws Exception{
		return chartmapper.select_p2p_input_list(vo);
	}
}

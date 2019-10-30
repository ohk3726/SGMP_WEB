package com.sgmp.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgmp.web.dao.ProductDAO;
import com.sgmp.web.vo.ProductVO;

@Service("ProductService")
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductDAO productMapper;
	
	@Override
	@Transactional
	public List selectProductList() throws Exception {
		return productMapper.selectProductList();
	}

	@Override
	@Transactional
	public List selectedCompanyList_A(ProductVO vo) throws Exception {
		return productMapper.selectedCompanyList_A(vo);
	}

	@Override
	@Transactional
	public List prodList() throws Exception {
		return productMapper.prodList();
	}

	@Override
	@Transactional
	public List productInfo(ProductVO vo_2) throws Exception {
		return productMapper.productInfo(vo_2);
	}
}

package com.sgmp.web.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sgmp.web.vo.ProductVO;

public interface ProductDAO {
	List<ProductVO> selectProductList() throws Exception;
	List<ProductVO> selectedCompanyList_A(ProductVO vo) throws Exception;
	List<ProductVO> prodList() throws Exception;
	List<ProductVO> productInfo(ProductVO vo) throws Exception;
	int product_modify(ProductVO vo) throws Exception;
	int product_insert(ProductVO vo) throws Exception;
	List<ProductVO> prod_search(ProductVO vo) throws Exception;
	//�����ϱ�
	void xlsExcelReader(ProductVO vo) throws Exception;
	void xlsxExcelReader(ProductVO vo) throws Exception;
	//�ߺ�Ȯ��
	int ExcelReader_id_same_count(ProductVO vo) throws Exception;
	void Reader_modify_all(ProductVO vo) throws Exception;
	//���� �����ϱ�
	void xlsExcelReader_modify(ProductVO vo) throws Exception;
	void xlsxExcelReader_modify(ProductVO vo) throws Exception;
	
}	

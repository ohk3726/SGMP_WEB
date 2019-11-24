package com.sgmp.web.service;

import java.util.List;

import com.sgmp.web.vo.NoticeVO;
import com.sgmp.web.vo.ProductVO;

public interface MainService {
	List<NoticeVO> main_bbs() throws Exception;
	List<ProductVO> main_cnt_min(ProductVO vo) throws Exception;
	List<ProductVO> main_top_list(ProductVO vo) throws Exception;
	List<ProductVO> main_chart(ProductVO vo) throws Exception;
}

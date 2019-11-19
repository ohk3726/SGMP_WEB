package com.sgmp.web.service;

import java.util.List;

import com.sgmp.web.vo.NoticeVO;

public interface NoticeService {
	List<NoticeVO> notice_list() throws Exception;
	List<NoticeVO> notice_list_info(NoticeVO vo) throws Exception;
	int notice_write(NoticeVO vo) throws Exception;
	int notice_modify(NoticeVO vo) throws Exception;
	int notice_uphit(NoticeVO vo) throws Exception;
	int notice_delete(NoticeVO vo) throws Exception;
	List<NoticeVO> notice_search_title(NoticeVO vo) throws Exception;
	List<NoticeVO> notice_search_name(NoticeVO vo) throws Exception;
	int notice_page() throws Exception;
	
	List<NoticeVO> notice_list_page(NoticeVO vo) throws Exception;
	int notice_page_Title(NoticeVO vo) throws Exception;
	
}

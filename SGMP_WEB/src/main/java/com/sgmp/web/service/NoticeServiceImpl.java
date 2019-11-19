package com.sgmp.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgmp.web.dao.NoticeDAO;
import com.sgmp.web.vo.NoticeVO;



@Service("NoticeService")
@Repository
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private NoticeDAO NoticeMapper;

	@Override
	@Transactional
	public List notice_list() throws Exception {
		return NoticeMapper.notice_list();
	}
	
	
	@Override
	@Transactional
	public List notice_list_info(NoticeVO vo) throws Exception {
		return NoticeMapper.notice_list_info(vo);
	}
	
	@Override
	@Transactional
	public int notice_write(NoticeVO vo) throws Exception{
		return NoticeMapper.notice_write(vo);
	}
	
	@Override
	@Transactional
	public int  notice_modify(NoticeVO vo) throws Exception{
		return NoticeMapper.notice_modify(vo);
	}
	
	@Override
	@Transactional
	public int  notice_uphit(NoticeVO vo) throws Exception{
		return NoticeMapper.notice_uphit(vo);
	}
	
	
	@Override
	@Transactional
	public int notice_delete(NoticeVO vo) throws Exception{
		return NoticeMapper.notice_delete(vo);
	}

	@Override
	@Transactional
	public List notice_search_title(NoticeVO vo) throws Exception{
		return NoticeMapper.notice_search_title(vo);
	}
	
	@Override
	@Transactional
	public List notice_search_name(NoticeVO vo) throws Exception{
		return NoticeMapper.notice_search_name(vo);
	}
	
	@Override
	@Transactional
	public int notice_page() throws Exception{
		return NoticeMapper.notice_page();
	}
	
	@Override
	@Transactional
	public List notice_list_page(NoticeVO vo) throws Exception{
		return NoticeMapper.notice_list_page(vo);
	}
	
	@Override
	@Transactional
	public int notice_page_Title(NoticeVO vo) throws Exception{
		return NoticeMapper.notice_page_Title(vo);
	}
	
}

package com.sgmp.web.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgmp.web.dao.MentDAO;
import com.sgmp.web.vo.MentVO;

@Service("MentService")
@Repository
public class MentServiceImpl implements MentService{
	
	@Autowired
	MentDAO MentMapper;


   // ´ñ±Û ¸ñ·Ï
	@Override
	@Transactional
   public List<MentVO> mentList(MentVO ment) throws Exception{
		/* sqlSession.selectList("com.sgmp.web.dao.MentDAO.listMemt",no_bid); */
	   return MentMapper.mentList(ment);
   }

  
   // ´ñ±Û ÀÛ¼ºs
	@Override
	@Transactional
   public int mentInsert(MentVO ment) throws Exception{
		/* sqlSession.insert("com.sgmp.web.dao.MentDAO.mentInsert", ment); */
		return MentMapper.mentInsert(ment);
   }
   
   // ´ñ±Û ¼öÁ¤
	@Override
	@Transactional
   public int mentUpdate(MentVO ment) throws Exception{
		return MentMapper.mentUpdate(ment);
   }

   // ´ñ±Û »èÁ¦
	@Override
	@Transactional
   public int mentDelete(int ment_id) throws Exception{
		return MentMapper.mentDelete(ment_id);
   }
	
}

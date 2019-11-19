package com.sgmp.web.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sgmp.web.vo.MentVO;


public class MentDAOImpl implements MentDAO {

	@Autowired
	SqlSession sqlSession;

	@Override
	public List<MentVO> mentList(MentVO ment){
		return sqlSession.selectList("com.sgmp.web.dao.MentDAO.mentList",ment);
	}
	
	@Override
	public int mentInsert(MentVO vo) {
		
		System.out.println("mentInsert = MentDAOImpl");
		
		return sqlSession.insert("com.sgmp.web.dao.MentDAO.mentInsert", vo);
	}
	
	@Override
	public int mentUpdate(MentVO vo) {
		return sqlSession.update("com.sgmp.web.dao.MentDAO.mentUpdate", vo);
	}
	
	@Override
	public int mentDelete(Integer ment_id) {
		return sqlSession.delete("com.sgmp.web.dao.MentDAO.mentDelete", ment_id);
		
	}
}

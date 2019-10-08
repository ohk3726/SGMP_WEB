package com.sgmp.web.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.sgmp.web.dao.LoginDAO;
import com.sgmp.web.vo.LoginVO;

@Service
public class LoginServiceImpl implements LoginService{
	@Inject
	LoginDAO loginDAO;
	
	@Override
	public int check(LoginVO loginVO) {
		return loginDAO.check(loginVO);
	}
}

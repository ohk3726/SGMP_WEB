package com.sgmp.web.service;

import com.sgmp.web.vo.LoginVO;

public interface LoginService {
	//로그인 서비스 인터페이스
	public int check(LoginVO loginVO);
}

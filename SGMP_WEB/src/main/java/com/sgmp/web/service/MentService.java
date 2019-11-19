package com.sgmp.web.service;

import java.util.List;

import com.sgmp.web.vo.MentVO;

public interface MentService {


   // ´ñ±Û ¸ñ·Ï
   public List<MentVO> mentList(MentVO ment) throws Exception;

   // ´ñ±Û ÀÛ¼º
   public int mentInsert(MentVO ment) throws Exception;
   
   // ´ñ±Û ¼öÁ¤
   public int mentUpdate(MentVO ment) throws Exception;

   // ´ñ±Û »èÁ¦
   public int mentDelete(int cno) throws Exception;
}

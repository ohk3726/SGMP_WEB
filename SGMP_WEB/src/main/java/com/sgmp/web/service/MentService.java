package com.sgmp.web.service;

import java.util.List;

import com.sgmp.web.vo.MentVO;

public interface MentService {


   // ��� ���
   public List<MentVO> mentList(MentVO ment) throws Exception;

   // ��� �ۼ�
   public int mentInsert(MentVO ment) throws Exception;
   
   // ��� ����
   public int mentUpdate(MentVO ment) throws Exception;

   // ��� ����
   public int mentDelete(int cno) throws Exception;
}

package com.sgmp.web.dao;

import java.util.List;

import com.sgmp.web.vo.MentVO;

public interface MentDAO {


 
    // ��� ���
    public List<MentVO> mentList(MentVO ment) throws Exception;
 
    // ��� �ۼ�
    public int mentInsert(MentVO ment) throws Exception;
    
    // ��� ����
    public int mentUpdate(MentVO ment) throws Exception;
 
    // ��� ����
    public int mentDelete(Integer ment_id) throws Exception;
}

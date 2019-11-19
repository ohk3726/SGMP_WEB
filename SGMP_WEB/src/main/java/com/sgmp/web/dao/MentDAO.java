package com.sgmp.web.dao;

import java.util.List;

import com.sgmp.web.vo.MentVO;

public interface MentDAO {


 
    // ´ñ±Û ¸ñ·Ï
    public List<MentVO> mentList(MentVO ment) throws Exception;
 
    // ´ñ±Û ÀÛ¼º
    public int mentInsert(MentVO ment) throws Exception;
    
    // ´ñ±Û ¼öÁ¤
    public int mentUpdate(MentVO ment) throws Exception;
 
    // ´ñ±Û »èÁ¦
    public int mentDelete(Integer ment_id) throws Exception;
}

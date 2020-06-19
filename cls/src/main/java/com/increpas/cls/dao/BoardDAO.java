package com.increpas.cls.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.increpas.cls.vo.BoardVO;
import com.increpas.cls.util.PageUtil;

public class BoardDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	public int getCnt() {
		return sqlSession.selectOne("bSQL.selCnt");
	}
	
	public List<BoardVO> getList(PageUtil page){
		return sqlSession.selectList("bSQL.getList", page);
	}
}

package com.increpas.cls.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.increpas.cls.vo.BoardVO;
import com.increpas.cls.vo.ProfileVO;
import com.increpas.cls.util.PageUtil;

public class BoardDAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	// 전체 게시글 수
	public int getCnt() {
		return sqlSession.selectOne("bSQL.selCnt");
	}
	
	// 게시글페이지 폼 보기
	public List<BoardVO> getList(PageUtil page){
		return sqlSession.selectList("bSQL.getList", page);
	}
	
	// 게시글 글 쓰기 처리
	public int addList(BoardVO bVO) {
		return sqlSession.insert("bSQL.addList", bVO);
	}
	
	// 게시글 파일 업로드 처리
	public int addImg(ProfileVO fVO) {
		return sqlSession.insert("bSQL.addImg", fVO);
	}
	
	// 게시글 상세보기 처리
	public List<BoardVO> getDetail(BoardVO bVO) {
		return sqlSession.selectList("bSQL.selDetail", bVO);
	}
}

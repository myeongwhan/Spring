package com.increpas.cls.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.increpas.cls.vo.MemberVO;

public class MemberDAO {
	@Autowired	// 자동의존주입
	SqlSessionTemplate sqlSession;
	
	public void testDAO() {
		System.out.println("### testDAO() 실행 ###");
	}
	
	public int idCheck(String id) {
		return sqlSession.selectOne("mSQL.idCount", id);
	}
	
	// 로그인 질의명령 전담처리함수
	public int login(MemberVO mVO) {
		return sqlSession.selectOne("mSQL.Login", mVO);
	}
}

package com.increpas.cls.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.increpas.cls.vo.MemberVO;
import com.increpas.cls.vo.ProfileVO;

public class MemberDAO {
	@Autowired	// 자동의존주입
	SqlSessionTemplate sqlSession;
	
	public void testDAO() {
		System.out.println("### testDAO() 실행 ###");
	}
	
	// 아이디 중복확인 전담처리함수
	public int idCheck(String id) {
		return sqlSession.selectOne("mSQL.idCount", id);
	}
	
	// 로그인 질의명령 전담처리함수
	public int login(MemberVO mVO) {
		return sqlSession.selectOne("mSQL.Login", mVO);
	}
	
	// 회원가입 처리 전담처리함수
	public int join(MemberVO mVO) {
		return sqlSession.insert("mSQL.join", mVO);
	}
	
	// 회원 버튼 리스트 가져오기 전담처리함수
	public List<MemberVO> getList() {
		return sqlSession.selectList("mSQL.nameList");
	}
	
	// 회원 상세정보 db조회 전담처리함수
	public MemberVO getDetail(int mno) {
		return sqlSession.selectOne("mSQL.mDetail", mno);
	}
	
	// 아바타 추가 데이터베이스 작업 전담처리함수
	public int addAvt(List list) {
		return sqlSession.insert("mSQL.addAvt", list);
	}
	
	// 프사넣기
	public int insertProfile(ProfileVO fVO) {
		return sqlSession.insert("mSQL.addProfile", fVO);
	}
	
	// 프사 savename 뽑기
	public ProfileVO selProfile(int mno) {
		return sqlSession.selectOne("mSQL.selProfile", mno);
	}
	
	// mno 조회
	public int outMno(String id) {
		return sqlSession.selectOne("mSQL.mno", id);
	}
	
	// 정보수정(MemberVO)
	public int editMember(MemberVO mVO) {
		return sqlSession.update("mSQL.editMember", mVO);
	}
	
}

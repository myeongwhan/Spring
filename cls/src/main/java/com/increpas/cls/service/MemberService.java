package com.increpas.cls.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.increpas.cls.dao.MemberDAO;
import com.increpas.cls.vo.MemberVO;

/**
 * 이 클래스는 회원정보와 관련된 작업을 처리할 서비스적인 기능을 담당할 클래스
 * @author	이명환
 * @since	2020.06.18
 * @version	v.1.0
 *
 */
public class MemberService {
	@Autowired
	MemberDAO mDAO;
	
	public int editMember(MemberVO mVO) {
		int cnt = 0;
		// 할일
		// 받은 데이터로 데이터베이스 작업을 해주고 결과받아서 반환해준다
		cnt = mDAO.editMember(mVO);
		return cnt;
	}
	
	public void loginCk(HttpServletRequest req, ModelAndView mv) {
		System.out.println("### MemberService ###");
	}
}

package com.increpas.cls.controller.member;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.increpas.cls.dao.MemberDAO;
import com.increpas.cls.vo.MemberVO;

@Controller
@RequestMapping("/member")
public class Member {
	@Autowired
	MemberDAO mDAO;
	
	@RequestMapping("/logout.cls")
	public ModelAndView logout(HttpSession session, ModelAndView mv) {
		/*
		 	로그아웃 처리를 할 함수
		 	로그아웃 처리가 정상적으로 실행되면 로그인페이지로 강제 이동하고,
		 	실패하면 메인페이지로 다시 이동시키자
		 */
		String view = "/cls/member/login.cls";
		
		RedirectView rv = null;
		session.removeAttribute("SID");
		String sid = (String) session.getAttribute("SID");
		if(sid != null) { 
			view = "/cls/main";
		}
		
		rv = new RedirectView(view);
		mv.setView(rv);
		return mv;
	}

	@RequestMapping("/login.cls")
	public ModelAndView login(HttpSession session, ModelAndView mv) {
		String sid = (String) session.getAttribute("SID");
		String view = "member/login";
		if(sid != null) {
			System.out.println("Session SID: " + session.getAttribute("SID"));
			RedirectView rv= new RedirectView("/cls/main");
			mv.setView(rv);
		} else {
			mv.setViewName(view);
		}
		return mv;
	}
	
	@RequestMapping("/join.cls")
	public ModelAndView join(HttpSession session, ModelAndView mv) {
		String sid = (String) session.getAttribute("SID");
		String view = "member/join";
		if(sid != null) {
			RedirectView rv= new RedirectView("main");
			mv.setView(rv);
		} else {
			mv.setViewName(view);
		}
		return mv;
	}
	
	@RequestMapping(path={"/Login.cls", "/Join.cls"})
	public ModelAndView doolcuri(ModelAndView mv) {
		String view = "main";
		mv.setViewName(view);
		return mv;
	}
	
	@RequestMapping(value="/loginProc.cls", method=RequestMethod.POST, params= {"!id", "!pw"})
	public ModelAndView loginProcRedirect(String id, String pw, ModelAndView mv) {
		System.out.println("## redirect ##");
		System.out.println("## id: " + id);
		System.out.println("## pw: " + pw);
		
		// 임시로 당분간은 로그인페이지로 리다이렉트시키기로 하자
		RedirectView rv = new RedirectView("/cls/member/login.cls");
		
		mv.setView(rv);
		return mv;
	}
	
	
	@RequestMapping(value="/loginProc.cls", method=RequestMethod.POST, params= {"id", "pw"})
	public ModelAndView loginProc(String id, String pw, MemberVO mVO, ModelAndView mv, HttpSession session) {
//	public ModelAndView loginProc(String id, String pw, ModelAndView mv, HttpSession session) {
//		System.out.println("## id: " + id);
//		System.out.println("## pw: " + pw);
		
		// 데이터 받고 전달할 데이터 만들고
		/*
		MemberVO mVO = new MemberVO();
		mVO.setId(id);
		mVO.setPw(pw);
		*/
		int cnt = mDAO.login(mVO);
		mDAO.testDAO();
		
		RedirectView rv = null;
		if(cnt == 1) {
			session.setAttribute("SID", mVO.getId());
			rv = new RedirectView("/cls/main");
		} else {
			// 아이디와 비밀번호에 맞는 회원이 없는 경우이므로 다시 로그인페이지로 이동시킨다
			rv = new RedirectView("/cls/member/login.cls");
		}
		mv.setView(rv);
		return mv;
	}
	
	// 회원 버튼 리스트페이지 보기 요청처리함수
	@RequestMapping("/memberList.cls")
	public ModelAndView getList(ModelAndView mv) {
		String view = "member/memberList";
		// 할일
		// 1. 받을 데이터는 없으므로 보낼 데이터만 만들면 된다
		ArrayList<String> color = getColorList();
		
		// 1-2. 회원 버튼에 필요한 리스트 가져오기
		ArrayList<MemberVO> list = (ArrayList<MemberVO>)mDAO.getList();
		
		// 2. 데이터가 준비되었으면 데이터를 넘겨주고
		mv.addObject("COLOR", color);	// jsp의 req.setAttribute 역할
		mv.addObject("LIST", list);
		// 3. 뷰도 넘겨주고
		mv.setViewName(view);
		return mv;
	}
	
	// w3.css 컬러 클래스 리스트 반환함수
	public ArrayList<String> getColorList() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("w3-red"); 
		list.add("w3-pink"); 
		list.add("w3-purple"); 
		list.add("w3-deep-purple"); 
		list.add("w3-indigo"); 
		list.add("w3-blue"); 
		list.add("w3-cyan"); 
		list.add("w3-aqua"); 
		list.add("w3-teal"); 
		list.add("w3-green"); 
		list.add("w3-light-green"); 
		list.add("w3-lime"); 
		list.add("w3-khaki"); 
		list.add("w3-yellow"); 
		list.add("w3-amber"); 
		list.add("w3-orange"); 
		list.add("w3-deep-orange");
		
		return list;
	}
}

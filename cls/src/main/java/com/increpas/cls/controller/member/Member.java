package com.increpas.cls.controller.member;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.increpas.cls.dao.MemberDAO;
import com.increpas.cls.home.HomeController;
import com.increpas.cls.service.MemberService;
import com.increpas.cls.service.ProfileService;
import com.increpas.cls.vo.BoardVO;
import com.increpas.cls.vo.MemberVO;
import com.increpas.cls.vo.ProfileVO;

@Controller
@RequestMapping("/member")
public class Member {
	@Autowired
	MemberDAO mDAO;
	@Autowired
	ProfileService profileSrvc;
	@Autowired
	MemberService mSrvc;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping("/logout.cls")
	public ModelAndView logout(HttpSession session, ModelAndView mv) {
		/*
		 	로그아웃 처리를 할 함수
		 	로그아웃 처리가 정상적으로 실행되면 로그인페이지로 강제 이동하고,
		 	실패하면 메인페이지로 다시 이동시키자
		 */
		
		// 로그아웃 - 메인, 실패 - 로그인페이지로
		String view = "/cls/main";
		
		RedirectView rv = null;
		session.removeAttribute("SID");
		String sid = (String) session.getAttribute("SID");
		if(sid != null) { 
			System.out.println("로그아웃 실패");
			view = "/cls/member/login.cls";
		}
		
		rv = new RedirectView(view);
		mv.setView(rv);
		return mv;
	}

	@RequestMapping("/login.cls")
	public ModelAndView loginForm(HttpServletRequest req, ModelAndView mv) {
//		String sid = (String) session.getAttribute("SID");
//		String view = "member/login";
//		if(sid != null) {
//			System.out.println("Session SID: " + session.getAttribute("SID"));
//			RedirectView rv= new RedirectView("/cls/main");
//			mv.setView(rv);
//		} else {
//			mv.setViewName(view);
//		}
		System.out.println("####controller");
		return mv;
	}
	
	// 회원가입 뷰
	@RequestMapping("/join.cls")
	public ModelAndView joinForm(HttpServletRequest req, ModelAndView mv) {
//		String sid = (String) session.getAttribute("SID");
//		String view = "member/join";
//		if(sid != null) {
//			RedirectView rv= new RedirectView("main");
//			mv.setView(rv);
//		} else {
//			mv.setViewName(view);
//		}
		return mv;
	}
	
	// 회원가입 처리
	@RequestMapping("/joinProc.cls")
	public ModelAndView joinProc(ProfileVO fVO, MemberVO mVO, ModelAndView mv, HttpSession session) {
		// 전처리기로 mVO의 데이터가 유효하다는 전제하에 코딩해보자
		
		int cnt = mDAO.join(mVO);
		fVO.setMno(mVO.getMno());
		RedirectView rv = null;
		if(cnt == 1) {
			// 프로파일 정보를 데이터베이스에 저장한다
			int count = profileSrvc.addProfile(fVO, session);
			if(count != 1) {
				// 이 경우는 파일 정보 입력에 실패한 파일이 있는 경우이므로
				// 이후 적절히 처리해주기로 하자
				System.out.println("파일업로드 실패 ProfileService.addProfile()");
			}
			
			rv = new RedirectView("/cls/member/login.cls");
		} else {
			rv = new RedirectView("/cls/member/join.cls");
			System.out.println("회원가입 처리 에러");
		}
		
		mv.setView(rv);
		return mv;
	}
	
	// 아이디 중복확인 비동기처리
	@RequestMapping("/idCk.cls")
	@ResponseBody	// (응답body 안에 반환값이 들어간다) 비동기처리할때 쓰는 어노테이션
	public Map<String, Integer> idCheck(String id) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("cnt", mDAO.idCheck(id));	// {"cnt": 0}
		
		return map;
	}
	
	@RequestMapping(path={"/Login.cls", "/Join.cls"})
	public ModelAndView doolcuri(HttpServletRequest req, ModelAndView mv) {
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
	
	// (현재 실행중인)로그인처리
	@RequestMapping(value="/loginProc.cls", method=RequestMethod.POST, params= {"id", "pw"})
	public ModelAndView loginProc(String id, String pw, MemberVO mVO, ModelAndView mv, HttpSession session, Locale locale) {
//	public ModelAndView loginProc(String id, String pw, ModelAndView mv, HttpSession session) {
//		System.out.println("## id: " + id);
//		System.out.println("## pw: " + pw);
		
		// 데이터 받고 전달할 데이터 만들고
		/*
		MemberVO mVO = new MemberVO();
		mVO.setId(id);
		mVO.setPw(pw);
		*/
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		
		int cnt = mDAO.login(mVO);
		mDAO.testDAO();
		
		RedirectView rv = null;
		if(cnt == 1) {
			session.setAttribute("SID", mVO.getId());
			logger.info("Login ID : {} - {}", mVO.getId(), formattedDate);
			rv = new RedirectView("/cls/main");
		} else {
			// 아이디와 비밀번호에 맞는 회원이 없는 경우이므로 다시 로그인페이지로 이동시킨다
			rv = new RedirectView("/cls/member/login.cls");
		}
		mv.setView(rv);
		return mv;
	}
	
	// 정보수정페이지 뷰
	@RequestMapping("/memberEdit.cls")
	public ModelAndView memberEdit(HttpSession session, ModelAndView mv) {
		String sid = (String) session.getAttribute("SID");
		String view = "member/memberEdit";
		
		if(sid != null) {
			mv.setViewName(view);
		} else {
			RedirectView rv= new RedirectView("/cls/main");
			mv.setView(rv);
		}
		
		int mno = mDAO.outMno(sid);
		MemberVO mVO = mDAO.getDetail(mno);
		mVO.setBirth();
		ProfileVO fVO = mDAO.selProfile(mno);
//		List<ProfileVO> list = mDAO.sel2Profile(mno);
		
		mv.addObject("DATA", mVO);
		mv.addObject("PIC", fVO);
//		mv.addObject("PIC", list);
		return mv;
	}
	
	// 정보수정 처리
	@RequestMapping("/memberEditProc.cls")
	public ModelAndView editProc(HttpSession session, ModelAndView mv, MemberVO mVO, ProfileVO fVO) {
		// 할일
		// 데이터 체크하고
//		int cnt = 0;
		if(mVO.getPw() != null || mVO.getAno() != 0) {
			System.out.println("asdf");
			mSrvc.editMember(mVO);
		}
		
		// 맨 마지막 파일(인덱스) 널 빼기
		fVO.setFile(Arrays.copyOf(fVO.getFile(), fVO.getFile().length - 1));
		
		profileSrvc.addProfile(fVO, session);
		RedirectView rv = new RedirectView("/cls/member/memberDetail.cls?mno=" + mVO.getMno());
		mv.setView(rv);
		return mv;
		
//		int cnt = mDAO.editMember(mVO);
//		if(cnt == 1) {
//			RedirectView rv = new RedirectView("/cls/main");
//			mv.setView(rv);
//		} else {
//			System.out.println("정보수정에러");
//		}
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
	
	// 회원 상세정보 요청 처리 함수
	@RequestMapping("/memberDetail.cls")
	public ModelAndView getDetail(HttpSession session, ModelAndView mv, int mno) {
		// 먼저 로그인 되어있는지 확인하고
		if(session.getAttribute("SID") == null) {
			RedirectView rd = new RedirectView("/cls/member/login.cls");
			mv.setView(rd);
			return mv;
		}
		
		// 할일
		// 데이터는 이미 매개변수에서 받아왔으므로
		// db처리만 해주고 데이터 받아서 뷰에 넘기면 된다
		
		// 데이터 심고
		MemberVO mVO = mDAO.getDetail(mno);
		mv.addObject("DATA", mVO);
		// 뷰 심고
		mv.setViewName("member/memberDetail");
		return mv;
	}
	
	// 회원 상세정보 요청 처리 비동기통신 함수
	@RequestMapping("/mDetail.cls")
	@ResponseBody	//	==> 반환값을 여기에 채우라는 어노테이션. json문서 자동으로 만들어줌
	public MemberVO mDetail(HttpSession session, MemberVO mVO) {
		// 먼저 로그인 되어있는지 확인하고
		if(session.getAttribute("SID") == null) {
			mVO.setStatus("No");
		}
		mVO = mDAO.getDetail(mVO.getMno());
		mVO.setSdate();
		return mVO;
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

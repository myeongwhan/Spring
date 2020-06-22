package com.increpas.cls.controller.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.increpas.cls.dao.BoardDAO;
import com.increpas.cls.service.BoardService;
import com.increpas.cls.util.PageUtil;
import com.increpas.cls.vo.BoardVO;

@Controller
@RequestMapping("/board")
public class Board {
	@Autowired
	BoardDAO bDAO;
	@Autowired
	BoardService bSrvc;
	
	// 게시판페이지 뷰
	@RequestMapping("/boardList.cls")
	public ModelAndView boardList(HttpServletRequest req, ModelAndView mv, PageUtil page) {
		/*
		String sid = (String) req.getAttribute("SID");
		if(sid == null || sid.length() == 0) {
			RedirectView rv = new RedirectView("/cls/member/login.cls");
			mv.setView(rv);
		} else {
			if(page.getNowPage() == 0) {
				page.setNowPage(1);
//				page.toString();
			}
			
			int totalCount = bDAO.getCnt();
			page.setPage(totalCount);
			
			ArrayList<BoardVO> list = (ArrayList<BoardVO>)bDAO.getList(page);
			
			mv.addObject("LIST", list);
			mv.addObject("PAGE", page);
			mv.setViewName("board/boardList");
		}
		*/
		bSrvc.getBrdList(req, mv, page);
		
		return mv;
	}
	
	// 게시판 글쓰기 폼 보기
	@RequestMapping("/boardWrite.cls")
	public ModelAndView writeForm(HttpServletRequest req, ModelAndView mv, PageUtil page) {
		bSrvc.writeSrvc(req, mv, page);
		
		return mv;
	}
	
	// 게시판 글쓰기 처리
	@RequestMapping("/boardWriteProc.cls")
	public ModelAndView writeProc(HttpServletRequest req, ModelAndView mv, PageUtil page, BoardVO bVO) {
		String sid = (String) req.getSession().getAttribute("SID");
		System.out.println(sid);
		bVO.setId(sid);
		int cnt = bDAO.writeProc(bVO);
		System.out.println(cnt);
		if(cnt == 1) {
			bSrvc.writeProcSrvc(req, mv, page);
		} else {
			mv.setView(new RedirectView("/cls/member/login.cls"));
		}
		return mv;
	}
}

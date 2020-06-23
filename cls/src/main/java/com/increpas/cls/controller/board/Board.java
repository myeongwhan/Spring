package com.increpas.cls.controller.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.increpas.cls.dao.BoardDAO;
import com.increpas.cls.service.BoardService;
import com.increpas.cls.util.PageUtil;
import com.increpas.cls.vo.BoardVO;
import com.increpas.cls.vo.ProfileVO;

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
	public ModelAndView writeProc(HttpServletRequest req, ModelAndView mv, BoardVO bVO, ProfileVO fVO) {
		bVO.setfVO(fVO);
		try {
			bSrvc.writeSrvc(req, mv, bVO);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	// 게시판 상세보기 폼
	@RequestMapping("/boardDetail.cls")
	public ModelAndView boardDetail(HttpServletRequest req, ModelAndView mv, BoardVO bVO, PageUtil page) {
		bSrvc.getDetail(req, mv, bVO);
		mv.addObject("PAGE", page);
		return mv;
	}
	
	// 게시판 상세보기 처리
	@RequestMapping("/boardDetailProc.cls")
	public ModelAndView detailProc(HttpServletRequest req, ModelAndView mv, PageUtil page, BoardVO bVO) {
		
		return mv;
	}
}

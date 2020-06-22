package com.increpas.cls.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.increpas.cls.dao.BoardDAO;
import com.increpas.cls.util.PageUtil;
import com.increpas.cls.vo.BoardVO;

@Service	// 자동으로 빈처리 추가해줌(@Controller, @service 등)
public class BoardService {
	@Autowired
	BoardDAO bDAO;
	
	// 게시판리스트 요청처리 서비스 함수
	public void getBrdList(HttpServletRequest req, ModelAndView mv, PageUtil page) {
		if((Boolean)mv.getModel().get("isLogin")) {
			if(page.getNowPage() == 0) {
				page.setNowPage(1);
			}
			
			int totalCount = bDAO.getCnt();
			page.setPage(totalCount);
			
			ArrayList<BoardVO> list = (ArrayList<BoardVO>)bDAO.getList(page);
			
			mv.addObject("LIST", list);
			mv.addObject("PAGE", page);
			mv.setViewName("board/boardList");
		} else {
			mv.setView(new RedirectView("/cls/member/login.cls"));
		}
	}
	
	// 게시판 글쓰기 폼 보기 요청처리 서비스 함수
	public void writeSrvc(HttpServletRequest req, ModelAndView mv, PageUtil page) {
		if((Boolean)mv.getModel().get("isLogin")) {
			if(page.getNowPage() == 0) {
				page.setNowPage(1);
			}
			
			mv.addObject("PAGE", page);
			mv.setViewName("board/boardWrite");
		} else {
			mv.setView(new RedirectView("/cls/member/login.cls"));
		}
	}
	
	// 게시판 글쓰기 처리
	public void writeProcSrvc(HttpServletRequest req, ModelAndView mv, PageUtil page) {
		if((Boolean)mv.getModel().get("isLogin")) {
			if(page.getNowPage() == 0) {
				page.setNowPage(1);
			}
			
			mv.addObject("PAGE", page);
			mv.setView(new RedirectView("/cls/board/boardList.cls"));
		} else {
			mv.setView(new RedirectView("/cls/member/login.cls"));
		}
		
	}
}

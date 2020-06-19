package com.increpas.cls.controller.board;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.increpas.cls.dao.BoardDAO;
import com.increpas.cls.util.PageUtil;
import com.increpas.cls.vo.BoardVO;

@Controller
@RequestMapping("/board")
public class Board {
	@Autowired
	BoardDAO bDAO;
	
	// 게시판페이지 뷰
		@RequestMapping("/boardList.cls")
		public ModelAndView boardList(ModelAndView mv, PageUtil page, HttpSession session) {
			String sid = (String) session.getAttribute("SID");
			if(sid == null || sid.length() == 0) {
				RedirectView rv = new RedirectView("/cls/member/login.cls");
				mv.setView(rv);
			} else {
				if(page.getNowPage() == 0) {
					page.setNowPage(1);
					page.toString();
				}
				
				int totalCount = bDAO.getCnt();
				page.setPage(totalCount);
				
				ArrayList<BoardVO> list = (ArrayList<BoardVO>)bDAO.getList(page);
				
				mv.addObject("LIST", list);
				mv.addObject("PAGE", page);
				mv.setViewName("board/boardList");
			}
			
			return mv;
		}
}

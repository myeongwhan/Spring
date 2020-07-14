package com.increpas.cls.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.increpas.cls.dao.BoardDAO;
import com.increpas.cls.util.FileUtil;
import com.increpas.cls.util.PageUtil;
import com.increpas.cls.vo.BoardVO;
import com.increpas.cls.vo.ProfileVO;

@Service // 자동으로 빈처리 추가해줌(@Controller, @service 등)
public class BoardService {
	@Autowired
	BoardDAO bDAO;
	@Autowired
	FileUtil fUtil;

	// 게시판리스트 요청처리 서비스 함수
	public void getBrdList(HttpServletRequest req, ModelAndView mv, PageUtil page) {
		if ((Boolean) mv.getModel().get("isLogin")) {
			if (page.getNowPage() == 0) {
				page.setNowPage(1);
			}

			int totalCount = bDAO.getCnt();
			page.setPage(totalCount);

			ArrayList<BoardVO> list = (ArrayList<BoardVO>) bDAO.getList(page);

			mv.addObject("LIST", list);
			mv.addObject("PAGE", page);
			mv.setViewName("board/boardList");
		} else {
			mv.setView(new RedirectView("/cls/member/login.cls"));
		}
	}

	// 게시판 글쓰기 폼 보기 요청처리 서비스 함수
	public void writeSrvc(HttpServletRequest req, ModelAndView mv, PageUtil page) {
		if ((Boolean) mv.getModel().get("isLogin")) {
			if (page.getNowPage() == 0) {
				page.setNowPage(1);
			}

			mv.addObject("PAGE", page);
			mv.setViewName("board/boardWrite");
		} else {
			mv.setView(new RedirectView("/cls/member/login.cls"));
		}
	}

	// 게시판 글 작성 데이터베이스 입력 서비스 함수
	@Transactional
	public void writeSrvc(HttpServletRequest req, ModelAndView mv, BoardVO bVO) throws Exception {
		if ((Boolean) mv.getModel().get("isLogin")) {
			mv.setView(new RedirectView("/cls/board/boardWrite.cls"));
			bVO.setId((String) req.getSession().getAttribute("SID"));
			bDAO.addList(bVO);
			ProfileVO fVO = bVO.getfVO();
			fVO.setBno(bVO.getBno());

			MultipartFile[] file = fVO.getFile();
			if (file.length > 1 || file[0].getOriginalFilename() != null) {
				String[] savename = fUtil.getSaveName(req.getSession(), file, "upload");
				for (int i = 0; i < savename.length; i++) {
					fVO.setOriname(file[i].getOriginalFilename());
					fVO.setSavename(savename[i]);
					fVO.setLen(file[i].getSize());

					bDAO.addImg(bVO.getfVO());
				}
			}

			mv.setView(new RedirectView("/cls/board/boardList.cls"));
		} else {
			mv.setView(new RedirectView("/cls/member/login.cls"));
		}
	}

	/*
	 * // 게시판 글쓰기 처리 public void writeProcSrvc(HttpServletRequest req, ModelAndView
	 * mv, PageUtil page) { if((Boolean)mv.getModel().get("isLogin")) {
	 * if(page.getNowPage() == 0) { page.setNowPage(1); }
	 * 
	 * mv.addObject("PAGE", page); mv.setView(new
	 * RedirectView("/cls/board/boardList.cls")); } else { mv.setView(new
	 * RedirectView("/cls/member/login.cls")); }
	 * 
	 * }
	 */

	// 게시글 가져오기 서비스 함수
	public void getDetail(HttpServletRequest req, ModelAndView mv, BoardVO bVO) {
		if ((Boolean) mv.getModel().get("isLogin")) {
			List<BoardVO> list = bDAO.getDetail(bVO);
			mv.addObject("LIST", list);
			mv.setViewName("board/boardDetail");
		} else {
			mv.setView(new RedirectView("/cls/member/login.cls"));
		}
	}
}

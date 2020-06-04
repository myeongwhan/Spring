package com.increpas.cls.controller.reboard;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.increpas.cls.dao.ReBoardDAO;

@Controller
@RequestMapping("/reboard")
public class ReBoard {
	@Autowired
	ReBoardDAO rDAO;
	
	@RequestMapping("/reboard.cls")
	public ModelAndView reboard(HttpSession session, ModelAndView mv) {
		
		return mv;
		
	}
}

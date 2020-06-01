package com.increpas.cls;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Test01 {

	@RequestMapping("/test/t01.cls")
	public String test01() {
		return "test/test01";
	}
}

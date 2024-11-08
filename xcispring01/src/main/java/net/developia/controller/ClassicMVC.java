package net.developia.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

@Controller
public class ClassicMVC extends AbstractController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String msg = "Spring MVC~!";
		ModelAndView mav = new ModelAndView();
		// views에 있는 classic.jsp로 전달해준다.
		// src/main/webapp/WEB-INF/views/classic.jsp
		mav.setViewName("classic");
		// 필요한 매개변수 전부 나열하기
		mav.addObject("msg", msg);
		return mav;
	}
	
}

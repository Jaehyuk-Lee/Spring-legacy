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
		mav.setViewName("classic");
		mav.addObject("msg", msg);
		return mav;
	}
	
}

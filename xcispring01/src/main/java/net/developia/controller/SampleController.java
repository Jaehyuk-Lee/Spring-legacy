package net.developia.controller;

import java.util.Arrays;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.java.Log;
import net.developia.domain.SampleDTO;
import net.developia.domain.SampleDTOList;
import net.developia.domain.TodoDTO;

// Reuqest 받기! 예제들

@Controller
// 최상위에 RequestMapping을 넣어서 공통 url 설정
@RequestMapping("/sample/*")
@Log
public class SampleController {
	
	// 기본 매핑 (걸리지 않은 모든 url에 대해 작동)
	@RequestMapping(value="")
	public void basic() {
		log.info("basic.....");
	}
	
	// 메서드에 url 특정하기
	@RequestMapping(value="/basic", method= {RequestMethod.GET, RequestMethod.POST})
	public void basicGet() {
		log.info("basic GET......");
	}
	
	@GetMapping("/basicOnlyGet")
	public void basiconly() {
		log.info("basic get only get.........");
	}
	
	@GetMapping("/ex01")
	public String ex01(@ModelAttribute SampleDTO dto, Model model) { // @ModelAttribute는 알아서 붙여주지만 직접 붙이는게 정석
		log.info("" + dto);
//		model.addAttribute("name", dto.getName());
//		model.addAttribute("age", dto.getAge());
		return "ex";
	}
	
	@GetMapping("/ex02")
	public String ex02(@RequestParam("name") String name, @RequestParam(value="age", defaultValue="99") int age, Model model) {
		log.info("name: " + name);
		log.info("age: " + age);
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		return "ex";
	}
	
	@GetMapping("/ex02Array")
	public String ex02Array(@RequestParam("ids") String[] ids, Model model) {
		log.info("ids: " + Arrays.toString(ids));
		model.addAttribute("ids", ids);
		return "ex";
	}
	
	@GetMapping("/ex02Bean")
	public String ex02Bean(@ModelAttribute SampleDTOList list, Model model) {
		log.info("list dtos: " + list);
		model.addAttribute("list", list);
		return "ex";
	}
	
//	@InitBinder
//	public void initBiner(WebDataBinder binder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		binder.registerCustomEditor(java.util.Date.class,  new CustomDateEditor(dateFormat,  false));
//		log.info(dateFormat.toString());
//	}
	
	@GetMapping("/ex03")
	public String ex03(TodoDTO todo) {
		log.info("todo: " + todo);
		return "ex";
	}
	
	@GetMapping("/exsp")
	public ModelAndView exsp() {
		ModelAndView mav = new ModelAndView("ex");
		mav.addObject("name", "name sp");
		mav.addObject("age", 99);
		return mav;
	}
	
	// @ResponseBody를 쓰면 뷰와 모델을 거치지 않고 컨트롤러에서 바로 리스폰스를 보낸다.
	// API 만들 때 씀 (요청한 내용의 일부만 수정해서 다시 보내주기 등)
	// 기본적으로 xml로 보내짐. '.json'을 붙이면 json 형식으로 출력됨.
	// Response를 보낼 때는 상태값을 같이 보내주는게 좋다.
	@GetMapping("/ex06")
	public @ResponseBody SampleDTO ex06() {
		log.info("/ex06......");
		
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("홍길동");
		return dto;
	}
	
	// ResponseEntity를 리턴하는 것도 바로 뿌려줄 수 있네
	@GetMapping("/ex07")
	public ResponseEntity<SampleDTO> ex07() {
		log.info("/ex07.....");
		
		// String 응답하기
		// {"name": "홍길동"} ResponseEntity 제네릭 바꾸면 됨
		String msg = "{\"name\": \"홍길동\"}";
		
		// 객체 응답하기
		SampleDTO dto = new SampleDTO();
		dto.setAge(99);
		dto.setName("홍길동");
		
		HttpHeaders header = new HttpHeaders();
		header.add("Content-type", "application/json;charset=UTF-8");
		return new ResponseEntity<>(dto, header, HttpStatus.OK);
	}
	
	
}

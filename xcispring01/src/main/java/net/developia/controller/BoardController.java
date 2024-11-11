package net.developia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.developia.domain.BoardVO;
import net.developia.service.BoardService;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	private BoardService service;
	
	@RequestMapping("/list")
	public String list(Model model) {
		log.info("list........");
		try {
			model.addAttribute("list", service.getList());
			return "list";
		} catch (Exception e) {
			e.printStackTrace();
			// log.error(e.getMessage());
			return null;
		}
	}
	
	// Redirect를 하면 Model이 없고 RedirectAtrributes가 있다
	// 값을 한 번만 뿌려주고 사라지는 값
	// 완전히 새로운 페이지로 넘어가고, 세션에 값을 줌
	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {
		log.info("register:" + board);
		try {
			service.register(board);
			rttr.addFlashAttribute("result", board.getBno());
			return "redirect:/board/list";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@GetMapping("/get")
	public void get(long bno, Model model) {
		log.info("get: " + bno);
		try {
			model.addAttribute("board", service.get(bno));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr) {
		log.info("modify: " + board);
		
		try {
			if (service.modify(board)) {
				rttr.addFlashAttribute("result", "success");
			}
			return "redirect:/board/list";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
		log.info("remove: " + bno);
		try {
			if(service.remove(bno)) {
				rttr.addFlashAttribute("result", "success");
			}
			return "redirect:/board/list";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

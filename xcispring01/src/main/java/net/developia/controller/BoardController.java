package net.developia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import net.developia.domain.BoardVO;
import net.developia.domain.Criteria;
import net.developia.domain.PageDTO;
import net.developia.service.BoardService;

@Controller
@Log4j
@RequestMapping("/board/*")
@AllArgsConstructor
public class BoardController {
	private BoardService service;
	
//	@RequestMapping("/list")
//	public String list(Model model) {
//		log.info("list........");
//		try {
//			model.addAttribute("list", service.getList());
//			return "board/list";
//		} catch (Exception e) {
//			e.printStackTrace();
//			// log.error(e.getMessage());
//			return null;
//		}
//	}
	
	@RequestMapping("/list")
	public String list(Criteria cri, Model model) {
		log.info("list........" + cri);
		try {
			int total = service.getTotal(cri);
			log.info("total: " + total);
			model.addAttribute("list", service.getList(cri));
			model.addAttribute("pageMaker", new PageDTO(cri, total));
			return "board/list";
		} catch (Exception e) {
			e.printStackTrace();
			// log.error(e.getMessage());
			return null;
		}
	}
	
	@GetMapping("/register")
	public void register() { }
	
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
	
	@GetMapping({"/get", "/modify"})
	public void get(@RequestParam("bno") long bno, @ModelAttribute("cri") Criteria cri, Model model) {
		log.info("get or modify: " + bno);
		try {
			model.addAttribute("board", service.get(bno));
			// @ModelAttribute로 받은 파라미터는 자동으로 Model에 데이터를 지정한 이름으로 담아서 전달해줌
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("modify: " + board);
		
		try {
			if (service.modify(board)) {
				rttr.addFlashAttribute("result", "modify-success");
			}
			rttr.addAttribute("pageNum", cri.getPageNum());
			rttr.addAttribute("amount", cri.getAmount());
			
			return "redirect:/board/list";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("remove: " + bno);
		try {
			if(service.remove(bno)) {
				rttr.addFlashAttribute("result", "remove-success");
			}
			rttr.addAttribute("pageNum", cri.getPageNum());
			rttr.addAttribute("amount", cri.getAmount());
			
			return "redirect:/board/list";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

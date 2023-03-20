package dogwhiz.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dogwhiz.index.IndexController;
import dogwhiz.model.Post;
import dogwhiz.service.PostService;

@Controller
public class PostController {
	private static final Logger logger = LoggerFactory.getLogger(PostController.class);
	
	@Autowired
	private PostService service;
	
	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public String viewAnnoucement(Model model, 
		@RequestParam(value = "category", defaultValue = "all") String category,
		@RequestParam(value = "page", defaultValue = "1") int page) {
		
		// 한 페이지에 보여줄 게시글 수
		int size = 10;
		
		// 전체 게시글 수 구하기
		int totalCount = service.getPostCountByCategory(category);
		
		// 페이지 수 계산
		int pageCount = (int) Math.ceil((double) totalCount / size);
		
		// 현재 페이지의 게시물 가져오기
		int start = (page - 1) * size;
		int end = Math.min(start + size, totalCount);
		List<Post> posts = service.getPostByCategoryBetweenNo(category, start, end);
		
		// model에 데이터 추가
		model.addAttribute("post", posts);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("currentPage", page);
		model.addAttribute("category", category);
	    
		return "/post/postlist";
	}
	
	@GetMapping("/post/add")
	public String postAddGet(@ModelAttribute("post") Post post) {
		return "writepage";
	}	

	@PostMapping("/post/add")
	public String annoucementAdd(@ModelAttribute("post") @Valid Post post, Errors errors, RedirectAttributes redirectAttributes) {
		logger.info("게시글을 추가합니다.");
		
		// 에러가 발생하면 작성페이지로 이동
		if (errors.hasErrors()) {
			logger.info("에러 발생으로 돌아갑니다.");
			return "writepage";
		}
		
		// DB에 게시글 등록
		int newPage = service.insertPost(post);
		
		// 새로 추가된 게십물 번호로 이동
		return "redirect:/postview?no=" + newPage;
	}
	
	@RequestMapping(value = "/postview", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam int no) {
		ModelAndView mav = new ModelAndView("post/postview");
		Post post = service.getPostByNo(no);
		mav.addObject("post", post);
		service.increaseViewCount(no);
		return mav;
	}	
}


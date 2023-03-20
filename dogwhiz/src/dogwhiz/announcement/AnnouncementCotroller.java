package dogwhiz.announcement;

import java.util.List;

import javax.validation.Valid;

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

@Controller
public class AnnouncementCotroller {
	@Autowired
	private AnnouncementService service;
	
	@GetMapping("/announcement")
	public String viewAnnoucement(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
		// 한 페이지에 보여줄 게시글 수
		int size = 10;
		
		// 전체 게시글 수 구하기
		int totalCount = service.getAnnouncementCount();
		
		// 페이지 수 계산
		int pageCount = (int) Math.ceil((double) totalCount / size);
		
		// 현재 페이지의 게시르 가져오기
		int start = (page - 1) * size;
		int end = Math.min(start + size, totalCount);
		List<AnnouncementBoard> list = service.getAnnouncementBetweenNoDesc(start, end);
		
		// model에 데이터 추가
		model.addAttribute("annoucement", list);
	    model.addAttribute("totalCount", totalCount);
	    model.addAttribute("pageCount", pageCount);
	    model.addAttribute("currentPage", page);
	    
	    return "/board/announcement";
	}
	
	@GetMapping("/announcement/add")
	public String annoucementAddGet(@ModelAttribute("announcement") AnnouncementBoard board) {
		return "writepage";
	}
	
	@PostMapping("/announcement/add")
	public String annoucementAdd(@ModelAttribute("announcement") @Valid AnnouncementBoard board, Errors errors, RedirectAttributes redirectAttributes) {
		// 에러가 발생하면 작성페이지로 이동
		if (errors.hasErrors()) {
			return "writepage";
		}
		
		// DB에 게시글 등록
		int newPage = service.insertAnnouncement(board);
		
		// 새로 추가된 게십물 번호로 이동
		return "redirect:/announcementview?no=" + newPage;
	}
	
	@RequestMapping(value = "/announcementview", method = RequestMethod.GET)
	public ModelAndView view(@RequestParam int no) {
		ModelAndView mav = new ModelAndView("board/announcementview");
		AnnouncementBoard board = service.getAnnouncementwithNo(no);
		mav.addObject("annoucement", board);
		service.addViewCount(no);
		return mav;
	}
}

package dogwhiz.index;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dogwhiz.announcement.AnnouncementBoard;
import dogwhiz.announcement.AnnouncementService;

@Controller
public class IndexController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private AnnouncementService announcementService;
	
	@RequestMapping(value = { "/", "/index", "index.jsp" }, method = RequestMethod.GET)
	public String index() {
		logger.info("사용자가 INDEX 페이지를 요청하였습니다");
		
		return "/index/index";
	}
	
	@GetMapping("/main")
	public String viewMain(Model model) {
		int end = announcementService.getNewAnnouncementNo();
		
		System.out.println(end);
		
		List<AnnouncementBoard> list = announcementService.getMainAnnouncementDesc();
		System.out.println(list);
		
		// model에 데이터 추가
		model.addAttribute("annoucement", list);
		logger.info("사용자가 MAIN 페이지를 요청하였습니다");
	    return "/index/main";
	}
}

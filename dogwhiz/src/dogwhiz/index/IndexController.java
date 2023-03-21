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

import dogwhiz.model.Post;
import dogwhiz.service.PostService;

@Controller
public class IndexController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private PostService service;
	
	@RequestMapping(value = { "/", "/index", "index.jsp" }, method = RequestMethod.GET)
	public String index() {
		logger.info("사용자가 INDEX 페이지를 요청하였습니다");
		
		return "/index/index";
	}
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String viewMain(Model model) {	
		logger.info("사용자가 MAIN 페이지를 요청하였습니다");
		List<Post> eventList = service.getPostByCategoryWithLimit("이벤트", 6);
		List<Post> announceList = service.getPostByCategoryWithLimit("공지사항", 3);
		List<Post> walkList = service.getBestPost("산책커뮤니티", 10);
		List<Post> communityList = service.getBestPost("커뮤니티", 10);
		
		model.addAttribute("eventList", eventList);
		model.addAttribute("announceList", announceList);
		model.addAttribute("walkList", walkList);
		model.addAttribute("communityList", communityList);
		
	    return "/index/main";
	}
}

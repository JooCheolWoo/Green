package dogwhiz.write;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dogwhiz.announcement.AnnouncementBoard;

@Controller
public class WritepageController {
	@GetMapping("/writepage")
	public String write(Model model) {
	    model.addAttribute("announcement", new AnnouncementBoard());
	    return "/board/writepage";
	}
}

package dogwhiz.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@GetMapping("/login")
	public String viewLogin(Model model) {
		
			
		// model에 데이터 추가
		logger.info("사용자가 Login 페이지를 요청하였습니다");
	    return "/login/login";
	}
}

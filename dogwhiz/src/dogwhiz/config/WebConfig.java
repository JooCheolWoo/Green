package dogwhiz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration // 구성
@EnableWebMvc // Spring MVC에서 사용되는 기본적인 구성을 활성화
@ComponentScan("dogwhiz")
//지정된 패키지에서 @Component 어노테이션이 붙은 클래스를 스캔하여 빈으로 등록하는 역할
//등록된 빈은 @Autowired 등을 사용하여 DI를 받아 사용할 수 있음
public class WebConfig implements WebMvcConfigurer {
	
	
	// JSON 응답을 처리하기 위한 Jackson JSON 라이브러리를 사용하는 MappingJackson2JsonView를 설정하는 코드
	// Java 객체를 JSON 형태로 변환하고, 그 결과를 View로 반환
	@Bean
	public MappingJackson2JsonView jsonView() {
	    return new MappingJackson2JsonView();
	}
	
	
	// 파일 업로드를 처리하기 위한 MultipartResolver 설정
	// CommonsMultipartResolver : Apache Commons FileUpload 라이브러리를 사용하여 HTTP 요청의 body를 파싱하고, 파일 업로드 처리를 수행
	@Bean
	public MultipartResolver multipartResolver() {
		return new CommonsMultipartResolver();
	}
	
	
	// Controller에서 반환한 view 이름을 실제 view로 매핑
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}

	
	// CORS 설정
	@Override
	public void addCorsMappings(CorsRegistry registry) {
	    registry.addMapping("/**") // 모든 URL 패턴을 허용
	        .allowedOrigins("*")
	        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
	        .allowedHeaders("*");
	}
	
	
	// static resource를 처리하는 ResourceHandler 설정
	// static resource = 서버 측에서 변화하지 않는 이미지, CSS, JavaScript 등의 파일
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
	    registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
	    registry.addResourceHandler("/images/**").addResourceLocations("/resources/images/");
	    registry.addResourceHandler("/image/**").addResourceLocations("/resources/images/");
	    registry.addResourceHandler("/upload/**").addResourceLocations("/upload/"); // ckeditor 이미지 업로드
	}
}

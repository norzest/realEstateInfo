package pj210728.pj;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }
    // 로그인 하지 않으면 특정 경로 이동 불가 (/members)
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor())
                .addPathPatterns("/apiTest");
    }

    // 정적 파일들 매핑 (html 파일의 src 나 href)
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/bootstrap/**")
                .addResourceLocations("classpath:/static/bootstrap/");
    }
}

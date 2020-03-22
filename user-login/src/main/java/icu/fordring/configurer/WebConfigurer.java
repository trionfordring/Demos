package icu.fordring.configurer;

import icu.fordring.interceptors.RootCheck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description webMvc的配置类
 * @ClassName WebMvcConfiger
 * @Author fordring
 * @date 2020.03.12 15:19
 */
@Slf4j
@Configuration
public class WebConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("Init Interceptors.");
        registry.addInterceptor(new RootCheck())
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**");
    }
}

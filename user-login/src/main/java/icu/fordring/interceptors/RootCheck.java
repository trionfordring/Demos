package icu.fordring.interceptors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 * @ClassName RootCheck
 * @Author fordring
 * @date 2020.03.12 15:32
 */
@Slf4j
public class RootCheck implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("rootInterceptor receive a request.");
        return true;
    }
}

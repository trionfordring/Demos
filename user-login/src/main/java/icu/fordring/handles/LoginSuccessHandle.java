package icu.fordring.handles;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 登录成功处理器
 * @ClassName LoginSuccessHandle
 * @Author fordring
 * @date 2020.03.13 17:05
 */
@Slf4j
@Component("loginSuccessHandle")
public class LoginSuccessHandle implements AuthenticationSuccessHandler {
    @Resource
    private ObjectMapper objectMapper;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        log.info("{} login success",authentication.getName());
        httpServletResponse.setHeader("Content-type", "text/json;charset=UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        Writer writer = httpServletResponse.getWriter();
        Map<String,Object> map = new HashMap<>();
        map.put("code",200);
        map.put("msg","登录成功");
        map.put("name",authentication.getName());
        writer.write(objectMapper.writeValueAsString(map));
        writer.flush();
        writer.close();
    }
}

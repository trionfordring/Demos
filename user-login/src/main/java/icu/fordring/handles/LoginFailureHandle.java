package icu.fordring.handles;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
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
 * @Description
 * @ClassName LoginFailureHandle
 * @Author fordring
 * @date 2020.03.13 16:51
 */
@Slf4j
@Component("loginFailureHandle")
public class LoginFailureHandle implements AuthenticationFailureHandler {
    @Resource
    private ObjectMapper objectMapper;
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.info("login fail. msg:{}",e.getMessage());
        httpServletResponse.setHeader("Content-type", "text/json;charset=UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        Writer writer = httpServletResponse.getWriter();
        Map<String,Object> map = new HashMap<>();
        map.put("code",401);
        if(e instanceof UsernameNotFoundException||e instanceof BadCredentialsException){
            map.put("msg","用户名或密码错误");
        }else if(e instanceof DisabledException){
            map.put("msg","账户被禁用");
        }else{
            map.put("msg","登录失败！");
        }
        writer.write(objectMapper.writeValueAsString(map));
        writer.flush();
        writer.close();
    }
}

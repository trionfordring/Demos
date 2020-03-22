package icu.fordring.controllers;

import icu.fordring.beans.User;
import icu.fordring.dao.UserDao;
import icu.fordring.factorys.UserFactory;
import icu.fordring.properties.RegisterProperties;
import icu.fordring.service.MailSendService;
import icu.fordring.service.RegisterVerifier;
import icu.fordring.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Description 负责处理未登录的请求
 * @ClassName StrangerController
 * @Author fordring
 * @date 2020.03.16 23:20
 */
@Slf4j
@Controller
@RequestMapping("/stranger")
public class StrangerController {

    @Resource
    private UserFactory userFactory;

    @Resource
    private MailSendService mailSendService;

    @Resource
    private RegisterProperties registerProperties;

    @Resource
    private RegisterVerifier registerVerifier;

    @Resource
    private UserDao userDao;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private PasswordEncoder passwordEncoder;

    @ResponseBody
    @RequestMapping(value = "/verify/{name}/{email}",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public Map verifyEmail(HttpSession httpSession, @PathVariable String name, @PathVariable String email){
        Map<String,Object> map = new HashMap<>();
        if(!registerVerifier.checkNameFormat(name)){
            map.put("code",403);
            map.put("msg","用户名格式错误");
        }else if(!registerVerifier.checkNameNonExist(name)){
            map.put("code",403);
            map.put("msg","用户名已经被占用");
        }else if(!registerVerifier.checkMailFormat(email)){
            map.put("code",403);
            map.put("msg","邮箱格式错误");
        }else if(!registerVerifier.checkMailNonExist(email)){
            map.put("code",403);
            map.put("msg","邮箱已被注册");
        }else{
            String key = StringUtils.getRandomString(registerProperties.getVerificationLength());
            httpSession.setAttribute("mail_verify_"+email,name+"@"+key);
            mailSendService.sendMail(name,email,key);
            map.put("code",200);
            map.put("msg","邮件已发送");
        }
        return map;
    }
    @ResponseBody
    @RequestMapping(value = "/findBack",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public Map findBack(String name,String password, String verification, HttpSession httpSession, HttpServletRequest request){
        log.info("{} register password={},verification={}",name,password,verification);
        Map<String,Object> map = new HashMap<>();
        if(registerVerifier.checkNameNonExist(name)){
            //用户不存在
            map.put("code",403);
            map.put("msg","用户不存在");
        } else if (!registerVerifier.checkPassword(password)) {
            //密码不合法
            map.put("code",403);
            map.put("msg","密码不符合规范");
        }else {
            User user = userDao.findUserByUsername(name);
            if(Objects.equals(httpSession.getAttribute("findBack_"+user.getEmail()),name+"@"+verification)){
                httpSession.removeAttribute("findBack_"+user.getEmail());
                user.setPassword(passwordEncoder.encode(password));
                user=userDao.save(user);
                map.put("code",200);
                map.put("msg","密码修改成功");

                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(name,password);
                try{
                    token.setDetails(new WebAuthenticationDetails(request));
                    Authentication authenticatedUser = authenticationManager.authenticate(token);
                    SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
                    request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
                } catch( AuthenticationException e ){
                    map.put("error","密码修改已经成功，但是自动登陆失败，请手动登录");
                }
            }else {
                map.put("code",403);
                map.put("msg","校验码错误");
            }
        }
        return map;
    }

    /**
     * @Author fordring
     * @Description  用于登陆界面找回密码的邮箱验证部分
     * @Date 2020/3/22 15:46
     * @Param [httpSession, name]
     * @return java.util.Map
     **/
    @ResponseBody
    @RequestMapping(value = "/findBack/verify/{name}",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    public Map verifyEmailFindBack(HttpSession httpSession, @PathVariable String name){
        Map<String,Object> map = new HashMap<>();
        if(registerVerifier.checkNameNonExist(name)){
            map.put("code",403);
            map.put("msg","用户不存在");
        }else{
            User user = userDao.findUserByUsername(name);
            String key = StringUtils.getRandomString(registerProperties.getVerificationLength());
            httpSession.setAttribute("findBack_"+user.getEmail(),name+"@"+key);
            mailSendService.sendMail(name,user.getEmail(),key);
            map.put("code",200);
            map.put("msg","邮件已发送");
        }
        return map;
    }

    /**
     * @Author fordring
     * @Description  接收注册请求
     * @Date 2020/3/19 16:42
     * @Param [name, password, mail, verification]
     * @return Map
     **/
    @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public Map register(String name, String password, String mail, String verification, HttpSession httpSession, HttpServletRequest request){
        log.info("{} register password={},mail={},verification={}",name,password,mail,verification);
        Map<String,Object> map = new HashMap<>();
        if(!registerVerifier.checkName(name)){
            //用户名不合法
            map.put("code",403);
            map.put("msg","用户名不合法");
        } else if (!registerVerifier.checkPassword(password)) {
            //密码不合法
            map.put("code",403);
            map.put("msg","密码不合法");
        } else if (!registerVerifier.checkEmail(mail)) {
            //邮箱不合法
            map.put("code",403);
            map.put("msg","邮箱不合法");
        }else if(Objects.equals(httpSession.getAttribute("mail_verify_"+mail),name+"@"+verification)){
            httpSession.removeAttribute("mail_verify_"+mail);
            User user = userFactory.newDefaultUser(name,password,mail);
            userDao.save(user);
            map.put("code",200);
            map.put("msg","注册成功");

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(name,password);
            try{
                token.setDetails(new WebAuthenticationDetails(request));
                Authentication authenticatedUser = authenticationManager.authenticate(token);
                SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
                request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
            } catch( AuthenticationException e ){
                map.put("error","注册已经成功，但是自动登陆失败");
            }
        }else {
            map.put("code",403);
            map.put("msg","校验未通过");
        }
        return map;
    }
}

package icu.fordring.controllers;

import icu.fordring.beans.User;
import icu.fordring.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.*;

/**
 * @Description 该接口用于提供一些公开的工具接口
 * @ClassName ToolsController
 * @Author fordring
 * @date 2020.03.19 19:41
 */
@Slf4j
@Controller()
@RequestMapping("/tools")
public class ToolsController {
    @Resource
    private UserDao userDao;
    @ResponseBody
    @RequestMapping("/hasLogin")
    public Boolean hasLogin(Authentication authentication){
        if(authentication==null){
            return false;
        }
        return authentication.isAuthenticated();
    }
    @ResponseBody
    @RequestMapping(value = "/get/authorities",produces = "application/json;charset=UTF-8")
    public Set getAuthorities(){
        Set<String> set = new HashSet<>();
        for(GrantedAuthority authority:SecurityContextHolder.getContext().getAuthentication().getAuthorities()){
            set.add(authority.getAuthority());
        }
        return set;
    }

    @ResponseBody
    @RequestMapping(value = "/get/detail",produces = "application/json;charset=UTF-8")
    public Map getDetail(){
        User user = userDao.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Map<String,Object> map = new HashMap<>();
        map.put("name",user.getUsername());
        map.put("roles",user.getRolesList());
        map.put("authorities",getAuthorities());
        map.put("description",user.getDescription());
        map.put("mail",user.getEmail());
        map.put("exp",user.getExp()%100);
        map.put("level",user.getExp()/100);
        map.put("hasLogin",true);
        return map;
    }
}

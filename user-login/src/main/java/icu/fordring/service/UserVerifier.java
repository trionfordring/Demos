package icu.fordring.service;

import icu.fordring.beans.User;
import icu.fordring.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description 校验用户
 * @ClassName UserVerifier
 * @Author fordring
 * @date 2020.03.13 18:02
 */
@Slf4j
@Service
public class UserVerifier implements UserDetailsService {
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("get a login request name={}",s);
        User user = userDao.findUserByUsername(s);
        if(user==null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        return user;
    }

}

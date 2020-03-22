package icu.fordring.factorys;

import icu.fordring.beans.Role;
import icu.fordring.beans.User;
import icu.fordring.dao.RoleDao;
import icu.fordring.dao.UserDao;
import icu.fordring.properties.RegisterProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description 用于创建用户对象
 * @ClassName UserFactory
 * @Author fordring
 * @date 2020.03.19 14:15
 */

@Component
public class UserFactory {
    @Resource
    private RegisterProperties registerProperties;
    @Resource
    private RoleDao roleDao;
    @Resource
    private PasswordEncoder passwordEncoder;
    public User newDefaultUser(String name,String unEncodePassword,String mail){
        User user = new User();
        user.setUsername(name);
        user.setEmail(mail);
        user.setExp(0L);
        user.setPassword(passwordEncoder.encode(unEncodePassword));
        Set<Role> roles = new HashSet<>();
        for(String r:registerProperties.getDefaultRoles()){
            roles.add(roleDao.findRoleByName(r));
        }
        user.getRoles().addAll(roles);
        user.setCreateDate(new Date());
        user.setEnabled(true);
        return user;
    }
}

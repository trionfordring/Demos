package icu.fordring.service;

import icu.fordring.dao.UserDao;
import icu.fordring.properties.RegisterProperties;
import icu.fordring.utils.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Description 检查注册账户的合法性
 * @ClassName RegisterVerifier
 * @Author fordring
 * @date 2020.03.19 16:28
 */
@Component
public class RegisterVerifier {

    @Resource
    private RegisterProperties registerProperties;
    @Resource
    private UserDao userDao;
    /**
     * @Author fordring
     * @Description  验证用户名合法性
     * @Date 2020/3/19 16:41
     * @Param [name]
     * @return boolean
     **/
    public boolean checkName(String name){
        return checkNameFormat(name)&&checkNameNonExist(name);
    }
    /**
     * @Author fordring
     * @Description  检查用户名是否符合格式
     * @Date 2020/3/21 12:14
     * @Param [name]
     * @return boolean
     **/
    public boolean checkNameFormat(String name){
        if(name==null||
                name.length()<registerProperties.getMinNameLength()||
                name.length()>registerProperties.getMaxNameLength()){
            return false;
        }
        return true;
    }
    /**
     * @Author fordring
     * @Description  检查用户名是否被占用
     * @Date 2020/3/21 12:14
     * @Param [name]
     * @return boolean
     **/
    public boolean checkNameNonExist(String name){
        if(name==null)return false;
        return !userDao.existsUserByUsername(name);
    }
    /**
     * @Author fordring
     * @Description  验证密码合法性
     * @Date 2020/3/19 16:41
     * @Param [password]
     * @return boolean
     **/
    public boolean checkPassword(String password){
        if(password==null||
                password.length()<registerProperties.getMinPasswordLength()||
                password.length()>registerProperties.getMaxPasswordLength()
        ) {
            return false;
        }else {
            return true;
        }
    }
    /**
     * @Author fordring
     * @Description  验证邮箱合法性
     * @Date 2020/3/19 16:41
     * @Param [email]
     * @return boolean
     **/
    public boolean checkEmail(String mail){
        return checkMailNonExist(mail)&& checkMailFormat(mail);
    }

    /**
     * @Author fordring
     * @Description  判断邮箱是否不存在
     * @Date 2020/3/21 12:04
     * @Param [mail]
     * @return boolean
     **/
    public boolean checkMailNonExist(String mail){
        if(mail==null){
            return false;
        }
        return !userDao.existsUserByEmail(mail);
    }

    /**
     * @Author fordring
     * @Description  判断邮箱是否符合格式
     * @Date 2020/3/21 12:05
     * @Param [mail]
     * @return boolean
     **/
    public boolean checkMailFormat(String mail){
        return StringUtils.isEmail(mail);
    }
}

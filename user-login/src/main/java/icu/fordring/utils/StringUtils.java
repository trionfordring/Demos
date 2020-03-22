package icu.fordring.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description 字符串的工具类
 * @ClassName StringUtils
 * @Author fordring
 * @date 2020.03.19 15:57
 */
public class StringUtils {
    public static String getRandomString(int length){
        String str="ABCDEFGHJKLMNPQRSTUVWXYZ23456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(32);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
    /**
     * @Author fordring
     * @Description  判断邮箱的合法性
     * @Date 2020/3/19 16:39
     * @Param [email]
     * @return boolean
     **/
    public static boolean isEmail(String email) {
        if (email == null)
            return false;
        String rule = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(rule);
        matcher = pattern.matcher(email);
        if (matcher.matches())
            return true;
        else
            return false;
    }
}

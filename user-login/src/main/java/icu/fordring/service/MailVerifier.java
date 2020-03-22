package icu.fordring.service;


/**
 * @Description 统一验证邮箱的接口
 * @ClassName MailVerifier
 * @Author fordring
 * @date 2020.03.19 16:14
 */
@Deprecated
public interface MailVerifier {

    /**
     * @Author fordring
     * @Description  发送一封校验邮件给指定邮箱
     * @Date 2020/3/19 16:17
     * @Param [name, mail]
     * @return String 验证码
     **/
    String sendMail(String name,String mail);
    /**
     * @Author fordring
     * @Description  完成一个邮箱的验证
     * @Date 2020/3/19 16:18
     * @Param [name, mail]
     * @return boolean
     **/
    boolean VerifyMailbox(String name,String mail,String verification);
}

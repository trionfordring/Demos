package icu.fordring.service;

/**
 * @Description 用于发送验证邮件
 * @ClassName MailSendService
 * @Author fordring
 * @date 2020.03.19 17:17
 */
public interface MailSendService {
    void sendMail(String name,String to,String verification);
}

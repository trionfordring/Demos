package icu.fordring.service.impl;

import icu.fordring.properties.RegisterProperties;
import icu.fordring.service.MailSendService;
import icu.fordring.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description 实现了简单邮件的发送
 * @ClassName SimpleMailSender
 * @Author fordring
 * @date 2020.03.20 14:14
 */
@Slf4j
@Service("mailSenderService")
public class SimpleMailSender implements MailSendService {
    @Value("${spring.mail.username}")
    private String from;

    @Resource
    private MailSender mailSender;

    @Override
    public void sendMail(String name,String to, String verification) {
        log.info("sendMail -name:{},to:{},verification:{}",name,to,verification);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject("来自fordring的验证码");
        message.setText("hi,"+name+"! 你的验证码为："+verification);
        mailSender.send(message);
    }
}

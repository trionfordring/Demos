package icu.fordring.service.impl;

import icu.fordring.service.MailSendService;
import icu.fordring.service.MailVerifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description 统一发送邮件
 * @ClassName MemeryMailVerifier
 * @Author fordring
 * @date 2020.03.19 17:08
 */
@Deprecated
@Service("mailVerifier")
public class MemoryMailVerifier implements MailVerifier {

    @Resource(name = "mailSenderService")
    private MailSendService mailSendService;
    @Override
    public String sendMail(String name, String mail) {

        return "";
    }

    @Override
    public boolean VerifyMailbox(String name, String mail, String verification) {
        return true;
    }
}

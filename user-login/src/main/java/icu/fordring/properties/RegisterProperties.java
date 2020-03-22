package icu.fordring.properties;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @Description 储存StrangerController的配置文件
 * @ClassName StrangerControllerProperties
 * @Author fordring
 * @date 2020.03.19 16:02
 */
@Data
@Slf4j
@ToString
@Component
@ConfigurationProperties(prefix = "application.register")
public class RegisterProperties {
    private int verificationLength;
    private long verificationTimeOut;
    private int minNameLength;
    private int maxNameLength;
    private int minPasswordLength;
    private int maxPasswordLength;
    private String[] defaultRoles;

    @PostConstruct
    private void showProperties(){
        log.info("RegisterProperties init over:\n{}",this);
    }
}

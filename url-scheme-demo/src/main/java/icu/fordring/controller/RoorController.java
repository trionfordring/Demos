package icu.fordring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description
 * @ClassName RoorController
 * @Author fordring
 * @date 2020.04.01 16:33
 */
@Controller
public class RoorController {
    private Logger logger = LoggerFactory.getLogger(RoorController.class);
    @RequestMapping("/*")
    public String indexPage(){
        logger.info("get request");
        return "test";
    }
}

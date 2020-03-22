package icu.fordring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description
 * @ClassName RootController
 * @Author fordring
 * @date 2020.03.12 15:15
 */
@Controller
public class RootController {
    @RequestMapping("/")
    public String indexPage(){
        return "index";
    }


}

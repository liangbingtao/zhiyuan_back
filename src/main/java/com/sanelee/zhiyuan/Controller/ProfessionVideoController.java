package com.sanelee.zhiyuan.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author sanelee
 * @date 2020/7/24
 **/
@Controller
public class ProfessionVideoController {
    @GetMapping("/professionVideo")
    public String ProfessionVideo(){
        return "professionVideo";
    }
}

package com.sanelee.zhiyuan.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ScoreLineController {
    @RequestMapping("/scoreline")
    public String scoreLine(){
        return "scoreline";
    }
}

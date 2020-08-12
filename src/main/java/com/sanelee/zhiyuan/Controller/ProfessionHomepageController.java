package com.sanelee.zhiyuan.Controller;

import com.alibaba.fastjson.JSON;
import com.sanelee.zhiyuan.DTO.PaginationDTO;
import com.sanelee.zhiyuan.Mapper.*;
import com.sanelee.zhiyuan.Model.*;
import com.sanelee.zhiyuan.Service.GaoKaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class ProfessionHomepageController {
    @Autowired
    private ProfessionMapper professionMapper;
    @Autowired
    private ProfessionExtMapper professionExtMapper;
    @Autowired
    private GaoKaoExtMapper gaoKaoExtMapper;
    @Autowired
    private GaoKaoService gaoKaoService;

    //在“查专业”的专业列表页面，点击具体的专业跳转到专业详情页面
    @RequestMapping("/professionHomepage/{proname}")
    @ResponseBody
    public String professionHomepage(Model model,
                                    @RequestParam(name = "page",defaultValue = "1") Integer page,
                                    @RequestParam(name = "size",defaultValue = "5") Integer size,
                                    @PathVariable(name = "proname") String proname,
                                     Map<String,Object> map){
        Profession profession = professionExtMapper.selectByProname(proname);

        PaginationDTO pagination = gaoKaoService.list(page,size,proname);

        map.put("profession",profession);
        map.put("professionSchool",pagination);
        return JSON.toJSONString(map);
//        model.addAttribute("profession",profession);
//        model.addAttribute("professionSchool",pagination);
//        model.addAttribute("proname",proname);
//        model.addAttribute("size",size);
//        return "professionHomepage";
    }
}

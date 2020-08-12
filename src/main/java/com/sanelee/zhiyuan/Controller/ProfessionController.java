package com.sanelee.zhiyuan.Controller;

import com.alibaba.fastjson.JSON;
import com.sanelee.zhiyuan.DTO.PaginationDTO;
import com.sanelee.zhiyuan.Mapper.ProfessionExtMapper;
import com.sanelee.zhiyuan.Model.Profession;
import com.sanelee.zhiyuan.Model.ProfessionExample;
import com.sanelee.zhiyuan.Service.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class ProfessionController {
    @Autowired
    private ProfessionService professionService;
    @Autowired
    private ProfessionExtMapper professionExtMapper;


    /**
     *“找专业”,因为查专业服务中需要根据major获取到对应的subject，但前后端分离过程中，直接传递list集合到前端接收会变成LinkedList
     * 因此在这里单独将根据major查subject抽取出来，成为一个单独的方法，返回list<Profession>到前端调用者
     */
    @RequestMapping("/profession")
    @ResponseBody
    public String Profession(@RequestParam(name = "page",defaultValue = "1") Integer page,
                             @RequestParam(name = "size",defaultValue = "10") Integer size,
                             @RequestParam(name = "major",required = false) String major,
                             @RequestParam(name = "search",required = false) String search,
                             @RequestParam(name = "subject",required = false) String subject,
                             Map<String,Object> map){
        PaginationDTO pagination = professionService.list(page,size,major,search,subject);

        ProfessionExample example1 = new ProfessionExample();
        example1.setDistinct(true);
        example1.setOrderByClause("pid asc");
        List<Profession> majorList = professionExtMapper.selectMajorByExample(example1);
        List<Profession> subjectListByMajor = professionService.subjectList(major);
        map.put("majorinfo",subjectListByMajor.get(0));
        map.put("pagination",pagination);
        map.put("majorList",majorList);
//        map.put("subjectListByMajor",subjectListByMajor);

        return JSON.toJSONString(map);


//        model.addAttribute("pagination",pagination);
//        model.addAttribute("search",search);
//        model.addAttribute("majorList",majorList);
//        model.addAttribute("subjectList",subjectListByMajor);
//        model.addAttribute("major",major);
//        model.addAttribute("subject",subject);

//        return "profession";
    }


    //根据传入的major获取对应的subjcet
    @RequestMapping("/getSubjectByMajor")
    @ResponseBody
    public List<Profession> getSubjectByMajor(@RequestParam(name = "major",required = false) String major){
        List<Profession> subjectListByMajor = professionService.subjectList(major);
        return subjectListByMajor;

    }
}

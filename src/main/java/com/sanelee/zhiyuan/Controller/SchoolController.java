package com.sanelee.zhiyuan.Controller;

import com.alibaba.fastjson.JSON;
import com.sanelee.zhiyuan.DTO.PaginationDTO;
import com.sanelee.zhiyuan.Mapper.SchoolExtMapper;
import com.sanelee.zhiyuan.Mapper.SchoolMapper;
import com.sanelee.zhiyuan.Model.School;
import com.sanelee.zhiyuan.Model.SchoolExample;
import com.sanelee.zhiyuan.Service.SchoolService;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class SchoolController {

    @Autowired
    private SchoolExtMapper schoolExtMapper;

    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private SchoolService schoolService;


    //导航栏“找大学”功能，包含了输入大学名称查找大学功能
    @GetMapping("/school")
    @ResponseBody
    public String school(Model model,
                         Map<String,Object> map,
                         @RequestParam(name = "page",defaultValue = "1") Integer page,
                         @RequestParam(name = "size",defaultValue = "6") Integer size,
                         @RequestParam(name = "areaid",required = false) Integer areaid,
                         @RequestParam(name = "search",required = false) String search,
                         @RequestParam(name = "type",required = false) String type,
                         @RequestParam(name = "is985",required = false) Integer is985,
                         @RequestParam(name = "is211",required = false) Integer is211,
                         @RequestParam(name = "isdoublefirstclass",required = false) Integer isdoublefirstclass){

        PaginationDTO pagination = schoolService.list(page,size,areaid,search,type,is211,is985,isdoublefirstclass);

        SchoolExample example1 = new SchoolExample();
        SchoolExample example2 = new SchoolExample();
        example1.setDistinct(true);
        example1.setOrderByClause("areaid asc");
        example2.setDistinct(true);
        List<School> areaList = schoolExtMapper.selectAreaByExample(example1);
        List<School> typeList = schoolExtMapper.selectTypeByExample(example2);
        map.put("areaList",areaList);
        map.put("typeList",typeList);
        map.put("pagination", pagination);
//        model.addAttribute("pagination",pagination);
//        model.addAttribute("area",areaList);
//        model.addAttribute("typeList",typeList);
//        model.addAttribute("search",search);
//        model.addAttribute("areaid",areaid);
//        model.addAttribute("type",type);
//        model.addAttribute("is211",is211);
//        model.addAttribute("is985",is985);
//        model.addAttribute("isdoublefirstclass",isdoublefirstclass);

        return JSON.toJSONString(map);
    }


}

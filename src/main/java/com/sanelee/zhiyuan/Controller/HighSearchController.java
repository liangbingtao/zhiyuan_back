package com.sanelee.zhiyuan.Controller;

import com.alibaba.fastjson.JSON;
import com.sanelee.zhiyuan.Enums.ResultEnum;
import com.sanelee.zhiyuan.Mapper.GaoKaoExtMapper;
import com.sanelee.zhiyuan.Mapper.ProfessionExtMapper;
import com.sanelee.zhiyuan.Mapper.ProfessionMapper;
import com.sanelee.zhiyuan.Model.GaoKao;
import com.sanelee.zhiyuan.Model.Profession;
import com.sanelee.zhiyuan.Model.Result;
import com.sanelee.zhiyuan.Service.HighSearchService;
import com.sanelee.zhiyuan.Util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
public class HighSearchController {

    @Autowired
    private ProfessionMapper professionMapper;
    @Autowired
    private ProfessionExtMapper professionExtMapper;
    @Autowired
    private GaoKaoExtMapper gaoKaoExtMapper;
    @Autowired
    private HighSearchService highSearchService;

    //高级搜索页面
    @RequestMapping("/highSearch")
    public Result highSearch(Model model) {
        List<Profession> professionList = professionExtMapper.selectProfession();
        if (professionList == null){
            return (ResultUtil.error(ResultEnum.SYS_ERROR));
        }else{
            return ResultUtil.success(professionList);
        }

    }

    /*高级搜索页面选择省份，等级，专业之后显示查询结果*/
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result search(Model model,
                         HttpServletRequest request,
                         Map<String, Object> map,
                         @RequestParam(name = "area", required = false) String area,
                         @RequestParam(name = "profession", required = false) String profession,
                         @RequestParam(name = "type", required = false) Integer type
    ) {

        if (profession.equals("null")) {
            map.put("msg", "专业为必选项");
            List<Profession> professionList = professionExtMapper.selectProfession();
            map.put("professionList", professionList);
            return ResultUtil.success(JSON.toJSONString(map));
//            return JSON.toJSONString(map);

        } else {
            List<GaoKao> schoolSearchList = highSearchService.schoolHighSearch(area, profession, type);
            List<Profession> professionList = professionExtMapper.selectProfession();
            map.put("professionList", professionList);
            map.put("schoolSearchList", schoolSearchList);
            return ResultUtil.success(JSON.toJSONString(map));
//            return JSON.toJSONString(map);

        }
    }
}

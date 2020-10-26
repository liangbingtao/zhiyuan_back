package com.sanelee.zhiyuan.Controller;

import com.alibaba.fastjson.JSON;
import com.sanelee.zhiyuan.Enums.ResultEnum;
import com.sanelee.zhiyuan.Mapper.GaoKaoMapper;
import com.sanelee.zhiyuan.Mapper.SchoolExtMapper;
import com.sanelee.zhiyuan.Mapper.SchoolMapper;
import com.sanelee.zhiyuan.Mapper.SchoolScoreExtMapper;
import com.sanelee.zhiyuan.Model.*;
import com.sanelee.zhiyuan.Util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class SchoolHomepageController {
    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private SchoolExtMapper schoolExtMapper;
    @Autowired
    private SchoolScoreExtMapper schoolScoreExtMapper;
    @Autowired
    private GaoKaoMapper gaoKaoMapper;


    /*
     * 点击找大学进入大学列表页面，这里实现的是在大学列表页面点击具体某个学校，进入学校详情页面，同样的我们把这个服务拆成了两部分
     *
     * */
    @RequestMapping("/schoolHomepage/{scid}")
    @ResponseBody
    public Result schoolHomepage(@PathVariable(name = "scid") Integer scid,
                                 @RequestParam(name = "province", defaultValue = "陕西") String province,
                                 @RequestParam(name = "sort", defaultValue = "1") Integer sort,
                                 Map<String, Object> map) {
        School school = schoolMapper.selectByPrimaryKey(scid);
        List<SchoolScore> schoolScore = schoolScoreExtMapper.selectByScidSort(scid, sort);
        SchoolExample example1 = new SchoolExample();
        example1.setDistinct(true);
        example1.setOrderByClause("areaid asc");
        List<School> areaList = schoolExtMapper.selectAreaByExample(example1);

//        GaoKaoExample example = new GaoKaoExample();
//        example.createCriteria().andScidEqualTo(scid).andLocalProvinceNameEqualTo(province).andLocalTypeNameEqualTo(sort).andYearEqualTo(2018);
//        List<GaoKao> gaoKao = gaoKaoMapper.selectByExample(example);
        map.put("schools", school);
        map.put("schoolscores", schoolScore);
        map.put("areaList", areaList);
//        map.put("gaoKao",gaoKao);


        //研究生招生专业信息






        return ResultUtil.success(JSON.toJSONString(map));
//        return JSON.toJSONString(map);


    }


    @GetMapping("/schoolHomepage2/{scid}")
    @ResponseBody
    public Result getGaokao(@PathVariable(name = "scid") Integer scid,
                            @RequestParam(name = "province", defaultValue = "陕西") String province,
                            @RequestParam(name = "sort", defaultValue = "1") Integer sort) {
        GaoKaoExample example = new GaoKaoExample();
        example.createCriteria().andScidEqualTo(scid).andLocalProvinceNameEqualTo(province).andLocalTypeNameEqualTo(sort).andYearEqualTo(2018);
        List<GaoKao> gaoKao = gaoKaoMapper.selectByExample(example);
        if (gaoKao.size() == 0) {
            return ResultUtil.error(ResultEnum.SCHOOL_NOTFOUND);
        }
        return ResultUtil.success(gaoKao);
    }


}

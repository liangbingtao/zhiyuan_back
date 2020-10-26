package com.sanelee.zhiyuan.Controller;

import com.alibaba.fastjson.JSON;
import com.sanelee.zhiyuan.Enums.ResultEnum;
import com.sanelee.zhiyuan.Mapper.UserMapper;
import com.sanelee.zhiyuan.Model.GaoKao;
import com.sanelee.zhiyuan.Model.Result;
import com.sanelee.zhiyuan.Service.GaoKaoService;
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
public class SimulationController {
    @Autowired
    private GaoKaoService gaoKaoService;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("simulation")
    public String simulation(HttpServletRequest request,Model model){
//        User user = (User)request.getSession().getAttribute("loginUser");
//        model.addAttribute("user",user);
        return "simulation";
    }

    /*填报推荐，获取推荐结果
    * 传入参数：score(分数)，userarea(用户地区)，usersort(用户文理科)*/
    @GetMapping("/simulationResult")
    public Result major(Map<String,Object> map,
                        HttpServletRequest request,
                        @RequestParam(name="score",required = false) Integer score,
                        @RequestParam("userarea") String userarea,
                        @RequestParam("usersort") String usersort) {
        if (score != null) {
//            User user = (User)request.getSession().getAttribute("loginUser");
            List<GaoKao> gaokaoList = gaoKaoService.gaokaoQuery(score, userarea, usersort);
            map.put("gaokao",gaokaoList);
            return ResultUtil.success(JSON.toJSONString(map));
//            return JSON.toJSONString(map);
        }else {
            map.put("msg","分数不能为空!");
            return ResultUtil.error(ResultEnum.SCORE_NOTBLANK);
//            return JSON.toJSONString(map);
        }
    }
}

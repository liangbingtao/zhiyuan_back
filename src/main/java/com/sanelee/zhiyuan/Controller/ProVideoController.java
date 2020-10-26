package com.sanelee.zhiyuan.Controller;

import com.sanelee.zhiyuan.DTO.PaginationDTO;
import com.sanelee.zhiyuan.Mapper.GaoKaoQuestionMapper;
import com.sanelee.zhiyuan.Model.GaoKaoQuestion;
import com.sanelee.zhiyuan.Model.Result;
import com.sanelee.zhiyuan.Service.ProfessionService;
import com.sanelee.zhiyuan.Util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProVideoController {

    @Autowired
    private ProfessionService professionService;

    @Autowired
    private GaoKaoQuestionMapper gaoKaoQuestionMapper;

    @GetMapping("/")
    @ResponseBody
    public Result getVideoList(Model model,
                               @RequestParam(value = "page", defaultValue = "1") Integer page,
                               @RequestParam(value = "size", defaultValue = "9") Integer size) {

        PaginationDTO videos = professionService.listProVideo(page, size);
//        model.addAttribute("videos",videos);

        List<GaoKaoQuestion> questions = gaoKaoQuestionMapper.findLatestQuestions();
//        model.addAttribute("gaokaoQuestions", questions);
        videos.setGaoKaoQuestionList(questions);
//        return "index";
        return ResultUtil.success(videos);
//        return videos;
    }
}

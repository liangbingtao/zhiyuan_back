package com.sanelee.zhiyuan.Controller;

import com.alibaba.fastjson.JSONArray;
import com.sanelee.zhiyuan.Mapper.GaoKaoQuestionMapper;
import com.sanelee.zhiyuan.Model.GaoKaoQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
public class GaoKaoQuestionController {
    @Autowired
    private GaoKaoQuestionMapper gaoKaoQuestionMapper;

    //查询所有高考问题，和所有问题的类型
    @GetMapping("/gaokaoQuestion")
    @ResponseBody
    public String gaokaoQuestion(Model model, Map<String, Object> map) {
        List<GaoKaoQuestion> questionType = gaoKaoQuestionMapper.findQuestionType();
//        model.addAttribute("questionType",questionType);
        List<GaoKaoQuestion> allQuestions = gaoKaoQuestionMapper.findAllQuestions();
        map.put("questionType", questionType);
        map.put("allQuestions", allQuestions);
        return JSONArray.toJSONString(map);
//        model.addAttribute("allquestion",allQuestions);


//        PaginationDTO questionList = gaoKaoQuestionService.listQuestions(page, size);
//        model.addAttribute("questionList",questionList);

//        return "gaokaoQuestion";
    }

    //根据问题id查看问题详细内容
    @GetMapping("/questionDetail/{id}")
    public GaoKaoQuestion questionDetail(@PathVariable("id") Integer id, Model model) {
        GaoKaoQuestion questionById = gaoKaoQuestionMapper.findQuestionById(id);
//        model.addAttribute("questionById", questionById);
        return questionById;
    }
}

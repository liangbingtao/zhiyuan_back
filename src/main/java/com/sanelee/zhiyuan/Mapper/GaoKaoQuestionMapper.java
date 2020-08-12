package com.sanelee.zhiyuan.Mapper;


import com.sanelee.zhiyuan.Model.GaoKaoQuestion;

import java.util.List;

public interface GaoKaoQuestionMapper {

    List<GaoKaoQuestion> findLatestQuestions();

    GaoKaoQuestion findQuestionById(Integer id);

    List<GaoKaoQuestion> findQuestionType();

    List<GaoKaoQuestion> findAllQuestions();

//    Integer countQuestions();

//    List<GaoKaoQuestion> findQuestions(Integer offset, Integer size);
}

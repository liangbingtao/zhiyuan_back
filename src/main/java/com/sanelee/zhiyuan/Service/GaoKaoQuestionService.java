package com.sanelee.zhiyuan.Service;//package com.sanelee.zhiyuan.Service;
//
//import com.sanelee.zhiyuan.DTO.PaginationDTO;
//import com.sanelee.zhiyuan.Mapper.GaoKaoQuestionMapper;
//import com.sanelee.zhiyuan.Model.GaoKaoQuestion;
//import com.sanelee.zhiyuan.Model.ProVideo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class GaoKaoQuestionService {
//    @Autowired
//    private GaoKaoQuestionMapper gaoKaoQuestionMapper;
//
//    public PaginationDTO listQuestions(Integer page, Integer size) {
//        PaginationDTO paginationDTO =new PaginationDTO();
//        Integer totalPage;
//        Integer totalCount =gaoKaoQuestionMapper.countQuestions();  //计算问题总数
//
//        /*计算总页数*/
//        if (totalCount % size == 0) {
//            totalPage = totalCount / size;
//        } else {
//            totalPage = totalCount / size + 1;
//        }
//
//        /* 容错处理*/
//        if (page < 1) {
//            page = 1;
//        }
//        if (page > totalPage) {
//            page = totalPage;
//        }
//        if (page == 0) {
//            page = 1;
//        }
//
//        paginationDTO.setPagination(totalPage,page); //setPagination方法用来计算页面展示逻辑
//        Integer offset = size *(page-1);
//        List<GaoKaoQuestion> questions = gaoKaoQuestionMapper.findQuestions(offset, size);
//        paginationDTO.setGaoKaoQuestionList(questions);
//        return paginationDTO;
//    }
//}

package com.sanelee.zhiyuan.Mapper;

import com.sanelee.zhiyuan.DTO.SchoolQueryDTO;
import com.sanelee.zhiyuan.Model.School;
import com.sanelee.zhiyuan.Model.SchoolExample;

import java.util.List;

public interface SchoolExtMapper {

    List<School> selectAreaByExample(SchoolExample example);
    List<School> selectTypeByExample(SchoolExample example);
    Integer countBySomething(SchoolQueryDTO schoolQueryDTO);
    List<School> selectBySomething(SchoolQueryDTO schoolQueryDTO);

}
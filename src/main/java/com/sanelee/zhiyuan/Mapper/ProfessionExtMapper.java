package com.sanelee.zhiyuan.Mapper;

import com.sanelee.zhiyuan.DTO.ProfessionQueryDTO;
import com.sanelee.zhiyuan.Model.Profession;
import com.sanelee.zhiyuan.Model.ProfessionExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;


public interface ProfessionExtMapper {

    Integer countBySomething(ProfessionQueryDTO professionQueryDTO);

    List<Profession> selectBySomething(ProfessionQueryDTO professionQueryDTO);

    List<Profession> selectMajorByExample(ProfessionExample example1);

    List<Profession> selectSubjectByExample(ProfessionExample example2);

    List<Profession> selectSubjectByMajor(Profession example3);

    Profession selectByProname(String proname);

    List<Profession> selectProfession();

}
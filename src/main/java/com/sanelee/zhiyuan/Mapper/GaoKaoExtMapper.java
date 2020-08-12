package com.sanelee.zhiyuan.Mapper;

import com.sanelee.zhiyuan.DTO.GaoKaoDTO;
import com.sanelee.zhiyuan.Model.GaoKao;
import com.sanelee.zhiyuan.Model.GaoKaoExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface GaoKaoExtMapper {

    Integer countSelectSchoolByProname(GaoKaoDTO gaoKaoDTO);

    List<GaoKao> selectByProname(GaoKaoDTO gaoKaoDTO);

    List<GaoKao> searchByScore_Province_Object_Direction(GaoKaoDTO gaoKaoDTO);



    List<GaoKao> selectChongSchoolProfession(int score, Integer sort, String school, String area);

    List<GaoKao> selectWenSchoolProfession(int score, Integer sort, String school, String area);

    List<GaoKao> selectBaoSchoolProfession(int score, Integer sort, String school, String area);

    List<GaoKao> selectChongBySelect(int score, String area, Integer sort, String province1, String province2, String province3, String type1, String type2, String type3, String type4, String type5, String type6);

    List<GaoKao> selectWenBySelect(int score, String area, Integer sort, String province1, String province2, String province3, String type1, String type2, String type3, String type4, String type5, String type6);

    List<GaoKao> selectBaoBySelect(int score, String area, Integer sort, String province1, String province2, String province3, String type1, String type2, String type3, String type4, String type5, String type6);

    List<GaoKao> schoolHighSearch(String area, String profession, Integer is211, Integer is985, Integer isDoubleFirstClass, String subject, String major, String localBatchName);

}
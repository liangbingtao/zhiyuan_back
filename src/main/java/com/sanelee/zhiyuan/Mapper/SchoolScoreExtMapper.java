package com.sanelee.zhiyuan.Mapper;

import com.sanelee.zhiyuan.Model.SchoolScore;
import com.sanelee.zhiyuan.Model.SchoolScoreExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface SchoolScoreExtMapper {

    List<SchoolScore> selectByScidSort(Integer scid, Integer sort);
}
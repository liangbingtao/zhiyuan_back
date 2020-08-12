package com.sanelee.zhiyuan.Mapper;

import com.sanelee.zhiyuan.Model.ProVideo;

import java.util.List;

public interface ProVideoMapper {

    Integer countVideo();

    List<ProVideo> findVideos(Integer offset, Integer size);
}

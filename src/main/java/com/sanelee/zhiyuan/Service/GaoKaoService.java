package com.sanelee.zhiyuan.Service;

import com.sanelee.zhiyuan.DTO.GaoKaoDTO;
import com.sanelee.zhiyuan.DTO.PaginationDTO;
import com.sanelee.zhiyuan.Mapper.GaoKaoExtMapper;
import com.sanelee.zhiyuan.Mapper.GaoKaoMapper;
import com.sanelee.zhiyuan.Mapper.ProfessionExtMapper;
import com.sanelee.zhiyuan.Model.GaoKao;
import com.sanelee.zhiyuan.Model.Profession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GaoKaoService {
    @Autowired
    private GaoKaoExtMapper gaoKaoExtMapper;
    @Autowired
    private ProfessionExtMapper professionExtMapper;


    public PaginationDTO list(Integer page, Integer size, String proname) {
        Profession profession = professionExtMapper.selectByProname(proname);
        String subject = profession.getSubject();
        String major = profession.getMajor();
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;

        GaoKaoDTO gaoKaoDTO = new GaoKaoDTO();

        gaoKaoDTO.setSpname(proname);
        gaoKaoDTO.setSubject(subject);
        gaoKaoDTO.setMajor(major);
        Integer totalCount = gaoKaoExtMapper.countSelectSchoolByProname(gaoKaoDTO);

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }
        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        if (totalPage == 0){
            page = 1;
        }
        paginationDTO.setPagination(totalPage, page);

        Integer offset = size * (page -1);


        gaoKaoDTO.setSize(size);
        gaoKaoDTO.setPage(offset);
        List<GaoKao> gaoKaoList = gaoKaoExtMapper.selectByProname(gaoKaoDTO);
        paginationDTO.setGaoKaos(gaoKaoList);
        return paginationDTO;
    }

    public List<GaoKao> gaokaoQuery(Integer score, String province, String object) {
        Integer sort = null;
        switch (object){
            case "理科":
                sort=1;
                break;
            case "文科":
                sort=2;
                break;
        }
        GaoKaoDTO gaoKaoDTO = new GaoKaoDTO();
        gaoKaoDTO.setProvince(province);
        gaoKaoDTO.setSort(sort);
        gaoKaoDTO.setScore(score);
        List<GaoKao> gaokaoList = gaoKaoExtMapper.searchByScore_Province_Object_Direction(gaoKaoDTO);

        return gaokaoList;
    }
}

package com.sanelee.zhiyuan.Service;


import com.sanelee.zhiyuan.DTO.PaginationDTO;
import com.sanelee.zhiyuan.DTO.SchoolQueryDTO;
import com.sanelee.zhiyuan.Mapper.SchoolExtMapper;
import com.sanelee.zhiyuan.Mapper.SchoolMapper;
import com.sanelee.zhiyuan.Mapper.SchoolScoreMapper;
import com.sanelee.zhiyuan.Model.School;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    @Autowired
    private SchoolExtMapper schoolExtMapper;

    @Autowired
    private SchoolMapper schoolMapper;

    @Autowired
    private SchoolScoreMapper schoolScoreMapper;

    public PaginationDTO list(Integer page, Integer size,Integer areaid,String search,String type,Integer is211,Integer is985,Integer isdoublefirstclass){
        if (StringUtils.isNotBlank(search)) {
            String[] tags = StringUtils.split(search, " ");
            search = Arrays.stream(tags).collect(Collectors.joining("|"));
        }
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        SchoolQueryDTO schoolQueryDTO = new SchoolQueryDTO();
        schoolQueryDTO.setSearch(search);
        schoolQueryDTO.setAreaid(areaid);
        schoolQueryDTO.setType(type);
        schoolQueryDTO.setIs211(is211);
        schoolQueryDTO.setIs985(is985);
        schoolQueryDTO.setIsdoublefirstclass(isdoublefirstclass);

        Integer totalCount= schoolExtMapper.countBySomething(schoolQueryDTO);

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
//        SchoolExample example = new SchoolExample();
//        example.setOrderByClause("rank asc");
        schoolQueryDTO.setSize(size);
        schoolQueryDTO.setPage(offset);
        List<School> schoolList = schoolExtMapper.selectBySomething(schoolQueryDTO);
        paginationDTO.setSchools(schoolList);

        return paginationDTO;
    }


}

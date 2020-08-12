package com.sanelee.zhiyuan.DTO;

import lombok.Data;

@Data
public class SchoolQueryDTO {
    private String search;
    private Integer page;
    private Integer size;
    private Integer areaid;
    private String rank;
    private String type;
    private Integer is985;
    private Integer is211;
    private Integer isdoublefirstclass;
}

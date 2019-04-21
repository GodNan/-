package com.css.bdpfnew.model.dto;

import lombok.Data;

/**
 * @Author lvmn
 * @Date 2019/3/22 15:12
 * @Description
 *  残疾人环节查询参数类
 */
@Data
public class DtoDisabledLink {

    private String cityid;
    private Integer pageNum;
    private Integer pageRow;

    private String citizenId;
    private String name;
    private String link;
}

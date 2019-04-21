package com.css.bdpfnew.model.vo.query;

import lombok.Data;

import java.util.Date;

@Data
public class VoCredentials {

    private Boolean isInclude;
    private String cityid;
    private Integer pageNum;
    private Integer pageRow;

    private String citizenId;
    private String  name;
    private Integer idtKind;
    private Integer idtLevel;
    private Integer gender;
    private Integer eduLevel;
    private Integer race;
    private Integer marriager;
    private Integer newType;
    private Integer hukouKind;
    private Integer political;
    private Integer dataFrom;
    private Integer idtType;
    private Date firstCertDateStart;
    private Date firstCertDateEnd;
}

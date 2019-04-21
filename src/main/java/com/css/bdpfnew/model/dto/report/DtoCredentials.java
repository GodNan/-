package com.css.bdpfnew.model.dto.report;

import lombok.Data;

import java.util.Date;

@Data
public class DtoCredentials {

    private String citizenId;
    private String cityIdP;
    private Integer idtKind;
    private Integer idtLevel;
    private Integer gender;
    private Integer hukouKind;
    private String[] certDate;
    private Integer reportType;
}

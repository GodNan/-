package com.css.bdpfnew.model.entity.bdpfnew;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author lvmn
 * @Date 2018/11/19 16:54
 * @Description
 */
@Entity
@Table(name = "web_hospital_book", schema = "bdpfnew2018")
@Data
public class WebHospitalBook extends BaseEntity{
    @Column(name = "number_people")
    private Integer number;
    private Integer dateType;
    private String[] dates;
    private Integer idtType;
    private String webHospitalUuid;
    // 收费标准
    @Column(nullable = true, columnDefinition = "number(10)")
    private Integer idtFee;
    // 是否需要挂号 (1：是 ,0：否)
    @Column(nullable = true, columnDefinition = "number(2)")
    private Integer regNeeded;
    // 评定时间描述
    @Column(nullable = true, columnDefinition = "varchar2(1000)")
    private String idtTimeDesc;
    // 需要申请人提供的资料及资质要求
    @Column(nullable = true, columnDefinition = "varchar2(2000)")
    private String idtDesc;

    public WebHospitalBook(Integer dateType, String[] dates,
                           Integer idtType,Integer number,
                           String webHospitalUuid,Integer idtFee,
                           Integer regNeeded,String idtTimeDesc,
                           String idtDesc) {
        this.number = number;
        this.dateType = dateType;
        this.dates = dates;
        this.idtType = idtType;
        this.webHospitalUuid = webHospitalUuid;
        this.regNeeded = regNeeded;
        this.idtTimeDesc = idtTimeDesc;
        this.idtDesc = idtDesc;
        this.idtFee = idtFee;
    }

    public WebHospitalBook() {
    }
}

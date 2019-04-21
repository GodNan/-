package com.css.bdpfnew.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author lvmn
 * @Date 2019/2/26 15:45
 * @Description
 *  医院预约残疾人参数类
 */
@ApiModel(value = "医院预约残疾人参数", description = "医院预约残疾人参数")
@Data
public class DtoCitizenKindHospital {
    private Integer pageNum;
    private Integer pageRow;
    @ApiModelProperty(value = "医院id-必填",required = true)
    private String hospitalId;
    @ApiModelProperty(value = "录入类型；0.残联录入，1.医院录入")
    private String inputType;
    @ApiModelProperty(value = "残疾人姓名")
    private String name;
    @ApiModelProperty(value = "残疾人身份证号")
    private String citizenId;
    @ApiModelProperty(value = "评残类别")
    private String idtKind;
    @ApiModelProperty(value = "评定情况")
    private Integer finishedState;
    @ApiModelProperty(value = "预约时间段开始时间")
    private Date bookDateStart;
    @ApiModelProperty(value = "预约时间段结束时间")
    private Date bookDateEnd;
}

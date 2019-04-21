package com.css.bdpfnew.model.entity.bdpfnet;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Author lvmn
 * @Date 2018/11/19 16:54
 * @Description
 */
@Entity
@Table(name = "HOSPITAL_BOOKING_INFO",schema = "bdpfnet")
//@Table(name = "HOSPITAL_BOOKING_INFO",schema = "bdpfnet2018")
@Data
public class NetHospitalBook {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(length = 32)
    private String uuid;
    // 医院id
    private String hospitalId;
    private String hospitalName;
    private String hospitalAdd;
    private String hospitalTel;
    // 每日评定名额
    private Integer idtNum;
    // 收费标准
    private Integer idtFee;
    // 是否需要挂号
    private Integer regNeeded;
    // 日期类型
    private Integer dateType;
    // 周时间
    private String weekdays;
    // 月时间
    private String monthDays;
    // 残疾类别
    private Integer idtkind;

    @Column(nullable = true, columnDefinition = "varchar2(1000)")
    private String idtTimeDesc;
    // 需要申请人提供的资料及资质要求
    @Column(nullable = true, columnDefinition = "varchar2(2000)")
    private String idtDesc;

}

package com.css.bdpfnew.model.entity.bdpfnet;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author lvmn
 * @Date 2019/4/18 14:41
 * @Description
 *
 */
@Entity
@Table(name = "CITIZEN_KIND_HOSPITAL",schema = "bdpfnet")
//@Table(name = "CITIZEN_KIND_HOSPITAL",schema = "bdpfnet2018")
@Data
public class NetCitizenKindHospital {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "assigned")
    @GeneratedValue(generator = "idGenerator")
    @Column(length = 32)
    private String uuid;
    private String citizenId;

    private String hospital;

    private String idtRecordUuid;
 
    private Integer  idtkind;

    @Temporal(TemporalType.TIMESTAMP)
    private Date idttime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date bookingtime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date edittime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createtime;

    // private Integer bookingnum;
    // 1 : 通过;0 : 不通过
    private Integer passed;
    // 有效标志：
    // 1 : 有效 ; 0 : 无效
    private Integer flag;

    // 使用评残医院标志
    // 1: 是,0:否
    private Integer isWorking;
}

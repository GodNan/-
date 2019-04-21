package com.css.bdpfnew.model.entity.bdpfnet;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author lvmn
 * @Date 2019/3/5 15:19
 * @Description
 *  医院预约数量信息
 */
@Entity
@Table(name = "BDPF_NET_HOSPITAL",schema = "bdpfnet")
//@Table(name = "BDPF_NET_HOSPITAL",schema = "bdpfnet2018")
@Data
public class NetHospitalBookNum {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(length = 32)
    private String uuid;

    private Integer bookNum;

    @Temporal(TemporalType.TIMESTAMP)
    private Date bookingTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date editTime;

    private String hospitalId;

    private Integer idtKind;

    private String idtRecordUuid;
}

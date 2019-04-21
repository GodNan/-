package com.css.bdpfnew.model.entity.bdpfnew;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Author lvmn
 * @Date 2019/1/16 14:46
 * @Description
 */
@Entity
@Table(name = "DANGAN_DATA", schema = "bdpfnew2018")
@Data
public class CdpfDangAn {
    /** UUID */
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(length = 32)
    private String uuid;
    private String citizenId;
    private String workKind;
}

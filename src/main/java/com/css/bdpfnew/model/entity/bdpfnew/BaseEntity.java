package com.css.bdpfnew.model.entity.bdpfnew;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Entity-基类
 *
 * @author GodNan
 * @version 1.0
 */
@Data
@Accessors(fluent  = false)
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    /** UUID */
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(length = 32)
    private String uuid;

    /** 创建日期 */
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true, updatable = false)
    private Date createDate;

    /** 修改日期 */
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date modifyDate;


    /**
     * 获取创建日期
     *
     * @return 创建日期
     */
    @Transient
    @JsonIgnore
    public String getCreateDateFormat() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(createDate);
        return dateString;
    }

    /**
     * 获取修改日期
     *
     * @return 修改日期
     */
    @Transient
    @JsonIgnore
    public String getModifyDateFormat() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(modifyDate);
        return dateString;
    }

}

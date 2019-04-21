package com.css.bdpfnew.model.entity.bdpfnew;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Auther: GodNan
 * @Date: 2018/5/25 11:15
 * @Version: 1.0
 * @Description:
 */
@Entity
@Where(clause = "valid_sign = '1'")
@Table(name = "CODE_AREA", schema = "BDPFNEW2018", catalog = "")
@Accessors(chain = true)
@Data
@EqualsAndHashCode(callSuper = false)
public class CodeAreaEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false, columnDefinition = "varchar2(12)")
    private String areaCode;
	@Column(nullable = false, columnDefinition = "varchar2(50)")
    private String areaName;
	@Column(nullable = false, columnDefinition = "number(1)")
    private Integer areaLevel;
    @Column(nullable = true, columnDefinition = "varchar2(12)")
    private String parentAreaCode;
    @Column(nullable = true, columnDefinition = "varchar2(5)")
    private String areaAttribute;
    @Column(nullable = true, columnDefinition = "varchar2(50)")
    private String memo;
    @Column(nullable = true, columnDefinition = "varchar2(1)")
    private String validSign;
}

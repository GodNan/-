/**
 * 
 */
package com.css.bdpfnew.model.entity.bdpfnew;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

/**
 * @Author erDuo
 * @Date 2018年12月22日 下午8:51:54
 * @Description
 */
@Entity
@Table(name = "DATA_GONGAN", schema = "bdpfnew2018")
@Data
public class GonganData {
	@Id
	@Column(length = 32)
	private String uuid;
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String citizenId;
	@Column(nullable = false, columnDefinition = "varchar2(100)")
	private String name;
	@Column(nullable = false, columnDefinition = "varchar2(50)")
	private String csrq;
	@Column(nullable = false, columnDefinition = "varchar2(50)")
	private String mz;
	@Column(nullable = false, columnDefinition = "varchar2(500)")
	private String hjdz;
	@Temporal(TemporalType.DATE)
	private Date insertDate;
}

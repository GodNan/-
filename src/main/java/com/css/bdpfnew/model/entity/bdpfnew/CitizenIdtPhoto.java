/**
 * 
 */
package com.css.bdpfnew.model.entity.bdpfnew;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author erDuo
 * @Date 2018年10月29日 上午10:32:07
 * @Description
 */

@Entity
@Where(clause = "del_flag = 0")
@Table(name = "CITIZEN_IDT_PHOTOS", schema = "bdpfnew2018")
@Data
@EqualsAndHashCode(callSuper = false)
public class CitizenIdtPhoto extends BaseEntity {

	private static final long serialVersionUID = -5391673364277412020L;
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String citizenId; // 残疾人主键
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String idtRecordUuid;
	@Column(nullable = false, columnDefinition = "number(1) default 1", insertable = false)
	private Integer flag;
	@Column(nullable = false, columnDefinition = "number(1)")
	private Integer idtkind;

	private Blob picture;

	@Column(nullable = false, columnDefinition = "number(4) default 0", insertable = false)
	private Integer delFlag;

}

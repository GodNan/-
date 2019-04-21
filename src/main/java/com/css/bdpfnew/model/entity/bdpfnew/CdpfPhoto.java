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
 * @Date 2018年9月20日 上午11:12:06
 * @Description
 */

@Entity
@Where(clause = "del_flag = 0")
@Table(name = "CDPF_PHOTOS", schema = "bdpfnew")
@Data
@EqualsAndHashCode(callSuper = false)
public class CdpfPhoto extends BaseEntity {
	private static final long serialVersionUID = -7750481239934768356L;
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String cdpfid;
	@Column(nullable = false, columnDefinition = "varchar2(20)")
	private String citizenId;
	@Column(nullable = true, columnDefinition = "number(4)")
	private Integer updateFlag;
	@Column(nullable = false, columnDefinition = "number(4) default 0")
	private Integer hlSynced;
	@Column(nullable = false, columnDefinition = "number(4) default 0")
	private Integer isHulian;

	private Blob data;
	private Blob photoTemp;
	private Blob imageIdt;
	private Blob strangeWord;
	private Blob innerData;

	@Column(nullable = false, columnDefinition = "number(4) default 0", insertable = false)
	private Integer delFlag;
}

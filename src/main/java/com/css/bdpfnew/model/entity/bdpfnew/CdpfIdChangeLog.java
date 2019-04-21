package com.css.bdpfnew.model.entity.bdpfnew;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "CDPF_IDCHANGE_LOG", schema = "bdpfnew2018")
@Data
@EqualsAndHashCode(callSuper = false)
public class CdpfIdChangeLog extends BaseEntityUuidAssigned {
	
	private static final long serialVersionUID = 6570821017979119120L;
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String cdpfId;
	@Column(nullable = false, columnDefinition = "varchar2(50)")
	private String oldcitizenid;
	@Column(nullable = false, columnDefinition = "varchar2(50)")
	private String newcitizenid;
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String operid;
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String opername;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date edittime;

}
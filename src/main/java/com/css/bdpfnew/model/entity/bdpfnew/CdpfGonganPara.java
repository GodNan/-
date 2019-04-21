package com.css.bdpfnew.model.entity.bdpfnew;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "cdpf_gongan_para", schema = "bdpfnew2018")
@Data
@EqualsAndHashCode(callSuper = false)
public class CdpfGonganPara extends BaseEntityUuidAssigned {

	private static final long serialVersionUID = 1L;
	
	@Column(nullable = false, columnDefinition = "varchar2(200)")
	private String sessionId;
	@Column(nullable = false, columnDefinition = "varchar2(30)")
	private String timeStamp;
	
}
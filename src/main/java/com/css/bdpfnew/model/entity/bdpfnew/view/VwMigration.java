package com.css.bdpfnew.model.entity.bdpfnew.view;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "vw_migration", schema = "bdpfnew2018")
@Data
public class VwMigration implements Serializable {
	private static final long serialVersionUID = 2747361228645836057L;
	@Id
	private String uuid;
	private String oldCode;
	private String oldName;
	private String newCode;
	private String newName;
	private String disableId;
	private String disableName;
	private String currentState;
	private Integer formerState;
	private Integer processId;
	private Integer status;
	private String statusRemark;
	private String requestId;
	private Date modifyDate;
}

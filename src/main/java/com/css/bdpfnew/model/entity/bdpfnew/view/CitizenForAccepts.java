package com.css.bdpfnew.model.entity.bdpfnew.view;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @Author HEYCH
 * @Date 2018年11月13日 上午10:41:19
 * @Description:
 */
@Entity
@Table(name = "citizen_for_accepts", schema = "bdpfnew2018")
@Data
public class CitizenForAccepts implements Serializable {
	private static final long serialVersionUID = 2747361228645836057L;
	@Id
	private String uuid;
	private String citizenId;
	private String name;
	private String cityid;
	private String jiguanCode;
	private String nextArea;
	private String nextName;
	private Date preTime;
	private Date modifyDate;

}

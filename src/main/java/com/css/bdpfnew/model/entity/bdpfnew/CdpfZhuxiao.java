package com.css.bdpfnew.model.entity.bdpfnew;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Where;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author HEYCH
 * @Date 2018年11月13日 上午10:41:19
 * @Description:
 */
@Entity
@Where(clause = "del_flag = 0")
@Table(name = "CDPF_ZHUXIAO", schema = "bdpfnew2018")
@Data
@EqualsAndHashCode(callSuper = false)
public class CdpfZhuxiao extends BaseEntityUuidAssigned {

	private static final long serialVersionUID = -5099271415967244073L;

	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String citizenId;
	@Column(nullable = false, columnDefinition = "varchar2(100)")
	private String name;
	@Column(nullable = true, columnDefinition = "number(3)")
	private Integer status; // 888:临时, 666:中间状态, 0:注销, 1:不注销
	@Column(nullable = true, columnDefinition = "number(1)")
	private Integer zxSource;
	@Temporal(TemporalType.DATE)
	private Date deathDate;
	@Temporal(TemporalType.DATE)
	private Date filloutDate;
	@Column(nullable = false, columnDefinition = "number(4) default 0", insertable = false)
	private Integer delFlag;

}
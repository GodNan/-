package com.css.bdpfnew.model.entity.bdpfnew;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author erDuo
 * @Date 2018年6月14日 上午10:40:33
 * @Description: 任务数据表
 */
@Entity
@Where(clause = "del_flag = 0")
@Table(name = "REQUEST_DATA", schema = "bdpfnew2018")
@Data
@EqualsAndHashCode(callSuper = false)
public class RequestData extends BaseEntity {

	private static final long serialVersionUID = -7070601830762819308L;
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String requestId;// 请求id
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer nameCode;// 数据表code
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String nameValue;// 数据主键
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer isChief;// 是否为主表
	@Column(nullable = false, columnDefinition = "number(4) default 0", insertable = false)
	private Integer delFlag;
}

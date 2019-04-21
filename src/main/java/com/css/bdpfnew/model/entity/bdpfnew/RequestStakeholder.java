package com.css.bdpfnew.model.entity.bdpfnew;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author erDuo
 * @Date 2018年6月14日 上午10:46:08
 * @Description: 任务操作人员表
 */
@Entity
@Where(clause = "del_flag = 0")
@Table(name = "REQUEST_STAKEHOLDER", schema = "bdpfnew2018")
@Data
@EqualsAndHashCode(callSuper = false)
public class RequestStakeholder extends BaseEntity {

	private static final long serialVersionUID = 1331889579261756559L;
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String requestId;// 请求id
	@Column(nullable = true, columnDefinition = "varchar2(32)")
	private String userId;// 不一定用得上
	@Column(nullable = false, columnDefinition = "varchar2(12)")
	private String cityid;// 待处理人的区划
	@Column(nullable = false, columnDefinition = "varchar2(100)")
	private String permissionId;// 权限
	@Column(nullable = false, columnDefinition = "number(1) default 0", insertable = false)
	private Integer hlSynced; //互联网同步标志
	@Column(nullable = false, columnDefinition = "number(4) default 0", insertable = false)
	private Integer delFlag;
}

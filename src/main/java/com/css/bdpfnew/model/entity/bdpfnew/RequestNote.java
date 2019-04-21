package com.css.bdpfnew.model.entity.bdpfnew;

import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;
import org.springframework.util.StringUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author erDuo
 * @Date 2018年6月14日 上午10:51:39
 * @Description: 任务日志表
 */
@Entity
@Where(clause = "del_flag = 0")
@Table(name = "REQUEST_NOTE", schema = "bdpfnew2018")
@Data
@EqualsAndHashCode(callSuper = false)
public class RequestNote extends BaseEntity {

	private static final long serialVersionUID = 8454933235139934274L;
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String requestId;// 请求id
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String userId;// 操作用户id
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String userName;// 操作用户姓名
	@Column(nullable = true, columnDefinition = "varchar2(500)")
	private String note;// 操作记录
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer handleState; // 操作前状态
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer finishState; // 操作后状态
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer handleType; // 操作类型
	@Column(nullable = false, columnDefinition = "number(4) default 1", insertable = false)
	private Integer latest;// 最新操作日志
	@Column(nullable = false, columnDefinition = "number(1) default 0", insertable = false)
	private Integer hlSynced; // 互联网同步标志
	@Column(nullable = false, columnDefinition = "number(4) default 0", insertable = false)
	private Integer delFlag;

	@Transient
	public String getHandleDate() {
		if (!StringUtils.isEmpty(this.getCreateDate())) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = formatter.format(super.getCreateDate());
			return dateString;
		} else {
			return "";
		}
	}

}

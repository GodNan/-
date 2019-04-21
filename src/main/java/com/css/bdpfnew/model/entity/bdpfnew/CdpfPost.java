/**
 * 
 */
package com.css.bdpfnew.model.entity.bdpfnew;

import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author erDuo
 * @Date 2018年11月5日 上午10:42:29
 * @Description
 */

@Entity
@Where(clause = "del_flag = 0")
@Table(name = "CDPF_POST", schema = "bdpfnew2018")
@Data
@EqualsAndHashCode(callSuper = false)
public class CdpfPost extends BaseEntity {

	private static final long serialVersionUID = 8454933235139934274L;
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String cdpfId;// 残疾人id
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String requestId;// 请求id
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String postUserId;// 经办人id
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String postUserName;// 经办人姓名
	@Column(nullable = true, columnDefinition = "varchar2(32)")
	private String postManagerId;// 理事长id
	@Column(nullable = true, columnDefinition = "varchar2(32)")
	private String postManagerName;// 理事长姓名
	@Column(nullable = true, columnDefinition = "varchar2(500)")
	private String note;// 公示问题
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer postType; // 公示类型
	@Column(nullable = false, columnDefinition = "number(4) default 0", insertable = false)
	private Integer delFlag;

	@Transient
	public String getHandleDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(super.getCreateDate());
		return dateString;
	}

}
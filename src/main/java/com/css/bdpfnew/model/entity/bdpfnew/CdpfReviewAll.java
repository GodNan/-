/**
 * 
 */
package com.css.bdpfnew.model.entity.bdpfnew;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author erDuo
 * @Date 2019年1月6日 下午5:39:22
 * @Description
 */

@Entity
@Where(clause = "del_flag = 0")
@Table(name = "CDPF_REVIEW_ALL", schema = "bdpfnew2018")
@Data
@EqualsAndHashCode(callSuper = false)
public class CdpfReviewAll extends BaseEntity {

	private static final long serialVersionUID = -4548922921761940815L;
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String cdpfId;
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String citizenId;
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer handleBusiness;
	@Column(nullable = false, columnDefinition = "varchar2(500)")
	private String handleComment;
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String handleDept;
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer handleNextStep;
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String handlePerson;
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer handleState;
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer handleStep;
	@Column(nullable = false, columnDefinition = "number(4)")
	private Integer handleStepXh;
	@Temporal(TemporalType.TIMESTAMP)
	private Date handleTime;
	@Column(nullable = false, columnDefinition = "number(4) default 0", insertable = false)
	private Integer updateFlag;
	@Column(nullable = false, columnDefinition = "number(4) default 0", insertable = false)
	private Integer delFlag;

}

/**
 * 
 */
package com.css.bdpfnew.model.entity.bdpfnew;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author erDuo
 * @Date 2018年12月9日 下午2:11:41
 * @Description
 */
@Entity
@Where(clause = "del_flag = 0")
@Table(name = "CITIZEN_JIEDONG_PHOTOS", schema = "bdpfnew2018")
@Data
@EqualsAndHashCode(callSuper = false)
public class CitizenJiedongPhoto extends BaseEntity {

	private static final long serialVersionUID = -6553982825366668135L;
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String citizenId;
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String cdpfId;

	private Blob picture;

	@Column(nullable = false, columnDefinition = "number(4) default 0", insertable = false)
	private Integer delFlag;

}

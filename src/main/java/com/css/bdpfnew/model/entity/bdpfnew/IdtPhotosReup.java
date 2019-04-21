package com.css.bdpfnew.model.entity.bdpfnew;

import lombok.Data;
import org.hibernate.annotations.Where;
import javax.persistence.*;


@Entity
@Where(clause = "del_flag = 0")
@Table(name = "IDT_PHOTOS_REUP", schema = "bdpfnew2018")
@Data

public class IdtPhotosReup extends BaseEntity {
	private static final long serialVersionUID = 2207673099552851752L;

	// 基本信息
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String citizenId;// 身份证号
	@Column(nullable = false, columnDefinition = "varchar2(32)")
	private String name;// 姓名
	@Column(nullable = false)
	private String cityid;// 户籍区划id
	@Column(nullable = false)
	private String jiguanCode;// 户籍地详细地址
	@Column(nullable = true, columnDefinition = "varchar2(32)")
	private String requestId;// 任务主键
	@Column(nullable = true, columnDefinition = "varchar2(32)")
	private String requestIdFinished;// 任务主键
	@Column(nullable = false, columnDefinition = "number(4) default 0", insertable = false)
	private Integer delFlag;//0未补传   1已补传

}
package com.css.bdpfnew.model.entity.bdpfnew;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data

@Entity
@Table(name = "t_hibernate")

public class THibernate {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	private String id;

	private String name;

	private String num;

	private String remark;
}

package com.css.bdpfnew.repository;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.entity.bdpfnew.THibernate;

@Repository
@Table(name = "t_hibernate")
@Qualifier("thibernateRepository")
public interface THibernateRepository extends CrudRepository<THibernate, String> {

	public THibernate findOne(String id);

	// @SuppressWarnings("unchecked")
	@Transactional
	public THibernate save(THibernate h);

	@Query("select t from THibernate t where t.name=:name")
	public THibernate findSingleByName(@Param("name") String name);

}

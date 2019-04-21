package com.css.bdpfnew.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @Auther: GodNan
 * @Date: 2018/6/8 15:51
 * @Version: 1.0
 * @Description:
 */
@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, Integer>, JpaSpecificationExecutor<T> {
    Page<T> findAll(Specification<T> specification, Pageable pageable);
    Page<T> findAll(Pageable pageable);
}

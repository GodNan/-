package com.css.bdpfnew.repository;

import com.css.bdpfnew.model.entity.bdpfnew.Superman;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SupermanRepository extends JpaRepository<Superman, Integer>,CustomRepository<Superman, Integer> {
    Superman findById(String id);

    @Override
    Page<Superman> findAll(Pageable pageable);

    List<Superman> findByIdIn(String[] ids);
}

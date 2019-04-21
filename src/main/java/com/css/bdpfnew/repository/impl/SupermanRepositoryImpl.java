package com.css.bdpfnew.repository.impl;

import com.css.bdpfnew.model.entity.bdpfnew.Superman;
import com.css.bdpfnew.repository.CustomRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


public class SupermanRepositoryImpl implements CustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void method() {
        Query query = entityManager.createQuery("SELECT a from Superman a",Superman.class);
        List<Superman> list = query.getResultList();
        for (int i = 0; i < list.size() ; i++) {
            Superman superman = list.get(i);
            System.out.println(superman);
        }
        System.out.println("自定义方法执行了,and成功");
    }
}

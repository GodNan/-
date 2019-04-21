package com.css.bdpfnew.service.impl;

import com.css.bdpfnew.model.entity.bdpfnew.Superman;
import com.css.bdpfnew.repository.SupermanRepository;
import com.css.bdpfnew.service.SupermanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: GodNan
 * @Date: 2018/5/24 16:22
 * @Version: 1.0
 * @Description:
 */
@Service
public class SurpermanServiceImpl implements SupermanService {
    @Autowired
    private SupermanRepository supermanRepository;

    @Override
    public Page<Superman> findPage(Pageable pageable) {
        Page<Superman> page = supermanRepository.findAll(pageable);
        return page;
    }

    @Override
    public Superman findById(String uuid) {
        Superman superman = supermanRepository.findById(uuid);
        return superman;
    }

    @Override
    public List<Superman> findByIds(String[] ids) {

        return supermanRepository.findByIdIn(ids);
    }
}

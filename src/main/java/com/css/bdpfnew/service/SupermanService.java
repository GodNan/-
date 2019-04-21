package com.css.bdpfnew.service;

import com.css.bdpfnew.model.entity.bdpfnew.Superman;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Auther: GodNan
 * @Date: 2018/5/24 16:19
 * @Version: 1.0
 * @Description:
 */
public interface SupermanService {
    Page<Superman> findPage(Pageable pageable);
    Superman findById(String uuid);

    List<Superman> findByIds(String[] ids);
}

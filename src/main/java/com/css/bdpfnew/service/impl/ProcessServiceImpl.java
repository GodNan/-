package com.css.bdpfnew.service.impl;

import com.css.bdpfnew.model.entity.bdpfnew.Process;
import com.css.bdpfnew.repository.ProcessRepository;
import com.css.bdpfnew.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author lvmn
 * @Date 2018/11/8 15:24
 * @Description
 */
@Service
public class ProcessServiceImpl extends BaseServiceImpl<Process, Long>  implements ProcessService {

    @Autowired
    private ProcessRepository processRepository;

    @Override
    public List<Process> findAll() {
        return processRepository.findAllByOrderByProcessIdAsc();
    }
}

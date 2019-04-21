package com.css.bdpfnew.service;

import com.css.bdpfnew.model.entity.bdpfnew.Process;
import com.css.bdpfnew.model.vo.VoProcess;

import java.util.List;

/**
 * @Author lvmn
 * @Date 2018/11/8 15:05
 * @Description
 *  工作流程Controller
 */
public interface ProcessService extends BaseService<Process, Long>{
    List<Process> findAll();
}

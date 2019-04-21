/**
 * 
 */
package com.css.bdpfnew.repository;

import com.css.bdpfnew.model.entity.bdpfnew.Process;

import java.util.List;

/**
 * @Author erDuo
 * @Date 2018年9月29日 下午2:57:12
 * @Description
 */

public interface ProcessRepository extends BaseRepository<Process, Long> {
	Process findByProcessId(Integer processId);

	List<Process> findAll();

	List<Process> findAllByOrderByProcessIdAsc();
}
package com.css.bdpfnew.service;

import java.util.List;

import com.css.bdpfnew.model.dto.DtoState;
import com.css.bdpfnew.model.entity.bdpfnew.State;
import com.css.bdpfnew.model.vo.VoState;

/**
 * @Author erDuo
 * @Date 2018年7月5日 下午4:45:01
 * @Description:
 */
public interface StateService extends BaseService<State, Long> {
	VoState findByUuid(String uuid);

	List<State> getProcessSteps(DtoState dtoState);

	List<State> getProcessBackSteps(DtoState dtoState);
	
	List<State> getBackStepsByOrderNum(DtoState dtoState);
	
	String[] findStatesByProcess(Integer processId);
}
package com.css.bdpfnew.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.bdpfnew.model.dto.DtoState;
import com.css.bdpfnew.model.entity.bdpfnew.State;
import com.css.bdpfnew.model.vo.VoState;
import com.css.bdpfnew.repository.StateRepository;
import com.css.bdpfnew.service.StateService;

/**
 * @Author erDuo
 * @Date 2018年7月5日 下午4:48:01
 * @Description:
 */
@Service
public class StateServiceImpl extends BaseServiceImpl<State, Long> implements StateService {

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	public void setBaseDao(StateRepository stateRepository) {
		super.setBaseDao(stateRepository);
	}

	@Override
	public VoState findByUuid(String uuid) {
		State state = stateRepository.findByUuid(uuid);
		if (state == null) {
			return null;
		}
		VoState voState = new VoState();
		BeanUtils.copyProperties(state, voState);

		return voState;
	}

	@Override
	public List<State> getProcessSteps(DtoState dtoState) {
		return stateRepository.findByProcessIdAndStateTypeLessThanOrderByOrderNumAsc(dtoState.getProcessId(),
				dtoState.getStateType());
	}

	@Override
	public List<State> getProcessBackSteps(DtoState dtoState) {
		return stateRepository.findByProcessIdAndStateTypeAndStateCodeLessThanOrderByOrderNumAsc(
				dtoState.getProcessId(), dtoState.getStateType(), dtoState.getStateCode());
	}

	@Override
	public List<State> getBackStepsByOrderNum(DtoState dtoState) {
		return stateRepository.findByProcessIdAndStateTypeAndOrderNumLessThanOrderByOrderNumAsc(dtoState.getProcessId(),
				dtoState.getStateType(), dtoState.getOrderNum());
	}

	/**
	 * 通过流程id查找所有子流程名称集合
	 * @param processId
	 * @return 名称的数组集合
	 */
	@Override
	public String[] findStatesByProcess(Integer processId) {
		return stateRepository.findStatesByProcess(processId);
	}

}

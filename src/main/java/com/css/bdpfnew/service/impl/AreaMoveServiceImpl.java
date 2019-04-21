package com.css.bdpfnew.service.impl;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.constant.Constants;
import com.css.bdpfnew.model.dto.DtoAreaMove;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizen;
import com.css.bdpfnew.model.entity.bdpfnew.Request;
import com.css.bdpfnew.model.entity.bdpfnew.RequestNote;
import com.css.bdpfnew.model.entity.bdpfnew.RequestStakeholder;
import com.css.bdpfnew.model.entity.bdpfnew.SysUser;
import com.css.bdpfnew.repository.CitizenRepository;
import com.css.bdpfnew.repository.RequestStakeholderRepository;
import com.css.bdpfnew.repository.task.RequestNoteRepository;
import com.css.bdpfnew.repository.task.RequestRepository;
import com.css.bdpfnew.service.AreaMoveService;

/**
 * @Author erDuo
 * @Date 2018年8月7日 上午9:48:16
 * @Description:
 */
@Service
public class AreaMoveServiceImpl extends BaseServiceImpl<CdpfCitizen, Long> implements AreaMoveService {

	private CitizenRepository citizenRepository;
	private RequestRepository requestRepository;
	private RequestNoteRepository requestNoteRepository;
	private RequestStakeholderRepository requestStakeholderRepository;

	@Autowired
	public AreaMoveServiceImpl(CitizenRepository citizenRepository, RequestRepository requestRepository,
			RequestNoteRepository requestNoteRepository, RequestStakeholderRepository requestStakeholderRepository) {
		this.citizenRepository = citizenRepository;
		this.requestRepository = requestRepository;
		this.requestNoteRepository = requestNoteRepository;
		this.requestStakeholderRepository = requestStakeholderRepository;
	}

	@Override
	@Transactional
	public String areaMoveApply(DtoAreaMove dtoAreaMove) {
		SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
		CdpfCitizen citizen = citizenRepository.findByUuid(dtoAreaMove.getUuid());
		if (citizen == null) {
			return null;
		}

		// TODO:
		// 1.新建 request 对应户籍迁移的数据
		// 2.新建 requestNote 对应户籍迁移的操作记录
		// 3.新建requestStakeholder 对应下一步的操作人员

		Request request = new Request();
		request.setCdpfId(dtoAreaMove.getUuid());
		request.setCitizenId(dtoAreaMove.getCitizenId());
		request.setCitizenName(dtoAreaMove.getName());
		request.setCityid(dtoAreaMove.getCityid());
		request.setCurrentState(Constants.FINAL_REVIEW);
		request.setFormerState(Constants.NEW);
		request.setProcessId(Constants.PROCESS_AREAMOVE);
		request.setTitle("政务网市内户籍迁移");
		request.setUserId(user.getUuid());
		request.setUserName(user.getNickname());
		request.setOptionsApplyFor(dtoAreaMove.getNewCityid());
		request = requestRepository.save(request);

		RequestNote requestNote = new RequestNote();
		requestNote.setRequestId(request.getUuid());
		requestNote.setUserId(user.getUuid());
		requestNote.setUserName(user.getNickname());
		requestNote.setHandleState(request.getFormerState());
		requestNote.setFinishState(request.getCurrentState());
		requestNote.setNote("政务网市内户籍迁移申请");
		requestNote.setHandleType(new Integer(1));
		requestNoteRepository.save(requestNote);

		RequestStakeholder requestStakeholder = new RequestStakeholder();
		requestStakeholder.setRequestId(request.getUuid());
		requestStakeholder.setCityid(dtoAreaMove.getNewCityid());
		requestStakeholder.setPermissionId("handle");
		requestStakeholderRepository.save(requestStakeholder);

		citizen.setRequestId(request.getUuid());
		citizen.setIsMoving(new Integer(1));
		return request.getUuid();
	}

	@Override
	public String areaMoveAccept(DtoAreaMove dtoAreaMove) {
		// TODO Auto-generated method stub
		return null;
	}

}

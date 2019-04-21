package com.css.bdpfnew.service.impl.task;

import com.css.bdpfnew.constant.Constants;
import com.css.bdpfnew.model.dto.DtoNewTask;
import com.css.bdpfnew.model.dto.DtoRequest;
import com.css.bdpfnew.model.dto.DtoTask;
import com.css.bdpfnew.model.entity.bdpfnet.NetCdpfCitizen;
import com.css.bdpfnew.model.entity.bdpfnew.*;
import com.css.bdpfnew.model.entity.bdpfnew.Process;
import com.css.bdpfnew.model.entity.bdpfnew.view.TasksTodo;
import com.css.bdpfnew.repository.*;
import com.css.bdpfnew.repository.card.CdpfCardLogRepository;
import com.css.bdpfnew.repository.card.CdpfCardRepository;
import com.css.bdpfnew.repository.idt.IdtResultRepository;
import com.css.bdpfnew.repository.task.RequestNoteRepository;
import com.css.bdpfnew.repository.task.RequestRepository;
import com.css.bdpfnew.repository.task.TasksTodoRepository;
import com.css.bdpfnew.repositorynet.NetCdpfCitizensRepository;
import com.css.bdpfnew.repositorynet.NetCitizenKindHospitalRepository;
import com.css.bdpfnew.service.CdpfLogService;
import com.css.bdpfnew.service.DCodeService;
import com.css.bdpfnew.service.impl.BaseServiceImpl;
import com.css.bdpfnew.service.task.TasksTodoService;
import com.css.bdpfnew.util.CardNumUtil;
import com.css.bdpfnew.util.DateUtil;
import com.css.bdpfnew.util.PcnoUtil;
import com.css.bdpfnew.util.WorkFlowUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Author erDuo
 * @Date 2018年6月19日 下午3:08:52
 * @Description:
 */

@Service
public class TasksTodoServiceImpl extends BaseServiceImpl<TasksTodo, Long> implements TasksTodoService {
	@Autowired
	private TasksTodoRepository tasksTodoRepository;

	@Autowired
	private CitizenRepository citizenRepository;
	@Autowired
	private RequestRepository requestRepository;
	@Autowired
	private RequestNoteRepository requestNoteRepository;
	@Autowired
	private RequestStakeholderRepository requestStakeholderRepository;
	@Autowired
	private HistoryRepository historyRepository;
	@Autowired
	private IdtResultRepository idtResultRepository;
	@Autowired
	private CdpfCardRepository cdpfCardRepository;
	@Autowired
	private CdpfCardLogRepository cdpfCardLogRepository;
	@Autowired
	private ProcessRepository processRepository;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private CitizenNetRepository citizenNetRepository;
	@Autowired
	private CdpfZhuxiaoRepository cdpfZhuxiaoRepository;
	@Autowired
	private CdpfLogService cdpfLogService;
	@Autowired
	private DCodeService dCodeService;
	@Autowired
	private CdpfReviewAllRepository cdpfReviewAllRepository;
	@Autowired
	private CitizenZhuxiaoKindDateRepository citizenZhuxiaoKindDateRepository;
	@Autowired
	private NetCdpfCitizensRepository netCdpfCitizensRepository;
	@Autowired
	private NetCitizenKindHospitalRepository netCitizenKindHospitalRepository;

	@Autowired
	public void setBaseDao(TasksTodoRepository tasksTodoRepository) {
		super.setBaseDao(tasksTodoRepository);
	}

	@Override
	public Integer getCountOfTasksToDo(DtoRequest dtoRequest) {

		Set<String> roleList = (Set<String>) SecurityUtils.getSubject().getSession()
				.getAttribute(Constants.SESSION_ROLE_INFO);
		// 1 8a15b8736771e1650167735f9e2e0002 03-12月-18 05.21.45.517000 下午 05-12月-18
		// 10.03.59.967000 上午 0 燕山区批准角色 yanshanPizhun
		// 2 8a15b873677c0f7201677c1490b10001 05-12月-18 09.56.21.809000 上午 05-12月-18
		// 10.04.17.434000 上午 0 燕山区审核角色 yanshanShenhe

		if (roleList.contains("8a15b873677c0f7201677c1490b10001")
				|| roleList.contains("8a15b8736771e1650167735f9e2e0002")) {
			String[] cityRoles = new String[] { "110111004", "110111005", "110111006", "110111007" };
			return tasksTodoRepository.countTasksToDoByCityAndPermissionsAndCityidRole(dtoRequest.getCityidHolder(),
					dtoRequest.getPermissions(), cityRoles);
		}

		return tasksTodoRepository.countTasksToDoByCityAndPermissions(dtoRequest.getCityidHolder(),
				dtoRequest.getPermissions());
	}

	@Override
	@Transactional
	public String handleTaskTodo(DtoTask dtoTask) {

		SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);

		String citizenId = "";
		// 更新request
		Request request = requestRepository.findByUuid(dtoTask.getRequestNote().getRequestId());
		if (request == null) {
			return null;
		}

		CdpfCitizen citizen = citizenRepository.findByUuid(request.getCdpfId());
		Integer hadFinalReviewed = 0;
		if (citizen != null) {
			hadFinalReviewed = citizen.getHadFinalReviewed();
		}

		if (WorkFlowUtil.isNewApplyHulian(dtoTask.getProcessId())
				&& WorkFlowUtil.isHeyanShouli(dtoTask.getRequestNote().getHandleState())
				&& dtoTask.getRequestNote().getHandleType() == 1) {
			if (hadFinalReviewed == 8) {
				List<CitizenZhuxiaoKindDate> citizenZKDs = citizenZhuxiaoKindDateRepository
						.findByCdpfId(citizen.getUuid());

				for (CitizenZhuxiaoKindDate citizenZKD : citizenZKDs) {
					if (citizenZKD != null) {
						if (citizenZKD.getZhuxiaoType().intValue() != 0) {
							Date now = new Date();
							if (citizenZKD.getNextApplyDate().after(now)) {
								citizenZKD.getNextApplyDate();
								SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
								return formatter.format(citizenZKD.getNextApplyDate());
							}
						}
					}
				}
			}

		}

		request.setCurrentState(dtoTask.getRequestNote().getFinishState());
		request.setFormerState(dtoTask.getRequestNote().getHandleState());

		// 当前状态 和 之前状态 一致 或 处理类型 为 3(不通过), 代表 业务顺利完成
		if (dtoTask.getRequestNote().getFinishState().equals(dtoTask.getRequestNote().getHandleState())
				|| dtoTask.getRequestNote().getHandleType() == 3) {
			request.setHlSynced(new Integer(0));

			// "不通过": 更新批准通过信息为"none"
			// 非 "不通过": 更新 批准通过信息 与 欲申请信息 一致
			if (dtoTask.getRequestNote().getHandleType() == 3) {
				request.setOptionsAdopt("none");
			} else {
				// 申请多重残疾,不一定所有残疾类别都通过,需要在之后的判断中更新为通过的残疾类别
				request.setOptionsAdopt(
						dtoTask.getProcessId() == 892 ? request.getCityid() : request.getOptionsApplyFor());
			}

			if (!WorkFlowUtil.isHeyanShouli(dtoTask.getRequestNote().getHandleState())) {
				// TODO: 暂时不删除request, 只设置删除标记, 定期删除
				request.setDelFlag(new Integer(1));

				// 复制 request 数据 到 history
				History history = new History();
				BeanUtils.copyProperties(request, history);

				// 更新 request： uuid 操作时间
				history.setRequestId(request.getUuid());
				history.setRequestCreateDate(request.getCreateDate());
				history.setRequestModifyDate(request.getModifyDate());

				historyRepository.save(history);
			}

			if (citizen != null) {
				citizenId = citizen.getCitizenId();
			}

			// TODO: 更新残疾人表 citizen 的 证卡状态 及 相关数据
			if (dtoTask.getRequestNote().getFinishState().equals(dtoTask.getRequestNote().getHandleState())) {

				// 1.确定具体的业务流程
				// 2.新证申请(1): 首次发证日期,首张残疾证号 ,类别等级变更(5): 发证日期,残疾证号,
				// 3.注销(4): 注销时间
				// 4.迁入迁出(8) - 迁移中 的标志

				if (WorkFlowUtil.isApply(dtoTask.getProcessId())) {

					IdtResult idtResult = idtResultRepository.findByRequestId(dtoTask.getRequestNote().getRequestId());
					if (idtResult == null) {
						return null;
					}

					citizen.setIdtKind(idtResult.getIdtKind());
					citizen.setIdtLevel(idtResult.getIdtLevel());

					// TODO: 新旧残疾类别赋值
					if (citizen.getKindstr() != null && !citizen.getKindstr().equals("")) {
						citizen.setOldkindstr(citizen.getKindstr());
						idtResult.setOldkindstr(citizen.getKindstr());
						idtResult.setHlSynced(new Integer(0));
						idtResultRepository.save(idtResult);
					}
					citizen.setKindstr(idtResult.getKindstr());
					citizen.setLevelstr(idtResult.getLevelstr());

					// 更新当前完成评定的任务id
					citizen.setRequestIdFinished(dtoTask.getRequestNote().getRequestId());
					// 申请残疾类别鉴定的残疾人, 最后的接受结果为 评定通过的残疾类别字符串
					request.setOptionsAdopt(idtResult.getKindstr());

					// 6 或 9 待定
					citizen.setStatusRecord(new Integer(9));
					// TODO: 互联网待同步标志
					citizen.setHlSynced(new Integer(0));

					citizen.setCertDate(Calendar.getInstance().getTime());

					String cardNum = CardNumUtil.getCardNumNew(citizen.getCardNum(),
							citizen.getCitizenId() + citizen.getIdtKind() + citizen.getIdtLevel());

					citizen.setCardNum(cardNum);

					// 1.新证申请业务: 批准通过后,新增卡流程
					// 2.当前卡流程只包含: "卡申请" 和 "卡批准"
					// 3.非单独申请的卡流程, 即 "证卡同办" 自动发起的 卡流程, 将request_id 复制到 request_id_card
					// 4.新增一条制卡记录 cdpf_card
					if (WorkFlowUtil.isNewApply(dtoTask.getProcessId())) {

						// 注销流程走了新证申请,更新citizen_zhuxiao_kind_date数据
						if (hadFinalReviewed == 8) {
							List<CitizenZhuxiaoKindDate> citizenZKDs = citizenZhuxiaoKindDateRepository
									.findByCdpfId(citizen.getUuid());

							for (CitizenZhuxiaoKindDate citizenZKD : citizenZKDs) {
								if (citizenZKD != null) {
									citizenZKD.setZhuxiaoType(new Integer(0));
									citizenZKD.setHlSynced(new Integer(0));
									citizenZhuxiaoKindDateRepository.save(citizenZKD);
								}
							}
						}

						// 已批准标记 -- 确定为残疾人
						citizen.setHadFinalReviewed(new Integer(1));
						citizen.setFirstCertDate(Calendar.getInstance().getTime());
						citizen.setFirstcardnum(citizen.getCitizenId() + citizen.getIdtKind() + citizen.getIdtLevel());

						citizen.setRequestIdCard(citizen.getRequestId());
						citizen.setCardStatus(new Integer(311));
						// citizen.setCardHlSynced(new Integer(0));

						String oldCardNo = ""; // 旧卡号
						String oldBankNo = ""; // 旧银行卡号
						if (citizen.getCitizenId() != null && citizen.getCitizenId() != "") {
							List<CdpfCard> cards = cdpfCardRepository.findByCitizenId(citizen.getCitizenId());
							if (cards.size() > 0) {
								oldCardNo = cards.get(0).getCardNo(); // 按编辑时间倒序，取第一条的cardNo
								oldBankNo = cards.get(0).getBankNo(); // 按编辑时间倒序，取第一条的bankNo
							}
						}

						CdpfCard card = new CdpfCard();
						card.setCdpfId(citizen.getUuid());
						card.setRequestId(citizen.getRequestId());
						card.setCitizenId(citizen.getCitizenId());
						card.setName(citizen.getName());
						card.setCityid(citizen.getCityid());
						card.setJiguanCode(citizen.getJiguanCode());
						card.setCardBusiness(hadFinalReviewed == 8 ? new Integer(1409) : new Integer(111));
						card.setPcno(PcnoUtil.getPcno());
						card.setCardStatus(new Integer(311));
						card.setBankNo(oldBankNo); // 制卡时用到旧银行卡号，制卡后会更新为最新的
						card.setOldCardNo(oldCardNo);
						cdpfCardRepository.save(card);

						// TODO: 添加 制卡操作日志
						CdpfCardLog cardLog = new CdpfCardLog();
						cardLog.setRequestId(citizen.getRequestId());
						cardLog.setCdpfId(citizen.getUuid());
						cardLog.setCardStatus(new Integer(311));
						cardLog.setCitizenId(citizen.getCitizenId());
						cardLog.setPcno(card.getPcno());
						cardLog.setLogdata("卡批准通过");
						cardLog.setOpername(user.getNickname());

						cdpfCardLogRepository.save(cardLog);
					}
				} else if (WorkFlowUtil.isCardApply(dtoTask.getProcessId())) {

					if (WorkFlowUtil.isCardReApply(dtoTask.getProcessId())) {
						String cardNum = CardNumUtil.getCardNumNewForReApplyCard(citizen.getCardNum());
						citizen.setCardNum(cardNum);
					}

					String oldCardNo = ""; // 旧卡号
					String oldBankNo = ""; // 旧银行卡号
					if (citizen.getCitizenId() != null && citizen.getCitizenId() != "") {
						List<CdpfCard> cards = cdpfCardRepository.findByCitizenId(citizen.getCitizenId());
						if (cards.size() > 0) {
							oldCardNo = cards.get(0).getCardNo(); // 按编辑时间倒序，取第一条的cardNo
							oldBankNo = cards.get(0).getBankNo(); // 按编辑时间倒序，取第一条的bankNo
						}
					}

					// TODO: 办卡流程
					CdpfCard card = new CdpfCard();
					card.setCdpfId(citizen.getUuid());
					card.setRequestId(citizen.getRequestIdCard());
					card.setCitizenId(citizen.getCitizenId());
					card.setName(citizen.getName());
					card.setCityid(citizen.getCityid());
					card.setJiguanCode(citizen.getJiguanCode());
					card.setCardBusiness(dtoTask.getProcessId());
					card.setPcno(PcnoUtil.getPcno());
					card.setCardStatus(new Integer(311));
					card.setBankNo(oldBankNo); // 制卡时用到旧银行卡号，制卡后会更新为最新的
					card.setOldCardNo(oldCardNo);
					cdpfCardRepository.save(card);

					// TODO: 添加 制卡操作日志
					CdpfCardLog cardLog = new CdpfCardLog();
					cardLog.setRequestId(citizen.getRequestIdCard());
					cardLog.setCdpfId(citizen.getUuid());
					cardLog.setCardStatus(new Integer(311));
					cardLog.setCitizenId(citizen.getCitizenId());
					cardLog.setPcno(card.getPcno());
					cardLog.setLogdata("卡批准通过");
					cardLog.setOpername(user.getNickname());

					cdpfCardLogRepository.save(cardLog);
				} else if (WorkFlowUtil.isZhuxiao(dtoTask.getProcessId())) {
					// TODO:
					// hadFinalReviewed 与 statusRecord 同时更新
					citizen.setStatusRecord(new Integer(8));
					citizen.setHadFinalReviewed(new Integer(8));
					citizen.setCardStatus(new Integer(318));
					citizen.setHlSynced(new Integer(0));
					citizen.setIsHulian(new Integer(1));
					Integer logoutReason = Integer.valueOf(request.getOptionsApplyFor().substring(0, 1));
					citizen.setLogoutReason(logoutReason > 4 ? 4 : logoutReason);//注销原因

					citizen.setZhuxiaoTime(Calendar.getInstance().getTime());

					CdpfZhuxiao cdpfZhuxiao = cdpfZhuxiaoRepository.findByCitizenId(citizen.getCitizenId());
					if (cdpfZhuxiao != null) {
						cdpfZhuxiao.setStatus(new Integer(0));
						cdpfZhuxiaoRepository.save(cdpfZhuxiao);
					}

					// TODO:
					// 操作注销类别时间表 citizen_zhuxiao_kind_date
					// 1.查询残疾人的具体类别 kindstr
					// 2.依据评残类别,新增或者更新citizen_zhuxiao_kind_date 数据
					String hadZuxiaoKindstr = citizen.getKindstr();
					if (hadZuxiaoKindstr != null) {
						String[] hadZhuxiaoKinds = hadZuxiaoKindstr.split(",");
						for (String hadZhuxiaoKind : hadZhuxiaoKinds) {
							CitizenZhuxiaoKindDate citizenZKD = citizenZhuxiaoKindDateRepository
									.findByCdpfIdAndIdtKind(citizen.getUuid(), Integer.parseInt(hadZhuxiaoKind));
							if (citizenZKD == null) {
								citizenZKD = new CitizenZhuxiaoKindDate();
								citizenZKD.setCdpfId(citizen.getUuid());
								citizenZKD.setCitizenId(citizen.getCitizenId());
								citizenZKD.setIdtKind(Integer.parseInt(hadZhuxiaoKind));
							}
							citizenZKD.setZhuxiaoType(new Integer(8));
							citizenZKD.setNextApplyDate(DateUtil.getNextYear());
							citizenZKD.setHlSynced(new Integer(0));
							citizenZhuxiaoKindDateRepository.save(citizenZKD);
						}
					}

					// 残疾人注销时添加操作日志
					String cdpfIdStr = citizen.getUuid();
					String requestIdStr = request.getUuid();
					String logData = "政务网残证注销批准通过";
					Integer logType = new Integer(92);
					Integer opertype = new Integer(1);
					cdpfLogService.create(cdpfIdStr, requestIdStr, logData, logType, opertype);

				} else if (WorkFlowUtil.isZhuxiaoResume(dtoTask.getProcessId())) {
					citizen.setStatusRecord(new Integer(9));
					citizen.setHadFinalReviewed(new Integer(1));
					citizen.setCardStatus(new Integer(340));
					citizen.setHlSynced(new Integer(0));
					citizen.setIsHulian(new Integer(1));

					// TODO:
					// 操作注销类别时间表 citizen_zhuxiao_kind_date
					// 1.查询残疾人的具体类别 kindstr
					// 2.依据评残类别,新增或者更新citizen_zhuxiao_kind_date 数据
					// zhuxiaoType: 8: 残疾人已注销; 1: 只注销了对应类别 ; 0: 未注销
					List<CitizenZhuxiaoKindDate> citizenZKDs = citizenZhuxiaoKindDateRepository
							.findByCdpfId(citizen.getUuid());

					for (CitizenZhuxiaoKindDate citizenZKD : citizenZKDs) {
						if (citizenZKD != null) {
							citizenZKD.setZhuxiaoType(new Integer(0));
							citizenZKD.setHlSynced(new Integer(0));
							citizenZhuxiaoKindDateRepository.save(citizenZKD);
						}
					}

					// 残疾人注销时添加操作日志
					String cdpfIdStr = citizen.getUuid();
					String requestIdStr = request.getUuid();
					String logData = "政务网注销恢复批准通过";
					Integer logType = new Integer(926);
					Integer opertype = new Integer(1);
					cdpfLogService.create(cdpfIdStr, requestIdStr, logData, logType, opertype);

				} else if (WorkFlowUtil.isAreamoveFinished(dtoTask.getProcessId())) {
					citizen.setIsMoving(new Integer(0));
					citizen.setHlSynced(new Integer(8));
					citizen.setCityid(
							dtoTask.getProcessId() == 892 ? request.getCityid() : request.getOptionsApplyFor());

					if (dtoTask.getRequestNote().getNote().indexOf("-") > -1) {
						citizen.setJiguanCode(dtoTask.getRequestNote().getNote().split("-")[1]);
						dtoTask.getRequestNote().setNote(dtoTask.getRequestNote().getNote().split("-")[0]);
					} else {
						citizen.setJiguanCode(dtoTask.getRequestNote().getNote());
					}
				} else if (WorkFlowUtil.isAreamoveHlOut(dtoTask.getProcessId())) {
					newTaskAreaMoveHLIn(citizen, request);
				} else if (WorkFlowUtil.isAreaChange(dtoTask.getProcessId())) {
					citizen.setOldcityidKsqc(citizen.getCityid());
					citizen.setCityid(request.getOptionsApplyFor()); //设置为外省的户籍地
					citizen.setHadFinalReviewed(new Integer(99));  //跨省迁出后设置为99
					citizen.setStatusRecord(new Integer(99)); //跨省迁出后设置为99
				}

			} else {
				// 不通过
				// 1.确定具体的业务流程
				// 2.新证申请(1): 首次发证日期,首张残疾证号 ,类别等级变更(5): 发证日期,残疾证号,
				// 3.注销(4): 注销时间
				// 4.迁入迁出(8) - 迁移中 的标志

				// TODO: 新证申请,类别等级变更 不通过退回 的数据处理
				if (WorkFlowUtil.isNewApplyHulian(dtoTask.getProcessId())) {
					CdpfCitizenNet citizenNet = citizenNetRepository
							.findByRequestId(dtoTask.getRequestNote().getRequestId());
					if (citizenNet != null) {
						citizenId = citizenNet.getCitizenId();
					}
					citizenNet.setReviewStatus(8);
					citizenNet.setHlSynced(new Integer(0));
					citizenNetRepository.save(citizenNet);
				}

				if (WorkFlowUtil.isAreamoveProcess(dtoTask.getProcessId())) {
					citizen.setIsMoving(new Integer(0));
					citizen.setHlSynced(new Integer(8));
				}

				if (WorkFlowUtil.isCardNewApply(dtoTask.getProcessId())) {
					citizen.setHadGaveCard(new Integer(8));
				}

				if (WorkFlowUtil.isCardApplyHulian(dtoTask.getProcessId())) {
					citizen.setCardStatus(new Integer(340));

					if (WorkFlowUtil.isCardReApply(dtoTask.getProcessId())) {
						citizen.setIsreportlosscard(new Integer(1));
					}

					citizen.setIsHulian(new Integer(1));
					citizen.setCardHlSynced(new Integer(0));
				}
			}
			if (citizen != null) {
				if (request.getProcessId() != null && (request.getProcessId() == 1209 || request.getProcessId() == 1409)
						&& citizen.getWaitforcardchange() != null && citizen.getWaitforcardchange() == 1) {
					citizen.setWaitforcardchange(new Integer(0));
				}
				citizenRepository.save(citizen);
			}
		}
		if (WorkFlowUtil.isHeyanShouli(dtoTask.getRequestNote().getHandleState())) {
			request.setOptionsAdopt(null);
		}
		requestRepository.save(request);

		// 需要更新 最新note
		// 将之前的 note 更新 为 非 最新
		// 通过 requestId, userId 和 latest 查询出 requestNote
		RequestNote requestNote = requestNoteRepository
				.findByRequestIdAndUserIdAndLatest(dtoTask.getRequestNote().getRequestId(), user.getUuid(), 1);
		if (requestNote != null) {
			requestNote.setLatest(new Integer(0));
			requestNoteRepository.save(requestNote);
		}

		requestNote = new RequestNote();
		BeanUtils.copyProperties(dtoTask.getRequestNote(), requestNote);
		requestNoteRepository.save(requestNote);

		Integer currentState = dtoTask.getRequestNote().getFinishState();
		Integer formerState = dtoTask.getRequestNote().getHandleState();

		RequestStakeholder requestStakeholder = requestStakeholderRepository
				.findByRequestId(dtoTask.getRequestStakeholder().getRequestId());
		if (requestStakeholder == null) {
			requestStakeholder = new RequestStakeholder();
			BeanUtils.copyProperties(dtoTask.getRequestStakeholder(), requestStakeholder);
			requestStakeholderRepository.save(requestStakeholder);
		} else {
			BeanUtils.copyProperties(dtoTask.getRequestStakeholder(), requestStakeholder, new String[] { "uuid" });
			if (citizen != null) {
				String cityid = citizen.getCityid();
				if (dtoTask.getRequestNote().getHandleType() == 2) {
					if (currentState == 119 || currentState == 209 || currentState == 219) {
						if (cityid.length() > 9) {
							requestStakeholder.setCityid(cityid.substring(0, 9) + "%");
						}
					}
				}
			}

			requestStakeholderRepository.save(requestStakeholder);
		}

		// 互联网流程 当前状态为 "录入" "预约医院" 的处理
		if (WorkFlowUtil.isHulian(dtoTask.getProcessId())) {
			if (currentState == 217) {
				CdpfCitizenNet citizenNet = citizenNetRepository
						.findByRequestId(dtoTask.getRequestNote().getRequestId());
				if (citizenNet != null) {
					citizenId = citizenNet.getCitizenId();
				}
				citizenNet.setReviewStatus(4);
				citizenNet.setHlSynced(new Integer(0));
				citizenNetRepository.save(citizenNet);
			} else if (currentState == 99) {
				if (formerState == 119 || formerState == 209) {
					CdpfCitizenNet citizenNet = citizenNetRepository
							.findByRequestId(dtoTask.getRequestNote().getRequestId());
					citizenNet.setReviewStatus(formerState == 119 ? 3 : 5);
					if (formerState == 119) {
						citizenNet.setHlShouliComment(dtoTask.getRequestNote().getNote());
						citizenNet.setHlShouliPerson(user.getNickname());
					} else {
						citizenNet.setHlReviewComment(dtoTask.getRequestNote().getNote());
						citizenNet.setHlReviewPerson(user.getNickname());

					}
					citizenNet.setHlSynced(new Integer(0));
					citizenNetRepository.save(citizenNet);
				}
			}
		}

		// 互联网 新证申请没有医院的残疾人 受理 处理 -- 复制残疾人信息 cdpf_citizen_net 到 cdpf_citizen
		if (WorkFlowUtil.isNewApplyWithoutHospital(dtoTask.getProcessId())) {
			if (currentState == 217) {
				CdpfCitizenNet citizenNet = citizenNetRepository.findByRequestId(request.getUuid());
				if (citizenNet != null) {
					citizenId = citizenNet.getCitizenId();
				}

				if (citizen == null) {
					CdpfCitizen citizenNew = new CdpfCitizen();
					BeanUtils.copyProperties(citizenNet, citizenNew);
					citizenNew.setHadFinalReviewed(new Integer(0));
					citizenNew.setHadGaveCard(new Integer(0));
					citizenNew.setPhotoPushState(new Integer(0));
					citizenNew.setWaitforcardchange(new Integer(0));
					citizenNew.setHadIdtPhoto(new Integer(1));
					citizenNew.setIsHulian(new Integer(1));
					citizenRepository.save(citizenNew);
				}

			}
		}

		//如果操作是回退至医院评定环节，执行backRequestToHospital（）方法，然后返回
		//如果是处于审核环节，进行回退操作
		if(dtoTask.getRequestNote().getHandleState() == 25 && dtoTask.getRequestNote().getHandleType() == 2){
			CdpfCitizenNet citizenNet = citizenNetRepository.findByRequestId(dtoTask.getRequestNote().getRequestId());
			if (citizenNet != null) {
				citizenId = citizenNet.getCitizenId();
			}
			//回退至医院评定（修改残疾人review_state和评定时间）
			if(dtoTask.getRequestNote().getFinishState() == 218){
				NetCdpfCitizen byCitizenId = netCdpfCitizensRepository.findByCitizenId(citizenId);
				byCitizenId.setReviewStatus("6");
				netCdpfCitizensRepository.save(byCitizenId);
				netCitizenKindHospitalRepository.updateByCitizenId(byCitizenId.getUuid());
			}
			//回退至预约医院（修改残疾人review_state和需删除预约信息，）
			if(dtoTask.getRequestNote().getFinishState() != 218){
				NetCdpfCitizen byCitizenId = netCdpfCitizensRepository.findByCitizenId(citizenId);
				byCitizenId.setReviewStatus("4");
				netCdpfCitizensRepository.save(byCitizenId);
				netCitizenKindHospitalRepository.deleteByCitizenId(byCitizenId.getUuid());
			}
		}

		// TODO: 类别等级变更(医院录入) -- 需要同步cdpf_idt数据
		if (WorkFlowUtil.isHulianKindLevelChangeWithHospital(dtoTask.getProcessId())) {
			if (currentState == 217) {
				if (citizen.getRequestIdFinished() != null) {
					IdtResult idtResultOld = idtResultRepository.findByRequestId(citizen.getRequestIdFinished());

					if (idtResultOld != null) {
						idtResultOld.setHlSynced(new Integer(9));
						idtResultRepository.save(idtResultOld);
					}
				}
			}
		}

		if (StringUtils.isEmpty(citizenId)) {
			CdpfCitizenNet citizenNet = citizenNetRepository.findByRequestId(dtoTask.getRequestNote().getRequestId());
			if (citizenNet != null) {
				citizenId = citizenNet.getCitizenId();
			}
		}

		if (!StringUtils.isEmpty(citizenId)) {
			CdpfReviewAll review = new CdpfReviewAll();
			review.setCdpfId(request.getUuid());
			review.setCitizenId(citizenId);
			review.setHandlePerson(dtoTask.getRequestNote().getUserName());
			review.setHandleDept(dtoTask.getRequestStakeholder().getCityid().replace("%", ""));
			review.setHandleComment(dtoTask.getRequestNote().getNote());
			review.setHandleBusiness(dtoTask.getProcessId());
			review.setHandleStep(dtoTask.getRequestNote().getHandleState());
			review.setHandleNextStep(dtoTask.getRequestNote().getFinishState());
			review.setHandleStepXh(new Integer(0));
			review.setHandleState(dtoTask.getRequestNote().getHandleType());
			review.setHandleTime(Calendar.getInstance().getTime());

			cdpfReviewAllRepository.save(review);
		}

		return dtoTask.getRequestStakeholder().getRequestId();
	}

	@Override
	public String newTask(DtoNewTask dtoNewTask) {

		CdpfCitizen citizen = citizenRepository.findByUuid(dtoNewTask.getCdpfId());
		if (citizen == null) {
			return null;
		}
		SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);

		Process process = processRepository.findByProcessId(dtoNewTask.getProcessId());
		// TODO:
		// states 与 stateList二选一
		// String[] states = process.getFlow().split(",");

		List<State> stateList = stateRepository
				.findByProcessIdAndStateTypeLessThanOrderByOrderNumAsc(dtoNewTask.getProcessId(), 3);

		// TODO:
		// 1.新建任务数据
		Request request = new Request();
		request.setCdpfId(dtoNewTask.getCdpfId());
		request.setCitizenId(citizen.getCitizenId());
		request.setCitizenName(citizen.getName());
		request.setCityid(citizen.getCityid());
		request.setCurrentState(stateList.get(1).getStateCode());
		request.setFormerState(stateList.get(0).getStateCode());
		request.setProcessId(dtoNewTask.getProcessId());
		request.setTitle(process != null ? process.getName() : "");
		request.setUserId(user.getUuid());
		request.setUserName(user.getNickname());
		if (WorkFlowUtil.isZhuxiao(dtoNewTask.getProcessId())) {
			request.setOptionsApplyFor(String.valueOf(dtoNewTask.getLogoutReason()));
			citizen.setLogoutReason(dtoNewTask.getLogoutReason());
		}
		if (WorkFlowUtil.isZhuxiaoResume(dtoNewTask.getProcessId())) {
			request.setOptionsApplyFor(String.valueOf(dtoNewTask.getLogoutResumeReason()));
		}
		request = requestRepository.save(request);

		// TODO:
		// 新建任务历史数据
		RequestNote requestNote = new RequestNote();
		requestNote.setRequestId(request.getUuid());
		requestNote.setUserId(user.getUuid());
		requestNote.setUserName(user.getNickname());
		requestNote.setHandleState(stateList.get(0).getStateCode());
		requestNote.setFinishState(stateList.get(1).getStateCode());
		requestNote.setNote(stateList.get(0).getName() + "完成,进入" + stateList.get(1).getName());
		requestNote.setHandleType(new Integer(1));
		requestNoteRepository.save(requestNote);

		// TODO:
		// 新建待办人员信息
		RequestStakeholder requestStakeholder = new RequestStakeholder();
		requestStakeholder.setRequestId(request.getUuid());
		requestStakeholder.setCityid(citizen.getCityid());
		requestStakeholder.setPermissionId(stateList.get(1).getPermissionId());
		requestStakeholderRepository.save(requestStakeholder);

		if (process.getType() == 2) {
			citizen.setRequestIdCard(request.getUuid());
			citizen.setWaitforcardchange(new Integer(0));
			citizen.setHadGaveCard(new Integer(0));
		}
		if (WorkFlowUtil.isZhuxiaoResume(dtoNewTask.getProcessId())) {
			citizen.setHadFinalReviewed(new Integer(81));
		}

		citizenRepository.save(citizen);

		// 残疾人注销时添加操作日志
		if (dtoNewTask.getProcessId() == 4) {

			CdpfZhuxiao cdpfZhuxiao = cdpfZhuxiaoRepository.findByCitizenId(citizen.getCitizenId());
			if (cdpfZhuxiao != null) {
				cdpfZhuxiao.setStatus(new Integer(666)); // 中间状态
				cdpfZhuxiaoRepository.save(cdpfZhuxiao);
			}

			String logoutReason = dCodeService
					.findByCodeAndTypeCode(dtoNewTask.getLogoutReason().toString(), "d_logout_reason").getDescription();
			String cdpfIdStr = citizen.getUuid();
			String requestIdStr = request.getUuid();
			String logData = "政务网残证注销，注销原因：" + logoutReason;
			Integer logType = new Integer(931);
			Integer opertype = new Integer(1);
			cdpfLogService.create(cdpfIdStr, requestIdStr, logData, logType, opertype);
		}

		if (WorkFlowUtil.isZhuxiaoResume(dtoNewTask.getProcessId())) {
			String logoutResumeReason = dCodeService
					.findByCodeAndTypeCode(dtoNewTask.getLogoutResumeReason().toString(), "d_logout_resume_reason")
					.getDescription();
			String cdpfIdStr = citizen.getUuid();
			String requestIdStr = request.getUuid();
			String logData = "政务网注销恢复，注销恢复原因：" + logoutResumeReason;
			Integer logType = new Integer(9316);
			Integer opertype = new Integer(1);
			cdpfLogService.create(cdpfIdStr, requestIdStr, logData, logType, opertype);
		}

		return request.getUuid();
	}

	public String newTaskAreaMoveHLIn(CdpfCitizen citizen, Request requestAreaMoveHlOut) {
		SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);

		Request request = new Request();
		request.setCdpfId(citizen.getUuid());
		request.setCitizenId(citizen.getCitizenId());
		request.setCitizenName(citizen.getName());
		request.setCityid(requestAreaMoveHlOut.getOptionsApplyFor());
		request.setCurrentState(Constants.HANDLE_HL);
		request.setFormerState(Constants.NEW);
		request.setProcessId(Constants.PROCESS_AREAMOVE_HL_IN);
		request.setTitle("互联网跨区迁入");
		request.setUserId(user.getUuid());
		request.setUserName(user.getNickname());
		request.setOptionsApplyFor(requestAreaMoveHlOut.getCityid());
		request = requestRepository.save(request);

		RequestNote requestNote = new RequestNote();
		requestNote.setRequestId(request.getUuid());
		requestNote.setUserId(user.getUuid());
		requestNote.setUserName(user.getNickname());
		requestNote.setHandleState(request.getFormerState());
		requestNote.setFinishState(request.getCurrentState());
		requestNote.setNote("互联网跨区迁出通过,进入互联网跨区迁入");
		requestNote.setHandleType(new Integer(1));
		requestNoteRepository.save(requestNote);

		RequestStakeholder requestStakeholder = new RequestStakeholder();
		requestStakeholder.setRequestId(request.getUuid());
		requestStakeholder.setCityid(requestAreaMoveHlOut.getOptionsApplyFor());
		requestStakeholder.setPermissionId("handleHL");
		requestStakeholderRepository.save(requestStakeholder);

		// 非残疾评定的流程是否需要关联cdpf_citizen.request_id
		// citizen.setRequestId(request.getUuid());
		return request.getUuid();
	}

	/**
	 * 已无公示,此方法废弃
	 */
//	@Override
//	@Transactional
//	public String handleTaskTodoPost(DtoPost dtoPost) {
//		SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
//
//		// 更新request
//		Request request = requestRepository.findByUuid(dtoPost.getRequestId());
//		if (request == null) {
//			return null;
//		}
//		if (dtoPost.getPostType() == 3) {
//			request.setCurrentState(Constants.DENY);
//			request.setOptionsAdopt("none");
//		} else {
//			request.setCurrentState(Constants.SECOND_REVIEW);
//		}
//		request.setFormerState(Constants.POST);
//		requestRepository.save(request);
//
//		// 需要更新 最新note
//		// 将之前的 note 更新 为 非 最新
//		// 通过 requestId, userId 和 latest 查询出 requestNote
//		RequestNote requestNote = requestNoteRepository.findByRequestIdAndUserIdAndLatest(dtoPost.getRequestId(),
//				user.getUuid(), 1);
//		if (requestNote != null) {
//			requestNote.setLatest(new Integer(0));
//			requestNoteRepository.save(requestNote);
//		}
//
//		// 新建任务历史数据
//		requestNote = new RequestNote();
//		requestNote.setRequestId(request.getUuid());
//		requestNote.setUserId(user.getUuid());
//		requestNote.setUserName(user.getNickname());
//		requestNote.setHandleState(Constants.POST);
//
//		if (dtoPost.getPostType() == 3) {
//			requestNote.setFinishState(Constants.DENY);
//			requestNote.setNote("公示未通过，流程结束");
//			requestNote.setHandleType(new Integer(3));
//		} else {
//			requestNote.setFinishState(Constants.SECOND_REVIEW);
//			requestNote.setNote("公示完成进入审核");
//			requestNote.setHandleType(new Integer(1));
//		}
//
//		requestNoteRepository.save(requestNote);
//
//		RequestStakeholder requestStakeholder = requestStakeholderRepository.findByRequestId(dtoPost.getRequestId());
//		if (requestStakeholder == null) {
//			return null;
//		} else {
//			if (dtoPost.getPostType() == 3) {
//				requestStakeholder.setPermissionId(Constants.PERMISSIONID_DENY);
//			} else {
//				// requestStakeholder.setPermissionId(Constants.PERMISSIONIDS_AFTER_POST.get(request.getProcessId()));
//			}
//			requestStakeholderRepository.save(requestStakeholder);
//		}
//
//		CdpfPost post = new CdpfPost();
//		BeanUtils.copyProperties(dtoPost, post);
//		post = postRepository.save(post);
//		return post.getRequestId();
//	}

	/**
	 * 将流程回退值医院评定环节
	 */
	private void backRequestToHospital(){
		//1.首先修改残疾人状态
		//2.修改request状态
		//3.修改requestNote记录
		//4.修改ca同步记录
	}

}

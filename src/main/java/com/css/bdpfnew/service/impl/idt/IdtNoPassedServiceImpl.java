package com.css.bdpfnew.service.impl.idt;

import java.text.SimpleDateFormat;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.constant.Constants;
import com.css.bdpfnew.model.dto.DtoIdt;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizen;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizenNet;
import com.css.bdpfnew.model.entity.bdpfnew.History;
import com.css.bdpfnew.model.entity.bdpfnew.IdtResult;
import com.css.bdpfnew.model.entity.bdpfnew.Request;
import com.css.bdpfnew.model.entity.bdpfnew.RequestNote;
import com.css.bdpfnew.model.entity.bdpfnew.RequestStakeholder;
import com.css.bdpfnew.model.entity.bdpfnew.SysUser;
import com.css.bdpfnew.repository.AreaRepository;
import com.css.bdpfnew.repository.CitizenNetRepository;
import com.css.bdpfnew.repository.CitizenRepository;
import com.css.bdpfnew.repository.HistoryRepository;
import com.css.bdpfnew.repository.RequestStakeholderRepository;
import com.css.bdpfnew.repository.idt.IdtResultRepository;
import com.css.bdpfnew.repository.task.RequestNoteRepository;
import com.css.bdpfnew.repository.task.RequestRepository;
import com.css.bdpfnew.service.CdpfLogService;
import com.css.bdpfnew.service.DCodeService;
import com.css.bdpfnew.service.idt.IdtNoPassedService;

/**
 * @Author erDuo
 * @Date 2018年6月30日 下午3:58:20
 * @Description:
 */
@Service
public class IdtNoPassedServiceImpl implements IdtNoPassedService {

	private CitizenRepository citizenRepository;

	private IdtResultRepository idtResultRepository;
	private RequestRepository requestRepository;
	private RequestNoteRepository requestNoteRepository;
	private RequestStakeholderRepository requestStakeholderRepository;
	private AreaRepository areaRepository;
	private String requestId;
	private String cdpfId;

	@Autowired
	private CdpfLogService cdpfLogService;
	@Autowired
	private DCodeService dCodeService;
	@Autowired
	private CitizenNetRepository citizenNetRepository;
	@Autowired
	private HistoryRepository historyRepository;

	@Autowired
	public IdtNoPassedServiceImpl(CitizenRepository citizenRepository, IdtResultRepository idtResultRepository,
			RequestRepository requestRepository, RequestNoteRepository requestNoteRepository,
			RequestStakeholderRepository requestStakeholderRepository, AreaRepository areaRepository) {
		this.citizenRepository = citizenRepository;
		this.idtResultRepository = idtResultRepository;
		this.requestRepository = requestRepository;
		this.requestNoteRepository = requestNoteRepository;
		this.requestStakeholderRepository = requestStakeholderRepository;
		this.areaRepository = areaRepository;
	}

	@Override
	@Transactional
	public String idtNoPassedUpdate(DtoIdt dtoIdt) {

		CdpfCitizenNet citizenNet = citizenNetRepository.findByUuid(dtoIdt.getDtocitizen().getUuid());
		if (citizenNet != null) {
			citizenNet.setReviewStatus(8);
			citizenNet.setHlSynced(new Integer(0));
			citizenNetRepository.save(citizenNet);
		}
		// 残疾人基本信息
		CdpfCitizen citizen = citizenRepository.findByUuid(dtoIdt.getDtocitizen().getUuid());

		// 通过比较得到基本信息不同的信息
		String logData = getCompareResult(citizen, dtoIdt);

		BeanUtils.copyProperties(dtoIdt.getDtocitizen(), citizen, new String[] { "uuid" });
		citizen = citizenRepository.save(citizen);

		// 操作日志
		String cdpfIdStr = citizen.getUuid();
		String requestIdStr = citizen.getRequestId();
		Integer logType = new Integer(96);
		Integer opertype = new Integer(2);
		cdpfLogService.create(cdpfIdStr, requestIdStr, logData, logType, opertype);

		System.out.println("IdtNoPassedServiceImpl - cdpfLogService");

		requestId = citizen.getRequestId();
		cdpfId = citizen.getUuid();
		String cityid = citizen.getCityid();

		IdtResult idtResult = idtResultRepository.findByRequestId(requestId);

		// 评残结果信息
		if (idtResult == null) {
			idtResult = new IdtResult();
		}
		if (dtoIdt.getDtoresultX().getIdtKind() != null) {
			BeanUtils.copyProperties(dtoIdt.getDtoresultX(), idtResult, new String[] { "uuid" });
			idtResult.setRequestId(requestId);
			idtResult.setCdpfId(cdpfId);
			idtResult.setHlSynced(new Integer(0));
			idtResult.setKindstr("0,");
			idtResult.setLevelstr("0,0,");
			idtResult = idtResultRepository.save(idtResult);
		}

		if (dtoIdt.getMin() == 0) {
			SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);

			Request request = requestRepository.findByUuid(citizen.getRequestId());
			if (request == null) {
				return null;
			}

			request.setCurrentState(Constants.DENY);
			request.setFormerState(Constants.IDTINFO_INPUT);
			request.setOptionsAdopt(Constants.OPTIONS_ADOPT_DENY);
			
			request.setDelFlag(new Integer(1));
			request.setHlSynced(new Integer(0));
			request = requestRepository.save(request);
			

			// 复制 request 数据 到 history
			History history = new History();
			BeanUtils.copyProperties(request, history);

			// 更新 request： uuid 操作时间
			history.setRequestId(request.getUuid());
			history.setRequestCreateDate(request.getCreateDate());
			history.setRequestModifyDate(request.getModifyDate());

			historyRepository.save(history);
			

			RequestNote requestNote = requestNoteRepository.findByRequestIdAndUserIdAndLatest(citizen.getRequestId(),
					user.getUuid(), 1);
			if (requestNote != null) {
				requestNote.setLatest(new Integer(0));
				requestNoteRepository.save(requestNote);
			}

			requestNote = new RequestNote();
			requestNote.setRequestId(request.getUuid());
			requestNote.setUserId(user.getUuid());
			requestNote.setUserName(user.getNickname());

			requestNote.setHandleState(request.getFormerState());
			requestNote.setFinishState(request.getCurrentState());
			requestNote.setNote(Constants.REQUEST_NOTE_DENY);
			requestNote.setHandleType(new Integer(3));
			requestNoteRepository.save(requestNote);

			RequestStakeholder requestStakeholder = requestStakeholderRepository.findByRequestId(request.getUuid());
			if (requestStakeholder == null) {
				return null;
			}
			requestStakeholder.setPermissionId(Constants.PERMISSIONID_DENY);
			requestStakeholder.setCityid(cityid.substring(0, 9) + "%");
			requestStakeholderRepository.save(requestStakeholder);

			// 类别等级变更不通过,自动发起注销流程
			if (request.getProcessId() == 58) {

			}
		}
		return citizen.getRequestId();
	}

	public String getCompareResult(CdpfCitizen citizen, DtoIdt dtoIdt) {

		String logData = "";

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		if (citizen.getName() != "" && dtoIdt.getDtocitizen().getName() != "" && citizen.getName() != null
				&& dtoIdt.getDtocitizen().getName() != null) {
			if (!citizen.getName().equals(dtoIdt.getDtocitizen().getName())) {
				logData += "[姓名]：由 “" + citizen.getName() + "” 修改为  “" + dtoIdt.getDtocitizen().getName() + "”； ";
			}
		}

		if (citizen.getCitizenId() != "" && dtoIdt.getDtocitizen().getCitizenId() != ""
				&& citizen.getCitizenId() != null && dtoIdt.getDtocitizen().getCitizenId() != null) {
			if (!citizen.getCitizenId().equals(dtoIdt.getDtocitizen().getCitizenId())) {
				logData += "[身份证号]：由 “" + citizen.getCitizenId() + "” 修改为  “" + dtoIdt.getDtocitizen().getCitizenId()
						+ "”； ";
			}
		}

		if (citizen.getBirthdate().toString() != "" && dtoIdt.getDtocitizen().getBirthdate().toString() != ""
				&& citizen.getBirthdate() != null && dtoIdt.getDtocitizen().getBirthdate() != null) {
			if (!citizen.getBirthdate().toString().equals(formatter.format(dtoIdt.getDtocitizen().getBirthdate()))) {
				String dateString = formatter.format(dtoIdt.getDtocitizen().getBirthdate());
				logData += "[出生年月]：由 “" + citizen.getBirthdate() + "” 修改为  “" + dateString + "”； ";
			}
		}

		if (citizen.getGender().toString() != "" && dtoIdt.getDtocitizen().getGender().toString() != ""
				&& citizen.getGender() != null && dtoIdt.getDtocitizen().getGender() != null) {
			if (!citizen.getGender().equals(dtoIdt.getDtocitizen().getGender())) {
				String str1 = dCodeService.findByCodeAndTypeCode(citizen.getGender().toString(), "d_gender")
						.getDescription();
				String str2 = dCodeService
						.findByCodeAndTypeCode(dtoIdt.getDtocitizen().getGender().toString(), "d_gender")
						.getDescription();
				logData += "[性别]：由 “" + str1 + "” 修改为  “" + str2 + "”； ";
			}
		}

		if (citizen.getRace().toString() != "" && dtoIdt.getDtocitizen().getRace().toString() != ""
				&& citizen.getRace() != null && dtoIdt.getDtocitizen().getRace() != null) {
			if (!citizen.getRace().equals(dtoIdt.getDtocitizen().getRace())) {
				String str1 = dCodeService.findByCodeAndTypeCode(citizen.getRace().toString(), "d_race")
						.getDescription();
				String str2 = dCodeService.findByCodeAndTypeCode(dtoIdt.getDtocitizen().getRace().toString(), "d_race")
						.getDescription();
				logData += "[民族]：由 “" + str1 + "” 修改为  “" + str2 + "”； ";
			}
		}

		if (citizen.getEduLevel().toString() != "" && dtoIdt.getDtocitizen().getEduLevel().toString() != ""
				&& citizen.getEduLevel() != null && dtoIdt.getDtocitizen().getEduLevel() != null) {
			if (!citizen.getEduLevel().equals(dtoIdt.getDtocitizen().getEduLevel())) {
				String str1 = dCodeService.findByCodeAndTypeCode(citizen.getEduLevel().toString(), "d_education")
						.getDescription();
				String str2 = dCodeService
						.findByCodeAndTypeCode(dtoIdt.getDtocitizen().getEduLevel().toString(), "d_education")
						.getDescription();
				logData += "[文化程度]：由 “" + str1 + "” 修改为  “" + str2 + "”； ";
			}
		}

		if (citizen.getMarriager().toString() != "" && dtoIdt.getDtocitizen().getMarriager().toString() != ""
				&& citizen.getMarriager() != null && dtoIdt.getDtocitizen().getMarriager() != null) {
			if (!citizen.getMarriager().equals(dtoIdt.getDtocitizen().getMarriager())) {
				String str1 = dCodeService.findByCodeAndTypeCode(citizen.getMarriager().toString(), "d_marriage_state")
						.getDescription();
				String str2 = dCodeService
						.findByCodeAndTypeCode(dtoIdt.getDtocitizen().getMarriager().toString(), "d_marriage_state")
						.getDescription();
				logData += "[婚姻状况]：由 “" + str1 + "” 修改为  “" + str2 + "”； ";
			}
		}

		if (citizen.getPolitical().toString() != "" && dtoIdt.getDtocitizen().getPolitical().toString() != ""
				&& citizen.getPolitical() != null && dtoIdt.getDtocitizen().getPolitical() != null) {
			if (!citizen.getPolitical().equals(dtoIdt.getDtocitizen().getPolitical())) {
				String str1 = dCodeService.findByCodeAndTypeCode(citizen.getPolitical().toString(), "d_political")
						.getDescription();
				String str2 = dCodeService
						.findByCodeAndTypeCode(dtoIdt.getDtocitizen().getPolitical().toString(), "d_political")
						.getDescription();
				logData += "[政治面貌]：由 “" + str1 + "” 修改为  “" + str2 + "”； ";
			}
		}

		if (citizen.getHukouKind().toString() != "" && dtoIdt.getDtocitizen().getHukouKind().toString() != ""
				&& citizen.getHukouKind() != null && dtoIdt.getDtocitizen().getHukouKind() != null) {
			if (!citizen.getHukouKind().equals(dtoIdt.getDtocitizen().getHukouKind())) {
				String str1 = dCodeService.findByCodeAndTypeCode(citizen.getHukouKind().toString(), "d_hukou_type")
						.getDescription();
				String str2 = dCodeService
						.findByCodeAndTypeCode(dtoIdt.getDtocitizen().getHukouKind().toString(), "d_hukou_type")
						.getDescription();
				logData += "[户口类别]：由 “" + str1 + "” 修改为  “" + str2 + "”； ";
			}
		}

		if (citizen.getMobilePhone() != "" && dtoIdt.getDtocitizen().getMobilePhone() != ""
				&& citizen.getMobilePhone() != null && dtoIdt.getDtocitizen().getMobilePhone() != null) {
			if (!citizen.getMobilePhone().equals(dtoIdt.getDtocitizen().getMobilePhone())) {
				logData += "[移动电话]：由 “" + citizen.getMobilePhone() + "” 修改为  “"
						+ dtoIdt.getDtocitizen().getMobilePhone() + "”； ";
			}
		}

		if (citizen.getPhoneNo() != "" && dtoIdt.getDtocitizen().getPhoneNo() != "" && citizen.getPhoneNo() != null
				&& dtoIdt.getDtocitizen().getPhoneNo() != null) {
			if (!citizen.getPhoneNo().equals(dtoIdt.getDtocitizen().getPhoneNo())) {
				logData += "[固定电话]：由 “" + citizen.getPhoneNo() + "” 修改为  “" + dtoIdt.getDtocitizen().getPhoneNo()
						+ "”； ";
			}
		}

		if (citizen.getEmail() != "" && dtoIdt.getDtocitizen().getEmail() != "" && citizen.getEmail() != null
				&& dtoIdt.getDtocitizen().getEmail() != null) {
			if (!citizen.getEmail().equals(dtoIdt.getDtocitizen().getEmail())) {
				logData += "[电子邮箱]：由 “" + citizen.getEmail() + "” 修改为  “" + dtoIdt.getDtocitizen().getEmail() + "”； ";
			}
		}

		if (citizen.getZipcode() != "" && dtoIdt.getDtocitizen().getZipcode() != "" && citizen.getZipcode() != null
				&& dtoIdt.getDtocitizen().getZipcode() != null) {
			if (!citizen.getZipcode().equals(dtoIdt.getDtocitizen().getZipcode())) {
				logData += "[户籍地邮政编码]：由 “" + citizen.getZipcode() + "” 修改为  “" + dtoIdt.getDtocitizen().getZipcode()
						+ "”； ";
			}
		}

		if (citizen.getResidentZipcode() != "" && dtoIdt.getDtocitizen().getResidentZipcode() != ""
				&& citizen.getResidentZipcode() != null && dtoIdt.getDtocitizen().getResidentZipcode() != null) {
			if (!citizen.getResidentZipcode().equals(dtoIdt.getDtocitizen().getResidentZipcode())) {
				logData += "[居住地邮政编码]：由 “" + citizen.getResidentZipcode() + "” 修改为  “"
						+ dtoIdt.getDtocitizen().getResidentZipcode() + "”； ";
			}
		}

		if (citizen.getCityid() != "" && dtoIdt.getDtocitizen().getCityid() != "" && citizen.getCityid() != null
				&& dtoIdt.getDtocitizen().getCityid() != null) {
			if (!citizen.getCityid().equals(dtoIdt.getDtocitizen().getCityid())) {
				String str1 = getHjd(citizen.getCityid());
				String str2 = getHjd(dtoIdt.getDtocitizen().getCityid());
				logData += "[户籍地]：由 “" + str1 + "” 修改为  “" + str2 + "”； ";
			}
		}

		if (citizen.getCityidAddrstr() != "" && dtoIdt.getDtocitizen().getCityidAddrstr() != ""
				&& citizen.getCityidAddrstr() != null && dtoIdt.getDtocitizen().getCityidAddrstr() != null) {
			if (!citizen.getCityidAddrstr().equals(dtoIdt.getDtocitizen().getCityidAddrstr())) {
				logData += "[户籍地地址]：由 “" + citizen.getCityidAddrstr() + "” 修改为  “"
						+ dtoIdt.getDtocitizen().getCityidAddrstr() + "”； ";
			}
		}

		if (citizen.getResidentCity() != "" && dtoIdt.getDtocitizen().getResidentCity() != ""
				&& citizen.getResidentCity() != null && dtoIdt.getDtocitizen().getResidentCity() != null) {
			if (!citizen.getResidentCity().equals(dtoIdt.getDtocitizen().getResidentCity())) {
				String str1 = getHjd(citizen.getResidentCity());
				String str2 = getHjd(dtoIdt.getDtocitizen().getResidentCity());
				logData += "[居住地]：由 “" + str1 + "” 修改为  “" + str2 + "”； ";
			}
		}

		if (citizen.getResidentcityAddrstr() != "" && dtoIdt.getDtocitizen().getResidentcityAddrstr() != ""
				&& citizen.getResidentcityAddrstr() != null
				&& dtoIdt.getDtocitizen().getResidentcityAddrstr() != null) {
			if (!citizen.getResidentcityAddrstr().equals(dtoIdt.getDtocitizen().getResidentcityAddrstr())) {
				logData += "[居住地地址]：由 “" + citizen.getResidentcityAddrstr() + "” 修改为  “"
						+ dtoIdt.getDtocitizen().getResidentcityAddrstr() + "”； ";
			}
		}

		if (citizen.getGuardianOrAgent().toString() != ""
				&& dtoIdt.getDtocitizen().getGuardianOrAgent().toString() != "" && citizen.getGuardianOrAgent() != null
				&& dtoIdt.getDtocitizen().getGuardianOrAgent() != null) {
			if (!citizen.getGuardianOrAgent().equals(dtoIdt.getDtocitizen().getGuardianOrAgent())) {
				String str1 = dCodeService
						.findByCodeAndTypeCode(citizen.getGuardianOrAgent().toString(), "d_guardiantype")
						.getDescription();
				String str2 = dCodeService
						.findByCodeAndTypeCode(dtoIdt.getDtocitizen().getGuardianOrAgent().toString(), "d_guardiantype")
						.getDescription();
				logData += "[联系人类型]：由 “" + str1 + "” 修改为  “" + str2 + "”； ";
			}
		}

		if (citizen.getGuardianGender().toString() != "" && dtoIdt.getDtocitizen().getGuardianGender().toString() != ""
				&& citizen.getGuardianGender() != null && dtoIdt.getDtocitizen().getGuardianGender() != null) {
			if (!citizen.getGuardianGender().equals(dtoIdt.getDtocitizen().getGuardianGender())) {
				String str1 = dCodeService.findByCodeAndTypeCode(citizen.getGuardianGender().toString(), "d_gender")
						.getDescription();
				String str2 = dCodeService
						.findByCodeAndTypeCode(dtoIdt.getDtocitizen().getGuardianGender().toString(), "d_gender")
						.getDescription();
				logData += "[联系人性别]：由 “" + str1 + "” 修改为  “" + str2 + "”； ";
			}
		}

		if (citizen.getGuardianRelation().toString() != ""
				&& dtoIdt.getDtocitizen().getGuardianRelation().toString() != ""
				&& citizen.getGuardianRelation() != null && dtoIdt.getDtocitizen().getGuardianRelation() != null) {
			if (!citizen.getGuardianRelation().equals(dtoIdt.getDtocitizen().getGuardianRelation())) {
				String str1 = dCodeService
						.findByCodeAndTypeCode(citizen.getGuardianRelation().toString(), "d_guardian_r")
						.getDescription();
				String str2 = dCodeService
						.findByCodeAndTypeCode(dtoIdt.getDtocitizen().getGuardianRelation().toString(), "d_guardian_r")
						.getDescription();
				logData += "[与联系人关系]：由 “" + str1 + "” 修改为  “" + str2 + "”； ";
			}
		}

		if (citizen.getGuardian() != "" && dtoIdt.getDtocitizen().getGuardian() != "" && citizen.getGuardian() != null
				&& dtoIdt.getDtocitizen().getGuardian() != null) {
			if (!citizen.getGuardian().equals(dtoIdt.getDtocitizen().getGuardian())) {
				logData += "[联系人姓名]：由 “" + citizen.getGuardian() + "” 修改为  “" + dtoIdt.getDtocitizen().getGuardian()
						+ "”； ";
			}
		}

		if (citizen.getGuardianCitizenId() != "" && dtoIdt.getDtocitizen().getGuardianCitizenId() != ""
				&& citizen.getGuardianCitizenId() != null && dtoIdt.getDtocitizen().getGuardianCitizenId() != null) {
			if (!citizen.getGuardianCitizenId().equals(dtoIdt.getDtocitizen().getGuardianCitizenId())) {
				logData += "[联系人身份证号]：由 “" + citizen.getGuardianCitizenId() + "” 修改为  “"
						+ dtoIdt.getDtocitizen().getGuardianCitizenId() + "”； ";
			}
		}

		if (citizen.getGuardianMobilePhone() != "" && dtoIdt.getDtocitizen().getGuardianMobilePhone() != ""
				&& citizen.getGuardianMobilePhone() != null
				&& dtoIdt.getDtocitizen().getGuardianMobilePhone() != null) {
			if (!citizen.getGuardianMobilePhone().equals(dtoIdt.getDtocitizen().getGuardianMobilePhone())) {
				logData += "[联系人移动电话]：由 “" + citizen.getGuardianMobilePhone() + "” 修改为  “"
						+ dtoIdt.getDtocitizen().getGuardianMobilePhone() + "”； ";
			}
		}

		if (citizen.getGuardianPhone() != "" && dtoIdt.getDtocitizen().getGuardianPhone() != ""
				&& citizen.getGuardianPhone() != null && dtoIdt.getDtocitizen().getGuardianPhone() != null) {
			if (!citizen.getGuardianPhone().equals(dtoIdt.getDtocitizen().getGuardianPhone())) {
				logData += "[联系人固定电话]：由 “" + citizen.getGuardianPhone() + "” 修改为  “"
						+ dtoIdt.getDtocitizen().getGuardianPhone() + "”； ";
			}
		}

		return logData;
	}

	public String getHjd(String cityid) {
		String hjd = "";
		if (cityid.length() == 6) {
			hjd += areaRepository.findByAreaCode(cityid).getAreaName();
		} else if (cityid.length() == 9) {
			hjd += areaRepository.findByAreaCode(cityid.substring(0, 6)).getAreaName();
			hjd += areaRepository.findByAreaCode(cityid).getAreaName();
		} else if (cityid.length() == 12) {
			hjd += areaRepository.findByAreaCode(cityid.substring(0, 6)).getAreaName();
			hjd += areaRepository.findByAreaCode(cityid.substring(0, 9)).getAreaName();
			hjd += areaRepository.findByAreaCode(cityid).getAreaName();
		}
		return hjd;
	}

}

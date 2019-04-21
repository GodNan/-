package com.css.bdpfnew.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import com.css.bdpfnew.model.dto.DtoIdt;
import com.css.bdpfnew.model.entity.bdpfnew.*;
import com.css.bdpfnew.model.vo.query.VoCredentials;
import com.css.bdpfnew.repository.*;
import com.css.bdpfnew.service.CdpfLogService;
import com.css.bdpfnew.service.DCodeService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.constant.Constants;
import com.css.bdpfnew.model.dto.DtoCitizen;
import com.css.bdpfnew.repository.task.RequestNoteRepository;
import com.css.bdpfnew.repository.task.RequestRepository;
import com.css.bdpfnew.service.CitizenService;
import com.css.bdpfnew.util.UuidUtil;

/**
 * @Author erDuo
 * @Date 2018年6月22日 上午10:33:17
 * @Description:
 */

@Service
public class CitizenServiceImpl extends BaseServiceImpl<CdpfCitizen, Long> implements CitizenService {
	@Autowired
	private AreaRepository areaRepository;
	@Autowired
	private CdpfLogService cdpfLogService;
	@Autowired
	private DCodeService dCodeService;
	@Autowired
	private CitizenNetRepository citizenNetRepository;

	@Autowired
	private CitizenRepository citizenRepository;
	@Autowired
	private ExcelRepository excelRepository;
	@Autowired
	private RequestRepository requestRepository;
	@Autowired
	private RequestNoteRepository requestNoteRepository;
	@Autowired
	private RequestStakeholderRepository requestStakeholderRepository;
	@Autowired
	private CdpfPhotoRepository cdpfPhotoRepository;

	@Autowired
	public void setBaseDao(CitizenRepository citizenRepository) {
		super.setBaseDao(citizenRepository);
	}

	@Override
	public CdpfCitizen findByUuid(String uuid) {
		return citizenRepository.findByUuid(uuid);
	}

	@Override
	@Transactional
	public String save(DtoCitizen dtoCitizen) {
		SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);

		String cdpfId = UuidUtil.getUuid();

		// 照片信息
		CdpfPhoto photo = new CdpfPhoto();
		photo.setCdpfid(cdpfId);
		photo.setCitizenId(dtoCitizen.getCitizenId());
		photo.setIsHulian(new Integer(0));
		photo = cdpfPhotoRepository.save(photo);

		// TODO: 将此部分 对 request/request_note/request_stakeholder的 操作 提出到
		// serviceimpl层
		// 新增残疾人,默认发起了 "新证申请" 流程
		Request request = new Request();
		request.setCdpfId(cdpfId);
		request.setCitizenId(dtoCitizen.getCitizenId());
		request.setCitizenName(dtoCitizen.getName());
		request.setCityid(dtoCitizen.getCityid());
		request.setCurrentState(Constants.HANDLE_ZW);
		request.setFormerState(Constants.NEW);
		request.setProcessId(new Integer(1));
		request.setTitle("新证申请");
		request.setUserId(user.getUuid());
		request.setUserName(user.getNickname());
		request = requestRepository.save(request);

		// TODO: 默认对应 新证申请 第一步 "录入" 的 request_note
		// select
		// n.request_id,n.user_name,n.latest,n.handle_state,n.finish_state,n.note,n.handle_type
		// from request_note n;
		RequestNote requestNote = new RequestNote();
		requestNote.setRequestId(request.getUuid());
		requestNote.setUserId(user.getUuid());
		requestNote.setUserName(user.getNickname());
		requestNote.setHandleState(request.getFormerState());
		requestNote.setFinishState(request.getCurrentState());
		requestNote.setNote("录入完成进入核验");
		requestNote.setHandleType(new Integer(1));
		requestNoteRepository.save(requestNote);

		// TODO: 默认对应 新证申请 第一步 "录入" 的 request_stakeholder
		// select h.request_id,h.cityid,h.permission_id from request_stakeholder
		// h;
		RequestStakeholder requestStakeholder = new RequestStakeholder();
		requestStakeholder.setRequestId(request.getUuid());
		requestStakeholder.setCityid(dtoCitizen.getCityid());
		requestStakeholder.setPermissionId("handle");
		requestStakeholderRepository.save(requestStakeholder);

		CdpfCitizen citizen = new CdpfCitizen();
		BeanUtils.copyProperties(dtoCitizen, citizen);
		citizen.setUuid(cdpfId);
		citizen.setRequestId(request.getUuid());
		citizen.setPhotoid(photo.getUuid());
		citizen = citizenRepository.save(citizen);

		System.out.println("cdpfId:" + cdpfId);
		System.out.println("citizen.getUuid():" + citizen.getUuid());

		return request.getUuid() + ";" + citizen.getUuid();
	}

	@Override
	@Transactional
	public String update(DtoCitizen dtoCitizen) {
		CdpfCitizen citizen = citizenRepository.findByUuid(dtoCitizen.getUuid());
		CdpfCitizenNet citizenNet = citizenNetRepository.findByUuid(dtoCitizen.getUuid());
		//通过比较得到基本信息不同的信息
		DtoIdt dtoIdt=new DtoIdt();
		dtoIdt.setDtocitizen(dtoCitizen);
		String logData = getCompareResult(citizen, dtoIdt);

		BeanUtils.copyProperties(dtoCitizen, citizen, new String[] { "uuid" });
		citizen.setHlSynced(0);
		citizen = citizenRepository.save(citizen);
		if(citizenNet != null){
			BeanUtils.copyProperties(dtoCitizen, citizenNet, new String[] { "uuid" });
			citizenNetRepository.save(citizenNet);
		}
		//操作日志
		String cdpfIdStr = citizen.getUuid();
		String requestIdStr = citizen.getRequestId();
		Integer logType = new Integer(96);
		Integer opertype = new Integer(2);
		cdpfLogService.create(cdpfIdStr, requestIdStr, logData, logType, opertype);

		System.out.println("update - citizen.getUuid():" + citizen.getUuid());
		return citizen.getUuid();
	}

	@Override
	public List<CdpfCitizen> findByCityidLike(String cityid) {
		List<CdpfCitizen> list = citizenRepository.findByCityidLike(cityid);
		return list;
	}

	@Override
	public List<Object[]> findByCredentials(VoCredentials credentials) {
		return excelRepository.findByCredentials(credentials);
	}


	public String getCompareResult(CdpfCitizen citizen, DtoIdt dtoIdt){

		String logData = "";

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		if (citizen.getName() != "" && dtoIdt.getDtocitizen().getName() != "" && citizen.getName() != null && dtoIdt.getDtocitizen().getName() != null) {
			if (!citizen.getName().equals(dtoIdt.getDtocitizen().getName())) {
				logData += "[姓名]：由 “" + citizen.getName() + "” 修改为  “" + dtoIdt.getDtocitizen().getName() + "”； ";
			}
		}

		if (citizen.getCitizenId() != "" && dtoIdt.getDtocitizen().getCitizenId() != "" && citizen.getCitizenId() != null && dtoIdt.getDtocitizen().getCitizenId() != null) {
			if (!citizen.getCitizenId().equals(dtoIdt.getDtocitizen().getCitizenId())) {
				logData += "[身份证号]：由 “" + citizen.getCitizenId() + "” 修改为  “" + dtoIdt.getDtocitizen().getCitizenId() + "”； ";
			}
		}

		if (citizen.getBirthdate().toString() != "" && dtoIdt.getDtocitizen().getBirthdate().toString() != "" && citizen.getBirthdate() != null && dtoIdt.getDtocitizen().getBirthdate() != null) {
			if (!citizen.getBirthdate().toString().equals(formatter.format(dtoIdt.getDtocitizen().getBirthdate()))) {
				String dateString = formatter.format(dtoIdt.getDtocitizen().getBirthdate());
				logData += "[出生年月]：由 “" + citizen.getBirthdate() + "” 修改为  “" + dateString + "”； ";
			}
		}

		if (citizen.getGender().toString() != "" && dtoIdt.getDtocitizen().getGender().toString() != "" && citizen.getGender() != null && dtoIdt.getDtocitizen().getGender() != null) {
			if (!citizen.getGender().equals(dtoIdt.getDtocitizen().getGender())) {
				String str1 = dCodeService.findByCodeAndTypeCode(citizen.getGender().toString(), "d_gender").getDescription();
				String str2 = dCodeService.findByCodeAndTypeCode(dtoIdt.getDtocitizen().getGender().toString(), "d_gender").getDescription();
				logData += "[性别]：由 “" + str1 + "” 修改为  “" + str2 + "”； ";
			}
		}

		if (citizen.getRace().toString() != "" && dtoIdt.getDtocitizen().getRace().toString() != "" && citizen.getRace() != null && dtoIdt.getDtocitizen().getRace() != null) {
			if (!citizen.getRace().equals(dtoIdt.getDtocitizen().getRace())) {
				String str1 = dCodeService.findByCodeAndTypeCode(citizen.getRace().toString(), "d_race").getDescription();
				String str2 = dCodeService.findByCodeAndTypeCode(dtoIdt.getDtocitizen().getRace().toString(), "d_race").getDescription();
				logData += "[民族]：由 “" + str1 + "” 修改为  “" + str2 + "”； ";
			}
		}

		if (citizen.getEduLevel().toString() != "" && dtoIdt.getDtocitizen().getEduLevel().toString() != "" && citizen.getEduLevel() != null && dtoIdt.getDtocitizen().getEduLevel() != null) {
			if (!citizen.getEduLevel().equals(dtoIdt.getDtocitizen().getEduLevel())) {
				String str1 = dCodeService.findByCodeAndTypeCode(citizen.getEduLevel().toString(), "d_education").getDescription();
				String str2 = dCodeService.findByCodeAndTypeCode(dtoIdt.getDtocitizen().getEduLevel().toString(), "d_education").getDescription();
				logData += "[文化程度]：由 “" + str1 + "” 修改为  “" + str2 + "”； ";
			}
		}

		if (citizen.getMarriager().toString() != "" && dtoIdt.getDtocitizen().getMarriager().toString() != "" && citizen.getMarriager() != null && dtoIdt.getDtocitizen().getMarriager() != null) {
			if (!citizen.getMarriager().equals(dtoIdt.getDtocitizen().getMarriager())) {
				String str1 = dCodeService.findByCodeAndTypeCode(citizen.getMarriager().toString(), "d_marriage_state").getDescription();
				String str2 = dCodeService.findByCodeAndTypeCode(dtoIdt.getDtocitizen().getMarriager().toString(), "d_marriage_state").getDescription();
				logData += "[婚姻状况]：由 “" + str1 + "” 修改为  “" + str2 + "”； ";
			}
		}

		if (citizen.getPolitical().toString() != "" && dtoIdt.getDtocitizen().getPolitical().toString() != "" && citizen.getPolitical() != null && dtoIdt.getDtocitizen().getPolitical() != null) {
			if (!citizen.getPolitical().equals(dtoIdt.getDtocitizen().getPolitical())) {
				String str1 = dCodeService.findByCodeAndTypeCode(citizen.getPolitical().toString(), "d_political").getDescription();
				String str2 = dCodeService.findByCodeAndTypeCode(dtoIdt.getDtocitizen().getPolitical().toString(), "d_political").getDescription();
				logData += "[政治面貌]：由 “" + str1 + "” 修改为  “" + str2 + "”； ";
			}
		}

		if (citizen.getHukouKind().toString() != "" && dtoIdt.getDtocitizen().getHukouKind().toString() != "" && citizen.getHukouKind() != null && dtoIdt.getDtocitizen().getHukouKind() != null) {
			if (!citizen.getHukouKind().equals(dtoIdt.getDtocitizen().getHukouKind())) {
				String str1 = dCodeService.findByCodeAndTypeCode(citizen.getHukouKind().toString(), "d_hukou_type").getDescription();
				String str2 = dCodeService.findByCodeAndTypeCode(dtoIdt.getDtocitizen().getHukouKind().toString(), "d_hukou_type").getDescription();
				logData += "[户口类别]：由 “" + str1 + "” 修改为  “" + str2 + "”； ";
			}
		}

		if (citizen.getMobilePhone() != "" && dtoIdt.getDtocitizen().getMobilePhone() != "" && citizen.getMobilePhone() != null && dtoIdt.getDtocitizen().getMobilePhone() != null) {
			if (!citizen.getMobilePhone().equals(dtoIdt.getDtocitizen().getMobilePhone())) {
				logData += "[移动电话]：由 “" + citizen.getMobilePhone() + "” 修改为  “" + dtoIdt.getDtocitizen().getMobilePhone() + "”； ";
			}
		}

		if (citizen.getPhoneNo() != "" && dtoIdt.getDtocitizen().getPhoneNo() != "" && citizen.getPhoneNo() != null && dtoIdt.getDtocitizen().getPhoneNo() != null) {
			if (!citizen.getPhoneNo().equals(dtoIdt.getDtocitizen().getPhoneNo())) {
				logData += "[固定电话]：由 “" + citizen.getPhoneNo() + "” 修改为  “" + dtoIdt.getDtocitizen().getPhoneNo() + "”； ";
			}
		}

		if (citizen.getEmail() != "" && dtoIdt.getDtocitizen().getEmail() != "" && citizen.getEmail() != null && dtoIdt.getDtocitizen().getEmail() != null) {
			if (!citizen.getEmail().equals(dtoIdt.getDtocitizen().getEmail())) {
				logData += "[电子邮箱]：由 “" + citizen.getEmail() + "” 修改为  “" + dtoIdt.getDtocitizen().getEmail() + "”； ";
			}
		}

		if (citizen.getZipcode() != "" && dtoIdt.getDtocitizen().getZipcode() != "" && citizen.getZipcode() != null && dtoIdt.getDtocitizen().getZipcode() != null) {
			if (!citizen.getZipcode().equals(dtoIdt.getDtocitizen().getZipcode())) {
				logData += "[户籍地邮政编码]：由 “" + citizen.getZipcode() + "” 修改为  “" + dtoIdt.getDtocitizen().getZipcode() + "”； ";
			}
		}

		if (citizen.getResidentZipcode() != "" && dtoIdt.getDtocitizen().getResidentZipcode() != "" && citizen.getResidentZipcode() != null && dtoIdt.getDtocitizen().getResidentZipcode() != null) {
			if (!citizen.getResidentZipcode().equals(dtoIdt.getDtocitizen().getResidentZipcode())) {
				logData += "[居住地邮政编码]：由 “" + citizen.getResidentZipcode() + "” 修改为  “" + dtoIdt.getDtocitizen().getResidentZipcode() + "”； ";
			}
		}

		if (citizen.getCityid() != "" && dtoIdt.getDtocitizen().getCityid() != "" && citizen.getCityid() != null && dtoIdt.getDtocitizen().getCityid() != null) {
			if (!citizen.getCityid().equals(dtoIdt.getDtocitizen().getCityid())) {
				String str1 = getHjd(citizen.getCityid());
				String str2 = getHjd(dtoIdt.getDtocitizen().getCityid());
				logData += "[户籍地]：由 “" + str1 + "” 修改为  “" + str2 + "”； ";
			}
		}

		if (citizen.getCityidAddrstr() != "" && dtoIdt.getDtocitizen().getCityidAddrstr() != "" && citizen.getCityidAddrstr() != null && dtoIdt.getDtocitizen().getCityidAddrstr() != null) {
			if (!citizen.getCityidAddrstr().equals(dtoIdt.getDtocitizen().getCityidAddrstr())) {
				logData += "[户籍地地址]：由 “" + citizen.getCityidAddrstr() + "” 修改为  “" + dtoIdt.getDtocitizen().getCityidAddrstr() + "”； ";
			}
		}

		if (citizen.getResidentCity() != "" && dtoIdt.getDtocitizen().getResidentCity() != "" && citizen.getResidentCity() != null && dtoIdt.getDtocitizen().getResidentCity() != null) {
			if (!citizen.getResidentCity().equals(dtoIdt.getDtocitizen().getResidentCity())) {
				String str1 = getHjd(citizen.getResidentCity());
				String str2 = getHjd(dtoIdt.getDtocitizen().getResidentCity());
				logData += "[居住地]：由 “" + str1 + "” 修改为  “" + str2 + "”； ";
			}
		}

		if (citizen.getResidentcityAddrstr() != "" && dtoIdt.getDtocitizen().getResidentcityAddrstr() != "" && citizen.getResidentcityAddrstr() != null && dtoIdt.getDtocitizen().getResidentcityAddrstr() != null) {
			if (!citizen.getResidentcityAddrstr().equals(dtoIdt.getDtocitizen().getResidentcityAddrstr())) {
				logData += "[居住地地址]：由 “" + citizen.getResidentcityAddrstr() + "” 修改为  “" + dtoIdt.getDtocitizen().getResidentcityAddrstr() + "”； ";
			}
		}

		if (citizen.getGuardianOrAgent().toString() != "" && dtoIdt.getDtocitizen().getGuardianOrAgent().toString() != "" && citizen.getGuardianOrAgent() != null && dtoIdt.getDtocitizen().getGuardianOrAgent() != null) {
			if (!citizen.getGuardianOrAgent().equals(dtoIdt.getDtocitizen().getGuardianOrAgent())) {
				String str1 = dCodeService.findByCodeAndTypeCode(citizen.getGuardianOrAgent().toString(), "d_guardiantype").getDescription();
				String str2 = dCodeService.findByCodeAndTypeCode(dtoIdt.getDtocitizen().getGuardianOrAgent().toString(), "d_guardiantype").getDescription();
				logData += "[联系人类型]：由 “" + str1 + "” 修改为  “" + str2 + "”； ";
			}
		}

		if (citizen.getGuardianGender().toString() != "" && dtoIdt.getDtocitizen().getGuardianGender().toString() != "" && citizen.getGuardianGender() != null && dtoIdt.getDtocitizen().getGuardianGender() != null) {
			if (!citizen.getGuardianGender().equals(dtoIdt.getDtocitizen().getGuardianGender())) {
				String str1 = dCodeService.findByCodeAndTypeCode(citizen.getGuardianGender().toString(), "d_gender").getDescription();
				String str2 = dCodeService.findByCodeAndTypeCode(dtoIdt.getDtocitizen().getGuardianGender().toString(), "d_gender").getDescription();
				logData += "[联系人性别]：由 “" + str1 + "” 修改为  “" + str2 + "”； ";
			}
		}

		if (citizen.getGuardianRelation().toString() != "" && dtoIdt.getDtocitizen().getGuardianRelation().toString() != "" && citizen.getGuardianRelation() != null && dtoIdt.getDtocitizen().getGuardianRelation() != null) {
			if (!citizen.getGuardianRelation().equals(dtoIdt.getDtocitizen().getGuardianRelation())) {
				String str1 = dCodeService.findByCodeAndTypeCode(citizen.getGuardianRelation().toString(), "d_guardian_r").getDescription();
				String str2 = dCodeService.findByCodeAndTypeCode(dtoIdt.getDtocitizen().getGuardianRelation().toString(), "d_guardian_r").getDescription();
				logData += "[与联系人关系]：由 “" + str1 + "” 修改为  “" + str2 + "”； ";
			}
		}

		if (citizen.getGuardian() != "" && dtoIdt.getDtocitizen().getGuardian() != "" && citizen.getGuardian() != null && dtoIdt.getDtocitizen().getGuardian() != null) {
			if (!citizen.getGuardian().equals(dtoIdt.getDtocitizen().getGuardian())) {
				logData += "[联系人姓名]：由 “" + citizen.getGuardian() + "” 修改为  “" + dtoIdt.getDtocitizen().getGuardian() + "”； ";
			}
		}

		if (citizen.getGuardianCitizenId() != "" && dtoIdt.getDtocitizen().getGuardianCitizenId() != "" && citizen.getGuardianCitizenId() != null && dtoIdt.getDtocitizen().getGuardianCitizenId() != null) {
			if (!citizen.getGuardianCitizenId().equals(dtoIdt.getDtocitizen().getGuardianCitizenId())) {
				logData += "[联系人身份证号]：由 “" + citizen.getGuardianCitizenId() + "” 修改为  “" + dtoIdt.getDtocitizen().getGuardianCitizenId() + "”； ";
			}
		}

		if (citizen.getGuardianMobilePhone() != "" && dtoIdt.getDtocitizen().getGuardianMobilePhone() != "" && citizen.getGuardianMobilePhone() != null && dtoIdt.getDtocitizen().getGuardianMobilePhone() != null) {
			if (!citizen.getGuardianMobilePhone().equals(dtoIdt.getDtocitizen().getGuardianMobilePhone())) {
				logData += "[联系人移动电话]：由 “" + citizen.getGuardianMobilePhone() + "” 修改为  “" + dtoIdt.getDtocitizen().getGuardianMobilePhone() + "”； ";
			}
		}

		if (citizen.getGuardianPhone() != "" && dtoIdt.getDtocitizen().getGuardianPhone() != "" && citizen.getGuardianPhone() != null && dtoIdt.getDtocitizen().getGuardianPhone() != null) {
			if (!citizen.getGuardianPhone().equals(dtoIdt.getDtocitizen().getGuardianPhone())) {
				logData += "[联系人固定电话]：由 “" + citizen.getGuardianPhone() + "” 修改为  “" + dtoIdt.getDtocitizen().getGuardianPhone() + "”； ";
			}
		}

		return logData;
	}

	public String getHjd(String cityid){
		String hjd = "";
		if(cityid.length() == 6){
			hjd +=  areaRepository.findByAreaCode(cityid).getAreaName();
		}else if(cityid.length() == 9){
			hjd +=  areaRepository.findByAreaCode(cityid.substring(0, 6)).getAreaName();
			hjd +=  areaRepository.findByAreaCode(cityid).getAreaName();
		}else if(cityid.length() == 12){
			hjd +=  areaRepository.findByAreaCode(cityid.substring(0, 6)).getAreaName();
			hjd +=  areaRepository.findByAreaCode(cityid.substring(0, 9)).getAreaName();
			hjd +=  areaRepository.findByAreaCode(cityid).getAreaName();
		}
		return hjd;
	}


}

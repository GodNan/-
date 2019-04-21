package com.css.bdpfnew.service.impl.idt;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.constant.Constants;
import com.css.bdpfnew.model.dto.DtoIdt;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfBody;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizen;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfEar;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfEye;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfGonganPara;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfIq;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfMental;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfSpeech;
import com.css.bdpfnew.model.entity.bdpfnew.IdtResult;
import com.css.bdpfnew.model.entity.bdpfnew.Request;
import com.css.bdpfnew.model.entity.bdpfnew.RequestNote;
import com.css.bdpfnew.model.entity.bdpfnew.RequestStakeholder;
import com.css.bdpfnew.model.entity.bdpfnew.SysUser;
import com.css.bdpfnew.repository.AreaRepository;
import com.css.bdpfnew.repository.CdpfGonganParaRepository;
import com.css.bdpfnew.repository.CitizenRepository;
import com.css.bdpfnew.repository.RequestStakeholderRepository;
import com.css.bdpfnew.repository.idt.BodyRepository;
import com.css.bdpfnew.repository.idt.EarRepository;
import com.css.bdpfnew.repository.idt.EyeRepository;
import com.css.bdpfnew.repository.idt.IdtResultRepository;
import com.css.bdpfnew.repository.idt.IqRepository;
import com.css.bdpfnew.repository.idt.MentalRepository;
import com.css.bdpfnew.repository.idt.SpeechRepository;
import com.css.bdpfnew.repository.task.RequestNoteRepository;
import com.css.bdpfnew.repository.task.RequestRepository;
import com.css.bdpfnew.service.CdpfLogService;
import com.css.bdpfnew.service.DCodeService;
import com.css.bdpfnew.service.idt.KsqrService;
import com.css.bdpfnew.util.HttpClientUtil;
import com.css.bdpfnew.util.RecordUtil;

/**
 * @Author HEYCH
 * @Date 2018年11月21日 下午13:58:20
 * @Description:
 */
@Service
public class KsqrServiceImpl implements KsqrService {

	private CitizenRepository citizenRepository;
	private EyeRepository eyeRepository;
	private EarRepository earRepository;
	private SpeechRepository speechRepository;
	private BodyRepository bodyRepository;
	private IqRepository iqRepository;
	private MentalRepository mentalRepository;
	private IdtResultRepository idtResultRepository;
	private RequestRepository requestRepository;
	private RequestNoteRepository requestNoteRepository;
	private RequestStakeholderRepository requestStakeholderRepository;
    private CdpfGonganParaRepository cdpfGonganParaRepository;
    private AreaRepository areaRepository;
	private DtoIdt dtoIdt;
	private String requestId;
	private String cdpfId;
	private Map<Integer, Integer> addMap = null;
	private Map<Integer, Integer> delMap = null;
	private Map<Integer, Integer> updateMap = null;
	@Autowired
	private CdpfLogService cdpfLogService;
	@Autowired
    private DCodeService dCodeService;
	
	@Autowired
	public KsqrServiceImpl(CitizenRepository citizenRepository, EyeRepository eyeRepository,
			EarRepository earRepository, SpeechRepository speechRepository, BodyRepository bodyRepository,
			IqRepository iqRepository, MentalRepository mentalRepository, IdtResultRepository idtResultRepository,
			RequestRepository requestRepository, RequestNoteRepository requestNoteRepository,
			RequestStakeholderRepository requestStakeholderRepository, CdpfGonganParaRepository cdpfGonganParaRepository,
			AreaRepository areaRepository) {
		this.citizenRepository = citizenRepository;
		this.eyeRepository = eyeRepository;
		this.earRepository = earRepository;
		this.speechRepository = speechRepository;
		this.bodyRepository = bodyRepository;
		this.iqRepository = iqRepository;
		this.mentalRepository = mentalRepository;
		this.idtResultRepository = idtResultRepository;
		this.requestRepository = requestRepository;
		this.requestNoteRepository = requestNoteRepository;
		this.requestStakeholderRepository = requestStakeholderRepository;
		this.cdpfGonganParaRepository = cdpfGonganParaRepository;
		this.areaRepository = areaRepository;
	}

	@Override
	@Transactional
	public String update(DtoIdt dtoIdt) {
		try {
		// 残疾人基本信息
		CdpfCitizen citizen = citizenRepository.findByUuid(dtoIdt.getDtocitizen().getUuid());
		
		//通过比较得到基本信息不同的信息
		//String logData = getCompareResult(citizen, dtoIdt);
		String logData = "跨省迁入";
		
		BeanUtils.copyProperties(dtoIdt.getDtocitizen(), citizen, new String[] { "uuid" });
		citizen.setStatusRecord(new Integer(1));
		citizen = citizenRepository.save(citizen);
		
		//操作日志
		String cdpfIdStr = citizen.getUuid(); 
		String requestIdStr = citizen.getRequestId();
		Integer logType = new Integer(97);
		Integer opertype = new Integer(2);
		cdpfLogService.create(cdpfIdStr, requestIdStr, logData, logType, opertype);

		this.dtoIdt = dtoIdt;
		requestId = citizen.getRequestId();
		cdpfId = citizen.getUuid();

		IdtResult idtResult = idtResultRepository.findByRequestId(requestId);

		// 新旧 类别字符串
		String oldKindstr = "";
		String newKindstr = dtoIdt.getDtoresultX().getKindstr();
		// 评残结果信息
		if (idtResult != null) {
			oldKindstr = idtResult.getKindstr();
		} else {
			idtResult = new IdtResult();
		}
		if (dtoIdt.getDtoresultX().getIdtKind() != null) {
			BeanUtils.copyProperties(dtoIdt.getDtoresultX(), idtResult, new String[] { "uuid" });
			idtResult.setRequestId(requestId);
			idtResult.setCdpfId(cdpfId);
			idtResult.setHlSynced(new Integer(0));
			idtResult = idtResultRepository.save(idtResult);
		}

		RecordUtil recordUtil = new RecordUtil(oldKindstr, newKindstr);

		addMap = recordUtil.getAddMap();
		delMap = recordUtil.getDelMap();
		updateMap = recordUtil.getUpdateMap();
		delOper();
		addOper();
		updateOper();

		if (dtoIdt.getMin() == 0) {
			SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);

			Request request = requestRepository.findByUuid(citizen.getRequestId());
			if (request == null) {
				return null;
			}

			request.setCityid(citizen.getCityid()); 
			request.setCurrentState(Constants.CURRENT_STATE_AFTER_IDT.get(request.getProcessId()));
			request.setFormerState(Constants.FORMER_STATE_AFTER_IDT.get(request.getProcessId()));
			request.setOptionsApplyFor(dtoIdt.getOptionsApplyFor());
			request = requestRepository.save(request);

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
			requestNote.setNote(Constants.REQUESTNOTES_AFTER_IDT.get(request.getProcessId()));
			requestNote.setHandleType(new Integer(1));
			requestNoteRepository.save(requestNote);

			RequestStakeholder requestStakeholder = requestStakeholderRepository.findByRequestId(request.getUuid());
			if (requestStakeholder == null) {
				return null;
			}
			requestStakeholder.setCityid(citizen.getCityid());
			requestStakeholder.setPermissionId("firstReviewHL");
			requestStakeholderRepository.save(requestStakeholder);
		}
		System.out.println("dtoIdt.getMin(): " + dtoIdt.getMin());
		
		//第一步保存成功时通过接口反馈中残联修改此人为迁入过程中
		String timeStr = new java.text.SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		CdpfGonganPara cdpfGonganPara = cdpfGonganParaRepository.findByTimeStampLike(timeStr + "%");
		if (cdpfGonganPara == null) {
			System.out.println("未生成当天sessionId！");
			return null;
		}
		String sessionid = cdpfGonganPara.getSessionId();
		String timestamp = cdpfGonganPara.getTimeStamp();
		//String urlStr = "https://apidemo.cdpf.org.cn/tj/api"; //测试
		String urlStr = "http://59.255.6.134:8000/tj/api"; //正式
		Map<String, String> params = new HashMap<String, String>();
		params.put("method","IZcl.cjr.updateQCCjrState");
		params.put("idCard", citizen.getCitizenId());
		//params.put("syscode","gajk_bj"); //请求接口系统代码(测试)
		params.put("syscode","bj_gajk"); //请求接口系统代码(正式)
		params.put("sessionId",sessionid);//系统授权码
		params.put("timestamp",timestamp);//时间戳
		String jsonStr = HttpClientUtil.sendPostMessage(urlStr,params, "utf-8");
		JSONObject oo = new JSONObject(jsonStr);
		String res_state = oo.getString("res_state");
		if (!res_state.equals("1")) {
			System.out.println("更新迁入状态时出错，请联系管理人员！");
			return null;
		}

		return citizen.getRequestId();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void addOper() {
		if (addMap == null)
			return;
		Iterator<Integer> iter = addMap.values().iterator();
		while (iter.hasNext()) {
			Integer iVal = (Integer) iter.next();
			addOrUpdateRecord(iVal.intValue());
		}
	}

	public void delOper() {
		if (delMap == null)
			return;
		Iterator<Integer> iter = delMap.values().iterator();
		while (iter.hasNext()) {
			Integer iVal = (Integer) iter.next();
			delRecord(iVal.intValue());
		}
	}

	public void updateOper() {
		if (updateMap == null)
			return;
		Iterator<Integer> iter = updateMap.values().iterator();
		while (iter.hasNext()) {
			Integer iVal = (Integer) iter.next();
			addOrUpdateRecord(iVal.intValue());
		}
	}

	// TODO:
	// 1.获取当前的残疾类别信息
	// 2.获取之前的残疾类别信息
	// 3.比较
	// 3.1.之前没有现在有的 或 之前现在都有的: 新增/更新
	// 3.2.之前有的 现在没有的: 删除

	public void addOrUpdateRecord(int iVal) {
		switch (iVal) {
		case 1:
			CdpfEye eye = eyeRepository.findByRequestId(requestId);
			if (eye == null) {
				eye = new CdpfEye();
			}
			BeanUtils.copyProperties(dtoIdt.getDtoeye(), eye, new String[] { "uuid" });
			// TODO: request_id在后台更新还是在前台更新
			eye.setRequestId(requestId);
			eye.setCdpfId(cdpfId);
			eye = eyeRepository.save(eye);
			break;
		case 2:
			CdpfEar ear = earRepository.findByRequestId(requestId);
			if (ear == null) {
				ear = new CdpfEar();
			}
			BeanUtils.copyProperties(dtoIdt.getDtoear(), ear, new String[] { "uuid" });
			// TODO: request_id在后台更新还是在前台更新
			ear.setRequestId(requestId);
			ear.setCdpfId(cdpfId);
			ear = earRepository.save(ear);
			break;
		case 3:
			CdpfSpeech speech = speechRepository.findByRequestId(requestId);
			if (speech == null) {
				speech = new CdpfSpeech();
			}
			BeanUtils.copyProperties(dtoIdt.getDtospeech(), speech, new String[] { "uuid" });
			// TODO: request_id在后台更新还是在前台更新
			speech.setRequestId(requestId);
			speech.setCdpfId(cdpfId);
			speech = speechRepository.save(speech);
			break;
		case 4:
			CdpfBody body = bodyRepository.findByRequestId(requestId);
			if (body == null) {
				body = new CdpfBody();
			}
			BeanUtils.copyProperties(dtoIdt.getDtobody(), body, new String[] { "uuid" });
			// TODO: request_id在后台更新还是在前台更新
			body.setRequestId(requestId);
			body.setCdpfId(cdpfId);
			body = bodyRepository.save(body);
			break;
		case 5:
			CdpfIq iq = iqRepository.findByRequestId(requestId);
			if (iq == null) {
				iq = new CdpfIq();
			}
			BeanUtils.copyProperties(dtoIdt.getDtoiq(), iq, new String[] { "uuid" });
			// TODO: request_id在后台更新还是在前台更新
			iq.setRequestId(requestId);
			iq.setCdpfId(cdpfId);
			iq = iqRepository.save(iq);
			break;
		case 6:
			CdpfMental mental = mentalRepository.findByRequestId(requestId);
			if (mental == null) {
				mental = new CdpfMental();
			}
			BeanUtils.copyProperties(dtoIdt.getDtomental(), mental, new String[] { "uuid" });
			// TODO: request_id在后台更新还是在前台更新
			mental.setRequestId(requestId);
			mental.setCdpfId(cdpfId);
			mental = mentalRepository.save(mental);
			break;
		}
	}

	public void delRecord(int iVal) {
		switch (iVal) {
		case 1:
			CdpfEye eye = eyeRepository.findByRequestId(requestId);
			if (eye != null) {
				eyeRepository.delete(eye);
			}
			break;
		case 2:
			CdpfEar ear = earRepository.findByRequestId(requestId);
			if (ear != null) {
				earRepository.delete(ear);
			}
			break;
		case 3:
			CdpfSpeech speech = speechRepository.findByRequestId(requestId);
			if (speech != null) {
				speechRepository.delete(speech);
			}
			break;
		case 4:
			CdpfBody body = bodyRepository.findByRequestId(requestId);
			if (body != null) {
				bodyRepository.delete(body);
			}
			break;
		case 5:
			CdpfIq iq = iqRepository.findByRequestId(requestId);
			if (iq != null) {
				iqRepository.delete(iq);
			}
			break;
		case 6:
			CdpfMental mental = mentalRepository.findByRequestId(requestId);
			if (mental != null) {
				mentalRepository.delete(mental);
			}
			break;
		}

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

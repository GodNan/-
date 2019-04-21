package com.css.bdpfnew.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.hibernate.annotations.common.util.StringHelper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.bdpfnew.constant.Constants;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfBody;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCard;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizen;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizenKsqr;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfEar;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfEye;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfGonganPara;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfIq;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfMental;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfPhoto;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfSpeech;
import com.css.bdpfnew.model.entity.bdpfnew.IdtResult;
import com.css.bdpfnew.model.entity.bdpfnew.Request;
import com.css.bdpfnew.model.entity.bdpfnew.RequestNote;
import com.css.bdpfnew.model.entity.bdpfnew.RequestStakeholder;
import com.css.bdpfnew.model.entity.bdpfnew.SysUser;
import com.css.bdpfnew.repository.AreaRepository;
import com.css.bdpfnew.repository.CdpfGonganParaRepository;
import com.css.bdpfnew.repository.CdpfPhotoRepository;
import com.css.bdpfnew.repository.CitizenRepository;
import com.css.bdpfnew.repository.RequestStakeholderRepository;
import com.css.bdpfnew.repository.card.CdpfCardRepository;
import com.css.bdpfnew.repository.idt.BodyRepository;
import com.css.bdpfnew.repository.idt.EarRepository;
import com.css.bdpfnew.repository.idt.EyeRepository;
import com.css.bdpfnew.repository.idt.IdtResultRepository;
import com.css.bdpfnew.repository.idt.IqRepository;
import com.css.bdpfnew.repository.idt.MentalRepository;
import com.css.bdpfnew.repository.idt.SpeechRepository;
import com.css.bdpfnew.repository.task.RequestNoteRepository;
import com.css.bdpfnew.repository.task.RequestRepository;
import com.css.bdpfnew.service.KsqrJiekouService;
import com.css.bdpfnew.util.HttpClientUtil;
import com.css.bdpfnew.util.JsonUtil;
import com.css.bdpfnew.util.RecordUtil;
import com.css.bdpfnew.util.UuidUtil;

/**
 * @Author HEYCH
 * @Date 2018年11月21日 上午14:30:18
 * @Description:
 */

@Service
public class KsqrJiekouServiceImpl extends BaseServiceImpl<CdpfCitizen, Long> implements KsqrJiekouService {
	@Autowired
	private CitizenRepository citizenRepository;
	@Autowired
	private RequestRepository requestRepository;
	@Autowired
	private RequestNoteRepository requestNoteRepository;
	@Autowired
	private RequestStakeholderRepository requestStakeholderRepository;
	@Autowired
	private CdpfPhotoRepository cdpfPhotoRepository;
	@Autowired
	private IdtResultRepository idtResultRepository;
	@Autowired
	private EyeRepository eyeRepository;
	@Autowired
	private EarRepository earRepository;
	@Autowired
	private SpeechRepository speechRepository;
	@Autowired
	private BodyRepository bodyRepository;
	@Autowired
	private IqRepository iqRepository;
	@Autowired
	private MentalRepository mentalRepository;
	@Autowired
	private CdpfCardRepository cdpfCardRepository;
	@Autowired
    private AreaRepository areaRepository;
	@Autowired
    private CdpfGonganParaRepository cdpfGonganParaRepository;
	
	private Map<Integer, Integer> addMap = null;
	private Map<Integer, Integer> delMap = null;
	private String cdpfId;
	private String requestId;
	private String kindstr;
	private String requestId2;

	@Autowired
	public void setBaseDao(CitizenRepository citizenRepository) {
		super.setBaseDao(citizenRepository);
	}
	
	@Override
	public String getCitizenInfo(String citizenId, String citizenName) {
		
		try {
			String timeStr = new java.text.SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
			CdpfGonganPara cdpfGonganPara = cdpfGonganParaRepository.findByTimeStampLike(timeStr + "%");
			
			if (cdpfGonganPara == null) {
				System.out.println("未生成当天sessionId！");
				return "1002";
			}
			String sessionid = cdpfGonganPara.getSessionId();
			String timestamp = cdpfGonganPara.getTimeStamp();
			
			//String urlStr = "http://59.255.6.134:8001/tj/api";  //测试
			//String urlStr = "https://apidemo.cdpf.org.cn/tj/api";  //测试
			String urlStr = "http://59.255.6.134:8000/tj/api"; //正式
			Map<String, String> params = new HashMap<String, String>();
			params.put("method","IZcl.cjr.getQCCjrInfo");
			params.put("idCard", citizenId);
			params.put("name", citizenName);
			//params.put("syscode","gajk_bj"); //请求接口系统代码(测试)
			params.put("syscode","bj_gajk"); //请求接口系统代码(正式)
			params.put("sessionId",sessionid);//系统授权码
			params.put("timestamp",timestamp);//时间戳
			String jsonStr = HttpClientUtil.sendPostMessage(urlStr,params, "utf-8");
			JSONObject oo;
			oo = new JSONObject(jsonStr);
			String res_state = oo.getString("res_state");
			if (!res_state.equals("1")) {
				System.out.println("调取出错，请联系管理人员！");
				return "1000";
			}
			String res_data = oo.getJSONObject("res_data").toString();
		 	if (StringHelper.isEmpty(res_data.replace("{", "").replace("}", ""))) {
		 		System.out.println("未调取到残疾人数据！");
		 		return "1001";
			}
			String cjr = oo.getJSONObject("res_data").getJSONObject("cjr").toString();
			String cdpfIdAndrequestId = "";
			if (StringHelper.isEmpty(cjr.replace("{", "").replace("}", ""))) {
				System.out.println("未调取到残疾人数据！");
				return "1001";
			} else {
				
				CdpfCitizen cjrinfo = citizenRepository.findByCitizenId(citizenId);
				if (cjrinfo != null) {//中残联能调取到  同时存在于本系统中
					
					requestId2 = cjrinfo.getRequestId();
					
					if(cjrinfo.getStatusRecord() != null && cjrinfo.getStatusRecord() != 888) {
						System.out.println("此人已存在于系统中，请勿重复录入！");
						return "1003";
					}
					
					//2019-01-09  修改为不删除，直接显现第一次调取过的信息
					if(cjrinfo.getStatusRecord() != null && cjrinfo.getStatusRecord() == 888) {
						cdpfIdAndrequestId = cjrinfo.getUuid() + ";" + cjrinfo.getRequestId() + ";" + cjrinfo.getKindstr();
						return cdpfIdAndrequestId;
					}
					
					/*
					//跨省迁入临时记录，先删除，再录入
					if(cjrinfo.getStatusRecord() != null && cjrinfo.getStatusRecord() == 888) {
						
						//1.删除CDPF_CITIZEN表中数据
						CdpfCitizen citizen = citizenRepository.findByCitizenId(citizenId);
						if (citizen != null) {
							citizenRepository.delete(citizen);
						}
						//2.删除CDPF_PHOTOS表中数据
						CdpfPhoto photo = cdpfPhotoRepository.findByCitizenId(citizenId);
						if (photo != null) {
							cdpfPhotoRepository.delete(photo);
						}
						
						//3.删除REQUEST表中数据
						Request request = requestRepository.findByUuid(requestId2);
						if (request != null) {
							requestRepository.delete(request);
						}
						
						//4.删除REQUEST_NOTE表中数据
						RequestNote requestNote = requestNoteRepository.findByRequestId(requestId2);
						if (requestNote != null) {
							requestNoteRepository.delete(requestNote);
						}
						
						//5.删除REQUEST_STAKEHOLDER表中数据
						RequestStakeholder requestStakeholder = requestStakeholderRepository.findByRequestId(requestId2);
						if (requestStakeholder != null) {
							requestStakeholderRepository.delete(requestStakeholder);
						}
						
						//6.删除CDPF_IDT表中数据
						IdtResult idtResult = idtResultRepository.findByRequestId(requestId2);
						if (idtResult != null) {
							idtResultRepository.delete(idtResult);
						}
						
						//7.删除CDPF_CARD表中数据
						CdpfCard card = cdpfCardRepository.findByRequestId(requestId2);
						if (card != null) {
							cdpfCardRepository.delete(card);
						}
						
						//8.删除CdpfEye、CdpfEar、CdpfSpeech、CdpfBody、CdpfBody、CdpfIq、CdpfMental 六类残疾表中数据
						String oldKindstr = cjrinfo.getKindstr();
						String newKindstr = "";
						RecordUtil recordUtil = new RecordUtil(oldKindstr, newKindstr);
						delMap = recordUtil.getDelMap();
						delOper();
					}
					*/
					
				}
				
				CdpfCitizenKsqr info = (CdpfCitizenKsqr) JsonUtil.deserializeToBean(cjr, CdpfCitizenKsqr.class);
				cdpfIdAndrequestId = saveCitizen(info); //将调取到的信息保存到数据库
				
				return cdpfIdAndrequestId;
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return "1000";
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
	
	public void delRecord(int iVal) {
		switch (iVal) {
		case 1:
			CdpfEye eye = eyeRepository.findByRequestId(requestId2);
			if (eye != null) {
				eyeRepository.delete(eye);
			}
			break;
		case 2:
			CdpfEar ear = earRepository.findByRequestId(requestId2);
			if (ear != null) {
				earRepository.delete(ear);
			}
			break;
		case 3:
			CdpfSpeech speech = speechRepository.findByRequestId(requestId2);
			if (speech != null) {
				speechRepository.delete(speech);
			}
			break;
		case 4:
			CdpfBody body = bodyRepository.findByRequestId(requestId2);
			if (body != null) {
				bodyRepository.delete(body);
			}
			break;
		case 5:
			CdpfIq iq = iqRepository.findByRequestId(requestId2);
			if (iq != null) {
				iqRepository.delete(iq);
			}
			break;
		case 6:
			CdpfMental mental = mentalRepository.findByRequestId(requestId2);
			if (mental != null) {
				mentalRepository.delete(mental);
			}
			break;
		}

	}
	
	@Override
	public String saveCitizen(CdpfCitizenKsqr info) {
		SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);

		cdpfId = UuidUtil.getUuid();

		//【1】CdpfPhoto 照片数据
		CdpfPhoto photo = new CdpfPhoto();
		photo.setCdpfid(cdpfId);
		photo.setCitizenId(info.getDisabled_number().substring(0, 18));
		photo.setIsHulian(new Integer(0));
		photo.setHlSynced(new Integer(0));
		photo = cdpfPhotoRepository.save(photo);

		//【2】Request 任务数据
		//政务网跨省迁入	  91    10,20,25,30,
		//跨省迁入流程[91]：录入/申请[10]===》受理[20]===》审核[25]===》批准[30] 
		Request request = new Request();
		request.setCdpfId(cdpfId);
		request.setCitizenId(info.getDisabled_number().substring(0, 18));
		request.setCitizenName(info.getName());
		//request.setCityid(user.getCityid());
		request.setCityid(" ");  //为了临时数据不出现在任务中心
		//request.setCurrentState(Constants.FIRST_REVIEW_ZW); //当前节点  20
		request.setCurrentState(new Integer(888)); //临时数据
		request.setFormerState(Constants.NEW);  //上一节点  10
		request.setProcessId(new Integer(91)); //流程
		request.setOptionsApplyFor(info.getKindstr());
		request.setTitle("政务网跨省迁入");
		request.setUserId(user.getUuid());
		request.setUserName(user.getNickname());
		request = requestRepository.save(request);
		
		requestId = request.getUuid();

		//【3】RequestNote 任务历史数据
		//跨省迁入流程[91]：录入/申请[10]===》受理[20]===》审核[25]===》批准[30]
//		RequestNote requestNote = new RequestNote();
//		requestNote.setRequestId(request.getUuid());
//		requestNote.setUserId(user.getUuid());
//		requestNote.setUserName(user.getNickname());
//		requestNote.setHandleState(request.getFormerState());
//		requestNote.setFinishState(request.getCurrentState());
//		requestNote.setNote("跨省迁入录入完成进入受理");
//		requestNote.setLatest(new Integer(1));
//		requestNote.setHandleType(new Integer(1));// 操作类型   1：通过   2：回退   3：不通过
//		requestNote = requestNoteRepository.save(requestNote);

		//【4】RequestStakeholder 待办人员信息
		RequestStakeholder requestStakeholder = new RequestStakeholder();
		requestStakeholder.setRequestId(request.getUuid());
		//requestStakeholder.setCityid(user.getCityid());
		requestStakeholder.setCityid(" "); //为了临时数据不出现在任务中心
		requestStakeholder.setPermissionId("handle");
		requestStakeholder = requestStakeholderRepository.save(requestStakeholder);
		
		//【5】CdpfCitizen 残疾人主表
		CdpfCitizen citizen = new CdpfCitizen();
		copyKsqrcjrInfo(info, citizen);  //将接口数据赋值给CdpfCitizen
		citizen.setUuid(cdpfId);
		citizen.setRequestId(request.getUuid());
		citizen.setPhotoid(photo.getUuid());
		citizen.setIsMoving(new Integer(0));
		/*下面为临时设的值，否则无法保存*/
		citizen.setPolitical(new Integer(1));
		citizen.setGuardianOrAgent(new Integer(1));
		citizen.setGuardianGender(new Integer(1));
		citizen.setGuardianRelation(new Integer(1));
		citizen.setZipcode(" ");
		citizen.setResidentZipcode(" ");
		citizen.setCityidAddrstr(" ");
		citizen.setResidentcityAddrstr(" ");
		citizen.setResidentCity(" ");
		citizen.setResidentAdd(" ");
		citizen.setGuardian(" ");
		citizen.setGuardianCitizenId(" ");
		citizen.setGuardianMobilePhone(" ");
		citizen.setJiguanCode(" ");
		citizen.setCardNo(" ");
		citizen.setHadIdtPhoto(new Integer(1));
//		private Integer hadGaveCard;//默认为0
//		private Integer hadFinalReviewed;//默认为0
//		private Integer delFlag;//默认为0
//		private Integer waitforcardchange;//默认为0
//		private Integer photoPushState;//默认为0
		//citizen.setRequestIdCard(request.getUuid());
		//citizen.setRequestIdFinished(dtoTask.getRequestNote().getRequestId());
		//citizen.setHadFinalReviewed(new Integer(1));
		//citizen.setFirstcardnum(citizen.getCitizenId() + citizen.getIdtKind() + citizen.getIdtLevel());
		//citizen.setRequestIdCard(citizen.getRequestId());
		//citizen.setCardStatus(new Integer(311));
		//citizen.setZhuxiaoTime(Calendar.getInstance().getTime());
		//citizen.setWaitforcardchange(new Integer(0));
		citizen = citizenRepository.save(citizen);
		
		kindstr = citizen.getKindstr();
		
		//【6】IdtResult 评定表
		IdtResult idtResult = new IdtResult();
		copyKsqrpingdingInfo(info, idtResult);  //将接口数据赋值给IdtResult
		idtResult.setCdpfId(cdpfId);
		idtResult.setRequestId(request.getUuid());
		idtResult.setIdtType(new Integer(1));//评定方式    
		idtResult.setIdtKind(info.getDisabled_type() == null ? null : Integer.valueOf(info.getDisabled_type().replace(",", "").replace(";", ""))); //类别
		idtResult.setIdtLevel(info.getDisabled_level() == null ? null : Integer.valueOf(info.getDisabled_level().replace(";", "").replace(",", "")));//等级
		idtResult.setKindstr(info.getKindstr()); //类别串
		idtResult.setLevelstr(" "); //类别等级串
		//idtResult.setIdtDate();	//评定日期  
		//idtResult.setCjqkDescribe(cjqkDescribe); //残疾情况描述   
		//idtResult.setIdtComment(idtComment); //评定意见    
		//idtResult.setIdtHospital(idtHospital); //评定医院   
		//idtResult.setOldkindstr(oldkindstr);
		//idtResult.setMemo(memo);
		idtResult = idtResultRepository.save(idtResult);
		
		//【7】 CdpfEye、CdpfEar、CdpfSpeech、CdpfBody、CdpfBody、CdpfIq、CdpfMental 六类残疾表
		String oldKindstr = "";
		String newKindstr = info.getKindstr();
		RecordUtil recordUtil = new RecordUtil(oldKindstr, newKindstr);
		addMap = recordUtil.getAddMap();
		addOper(info);  //将接口数据赋值给六类残疾表

		return cdpfId + ";" + requestId + ";" + kindstr;
	}
	
	//给主表赋值 CdpfCitizen
	public void copyKsqrcjrInfo(CdpfCitizenKsqr info, CdpfCitizen citizen) {
		
		citizen.setName(info.getName());
		citizen.setCitizenId(info.getDisabled_number().substring(0, 18));
		citizen.setBirthdate(info.getBrith_time());
		citizen.setRace(info.getNation() == null ? null : Integer.valueOf(info.getNation().replace(";", "").replace(",", "")));
		citizen.setGender(info.getSex() == null ? null : Integer.valueOf(info.getSex().replace(";", "").replace(",", "")));
		citizen.setEduLevel(info.getEducation() == null ? null : Integer.valueOf(info.getEducation().replace(";", "").replace(",", "")));
		citizen.setHukouKind(info.getResidence() == null ? null : Integer.valueOf(info.getResidence().replace(";", "").replace(",", "")));
		citizen.setPhoneNo(info.getCon_tel());
		citizen.setMarriager(info.getMarital() == null ? null : Integer.valueOf(info.getMarital().replace(";", "").replace(",", "")));
		citizen.setMobilePhone(info.getCon_phone());
		citizen.setGuardian(info.getGuard_name());
		citizen.setGuardianMobilePhone(info.getGuard_phone());
		citizen.setGuardianPhone(info.getGuard_contelphone());
		citizen.setIdtKind(info.getDisabled_type() == null ? null : Integer.valueOf(info.getDisabled_type().replace(",", "").replace(";", "")));
		citizen.setIdtLevel(info.getDisabled_level() == null ? null : Integer.valueOf(info.getDisabled_level().replace(";", "").replace(",", "")));
		citizen.setCardNum(info.getDisabled_number());
		SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
		//citizen.setCityid(user.getCityid());//迁入地
		citizen.setCityid(" ");//迁入地     为了临时数据不显示设置为空
		String quxianStr = areaRepository.findByAreaCode(user.getCityid().length() >= 6 ? user.getCityid().substring(0,6) : user.getCityid()).getAreaName();
		citizen.setCityidAddrstr(quxianStr);//临时只设置区级地址
		citizen.setFirstCertDate(info.getFirst_time());    //首次发证时间
		citizen.setCertDate(info.getStime()); //发证时间
		citizen.setKindstr(info.getKindstr());
		citizen.setIsHulian(new Integer(0));
		citizen.setNewType(new Integer(1));
		citizen.setBusinessId(new Integer(91));//跨省迁入
		citizen.setStatusRecord(new Integer(888)); //跨省迁入调取临时记录
		citizen.setIsMoving(new Integer(1));

	}
	
	//给评定表赋值  IdtResult
	public void copyKsqrpingdingInfo(CdpfCitizenKsqr info, IdtResult idtResult) {
		
		idtResult.setDelFlag(new Integer(0));
		idtResult.setHlSynced(new Integer(0));
		idtResult.setIdtKind(info.getDisabled_type() == null ? null : Integer.valueOf(info.getDisabled_type().replace(",", "").replace(";", "")));
		idtResult.setIdtLevel(info.getDisabled_level() == null ? null : Integer.valueOf(info.getDisabled_level().replace(";", "").replace(",", "")));
		idtResult.setKindstr(info.getKindstr());
		idtResult.setNewType(new Integer(1));
		StringBuffer sb = new StringBuffer();  //评定医师       （如：张桂玲、刘金霞、刘妍）
		if(StringHelper.isNotEmpty(info.getPhysician_name())){//评定医师
			sb.append(sb.length() > 0 ? "、" :"" + info.getPhysician_name());
		}
		if(StringHelper.isNotEmpty(info.getPhysician_namesl())){//视力
			sb.append(sb.length() > 0 ? "、" :"" + info.getPhysician_namesl());
		}
		if(StringHelper.isNotEmpty(info.getPhysician_nametl())){//听力
			sb.append(sb.length() > 0 ? "、" :"" + info.getPhysician_nametl());
		}
		if(StringHelper.isNotEmpty(info.getPhysician_nameyy())){//言语
			sb.append(sb.length() > 0 ? "、" :"" + info.getPhysician_nameyy());
		}
		if(StringHelper.isNotEmpty(info.getPhysician_namezt())){//肢体
			sb.append(sb.length() > 0 ? "、" :"" + info.getPhysician_namezt());
		}
		if(StringHelper.isNotEmpty(info.getPhysician_namezl())){//智力
			sb.append(sb.length() > 0 ? "、" :"" + info.getPhysician_namezl());
		}
		if(StringHelper.isNotEmpty(info.getPhysician_namejs())){//精神
			sb.append(sb.length() > 0 ? "、" :"" + info.getPhysician_namejs());
		}
		idtResult.setIdtName(sb.toString());

	}
	
	public void addOper(CdpfCitizenKsqr info) {
		if (addMap == null)
			return;
		Iterator<Integer> iter = addMap.values().iterator();
		while (iter.hasNext()) {
			Integer iVal = (Integer) iter.next();
			copyKsqridtInfo(iVal.intValue(), info);
		}
	}
	
	//给六类残疾表赋值  CdpfEye  CdpfEar  CdpfSpeech CdpfBody  CdpfBody  CdpfIq  CdpfMental
	public void copyKsqridtInfo(int iVal, CdpfCitizenKsqr info) {
		switch (iVal) {
		case 1:
			CdpfEye eye = eyeRepository.findByRequestId(requestId);
			if (eye == null) {
				eye = new CdpfEye();
			}
			eye.setRequestId(requestId);
			eye.setCdpfId(cdpfId);
			eye.setEyeLevel(info.getVisual_level() == null ? null : Integer.valueOf(info.getVisual_level().replace(";", "").replace(",", "")));
			eye.setEyeReason(info.getVisual_reason() == null ? null : Integer.valueOf(info.getVisual_reason().replace(";", "").replace(",", "")));
			eye.setSightRight(info.getRight_eye_vision() == null ? null : BigDecimal.valueOf(Double.parseDouble(info.getRight_eye_vision().replace(";", "").replace(",", ""))));
			eye.setSightLeft(info.getLeft_eye_vision() == null ? null : BigDecimal.valueOf(Double.parseDouble(info.getLeft_eye_vision().replace(";", "").replace(",", ""))));
			eye.setVisionRight(info.getRight_eye_field() == null ? null : Integer.valueOf(info.getRight_eye_field().replace(";", "").replace(",", "")));
			eye.setVisionLeft(info.getLeft_eye_field() == null ? null : Integer.valueOf(info.getLeft_eye_field().replace(";", "").replace(",", "")));
//			eye.setCreatetime(Calendar.getInstance().getTime());
//			eye.setEdittime(Calendar.getInstance().getTime());
			eye = eyeRepository.save(eye);
			break;
		case 2:
			CdpfEar ear = earRepository.findByRequestId(requestId);
			if (ear == null) {
				ear = new CdpfEar();
			}
			ear.setRequestId(requestId);
			ear.setCdpfId(cdpfId);
			ear.setEarLevel(info.getHearing_level() == null ? null : Integer.valueOf(info.getHearing_level().replace(";", "").replace(",", "")));
			ear.setEarReason(info.getHearing_reason() == null ? null : Integer.valueOf(info.getHearing_reason().replace(";", "").replace(",", "")));
			ear.setHearingLose(info.getHearing_loss_avg() == null ? null : Integer.valueOf(info.getHearing_loss_avg().replace(";", "").replace(",", "")));
			ear.setEarRight1(info.getRight_test_500() == null ? null : Integer.valueOf(info.getRight_test_500().replace(";", "").replace(",", "")));
			ear.setEarRight2(info.getRight_test_1000() == null ? null : Integer.valueOf(info.getRight_test_1000().replace(";", "").replace(",", "")));
			ear.setEarRight3(info.getRight_test_2000() == null ? null : Integer.valueOf(info.getRight_test_2000().replace(";", "").replace(",", "")));
			ear.setEarRight4(info.getRight_test_4000() == null ? null : Integer.valueOf(info.getRight_test_4000().replace(";", "").replace(",", "")));
			ear.setEarLeft1(info.getLeft_test_500() == null ? null : Integer.valueOf(info.getLeft_test_500().replace(";", "").replace(",", "")));
			ear.setEarLeft2(info.getLeft_test_1000() == null ? null : Integer.valueOf(info.getLeft_test_1000().replace(";", "").replace(",", "")));
			ear.setEarLeft3(info.getLeft_test_2000() == null ? null : Integer.valueOf(info.getLeft_test_2000().replace(";", "").replace(",", "")));
			ear.setEarLeft4(info.getLeft_test_4000() == null ? null : Integer.valueOf(info.getLeft_test_4000().replace(";", "").replace(",", "")));
			ear.setEarSpeech(info.getHearing_speech_ability() == null ? null : Integer.valueOf(info.getHearing_speech_ability().replace(";", "").replace(",", "")));
			ear.setNoise(info.getBackground_noise() == null ? null : Integer.valueOf(info.getBackground_noise().replace(";", "").replace(",", "")));
//			ear.setCreatetime(Calendar.getInstance().getTime());
//			ear.setEdittime(Calendar.getInstance().getTime());
			ear = earRepository.save(ear);
			break;
		case 3:
			CdpfSpeech speech = speechRepository.findByRequestId(requestId);
			if (speech == null) {
				speech = new CdpfSpeech();
			}
			speech.setRequestId(requestId);
			speech.setCdpfId(cdpfId);
			speech.setSpeechLevel(info.getSpeech_level() == null ? null : Integer.valueOf(info.getSpeech_level().replace(";", "").replace(",", "")));
			speech.setSpeechReason(info.getSpeech_reason() == null ? null : Integer.valueOf(info.getSpeech_reason().replace(";", "").replace(",", "")));
			speech.setSpeechDis(info.getSpeech_category() == null ? null : Integer.valueOf(info.getSpeech_category().replace(";", "").replace(",", "")));
			speech.setSpeechClear(info.getSpeech_clarity() == null ? null : Integer.valueOf(info.getSpeech_clarity().replace(";", "").replace(",", "")));
			speech.setSpeechFunc(info.getSpeech_ability() == null ? null : Integer.valueOf(info.getSpeech_ability().replace(";", "").replace(",", "")));
//			speech.setCreatetime(Calendar.getInstance().getTime());
//			speech.setEdittime(Calendar.getInstance().getTime());
			speech = speechRepository.save(speech);
			break;
		case 4:
			CdpfBody body = bodyRepository.findByRequestId(requestId);
			if (body == null) {
				body = new CdpfBody();
			}
			body.setRequestId(requestId);
			body.setCdpfId(cdpfId);
			body.setBodyLevel(info.getPhysical_level() == null ? null : Integer.valueOf(info.getPhysical_level().replace(";", "").replace(",", "")));
			body.setBodyDetail(info.getPhysical_perform() == null ? null : Integer.valueOf(info.getPhysical_perform().replace(";", "").replace(",", "")));
			body.setBodyReason(info.getPhysical_reason() == null ? null : Integer.valueOf(info.getPhysical_reason().replace(";", "").replace(",", "")));
//			body.setCreatetime(Calendar.getInstance().getTime());
//			body.setEdittime(Calendar.getInstance().getTime());
			body = bodyRepository.save(body);
			break;
		case 5:
			CdpfIq iq = iqRepository.findByRequestId(requestId);
			if (iq == null) {
				iq = new CdpfIq();
			}
			iq.setRequestId(requestId);
			iq.setCdpfId(cdpfId);
			iq.setIqLevel(info.getIntellectual_level() == null ? null : Integer.valueOf(info.getIntellectual_level().replace(";", "").replace(",", "")));
			iq.setIqReason(info.getIntellectual_reason() == null ? null : Integer.valueOf(info.getIntellectual_reason().replace(";", "").replace(",", "")));
			iq.setIqDegree1(info.getIntellectual_develope() == null ? null : Integer.valueOf(info.getIntellectual_develope().replace(";", "").replace(",", "")));
			iq.setIqDegree2(info.getIq() == null ? null : Integer.valueOf(info.getIq().replace(";", "").replace(",", "")));
//			iq.setCreatetime(Calendar.getInstance().getTime());
//			iq.setEdittime(Calendar.getInstance().getTime());
			iq = iqRepository.save(iq);
			break;
		case 6:
			CdpfMental mental = mentalRepository.findByRequestId(requestId);
			if (mental == null) {
				mental = new CdpfMental();
			}
			mental.setRequestId(requestId);
			mental.setCdpfId(cdpfId);
			mental.setMentalLevel(info.getMental_level() == null ? null : Integer.valueOf(info.getMental_level().replace(";", "").replace(",", "")));
			mental.setMentalReason(info.getMental_reason() == null ? null : Integer.valueOf(info.getMental_reason().replace(";", "").replace(",", "")));
			mental.setWhoDasIi(info.getWho_grade() == null ? null : Integer.valueOf(info.getWho_grade().replace(";", "").replace(",", "")));
//			mental.setCreatetime(Calendar.getInstance().getTime());
//			mental.setEdittime(Calendar.getInstance().getTime());
			mental = mentalRepository.save(mental);
			break;
		}
	}
	
//	public static void main(String[] args) {
//	KsqrJiekouServiceImpl ksqrJiekouServiceImpl = new KsqrJiekouServiceImpl();
//	ksqrJiekouServiceImpl.getCitizenInfo();		
//}
	
}

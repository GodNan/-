package com.css.bdpfnew.service.impl.card;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.bdpfnew.model.entity.bdpfnew.CdpfCard;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizen;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfPhoto;
import com.css.bdpfnew.repository.AreaRepository;
import com.css.bdpfnew.repository.CdpfPhotoRepository;
import com.css.bdpfnew.repository.card.CardCheckRepository;
import com.css.bdpfnew.repository.card.CdpfCardRepository;
import com.css.bdpfnew.service.card.CardCheckService;
import com.css.bdpfnew.service.impl.BaseServiceImpl;

/**
 * @Author HEYCH
 * @Date 2018年10月18日 下午4:19:16
 * @Description
 */

@Service
public class CardCheckServiceImpl extends BaseServiceImpl<CdpfCitizen, Long> implements CardCheckService {
	@Autowired
	private CardCheckRepository cardCheckRepository;
	@Autowired
	private CdpfCardRepository cdpfCardRepository;
	@Autowired
	private CdpfPhotoRepository cdpfPhotoRepository;
	@Autowired
	private AreaRepository areaRepository;

	@Autowired
	public void setBaseDao(CardCheckRepository cardCheckRepository) {
		super.setBaseDao(cardCheckRepository);
	}

	@Override
	public CdpfCitizen findByCitizenId(String citizenId) {
		return cardCheckRepository.findByCitizenId(citizenId);
	}
	
	@Override
	public String sInfoData(String citizenId){
		CdpfCitizen cdpfCitizen = cardCheckRepository.findByCitizenId(citizenId);
		String sInfoData = "";
		String temp = "";
		
		if(cdpfCitizen != null){
			//有效期
			Calendar curr = Calendar.getInstance();
			curr.add(Calendar.DATE,365);
			Date date=curr.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			temp = sdf.format(date);
			sInfoData=temp;	
			sInfoData+=",";
			
			//残疾人编码(残疾证号)
			temp = cdpfCitizen.getCardNum() == null ? "" : cdpfCitizen.getCardNum();
			sInfoData+=temp;	
			sInfoData+=",";
			
			//姓名
			temp = cdpfCitizen.getName() == null ? "" : cdpfCitizen.getName();
			sInfoData+=temp;
			sInfoData+=",";
			
			//身份证号
			temp = cdpfCitizen.getCitizenId() == null ? "" : cdpfCitizen.getCitizenId();
			sInfoData+=temp;
			sInfoData+=",";
			
			//移动电话号码
			temp = cdpfCitizen.getMobilePhone() == null ? "" : cdpfCitizen.getMobilePhone();
			sInfoData+=temp;
			sInfoData+=",";
			
			//家庭电话
			temp = cdpfCitizen.getPhoneNo() == null ? "" : cdpfCitizen.getPhoneNo();
			sInfoData+=temp;
			sInfoData+=",";
			
			//办公电话
			temp = "";
			sInfoData+=temp;
			sInfoData+=",";
			
			//email
			temp = cdpfCitizen.getEmail() == null ? "" : cdpfCitizen.getEmail();
			sInfoData+=temp;
			sInfoData+=",";
			
			//户籍地址
			temp = cdpfCitizen.getJiguanCode() == null ? "" : cdpfCitizen.getJiguanCode();
			sInfoData+=temp;
			sInfoData+=",";
			
			//邮编
			temp = cdpfCitizen.getZipcode() == null ? "" : cdpfCitizen.getZipcode();
			sInfoData+=temp;
			sInfoData+=",";
			
			//交易密码
			sInfoData+="999999";
			sInfoData+=",";
			
			//查询密码
			sInfoData+="888888";	
			sInfoData = sInfoData.replaceAll(",,",", ,");
			sInfoData = sInfoData.replaceAll(",,",", ,");
		}
		
		return sInfoData;
	}
	
	@Override
	public String sYearInfo(String requestId) {
		CdpfCard card = cdpfCardRepository.findByRequestId(requestId);
		String sYearInfo = "";
		
		if(card != null){
			//卡号
			String temp = card.getCardNo() == null ? "" : card.getCardNo(); 
			sYearInfo=temp;
			sYearInfo+=",";
			
			//有效期
			Calendar curr = Calendar.getInstance();
			curr.add(Calendar.DATE,365);
			Date date=curr.getTime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			temp = sdf.format(date);
			sYearInfo+=temp;
		}
		
		return sYearInfo;
	}
	
	@Override
	public String sLossData(String citizenId){
		CdpfCitizen cdpfCitizen = cardCheckRepository.findByCitizenId(citizenId);
		CdpfCard card = cdpfCardRepository.findByRequestId(cdpfCitizen.getRequestIdCard());
		
		String sLossData = "";
		
		if(card != null) {
			//卡号
			String temp = card.getCardNo() == null ? "" : card.getCardNo(); 
			sLossData+=temp;
			sLossData+=",";		
			
			//姓名
			temp = cdpfCitizen.getName() == null ? "" : cdpfCitizen.getName(); 
			sLossData+=temp;
			sLossData+=",";
			
			//移动电话
			temp = cdpfCitizen.getMobilePhone() == null ? "" : cdpfCitizen.getMobilePhone(); 
			sLossData+=temp;
			sLossData+=",";
			
			//账户密码(账户密码暂时取不到,用999999代替)
			sLossData+="999999,";
			
			//身份证号
			temp = cdpfCitizen.getCitizenId() == null ? "" : cdpfCitizen.getCitizenId(); 
			sLossData+=temp;
			sLossData = sLossData.replaceAll(",,",", ,");
			sLossData = sLossData.replaceAll(",,",", ,");
		}
		
		return sLossData;
	}
	
	@Override
	public String sReleaseData(String citizenId){
		CdpfCitizen cdpfCitizen = cardCheckRepository.findByCitizenId(citizenId);
		CdpfCard card = cdpfCardRepository.findByRequestId(cdpfCitizen.getRequestIdCard());
		String sReleaseData = "";
		
		if(card != null) {
			//卡号
			String temp = card.getCardNo() == null ? "" : card.getCardNo(); 
			sReleaseData+=temp;
			sReleaseData+=",";		
			
			//姓名
			temp = cdpfCitizen.getName() == null ? "" : cdpfCitizen.getName(); 
			sReleaseData+=temp;
			sReleaseData+=",";
			
			//移动电话
			temp = cdpfCitizen.getMobilePhone() == null ? "" : cdpfCitizen.getMobilePhone(); 
			sReleaseData+=temp;
			sReleaseData+=",";
			
			//账户密码
			sReleaseData+="999999,";
			
			//身份证号
			temp = cdpfCitizen.getCitizenId() == null ? "" : cdpfCitizen.getCitizenId(); 
			sReleaseData+=temp;
			sReleaseData = sReleaseData.replaceAll(",,",", ,");
			sReleaseData = sReleaseData.replaceAll(",,",", ,");
		}
		
		return sReleaseData;
	}
	
	@Override
	public String sNewCardInfo(String citizenId){
		CdpfCitizen cdpfCitizen = cardCheckRepository.findByCitizenId(citizenId);
		String sNewCardInfo = "";	
		
		if(cdpfCitizen != null){
			//姓名
			String temp = cdpfCitizen.getName() == null ? "" : cdpfCitizen.getName(); 
			sNewCardInfo+=temp;
			sNewCardInfo+=",";
			
			//移动电话
			temp = cdpfCitizen.getMobilePhone() == null ? "" : cdpfCitizen.getMobilePhone(); 
			sNewCardInfo+=temp;
			sNewCardInfo+=",";
			
			//账户密码
			sNewCardInfo+="999999,";
			
			//身份证号
			temp = cdpfCitizen.getCitizenId() == null ? "" : cdpfCitizen.getCitizenId(); 
			sNewCardInfo+=temp;
			sNewCardInfo = sNewCardInfo.replaceAll(",,",", ,");
			sNewCardInfo = sNewCardInfo.replaceAll(",,",", ,");
		}
		
		return sNewCardInfo;
	}
	
	@Override
	public String sNewInfo(String citizenId){
		CdpfCitizen cdpfCitizen = cardCheckRepository.findByCitizenId(citizenId);
		CdpfCard card = cdpfCardRepository.findByRequestId(cdpfCitizen.getRequestIdCard());
		String sNewInfo = "";	
		
		if(card != null){
			//卡号
			String temp = card.getCardNo() == null ? "" : card.getCardNo();
			sNewInfo+=temp;
			sNewInfo+=",";
			
			//姓名
			temp = cdpfCitizen.getName() == null ? "" : cdpfCitizen.getName(); 
			sNewInfo+=temp;
			sNewInfo+=",";
			
			//身份证号
			temp = cdpfCitizen.getCitizenId() == null ? "" : cdpfCitizen.getCitizenId(); 
			sNewInfo+=temp;
			sNewInfo+=",";		
			
			//移动电话
			temp = cdpfCitizen.getMobilePhone() == null ? "" : cdpfCitizen.getMobilePhone(); 
			sNewInfo+=temp;
			sNewInfo+=",";
			
			//家庭电话
			temp = cdpfCitizen.getPhoneNo() == null ? "" : cdpfCitizen.getPhoneNo();
			sNewInfo+=temp;
			sNewInfo+=",";
			
			//办公电话
			temp = "";
			sNewInfo+=temp;
			sNewInfo+=",";
			
			//电子邮件
			temp = cdpfCitizen.getEmail() == null ? "" : cdpfCitizen.getEmail();
			sNewInfo+=temp;
			sNewInfo+=",";
			
			//户籍地址
			temp = cdpfCitizen.getJiguanCode() == null ? "" : cdpfCitizen.getJiguanCode();
			sNewInfo+=temp;
			sNewInfo+=",";
			
			//邮编
			temp = cdpfCitizen.getZipcode() == null ? "" : cdpfCitizen.getZipcode();
			sNewInfo+=temp;
			
			sNewInfo = sNewInfo.replaceAll(",,",", ,");
			sNewInfo = sNewInfo.replaceAll(",,",", ,");
		} 
		
		return sNewInfo;
	}
	
	@Override
	public String cPhotoInfo(String citizenId){
		String cPhotoInfo = "";
		try {
			CdpfPhoto photo = cdpfPhotoRepository.findByCitizenId(citizenId);
			if(photo!=null){
				java.sql.Blob blob = photo.getInnerData();
				if (blob != null) {
					int length = (int) blob.length();
					byte[] imgdata = blob.getBytes(1, length);
					cPhotoInfo = Base64.getEncoder().withoutPadding().encodeToString(imgdata);
				} else {
					cPhotoInfo = "";
				}	
			}
			return cPhotoInfo;
		} catch (Exception ex) {
			ex.printStackTrace();
			return "查询失败";
		}
	}
	
	@Override
	public String cPersonInfo(String citizenId){
		String cPersonInfo = "";
		CdpfCitizen cdpfCitizen = cardCheckRepository.findByCitizenId(citizenId);
		/*姓名,性别代码,民族代码,出生日期（8位字符串yyyymmdd）,*/
		
		//姓名
		String temp = cdpfCitizen.getName() == null ? "" : cdpfCitizen.getName(); 
		cPersonInfo+=temp;
		cPersonInfo+=",";
		
		//性别代码
		temp = cdpfCitizen.getGender() == null ? "" : cdpfCitizen.getGender().toString(); 
		cPersonInfo+=temp;
		cPersonInfo+=",";
		
		//民族代码
		temp = cdpfCitizen.getRace() == null ? "" : cdpfCitizen.getRace().toString(); 
		cPersonInfo+=temp;
		cPersonInfo+=",";
		
		//出生日期  8位字符串yyyymmdd
		temp = cdpfCitizen.getBirthdate() == null ? "" : new java.text.SimpleDateFormat("yyyyMMdd").format(cdpfCitizen.getBirthdate()); 
		cPersonInfo+=temp;
		cPersonInfo+=",";	
		
		/*公民身份证号码,户籍地编码,户籍地地址,所属街道名称,*/
		
		//公民身份证号码
		temp = cdpfCitizen.getCitizenId() == null ? "" : cdpfCitizen.getCitizenId(); 
		cPersonInfo+=temp;
		cPersonInfo+=",";	
		
		//户籍地编码
		temp = cdpfCitizen.getCityid() == null ? "" : cdpfCitizen.getCityid().toString(); 
		cPersonInfo+=temp;
		cPersonInfo+=",";	
		
		//户籍地地址
		temp = cdpfCitizen.getCityidAddrstr()==null ? "" : cdpfCitizen.getCityidAddrstr() ; 
		cPersonInfo+=temp;
		cPersonInfo+=",";
		
		//所属街道名称
		if(cdpfCitizen.getCityid() == null){
			temp = "";
		}else if(String.valueOf(cdpfCitizen.getCityid()).length() < 9){
			temp = areaRepository.findByAreaCode(cdpfCitizen.getCityid()).getAreaName();
		}else{
			temp = cdpfCitizen.getCityid() == null ? "" : areaRepository.findByAreaCode(String.valueOf(cdpfCitizen.getCityid()).substring(0,9)).getAreaName();
		}
		cPersonInfo+=temp;
		cPersonInfo+=",";		
		
		/*固定电话,移动电话,残疾证号,残疾证签发日期（8位字符串yyyymmdd）,*/
			
		//固定电话
		temp = cdpfCitizen.getPhoneNo() == null ? "" : cdpfCitizen.getPhoneNo(); 
		cPersonInfo+=temp;
		cPersonInfo+=",";
		
		//移动电话
		temp = cdpfCitizen.getMobilePhone() == null ? "" : cdpfCitizen.getMobilePhone(); 
		cPersonInfo+=temp;
		cPersonInfo+=",";
		
		//残疾证号
		temp = cdpfCitizen.getCardNum() == null ? "" : cdpfCitizen.getCardNum(); 
		cPersonInfo+=temp;
		cPersonInfo+=",";
		
		//残疾证签发日期（8位字符串yyyymmdd）
		temp = cdpfCitizen.getCertDate() == null ? "" : new java.text.SimpleDateFormat("yyyyMMdd").format(cdpfCitizen.getCertDate()); 
		cPersonInfo+=temp;
		cPersonInfo+=",";		
		
		//文化程度代码,婚姻状况代码,政治面貌代码,户口性质代码,
		
		//文化程度代码
		temp = cdpfCitizen.getEduLevel() == null ? "" : cdpfCitizen.getEduLevel().toString(); 
		cPersonInfo+=temp;
		cPersonInfo+=",";
		
		//婚姻状况代码
		temp = cdpfCitizen.getMarriager() == null ? "" : cdpfCitizen.getMarriager().toString(); 
		cPersonInfo+=temp;
		cPersonInfo+=",";
		
		//政治面貌代码
		temp = cdpfCitizen.getPolitical() == null ? "" : cdpfCitizen.getPolitical().toString(); 
		cPersonInfo+=temp;
		cPersonInfo+=",";
		
		//户口性质代码
		temp = cdpfCitizen.getHukouKind() == null ? "" : cdpfCitizen.getHukouKind().toString(); 
		cPersonInfo+=temp;
		cPersonInfo+=",";
		
		//联系人姓名,联系人关系,联系人电话,居住地编码,居住地地址,
				
		//联系人姓名
		temp = cdpfCitizen.getGuardian() == null ? "" : cdpfCitizen.getGuardian(); 
		cPersonInfo+=temp;
		cPersonInfo+=",";
		
		//联系人关系
		temp = cdpfCitizen.getGuardianRelation() == null ? "" : cdpfCitizen.getGuardianRelation().toString(); 
		cPersonInfo+=temp;
		cPersonInfo+=",";
		
		//联系人电话
		temp = cdpfCitizen.getGuardianMobilePhone() == null ? cdpfCitizen.getGuardianPhone() == null ? "" : cdpfCitizen.getGuardianPhone() : cdpfCitizen.getGuardianMobilePhone(); 
		cPersonInfo+=temp;
		cPersonInfo+=",";
		
		//居住地编码
		temp = cdpfCitizen.getResidentCity() == null ? "" : cdpfCitizen.getResidentCity().toString(); 
		cPersonInfo+=temp;
		cPersonInfo+=",";	
		
		//居住地地址
		temp = cdpfCitizen.getResidentcityAddrstr() == null ? "" : cdpfCitizen.getResidentcityAddrstr(); 
		cPersonInfo+=temp;
		cPersonInfo+=",";
		
		//残疾人类别代码,残疾人等级代码,
		//残疾人类别代码
		temp = cdpfCitizen.getIdtKind() == null ? "" : cdpfCitizen.getIdtKind().toString(); 
		cPersonInfo+=temp;
		cPersonInfo+=",";		
		
		//残疾人等级代码
		temp = cdpfCitizen.getIdtLevel() == null ? "" : cdpfCitizen.getIdtLevel().toString(); 
		cPersonInfo+=temp;
		cPersonInfo+=",";		

		//视力残疾等级代码,听力残疾等级代码,言语残疾等级代码,肢体残疾等级代码,智力残疾等级代码,精神残疾等级代码,
		String levelStr = cdpfCitizen.getLevelstr() == null ? "" : cdpfCitizen
				.getLevelstr();
		String[] levelArr = levelStr.split(",");
		boolean c = true;
		// 多重残疾信息1(视力)
		for (int i = 0; i < levelArr.length; i = i + 2) {
			if ("1".equals(levelArr[i])) {
				c = false;
				temp = levelArr[i + 1];
			}
		}
		if (c) {
			temp = "0";
		}
		c = true;

		cPersonInfo += temp;
		cPersonInfo += ",";

		// 多重残疾信息2(听力)
		for (int i = 0; i < levelArr.length; i = i + 2) {
			if ("2".equals(levelArr[i])) {
				c = false;
				temp = levelArr[i + 1];
			}
		}
		if (c) {
			temp = "0";
		}
		c = true;

		cPersonInfo += temp;
		cPersonInfo += ",";

		// 多重残疾信息3(言语)
		for (int i = 0; i < levelArr.length; i = i + 2) {
			if ("3".equals(levelArr[i])) {
				c = false;
				temp = levelArr[i + 1];
			}
		}
		if (c) {
			temp = "0";
		}
		c = true;

		cPersonInfo += temp;
		cPersonInfo += ",";

		// 多重残疾信息4(肢体)
		for (int i = 0; i < levelArr.length; i = i + 2) {
			if ("4".equals(levelArr[i])) {
				c = false;
				temp = levelArr[i + 1];
			}
		}
		if (c) {
			temp = "0";
		}
		c = true;

		cPersonInfo += temp;
		cPersonInfo += ",";

		// 多重残疾信息5(智商)
		for (int i = 0; i < levelArr.length; i = i + 2) {
			if ("5".equals(levelArr[i])) {
				c = false;
				temp = levelArr[i + 1];
			}
		}
		if (c) {
			temp = "0";
		}
		c = true;

		cPersonInfo += temp;
		cPersonInfo += ",";

		// 多重残疾信息6(精神)
		for (int i = 0; i < levelArr.length; i = i + 2) {
			if ("6".equals(levelArr[i])) {
				c = false;
				temp = levelArr[i + 1];
			}
		}
		if (c) {
			temp = "0";
		}
		c = true;

		cPersonInfo += temp;
		cPersonInfo += ",";		
		
		return cPersonInfo;
		
	}

}

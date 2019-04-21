package com.css.bdpfnew.service.card;

import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizen;
import com.css.bdpfnew.service.BaseService;

/**
 * @Author HEYCH
 * @Date 2018年10月18日 下午4:19:16
 * @Description
 */

public interface CardCheckService extends BaseService<CdpfCitizen, Long> {
	CdpfCitizen findByCitizenId(String citizenId);

	String sInfoData(String citizenId);

	String sYearInfo(String requestId);

	String cPersonInfo(String citizenId);

	String cPhotoInfo(String citizenId);

	String sNewInfo(String citizenId);

	String sNewCardInfo(String citizenId);

	String sReleaseData(String citizenId);

	String sLossData(String citizenId);
	
}

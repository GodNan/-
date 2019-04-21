package com.css.bdpfnew.service.impl;


import com.css.bdpfnew.model.entity.bdpfnew.CdpfCard;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizen;
import com.css.bdpfnew.model.entity.bdpfnew.view.VwCardMake;
import com.css.bdpfnew.model.vo.VoPhotoToCards;
import com.css.bdpfnew.model.vo.VoUnlockPhoto;
import com.css.bdpfnew.repository.CardPhotoRepository;
import com.css.bdpfnew.repository.CitizenRepository;
import com.css.bdpfnew.repository.card.CdpfCardRepository;
import com.css.bdpfnew.service.CardPhotoService;

import com.css.bdpfnew.service.CdpfLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


@Service
public class CardPhotoServiceImpl extends BaseServiceImpl<VwCardMake, Long> implements CardPhotoService {
	@Autowired
	private CardPhotoRepository cardPhotoRepository;
	@Autowired
	private CitizenRepository citizenRepository;
	@Autowired
	private CdpfCardRepository cdpfCardRepository;
	@Autowired
	private CdpfLogService cdpfLogService;


	@Override
	public List<VoUnlockPhoto> getPhotoList(List<String> strList) throws SQLException {
		List<VoUnlockPhoto> voUnlockPhotoList= new ArrayList<VoUnlockPhoto>();
		List<VwCardMake> cdpfPhotoList = cardPhotoRepository.findByBdpfidIn(strList);
		for(VwCardMake photoItem:cdpfPhotoList){
			VoUnlockPhoto voUnlockPhoto = new VoUnlockPhoto();
			BeanUtils.copyProperties(photoItem, voUnlockPhoto);

			Blob blob = photoItem.getData();
			byte[] picByte64;
			picByte64 = blob.getBytes(1, (int) blob.length());

			String citizenPhoto = Base64.getEncoder().withoutPadding().encodeToString(picByte64);

			voUnlockPhoto.setPhoto (citizenPhoto);
			voUnlockPhotoList.add(voUnlockPhoto);
		}
		return voUnlockPhotoList;
	}

	@Override
	public void moveToCardsMake(List<VoPhotoToCards> strList) {
		for(VoPhotoToCards v : strList){
			CdpfCitizen citizen = citizenRepository.findByCitizenId(v.getCitizenId());
			if(citizen != null){
				citizen.setPhotoPushState(1);
				citizenRepository.save(citizen);
				CdpfCard cdpfCard= cdpfCardRepository.findByRequestId(citizen.getRequestIdCard());
				if(cdpfCard != null){
					cdpfCard.setRemindText(v.getText());
					cdpfCardRepository.save(cdpfCard);
					//操作日志
					String cdpfIdStr = citizen.getUuid();
					String requestIdStr = citizen.getRequestIdCard();
					String logData = v.getText();
					Integer logType = new Integer(932);
					Integer opertype = new Integer(1);
					cdpfLogService.create(cdpfIdStr, requestIdStr, logData, logType, opertype);
				}
			}
		}

	}


}

/**
 * 
 */
package com.css.bdpfnew.service.impl;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.bdpfnew.model.entity.bdpfnew.CitizenJiedongPhoto;
import com.css.bdpfnew.model.vo.VoUnlockPhoto;
import com.css.bdpfnew.repository.UnlockPhotoRepository;
import com.css.bdpfnew.service.UnlockPhotoService;

/**
 * @Author erDuo
 * @Date 2018年12月9日 下午2:21:27
 * @Description
 */
@Service
public class UnlockPhotoServiceImpl extends BaseServiceImpl<CitizenJiedongPhoto, Long> implements UnlockPhotoService {

	@Autowired
	private UnlockPhotoRepository unlockPhotoRepository;

	@Autowired
	public void setBaseDao(UnlockPhotoRepository unlockPhotoRepository) {
		super.setBaseDao(unlockPhotoRepository);
	}

	@Override
	public VoUnlockPhoto findByUuid(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VoUnlockPhoto findByCdpfId(String cdpfId) throws SQLException {
		CitizenJiedongPhoto citizenUnlockPhoto = unlockPhotoRepository.findByCdpfId(cdpfId);
		if (citizenUnlockPhoto == null || citizenUnlockPhoto.getPicture() == null) {
			return null;
		}

		VoUnlockPhoto voUnlockPhoto = new VoUnlockPhoto();
		BeanUtils.copyProperties(citizenUnlockPhoto, voUnlockPhoto);

		Blob blob = citizenUnlockPhoto.getPicture();

		byte[] picByte64;
		picByte64 = blob.getBytes(1, (int) blob.length());

		String idtphoto = Base64.getEncoder().withoutPadding().encodeToString(picByte64);

		voUnlockPhoto.setPhoto(idtphoto);

		return voUnlockPhoto;
	}

	@Override
	public VoUnlockPhoto findByCitizenId(String citizenId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save(CitizenJiedongPhoto idtPhoto) throws SerialException, SQLException, IOException {
		CitizenJiedongPhoto photo = unlockPhotoRepository.findByCdpfId(idtPhoto.getCdpfId());

		if (photo == null) {
			idtPhoto = unlockPhotoRepository.save(idtPhoto);
			return idtPhoto.getUuid();
		} else {
			photo.setPicture(idtPhoto.getPicture());
			photo = unlockPhotoRepository.save(photo);
			return photo.getUuid();
		}

	}

	@Override
	public String update(CitizenJiedongPhoto idtPhoto) {
		// TODO Auto-generated method stub
		return null;
	}

}

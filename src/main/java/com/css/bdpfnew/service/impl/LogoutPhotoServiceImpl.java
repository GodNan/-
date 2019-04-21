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
import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.entity.bdpfnew.CitizenLogoutPhotos;
import com.css.bdpfnew.model.vo.VoLogoutPhoto;
import com.css.bdpfnew.repository.LogoutPhotoRepository;
import com.css.bdpfnew.service.LogoutPhotoService;

/**
 * @Author erDuo
 * @Date 2018年12月9日 下午2:21:27
 * @Description
 */
@Service
public class LogoutPhotoServiceImpl extends BaseServiceImpl<CitizenLogoutPhotos, Long> implements LogoutPhotoService {

	@Autowired
	private LogoutPhotoRepository logoutPhotoRepository;

	@Autowired
	public void setBaseDao(LogoutPhotoRepository logoutPhotoRepository) {
		super.setBaseDao(logoutPhotoRepository);
	}

	@Override
	public VoLogoutPhoto findByUuid(String uuid) {
		return null;
	}

	@Override
	public VoLogoutPhoto findByCdpfId(String cdpfId) throws SQLException {
		CitizenLogoutPhotos citizenLogoutPhotos = logoutPhotoRepository.findByCdpfId(cdpfId);
		if (citizenLogoutPhotos == null || citizenLogoutPhotos.getPicture() == null) {
			return null;
		}

		VoLogoutPhoto voLogoutPhoto = new VoLogoutPhoto();
		BeanUtils.copyProperties(citizenLogoutPhotos, voLogoutPhoto);

		Blob blob = citizenLogoutPhotos.getPicture();

		byte[] picByte64;
		picByte64 = blob.getBytes(1, (int) blob.length());

		String logoutphoto = Base64.getEncoder().withoutPadding().encodeToString(picByte64);

		voLogoutPhoto.setPhoto(logoutphoto);

		return voLogoutPhoto;
	}

	@Override
	public VoLogoutPhoto findByCitizenId(String citizenId) {
		return null;
	}

	@Override
	public String save(CitizenLogoutPhotos logoutPhoto) throws SerialException, SQLException, IOException {
		CitizenLogoutPhotos photo = logoutPhotoRepository.findByCdpfId(logoutPhoto.getCdpfId());

		if (photo == null) {
			logoutPhoto = logoutPhotoRepository.save(logoutPhoto);
			return logoutPhoto.getUuid();
		} else {
			photo.setPicture(logoutPhoto.getPicture());
			photo = logoutPhotoRepository.save(photo);
			return photo.getUuid();
		}

	}

	@Override
	public String update(CitizenLogoutPhotos logoutPhoto) {
		return null;
	}

	@Override
	@Transactional
	public String deleteByUuid(String uuid) throws SerialException, SQLException, IOException {
		logoutPhotoRepository.deleteByUuid(uuid);
		
		return uuid;
	}

}

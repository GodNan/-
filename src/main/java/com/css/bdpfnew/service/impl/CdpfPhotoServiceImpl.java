package com.css.bdpfnew.service.impl;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizen;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfPhoto;
import com.css.bdpfnew.model.vo.VoUnlockPhoto;
import com.css.bdpfnew.repository.CdpfPhotoRepository;
import com.css.bdpfnew.repository.CitizenRepository;
import com.css.bdpfnew.service.CdpfPhotoService;

/**
 * @Author HEYCH
 * @Date 2018年10月18日 下午4:19:16
 * @Description
 */

@Service
public class CdpfPhotoServiceImpl extends BaseServiceImpl<CdpfPhoto, Long> implements CdpfPhotoService {
	@Autowired
	private CdpfPhotoRepository cdpfPhotoRepository;

	@Autowired
	private CitizenRepository citizenRepository;

	@Autowired
	public void setBaseDao(CdpfPhotoRepository cdpfPhotoRepository) {
		super.setBaseDao(cdpfPhotoRepository);
	}

	@Override
	public CdpfPhoto findByCitizenId(String citizenId) {
		return cdpfPhotoRepository.findByCitizenId(citizenId);
	}

	@Override
	public VoUnlockPhoto findCitizenPhotoByCitizenId(String citizenId) throws SQLException {
		CdpfPhoto cdpfPhoto = cdpfPhotoRepository.findByCitizenId(citizenId);
		if (cdpfPhoto == null || cdpfPhoto.getData() == null) {
			return null;
		}

		VoUnlockPhoto voUnlockPhoto = new VoUnlockPhoto();
		BeanUtils.copyProperties(cdpfPhoto, voUnlockPhoto);

		Blob blob = cdpfPhoto.getData();
		byte[] picByte64;
		picByte64 = blob.getBytes(1, (int) blob.length());

		String citizenPhoto = Base64.getEncoder().withoutPadding().encodeToString(picByte64);

		voUnlockPhoto.setPhoto(citizenPhoto);

		return voUnlockPhoto;
	}

	@Override
	public VoUnlockPhoto findCardPhotoByCitizenId(String citizenId) throws SQLException {
		CdpfPhoto cdpfPhoto = cdpfPhotoRepository.findByCitizenId(citizenId);
		if (cdpfPhoto == null || cdpfPhoto.getPhotoTemp() == null) {
			return null;
		}

		VoUnlockPhoto voUnlockPhoto = new VoUnlockPhoto();
		BeanUtils.copyProperties(cdpfPhoto, voUnlockPhoto);

		Blob blob = cdpfPhoto.getPhotoTemp();
		byte[] picByte64;
		picByte64 = blob.getBytes(1, (int) blob.length());

		String cardPhoto = Base64.getEncoder().withoutPadding().encodeToString(picByte64);

		voUnlockPhoto.setPhoto(cardPhoto);

		return voUnlockPhoto;
	}


	@Override
	public VoUnlockPhoto findIdtPhotoOldByCitizenId(String citizenId) throws SQLException {
		CdpfPhoto cdpfPhoto = cdpfPhotoRepository.findByCitizenId(citizenId);
		if (cdpfPhoto == null || cdpfPhoto.getImageIdt() == null) {
			return null;
		}

		VoUnlockPhoto voUnlockPhoto = new VoUnlockPhoto();
		BeanUtils.copyProperties(cdpfPhoto, voUnlockPhoto);

		Blob blob = cdpfPhoto.getImageIdt();
		byte[] picByte64;
		picByte64 = blob.getBytes(1, (int) blob.length());

		String idtPhotoOld = Base64.getEncoder().withoutPadding().encodeToString(picByte64);

		voUnlockPhoto.setPhoto(idtPhotoOld);

		return voUnlockPhoto;
	}

	@Override
	public CdpfPhoto getByCdpfId(String cdpfId) {
		CdpfPhoto cdpfPhoto = cdpfPhotoRepository.findByCdpfid(cdpfId);
		return cdpfPhoto;
	}

	@Override
	public String save(CdpfPhoto cdpfPhoto) throws SerialException, SQLException, IOException {
		return null;
	}

	@Override
	public String cardPhotoSubmit(String cdpfId) {
		CdpfCitizen citizen = citizenRepository.findByUuid(cdpfId);

		citizen.setPhotoPushState(new Integer(0));

		citizenRepository.save(citizen);

		return citizen.getUuid();
	}

	@Override
	public String updateImageIdt(byte[] oldPhoto, String citizenId) {
		cdpfPhotoRepository.updateImageIdt(oldPhoto,citizenId);
		return null;
	}




}

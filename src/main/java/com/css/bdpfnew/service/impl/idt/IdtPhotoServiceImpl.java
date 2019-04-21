/**
 * 
 */
package com.css.bdpfnew.service.impl.idt;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.bdpfnew.model.dto.DtoIdtPhoto;
import com.css.bdpfnew.model.entity.bdpfnew.CitizenIdtPhoto;
import com.css.bdpfnew.model.vo.VoIdtPhoto;
import com.css.bdpfnew.repository.idt.IdtPhotoRepository;
import com.css.bdpfnew.service.idt.IdtPhotoService;
import com.css.bdpfnew.service.impl.BaseServiceImpl;

/**
 * @Author erDuo
 * @Date 2018年10月29日 上午10:45:24
 * @Description
 */

@Service
public class IdtPhotoServiceImpl extends BaseServiceImpl<CitizenIdtPhoto, Long> implements IdtPhotoService {

	@Autowired
	private IdtPhotoRepository idtPhotoRepository;

	@Autowired
	public void setBaseDao(IdtPhotoRepository IdtPhotoRepository) {
		super.setBaseDao(IdtPhotoRepository);
	}

	@Override
	public VoIdtPhoto findByUuid(String uuid) {
		CitizenIdtPhoto citizenIdtPhoto = idtPhotoRepository.findByUuid(uuid);
		if (citizenIdtPhoto == null) {
			return null;
		}
		VoIdtPhoto voIdtPhoto = new VoIdtPhoto();
		BeanUtils.copyProperties(citizenIdtPhoto, voIdtPhoto);

		return voIdtPhoto;
	}

	@Override
	public VoIdtPhoto findByIdtRecordUuidAndCitizenIdAndIdtkind(DtoIdtPhoto dtoIdtPhoto)
			throws SQLException, UnsupportedEncodingException {
		CitizenIdtPhoto citizenIdtPhoto = idtPhotoRepository.findByIdtRecordUuidAndCitizenIdAndIdtkind(
				dtoIdtPhoto.getIdtRecordUuid(), dtoIdtPhoto.getCitizenId(), dtoIdtPhoto.getIdtkind());
		if (citizenIdtPhoto == null || citizenIdtPhoto.getPicture() == null) {
			return null;
		}

		VoIdtPhoto voIdtPhoto = new VoIdtPhoto();
		BeanUtils.copyProperties(citizenIdtPhoto, voIdtPhoto);

		Blob blob = citizenIdtPhoto.getPicture();

		byte[] picByte64;
		picByte64 = blob.getBytes(1, (int) blob.length());

		String idtphoto = Base64.getEncoder().withoutPadding().encodeToString(picByte64);

		voIdtPhoto.setPhoto(idtphoto);

		return voIdtPhoto;
	}

	@Override
	public CitizenIdtPhoto findByRecordUuidAndCitizenIdAndIdtkind(DtoIdtPhoto dtoIdtPhoto) {
		return   idtPhotoRepository.findByIdtRecordUuidAndCitizenIdAndIdtkind(
				dtoIdtPhoto.getIdtRecordUuid(), dtoIdtPhoto.getCitizenId(), dtoIdtPhoto.getIdtkind());
	}

	@Override
	public String save(CitizenIdtPhoto idtPhoto) throws SerialException, SQLException, IOException {
		CitizenIdtPhoto photo = idtPhotoRepository.findByIdtRecordUuidAndCitizenIdAndIdtkind(
				idtPhoto.getIdtRecordUuid(), idtPhoto.getCitizenId(), idtPhoto.getIdtkind());

		if (photo == null) {
			idtPhoto = idtPhotoRepository.save(idtPhoto);
			return idtPhoto.getUuid();
		} else {
			photo.setPicture(idtPhoto.getPicture());
			photo = idtPhotoRepository.save(photo);
			return photo.getUuid();
		}

	}

	@Override
	public String update(CitizenIdtPhoto idtPhoto) {
		// TODO Auto-generated method stub
		return null;
	}



}

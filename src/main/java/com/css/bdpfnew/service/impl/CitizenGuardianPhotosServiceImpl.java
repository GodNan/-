/**
 * 
 */
package com.css.bdpfnew.service.impl;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.entity.bdpfnew.CitizenGuardianPhotos;
import com.css.bdpfnew.model.vo.VoUnlockPhoto;
import com.css.bdpfnew.repository.CitizenGuardianPhotosRepository;
import com.css.bdpfnew.service.CitizenGuardianPhotosService;

/**
 * @Author erDuo
 * @Date 2018年12月17日 下午7:11:43
 * @Description
 */
@Service
public class CitizenGuardianPhotosServiceImpl extends BaseServiceImpl<CitizenGuardianPhotos, Long>
		implements CitizenGuardianPhotosService {

	@Autowired
	private CitizenGuardianPhotosRepository citizenGuardianPhotosRepository;

	@Autowired
	public void setBaseDao(CitizenGuardianPhotosRepository citizenGuardianPhotosRepository) {
		super.setBaseDao(citizenGuardianPhotosRepository);
	}

	@Override
	public CitizenGuardianPhotos getByUuid(String uuid) {
		return citizenGuardianPhotosRepository.findByUuid(uuid);
	}

	@Override
	public List<VoUnlockPhoto> findByCdpfId(String cdpfId) throws SQLException {
		List<CitizenGuardianPhotos> cdpfPhotos = citizenGuardianPhotosRepository.findByCdpfid(cdpfId);
		if (cdpfPhotos == null || cdpfPhotos.size() == 0) {
			return null;
		}

		List<VoUnlockPhoto> voUnlockPhotos = new ArrayList<VoUnlockPhoto>();
		for (CitizenGuardianPhotos cdpfPhoto : cdpfPhotos) {
			VoUnlockPhoto voUnlockPhoto = new VoUnlockPhoto();
			BeanUtils.copyProperties(cdpfPhoto, voUnlockPhoto);
			if (cdpfPhoto.getPicture() != null) {
				Blob blob = cdpfPhoto.getPicture();
				byte[] picByte64;
				picByte64 = blob.getBytes(1, (int) blob.length());

				String photo = Base64.getEncoder().withoutPadding().encodeToString(picByte64);

				voUnlockPhoto.setPhoto(photo);
			}
			voUnlockPhotos.add(voUnlockPhoto);
		}

		return voUnlockPhotos;
	}

	@Override
	@Transactional
	public String save(CitizenGuardianPhotos cdpfPhoto) throws SerialException, SQLException, IOException {
		cdpfPhoto = citizenGuardianPhotosRepository.save(cdpfPhoto);
		return cdpfPhoto.getUuid();
	}

	@Override
	@Transactional
	public String deleteByUuid(String uuid) throws SerialException, SQLException, IOException {
		citizenGuardianPhotosRepository.deleteByUuid(uuid);
		return uuid;
	}


	@Override
	public Integer countByCdpfid(String cdpfid) {
		return citizenGuardianPhotosRepository.countByCdpfid(cdpfid);
	}

}

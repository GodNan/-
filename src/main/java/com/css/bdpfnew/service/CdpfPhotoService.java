package com.css.bdpfnew.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.entity.bdpfnew.CdpfPhoto;
import com.css.bdpfnew.model.vo.VoUnlockPhoto;

/**
 * @Author HEYCH
 * @Date 2018年10月18日 下午4:19:16
 * @Description
 */

public interface CdpfPhotoService extends BaseService<CdpfPhoto, Long> {
	CdpfPhoto findByCitizenId(String citizenId);

	VoUnlockPhoto findCardPhotoByCitizenId(String citizenId) throws SQLException;
	
	VoUnlockPhoto findCitizenPhotoByCitizenId(String citizenId) throws SQLException;
	
	VoUnlockPhoto findIdtPhotoOldByCitizenId(String citizenId) throws SQLException;

	CdpfPhoto getByCdpfId(String cdpfId);

	@Transactional
	String save(CdpfPhoto cdpfPhoto) throws SerialException, SQLException, IOException;

	@Transactional
	String cardPhotoSubmit(String cdpfId);
	@Transactional
	String updateImageIdt(byte[] oldPhoto, String citizenId);

}

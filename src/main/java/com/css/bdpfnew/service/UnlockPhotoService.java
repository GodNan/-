/**
 * 
 */
package com.css.bdpfnew.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.entity.bdpfnew.CitizenJiedongPhoto;
import com.css.bdpfnew.model.vo.VoUnlockPhoto;

/**
 * @Author erDuo
 * @Date 2018年12月9日 下午2:16:45
 * @Description
 */
public interface UnlockPhotoService extends BaseService<CitizenJiedongPhoto, Long> {

	VoUnlockPhoto findByUuid(String uuid);

	VoUnlockPhoto findByCdpfId(String cdpfId) throws SQLException;

	VoUnlockPhoto findByCitizenId(String citizenId);

	@Transactional
	String save(CitizenJiedongPhoto idtPhoto) throws SerialException, SQLException, IOException;

	@Transactional
	String update(CitizenJiedongPhoto idtPhoto);

}

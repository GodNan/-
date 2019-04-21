/**
 * 
 */
package com.css.bdpfnew.service;

import java.io.IOException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.entity.bdpfnew.CitizenLogoutPhotos;
import com.css.bdpfnew.model.vo.VoLogoutPhoto;

/**
 * @Author erDuo
 * @Date 2018年12月9日 下午2:16:45
 * @Description
 */
public interface LogoutPhotoService extends BaseService<CitizenLogoutPhotos, Long> {

	VoLogoutPhoto findByUuid(String uuid);

	VoLogoutPhoto findByCdpfId(String cdpfId) throws SQLException;

	VoLogoutPhoto findByCitizenId(String citizenId);

	@Transactional
	String save(CitizenLogoutPhotos idtPhoto) throws SerialException, SQLException, IOException;

	@Transactional
	String update(CitizenLogoutPhotos idtPhoto);

	@Transactional
	String deleteByUuid(String uuid) throws SerialException, SQLException, IOException;

}

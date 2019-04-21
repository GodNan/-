/**
 * 
 */
package com.css.bdpfnew.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.entity.bdpfnew.CitizenGuardianPhotos;
import com.css.bdpfnew.model.vo.VoUnlockPhoto;

/**
 * @Author erDuo
 * @Date 2018年12月17日 下午7:05:39
 * @Description
 */

public interface CitizenGuardianPhotosService extends BaseService<CitizenGuardianPhotos, Long> {

	List<VoUnlockPhoto> findByCdpfId(String cdpfId) throws SQLException;

	CitizenGuardianPhotos getByUuid(String uuid);
	
	Integer countByCdpfid(String cdpfid);

	@Transactional
	String save(CitizenGuardianPhotos cdpfPhoto) throws SerialException, SQLException, IOException;
	@Transactional
	String deleteByUuid(String uuid)  throws SerialException, SQLException, IOException;

}

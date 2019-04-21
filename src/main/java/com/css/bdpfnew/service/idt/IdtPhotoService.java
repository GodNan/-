/**
 * 
 */
package com.css.bdpfnew.service.idt;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.dto.DtoIdtPhoto;
import com.css.bdpfnew.model.entity.bdpfnew.CitizenIdtPhoto;
import com.css.bdpfnew.model.vo.VoIdtPhoto;
import com.css.bdpfnew.service.BaseService;

/**
 * @Author erDuo
 * @Date 2018年10月29日 上午10:39:57
 * @Description
 */
public interface IdtPhotoService extends BaseService<CitizenIdtPhoto, Long> {

	VoIdtPhoto findByUuid(String uuid);

	VoIdtPhoto findByIdtRecordUuidAndCitizenIdAndIdtkind(DtoIdtPhoto dtoIdtPhoto)
			throws SQLException, UnsupportedEncodingException;

	CitizenIdtPhoto   findByRecordUuidAndCitizenIdAndIdtkind(DtoIdtPhoto dtoIdtPhoto);

	@Transactional
	String save(CitizenIdtPhoto idtPhoto) throws SerialException, SQLException, IOException;

	@Transactional
	String update(CitizenIdtPhoto idtPhoto);

}

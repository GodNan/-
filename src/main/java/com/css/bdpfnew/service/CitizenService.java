package com.css.bdpfnew.service;

import java.util.List;
import java.util.Map;

import com.css.bdpfnew.model.page.PageBean;
import com.css.bdpfnew.model.vo.query.VoCitzen;
import com.css.bdpfnew.model.vo.query.VoCredentials;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import com.css.bdpfnew.model.dto.DtoCitizen;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCitizen;

/**
 * @Author erDuo
 * @Date 2018年6月22日 上午10:30:18
 * @Description:
 */

public interface CitizenService extends BaseService<CdpfCitizen, Long> {
	CdpfCitizen findByUuid(String uuid);

	@Transactional
	String save(DtoCitizen dtoCitizen);

	@Transactional
	String update(DtoCitizen dtoCitizen);
	
	List<CdpfCitizen> findByCityidLike(String cityid);

    List<Object[]> findByCredentials(VoCredentials credentials);
}

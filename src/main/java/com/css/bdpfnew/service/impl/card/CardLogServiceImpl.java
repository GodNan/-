/**
 * 
 */
package com.css.bdpfnew.service.impl.card;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.bdpfnew.model.entity.bdpfnew.CdpfCardLog;
import com.css.bdpfnew.model.vo.VoCdpfCardLog;
import com.css.bdpfnew.repository.card.CdpfCardLogRepository;
import com.css.bdpfnew.service.card.CardLogService;
import com.css.bdpfnew.service.impl.BaseServiceImpl;

/**
 * @Author erDuo
 * @Date 2018年9月21日 下午4:24:09
 * @Description
 */

@Service
public class CardLogServiceImpl extends BaseServiceImpl<CdpfCardLog, Long> implements CardLogService {
	@Autowired
	private CdpfCardLogRepository cdpfCardLogRepository;

	@Autowired
	public void setBaseDao(CdpfCardLogRepository cdpfCardLogRepository) {
		super.setBaseDao(cdpfCardLogRepository);
	}

	@Override
	public VoCdpfCardLog findByUuid(String uuid) {

		CdpfCardLog cardLog = cdpfCardLogRepository.findByUuid(uuid);
		if (cardLog == null) {
			return null;
		}
		VoCdpfCardLog voCardLog = new VoCdpfCardLog();
		BeanUtils.copyProperties(cardLog, voCardLog);
		return voCardLog;
	}

}

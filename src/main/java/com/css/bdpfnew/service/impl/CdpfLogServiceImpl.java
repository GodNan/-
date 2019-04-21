/**
 * 
 */
package com.css.bdpfnew.service.impl;

import java.util.Calendar;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.bdpfnew.constant.Constants;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfLog;
import com.css.bdpfnew.model.entity.bdpfnew.SysUser;
import com.css.bdpfnew.repository.CdpfLogRepository;
import com.css.bdpfnew.service.CdpfLogService;

/**
 * @Author HEYCH
 * @Date 2018年12月19日
 * @Description
 */

@Service
public class CdpfLogServiceImpl extends BaseServiceImpl<CdpfLog, Long>  implements CdpfLogService {

	@Autowired
	private CdpfLogRepository cdpfLogRepository;
	
	@Autowired
	public void setBaseDao(CdpfLogRepository cdpfLogRepository) {
		super.setBaseDao(cdpfLogRepository);
	}

	@Override
	public boolean create(String cdpfId, String requestId, String logData, Integer logType, Integer opertype) {
		
		SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
		CdpfLog clog = new CdpfLog();
		clog.setCdpfId(cdpfId);
		clog.setRequestId(requestId);
		clog.setLogData(logData);
		clog.setLogType(logType);
		clog.setOpertype(opertype);  //d_oper_type：1新添，  2修改， 3删除，  4打印， 5其它操作
		clog.setOperid(user.getUuid());
		clog.setOpername(user.getNickname());
		clog.setUpdateFlag(new Integer(1));
		clog.setEdittime(Calendar.getInstance().getTime());
		cdpfLogRepository.save(clog);
		return false;
	}

}
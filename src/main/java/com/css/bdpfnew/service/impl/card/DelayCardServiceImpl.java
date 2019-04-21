package com.css.bdpfnew.service.impl.card;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.css.bdpfnew.constant.Constants;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCard;
import com.css.bdpfnew.model.entity.bdpfnew.CdpfCardLog;
import com.css.bdpfnew.model.entity.bdpfnew.SysUser;
import com.css.bdpfnew.repository.card.CdpfCardRepository;
import com.css.bdpfnew.service.CdpfLogService;
import com.css.bdpfnew.service.card.DelayCardService;

/**
 * @Author HEYCH
 * @Date 2018年10月28日
 * @Description:
 */
@Service
public class DelayCardServiceImpl implements DelayCardService {

	@Autowired
	private CdpfCardRepository cdpfCardRepository;
	@Autowired
	private CdpfLogService cdpfLogService;

	@Override
	public boolean update(String requestIdCard) throws ParseException {
		
		SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(Constants.SESSION_USER_INFO);
		CdpfCard card = cdpfCardRepository.findByRequestId(requestIdCard);
		
		Calendar curr = Calendar.getInstance();
		curr.add(Calendar.DATE,365);
		Date date=curr.getTime();
		card.setCarddeadline(date);
        cdpfCardRepository.save(card);
        
        //添加卡延期日志
		CdpfCardLog cardLog = new CdpfCardLog();
		cardLog.setRequestId(card.getRequestId());
		cardLog.setCdpfId(card.getCdpfId());
		cardLog.setCardStatus(new Integer(340));
		cardLog.setCitizenId(card.getCitizenId());
		cardLog.setPcno(card.getPcno());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String carddeadline = formatter.format(card.getCarddeadline());
		cardLog.setLogdata("卡延期成功，卡到期时间：" + carddeadline);
		cardLog.setOpername(user.getNickname());
		
		//操作日志
		String cdpfIdStr = card.getCdpfId(); 
		String requestIdStr = card.getRequestId();
		String logData = "一卡通号："+card.getCardNo();
		Integer logType = new Integer(116);
		Integer opertype = new Integer(5);
		cdpfLogService.create(cdpfIdStr, requestIdStr, logData, logType, opertype);
        
		return true;
	}
}

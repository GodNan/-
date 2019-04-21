/**
 * 
 */
package com.css.bdpfnew.model.entity.bdpfnew.view;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.util.StringUtils;

import com.css.bdpfnew.util.DateUtil;

import lombok.Data;

/**
 * @Author erDuo
 * @Date 2018年10月4日 下午12:09:41
 * @Description
 */

@Entity
@Table(name = "CARD_FOR_DELAYS", schema = "bdpfnew2018")
@Data
public class CardForDelays implements Serializable {

	private static final long serialVersionUID = -8220856535814368110L;
	@Id
	private String uuid;
	private String requestId;
	private String cdpfId;
	private String citizenId;
	private String name;
	private String cityid;
	private String jiguanCode;
	private Date carddeadline;

	@Transient
	public String getHandleDate() {
		String dateString = "";
		if (!StringUtils.isEmpty(this.getCarddeadline())) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dateString = formatter.format(this.getCarddeadline());
		}
		return dateString;
	}

	@Transient
	public String getExpiredDays() {
		Date nowDate = new Date();
		if (!StringUtils.isEmpty(this.getCarddeadline()) && nowDate.after(this.getCarddeadline())) {
			long between = DateUtil.daysBetween(this.getCarddeadline(), new Date());
			return String.valueOf(between);
		}else if (StringUtils.isEmpty(this.getCarddeadline())) {
			return "数据缺失";
		}else {
			return "未过期";
		}
	}

}
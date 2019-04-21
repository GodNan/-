/**
 * 
 */
package com.css.bdpfnew.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author erDuo
 * @Date 2018年9月20日 下午2:41:24
 * @Description
 */
public class PcnoUtil {

	public static String getPcno() {
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateNowStr = sdf.format(now);

		return dateNowStr;
	}

}

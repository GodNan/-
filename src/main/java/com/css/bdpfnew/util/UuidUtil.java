package com.css.bdpfnew.util;

import java.util.UUID;

/**
 * @author Administrator TODO To change the template for this generated type
 *         comment go to Window - Preferences - Java - Code Style - Code
 *         Templates
 */
public class UuidUtil {
	public static final String getUuid() {
		String uuid = UUID.randomUUID().toString();
		return uuid.replaceAll("-", "");
	}
}
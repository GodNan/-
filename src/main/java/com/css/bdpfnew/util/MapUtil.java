package com.css.bdpfnew.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author erDuo
 * @Date 2018年7月31日 上午11:16:18
 * @Description:
 */
public class MapUtil {
	public static Map<Integer, Integer> getModuleMap(String s) {
		Map<Integer, Integer> modMap = null;
		if (StringUtils.isNotEmpty(s)) {
			modMap = new LinkedHashMap<Integer, Integer>();
			String[] mod = s.split(",");
			for (int i = 0; i < mod.length; i++) {
				mod[i] = mod[i].trim();
				modMap.put(new Integer(mod[i]), new Integer(mod[i]));
			}
		}
		return modMap;
	}

	public static Map<Integer, Integer> getModuleMapFromLevel(String s) {
		Map<Integer, Integer> modMap = null;
		if (StringUtils.isNotEmpty(s)) {
			modMap = new LinkedHashMap<Integer, Integer>();
			String[] mod = s.split(",");
			for (int i = 0; i < mod.length; i = i + 2) {
				mod[i] = mod[i].trim();
				modMap.put(new Integer(mod[i]), new Integer(mod[i]));
			}
		}
		return modMap;
	}

	public static String level2kind(String s) {
		String str = "";
		if (StringUtils.isNotEmpty(s)) {
			String[] mod = s.split(",");
			for (int i = 0; i < mod.length; i = i + 2) {
				mod[i] = mod[i].trim();
				str += mod[i] + (i == mod.length - 2 ? "" : ",");
			}

		}
		return str;
	}

	public static Integer checkModule(Map<Integer, Integer> m, Object key) {
		if (key == null || m == null)
			return null;
		Integer skey = (Integer) key;
		return (Integer) m.get(skey);
	}

	public static void main(String[] args) {
		try {
			System.out.println(UuidUtil.getUuid());
		} catch (Exception ex) {
			System.out.print(ex.getMessage());
		}
	}
}

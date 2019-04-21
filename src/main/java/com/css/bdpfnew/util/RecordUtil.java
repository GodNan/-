package com.css.bdpfnew.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author erDuo
 * @Date 2018年7月31日 上午11:02:23
 * @Description:
 */
public class RecordUtil {

	// oldStr表示记录的原有的kindStr
	private String oldStr;
	// newStr表示提交的新的kingStr
	private String newStr;
	// oMap表示原有的map
	private Map<Integer, Integer> oMap = null;
	// oMap表示新的map
	private Map<Integer, Integer> nMap = null;
	// 添加map
	private Map<Integer, Integer> addMap = null;
	// 删除map
	private Map<Integer, Integer> delMap = null;
	// 修改map
	private Map<Integer, Integer> updateMap = null;

	public RecordUtil(String oldStr, String newStr) {
		this.oldStr = oldStr;
		this.newStr = newStr;
		// oldStr、newStr都为空，则返回。
		if (StringUtils.isEmpty(this.oldStr) && StringUtils.isEmpty(this.newStr))
			return;
		// 如果oldStr为空，则addMap为newStr构造的map。
		if (StringUtils.isEmpty(this.oldStr)) {
			addMap = MapUtil.getModuleMap(newStr);
			return;
		}
		// 如果newStr为空，则delMap为oldStr，表示将原有的所有残疾类别都删除掉。
		if (StringUtils.isEmpty(this.newStr)) {
			delMap = MapUtil.getModuleMap(oldStr);
			return;
		}
		// 如果oldStr与newStr的值相等，则updateMap设置为oldStr构造的map。
		if (this.oldStr.equals(this.newStr)) {
			updateMap = MapUtil.getModuleMap(oldStr);
			return;
		}
		addMap = new HashMap<Integer, Integer>();
		updateMap = new HashMap<Integer, Integer>();
		oMap = MapUtil.getModuleMap(oldStr);
		nMap = MapUtil.getModuleMap(newStr);
		Iterator<Integer> iter = nMap.values().iterator();
		while (iter.hasNext()) {
			String val = String.valueOf((Integer) iter.next());
			Integer iVal = (Integer) oMap.get(val);
			if (iVal == null)
				addMap.put(new Integer(val), new Integer(val));
			else {
				updateMap.put(iVal, iVal);
				oMap.remove(String.valueOf(iVal));
			}
		}
		delMap = oMap;
		if (addMap.size() < 1)
			addMap = null;
		if (updateMap.size() < 1)
			updateMap = null;
		if (delMap.size() < 1)
			delMap = null;
	}

	public Map<Integer, Integer> getAddMap() {
		return addMap;
	}

	public void setAddMap(Map<Integer, Integer> addMap) {
		this.addMap = addMap;
	}

	public Map<Integer, Integer> getDelMap() {
		return delMap;
	}

	public void setDelMap(Map<Integer, Integer> delMap) {
		this.delMap = delMap;
	}

	public Map<Integer, Integer> getUpdateMap() {
		return updateMap;
	}

	public void setUpdateMap(Map<Integer, Integer> updateMap) {
		this.updateMap = updateMap;
	}

}

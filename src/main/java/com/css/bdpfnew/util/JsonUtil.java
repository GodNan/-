package com.css.bdpfnew.util;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

/**
 * 用于JSON格式读写操作的工具类
 * 
 * @version 1.0
 * 
 */
@SuppressWarnings("unchecked")
public class JsonUtil {

	private static GsonBuilder gbuilder = new GsonBuilder()
			.disableHtmlEscaping();// 不转义html标记
	private static Gson gson = gbuilder.setDateFormat("yyyy-MM-dd HH:mm:ss")
			.create();// 格式时间
	private static JsonParser jsonParser = new JsonParser();// JSON解析器

	/**
	 * 转换json串为bean对象
	 * 
	 * @param jsonStr
	 *            指定JSON字符串
	 * @param beanClazz
	 *            指定转换类
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object deserializeToBean(String jsonStr, Class beanClazz) {
		return gson.fromJson(transJsonTime(jsonStr), beanClazz);
	}

	/**
	 * 转换json串为bean对象
	 * 
	 * @param jsonStr
	 *            指定JSON字符串
	 * @param type
	 *            指定转换类型 java.lang.reflect.Type
	 * @return
	 */
	public static Object deserializeToBean(String jsonStr, Type type) {
		return gson.fromJson(transJsonTime(jsonStr), type);
	}

	/**
	 * 转换类型为String的json串为beanList
	 * 
	 * @param jsonStr
	 *            指定JSON字符串
	 * @param type
	 *            指定转换类型 java.lang.reflect.Type
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static List deserializeToBeanList(String jsonStr, Type type) {
		// return JSONArray.toList(JSONArray.fromObject(jsonStr),beanClazz);
		return gson.fromJson(transJsonTime(jsonStr), type);
	}

	/**
	 * 转换类型为obj的json对象为list
	 * 
	 * @param bean
	 *            指定JavaBean java.lang.Object
	 * @param type
	 *            指定转换类型 java.lang.reflect.Type
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static List deserializeObjToBeanList(Object bean, Type type) {
		// return JSONArray.toList(JSONArray.fromObject(obj),beanClazz);
		return gson.fromJson(transJsonTime(bean.toString()), type);
	}

	/**
	 * 转换bean为json串
	 * 
	 * @param bean
	 *            指定JavaBean java.lang.Object
	 * @param type
	 *            指定转换类型 java.lang.reflect.Type
	 * @return
	 */
	public static String serializeFromBean(Object bean, Type type) {
		return gson.toJson(bean, type);
	}

	/**
	 * 转换bean为json串
	 * 
	 * @param 指定JavaBean
	 *            java.lang.Object
	 * @return
	 */
	public static String serializeFromBean(Object bean) {
		// return JSONObject.fromObject(bean).toString();
		return gson.toJson(bean);
	}

	/**
	 * 转换beanList为json串
	 * 
	 * @param beanList
	 *            指定JavaBean的List集合
	 * @param type
	 *            指定转换类型 java.lang.reflect.Type
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String serializeFromBeanList(List beanList, Type type) {
		return gson.toJson(beanList);
	}

	/**
	 * 转换beanList为json串
	 * 
	 * @param beanList
	 *            指定JavaBean的List集合
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String serializeFromBeanList(List beanList) {
		return gson.toJson(beanList);
	}

	/**
	 * 转换json date为"yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param str
	 *            指定JSON字符串，带Long数字型数据
	 * @return
	 */
	public static String transJsonTime(String str) {
		String regex = "\\\"createDt\\\":\\{([^\\{\\}:]+?:[^\\{\\}]+?){9}\\}";
		@SuppressWarnings("unused")
		long begin = System.currentTimeMillis();
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);// 忽略大小写
		Matcher matcher = pattern.matcher(str);
		if (matcher.find()) {
			str = matcher.replaceFirst(replaceJsonDateFormat(matcher.group()));
			str = transJsonTime(str);
		}
		return str;
	}

	@SuppressWarnings("rawtypes")
	private static String replaceJsonDateFormat(String jsonStr) {
		int position = jsonStr.indexOf(":{") + 1;
		String prefix = jsonStr.substring(0, position);
		Map<String, String> map = (HashMap) JsonUtil.deserializeToBean(jsonStr
				.substring(position), new TypeToken<HashMap<String, String>>() {
		}.getType());
		// Date Set
		Date d = new Date(Long.valueOf(map.get("time")));
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return prefix + "\"" + df.format(d) + "\"";
	}

	private static JsonElement getJsonElement(String jsonStr) {
		return jsonParser.parse(jsonStr);
	}

	private static JsonObject getJsonObject(String jsonStr) {
		JsonObject jo = null;
		JsonElement ele = getJsonElement(jsonStr);
		if (ele.isJsonObject()) {
			jo = ele.getAsJsonObject();
		}
		return jo;
	}

	/**
	 * 得到对象的数值(JsonObject)
	 * 
	 * @param jsonStr
	 *            指定的JSON字符串
	 * @param key
	 *            指定的Key
	 * @return
	 */
	public static Object getObject(String jsonStr, String key) {
		JsonObject jo = getJsonObject(jsonStr);
		if (jo.isJsonNull() || jo == null) {
			return null;
		} else {
			return jo.get(key);
		}
	}

	/**
	 * 得到对象的数值(JsonObject)
	 * 
	 * @param jsonStr
	 *            指定的JSON字符串
	 * @param key
	 *            指定的Key
	 * @return
	 */
	public static String getValue(String jsonStr, String key) {
		Object obj = getObject(jsonStr, key);
		String str = String.valueOf(obj == null ? "" : obj);
		if (str != null && !"".equals(str)) {
			if (str.indexOf("\"") == 0) {
				str = str.substring(1, str.length()); // 去掉开头一个"
			}
			if (str.lastIndexOf("\"") == (str.length() - 1)) {
				str = str.substring(0, str.length() - 1); // 去掉最后一个"
			}
		}
		return str;
	}

	/**
	 * 得到对象的集合(JsonArray)
	 * 
	 * @param jsonStr
	 *            指定的JSON字符串
	 * @param key
	 *            指定的Key
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static List getList(String jsonStr, String key) {
		jsonStr = getValue(jsonStr, key);
		if (jsonStr == null || "".equals(jsonStr)) {
			return null;
		} else {
			return getList(jsonStr);
		}
	}

	/**
	 * 得到对象的集合(JsonArray)
	 * 
	 * @param jsonStr
	 *            指定的JSON字符串
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static List getList(String jsonStr) {
		List list = null;
		JsonElement je = getJsonElement(jsonStr);
		if (je != null && je.isJsonArray()) {
			JsonArray ja = je.getAsJsonArray();
			if (ja.size() > 0) {
				list = new ArrayList<JsonElement>();
				for (int i = 0; i < ja.size(); i++) {
					list.add(ja.get(i));
				}
			}
			ja = null;
		}
		return list;
	}

	/*public static void main(String[] args) throws Exception {

		// String jsonStr =
		// "{\"id\":222,\"gname\":\"1243124\"},\"items\":{[\"id\":33,\"name\":\"cailiang33\"],[\"id\":22,\"name\":\"cailiang22\"]}";
		// String jsonStr =
		// "{id:222,gname:\"1243124\",items:{[id:33,name:\"cailiang33\"],[id:22,name:\"cailiang22\"]}";
		String jsonStr = "{id:222,gname:\"1243124\"}";

		System.out.println(getObject(jsonStr, "gname"));
	}*/
}

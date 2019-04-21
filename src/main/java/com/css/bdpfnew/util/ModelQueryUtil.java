package com.css.bdpfnew.util;

import java.lang.reflect.Field;

public class ModelQueryUtil {
	public static final char UNDERLINE = '_';

	public static void main(String[] args) throws Exception {
		// whereQueryForMybatis("com.css.bdpfnew.model.query.CitizenQuery");

		showDataJSON("com.css.bdpfnew.model.entity.bdpfnew.Request");

	}
	
	public static void showDataJSON(String classPcakageName) throws Exception {
		Class<?> clazz = Class.forName(classPcakageName);
		StringBuilder sb = new StringBuilder();

		// 取得本类的全部属性
		Field[] field = clazz.getDeclaredFields();
		for (int i = 0; i < field.length; i++) {
			String prop = field[i].getName();

			if (i == 0) {
				sb.append(prop + ":\"\"\n");
			} else {
				sb.append("," + prop + ":\"\"\n");
			}
		}
		String whereCol = sb.toString();

		System.out.println(whereCol);
	}
	

	public static void showWhereCol(String classPcakageName) throws Exception {
		Class<?> clazz = Class.forName(classPcakageName);
		StringBuilder sb = new StringBuilder();

		// 取得本类的全部属性
		Field[] field = clazz.getDeclaredFields();
		for (int i = 0; i < field.length; i++) {
			String prop = field[i].getName();

			if (i == 0) {
				sb.append(prop + "\n");
			} else {
				sb.append("," + prop + "\n");
			}
		}
		String whereCol = sb.toString();

		System.out.println(whereCol);
	}

	public static void showWhereColDB(String classPcakageName) throws Exception {
		Class<?> clazz = Class.forName(classPcakageName);
		StringBuilder sb = new StringBuilder();

		// 取得本类的全部属性
		Field[] field = clazz.getDeclaredFields();
		for (int i = 0; i < field.length; i++) {
			String propDB = camelToUnderline(field[i].getName());

			if (i == 0) {
				sb.append(propDB);
			} else {
				sb.append("," + propDB);
			}
		}
		String whereCol = sb.toString();

		System.out.println(whereCol);
	}

	public static void whereQueryForMybatis(String classPcakageName) throws Exception {

		Class<?> clazz = Class.forName(classPcakageName);
		StringBuilder sb = new StringBuilder();

		// 取得本类的全部属性
		Field[] field = clazz.getDeclaredFields();
		for (int i = 0; i < field.length; i++) {
			String prop = field[i].getName();
			String propDB = camelToUnderline(field[i].getName());

			sb.append("<if test=\"");
			sb.append(prop + " != null\">");
			if (i == 0) {
				if (field[i].getType().toString().endsWith("Date")) {
					sb.append("to_char(" + propDB + ",'yyyyMMdd')" + " = #{" + prop + "}");
				}
				sb.append(propDB + " = #{" + prop + "}");
			} else {
				if (field[i].getType().toString().endsWith("Date")) {
					sb.append("and to_char(" + propDB + ",'yyyyMMdd')" + " = #{" + prop + "}");
				} else {
					sb.append("and " + propDB + " = #{" + prop + "}");
				}
			}
			sb.append("</if>\n");

		}
		String where = sb.toString();
		System.out.println(where);
	}

	public static String camelToUnderline(String param) {
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (Character.isUpperCase(c)) {
				sb.append(UNDERLINE);
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String lowerName(String name) {
		char[] cs = name.toCharArray();
		cs[0] += 32;
		return String.valueOf(cs);

	}
}

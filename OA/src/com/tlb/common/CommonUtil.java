package com.tlb.common;

import java.util.List;
import java.util.UUID;

public class CommonUtil {
	
	/**
	 * 随机获取UUID字符串(无中划线).
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
	}
	
	/**
	 * 把固定格式“[0,1,2,3...]”数组字符串转换整型数组
	 * @param strArr 固定格式的数组字符串
	 * @return 整型数组
	 */
	public static Integer[] strArrToIntArr(String strArr) {
		String[] array = strArr.substring(1, strArr.length() - 1).split(",");
		Integer[] intArr = new Integer[array.length];
		for(int i=0;i<array.length;i++) {
			intArr[i] = Integer.parseInt(array[i]);
		}
		return intArr;
	}
	
	/**
	 * 把固定格式“[0.00,1.00,2.00,3.00...]”数组字符串转换浮点型数组
	 * @param strArr 固定格式的数组字符串
	 * @return 浮点型数组
	 */
	public static Double[] strArrToDoubleArr(String strArr) {
		String[] array = strArr.substring(1, strArr.length() - 1).split(",");
		Double[] intArr = new Double[array.length];
		for(int i=0;i<array.length;i++) {
			intArr[i] = Double.parseDouble(array[i]);
		}
		return intArr;
	}
	
	/**
	 * 转换分号字符串为数据库逗号字符串.(1;2;3;4 -> '1','2','3','4')
	 */
	public static String transToStrId(String Ids) {
		if (Ids == null) return "";
		String[] cls = Ids.split(";");
		String res = "";
		for (String cl : cls) {
			if (NullUtils.isEmpty(res)) {
				res = "'" + cl + "'";
			} else {
				res += ",'" + cl + "'";
			}
		}
		return res;
	}
	
	/**
	 * 转换sql语句In参数格式
	 */
	public static String toSQLIn (List<String> params) {
		if(params == null || params.size() == 0) return "(' ')";
		String in = " (";
		for (String param : params) {
			in += "'"+param+"'" + ",";
		}
		in = in.substring(0, in.lastIndexOf(",")) + ")";
		return in;
	}
	
	/**
	 * 转换sql语句In参数格式
	 */
	public static String toSQLIn (String[] params) {
		if(params == null || params.length == 0) return "(' ')";
		String in = " (";
		for (String param : params) {
			in += "'"+param+"'" + ",";
		}
		in = in.substring(0, in.lastIndexOf(",")) + ")";
		return in;
	}
	
}

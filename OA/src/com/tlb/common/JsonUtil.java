package com.tlb.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
@SuppressWarnings({ "rawtypes", "unchecked" })
public class JsonUtil {
	/**
	 * JSON字符串Array
	 */
	public static String toString(final Object obj, final String ...fields) {
		JsonConfig config = new JsonConfig();
		config.setExcludes(fields);
		JSONArray json = JSONArray.fromObject(obj, config);
		return json.toString();
	}
	
	/**
	 * JSON字符串Array 排除NULL的属性
	 */
	public static String toStringExcludeNull(final Object obj, final String ...fields) {
		JsonConfig config = new JsonConfig();
		config.setExcludes(fields);
	    PropertyFilter filter = new PropertyFilter() {
            public boolean apply(Object object, String fieldName, Object fieldValue) {
            	return null == fieldValue;
            }
	    };
	    config.setJsonPropertyFilter(filter);
		JSONArray json = JSONArray.fromObject(obj, config);
		return json.toString();
	}
	
	/**
	 * JSON字符串Array(排序某些字段)
	 */
	public static String toStringExclusions(final Object obj, final String ...fields) {
		JsonConfig config = new JsonConfig();
		config.setExcludes(fields);
		JSONArray json = JSONArray.fromObject(obj, config);
		return json.toString();
	}
	
	/**
	 * JSON字符串Object
	 */
	public static String toStringFromObject(final Object obj) {
		JSONObject json = JSONObject.fromObject(obj);
		return json.toString();
	}
	
	/**
	 * JSON字符串Object 排除NULL的属性
	 */
	public static String toStringFromObjectExcludeNull(final Object obj) {
		JsonConfig config = new JsonConfig();
	    PropertyFilter filter = new PropertyFilter() {
            public boolean apply(Object object, String fieldName, Object fieldValue) {
            	return null == fieldValue;
            }
	    };
	    config.setJsonPropertyFilter(filter);
		JSONObject json = JSONObject.fromObject(obj);
		return json.toString();
	}
	
	/**
	 * 返回结果字符串.
	 */
	public static String toRes(String title, String msg, Map<String, Object> ...res) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("success", true);
		resMap.put("title", title);
		resMap.put("msg", msg);
		if (res != null && res.length > 0) {
			resMap.putAll(res[0]);
		}
		return JsonUtil.toStringFromObject(resMap);
	}
	
	/**
	 * 返回结果字符串.
	 */
	public static String toRes(String title, Map<String, Object> ...res) {
		return toRes(title, "", res);
	}
	
	/**
	 * 返回结果字符串(失败).
	 */
	public static String toResOfFail(String title, Map<String, Object> ...res) {
		return toResOfFail(title, "", res);
	}
	
	/**
	 * 返回结果字符串(失败).
	 */
	public static String toResOfFail(String title, String msg, Map<String, Object> ...res) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("success", false);
		resMap.put("title", title);
		resMap.put("msg", msg);
		if (res != null && res.length > 0) {
			resMap.putAll(res[0]);
		}
		return JsonUtil.toStringFromObject(resMap);
	}
	
	/**
	 * 根据json字符串转List<Class>
	 */
	public static List getListForJsonString(String jsonString, Class clasz, Map<String, Class<?>> classMap) {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        JSONObject jsonObject = null;
        Object pojoValue = null;
        List list = new ArrayList();  
        for ( int i = 0 ; i<jsonArray.size(); i++) {  
            jsonObject = jsonArray.getJSONObject(i);  
            pojoValue = JSONObject.toBean(jsonObject, clasz, classMap);
            list.add(pojoValue);  
        }  
        return list;  
    }
	
	/**
	 * 根据json字符串转Map
	 */
	public static HashMap<String, String> toHashMap(Object object) {  
        HashMap<String, String> data = new HashMap<String, String>();  
        JSONArray  jsonArray = JSONArray.fromObject(object);  
        if(jsonArray.size() > 0) {
	    	  for(int i=0;i<jsonArray.size();i++) {
	    	    JSONObject jsonObject = jsonArray.getJSONObject(i);
	    	    Iterator it = jsonObject.keys();  
		    	    while (it.hasNext()) {  
		    	    	String key = String.valueOf(it.next());  
		    	    	String value = (String) jsonObject.get(key);  
		    	    	data.put(key, value);  
		    	    }
	    	  }
    	}
        return data;  
    }
	
	/**
	 * 返回结果.
	 */
	public static Map<String, Object> toHint(String message) {
		Map<String, Object> hm = new HashMap<String, Object>();
		hm.put("message", message);
		return hm;
	}
}

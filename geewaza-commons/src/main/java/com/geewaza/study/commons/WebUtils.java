package com.geewaza.study.commons;

import org.json.JSONException;
import org.json.JSONObject;

public class WebUtils {
	
	public static String buildErrorMsg(String errorCode, String desc, String provider) {
		  StringBuilder builder = new StringBuilder();
		  builder.append("{\"e\": {\"provider\":\"").append(provider).append("\",\"code\":\"").append(errorCode).append("\",\"desc\":\"").append(desc).append("\"}}");
		  return builder.toString();
	}
	
	public static JSONObject buildErrorJSON(String errorCode, String desc, String provider) throws JSONException {
		JSONObject e = new 	JSONObject();
		e.put("code", errorCode);
		e.put("desc", desc);
		e.put("provider", provider);
		return e;
	}

}

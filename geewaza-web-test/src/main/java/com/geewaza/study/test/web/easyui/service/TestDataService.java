package com.geewaza.study.test.web.easyui.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.geewaza.study.commons.DataFormat;
import com.geewaza.study.commons.WebUtils;
import com.geewaza.study.test.web.easyui.pojo.PromoteWord;
import com.geewaza.study.test.web.easyui.common.State;
import com.geewaza.study.test.web.service.AbstractService;

@Component("testDataService")
public class TestDataService extends AbstractService {
	private static Logger logger = LoggerFactory.getLogger(TestDataService.class);

	@Override
	public void doService(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String fun = request.getParameter("fun");
		if (StringUtils.isBlank(fun)) {
			response.getWriter().print(WebUtils.buildErrorMsg("1", "不是有效的fun", "TestDataService"));
			response.getWriter().flush();
			logger.error("fun = " + fun);
			return;
		}
		
		if ("query_promote".equals(fun)) {
			JSONObject resultJSON = new JSONObject();
			JSONObject data = queryPromote();
			resultJSON.put("e", WebUtils.buildErrorJSON("0", "状态保存成功", "TestDataService"));
			resultJSON.put("data", data);
			response.getWriter().print(data.toString());
			response.getWriter().flush();
		}

	}

	/**
	 * 构建一堆promote数据
	 * @return
	 * @throws JSONException 
	 * @throws NumberFormatException 
	 */
	private JSONObject queryPromote() throws NumberFormatException, JSONException {
		String[][] templet = {
				{"1", "word1", "1000000", "1", "0", "2015-09-12", "2015-10-12", "0", "wangheng", "2015-09-13 00:00:00"},
				{"2", "word2", "1000000", "1", "0", "2015-09-12", "2015-10-12", "0", "wangheng", "2015-09-13 00:00:00"},
				{"3", "word3", "1000000", "1", "0", "2015-09-12", "2015-10-12", "0", "wangheng", "2015-09-13 00:00:00"},
				{"4", "word4", "1000000", "1", "0", "2015-09-12", "2015-10-12", "0", "wangheng", "2015-09-13 00:00:00"},
		};
		JSONArray list = new JSONArray();
		for (String[] dataItem : templet) {
			PromoteWord word = new PromoteWord();
			word.setId(Integer.parseInt(dataItem[0]));
			word.setWord(dataItem[1]);
			word.setQueryCount(Long.parseLong(dataItem[2]));
			word.setCateId(Integer.parseInt(dataItem[3]));
			word.setSite(Integer.parseInt(dataItem[4]));
			word.setStartTime(DataFormat.formatDate(dataItem[5]));
			word.setEndTime(DataFormat.formatDate(dataItem[6]));
			word.setState(State.getWordState(DataFormat.formatDate(dataItem[5]), DataFormat.formatDate(dataItem[6]), new Date()).getValue());
			word.setEditor(dataItem[8]);
			word.setLastUpdate(DataFormat.formatDate(dataItem[9]));
			list.put(new JSONObject(word));
		}
		JSONObject resultObject = new JSONObject();
		resultObject.put("total", templet.length);
		resultObject.put("rows", list);
		return resultObject;
	}

}

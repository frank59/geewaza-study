package com.geewaza.study.test.web.service;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by WangHeng on 2016/2/1.
 */
@Component("costTimeService")
public class CostTimeService extends AbstractService {
	private static Logger logger = LoggerFactory.getLogger(CostTimeService.class);

	@Override
	public void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Random random = new Random();
		int randomValue = random.nextInt(5) + 1;
		int sleepTime = randomValue * 400;
		String key = request.getParameter("key");
		PrintWriter out = response.getWriter();
		try {
			logger.info("key = " + key + " sleep " + sleepTime + "ms");
			TimeUnit.MILLISECONDS.sleep(sleepTime);
			JSONObject responseObject = new JSONObject();
			responseObject.put("cost", sleepTime);
			responseObject.put("data",key);
			out.write(responseObject.toString());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			out.flush();
			out.close();
		}

	}
}

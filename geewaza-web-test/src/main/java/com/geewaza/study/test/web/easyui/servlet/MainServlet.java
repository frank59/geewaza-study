package com.geewaza.study.test.web.easyui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.geewaza.study.test.web.easyui.service.AbstractService;
import com.geewaza.study.test.web.easyui.util.CommenUtils;


/**
 * Servlet implementation class MainServlet
 */
public class MainServlet extends HttpServlet {
	
	private static Logger logger = LoggerFactory.getLogger(MainServlet.class);
       
	private static final long serialVersionUID = -5161699329411048022L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain;charset=utf-8");
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		String serviceName = request.getServletPath().replaceAll("\\.do", "").substring(1);
		logger.info("serviceName=" + serviceName);
		if (StringUtils.isNotBlank(serviceName)) {
			AbstractService service = null;
			try {
				service = (AbstractService) ctx.getBean(serviceName);
			} catch (Exception e) {
				response.getWriter().print(CommenUtils.buildErrorMsg("1", "service:" + serviceName + " 不是有效的service", "MainServlet"));
				response.getWriter().flush();
				logger.error(e.getMessage(), e);
			}
			try {
				service.doService(request, response);
			} catch (Exception e) {
				//执行失败
				response.getWriter().print(CommenUtils.buildErrorMsg("1", "service:" + serviceName + " 执行异常" , "MainServlet"));
				response.getWriter().flush();
				logger.error(e.getMessage(), e);
			}
		} else {
			response.getWriter().print(CommenUtils.buildErrorMsg("1", "请求没有指定service.", "MainServlet"));
			response.getWriter().flush();
			logger.info("请求没有指定service.");
		}
	}

}

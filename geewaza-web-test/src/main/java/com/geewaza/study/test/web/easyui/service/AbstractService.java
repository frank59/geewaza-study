package com.geewaza.study.test.web.easyui.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractService {
	
	public abstract void doService(HttpServletRequest request, HttpServletResponse response) throws Exception;

}

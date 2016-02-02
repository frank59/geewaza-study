package com.geewaza.study.commons.spider;

/**
 * Created by WangHeng on 2016/2/2.
 */
public abstract class AbstractSpider {
	protected SpiderUtil spiderUtil;

	public void setSpiderUtil(SpiderUtil spiderUtil) {
		this.spiderUtil = spiderUtil;
	}

	public SpiderUtil getSpiderUtil() {
		return spiderUtil;
	}


}

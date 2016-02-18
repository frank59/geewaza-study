package com.geewaza.web.test.h2;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Created by WangHeng on 2016/2/18.
 */
public class H2TestDao extends JdbcDaoSupport {


	public void createTest() {
		String sql = "create table `test` (" +
				"`id` int(11) NOT NULL AUTO_INCREMENT" + "," +
				"`name` varchar(100) NOT NULL" + "," +
				"`passwd` varchar(100) NOT NULL" + "," +
				"PRIMARY KEY (`id`)" + "," +
				")";

		getJdbcTemplate().execute(sql);
	}




}

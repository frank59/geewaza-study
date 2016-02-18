package com.geewaza.study.test.web.h2;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;
import java.util.Map;

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

	public void insert() {
		String sql = "insert into test (name, passwd) values ('wangheng', '123456')";
		getJdbcTemplate().update(sql);
	}



	public List<Map<String, Object>> select() {
		String sql = "select * from test";
		return getJdbcTemplate().queryForList(sql);
	}

}

package com.geewaza.spring.test.dao;

import com.geewaza.spring.test.domain.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by wangh on 2016/8/28.
 */
@Repository
public class LoginLogDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void insertLoginLog(LoginLog loginLog) {
		String sql = "INSERT INTO t_login_log(user_id, ip, login_datetime) VALUES(?,?,?)";
		Object[] args = {loginLog.getUserId(), loginLog.getIp(), loginLog.getLoginDate()};
		jdbcTemplate.update(sql, args);
	}
}

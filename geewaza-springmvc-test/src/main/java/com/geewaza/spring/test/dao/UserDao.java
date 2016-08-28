package com.geewaza.spring.test.dao;

import com.geewaza.spring.test.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by wangh on 2016/8/28.
 */
@Repository
public class UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	public int getMatchCount(String userName, String password) {
		String sqlStr = "SELECT count(*) as cut FROM t_user WHERE user_name = ? and password=?";
		return jdbcTemplate.query(sqlStr, new Object[]{userName, password}, new ResultSetExtractor<Integer>() {
			@Override
			public Integer extractData(ResultSet resultSet) throws SQLException, DataAccessException {
				if (resultSet.next()) {
					return resultSet.getInt("cut");
				} else {
					return null;
				}
			}
		});
	}

	public User findUserByUserName(final String userName) {
		String sql = "SELECT user_id, user_name, credits FROM t_user WHERE user_name=?";
		final User user = new User();
		jdbcTemplate.query(sql, new Object[]{userName}, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet resultSet) throws SQLException {
				user.setUserId(resultSet.getInt("user_id"));
				user.setUserName(userName);
				user.setCredits(resultSet.getInt("credits"));
			}
		});
		return user;
	}

	public void updateLoginInfo(User user) {
		String sql = "UPDATE t_user SET last_visit=?, last_ip=?, credits=? WHERE user_id=?";
		jdbcTemplate.update(sql, new Object[]{user.getLastVisit(), user.getLastIp(), user.getCredits(), user.getUserId()});
	}
}

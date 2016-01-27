package com.geewaza.study.test.web.easyui.dao;

import com.geewaza.study.test.web.easyui.pojo.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by WangHeng on 2016/1/27.
 */
@Component("usersDao")
public class UsersDao extends JdbcDaoSupport {

	public List<User> findAll() {
		String sql = "select * from users";
		return getJdbcTemplate().query(sql, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				return user;
			}
		});
	}
}

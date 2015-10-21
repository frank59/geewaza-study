package com.geewaza.study.test.web.easyui.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import com.geewaza.study.test.web.easyui.common.WordState;
import com.geewaza.study.test.web.easyui.pojo.PromoteWord;

@Component("promoteWordDao")
public class PromoteWordDao extends JdbcDaoSupport{
	private static final String TABLE_NAME = "t_kubox_promote_word";
	
	/**
	 * 根据id查询记录
	 * @param id
	 * @return
	 */
	public PromoteWord findById(int id) { 
		String sql = "select * from " + TABLE_NAME + " where id = ?";
		Object[] obj = {id};
		List<PromoteWord> resultList = null;
		resultList = getJdbcTemplate().query(sql, obj, new RowMapper<PromoteWord>() {
			
			@Override
			public PromoteWord mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				PromoteWord promoteWord = new PromoteWord();
				promoteWord.setId(rs.getInt("id"));
				promoteWord.setWord(rs.getString("word"));
				promoteWord.setQueryCount(rs.getLong("query_count"));
				promoteWord.setCateId(rs.getInt("fk_cateid"));
				promoteWord.setSite(rs.getInt("site_id"));
				promoteWord.setStartTime(rs.getDate("start_time"));
				promoteWord.setEndTime(rs.getDate("end_time"));
				promoteWord.setState(rs.getInt("state"));
				promoteWord.setLastUpdate(rs.getTimestamp("last_update"));
				promoteWord.setEditor(rs.getString("editor"));
				return promoteWord;
			}
		});
		if (resultList.size() > 0) {
			return resultList.get(0);
		}
		return null;
	}
	
	/**
	 * 批量添加词
	 * @param list
	 * @return
	 */
	public int insert(final List<PromoteWord> list) {
		String sql = "insert into " + TABLE_NAME + "(word, query_count, fk_cateid, site_id, start_time, end_time, editor) "
				+ "values(?, ?, ?, ?, ?, ?, ?) "
				+ "ON DUPLICATE KEY UPDATE query_count=?, fk_cateid=?, site_id=?, start_time=?, end_time=?, editor=?, state=0";
		int[] results = getJdbcTemplate().batchUpdate(sql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				ps.setString(1, list.get(i).getWord());
				ps.setLong(2, list.get(i).getQueryCount());
				ps.setInt(3, list.get(i).getCateId());
				ps.setInt(4, list.get(i).getSite());
				if (list.get(i).getStartTime() != null) {
					ps.setDate(5, new java.sql.Date(list.get(i).getStartTime().getTime()));
				} else {
					ps.setDate(5, null);
				}
				if (list.get(i).getEndTime() != null) {
					ps.setDate(6, new java.sql.Date(list.get(i).getEndTime().getTime()));
				} else {
					ps.setDate(6, null);
				}
				ps.setString(7, list.get(i).getEditor());
				//UPDATE 
				ps.setLong(8, list.get(i).getQueryCount());
				ps.setInt(9, list.get(i).getCateId());
				ps.setInt(10, list.get(i).getSite());
				if (list.get(i).getStartTime() != null) {
					ps.setDate(11, new java.sql.Date(list.get(i).getStartTime().getTime()));
				} else {
					ps.setDate(11, null);
				}
				if (list.get(i).getEndTime() != null) {
					ps.setDate(12, new java.sql.Date(list.get(i).getEndTime().getTime()));
				} else {
					ps.setDate(12, null);
				}
				ps.setString(13, list.get(i).getEditor());
			}

			public int getBatchSize() {
				return list.size();
			}
			
		});
		int result = 0;
		for (int rowNum : results) {
			result += rowNum;
		}
		return result;
	}
	
	/**
	 * 更新某个词的数据
	 * @param word
	 */
	public int update(PromoteWord word) {
		String sql = "update " + TABLE_NAME + " set word=?, query_count=?, fk_cateid=?, site_id=?, start_time=?, end_time=?, editor=? "
				+ "where id = ?";
		Object[] obj = {word.getWord(), word.getQueryCount(), word.getCateId(), 
				word.getSite(), word.getStartTime(), word.getEndTime(), 
				word.getEditor(), word.getId()};
		return getJdbcTemplate().update(sql, obj);
	}
	
	/**
	 * 更新词的状态
	 * @param id
	 * @param state
	 */
	public void updateEndTime(int id, String editor, Date endTime) {
		String sql = "update " + TABLE_NAME + " set editor=?, end_time=? where id = ?";
		Object[] obj = {editor, endTime, id};
		getJdbcTemplate().update(sql, obj);
	}
	
	/**
	 * 更新词的状态
	 * @param id
	 * @param state
	 */
	public void updateStartTimeAndEndTime(int id, String editor, Date startTime, Date endTime) {
		String sql = "update " + TABLE_NAME + " set editor=?, start_Time=?, end_time=? where id = ?";
		Object[] obj = {editor, startTime, endTime, id};
		getJdbcTemplate().update(sql, obj);
	}
	
	/**
	 * 根据条件查询表中记录
	 * @param cateId
	 * @param siteId
	 * @param state
	 * @return
	 */
	public List<PromoteWord> query(String keyword, Integer cateId, Integer siteId, WordState wordState, int start, int pageLength) {
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> objList = new ArrayList<Object>();
		sqlBuilder.append("select * from ").append(TABLE_NAME).append(" where state = 0");
		if (StringUtils.isNotBlank(keyword)) {
			sqlBuilder.append(" and word like '%").append(keyword).append("%'");
		}
		if (cateId != null) {
			sqlBuilder.append(" and fk_cateid=").append(cateId);
		}
		if (siteId != null) {
			sqlBuilder.append(" and site_id=").append(siteId);
		}
		if (wordState != null) {
			if (wordState == WordState.Wait_Online) {
				sqlBuilder.append(" and start_time > ").append("?");
				sqlBuilder.append(" and end_time > ").append("?");
				objList.add(new Date());
				objList.add(new Date());
			} else if (wordState == WordState.Online) {
				sqlBuilder.append(" and start_time <= ").append("?");
				sqlBuilder.append(" and end_time > ").append("?");
				objList.add(new Date());
				objList.add(new Date());
			} else if (wordState == WordState.Offline) {
				sqlBuilder.append(" and end_time <= ").append("?");
				objList.add(new Date());
			}
		}
		sqlBuilder.append(" order by last_update desc limit ?, ?");
		objList.add(start);
		objList.add(pageLength);
		List<PromoteWord> resultList = null;
		resultList = getJdbcTemplate().query(sqlBuilder.toString(), objList.toArray(), new RowMapper<PromoteWord>() {
			
			@Override
			public PromoteWord mapRow(ResultSet rs, int rowNum)
					throws SQLException {
				PromoteWord promoteWord = new PromoteWord();
				promoteWord.setId(rs.getInt("id"));
				promoteWord.setWord(rs.getString("word"));
				promoteWord.setQueryCount(rs.getLong("query_count"));
				promoteWord.setCateId(rs.getInt("fk_cateid"));
				promoteWord.setSite(rs.getInt("site_id"));
				promoteWord.setStartTime(rs.getDate("start_time"));
				promoteWord.setEndTime(rs.getDate("end_time"));
				promoteWord.setState(rs.getInt("state"));
				promoteWord.setLastUpdate(rs.getTimestamp("last_update"));
				promoteWord.setEditor(rs.getString("editor"));
				return promoteWord;
			}
		});
		return resultList;
	}
	
	/**
	 * 查询条数
	 * @param keyword
	 * @param cateId
	 * @param siteId
	 * @param state
	 * @return
	 */
	public int queryForCount(String keyword, Integer cateId, Integer siteId, WordState wordState) {
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> objList = new ArrayList<Object>();
		sqlBuilder.append("select count(1) as con from ").append(TABLE_NAME).append(" where state = 0");
		if (StringUtils.isNotBlank(keyword)) {
			sqlBuilder.append(" and word like '%").append(keyword).append("%'");
		}
		if (cateId != null) {
			sqlBuilder.append(" and fk_cateid=").append(cateId);
		}
		if (siteId != null) {
			sqlBuilder.append(" and site_id=").append(siteId);
		}
		if (wordState != null) {
			if (wordState == WordState.Wait_Online) {
				sqlBuilder.append(" and start_time > ").append("?");
				sqlBuilder.append(" and end_time > ").append("?");
				objList.add(new Date());
				objList.add(new Date());
			} else if (wordState == WordState.Online) {
				sqlBuilder.append(" and start_time <= ").append("?");
				sqlBuilder.append(" and end_time > ").append("?");
				objList.add(new Date());
				objList.add(new Date());
			} else if (wordState == WordState.Offline) {
				sqlBuilder.append(" and end_time <= ").append("?");
				objList.add(new Date());
			}
		}
		sqlBuilder.append(" order by last_update desc");
		Map<String, Object> resultMap = getJdbcTemplate().queryForMap(sqlBuilder.toString(), objList.toArray());
		long rows = (Long) resultMap.get("con");
		return (int)rows;
	}

	
	
	
	
	
}

package com.geewaza.study.commons.jedis;


import redis.clients.jedis.Tuple;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;


public interface RedisManager {



	public String rpop(String key) throws Exception;

	public void lpush(String key,String jsonStr) throws Exception;

//	public void lpush(String jsonStr) throws Exception{
//		lpush(server.getKey(), jsonStr);
//	}

	public void lpush(String key, Collection<String> values) throws Exception ;

	public long llen(String key) throws Exception;
//	public long llen() throws Exception{
//		return llen(server.getKey());
//	}

	public String get(String key) throws Exception ;

	public void set(String key, String value) throws Exception;

	public void sadd(String key, String member) throws Exception ;

	public String spop(String key) throws Exception ;

	public long scard(String key) throws Exception ;

	public List<String> lrange(String key, long start, long end) throws Exception ;

	public void ltrim(String key, long start, long end) throws Exception ;

	/**
	 * 移除有序集key中，所有score值介于min和max之间(包括等于min或max)的成员
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 * @throws Exception
	 */
	public Long zRemRangeByScore(String key, double min, double max) throws Exception ;

	/**
	 * 移除有序集key中，指定排名(rank)区间内的所有成员。
	 * 以0为底，包含start和stop在内。
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public Long zRemRangeByRank(String key, int start, int end) throws Exception ;

	/**
	 * 将一个或多个member元素及其score值加入到有序集key当中
	 * @param key
	 * @param score
	 * @param member
	 * @return
	 * @throws Exception
	 */
	public Long zAdd(String key, double score, String member) throws Exception ;

	/**
	 * 将一个或多个member元素及其score值加入到有序集key当中
	 * @param key
	 * @param scoreMembers
	 * @return
	 * @throws Exception
	 */
	public Long zAdd(String key, Map<String, Double> scoreMembers) throws Exception ;

	/**
	 * 返回有序集key中，score值在min和max之间(默认包括score值等于min或max)的成员。
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 * @throws Exception
	 */
	public Long zCount(String key, double min, double max) throws Exception ;

	/**
	 * 返回有序集key中，指定区间内的成员
	 * 其中成员的位置按score值递增(从小到大)来排序
	 * @param key
	 * @param start
	 * @param stop
	 * @return
	 * @throws Exception
	 */
	public Set<String> zRange(String key, int start, int stop) throws Exception ;

	/**
	 * 返回有序集key中，指定区间内的成员
	 * 其中成员的位置按score值递减(从大到小)来排列
	 * @param key
	 * @param start
	 * @param stop
	 * @return
	 * @throws Exception
	 */
	public Set<String> zRevRange(String key, int start, int stop) throws Exception ;

	/**
	 * 返回有序集key中，所有score值介于min和max之间(包括等于min或max)的成员。有序集成员按score值递增(从小到大)次序排列
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 * @throws Exception
	 */
	public Set<String> zRangeByScore(String key, double min, double max) throws Exception;

	/**
	 * 返回有序集key中，所有score值介于min和max之间(包括等于min或max)的成员和成员的score。有序集成员按score值递增(从小到大)次序排列
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 * @throws Exception
	 */
	public Set<Tuple> zRangeByScoreWithScore(String key, double min, double max) throws Exception;

	/**
	 * 返回有序集key中，score值介于max和min之间(默认包括等于max或min)的所有的成员。有序集成员按score值递减(从大到小)的次序排列。
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 * @throws Exception
	 */
	public Set<String> zRevRangeByScore(String key, double min, double max) throws Exception ;

	/**
	 * 返回有序集key的基数。
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public long zCard(String key) throws Exception;

	/**
	 * 为给定key设置生存时间,当key过期时，它会被自动删除,如果Key存在，则设置新的过期时间
	 * @param key
	 * @param seconds
	 * @return
	 * @throws Exception
	 */
	public long expire(String key, int seconds) throws Exception;
	/**
	 * 移除给定key的生存时间。
	 * 如果key不存在或key没有设置生存时间，返回0。
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public long persist(String key) throws Exception ;
}

package com.geewaza.study.commons.jedis;


import org.apache.log4j.Logger;
import redis.clients.jedis.*;

import java.util.*;

/**
 * 分片Redis方法工具
 */
public class ShardedRedisManager implements RedisManager{
	
	private static Logger logger = Logger.getLogger(ShardedRedisManager.class);
	private static final int ERROR_TIMES = 5;
	
	public static final int WARNING_LIST_SIZE = 100000;//10万
	
	public static final int MAX_LIST_SIZE = 120000;//12万
	
	private List<HostAndPort> serverList;
	
	private ShardedJedisPool pool;

	public ShardedRedisManager(String servers, JedisPoolConfig jedisPoolConfig) {
		this.serverList = new ArrayList<HostAndPort>();
		List<JedisShardInfo> jedisShardInfos = new ArrayList<JedisShardInfo>();
		for (String server : servers.split(",")) {
			String[] hostPort = server.split(":");
			HostAndPort hostAndPort = new HostAndPort(hostPort[0], Integer.parseInt(hostPort[1]));
			jedisShardInfos.add(new JedisShardInfo(hostAndPort.getHost(), hostAndPort.getPort()));
			serverList.add(hostAndPort);
		}
		pool = new ShardedJedisPool(jedisPoolConfig, jedisShardInfos);
		logger.info("初始化RedisServer:"+ servers);
	}

	public ShardedRedisManager(List<HostAndPort> servers, JedisPoolConfig jedisPoolConfig) {
		this.serverList = servers;
		List<JedisShardInfo> jedisShardInfos = new ArrayList<JedisShardInfo>();
		for(HostAndPort server : servers) {
			jedisShardInfos.add(new JedisShardInfo(server.getHost(), server.getPort()));
		}
		pool = new ShardedJedisPool(jedisPoolConfig, jedisShardInfos);
		logger.info("初始化RedisServer:"+ servers);
	}

	public List<HostAndPort> getServerList() {
		return serverList;
	}

	@Override
	public String rpop(String key) throws Exception{
		String result = null;
		ShardedJedis jedis = null;
		Exception exc = null;
		for(int times = 0; times<ERROR_TIMES * 2 ;times++){
			try{
				jedis = pool.getResource();
				result = jedis.rpop(key);
			} catch(Exception e){
				exc = e;
				logger.error("连接Redis超时，第"+(times+1)+"尝试...",e);
				Thread.sleep(100);
				continue;
			} finally{
				jedis.close();
			}
			return result;
		}
		throw new Exception(exc);
	}

	@Override
	public void lpush(String key,String jsonStr) throws Exception{
		ShardedJedis jedis = null;
		Exception exc = null;
		for(int times = 0 ; times < ERROR_TIMES * 2 ; times ++){
			try{
				jedis = pool.getResource();
				jedis.lpush(key, jsonStr);
			}catch(Exception e){
				exc = e;
				logger.error("连接Redis超时，第"+(times+1)+"尝试...",e);
				Thread.sleep(100);
				continue;
			}finally{
				jedis.close();
			}
			return ;
		}
		throw new Exception(exc);
	}
	
//	public void lpush(String jsonStr) throws Exception{
//		lpush(server.getKey(), jsonStr);
//	}

	@Override
	public void lpush(String key, Collection<String> values) throws Exception {
		ShardedJedis jedis = null;
		Exception exc = null;
		try{
			jedis = pool.getResource();
			for(String value : values) {
				for(int times = 0 ; times < ERROR_TIMES ; times++) {
					exc = null;
					try{
						jedis.lpush(key, value);
						break;
					} catch(Exception e) {
						exc = e;
						logger.error("连接Redis超时，第"+(times+1)+"尝试...",e);
						Thread.sleep(100);
						continue;
					}
				}
				//多次尝试后依然存在异常 则抛出
				if (exc != null) {
					throw new Exception(exc);
				}
			}
		} finally {
			jedis.close();
		}
	}

	@Override
	public long llen(String key) throws Exception{
		ShardedJedis jedis = null;
		Exception exc = null;
		long size = 0;
		for(int times = 0 ; times < ERROR_TIMES * 2 ; times ++){
			try{
				jedis = pool.getResource();
				size = jedis.llen(key);
			}catch(Exception e){
				exc = e;
				logger.error("连接Redis超时，第"+(times+1)+"尝试...",e);
				Thread.sleep(100);
				continue;
			}finally{
				jedis.close();
			}
			return size;
		}
		throw new Exception(exc);
	}
//	public long llen() throws Exception{
//		return llen(server.getKey());
//	}

	@Override
	public String get(String key) throws Exception {
		ShardedJedis jedis = null;
		Exception exc = null;
		String result = null;
		for(int times = 0 ; times < ERROR_TIMES * 2 ; times ++){
			try{
				jedis = pool.getResource();
				result = jedis.get(key);
			}catch(Exception e){
				exc = e;
				logger.error("连接Redis超时，第"+(times+1)+"尝试...",e);
				Thread.sleep(100);
				continue;
			}finally{
				jedis.close();
			}
			return result;
		}
		throw new Exception(exc);
	}

	@Override
	public void set(String key, String value) throws Exception {
		ShardedJedis jedis = null;
		Exception exc = null;
		for(int times = 0 ; times < ERROR_TIMES * 2 ; times ++){
			try{
				jedis = pool.getResource();
				jedis.set(key, value);
			}catch(Exception e){
				exc = e;
				e.printStackTrace();
				logger.error("连接Redis超时，第"+(times+1)+"尝试...",e);
				Thread.sleep(100);
				continue;
			}finally{
				jedis.close();
			}
			return;
		}
		throw new Exception(exc);
	}

	@Override
	public void sadd(String key, String member) throws Exception {
		ShardedJedis jedis = null;
		Exception exc = null;
		for(int times = 0 ; times < ERROR_TIMES * 2 ; times ++){
			try{
				jedis = pool.getResource();
				jedis.sadd(key, member);
			}catch(Exception e){
				exc = e;
				logger.error("连接Redis超时，第"+(times+1)+"尝试...",e);
				Thread.sleep(100);
				continue;
			}finally{
				jedis.close();
			}
			return;
		}
		throw new Exception(exc);
	}

	@Override
	public String spop(String key) throws Exception {
		ShardedJedis jedis = null;
		Exception exc = null;
		String result = null;
		for(int times = 0 ; times < ERROR_TIMES * 2 ; times ++){
			try{
				jedis = pool.getResource();
				result = jedis.spop(key);
			}catch(Exception e){
				exc = e;
				logger.error("连接Redis超时，第"+(times+1)+"尝试...",e);
				Thread.sleep(100);
				continue;
			}finally{
				jedis.close();
			}
			return result;
		}
		throw new Exception(exc);
	}

	@Override
	public long scard(String key) throws Exception {
		ShardedJedis jedis = null;
		Exception exc = null;
		long card = 0;
		for(int times = 0 ; times < ERROR_TIMES * 2 ; times ++){
			try{
				jedis = pool.getResource();
				card = jedis.scard(key);
			}catch(Exception e){
				exc = e;
				logger.error("连接Redis超时，第"+(times+1)+"尝试...",e);
				Thread.sleep(100);
				continue;
			}finally{
				jedis.close();
			}
			return card;
		}
		throw new Exception(exc);
	}

	@Override
	public List<String> lrange(String key, long start, long end) throws Exception {
		ShardedJedis jedis = null;
		Exception exc = null;
		List<String> results = null;
		for(int times = 0 ; times < ERROR_TIMES * 2 ; times ++){
			try{
				jedis = pool.getResource();
				results = jedis.lrange(key, start, end);
			}catch(Exception e){
				exc = e;
				logger.error("连接Redis超时，第"+(times+1)+"尝试...",e);
				Thread.sleep(100);
				continue;
			}finally{
				jedis.close();
			}
			return results;
		}
		throw new Exception(exc);
	}

	@Override
	public void ltrim(String key, long start, long end) throws Exception {
		ShardedJedis jedis = null;
		Exception exc = null;
		for(int times = 0 ; times < ERROR_TIMES * 2 ; times ++){
			try{
				jedis = pool.getResource();
				jedis.ltrim(key, start, end);
			}catch(Exception e){
				exc = e;
				logger.error("连接Redis超时，第"+(times+1)+"尝试...",e);
				Thread.sleep(100);
				continue;
			}finally{
				jedis.close();
			}
			return;
		}
		throw new Exception(exc);
	}
	
	/**
	 * 移除有序集key中，所有score值介于min和max之间(包括等于min或max)的成员
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 * @throws Exception
	 */
	@Override
	public Long zRemRangeByScore(String key, double min, double max) throws Exception {
		ShardedJedis jedis = null;
		Exception exc = null;
		Long result = 0L;
		for(int times = 0 ; times < ERROR_TIMES * 2 ; times ++){
			try{
				jedis = pool.getResource();
				result = jedis.zremrangeByScore(key, min, max);
			}catch(Exception e){
				exc = e;
				logger.error("连接Redis超时，第"+(times+1)+"尝试...",e);
				Thread.sleep(100);
				continue;
			}finally{
				jedis.close();
			}
			return result;
		}
		throw new Exception(exc);
	}
	
	/**
	 * 移除有序集key中，指定排名(rank)区间内的所有成员。
	 * 以0为底，包含start和stop在内。
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	@Override
	public Long zRemRangeByRank(String key, int start, int end) throws Exception {
		ShardedJedis jedis = null;
		Exception exc = null;
		Long result = 0L;
		for(int times = 0 ; times < ERROR_TIMES * 2 ; times ++){
			try{
				jedis = pool.getResource();
				result = jedis.zremrangeByRank(key, start, end);
			}catch(Exception e){
				exc = e;
				logger.error("连接Redis超时，第"+(times+1)+"尝试...",e);
				Thread.sleep(100);
				continue;
			}finally{
				jedis.close();
			}
			return result;
		}
		throw new Exception(exc);
	}
	
	/**
	 * 将一个或多个member元素及其score值加入到有序集key当中
	 * @param key
	 * @param score
	 * @param member
	 * @return
	 * @throws Exception
	 */
	@Override
	public Long zAdd(String key, double score, String member) throws Exception {
		ShardedJedis jedis = null;
		Exception exc = null;
		Long result = 0L;
		for(int times = 0 ; times < ERROR_TIMES * 2 ; times ++){
			try{
				jedis = pool.getResource();
				result = jedis.zadd(key, score, member);
			}catch(Exception e){
				exc = e;
				logger.error("连接Redis超时，第"+(times+1)+"尝试...",e);
				Thread.sleep(100);
				continue;
			}finally{
				jedis.close();
			}
			return result;
		}
		throw new Exception(exc);
	}
	
	/**
	 * 将一个或多个member元素及其score值加入到有序集key当中
	 * @param key
	 * @param scoreMembers
	 * @return
	 * @throws Exception
	 */
	@Override
	public Long zAdd(String key, Map<String, Double> scoreMembers) throws Exception {
		ShardedJedis jedis = null;
		Exception exc = null;
		Long result = 0L;
		for(int times = 0 ; times < ERROR_TIMES * 2 ; times ++){
			try{
				jedis = pool.getResource();
				result = jedis.zadd(key, scoreMembers);
			}catch(Exception e){
				exc = e;
				logger.error("连接Redis超时，第"+(times+1)+"尝试...",e);
				Thread.sleep(100);
				continue;
			}finally{
				jedis.close();
			}
			return result;
		}
		throw new Exception(exc);
	}
	
	
	/**
	 * 返回有序集key中，score值在min和max之间(默认包括score值等于min或max)的成员。
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 * @throws Exception
	 */
	@Override
	public Long zCount(String key, double min, double max) throws Exception {
		ShardedJedis jedis = null;
		Exception exc = null;
		Long result = 0L;
		for(int times = 0 ; times < ERROR_TIMES * 2 ; times ++){
			try{
				jedis = pool.getResource();
				result = jedis.zcount(key, min, max);
			}catch(Exception e){
				exc = e;
				logger.error("连接Redis超时，第"+(times+1)+"尝试...",e);
				Thread.sleep(100);
				continue;
			}finally{
				jedis.close();
			}
			return result;
		}
		throw new Exception(exc);
	}
	
	/**
	 * 返回有序集key中，指定区间内的成员
	 * 其中成员的位置按score值递增(从小到大)来排序
	 * @param key
	 * @param start
	 * @param stop
	 * @return
	 * @throws Exception
	 */
	@Override
	public Set<String> zRange(String key, int start, int stop) throws Exception {
		ShardedJedis jedis = null;
		Exception exc = null;
		Set<String> result = null;
		for(int times = 0 ; times < ERROR_TIMES * 2 ; times ++){
			try{
				jedis = pool.getResource();
				result = jedis.zrange(key, start, stop);
			}catch(Exception e){
				exc = e;
				logger.error("连接Redis超时，第"+(times+1)+"尝试...",e);
				Thread.sleep(100);
				continue;
			}finally{
				jedis.close();
			}
			return result;
		}
		throw new Exception(exc);
	}
	
	/**
	 * 返回有序集key中，指定区间内的成员
	 * 其中成员的位置按score值递减(从大到小)来排列
	 * @param key
	 * @param start
	 * @param stop
	 * @return
	 * @throws Exception
	 */
	@Override
	public Set<String> zRevRange(String key, int start, int stop) throws Exception {
		ShardedJedis jedis = null;
		Exception exc = null;
		Set<String> result = null;
		for(int times = 0 ; times < ERROR_TIMES * 2 ; times ++){
			try{
				jedis = pool.getResource();
				result = jedis.zrevrange(key, start, stop);
			}catch(Exception e){
				exc = e;
				logger.error("连接Redis超时，第"+(times+1)+"尝试...",e);
				Thread.sleep(100);
				continue;
			}finally{
				jedis.close();
			}
			return result;
		}
		throw new Exception(exc);
	}
	
	/**
	 * 返回有序集key中，所有score值介于min和max之间(包括等于min或max)的成员。有序集成员按score值递增(从小到大)次序排列
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 * @throws Exception
	 */
	@Override
	public Set<String> zRangeByScore(String key, double min, double max) throws Exception {
		ShardedJedis jedis = null;
		Exception exc = null;
		Set<String> result = null;
		for(int times = 0 ; times < ERROR_TIMES * 2 ; times ++){
			try{
				jedis = pool.getResource();
				result = jedis.zrangeByScore(key, min, max);
			}catch(Exception e){
				exc = e;
				logger.error("连接Redis超时，第"+(times+1)+"尝试...",e);
				Thread.sleep(100);
				continue;
			}finally{
				jedis.close();
			}
			return result;
		}
		throw new Exception(exc);
	}
	
	/**
	 * 返回有序集key中，所有score值介于min和max之间(包括等于min或max)的成员和成员的score。有序集成员按score值递增(从小到大)次序排列
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 * @throws Exception
	 */
	@Override
	public Set<Tuple> zRangeByScoreWithScore(String key, double min, double max) throws Exception {
		ShardedJedis jedis = null;
		Exception exc = null;
		Set<Tuple> result = null;
		for(int times = 0 ; times < ERROR_TIMES * 2 ; times ++){
			try{
				jedis = pool.getResource();
				result = jedis.zrangeByScoreWithScores(key, min, max);
			}catch(Exception e){
				exc = e;
				logger.error("连接Redis超时，第"+(times+1)+"尝试...",e);
				Thread.sleep(100);
				continue;
			}finally{
				jedis.close();
			}
			return result;
		}
		throw new Exception(exc);
	}
	
	/**
	 * 返回有序集key中，score值介于max和min之间(默认包括等于max或min)的所有的成员。有序集成员按score值递减(从大到小)的次序排列。
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 * @throws Exception
	 */
	@Override
	public Set<String> zRevRangeByScore(String key, double min, double max) throws Exception {
		ShardedJedis jedis = null;
		Exception exc = null;
		Set<String> result = null;
		for(int times = 0 ; times < ERROR_TIMES * 2 ; times ++){
			try{
				jedis = pool.getResource();
				result = jedis.zrevrangeByScore(key, min, max);
			}catch(Exception e){
				exc = e;
				logger.error("连接Redis超时，第"+(times+1)+"尝试...",e);
				Thread.sleep(100);
				continue;
			}finally{
				jedis.close();
			}
			return result;
		}
		throw new Exception(exc);
	}
	
	/**
	 * 返回有序集key的基数。
	 * @param key
	 * @return
	 * @throws Exception
	 */
	@Override
	public long zCard(String key) throws Exception {
		ShardedJedis jedis = null;
		Exception exc = null;
		Long result = 0L;
		for(int times = 0 ; times < ERROR_TIMES * 2 ; times ++){
			try{
				jedis = pool.getResource();
				result = jedis.zcard(key);
			}catch(Exception e){
				exc = e;
				logger.error("连接Redis超时，第"+(times+1)+"尝试...",e);
				Thread.sleep(100);
				continue;
			}finally{
				jedis.close();
			}
			return result;
		}
		throw new Exception(exc);
	}
	
	/**
	 * 为给定key设置生存时间,当key过期时，它会被自动删除,如果Key存在，则设置新的过期时间
	 * @param key
	 * @param seconds
	 * @return
	 * @throws Exception
	 */
	@Override
	public long expire(String key, int seconds) throws Exception {
		ShardedJedis jedis = null;
		Exception exc = null;
		Long result = null;
		for(int times = 0 ; times < ERROR_TIMES * 2 ; times ++){
			try{
				jedis = pool.getResource();
				result = jedis.expire(key, seconds);
			}catch(Exception e){
				exc = e;
				logger.error("连接Redis超时，第"+(times+1)+"尝试...",e);
				Thread.sleep(100);
				continue;
			}finally{
				jedis.close();
			}
			return result;
		}
		throw new Exception(exc);
	}
	/**
	 * 移除给定key的生存时间。
	 * 如果key不存在或key没有设置生存时间，返回0。
	 * @param key
	 * @return
	 * @throws Exception
	 */
	@Override
	public long persist(String key) throws Exception {
		ShardedJedis jedis = null;
		Exception exc = null;
		Long result = null;
		for(int times = 0 ; times < ERROR_TIMES * 2 ; times ++){
			try{
				jedis = pool.getResource();
				result = jedis.persist(key);
			}catch(Exception e){
				exc = e;
				logger.error("连接Redis超时，第"+(times+1)+"尝试...",e);
				Thread.sleep(100);
				continue;
			}finally{
				jedis.close();
			}
			return result;
		}
		throw new Exception(exc);
	}
	
}

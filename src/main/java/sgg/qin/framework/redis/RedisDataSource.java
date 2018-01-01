package sgg.qin.framework.redis;

import redis.clients.jedis.ShardedJedis;

public interface RedisDataSource {  
    public abstract ShardedJedis getShardedJedis();  
    public void returnResource(ShardedJedis shardedJedis);  
    public void returnResource(ShardedJedis shardedJedis,boolean broken);  
} 

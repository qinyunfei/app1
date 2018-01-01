package sgg.qin.framework.redis;

import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
  
import redis.clients.jedis.ShardedJedis;  
import redis.clients.jedis.ShardedJedisPool; 

public class RedisDataSourceImpl implements RedisDataSource {  
	  
    protected final Logger log = LoggerFactory.getLogger(getClass());  
  
    private ShardedJedisPool shardedJedisPool;  
    
    
    public ShardedJedisPool getShardedJedisPool() {
		return shardedJedisPool;
	}

	public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {  
        this.shardedJedisPool = shardedJedisPool;  
    } 
  
    @Override  
    public ShardedJedis getShardedJedis() {  
        try {  
            return shardedJedisPool.getResource();  
        } catch (Exception e) {  
            log.error("getRedisClent error", e);  
        }  
        return null;  
    }  
  
    @Override  
    public void returnResource(ShardedJedis shardedJedis) {  
       //shardedJedisPool.returnResource(shardedJedis);  
    	//shardedJedis.disconnect();
        shardedJedis.close();  
    }  
  
    @Override  
    public void returnResource(ShardedJedis shardedJedis, boolean broken) {  
        // if (broken) {  
        // shardedJedisPool.returnBrokenResource(shardedJedis);  
        // } else {  
        // shardedJedisPool.returnResource(shardedJedis);  
        // }  
        returnResource(shardedJedis);  
    }  
  
     
      
} 

package cn.jboa.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class RedisCacheStorageImpl implements RedisCacheStorage {
    /**
     * 日志记录
     */
    public static final Log LOG = LogFactory.getLog(RedisCacheStorageImpl.class);
    /**
     * redis 客户端
     */
    private RedisClient redisClient;
    /**
     * 默认过时时间
     */
    private static final int EXPRIE_TIME =3600*24;

    public void setRedisClient(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    @Override
    public boolean addHashMap(String key, Map map) {
        Jedis jedis=null;
        boolean isSuccess=true;
        if (StringUtils.isEmpty(key)){
            LOG.info("key is empty");
            return false;
        }
        try {
            jedis=redisClient.getResource();
            jedis.hmset(key,map);
        }catch (Exception e){
            e.printStackTrace();
            LOG.info("client can't connect server");
            isSuccess=false;
        }finally {
            if (jedis!=null){
                redisClient.returnResource(jedis);
            }
            return isSuccess;
        }
    }

    @Override
    public HashMap queryHashMapByKey(String key) {
        Jedis jedis=null;
        HashMap hashMap=null;
        if (StringUtils.isEmpty(key)){
            LOG.info("key is empty");
            return null;
        }
        try {
            jedis=redisClient.getResource();
            Map<String, String> map = jedis.hgetAll(key);
            if (map!=null  && map.size()!=0){
                hashMap=new HashMap();
                for (Map.Entry<String,String> item:map.entrySet()) {
                    hashMap.put(item.getKey(),item.getValue());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            LOG.info("client can't connect server");
        }finally {
            if (jedis!=null){
                redisClient.returnResource(jedis);
            }
            return hashMap;
        }

    }
}

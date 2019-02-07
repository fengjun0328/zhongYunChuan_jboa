package cn.jboa.util;


import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * redis 客户端封装
 */
public class RedisClient {

    /**
     * 日期记录
     */
    private final Logger LOG= Logger.getLogger(RedisClient.class);

    /**
     * redis 连接池
     */
    private JedisPool pool;

    public void setPool(JedisPool pool) {
        this.pool = pool;
    }

    /**
     * 获取redis
     * @return
     */
    public Jedis getResource(){
        Jedis jedis=null;
        try{
            jedis=pool.getResource();
        }catch (Exception e){
            e.printStackTrace();
            LOG.info("can't  get  the redis resource");
        }
        return jedis;
    }

    /**
     * 关闭连接
     * @param jedis
     */
    public void disconnect(Jedis jedis){
        jedis.disconnect();
    }

    /**
     *  将jedis 返还连接池
     * @param jedis
     */
    public void returnResource(Jedis jedis){
        if (jedis!=null){
            try {
                jedis.close();
            }catch (Exception e){
                LOG.info("can't return jedis to jedisPool");
            }
        }
    }


}

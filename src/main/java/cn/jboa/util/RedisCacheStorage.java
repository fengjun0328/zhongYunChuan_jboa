package cn.jboa.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 缓存存储接口
 */
public interface RedisCacheStorage {

    public boolean addHashMap(String key,Map map);

    public HashMap queryHashMapByKey(String key);
}

package com.gzczy.datastructures.atguigu.itdachang.hash;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description #146 LRU Cache 缓存机制 借助LinkedHashMap 进行实现
 * @Author chenzhengyu
 * @Date 2021-01-28 13:07
 */
public class LRUCacheLinkedHashMap extends LinkedHashMap<Integer,Integer> {

    private int capacity;

    public LRUCacheLinkedHashMap(int capacity) {
        /**
         * 当accessOrder设置为false时，会按照插入顺序进行排序，当accessOrder为true是，会按照访问顺序
         */
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    public Integer get(Object key) {
        Integer result = super.get(key);
        if (result == null) {
            return -1;
        }
        return result;
    }

    @Override
    public Integer put(Integer key, Integer value) {
        return super.put(key, value);
    }

    /**
     * 用于检查是否删除最旧的条目，默认方法实现为false 可以通过修改自己判断条件
     * @param eldest
     * @return
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {
        LRUCacheLinkedHashMap lRUCache = new LRUCacheLinkedHashMap(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println(lRUCache.get(1));
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lRUCache.get(2));
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1));
        System.out.println(lRUCache.get(3));
        System.out.println(lRUCache.get(4));
    }


}

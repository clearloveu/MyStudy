package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zg
 * @create 2020-01-03 9:46
 *
 * LRU缓存机制[中等]
 *
 *
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 *
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 *
 * 进阶:
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 *
 * 示例:
 *
 * LRUCache cache = new LRUCache( 2 （ 缓存容量 ） );
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // 返回  1
 * cache.put(3,3);    // 该操作会使得密钥 2 作废
 * cache.get(2);       // 返回 -1 (未找到)
 * cache.put(4,4);    // 该操作会使得密钥 1 作废
 * cache.get(1);       // 返回 -1 (未找到)
 * cache.get(3);       // 返回  3
 * cache.get(4);       // 返回  4
 */
public class Test146 {
    //不符合题目要求的时间复杂度O(1)
    //但是如果把我使用的数组变成双向链表，可以实现O(1)复杂度
    //详情见https://leetcode-cn.com/problems/lru-cache/solution/lru-huan-cun-ji-zhi-by-leetcode/
    List<Integer> lruList ;
    Map<Integer,Integer> dic ;
    int listCapacity ;
    public Test146(int capacity) {
        lruList = new ArrayList<>();
        dic = new HashMap<>();
        listCapacity = capacity;
    }

    public int get(int key) {
        if (dic.get(key)!=null) {
            int index = lruList.indexOf(key);
            //按照列表的先后顺序规定最近使用频率，0为最近使用的页面
            for (int i=index;i>0;i--){
                lruList.set(i,lruList.get(i-1));
            }
            lruList.set(0,key);
            return dic.get(key);
        }
        else return -1;
    }

    public void put(int key, int value) {

        //如果key在列表中
        if (lruList.contains(key)){
            //防止key对应的value改变
            dic.put(key,value);
            int index = lruList.indexOf(key);
            //按照列表的先后顺序规定最近使用频率，0为最近使用的页面
            for (int i=index;i>0;i--){
                lruList.set(i,lruList.get(i-1));
            }
            lruList.set(0,key);
            return;
        }

        //如果key不在列表中
        //没有缺页，直接添加
        if (lruList.size()<listCapacity){
            lruList.add(0,key);
            dic.put(key,value);
        }else {//缺页了，删除最近最少使用的页面
            dic.remove(lruList.get(listCapacity-1));
            lruList.remove(listCapacity-1);
            lruList.add(0,key);
            dic.put(key,value);
        }
    }
}

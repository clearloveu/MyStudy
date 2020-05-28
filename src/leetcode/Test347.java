package leetcode;

import java.util.*;

/**
 * @author zg
 * @create 2020-02-08 17:01
 *
 * 前 K 个高频元素[中等]
 *
 *
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 *
 * 示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 *
 *
 * 示例 2:
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *
 * 说明
 * 	你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 	你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 *
 */
public class Test347 {
    private static List<Integer> topKFrequent(int[] nums, int k) {
        //第一想法：有序的Map解决，时间复杂度为O(n log n)
        //TreeMap是根据key进行排序的，pass

        //2：位图法，时间复杂度为O(n)，空间复杂度未知

        //3：HashMap先记录每个元素的数量，再迭代器遍历map中的键值对，key是出现次数，value是元素集合，TreeMap实现，取出TreeMap前k个元素
        HashMap<Integer,Integer> keyMap = new HashMap<>();
//        for (int num : nums) {
//            if (keyMap.containsKey(num)) {
//                keyMap.put(num, keyMap.get(num) + 1);
//            } else keyMap.put(num, 1);
//        }
        //技巧：可以用下面的代替上面的
        for (int n: nums) {
            keyMap.put(n, keyMap.getOrDefault(n, 0) + 1);
        }
        Map<Integer,List<Integer>> valueMap = new TreeMap<>((a,b)-> { return (int)a >(int)(b)?-1:(int)a <(int)(b)?1:0;});
        for (int key:keyMap.keySet()) {
            if (valueMap.containsKey(keyMap.get(key))) {
                List<Integer> list = valueMap.get(keyMap.get(key));
                list.add(key);
                valueMap.put(keyMap.get(key),list);
            }else {
                List<Integer> list = new ArrayList<>();
                list.add(key);
                valueMap.put(keyMap.get(key),list);
            }
        }
//        System.out.println(""+valueMap);
        List<Integer> answers = new ArrayList<>();

        for (Integer key:valueMap.keySet()) {
            List<Integer> answer = valueMap.get(key);
            answers.addAll(answer);
        }
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<k;i++){
            list.add(answers.get(i));
        }
        System.out.println(list);
        return list;
    }


    //领扣
    //首先建立一个元素值对应出现频率的哈希表
    //第二步建立堆，堆中添加一个元素的复杂度是 O(log(k))，要进行 N次复杂度是 O(NlogK)。
    //最后一步是输出结果，复杂度为 O(klogK)
    private static List<Integer> topKFrequent2(int[] nums, int k) {
        // build hash map : character and how often it appears
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int n: nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        // init heap 'the less frequent element first'
        PriorityQueue<Integer> heap =
                new PriorityQueue<Integer>((n1, n2) -> map.get(n1) - map.get(n2));

        // keep k top frequent elements in the heap
        for (int n: map.keySet()) {
            heap.add(n);
            if (heap.size() > k)
                heap.poll();
        }

        // build output list
        List<Integer> top_k = new LinkedList();
        while (!heap.isEmpty())
            top_k.add(heap.poll());
        Collections.reverse(top_k);
        return top_k;
    }


    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        int k = 2;
        topKFrequent(nums,k);
    }
}

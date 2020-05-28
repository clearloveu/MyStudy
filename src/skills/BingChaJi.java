package skills;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zg
 * @create 2020-04-18 10:09
 *
 * 并查集讲解：https://blog.csdn.net/niushuai666/article/details/6662911
 *
 * 并查集解法示例
 * leetcode128-最长连续序列[困难]
 *
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 * 要求算法的时间复杂度为 O(n)。
 *
 * 示例:
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 *
 */
public class BingChaJi {
    private Map<Integer,Integer> uf,cnt;
    //找到某个节点的上级
    private int find(int i){
        if (i==uf.get(i)){
            return i;
        }else {
            //路径压缩思想
            uf.put(i,find(uf.get(i)));
            return uf.get(i);
        }
    }
    // 合并某2个节点的集合
    private int merge(int x,int y){
        x = find(x);
        y = find(y);
        if (x==y) return  cnt.get(x);
        // 将y的上级设置为x
        uf.put(y,x);
        //更新合并之后的连通分量的元素个数，只需要更新x的集合个数，y不需要管他，因为以后用不到了，每次find都是查到x
        cnt.put(x,cnt.get(x)+cnt.get(y));
        return cnt.get(x);
    }
    //将连续的数字作为一个集合
    //那么扫描到一个数字，只要将它和它的下一个数字（假如存在）merge在一个集合即可。同时更新这个集合的元素个数
    //如果当前经过merge的集合的元素个数比当前记录的最长序列的长度都长，则更新当前最长记录
    private  int longestConsecutive(int[] nums) {
        // key：某个节点；value：某个节点的上级，看讲解网址
        uf = new HashMap<>();
        // 记录该集合的元素个数
        cnt = new HashMap<>();
        if(nums.length == 0) return 0;
        // 如果遇到重复的数字，则覆盖，重复的数字只需要记录一个
        for(int i: nums) {
            uf.put(i, i);
            cnt.put(i,1);
        }
        // -5 -4 -3 -2 -1 0 1 2
        int ans = 1;
        for(int i: uf.keySet()){
            if(uf.containsKey(i+1)) {
                // 计算当前的集合路径长度
                ans = Math.max(ans, merge(i, i+1));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,0,1};
        int[] nums2 = {4,2,2,-4,0,-2,4,-3,-4,-4,-5,1,4,-9,5,0,6,-8,-1,-3,6,5,-8,-1,-5,-1,2,-9,1};
        System.out.println(new BingChaJi().longestConsecutive(nums2));
    }
}



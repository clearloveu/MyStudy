package leetcode.jianZhiOffer;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zg
 * @create 2020-03-13 10:32
 *
 * 滑动窗口的最大值[中等]
 *
 */
public class Question59_1 {
    private int[] maxSlidingWindow(int[] nums, int k) {
        //特例
        if(nums.length==0) return new int[0];
        //双向队列，这里用LinkedList代替，存的是索引，详情见剑指offer
        List<Integer> queue = new LinkedList<>();
        queue.add(0);
        int[] res = new int[nums.length-k+1];
        // 用来辅助存储到res的索引
        int index = 0;
        if (k==1) {
            res[index] = nums[0];
            index++;
        }
        for(int i=1;i<nums.length;i++){
            //去掉已经移出滑动窗口的最大值的秩
            if((i-queue.get(0))>=k) queue.remove(0);
            //去掉一个值之后，可能为空队列
            if (queue.isEmpty()) queue.add(i);
                // 队列最后一个值大于当前值，则加入队列尾部
            else if (nums[queue.get(queue.size()-1)]>=nums[i]){
                queue.add(i);
            }else {
                // 当前值大于队列最后一个值，则把尾部小于当前值的数字都去掉
                while (!queue.isEmpty()&&nums[queue.get(queue.size()-1)]<nums[i]){
                    queue.remove(queue.size()-1);
                }
                queue.add(i);
            }
            // 看看窗口有没有形成，只有形成了大小为 k 的窗口，我才能收集窗口内的最大值
            if (i+1<k) continue;
            res[index] = nums[queue.get(0)];
            index ++;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int[] nums2 = {1,-1};
        int k = 3;
        new Question59_1().maxSlidingWindow(nums2,1);
    }
}
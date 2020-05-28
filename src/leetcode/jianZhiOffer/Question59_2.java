package leetcode.jianZhiOffer;

import java.util.LinkedList;

/**
 * @author zg
 * @create 2020-03-13 12:08
 */
public class Question59_2 {
    // 参考上一题：滑动窗口的最大值
    // 核心思想是保证辅助队列是从大到小排序，来了一个更大的，则把前面小于它的都删掉
    class MaxQueue {
        LinkedList<Integer> queue;
        LinkedList<Integer> windows;
        public MaxQueue() {
            queue = new LinkedList<>();
            windows = new LinkedList<>();

        }

        public int max_value() {
            if(queue.size()==0) return -1;
            return windows.getFirst();
        }

        public void push_back(int value) {
            queue.add(value);
            if(windows.size()==0 || windows.getLast()>=value) windows.add(value);
            else {
                while(!windows.isEmpty()&&windows.getLast()<value) windows.pollLast();
                windows.add(value);
            }
        }

        public int pop_front() {
            if(queue.size()==0) return -1;
            if(queue.getFirst().equals(windows.getFirst())) windows.pollFirst();
            return queue.pollFirst();
        }
    }
}

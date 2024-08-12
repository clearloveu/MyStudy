package leetcode;

import java.util.*;

/**
 * @author zg
 * @create 2020-01-11 16:07
 *
给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。

返回 滑动窗口中的最大值 。



示例 1：

输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
输出：[3,3,5,5,6,7]
解释：
滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
1 [3  -1  -3] 5  3  6  7       3
1  3 [-1  -3  5] 3  6  7       5
1  3  -1 [-3  5  3] 6  7       5
1  3  -1  -3 [5  3  6] 7       6
1  3  -1  -3  5 [3  6  7]      7
示例 2：

输入：nums = [1], k = 1
输出：[1]
 *
 */
public class Test239 {
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[0];
        }
        if (nums.length <= k) {
            int[] res = new int[1];
            int aa = Arrays.stream(nums).max().getAsInt();
            res[0] = aa;
            return res;
        }
        Deque<Integer> queue = new LinkedList<>();
        int left = 0;
        int right = 0;
        int[] res = new int[nums.length - k + 1];
        while (right < nums.length) {
            if (queue.isEmpty()) {
                queue.add(nums[right]);
            } else {
                while (true) {
                    if (queue.getLast()< nums[right]) {
                        queue.pollLast();
                    } else {
                        queue.addLast(nums[right]);
                        break;
                    }
                    if (queue.isEmpty()) {
                        queue.addLast(nums[right]);
                        break;
                    }
                }
            }
            while (right - left +1 == k) {
                res[left] = queue.getFirst();
                if (queue.getFirst() == nums[left]) {
                    queue.pollFirst();
                }
                left++;
            }
            right++;
        }
        return res;
    }

    public static int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] pair1, int[] pair2) {
                return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
            }
        });
        for (int i = 0; i < k; ++i) {
            pq.offer(new int[]{nums[i], i});
        }
        int[] ans = new int[n - k + 1];
        ans[0] = pq.peek()[0];
        for (int i = k; i < n; ++i) {
            pq.offer(new int[]{nums[i], i});
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] ints = {7,2,4};
        System.out.println(Arrays.toString(maxSlidingWindow2(ints, 2)));
    }
}

package niuke;

import java.util.Scanner;

/**
 * @author zg
 * @create 2020-03-19 19:33
 *
 * 给定一个数组，每个元素范围是0~K（K < 整数最大值2^32），将该数组分成两部分，使得 |S1- S2|最小，其中S1和S2分别是数组两部分的元素之和。
 *
 */
public class test3 {
    //暴力递归求解
    //初始化所有数字在一个集合A中，另外一个集合B为空，从A中拿取一个数到B，相当于在原本两个集合的差的基础上减去这个数的两倍，再求绝对值。
    private static long minDiff = Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];

        long sum = 0;
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
            sum += nums[i];
        }

        long diff = sum;

        solve(nums, 0, diff);

        System.out.println(minDiff);


    }
    private static void solve(int[] nums, int l, long diff) {
        if (l == nums.length - 1) {
            long minAbsDiff = Math.min(diff, Math.abs(diff - 2 * nums[l]));
            minDiff = Math.min(minDiff, minAbsDiff);
            return;
        }

        solve(nums, l + 1, diff);
        solve(nums, l + 1, Math.abs(diff - 2 * nums[l]));

    }


}

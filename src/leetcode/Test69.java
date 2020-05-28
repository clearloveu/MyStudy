package leetcode;

/**
 * @author zg
 * @create 2020-04-04 12:37
 *
 * x 的平方根[简单]
 *
 * 实现 int sqrt(int x) 函数。
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * 示例 1:
 * 输入: 4
 * 输出: 2
 *
 * 示例 2:
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 */
public class Test69 {
    private int mySqrt(int x) {
        if (x < 2) return x;

        long num;
        int pivot, left = 2, right = x / 2;
        while (left <= right) {
            pivot = left + (right - left) / 2;
            num = (long)pivot * pivot;
            if (num > x) right = pivot - 1;
            else if (num < x) left = pivot + 1;
            else return pivot;
        }
        // 如果没有找到target，会出现right比left小1的情况而退出循环，此时right就是小于target的最大数字的索引，left就是大于target的最小数字的索引。
        // 此时left就是第一个大于x的平方根的数，right就是第一个小于x的平方根的数，因为，当if判断的时候，当不等于时，永远都是left+1和right-1。
        return right; //一定要写right
    }
}

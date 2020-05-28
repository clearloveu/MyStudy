package leetcode;

/**
 * @author zg
 * @create 2019-12-11 16:09
 *
 * 盛最多水的容器[中等]
 *
 *  给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别
 *  为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 * 示例:
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 *
 */

public class Test11 {
    public static  int maxArea(int[] height) {

        int max_Area = 0 ;

        //双指针
        //理解：由于面积取决于边长短的那一端假设为m，所以要想得到比当前更大的面积，边长短的那一端必须舍弃，因为如果不舍弃，
        // 高最大就是m，而随着指针的移动宽会一直减小，因此面积只会越来越小。
        //面积由小到大再到小，中间一定能遍历到面积最大的情况，因为移动的原则按照面积只可能增大的情况去移动。
        int left = 0;
        int right = height.length-1;
        while (left<right){
            int height_min = Integer.min(height[left],height[right]);
            int current_area = (right-left)*height_min;
            max_Area = Integer.max(current_area,max_Area);
            if (height[right]<height[left]) right -=1;
            else left+=1;
        }

        return max_Area;
    }

    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(height));
    }

}

package leetcode.jianZhiOffer;

import java.util.Arrays;

/**
 * @author zg
 * @create 2020-02-20 15:57
 *
 * 二维数组中的查找[简单]
 *
 */
public class Question4 {
    private boolean findNumberIn2DArray(int[][] matrix, int target) {
        //参考 240 搜索二维矩阵 II

        //每一行都二分法,时间复杂度：O(nlogn)
        for(int i=0;i<matrix.length;i++){
            int res = Arrays.binarySearch(matrix[i],target);
            if(res>=0) return true;
        }
        return false;

        //方法二：线性查找
        //由于给定的二维数组具备每行从左到右递增以及每列从上到下递增的特点，当访问到一个元素时，可以排除数组中的部分元素。
        //从二维数组的右上角开始查找。如果当前元素等于目标值，则返回 true。如果当前元素大于目标值，则移到左边一列。如果当前元素小于目标值，则移到下边一行。

    }

    public static void main(String[] args) {
        int[][] nums= {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        int target = 20;
        boolean ans = new Question4().findNumberIn2DArray(nums,target);
        System.out.println(ans);
    }
}

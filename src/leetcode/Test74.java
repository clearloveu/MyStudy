package leetcode;

import java.util.stream.Stream;

/**
 * @author zhaoguang
 * @create 2024-06-15 18:40
 *
 * 给你一个满足下述两条属性的 m x n 整数矩阵：
 *
 * 每行中的整数从左到右按非严格递增顺序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 * 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。
 *
 */
public class Test74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int left = 0;
        int right = matrix.length;
        while(left <right) {
            int mid=  ((right - left) >> 1) + left;
            if(matrix[mid][0] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if(left == 0 ) return false;
        left = left -1;
        if(matrix[left][0] == target) return true;
        int temp = left;
        left = 0;
        right = matrix[temp].length-1;
        while(left <= right) {
            int mid = ((right-left)>>1)+left;
            if(matrix[temp][mid] == target) return true;
            else if(matrix[temp][mid] >target) {
                right = mid-1;
            } else{
                left = mid +1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] ints1 = Stream.of(1, 3, 5, 7).mapToInt(Integer::intValue).toArray();
        int[] ints2 = Stream.of(10, 11, 16, 20).mapToInt(Integer::intValue).toArray();
        int[] ints3 = Stream.of(23, 30, 34, 60).mapToInt(Integer::intValue).toArray();
        int[][] arg = new int[3][4];
        arg[0] = ints1;
        arg[1] = ints2;
        arg[2] = ints3;

        System.out.println(new Test74().searchMatrix(arg, 3));
    }
}

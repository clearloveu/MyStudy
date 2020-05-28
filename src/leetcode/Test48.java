package leetcode;

import java.util.Arrays;

/**
 * @author zg
 * @create 2019-12-22 13:12
 *
 * 旋转图像[中等]
 *
 * 给定一个 n × n 的二维矩阵表示一个图像。 将图像顺时针旋转 90 度。
 * 说明：你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 *
 * 示例 1:
 * 给定 matrix =
 * [
 *   [1,2,3],
 *   [4,5,6],
 *   [7,8,9]
 * ],
 *
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [7,4,1],
 *   [8,5,2],
 *   [9,6,3]
 * ]
 *
 * 示例 2:
 * 给定 matrix =
 * [
 *   [ 5, 1, 9,11],
 *   [ 2, 4, 8,10],
 *   [13, 3, 6, 7],
 *   [15,14,12,16]
 * ],
 *
 * 原地旋转输入矩阵，使其变为:
 * [
 *   [15,13, 2, 5],
 *   [14, 3, 4, 1],
 *   [12, 6, 8, 9],
 *   [16, 7,10,11]
 * ]
 *
 */
public class Test48 {
    private static void rotate(int[][] matrix) {
        int length = matrix.length;
        //递归实现
//        recursion(matrix,length);

        //迭代实现，思路和递归一样
        //一个点旋转90°，坐标的变化规律：(x,y)---->(y,n-x)---->(n-x,n-y)---->(n-y,x)
        //每次旋转时，交换4个点的位置，最开始是最外面一层nxn的点的交换（i=0），下一次迭代是(n-1)X(n-1)这一层点的交换（i=1）
        //注意点：1，行结束判断是2*i<matrix.length，因为最后一定1x1或者2x2的一层
        //2，不需要遍历到每一行的最后一个元素，因为最后一个元素是第一个元素旋转而来的，故i<endIndex
        for (int i =0;2*i<matrix.length;i++){
            for (int j =i;j<matrix.length-1-i;j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[matrix.length-1-j][i];
                matrix[matrix.length-1-j][i] = matrix[matrix.length-1-i][matrix.length-1-j];
                matrix[matrix.length-1-i][matrix.length-1-j] = matrix[j][matrix.length-1-i];
                matrix[j][matrix.length-1-i] = temp ;
            }
        }

    }

    private static void recursion(int[][] matrix,int length){
        //递归基
        if (length==0 || length==1) return;

        //递归体
        //遍历的行的索引，也是列开始的索引
        int startIndex = (matrix.length-length)/2;
        //列结束索引
        int endIndex = matrix.length-startIndex-1;
        //一个点旋转90°，坐标的变化规律：(x,y)---->(y,n-x)---->(n-x,n-y)---->(n-y,x)
        //x = startIndex  ,  y = i  , n = matrix.length-1;
        //不需要遍历到每一行的最后一个元素，因为最后一个元素是第一个元素旋转而来的，故i<endIndex
        for (int i = startIndex;i<endIndex;i++){
            int temp = matrix[i][matrix.length-1-startIndex];
            int temp2 = matrix[matrix.length-1-startIndex][matrix.length-1-i];
            int temp3 = matrix[matrix.length-1-i][startIndex];
            matrix[i][matrix.length-1-startIndex] = matrix[startIndex][i];
            matrix[matrix.length-1-startIndex][matrix.length-1-i] = temp;
            matrix[matrix.length-1-i][startIndex] = temp2;
            matrix[startIndex][i] = temp3;
        }


        recursion(matrix,length-2);

    }


    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] matrix2 = {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        rotate(matrix2);
        System.out.println(Arrays.deepToString(matrix2));
    }
}

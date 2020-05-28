package leetcode;

/**
 * @author zg
 * @create 2020-01-11 16:28
 *
 * 搜索二维矩阵 II[中等]
 *
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 *
 * 	每行的元素从左到右升序排列。
 * 	每列的元素从上到下升序排列。
 *
 * 示例:
 * 现有矩阵 matrix 如下：
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 *
 * 给定 target = 5，返回 true。
 * 给定 target = 20，返回 false。
 *
 */
public class Test240 {
    //以下是错误求解，正确求解请参考leetcode
//    https://leetcode-cn.com/problems/search-a-2d-matrix-ii/solution/sou-suo-er-wei-ju-zhen-ii-by-leetcode-2/
//    方法一：暴力法
//    方法二：二分法搜索：迭代矩阵对角线，对当前元素的列和行进行二分搜索
//    方法三：搜索空间的缩减：我们可以将已排序的二维矩阵划分为四个子矩阵，其中两个可能包含目标，其中两个肯定不包含。
//    方法四：贪心
    private static boolean searchMatrix(int[][] matrix, int target) {
        return recursion(matrix,target,0,0,matrix.length-1,matrix[0].length-1);
    }

    private  static boolean recursion(int[][] matrix,int target,int startI,int startJ,int endI,int endJ){
        //递归基
        if(endI-startI<1&&endJ-startJ<1){
            if(endI-startI<1){
                for(int i=startI;i<=endI;i++){
                    for(int j = startJ;j<=endJ;j++){
                        if(matrix[startI][j]==target) return true;
                    }
                }
                return false;
            }else {
                for(int i=startJ;i<=endJ;i++){
                    for(int j = startI;j<=endI;j++){
                        if(matrix[j][startJ]==target) return true;
                    }
                }
                return false;
            }
        }
        //递归体
        int tempI = (startI+endI)/2;
        int tempJ = (startJ+endJ)/2;

//        [[1,2,3,4,5],
//        [6,7,8,9,10],
//        [11,12,13,14,15],
//        [16,17,18,19,20],
//        [21,22,23,24,25]]
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,   4,  7, 11, 15}, {2,   5,  8, 12, 19}, {3,   6,  9, 16, 22}, {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};
        int target = 5;
        System.out.println(searchMatrix(matrix,target));

    }
}

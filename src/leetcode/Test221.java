package leetcode;

/**
 * @author zg
 * @create 2020-01-11 15:19
 *
 * 最大正方形[中等]
 *
 *
 * 在一个由 0 和 1 组成的二维矩阵内，找到只包含 1 的最大正方形，并返回其面积。
 *
 * 示例:
 * 输入:
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 *
 * 输出: 4
 *
 */
public class Test221 {

    //暴力法
    private  static int maximalSquare(char[][] matrix) {
        int maxArea = 0;
        for(int i = 0;i<matrix.length;i++){
            for(int j = 0;j<matrix[i].length;j++){
                if(matrix[i][j]=='1') {
                    int area = findSquare(matrix,i,j);
                    maxArea = Integer.max(area,maxArea);
                }
            }
        }
        return maxArea;
    }

    private  static int findSquare(char[][] matrix,int i,int j){
        int level = 0;
        int maxI = i;
        int maxJ = j;
        boolean flag = true;
        while(flag){
            level +=1;
            maxI +=1;
            maxJ +=1;
            if(maxI==matrix.length || maxJ==matrix[i].length) break;
            for(int k = i;k<=maxI;k++){
                if(matrix[k][maxJ]=='0') flag=false;
            }
            for(int k= j;k<=maxJ;k++){
                if(matrix[maxI][k]=='0') flag=false;
            }
        }
        return level*level;
    }

    //动态规划思想没有想到：https://leetcode-cn.com/problems/maximal-square/solution/zui-da-zheng-fang-xing-by-leetcode/
    //递推方程：对原始矩阵中的每一个1，我们将当前元素的值更新为dp(i, j)=min(dp(i−1, j), dp(i−1, j−1), dp(i, j−1))+1
    public static void main(String[] args) {
        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        System.out.println(maximalSquare(matrix));
    }
}

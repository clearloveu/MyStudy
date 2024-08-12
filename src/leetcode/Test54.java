package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zg
 * @create 2019-12-09 15:42
 *
给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 *
 */
public class Test54 {
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        spiralOrder2(matrix, 0, 0, res);
        return res;
    }

    private static void  spiralOrder2(int[][] matrix, int xStart, int yStart, List<Integer> res) {
        if(matrix[0].length - 1 - xStart < xStart) return;
        if(matrix.length -1 -yStart < yStart) return;
        if(matrix.length - 1 - xStart == xStart) {
            for(int y = yStart;y <matrix[0].length - yStart;y++) res.add(matrix[xStart][y]);
            return;
        }
        if(matrix[0].length - 1 - yStart == yStart) {
            for(int x = xStart;x <matrix.length - xStart;x++) res.add(matrix[x][yStart]);
            return;
        }
        for(int y = yStart ;y <matrix[0].length - yStart;y++) {
            res.add(matrix[xStart][y]);
        }
        for(int x = xStart +1;x <matrix.length - xStart;x++) {
            res.add(matrix[x][matrix[0].length - 1 - yStart]);
        }
        for(int y = matrix[0].length - 1 - yStart -1 ;y >=yStart;y--) {
            res.add(matrix[matrix.length - 1-  xStart][y]);
        }
        for(int x = matrix.length -1  - xStart -1;x >=xStart+1;x--) {
            res.add(matrix[x][yStart]);
        }
        spiralOrder2(matrix, xStart +1, yStart +1, res);
    }




    public static void main(String[] args) {
        int[][] nums2 = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        System.out.println(spiralOrder(nums2));

    }

}

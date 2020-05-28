package leetcode;

/**
 * @author zg
 * @create 2020-01-10 18:07
 *
 * 岛屿数量[中等]
 *
 * 给定一个由 '1'（陆地）和 '0'（水）组成的的二维网格，计算岛屿的数量。一个岛被水包围，并且它是通过水平方向或垂直方向上相邻的陆地
 * 连接而成的。你可以假设网格的四个边均被水包围。
 *
 * 示例 1:
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 *
 * 输出: 1
 *
 *
 * 示例 2:
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 *
 * 输出: 3
 *
 *
 */
public class Test200 {
    //思路：广度优先、深度优先算法
    private int numIslands(char[][] grid) {
        int count = 0;
        for (int i =0;i<grid.length;i++){
            for (int j = 0;j<grid[i].length;j++){
                if (grid[i][j]=='1') {
                    count +=1;
                    deepSearch(grid,i,j);
                }
            }
        }
        return count;
    }

    private void deepSearch(char[][] grid,int i,int j){
        grid[i][j] = '0';
        //向上搜索
        if (i!=0 && grid[i-1][j]=='1') deepSearch(grid,i-1,j);
        //向下搜索
        if (i!=grid.length-1&&grid[i+1][j]=='1') deepSearch(grid,i+1,j);
        //向左搜索
        if (j!=0 && grid[i][j-1]=='1') deepSearch(grid,i,j-1);
        //向右搜索
        if (j!=grid[i].length-1 && grid[i][j+1]=='1') deepSearch(grid,i,j+1);
    }

    public static void main(String[] args) {
        char[][] grid = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        System.out.println(new Test200().numIslands(grid));
    }
}

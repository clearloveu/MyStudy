package leetcode.jianZhiOffer;

/**
 * @author zg
 * @create 2020-02-22 10:47
 *
 * 机器人的运动范围[中等]
 */
public class Question13 {
    private int[][] grid ; //用来表示某个坐标是否被遍历过，0：没有被遍历过，1：被遍历过
    private int res ;
    private int max;
    private int movingCount(int m, int n, int k) {
        grid = new int[m][n];
        res = 1;
        max = k ;
        grid[0][0] = 1;
        recursion(0,0,m,n);
        return res;
    }
    // 只要依次向下向右就能遍历到所有可能的格子，不需要向上向左
    private void recursion(int x,int y,int m,int n){
        //递归体
        //向下
        if(x<m-1 && grid[x+1][y]==0 && isvalid(x+1,y)){
            grid[x+1][y]=1;
            res+=1;
            recursion(x+1,y,m,n);
        }

        //向右
        if(y<n-1 && grid[x][y+1]==0 && isvalid(x,y+1)){
            grid[x][y+1]=1;
            res+=1;
            recursion(x,y+1,m,n);
        }

        //向上

        //向左
    }
    //判断此时的行坐标和列坐标的数位之和是否大于k
    private boolean isvalid(int x,int y){
        int ge_x= x%10;
        int shi_x= (x/10)%10;
        int ge_y= y%10;
        int shi_y= (y/10)%10;
        if((ge_x+shi_x+ge_y+shi_y)<=max) return true;
        else return false;
    }

    public static void main(String[] args) {
        System.out.println(new Question13().movingCount(1,2,1));
    }


}

package niuke;

import java.util.Scanner;

/**
 * @author zg
 * @create 2020-03-19 19:33
 *
 * 百度笔试3.29
 *
 */
public class Baidu3 {
    static int[][] mesh;
    static int[] b;
    static int a = 1;
    static  int res ;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        res = 0;
        a = in.nextInt();
        b = new int[a];
        for (int i=0;i<a;i++) b[i ]= in.nextInt();
        mesh = new int[a][a];
        for (int i = 0; i < a-1; i++) {
            int start = in.nextInt();
            int end = in.nextInt();
            mesh[start][end] = 1;
            mesh[end][start] = 1;
        }

        for (int i=0;i<a;i++){
            rersion(i,0);
        }
        System.out.println(res);


    }
    private static  void rersion(int node, int ans){
        //递归基
        res = Math.max(ans,res);

        //递归体
        for (int start  = 0 ;start<a;start++){
            if (mesh[start][node]==1&&b[node]<b[start]){
                mesh[start][node] = 0;
                rersion(start,ans+1);
                mesh[start][node] = 1;
            }
        }
        for (int start  = 0 ;start<a;start++){
            if (mesh[node][start]==1&&b[node]<b[start]){
                mesh[node][start] = 0;
                rersion(start,ans+1);
                mesh[node][start] = 1;
            }
        }


    }
}

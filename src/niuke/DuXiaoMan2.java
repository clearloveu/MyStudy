package niuke;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author zg
 * @create 2020-04-21 12:55
 *
 * 度小满4.20笔试题
 *
 * 西西所在的国家有N座城市，每座城市都有一道传送门，城市 i 的传送门通往城市 a[i]。
 * 当西西位于城市 i 时，每次他可以执行以下三种操作中的一种：
 *   花费 A 的费用，从城市 i 前往城市 a[i]；
 *   如果 a[i] > 1，可以花费 B 的费用，将 a[i] 的值减少 1；
 *   如果 a[i] < N，可以花费 C 的费用，将 a[i] 的值增加 1。
 * 现在，西西想从城市 1 前往城市 N，那么他至少要花费多少费用？
 * input：
 * 第一行输入四个整数 N、A、B、C（1 < N <= 10000，1 <= A、B、C <= 100000）。
 * 第二行输入 N 个整数 a[1] 到 a[N]（1 <= a[i] <= N）。
 * output:
 * 输出一个整数，表示从城市 1 前往城市 N 所花费的最少费用。
 * sample:
 * 7  1  1  1
 * 3  6  4  3  4  5  6
 * output
 * 4
 */
public class DuXiaoMan2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int a=sc.nextInt();
        int b=sc.nextInt();
        int c=sc.nextInt();
        int[] ai = new int[n + 1];
        for(int i=1;i<=n;i++){
            ai[i]=sc.nextInt();
        }
        boolean vis[]=new boolean[n+1];
        long dis[]=new long[n+1];
        Arrays.fill(dis,Long.MAX_VALUE);
        //计算答案的最大值就是 1直接到终点
        long ans=a+(n-ai[1])*1L*c;
        dis[1]=0;
        //迪杰斯特拉+堆优化
        PriorityQueue<Node> que = new PriorityQueue<Node>(Comparator.comparingLong(o -> o.len));
        que.add(new Node(1,0));
        while(!que.isEmpty()){
            Node node = que.poll();
            if(node.cur==n){
                ans=Math.min(ans,node.len);
                break;
            }
            if(vis[node.cur])continue;
            vis[node.cur]=true;
            for(int i = 2; i<=n; i++){
                if(vis[i])continue;
                int cur=node.cur;
                long cost=0;
                if(ai[cur]<i){
                    cost=1L*(i-ai[cur])*c;
                }
                else cost=1L*(ai[cur]-i)*b;
                if(dis[i]>+node.len+cost+a){//迪杰斯特拉的松弛操作
                    // 大于最大值，就不考虑了，剪枝策略
                    if(node.len+cost+a>=ans)continue;
                    dis[i]=node.len+cost+a;
                    que.add(new Node(i,dis[i]));
                }
            }
        }
        System.out.println(ans);
    }
    static class Node{
        public Node() {        }
        public Node(int cur, long len) {
            this.cur = cur;
            this.len = len;
        }
        int cur;
        long len;
    }
}

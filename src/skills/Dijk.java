package skills;

/**
 * @author zg
 * @create 2020-04-21 12:00
 *
 * Dijkstra算法+堆优化，基于邻接表的代码实现
 *
 * 测例：https://www.luogu.com.cn/problem/P4779
 *
 * 题目描述
 * 给定一个n个点，m 条有向边的带非负权图，请你计算从s 出发，到每个点的距离。
 * 数据保证你能从s 出发到任意点。
 * 输入格式
 * 第一行为三个正整数n,m,s。 第二行起m 行，每行三个非负整数  ui,vi,wi，表示从ui到vi有一条权值为wi的有向边。
 * 输出格式
 * 输出一行 n 个空格分隔的非负整数，表示s 到每个点的距离。
 *
 */
import java.io.*;
import java.util.*;
class Edge implements Comparable<Edge> {
    // to ： 有向边的目的节点是to节点；w：边的权值
    int to, w;
    Edge() { to = w = 0; }
    Edge(int m_to, int m_w) {
        to = m_to;
        w = m_w;
    }
    public int compareTo(Edge obj) {
        return this.w - obj.w;
    }
}
class Dijk {
    static int INF = 0x7fffffff;//表示断开
    static int[] dis;  // 表示从src到其他点的距离
    static boolean[] vis;  // 表示是否访问过
    static ArrayList<Edge>[] G;  //表示邻接表

    private static void Init(int n) {
        // 注意：这里初始化n+1是因为测例中节点的序号是从1开始的，所以秩为0是虚设的，有用的数据是从1-n这n个节点
        dis = new int[n + 1];
        vis = new boolean[n + 1];
        G = new ArrayList[n + 1];
        Arrays.fill(dis, INF);
        Arrays.fill(vis, false);
        for(int i = 0; i <= n; ++i) {
            G[i] = new ArrayList<Edge>();
        }
    }
    //s：源节点
    private static void Dijkstra(int s) {
        // 维护的是从s到其他节点的权值大小排序，权值小的优先级高
        PriorityQueue<Edge> que = new PriorityQueue<>();
        // 该edge代表是从s到s的有向边的权值是0
        que.add(new Edge(s, 0));
        dis[s] = 0;
        while(!que.isEmpty()) {
            int u = que.poll().to;
            if(vis[u]) continue;
            vis[u] = true;
            for(int i = 0; i < G[u].size(); ++i) {
                Edge temp = G[u].get(i);
                if(dis[temp.to] > dis[u] + temp.w) {
                    dis[temp.to] = dis[u] + temp.w;
                    que.add(new Edge(temp.to, dis[temp.to]));
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        StreamTokenizer input = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
        input.nextToken();  //将Scanner改成流输入，否则会超时或者超内存
        int n = (int)input.nval;
        input.nextToken();
        int m = (int)input.nval;
        input.nextToken();
        int s = (int)input.nval;
        Init(n);
        for(int i = 1; i <= m; ++i) {
            //表示从ui到vi有一条权值为wi的有向边。
            input.nextToken();
            int u = (int)input.nval;
            input.nextToken();
            int v = (int)input.nval;
            input.nextToken();
            int w = (int)input.nval;
            G[u].add(new Edge(v, w));
        }
        // 节点从1开始，0是哨兵
        Dijkstra(s);
        for(int i = 1; i <= n; ++i) {
            System.out.print(dis[i] + " ");
        }
    }
}

package skills;

/**
 * @author zg
 * @create 2020-04-21 12:00
 *
 * Dijkstra算法
 * 基于邻接矩阵的代码实现：没有堆优化    https://blog.csdn.net/qq_38410730/article/details/79587768
 *
 */

class Dijk2 {

    private static int numOfVexs;
    private static int[][] edges;

    public static int[] dijkstra(int v) {
        if (v < 0 || v >= numOfVexs) throw new ArrayIndexOutOfBoundsException();
        boolean[] st = new boolean[numOfVexs];// 默认初始为false
        int[] distance = new int[numOfVexs];// 存放源点到其他点的矩离
        for (int i = 0; i < numOfVexs; i++)
            for (int j = 0; j < numOfVexs; j++) {
                if (edges[i][j] == 0) {
                    // 有向边
                    if (i==j) continue; // 从s到s的边的权值是0
                    edges[i][j] = Integer.MAX_VALUE;
//                    edges[j][i] = Integer.MAX_VALUE;
                }
            }
        for (int i = 0; i < numOfVexs; i++) {
            distance[i] = edges[v][i];
        }
        st[v] = true;
        // 处理从源点到其余顶点的最短路径,这里是numOfVexs-1，处理从src到其他的点，最多迭代numOfVexs-1次（找numOfVexs-1次）
        for (int i = 0; i < numOfVexs-1; ++i) {
            int min = Integer.MAX_VALUE;
            int index=-1;
            // 比较从源点到其余顶点的路径长度
            for (int j = 0; j < numOfVexs; ++j) {
                // 从源点到j顶点的最短路径还没有找到
                if (st[j]==false) {
                    // 从源点到j顶点的路径长度最小
                    if (distance[j] < min) {
                        index = j;
                        min = distance[j];
                    }
                }
            }
            //找到源点到索引为index顶点的最短路径长度
            if(index!=-1) st[index] = true;
            else return distance;//如果从源点到剩余未遍历的节点都不可达，则算法结束
            // 更新当前最短路径及距离
            for (int w = 0; w < numOfVexs; w++)
                if (st[w] == false) {
                    if (edges[index][w] != Integer.MAX_VALUE
                            && (min + edges[index][w] < distance[w]))
                        distance[w] = min + edges[index][w];
                }
        }
        return distance;
    }

    public static void main(String[] args) {
        numOfVexs = 6;
        edges = new int[][]{{0, 200, 10, 0, 30, 100}, {0, 0, 5, 0, 0, 0}, {0, 0, 0, 50, 0, 0}, {0, 0, 0, 0, 0, 10},
                {0, 0, 0, 20, 0, 60}, {0, 0, 0, 0, 0, 0}};
        // 这里传入的参数是从0开始的
        int[] distance = dijkstra(0);
        for (int i = 0; i < distance.length; i++) {
            System.out.println(distance[i]);
        }

    }

}






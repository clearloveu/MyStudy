package leetcode;


import leetcode.sort.TopologySort;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zg
 * @create 2020-01-19 12:21
 *
 * 课程表[中等]
 *
 * 现在你总共有 n 门课需要选，记为 0 到 n-1。
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 * 给定课程总量以及它们的先决条件，判断是否可能完成所有课程的学习？
 *
 * 示例 1:
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 *
 * 示例 2:
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 *
 * 说明:
 * 	输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
 * 	你可以假定输入的先决条件中没有重复的边。
 *
 * 提示:
 * 	这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
 * 	通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
 *
 * 	拓扑排序也可以通过 BFS 完成。
 *
 */
public class Test207 {
    //思想类似于操作系统的检测死锁
    //我用的是邻接矩阵，复杂度过高，用入度表代替
    private  static boolean canFinish(int numCourses, int[][] prerequisites) {
        //邻接矩阵
        int[][] adjestList = new int[numCourses][];
        //初始化
        for(int i=0;i<numCourses;i++){
            adjestList[i]=new int[numCourses];
            for(int j=0;j<numCourses;j++){
                adjestList[i][j]=0;
            }
        }
        //将边缘列表变成邻接矩阵
        for (int[] temp : prerequisites) {
            int start = temp[0];
            int end = temp[1];
            adjestList[start][end] = 1;
        }
        //拓扑排序
        boolean flag = true;
        Set<Integer> set = new HashSet<>();
        while(flag){
            flag = false;
            //列
            for(int i=0;i<numCourses;i++){
                int temp=0;
                //行
                for(int j=0;j<numCourses;j++){
                    //如果一列的和为0，代表这门课没有其他课作为先决条件，可以先学
                    temp += adjestList[j][i];
                }
                if(!set.contains(i)&&temp==0){
                    set.add(i);
                    //该门课先学，相当于把以该门课作为某些课的依赖去掉了
                    for(int k = 0;k<numCourses;k++){
                        adjestList[i][k] = 0;
                    }
                    flag=true;
                }
            }
        }
        for(int i=0;i<numCourses;i++){
            for(int j=0;j<numCourses;j++){
                if(adjestList[i][j]==1) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int numCourses= 2;
        int[][] prerequisites= {{1,0},{0,1}};
        System.out.println(canFinish(numCourses,prerequisites));
        System.out.println(TopologySort.topologySort(numCourses,prerequisites));
    }
}

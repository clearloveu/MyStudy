package leetcode.sort;

import java.util.LinkedList;

/**
 * @author zg
 * @create 2020-01-19 13:04
 *
 * 拓扑排序
 *
 * 第一想法是邻接矩阵，复杂度过高，用入度表代替邻接矩阵
 *
 * 详情看leetcode第207题的题解：https://leetcode-cn.com/problems/course-schedule/solution/course-schedule-tuo-bu-pai-xu-bfsdfsliang-chong-fa/
 *
 */
public class TopologySort {
    //方法一：入度表（广度优先遍历）
    //参数1：节点数量；参数2：边缘列表，不是邻接矩阵
    public static boolean topologySort(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];
        for(int[] cp : prerequisites) indegrees[cp[0]]++;
        LinkedList<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(indegrees[i] == 0) queue.addLast(i);
        }
        while(!queue.isEmpty()) {
            Integer pre = queue.removeFirst();
            numCourses--;
            for(int[] req : prerequisites) {
                if(req[1] != pre) continue;
                if(--indegrees[req[0]] == 0) queue.add(req[0]);
            }
        }
        return numCourses == 0;
    }
    //方法2：深度优先遍历
    //借助一个标志列表 flags，用于判断每个节点 i （课程）的状态：
    //未被 DFS 访问：i == 0；
    //已被其他节点启动的DFS访问：i == -1；
    //已被当前节点启动的DFS访问：i == 1。
    public static boolean topologySort2(int numCourses, int[][] prerequisites) {
        int[][] adjacency = new int[numCourses][numCourses];
        int[] flags = new int[numCourses];
        for(int[] cp : prerequisites)
            adjacency[cp[1]][cp[0]] = 1;
        for(int i = 0; i < numCourses; i++){
            if(!dfs(adjacency, flags, i)) return false;
        }
        return true;
    }
    private static boolean dfs(int[][] adjacency, int[] flags, int i) {
        if(flags[i] == 1) return false;
        if(flags[i] == -1) return true;
        flags[i] = 1;
        for(int j = 0; j < adjacency.length; j++) {
            if(adjacency[i][j] == 1 && !dfs(adjacency, flags, j)) return false;
        }
        flags[i] = -1;
        return true;
    }

}

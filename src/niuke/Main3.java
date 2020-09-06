package niuke;

import java.util.*;

/**
 * 小团惹小美生气了，小美要去找小团“讲道理”。小团望风而逃，他们住的地方可以抽象成一棵有n个结点的树，小美位于x位置，
 * 小团位于y位置。小团和小美每个单位时间内都可以选择不动或者向相邻的位置转移，假设小美足够聪明，很显然最终小团会无路可逃，
 * 只能延缓一下被“讲道理”的时间，请问最多经过多少个单位时间后，小团会被追上。
 *
 * 输入第一行包含三个整数n，x，y，分别表示树上的结点数量，小美所在的位置和小团所在的位置。(1<=n<=50000)
 *
 * 接下来有n-1行，每行两个整数u，v，表示u号位置和v号位置之间有一条边，即u号位置和v号位置彼此相邻。
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int treeNum = in.nextInt();
        int meiNode = in.nextInt()-1;
        int tuanNode = in.nextInt()-1;
        int[][] adj = new int[treeNum][treeNum];
        for (int i = 0; i < treeNum-1; i++) {
            int from = in.nextInt()-1;
            int to = in.nextInt()-1;
            adj[from][to] = 1;
            adj[to][from] = 1;
        }
        Set<Integer> set = new HashSet<>();
        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();
        queue1.add(meiNode);
        set.add(meiNode);
        boolean flag = true;
        int time = -1;
        int[] pre = new int[treeNum];
        while (flag) {
            time++;
            if (queue2.isEmpty()) {
                while (!queue1.isEmpty()) {
                    int node = queue1.poll();
                    if (node == tuanNode) {
                        flag = false;
                        break;
                    }
                    for (int i = 0; i < treeNum; i++) {
                        if (adj[node][i] == 1 && !set.contains(i)) {
                            set.add(i);
                            pre[i] = node;
                            queue2.add(i);
                        }
                    }
                    for (int i = 0; i < treeNum; i++) {
                        if (adj[i][node] == 1 && !set.contains(i)) {
                            set.add(i);
                            pre[i] = node;
                            queue2.add(i);
                        }
                    }
                }
            } else {
                while (!queue2.isEmpty()) {
                    int node = queue2.poll();
                    if (node == tuanNode) {
                        flag = false;
                        break;
                    }
                    for (int i = 0; i < treeNum; i++) {
                        if (adj[node][i] == 1 && !set.contains(i)) {
                            set.add(i);
                            pre[i] = node;
                            queue1.add(i);
                        }
                    }
                    for (int i = 0; i < treeNum; i++) {
                        if (adj[i][node] == 1 && !set.contains(i)) {
                            set.add(i);
                            pre[i] = node;
                            queue1.add(i);
                        }
                    }
                }
            }
        }
        queue1.clear();
        queue2.clear();
        set.clear();
        set.add(tuanNode);
        int preNode = tuanNode;
        while (true) {
            preNode = pre[preNode];
            set.add(preNode);
            if (preNode == meiNode) break;
        }
        queue1.add(tuanNode);
        time--;//1
        while (true) {
            time++;
            if (queue1.isEmpty() && queue2.isEmpty()) break;
            else if (queue2.isEmpty()) {
                while (!queue1.isEmpty()) {
                    int node = queue1.poll();
                    for (int i = 0; i < treeNum; i++) {
                        if (adj[node][i] == 1 && !set.contains(i)) {
                            set.add(i);
                            queue2.add(i);
                        }
                    }
                    for (int i = 0; i < treeNum; i++) {
                        if (adj[i][node] == 1 && !set.contains(i)) {
                            set.add(i);
                            queue2.add(i);
                        }
                    }
                }
            } else if (queue1.isEmpty()){
                while (!queue2.isEmpty()) {
                    int node = queue2.poll();
                    for (int i = 0; i < treeNum; i++) {
                        if (adj[node][i] == 1 && !set.contains(i)) {
                            set.add(i);
                            queue1.add(i);
                        }
                    }
                    for (int i = 0; i < treeNum; i++) {
                        if (adj[i][node] == 1 && !set.contains(i)) {
                            set.add(i);
                            queue1.add(i);
                        }
                    }
                }
            }
        }
        time--;
        System.out.println(time);
    }

}

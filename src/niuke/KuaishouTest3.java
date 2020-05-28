package niuke;

import java.util.Scanner;

/**
 * @author zg
 * @create 2020-03-19 19:33
 *
 * 给定一组石头，每个石头有一个正数的重量。每一轮开始的时候，选择两个石头一起碰撞，假定两个石头的重量为x，y，x<=y,碰撞结果为
 * 1. 如果x==y，碰撞结果为两个石头消失
 * 2. 如果x != y，碰撞结果两个石头消失，生成一个新的石头，新石头重量为y-x
 *
 * 最终最多剩下一个石头为结束。求解最小的剩余石头质量的可能性是多少。
 *
 * 输入描述:
 * 第一行输入石头个数(<=100)
 *
 * 第二行输入石头质量，以空格分割，石头质量总和<=10000
 *
 * 输出描述:
 * 最终的石头质量
 *
 * 输入例子1:
 * 6
 * 2 7 4 1 8 1
 *
 * 输出例子1:
 * 1
 *
 */
public class KuaishouTest3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            /* 1.读取数据 */
            int number = sc.nextInt(); // 物品的数量
            // 创建n+1项，第0项为哨兵，这样做的目的是方便计算
            int[] weight = new int[number + 1]; // {0,2,3,4,5} 每个物品对应的重量
            int[] value = new int[number + 1]; // {0,3,4,5,6} 每个物品对应的价值
            weight[0] = 0;
            for (int i = 1; i < number + 1; i++) {
                weight[i] = sc.nextInt();
            }
            value[0] = 0;
            int sum = 0;
            for (int i = 1; i < number + 1; i++) {
                value[i] = weight[i];
                sum+=weight[i];
            }
            int capacity = sum/2; // 背包容量
            /* 2.求解01背包问题 */
            int[][] dp = new int[number + 1][capacity + 1];// 声明动态规划表.其中v[i][j]对应于：当前有i个物品可选，并且当前背包的容量为j时，我们能得到的最大价值
            // 填动态规划表。当前有i个物品可选，并且当前背包的容量为j。
            for (int i = 0; i < number + 1; i++) {
                for (int j = 0; j < capacity + 1; j++) {
                    if (i == 0) {
                        dp[i][j] = 0; // 边界情况：令V(0,j)=0
                    } else if (j == 0) {
                        dp[i][j] = 0; // 边界情况：令V(i,0)=0
                    } else {
                        if (j < weight[i]) {
                            dp[i][j] = dp[i - 1][j];// 包的容量比当前该物品体积小，装不下，此时的价值与前i-1个的价值是一样的，即V(i,j)=V(i-1,j)；
                        } else {
                            dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                        }
                    }
                }
            }

            System.out.println(Math.abs(sum-dp[number][capacity]*2));
        }
    }

}

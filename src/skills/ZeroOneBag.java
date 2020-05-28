package skills;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author zg
 * @create 2020-04-12 15:26
 *
 * 0/1背包代码
 *
 * 第一个函数：包括求解01背包问题+找到最优解
 * 第二个函数：二维dp变成一维dp，空间优化
 * 第三个函数：完全背包问题解答
 *
 */
public class ZeroOneBag {

        // 包括求解01背包问题+找到最优解
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
                for (int i = 1; i < number + 1; i++) {
                    value[i] = sc.nextInt();
                }
                int capacity = sc.nextInt(); // 背包容量
                /* 2.求解01背包问题 */
                int[][] dp = new int[number + 1][capacity + 1];// 声明动态规划表.其中dp[i][j]对应于：当前有i个物品可选，并且当前背包的容量为j时，我们能得到的最大价值
                // 填动态规划表。当前有i个物品可选，并且当前背包的容量为j。
                for (int i = 0; i < number + 1; i++) {
                    for (int j = 0; j < capacity + 1; j++) {
                        if (i == 0) {
                            dp[i][j] = 0; // 边界情况：令dp(0,j)=0
                        } else if (j == 0) {
                            dp[i][j] = 0; // 边界情况：令dp(i,0)=0
                        } else {
                            if (j < weight[i]) {
                                dp[i][j] = dp[i - 1][j];// 包的容量比当前该物品体积小，装不下，此时的价值与前i-1个的价值是一样的，即dp(i,j)=dp(i-1,j)；
                            } else {
// 还有足够的容量可以装当前该物品，但装了当前物品也不一定达到当前最优价值，所以在装与不装之间选择最优的一个，即dp(i,j)=max｛dp(i-1,j)，dp(i-1,j-w(i))+dp(i)｝。
                                dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i]] + value[i]);
                            }
                        }
                    }
                }
//                System.out.println();
//                System.out.println("动态规划表如下：");
//                for (int i = 0; i < number + 1; i++) {
//                    for (int j = 0; j < capacity + 1; j++) {
//                        System.out.print(dp[i][j] + "\t");
//                    }
//                    System.out.println();
//                }
//                System.out.println("背包内最大的物品价值总和为：" + dp[number][capacity]);// 有number个物品可选，且背包的容量为capacity的情况下，能装入背包的最大价值

                /* 3.价值最大时，包内装入了哪些物品？ */
                int[] item = new int[number + 1];// 下标i对应的物品若被选中，设置值为1
                Arrays.fill(item, 0);// 将数组item的所有元素初始化为0

                // 从最优解，倒推回去找
                int j = capacity;
                for (int i = number; i > 0; i--) {
                    if (dp[i][j] > dp[i - 1][j]) {// 在最优解中，dp[i][j]>dp[i-1][j]说明选择了第i个商品
                        item[i] = 1;
                        j = j - weight[i];
                    }
                }
                System.out.print("包内物品的编号为：");
                for (int i = 0; i < number + 1; i++) {
                    if (item[i] == 1) {
                        System.out.print(i + " ");
                    }
                }
                System.out.println("----------------------------");
            }
        }



        // 二维dp变成一维dp，空间优化
        private static void test2(){
            Scanner sc = new Scanner(System.in);
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
            for (int i = 1; i < number + 1; i++) {
                value[i] = sc.nextInt();
            }
            int capacity = sc.nextInt(); // 背包容量
            /* 2.求解01背包问题 */
            int[] dp = new int[capacity + 1];
            for(int i=0;i<number+1;i++)
            {
                for(int j=capacity;j>=weight[i];j--)
                {
                    dp[j]=Math.max(dp[j],dp[j-weight[i]]+value[i]);
                }
            }

        }

        //完全背包问题解答（未验证）
        private static void test3(){
            Scanner sc = new Scanner(System.in);
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
            for (int i = 1; i < number + 1; i++) {
                value[i] = sc.nextInt();
            }
            int capacity = sc.nextInt(); // 背包容量
            /* 2.求解01背包问题 */
            int[] dp = new int[capacity + 1];
            for(int i=0;i<number+1;i++) {
                for(int j=weight[i];j<=capacity;j++) {
                    dp[j]=Math.max(dp[j],dp[j-weight[i]]+value[i]);
                }
            }

        }

}

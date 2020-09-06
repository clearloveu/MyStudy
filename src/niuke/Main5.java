package niuke;

import java.util.Scanner;

/**
 * 小团是一个莫得感情的CtrlCV大师，他有一个下标从1开始的序列A和一个初始全部为-1的序列B，两个序列的长度都是n。他会进行若干次操作，
 * 每一次操作，他都会选择A序列中一段连续区间，将其粘贴到B序列中的某一个连续的位置，在这个过程中他也会查询B序列中某一个位置上的值。
 *
 * 我们用如下的方式表示他的粘贴操作和查询操作：
 *
 * 粘贴操作：1  k x y，表示把A序列中从下标x位置开始的连续k个元素粘贴到B序列中从下标y开始的连续k个位置上，原始序列中对应的元素被覆盖。
 * （数据保证不会出现粘贴后k个元素超出B序列原有长度的情况）
 *
 * 查询操作：2 x，表示询问当前B序列下标x处的值是多少。
 *
 * 输入第一行包含一个正整数n，表示序列A和序列B的长度。(1<=n<=2000)
 *
 * 输入第二行包含n个正整数，表示序列A中的n个元素，第 i 个数字表示下标为 i 的位置上的元素，每一个元素保证在10^9以内。
 *
 * 输入第三行是一个操作数m，表示进行的操作数量。(1<=m<=2000)
 *
 * 接下来m行，每行第一个数字为1或2，具体操作细节详见题面。
 *
 */
public class Main5 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int length = in.nextInt();
        int[] A = new int[length];
        int[] B = new int[length];
        for (int i = 0; i < length; i++) {
            A[i] = in.nextInt();
            B[i] = -1;
        }
        int m = in.nextInt();
        in.nextLine();
        for (int i = 0; i < m; i++) {
            String sss = in.nextLine();
            String[] ss = sss.split(" ");
            int temp1 = Integer.parseInt(ss[0]);
            if (temp1==2) {
                int temp2 = Integer.parseInt(ss[1])-1;
                System.out.println(B[temp2]);
            } else {
                int k = Integer.parseInt(ss[1]);
                int x = Integer.parseInt(ss[2])-1;
                int y = Integer.parseInt(ss[3])-1;
                for (int j = 0; j < k; j++) {
                    B[y++] = A[x++];
                }
            }
        }
    }
}

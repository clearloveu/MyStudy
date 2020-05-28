package niuke;

import java.util.Scanner;

/**
 * @author zg
 * @create 2020-03-19 12:54
 *
 * 给出一个正整数N和长度L，找出一段长度大于等于L的连续非负整数，他们的和恰好为N。答案可能有多个，我我们需要找出长度最小的那个。
 * 例如 N = 18 L = 2：
 * 5 + 6 + 7 = 18
 * 3 + 4 + 5 + 6 = 18
 * 都是满足要求的，但是我们输出更短的 5 6 7
 *
 */

/* * 题目需要找出一段长度大于等于L的连续非负整数，使得其和等于N。L要尽可能小。
 * 考虑是连续非负整数，所以其和我们能用中位数来表示，分两种情况：
 * 情况一，长度为奇数的情况：
 *   此时中位数一定是整数，N = 中位数 x L
 * 情况二，长度为偶数的情况：
 *   此时中位数肯定是xx.5的形式，N = xx.5 x L
 * * 所以我们从长度L开始枚举，至100为止，分奇偶讨论。 */
public class test1 {
    public static void main(String[] args) {
//        myTest(543792409,57);
        otherTest(543792409,57);
    }

    private static void myTest(){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int l = in.nextInt();
        in.close();
        int bg = -1,ed = -1;
        for(int i = l;i<=100;i++){
            //奇数
            if((i&1)==1&&n%i==0){
                int mid = n/i;
                bg = mid-(i-1)/2;
                ed = mid+(i-1)/2;
                if(bg>=0) break;
            }
            else if((i&1)==0){
                if(n%i==(i/2)%i){
                    int mid = n/i;
                    bg = mid-(i-2)/2;
                    ed = mid+(i)/2;
                    if(bg>=0) break;
                }
            }
        }
        if(bg>=0){
            for(int i = bg;i<ed;i++){
                System.out.print(i+" ");
            }
            System.out.println(ed);

        }
        else System.out.println("No");
    }


    private static void otherTest(int n, int l){
        int bg = -1, ed = -1;
        for (int i = l; i <= 100; ++i) {
            // 奇数，中位数一定是整数
            if (i % 2 == 1 && n % i == 0) {
                int mid = n / i;
                bg = mid - (i - 1) / 2;
                ed = mid + (i - 1) / 2;
                if (bg >= 0) // 答案要合法，即需要是非负整数
                    break;
            }
            // 偶数，中位数一定是0.5形式
            if (i % 2 == 0 && (double)n / i - n / i == 0.5f) {
                int mid = n / i;
                bg = mid - i / 2 + 1;
                ed = mid + i / 2;
                if (bg >= 0)
                    break;
            }
        }
        if (bg >= 0) {
            for (int i = bg; i < ed; ++i) {
                System.out.print(i + " ");
            }
            System.out.println(ed);
        }
        else {
            System.out.println("No");
        }
    }
}
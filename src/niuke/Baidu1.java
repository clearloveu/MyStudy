package niuke;

import java.util.Scanner;

/**
 * @author zg
 * @create 2020-03-19 19:33
 *
 * 百度笔试3.29
 *
 */
public class Baidu1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
//        int n = 3;

//        int res = -1;
        //暴力法
//        for (int i = n; i >=2; i--) {
//            for (int j=i-1;j>=1;j--){
//                if (res<i*j){
//                    int temp =   findYue(i,j);
//                    int ans = i*j/temp - temp;
//                    res = Math.max(ans,res);
//                }
//            }
//        }

        int max = 1;
        for (int i = n-1; i >=2; i--) {
            if (findYue(i,n)==1){
                max = i;
                break;
            };
        }

        System.out.println(n*max-1); // 将会超过int范围，所以只有30%的正确率

    }

    //最大公约数
    private static int findYue(int a, int b){
        int res = 1;
        for (int i = 2; i<a||i<b;i++){
            if (a%i==0 && b%i==0){
                res *=i;
                a = a/i;
                b = b/i;
                i--;
            }
        }
        return res;
    }

}

package niuke;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author zg
 * @create 2020-03-19 19:33
 *
 * 网易笔试4.11
 *
 */
public class Wangyi4 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        s = s.substring(1,s.length()-1);
        String[] ss = s.split("]");
        int[][] hezi = new int[ss.length][3];
        for (int i = 0; i < ss.length; i++) {
            if (ss[i].startsWith(",")) ss[i] = ss[i].substring(1);
            String[] temp = ss[i].split(",");
            for (int j = 0; j < 3; j++) {
                String t = temp[j].trim();
                if (t.startsWith("[")) t=t.substring(1);
                hezi[i][j] = Integer.parseInt(t);
            }
        }

        Arrays.sort(hezi,(a,b)->{
            int tempA = a[0]+a[1]+a[2];
            int tempB = b[0]+b[1]+b[2];
            if (tempA>=tempB) return 1;
            else return -1;
        });
        int[] dp = new int[hezi.length];
        dp[0] = 1;
        for (int i = 1; i < hezi.length; i++) {
            int temp = 1;
            for (int j = 0; j < i; j++) {
                if (hezi[i][0]>hezi[j][0]&&hezi[i][1]>hezi[j][1]&&hezi[i][2]>hezi[j][2]){
                    temp = Math.max(temp,dp[j]+1);
                }
            }
            dp[i] = temp;
        }
        int res =1;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res,dp[i]);
        }
        System.out.println(res);

    }

}

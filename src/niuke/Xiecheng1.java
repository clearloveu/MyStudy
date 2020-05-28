package niuke;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * @author zg
 * @create 2020-03-19 19:33
 *
 * 4.1携程笔试
 * 至少需要多少电话员：牛客搜题
 *
 */
public class Xiecheng1 {


    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    static int calcMinStaff(String[] phones) {
        int[][] myPhones = new int[phones.length][2];
        for (int i=0;i<phones.length;i++){
            String[] temp = phones[i].split(",");
            myPhones[i][0] = Integer.parseInt(temp[0]);
            myPhones[i][1] = Integer.parseInt(temp[1]);
        }

        //贪心
        Arrays.sort(myPhones,(a,b)->{
            if (a[0]-b[0]>0) return 1;
            else if (a[0]-b[0]<0) return -1;
            else if (a[1]-b[1]>0) return 1;
            else if (a[1]-b[1]<0) return -1;
            else return 0;
        });
        int res = 1;
        HashSet<Integer> set = new HashSet<>();
        int[] dp = new int[myPhones.length];
        dp[0] = 1;
        for (int i = 1; i < myPhones.length; i++) {
            int start = myPhones[i][0];
            int temp  =0;
            for (int j = 0;j<i;j++){
                if (set.contains(j))continue;
                if (myPhones[j][1]<=start) {
                    temp--;
                    set.add(j);
                }
            }
            dp[i] = dp[i-1] + 1 + temp;
            res = Math.max(dp[i],res);
        }



        return res;
    }
    /******************************结束写代码******************************/


    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        String[] phones = new String[n];
        for (int i = 0; i < n; i++) {
            phones[i] = in.nextLine();
        }

        int res;
        res = calcMinStaff(phones);
        System.out.println(String.valueOf(res));

    }
}

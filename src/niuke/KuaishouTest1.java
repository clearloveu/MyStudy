package niuke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author zg
 * @create 2020-03-19 19:33
 *
 * https://www.nowcoder.com/test/22088902/summary
 *
 * 小明最近在做病毒自动检测，他发现，在某些library 的代码段的二进制表示中，如果包含子串并且恰好有k个1，就有可能有潜在的病毒。library的二进制表示可能很大，并且子串可能很多，人工分析不可能，于是他想写个程序来先算算到底有多少个子串满足条件。如果子串内容相同，但是开始或者结束位置不一样，则被认为是不同的子串。
 * 注：子串一定是连续的。例如"010"有6个子串，分别是 "0, "1", "0", "01", "10", "010"
 *
 */
public class KuaishouTest1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int k = in.nextInt();
        in.nextLine();
        String s = in.nextLine();
        s = s.trim();
        if (k==0) {
            int left = 0;
            int right = 0;
            int res = 0;
            while (right<s.length()){
                if (s.charAt(right)=='1') {
                    res += (right-left)*(right-left+1)/2;
                    left = right+1;
                }
                right++;
            }
            if (s.charAt(right-1)=='0') res += (right-left)*(right-left+1)/2;
            System.out.println(res);
        }
        else {
            int res = test(s, k);
            System.out.println(res);
        }

    }


    public static int test(String s,int k){
        int count = 0;
        int right = 0;
        int res = 0;
        List<Integer> list = new ArrayList<>();
        while (right<s.length()){
            if (s.charAt(right)=='1') {
                count++;
                list.add(right);
            }
            right++;
            if (count>=k){
                res += test2(s,list,k);
                count--;
            }

        }
        return res;
    }
    private static int test2(String s,List<Integer> list,int k){
        int leftOne = list.get(list.size()-k);
        int rightOne = list.get(list.size()-1);
        int left ;
        //找到前面的最前的0
        if (list.size()-k==0) {
            left = 0;
        }
        else left = list.get(list.size()-1-k)+1;
        //找到后一个最后的0
        int right = s.length()-1;
        for (int i = rightOne+1; i < s.length(); i++) {
            if (s.charAt(i)=='1') {
                right = i-1;
                break;
            }
        }
        return (leftOne-left+1)*(right-rightOne+1);

    }
}

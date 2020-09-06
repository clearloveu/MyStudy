package niuke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 小团从某不知名论坛上突然得到了一个测试默契度的游戏，想和小美玩一次来检验两人的默契程度。游戏规则十分简单，首先有给出一个长度为n的序列，最大值不超过m。
 *
 * 小团和小美各自选择一个[1,m]之间的整数，设小美选择的是l，小团选择的是r，我们认为两个人是默契的需要满足以下条件:
 *
 * 1. l 小于等于r。
 *
 * 2. 对于序列中的元素x，如果0<x<l,或r<x<m+1,则x按其顺序保留下来，要求保留下来的子序列单调不下降。
 *
 * 小团为了表现出与小美最大的默契，因此事先做了功课，他想知道能够使得两人默契的二元组<l,r>一共有多少种。
 *
 * 我们称一个序列A为单调不下降的，当且仅当对于任意的i>j,满足A_i>=A_j。
 *
 * 输入第一行包含两个正整数m和n，表示序列元素的最大值和序列的长度。(1<=n,m<=100000)
 *
 * 输入第二行包含n个正整数，表示该序列。
 */
public class Main4 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums.add(in.nextInt());
        }
        //暴力
        int res = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = i; j < m+1; j++) {
                List<Integer> list = new ArrayList<>(nums);
                for (int k = 0; k < list.size(); k++) {
                    int temp = list.get(k);
                    if (!((0<temp && temp<i) || (j<temp && temp < m+1) )) {
                        list.remove(k);
                        list.add(k, 0);
                    }
                }
                boolean flag = true;
                int start = 0;
                for (int k = 0; k < list.size(); k++) {
                    int temp = list.get(k);
                    if (temp==0) continue;
                    if (start == 0) start = temp;
                    else if (temp < start) {
                        flag = false;
                        break;
                    } else start = temp;

                }
                if (flag) res++;
            }
        }
        System.out.println(res);
    }
}



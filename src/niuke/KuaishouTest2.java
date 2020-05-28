package niuke;

import java.util.Scanner;

/**
 * @author zg
 * @create 2020-03-19 19:33
 *
 * 现在你的班级刚刚参加了一个只有单选题的考试。班级一共n个学生，考试有m个问题。每个题目都有5个可选答案（A，B，C，D，E）。并且每个题目只有一个正确答案。每个题目的分数并不一样，第i个题目的分数用a[i]表示。如果题目没答对该题会获得0分。
 * 考试结束后，每个学生都记得自己的答案，但是他们还不知道正确答案是什么。如果非常乐观的考虑，他们班级最多可能得到多少分呢？
 *
 *
 * 输入描述:
 * 第一行包含2个正整数，n和m。(1 <= n, m <= 1000，n是班级中学生数量，m是题目的数量)
 *
 * 下面n行数据每行包含一个string si，表示第i个学生的答案。si的第j个字符表示该学生第j个题目的答案。
 *
 * 输出描述:
 * 一个正整数，全班学生最大的可能获得的分数总和。
 *
 */
public class KuaishouTest2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int studentNum = in.nextInt();
        int timuNum = in.nextInt();
        in.nextLine();
        String[] answer =new String[studentNum];
        for (int i = 0; i < studentNum; i++) {
            answer[i] = in.nextLine();
        }
        int[] socres = new int[timuNum];
        StringBuilder[] res = new StringBuilder[timuNum];
        for (int i = 0; i < timuNum; i++) {
            socres[i] = in.nextInt();
            res[i] = new StringBuilder();
        }


        for (int i = 0; i < studentNum; i++) {
            String thisanswer = answer[i];
            for (int j = 0; j < timuNum; j++) {
                char xuanze = thisanswer.charAt(j);
                res[j].append(xuanze);
            }
        }
        int s = 0;
        for (int i = 0; i < timuNum; i++) {
            String temp = res[i].toString();
            int A = 0;
            int B =0;
            int C = 0;
            int D =0;
            int E =0;
            for (int j = 0; j < studentNum; j++) {
                if (temp.charAt(j)=='A') A++;
                else if (temp.charAt(j)=='B') B++;
                else if (temp.charAt(j)=='C') C++;
                else if (temp.charAt(j)=='D') D++;
                else if (temp.charAt(j)=='E') E++;
            }
            int max = Math.max(A,Math.max(B,Math.max(C,Math.max(D,E))));
            s +=max*socres[i];
        }
        System.out.println(s);
    }
}

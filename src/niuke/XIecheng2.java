package niuke;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author zg
 * @create 2020-03-19 19:33
 *
 * 4.1携程笔试
 * 海豚，每过多少年生小海豚，然后x年有多少只海豚
 *
 */
public class XIecheng2 {


    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    static long countDolphin(int n, int m, int[] birthYear, int x) {

        //暴力法
        int num = 1;
        int die = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(0);
        while (!q.isEmpty()){
            int current = q.poll();
            if (current+m<x) die++;
            for (int i = 0; i < birthYear.length; i++) {
//                if (birthYear[i]>m) continue;
                if (current+birthYear[i]<=x) {
                    q.add(current+birthYear[i]);
                    num +=1;
                }
            }
        }


        return num*n-die*n;
    }
    /******************************结束写代码******************************/


    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        long res;

        int _n;
        _n = Integer.parseInt(in.nextLine().trim());

        int _m;
        _m = Integer.parseInt(in.nextLine().trim());

        int _birthYear_size = 0;
        _birthYear_size = Integer.parseInt(in.nextLine().trim());
        int[] _birthYear = new int[_birthYear_size];
        int _birthYear_item;
        for(int _birthYear_i = 0; _birthYear_i < _birthYear_size; _birthYear_i++) {
            _birthYear_item = Integer.parseInt(in.nextLine().trim());
            _birthYear[_birthYear_i] = _birthYear_item;
        }

        int _x;
        _x = Integer.parseInt(in.nextLine().trim());

        res = countDolphin(_n, _m, _birthYear, _x);
        System.out.println(String.valueOf(res));

    }
}

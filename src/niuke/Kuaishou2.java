package niuke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author zg
 * @create 2020-03-19 19:33
 *
 * 快手笔试4.12
 *
 */
public class Kuaishou2 {
    public  static  int[] GetPowerFactor (int R, int N) {
        List<Integer> list = new ArrayList<>();
        boolean flag = true;
        while (R!=0){
            int t = R%N;
            if (t>1) {
                flag = false;
                break;
            }
            list.add(t);
            R = R/N;
        }

        if (flag){
            int s = 0;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i)==1) s++;
            }
            int[] res = new int[s];
            int index = 0;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i)==1) {
                    res[index] = i;
                    index++;
                }
            }
            return res;
        }else return new int[0];
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String temp = in.nextLine();
        String[] temp2 = temp.trim().split(",");
        int R = Integer.parseInt(temp2[0]);
        int N  = Integer.parseInt(temp2[1]);

        int[] res = GetPowerFactor(R,N);
        System.out.println(Arrays.toString(res));

    }
}

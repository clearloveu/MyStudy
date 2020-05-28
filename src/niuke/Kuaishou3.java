package niuke;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zg
 * @create 2020-03-19 19:33
 *
 * 快手笔试4.12
 *
 */
public class Kuaishou3 {
    public static int[] WaitInLine (int[] a, int[] b) {

        int[][] temp = new int[a.length][2];
        Map<String,Integer> map = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            temp[i][0] = a[i];
            temp[i][1] = b[i];
            String key = String.valueOf(a[i])+String.valueOf(b[i]);
            map.put(key,i);

        }


        Arrays.sort(temp,(c,d)->{

            int tempC = c[0]-c[1];
            int tempD = d[0] - d[1];
            if (tempC>tempD) return -1;
            else if (tempC<tempD) return 1;
            else if (c[0]>d[0]) return 1;
            else return -1;


        });
        int[] res = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            String key = String.valueOf(temp[i][0])+String.valueOf(temp[i][1]);
            res[i] = map.get(key)+1;
        }



        return res;


    }
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
        int[] a = {8,9,7};
        int[] b = {5,8,3};
        System.out.println(Arrays.toString(WaitInLine(a, b)));


    }
}

package niuke;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author zg
 * @create 2020-03-19 19:33
 *
 * https://www.nowcoder.com/test/1088888/summary
 *
 */
public class HuaweiTest2 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
        while (in.hasNext()){
            Set<Integer> set = new HashSet<>();
            int n = in.nextInt();
            for (int i = 0; i < n; i++) {
                set.add(in.nextInt());
            }
            int[] res = new int[set.size()];
            int index =0 ;
            for (int temp: set) {
                res[index] = temp;
                index++;
            }
            Arrays.sort(res);
            for (int i = 0; i < res.length; i++) {
                System.out.println(res[i]);
            }

        }


    }

}

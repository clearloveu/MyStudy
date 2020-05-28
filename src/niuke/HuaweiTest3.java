package niuke;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author zg
 * @create 2020-03-19 19:33
 *
 * https://www.nowcoder.com/test/1088888/summary
 *
 */
public class HuaweiTest3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNext()){
            String s = in.nextLine();
            s = s.trim();
            s = s.substring(2);
            Map<String,Integer> m = new HashMap<>();
            for (int i = 0; i < 10; i++) {
                m.put(String.valueOf(i),i);
            }
            m.put("A",10);
            m.put("B",11);
            m.put("C",12);
            m.put("D",13);
            m.put("E",14);
            m.put("F",15);
            long res = 0;
            long index = 1;
            for (int i = s.length()-1; i >=0; i--) {
                int temp = m.get(String.valueOf(s.charAt(i)));
                res +=temp*index;
                index*=16;
            }
            System.out.println(res);
        }


    }
}

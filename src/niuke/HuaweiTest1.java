package niuke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author zg
 * @create 2020-03-19 19:33
 *
 * https://www.nowcoder.com/test/1088888/summary
 *
 */
public class HuaweiTest1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
        List<Integer> list = new ArrayList<>();


        while(in.hasNext()){
            int temp  = in.nextInt();
            System.out.println(conculate(temp));
        }

//        while(true){
//            int temp  = in.nextInt();
//            if ((temp )!=0){
//                list.add(temp);
//            }else break;
//        }
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(conculate(list.get(i)));
//        }

    }
    public static int conculate(int sum){
        int res = 0;
        while (sum>2){
            int temp = sum/3;
            res +=temp;
            sum = sum/3+sum%3;
        }
        if (sum==2) res++;
        return res;
    }

}

package niuke;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author zg
 * @create 2020-03-19 19:33
 *
 * 圆景笔试3.22 第二题
 *
 */
public class YuanJing2 {
    public static void main(String[] args) {

//        int n = sc.nextInt();
//        int ans = 0, x;
//        for(int i = 0; i < n; i++){
//            for(int j = 0; j < n; j++){
//                x = sc.nextInt();
//                ans += x;
//            }
//        }

        Scanner in = new Scanner(System.in);
        int num = in.nextInt();
//        in.nextLine();
        int[][] sores = new int[num][2];

        for (int i=0;i<num;i++){
            for (int j=0;j<2;j++){
                int temp = in.nextInt();
                sores[i][j] = temp;
            }
        }
//        int index = 0;
//        while (in.hasNext()){
//            String string = in.nextLine();
////            if (string.equals("end")) break;
//            string = string.trim();
//            String[] strings = string.split(" ");
//            for (int i =0;i<strings.length;i++){
//                sores[index][i] = Integer.parseInt(strings[i]);
//            }
//            index+=1;
//        }
        mySort(sores);
        //输出
        for (int i = 0;i<num;i++){
            System.out.println(sores[i][0]+" "+sores[i][1]);

        }

    }

    public  static void mySort(int[][] sores){
        Arrays.sort(sores,(a,b)->{
            if(a[1]>b[1]) return 1;
            else if(a[1]<b[1]) return -1;
            else if (a[0]>b[0]) return -1;
            else if (a[0]<b[0]) return 1;
            else return 0;
        });

    }

}

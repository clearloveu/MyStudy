package niuke;

import java.util.Scanner;

/**
 * @author zg
 * @create 2020-03-19 19:33
 */
public class Huawei2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] m2 = new int[n];
        String[] shuju2 = new String[n];
        for (int i = 0; i < n; i++) {
            int m = in.nextInt();
            in.nextLine();
            String shuju = in.nextLine();

            m2[i] = m;
            shuju2[i] = shuju;

        }

        for (int i = 0; i < n; i++) {
            System.out.println(conculate(m2[i],shuju2[i]));
        }

    }

    private static String conculate(int n , String shuju){
        shuju = shuju.trim();
        char[] temp = shuju.toCharArray();
        for (int i = 0; i < n; i++) {
            if (temp[i]!='1') {

                boolean canGai = false;
                boolean canFind = false;
                int index = -1;
                for (int j = i+1; j < n; j++) {
                    if (j==i+1&&temp[j]=='0'){
                        temp[i] = '1';
                        canGai = true;
                        canFind = true;
                        break;
                    }else if (temp[j]=='0'){
                        index = j;
                        canFind = true;
                        break;
                    }
                }
                if (!canFind) continue;
                if (!canGai){
                    for (int j = i; j <= index; j++) {
                        if (j==i+1) temp[j] = '0';
                        else temp[j]='1';
                    }
                }

            }

        }
        return new String(temp);

    }
}

package huawei2020;

import java.util.Scanner;

/**
 * @author zg
 * @create 2020-04-01 10:43
 */
public class test {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        in.nextLine();
        String[] strings = new String[N];
        for (int i = 0; i < N; i++) {
            strings[i] = in.nextLine();
        }

        String[] res = new String[N];
        for (int i = 0; i < N; i++) {
            String temp = resersion(strings[i]);
            String temp2 = resersion(new StringBuilder((strings[i])).reverse().toString());
            res[i] = temp.equals("NO")?temp2:temp2.equals("NO")?"NO":Integer.parseInt(temp)>Integer.parseInt(temp2)?temp2:temp;
        }

        for (int i=0;i<N;i++){
            System.out.println(res[i]);
        }

    }


    private  static String resersion(String s){
        char[] res  = s.toCharArray();
        int count = 0;
        for (int i=1;i<s.length()-1;i++){
            if (res[i-1]=='0') continue;
            else {
                count++;
                res[i-1] = '0';
                res[i] = res[i]=='0'?'1':'0';
                res[i+1] = res[i+1]=='0'?'1':'0';
            }
        }
        if ((res[s.length()-1]=='0'&&res[s.length()-2]=='0')) return new String(String.valueOf(count));
        else if (res[s.length()-1]=='1'&&res[s.length()-2]=='1') return new String(String.valueOf(count+1));
        else return "NO";

    }
}

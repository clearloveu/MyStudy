package niuke;

import java.util.Scanner;

/**
 * @author zg
 * @create 2020-03-19 19:33
 *
 * https://www.nowcoder.com/question/next?pid=16516564&qid=362291&tid=32475786
 *
 */
public class ZijieTest1 {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        String[] strings = new String[n];
        for (int i = 0; i < n; i++) {
            strings[i] = in.nextLine();
        }
        in.close();
        for (int i = 0; i < n; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(strings[i].charAt(0));
            int j = 1;
            while ( j < strings[i].length()) {
                String temp = strings[i];
                if (temp.charAt(j)!=temp.charAt(j-1)){
                    stringBuilder.append(temp.charAt(j));
                    j++;
                }else {
                    int res = findIndex(temp,j);
                    if (res==-1){
                        stringBuilder.append(temp.charAt(j));
                        j++;

                    }else if (temp.charAt(j+1)==temp.charAt(j)){
                        stringBuilder.append(temp.charAt(j));
                        j = res+1;
                    } else  {
                        stringBuilder.append(temp.charAt(j));
                        stringBuilder.append(temp.charAt(j + 1));
                        j = res+1;

                    }
                }
            }
            System.out.println(stringBuilder);
        }

    }

    private static int findIndex(String temp , int index ){
        if (index==temp.length()-1) return -1;
        if (temp.charAt(index+1)==temp.charAt(index)){
            while (index<=temp.length()-2 && temp.charAt(index+1)==temp.charAt(index)){
                index++;
            }
            return index;
        }else {
            if (temp.length()-1-index<2)return -1;
            else if (temp.charAt(index+1)!=temp.charAt(index+2)) return -1;
            else {
                index = index+2;
                while (index<=temp.length()-2 && temp.charAt(index+1)==temp.charAt(index)){
                    index++;
                }
                return index;
            }
        }
    }

}

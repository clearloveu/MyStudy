package niuke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<String> res ;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String s = in.nextLine();
        s = s.trim();
        res = new ArrayList<>();
        res.add(String.valueOf(s.charAt(0)));

        for (int i = 0; i < s.length(); i++) {
            find(s,i,true);
            find(s,i,false);
        }
        if (res.get(0).length()==1) System.out.println("null");
        else {
            for (int i = 0; i < res.size(); i++) {
                System.out.println(res.get(i));
            }
        }



    }

    private static void find(String a , int index,boolean flag){
        int left;
        int right;
        StringBuilder temp = new StringBuilder();
        if (flag){
            left = index-1;
            right = index+1;
            temp.append(a.charAt(index));
        }else {
            left = index;
            right = index+1;
        }
        while (left>=0&&right<=a.length()-1&&a.charAt(left)==a.charAt(right)){

            temp.insert(0,a.charAt(left));
            temp.append(a.charAt(right));
            left--;
            right++;

        }
        if (res.get(0).length()<temp.length()){
            res.clear();
            res.add(temp.toString());
        }else if (res.get(0).length()==temp.length()){
            res.add(temp.toString());
        }
    }



}

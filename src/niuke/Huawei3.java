package niuke;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

/**
 * @author zg
 * @create 2020-03-19 19:33
 */
public class Huawei3 {
    static int res;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            int[][] A = new int[9][9];
            List<HashSet<Integer>> r = new ArrayList<>();
            List<HashSet<Integer>> c = new ArrayList<>();
            List<HashSet<Integer>> small = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                r.add(new HashSet<>());
                c.add(new HashSet<>());
                small.add(new HashSet<>());

            }

            for (int i = 0; i < 9; i++) {
                String temp = in.nextLine();
                temp = temp.trim();
                temp = temp.substring(1,temp.length()-1);
                String[] temp2 = temp.split(",");
                for (int j = 0; j < 9; j++) {
                    A[i][j] = Integer.parseInt(temp2[j]);
                    if (A[i][j]!=0){
                        r.get(i).add(A[i][j]);
                        c.get(j).add(A[i][j]);
                        small.get(i/3*3+j/3).add(A[i][j]);
                    }
                }
            }
            recursion(A,r,c,small,0);


            for (int i = 0; i < 9; i++) {
                System.out.print("{");
                for (int j = 0; j < 8; j++) {
                    System.out.print(A[i][j]+",");
                }
                System.out.println(A[i][8]+"}");
            }


        }


    }
    private  static  boolean recursion(int[][] A,List<HashSet<Integer>> r,List<HashSet<Integer>> c,List<HashSet<Integer>> small,int count){
        if (count==81) {
            return true;

        }

        int m = count/9;
        int n = count%9;
        int k =m/3*3+n/3;
        if (A[m][n]!=0){
            return recursion(A,r,c,small,count+1);
        }else {
            for (int i = 1; i <= 9; i++) {
                if (!r.get(m).contains(i)&&!c.get(n).contains(i)&&!small.get(k).contains(i)){
                    A[m][n] = i;
                    r.get(m).add(i);
                    c.get(n).add(i);
                    small.get(k).add(i);
                    if (recursion(A,r,c,small,count+1)) return true;

                    A[m][n] = 0;
                    r.get(m).remove(i);
                    c.get(n).remove(i);
                    small.get(k).remove(i);

                }
            }
            return false;

        }




    }
}

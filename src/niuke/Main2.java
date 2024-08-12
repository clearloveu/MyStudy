package niuke;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main2 {
    private static BigInteger ans;
    private static int[] nums;
    private static int k;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        k = scanner.nextInt();
        nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        ans = new BigInteger("0");
//        findAll(line);

        System.out.println(ans.toString());
    }

    private static void findAll(String line){
        int length = 2;
        while (length <= line.length()){
            // 回溯法寻找该长度下的所有子序列
            huiSu(length, line, new StringBuilder(), 0);
            length++;
        }
    }

    private static void huiSu(int length, String line, StringBuilder seq, int index){
        boolean flag = judge(seq.toString());
        if(!flag) return;

        if (seq.length() == length) {
            ans = ans.add(new BigInteger("1"));
//            System.out.println(seq.toString());
            return;
        } else if (seq.length() > length) return;

        for (int i = index; i < line.length(); i++) {
            seq.append(line.charAt(i));
            huiSu(length, line, seq, i+1);
            seq.deleteCharAt(seq.length()-1);
        }
    }

    private static boolean judge(String temp){
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < temp.length(); i++) {
            char currentChar = temp.charAt(i);
            if (set.contains(currentChar)) return false;
            set.add(currentChar);
        }
        return true;
    }
}

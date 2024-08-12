package niuke;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main5 {

    // 5 3 7 2 1 6 5 3 21 30
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int q = in.nextInt();


        List<Integer> nums = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            nums.add(in.nextInt());
        }
        for (int i = 0; i < q; i++) {
            int temp = in.nextInt();
            boolean flag = helper(nums, temp);
            System.out.println(flag?"YES":"NO");
        }
    }

    private static boolean helper(List<Integer> nums, int query){
        if (nums.size() <=0 ) return false;

        // Collections.sort(nums);
        int sum = 0;
        for (int i = 0; i < nums.size(); i++) {
            sum += nums.get(i);
        }
        if (sum < query) return false;
        else if (sum == query) return true;

        int arg = sum/nums.size();
        List<Integer> upper = new ArrayList<>();
        List<Integer> lower = new ArrayList<>();

        for (int i = 0; i < nums.size(); i++) {
            int current = nums.get(i);
            if (current <= arg) lower.add(current);
            else upper.add(current);
        }

        boolean flag = helper(lower, query);
        if (flag) return flag;
        return helper(upper, query);
    }
}



package niuke;

import java.util.Scanner;

public class Main3 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int length = in.nextInt();
        int[] nums = new int[length];
        for (int i = 0; i < length; i++) {
            nums[i] = in.nextInt();
        }
        int[] dp1 = new int[length];
        dp1[0] = 1;
        int[] dp2 = new int[length];
        dp2[length-1] = 1;

        for (int i = 1; i < length; i++) {
            int current = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    int temp = dp1[j]+1;
                    current = Math.max(current, temp);
                }
            }
            dp1[i] = current;
        }
        for (int i = length-2; i >=0; i--) {
            int current = 1;
            for (int j = length-1; j > i; j--) {
                if (nums[j] < nums[i]) {
                    int temp = dp2[j]+1;
                    current = Math.max(current, temp);
                }
            }
            dp2[i] = current;
        }

        int max = 0;
        for (int i = 0; i < length; i++) {
            int temp = dp1[i]+dp2[i]-1;
            max = Math.max(max, temp);
        }
        boolean flag = false;
        if (max == length) {
            if (nums[0] <= nums[1]) {
                for (int i = 1; i < length-1; i++) {
                    if (nums[i] > nums[i+1]) flag = true;
                }
            } else {
                for (int i = 1; i < length-1; i++) {
                    if (nums[i] < nums[i+1]) flag = true;
                }
            }
        }
        System.out.println(!flag?max:0);

    }
}

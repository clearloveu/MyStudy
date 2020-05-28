package leetcode.jianZhiOffer;

import java.util.Arrays;

/**
 * @author zg
 * @create 2020-03-12 13:24
 *
 * 把数组排成最小的数[中等]
 *
 */
public class Question45 {
    private String minNumber(int[] nums) {
        Integer[] nums2 = new Integer[nums.length];
        for(int i=0;i<nums.length;i++){
        nums2[i] = nums[i];
    }


        Arrays.sort(nums2,(a, b)->{

        String tempA = String.valueOf(a);
        String tempB = String.valueOf(b);
        String AB = tempA+tempB;
        String BA = tempB+tempA;
        int index = 0;
        while (true){
            // AB = BA
            if(index>=AB.length()) return 0;
            // AB 比 BA 大
            if(AB.charAt(index)-BA.charAt(index)>0) return 1;
            // AB 比 BA 小
            if(AB.charAt(index)-BA.charAt(index)<0) return -1;
            index +=1;
        }
    });
    StringBuilder sb = new StringBuilder();
        for(int i:nums2){
        sb.append(i);
    }
        return sb.toString();

    }

    public static void main(String[] args) {
        int[] nums = {3,30,34,5,9};
        System.out.println(new Question45().minNumber(nums));
    }

}

package leetcode;

/**
 * @author zg
 * @create 2019-12-09 15:42
 *
 * 寻找两个有序数组的中位数[困难]                   [未完成]
 *
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 *
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 *
 */
public class Test4 {
//    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
//
//        int len_s1 = nums1.length;
//        int len_s2 = nums2.length;
//
//        int midIndex = (int) Math.ceil(((len_s1+len_s2)/2.0));
//
//        int mid_s1 = len_s1/2-1 ;
//        int mid_s2 = midIndex-mid_s1-2;
//
//        int[] nums1_1 = new int[len_s1+1];
//        int[] nums2_2 = new int[len_s2+1];
//
//        for (int i =0 ; i<len_s1;i++){
//            nums1_1[i] = nums1[i];
//        }
//        nums1_1[len_s1] = Integer.MAX_VALUE;
//        for (int i =0 ; i<len_s2;i++){
//            nums2_2[i] = nums2[i];
//        }
//        nums2_2[len_s2] = Integer.MAX_VALUE;
//
//
//        while(nums1_1[mid_s1]<nums2_2[mid_s2+1] && nums2_2[mid_s2]<nums1_1[mid_s1+1]){
//            if (nums1_1[mid_s1]>nums2_2[mid_s2+1]){
//                mid_s1 = mid_s1 - (len_s1-mid_s1)/2;
//                mid_s2 = midIndex - mid_s1 ;
//            }else {
//                mid_s1 = mid_s1 + (len_s1-mid_s1)/2;
//                mid_s2 = midIndex - mid_s1 ;
//            }
//        }
//        double answer ;
//        if ((len_s1+len_s2)%2 == 0){
//            answer = (Math.max(nums1_1[mid_s1],nums2_2[mid_s2])+Math.min(nums1_1[mid_s1+1],nums2_2[mid_s2+1]))/2.0;
//        }
//
//        return 0.0;
//    }


    //官方解答，非自己的代码
    public static double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A; A = B; B = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j-1] > A[i]){
                iMin = i + 1; // i is too small
            }
            else if (i > iMin && A[i-1] > B[j]) {
                iMax = i - 1; // i is too big
            }
            else { // i is perfect
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; }

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;


    }




    public static void main(String[] args) {
        int[] nums1 = {1,3};
        int[] nums2 = {2,4};
        double answer = findMedianSortedArrays(nums1,nums2);
        System.out.println(answer);

    }

}

package leetcode;

/**
 * @author zg
 * @create 2020-03-25 15:33
 */
public class Main {
    public static void main(String[] args) {
        int[] a = { 49, 38, 65, 97, 76, 13, 27, 50 };
        int[] b = {10,6,13,41,5,6,61,6,6,6,6,16,7,18,6,10};
        quickSort(a, 0, a.length-1);
//        System.out.println("排序好的数组：");
        for (int e : a)
            System.out.print(e+" ");

    }
    public static void quickSort(int[] nums, int left, int right) {
        if (left<right) {
            int mid = (left+right)/2;
            quickSort(nums, left, mid);
            quickSort(nums, mid+1, right);
            merge(nums, left, mid, right);
        }
    }


    private static void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[nums.length];
        int leftIndex = left;
        int rightIndex = mid+1;
        int index = left;
        while (leftIndex<=mid&&rightIndex<=right) {
            if (nums[leftIndex]<nums[rightIndex]) temp[index++] = nums[leftIndex++];
            else temp[index++] = nums[rightIndex++];
        }
        while (leftIndex<=mid) temp[index++] = nums[leftIndex++];
        while (rightIndex<=right) temp[index++] = nums[rightIndex++];
        for (int i = left; i <= right ; i++) {
            nums[i] = temp[i];
        }
    }








}


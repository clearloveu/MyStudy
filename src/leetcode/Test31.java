package leetcode;

import java.util.Arrays;

/**
 * @author zg
 * @create 2019-12-12 17:01
 *
 * 下一个排列[中等]
 *
 *  实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 *
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 *
 * 必须原地修改，只允许使用额外常数空间。
 *
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 */
public class Test31 {
    // 复杂度高，参考nextPermutation2
    public void nextPermutation(int[] nums) {
        if(nums.length == 1) return;
        int index = nums.length -2;
        while(true) {
            if(index == -1) {
                break;
            }
            if(nums[index] < nums[index +1]) {
                break;
            }
            index--;
        }
        if(index == -1) {
            int left = 0;
            int right = nums.length-1;
            while(left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        } else {
            findMin(nums, index);
            // 顺序排序，从index+1
            // 快速排序
            fastSort(nums, index+1, nums.length-1);
        }
    }

    private void findMin(int[] nums, int index) {
        int thisIndex = nums[index];
        int findMin = Integer.MAX_VALUE;
        int findMinIndex = -1;
        for(int i = index+1;i<nums.length;i++) {
            if(thisIndex < nums[i]) {
                if(findMin > nums[i]) {
                    findMin = nums[i];
                    findMinIndex = i;
                }
            }
        }
        nums[index] = findMin;
        nums[findMinIndex] = thisIndex;
    }

    private void fastSort(int[] nums, int left, int right) {
        if(right>left ) {
            int index = findPatition(nums, left, right);
            fastSort(nums, left, index-1);
            fastSort(nums, index+1, right);
        }
    }

    private int findPatition(int[] nums, int left, int right) {
        int index = (int)(Math.random() * (right - left)) + left;
        int partition = nums[index];
        nums[index] = nums[left];

        while(left < right) {
            while(left < right && partition <= nums[right]) right--;
            nums[left] = nums[right];
            while(left < right && partition >= nums[left]) left++;
            nums[right] = nums[left];
        }
        nums[left] = partition;
        return left;
    }




    private static void nextPermutation2(int[] nums) {
        //表示字典序中从后往前第一个不是降序的数字的秩，记数字a
        int pivot = 0 ;
        // true：表示不存在下一个更大的排列，即此排序是纯降序的
        boolean flag = true;
        for (int i =nums.length-1 ; i>=1 ; i--) {

            if (nums[i] > nums[i - 1]) {
                pivot = i - 1;
                flag = false;
                break;
            }
        }
        //如果是纯降序排序
        if (flag){
            swap(nums,0,nums.length-1);
            return;
        }
        //如果不是，则交换a和a后面最小的比a大的数字
        for (int i = nums.length-1;i>pivot;i--){
            //因为后面是降序排列的，故从后往前找，第一个比a大的数字就是最小的比a大的数字
            // !!! 这里需要从后往前找，才能兼容相等的情况，比如[2,3,1,3,3] -> [2, 3, 3, 3, 1]，才能保证倒序
            if (nums[i]>nums[pivot]) {
                int temp = nums[pivot];
                nums[pivot] = nums[i];
                nums[i] = temp;
                break;
            }
        }
        //交换后，后面的序列仍然是降序排列的，只要原地修改把它变成升序即可
        int left = pivot+1;
        int right = nums.length-1;
        swap(nums,left,right);
    }

    private static void swap(int[] nums,int left,int right){
        while (left<right){
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left +=1;
            right -=1;
        }
    }



    public static void main(String[] args) {
        int[] nums = {1,2,3};
        int[] nums2 = {2,3,1, 3, 3};
        int[] nums3 = {1,3,2};

        new Test31().nextPermutation2(nums2);

//        for (int num:nums){
//            System.out.println(num+"->");
//        }

        System.out.print(Arrays.toString(nums2));

    }


}

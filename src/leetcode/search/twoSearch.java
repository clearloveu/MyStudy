package leetcode.search;

/**
 * @author zg
 * @create 2020-02-09 18:34
 *
 * 二分查找
 *
 */
public class twoSearch {
    public static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while(left <= right) {  // 注意点
            int mid = ((right - left) >> 1) + left;
            if(nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1;
            else if (nums[mid] > target)
                right = mid - 1;
        }
        // 如果没有找到target，会出现right比left小1的情况而退出循环，此时right就是小于target的最大数字的索引，left就是大于target的最小数字的索引。
        // 可以参考Leetcode69的解答。
        return -1;
    }



    public static int left_bound(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0;
        int right = nums.length; // 注意点1

        while (left < right) {    //注意点2
            int mid = ((right - left) >> 1) + left;
            if (nums[mid] >= target) {  // 注意点3
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        // target 比所有数都大
        if (left == nums.length) return -1;
        // 类似之前算法的处理方式
        return nums[left] == target ? left : -1;
    }


    public static int right_bound(int[] nums, int target) {
        if (nums.length == 0) return -1;
        int left = 0, right = nums.length;

        while (left < right) {
            int mid = ((right - left) >> 1) + left;
            if (nums[mid] == target) {
                // 这里为什么不是像上面一样是left = mid呢？因为(left + right) /2 永远到不了right，
                // 所以让left+1，最后再回过来判断left-1是不是target
                left = mid + 1; // 注意
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }
        if (left == 0) return -1;
        return nums[left-1] == target ? (left-1) : -1;
    }


    public static void main(String[] args) {
        int[] nums = {1,2,4,6,6,6,6,6,6,6,6,9};
        System.out.println(binarySearch(nums,5));
        System.out.println(left_bound(nums,6));
        System.out.println(right_bound(nums,6));
    }

}

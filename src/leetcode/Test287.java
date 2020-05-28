package leetcode;

import java.util.Arrays;

/**
 * @author zg
 * @create 2020-02-18 14:43
 *
 * 寻找重复数[中等]
 *
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 *
 * 示例 1:
 * 输入: [1,3,4,2,2]
 * 输出: 2
 *
 *
 * 示例 2:
 * 输入: [3,1,3,4,2]
 * 输出: 3
 *
 *
 * 说明：
 * 	不能更改原数组（假设数组是只读的）。
 * 	只能使用额外的 O(1) 的空间。
 * 	时间复杂度小于 O(n2) 。
 * 	数组中只有一个重复的数字，但它可能不止重复出现一次。
 *
 *
 */
public class Test287 {
    public int findDuplicate(int[] nums) {
        //更改了数组
        //排序
        Arrays.sort(nums);
        int temp = 0;//表示上一个数
        for(int i=0;i<nums.length;i++){
            if(nums[i]==temp) return nums[i];
            temp = nums[i];
        }
        return -1;
        //使用二分查找法定位在一个区间里的整数（结合抽屉原理）（O(nlogn)）：


        //快慢指针，循环检测
//        def findDuplicate(self, nums: List[int]) -> int:
//        # 把nums看成是顺序存储的链表，nums中每个元素的值是下一个链表节点的地址
//        # 那么如果nums有重复值，说明链表存在环，本问题就转化为了找链表中环的入口节点，因此可以用快慢指针解决
//
//        # 初始时，都指向链表第一个节点nums[0]
//        slow, fast = 0, 0
//        # 慢指针走一步，快指针走两步
//        slow, fast = nums[slow], nums[nums[fast]]
//        # 循环退出时，slow与fast相遇，相遇节点必在环中
//        while slow != fast:
//        slow, fast = nums[slow], nums[nums[fast]]
//        # 让before，after分别指向链表开始节点，相遇节点
//                before, after = 0, slow
//        # before与after相遇时，相遇点就是环的入口节点
//        while before != after:
//        before, after = nums[before], nums[after]
//
//        return before
    }
}

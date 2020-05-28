package leetcode;

import java.util.Arrays;

/**
 * @author zg
 * @create 2019-12-21 13:57
 *
 * 颜色分类[中等](荷兰三色旗问题解)
 *
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 *
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 * 注意:不能使用代码库中的排序函数来解决这道题。
 *
 * 示例:输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 *
 * 进阶：
 * 	一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 	首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 	你能想出一个仅使用常数空间的一趟扫描算法吗？
 *
 */
public class Test75 {

    //2趟扫描算法
    private static void sortColors2(int[] nums) {
        int zero_nums = 0;
        int one_nums = 0;
//        int two_nums = 0; //可以不用，因为剩下的数字都一定是2

        //迭代计算出0、1 和 2 元素的个数。
        for (int i = 0; i<nums.length;i++){
            if (nums[i]==0) zero_nums += 1;
            else if (nums[i]==1) one_nums +=1;
//            else two_nums +=1;
        }
        //按照0、1、2的排序，重写当前数组。
        for (int i = 0 ;i<nums.length;i++){
            if (i<zero_nums) nums[i]=0;
            else if (i<(zero_nums+one_nums)) nums[i] =1;
            else nums[i] = 2;
        }
    }

    //1趟扫描算法
    //类似于快速排序中的变种算法，三个指针记录位置
    private static void sortColors(int[] nums) {
        //末尾0的索引
        int zeroIndex = -1;
        //末尾1的索引
        int oneIndex = -1;
        //末尾2的索引
        int twoIndex = -1;//可以不需要，因为在下面一次遍历数组时，twoIndex一定等于i，指代当前元素
        for (int i = 0; i<nums.length;i++){
            if (nums[i]==0){
                oneIndex += 1;
                zeroIndex +=1;
                twoIndex +=1;
                //即0，1的末尾元素不重合，即有数字1;即1，2的末尾元素不重合，即有数字2
                if (zeroIndex != oneIndex && oneIndex!=twoIndex){
                    nums[zeroIndex] = 0;
                    nums[oneIndex] = 1;
                    nums[i] = 2;
                }else if (oneIndex!=twoIndex){  //有数字2，没有数字1
                    nums[zeroIndex] = 0;
                    nums[i] = 2;
                }else if (zeroIndex != oneIndex){ //有数字1，没有数字2
                    nums[zeroIndex] = 0;
                    nums[i] = 1;
                }
            }else if (nums[i]==1){
                oneIndex += 1;
                twoIndex += 1;
                //即1，2的末尾元素不重合，即有数字2
                if (oneIndex!=twoIndex){
                    nums[oneIndex] = 1;
                    nums[i] = 2;
                }
            }else twoIndex+=1;
        }

    }


    public static void main(String[] args) {
        int[] nums = {2,0,2,1,1,0};
        sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}

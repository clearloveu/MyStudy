package leetcode;

/**
 * @author zg
 * @create 2020-02-09 16:32
 */
public class Test283 {

    private void moveZeroes(int[] nums) {


        // //记录原数组中非0的位置
        // List<Integer> list = new ArrayList<>();
        // for(int i = 0;i<nums.length;i++) if(nums[i]!=0) list.add(i);
        // //根据非0元素的顺序，直接复制到nums的前面
        // int index = 0;
        // for(int i =0;i<list.size();i++){
        //     nums[index] = nums[list.get(i)];
        //     index +=1;
        // }
        // //补0
        // for(int i = index;i< nums.length;i++) nums[i] = 0;



        //双指针:相当于领扣的方法二
        int index = 0;
        //非0元素的索引
        int rightIndex =0;
        while(rightIndex!=nums.length){
            if(nums[rightIndex]!=0){
                nums[index] = nums[rightIndex];
                index +=1;
            }
            rightIndex +=1;
        }
        while(index!=nums.length){
            nums[index] = 0;
            index +=1;
        }
    }
}

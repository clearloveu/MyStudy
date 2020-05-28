package leetcode.jianZhiOffer;

/**
 * @author zg
 * @create 2020-02-23 16:12
 *
 * 旋转数组的最小数字[简单]                 [未完成]
 *
 */
public class Question11 {
    //参考题解：https://leetcode-cn.com/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/solution/mian-shi-ti-11-xuan-zhuan-shu-zu-de-zui-xiao-shu-3/
    private int minArray(int[] numbers) {
        //二分法寻找
        int left = 0;
        int right = numbers.length-1;
        while(left<right){
            int mid = (left+right)/2;
            if(numbers[mid]>numbers[right]) left = mid+1;
            else if (numbers[mid] < numbers[right]) right = mid;
            else right--;
        }
        return numbers[left];
    }

}

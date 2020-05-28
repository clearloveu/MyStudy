package leetcode.jianZhiOffer;

/**
 * @author zg
 * @create 2020-03-12 22:30
 */
public class Question56_2 {
    // 剑指offer
    private int singleNumber(int[] nums) {
//        boolean flag = true;
//        int bit = 1;
//        int res = 0;
//        while(flag){
//            flag = false;
//            int count = 0;
//            for(int i=0;i<nums.length;i++){
//                if((nums[i]&bit)!=0) count++;
//                if(bit<=nums[i]) flag = true;          //错误解法，不能用bit<=nums[i]作为结束循环的标识，因为如果num[i]有32位那么大，则永远也跳不出循环
//            }
//            if(count%3!=0) res = res+ bit;
//
//            bit  = bit<<1;
//        }
//        return res;
        int res = 0;
        // int类型,最多32位
        for(int i=0;i<32;i++) {
            int count = 0;
            for (int temp : nums) {
                //如果此位不是0，则计数+1
                if (((1 << i) & temp) != 0) count += 1;
            }
            // 如果此位的数量不是3的倍数，则单独的那个数字此位必是1
            if (count % 3 != 0) res += (1 << i);
        }
        return res;

    }

    public static void main(String[] args) {
        System.out.println(new Question56_2().singleNumber(new int[]{3, 4, 3, 3}));
    }
}

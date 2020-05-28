package leetcode;

/**
 * @author zg
 * @create 2020-02-14 15:47
 *
 * 汉明距离[简单]
 *
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 *
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 *
 * 注意：
 * 0 ≤ x, y < 231.
 *
 * 示例:
 * 输入: x = 1, y = 4
 *
 * 输出: 2
 * 解释:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 *
 * 上面的箭头指出了对应二进制位不同的位置。
 *
 */
public class Test461 {
    private int hammingDistance(int x, int y) {
//        int[] x2 = new int[32];
//        int[] y2 = new int[32];
//        int index = -1;
//        while(x!=0){
//            index +=1;
//            if((x&1)==1) x2[index] =1;
//            x = x/2;
//        }
//        index = -1;
//        while(y!=0){
//            index +=1;
//            if((y&1)==1) y2[index] =1;
//            y = y/2;
//        }
//        int answer = 0;
//        for(int i =0;i<32;i++){
//            if(x2[i]!=y2[i]) answer+=1;
//        }
        // 空间优化
        int answer = 0;
        while(x!=0 || y!=0){
            if((y&1)!=(x&1)) answer+=1;
            y = y/2;
            x = x/2;
        }
        return answer;
//        //别人的异或做法
//        int i = x ^ y;
//        int count = 0;
//        while (i != 0) {
//            if ((i & 1) == 1) {
//                count++;
//            }
//            i = i >> 1;
//        }
//        return count;

    }

    public static void main(String[] args) {
        System.out.println(new Test461().hammingDistance(1,4));
    }
}

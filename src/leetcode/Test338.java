package leetcode;

/**
 * @author zg
 * @create 2020-02-08 16:15
 *
 * 比特位计数[中等]
 *
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 *
 * 示例 1:
 * 输入: 2
 * 输出: [0,1,1]
 *
 * 示例 2:
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 *
 * 进阶:
 * 	给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 	要求算法的空间复杂度为O(n)。
 * 	你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 *
 */
public class Test338 {

    private int[] countBits(int num) {
        // //暴力解法
        // int[] answer = new int[num+1];
        // for(int i=0;i<=num;i++){
        //     answer[i] = canculate(i);
        // }
        // return answer;


        //数学：根据二进制的规律：
        // 0；1；1，2；1，2，2，3；
        // 规律是[2^n,2^(n+1)) = [0,2^n)对应位+1，n=0，1，2，3...
        int[] answer = new int[num+1];
        answer[0] = 0;
        int n=0;
        boolean flag = true;
        while(flag){
            for(int i = 1<<n ;i<1<<(n+1);i++){
                if(i>num){
                    flag = false;
                    break;
                }
                answer[i] = answer[i-(1<<n)]+1;
            }
            n = n +1;
        }
        return answer;

        //领扣解法：
        //对于所有的数字，只有两类：
        //
        //奇数：二进制表示中，奇数一定比前面那个偶数多一个 1，因为多的就是最低位的 1。
        //          举例：
        //         0 = 0       1 = 1
        //         2 = 10      3 = 11
        //偶数：二进制表示中，偶数中 1 的个数一定和除以 2 之后的那个数一样多。因为最低位是 0，除以 2 就是右移一位，也就是把那个 0 抹掉而已，所以 1 的个数是不变的。
        //           举例：
        //          2 = 10       4 = 100       8 = 1000
        //          3 = 11       6 = 110       12 = 1100
        //另外，0 的 1 个数为 0，于是就可以根据奇偶性开始遍历计算了。
//        int[] ans = new int[num + 1];
//        for (int i = 1; i <= num; ++i)
//            ans[i] = ans[i >> 1] + (i & 1); // x / 2 is x >> 1 and x % 2 is x & 1
//        return ans;

    }



    // public int canculate(int i){
    //     int answer = 0;
    //     while(i!=0){
    //         if((i%2)==1) answer+=1;
    //         i = i/2;
    //     }
    //     return answer;
    // }

    public static void main(String[] args) {
        new Test338().countBits(4);
    }

}

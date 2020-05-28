package leetcode.jianZhiOffer;

/**
 * @author zg
 * @create 2020-02-23 14:18
 *
 * 青蛙跳台阶问题[简单]
 *
 */
public class Question10_2 {
    private int numWays(int n) {
        //和斐波那契数列一样的递推式
        int first = 1;
        int second = 1;
        if(n==0) return 1;
        else{
            n = n-1;
            while(n!=0){
                int temp = second;
                //Java ： 存在越界问题，解决方法是每次计算 sum = a + b 时对 1000000007取余。
                //此操作与最终返回前取余等价（建议借助图解理解）。

                //为什么可以边算边取模和最后再取模结果一样
                //证明： 要证（a+b）%c = （a%c+b%c）%c
                //即证a+b与a%c+b%c对c同余 则有c能整除(a+b-a%c-b%c)
                //设a=mc+p b=nc+q 则(a+b-a%c-b%c)=(m+n)c+p+q-p-q=(m+n)c 则证a+b与a%c+b%c对c同余，证毕
                second = (first+second)%1000000007;
                first = temp;
                n -=1;
            }
            return second;
        }
    }
}

package leetcode.jianZhiOffer;

/**
 * @author zg
 * @create 2020-02-23 17:13
 *
 * 剪绳子2[中等]
 *
 */
public class Question14_2 {

    //动态规划不行，因为递推式：f(index) = max(f(i)Xf(index-i)),0<i<index，
    // 如果在a(f(a)=1000000008)处值取余，b(f(b)=2)处没有取余，表面上f(a)<f(b)，其实f(a)>f(b)，这对后面找max(f(i)Xf(index-i))时会产生影响

    //贪婪算法
    private int cuttingRope(int n) {
        if(n <= 3) return n - 1;
        int b = n % 3, p = 1000000007;
        long rem = 1, x = 3;
        //循环求余法
        for(int a=0;a<n/3-1;a++){
            rem = rem*3%p;
        }
        if(b == 0) return (int)(rem * 3 % p);
        if(b == 1) return (int)(rem * 4 % p);
        return (int)(rem * 6 % p);
    }
}

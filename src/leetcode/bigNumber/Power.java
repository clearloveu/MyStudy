package leetcode.bigNumber;

/**
 * @author zhaoguang
 * @create 2024-06-02 22:17
 */
public class Power {
    public static void main(String[] args) {
        System.out.println(power(100, 100, 7));
        System.out.println(fastPower(100, 100, 7));
        System.out.println(fastPower2(100, 100, 7));
    }


    // 循环幂余法
    public static long power(long base, long power, long mod) {
        if (mod == 0) return 0; // 防止除以0错误
        base = base % mod; // 避免数值过大
        long result = 1;
        for (int i = 0; i <power ; i++) {
            result = (result * base) % mod;
        }
        return result;
    }



    // 二分求余算法(快速冪余法)
    public static long fastPower(long base, long power, long mod) {
        if (mod == 0) return 0; // 防止除以0错误
        long result = 1;
        base = base % mod; // 避免数值过大
        for (long i = power; i > 0; i /= 2) {
            if (i==1) {
                result = (result * base) % mod;
                break;
            }
            base = (base * base) % mod;
        }
        return  result;
    }
    public static long fastPower2(long base, long power, long mod) {
        if (mod == 0) return 0; // 防止除以0错误
        long result = 1;
        base = base % mod; // 避免数值过大
        while (power > 0) {
            if ((power & 1) == 1) {
                result = (result * base) % mod;
            }
            power >>= 1; // 右移一位相当于除以2
            base = (base * base) % mod;
        }
        return result;
    }
}

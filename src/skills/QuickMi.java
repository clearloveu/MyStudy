package skills;

/**
 * @author zg
 * @create 2020-04-09 2:43
 *
 * 快速幂:
 *
 */
public class QuickMi {
    private static long quickMi(long aa, long bb){
        long res = 1;
        long a = aa, b = bb;
        a %= 1000000007;
        for (; b != 0; b /=2) {
            if(b % 2 == 1){
                res = (res * a) % 1000000007;
            }
            a = (a * a) % 1000000007;
        }
        return res;
    }
    // O(log n)复杂度
    public double myPow(double x, int n) {
        if(n==0) return 1.0;
        double res = 1.0;
        // 这里不能使用int，因为n如果等于Integer.MIN_VALUE的话，-n还是Integer.MIN_VALUE,这里需要注意，要用long
//        int m = Math.abs(n);
        // 这样使用long是错误的，因为-n仍然有问题
//        long m = n;
//        if (n<0) m = -n;
        long m = n;
        if (n<0) m = -m;

        while (m!=1){
            if (m%2==1) res*=x;
            x *=x;
            m /=2;
        }
        return n>0?res*x:1/(res*x);

    }
    // O(log n)复杂度
    public static  double myPow2(double x, int n) {
        if(n==0) return 1.0;
        double res = 1.0;
        // 这里不能使用int，因为n如果等于Integer.MIN_VALUE的话，-n还是Integer.MIN_VALUE,这里需要注意，要用long
//        int m = Math.abs(n);
        // 这样使用long是错误的，因为-n仍然有问题
//        long m = n;
//        if (n<0) m = -n;

        while (n!=1){
            if (n%2==1) res*=x;
            x *=x;
            n /=2;
        }
        return res*x;

    }

    public static void main(String[] args) {
        System.out.println(myPow2(2,10));
    }
}

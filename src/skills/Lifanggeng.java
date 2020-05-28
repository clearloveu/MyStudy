package skills;

/**
 * @author zg
 * @create 2020-04-10 14:36
 *
 * 立方根
 *
 * 计算一个数字的立方根，不使用库函数
 * 详细描述：
 * •接口说明
 * 原型：
 * public static double getCubeRoot(double input)
 * 输入:double 待求解参数
 * 返回值:double  输入参数的立方根，保留一位小数
 *
 */
public class Lifanggeng {
    // 使用二分查找算法
    public static double getCubeRoot(double input)
    {

        //负数的情况
        if(input<0)
            return -1 * getCubeRoot(-input);
        if(input == 0 || input==1)   return input;
        //分y大于1和y小于1两种情况，只是区间改变，算法一致
        double min = input>1?1:0;
        double max = input>1?input:1; //为什么要分大于1和小于1呢：因为如果是小于1，则它的立方根会比input本身大，故初始范围应该设为[0,1]

//        double min = 0;
//        double max = input;
        double mid = 0;
        // 注意，这里的精度要提高一点，否则某些测试用例无法通过
        while ((max - min) > 0.001)
        {
            mid = (max + min) / 2;
            if (mid * mid * mid > input)
                max = mid;
            else if (mid * mid * mid < input)
                min = mid;
            else
                return mid;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(getCubeRoot(0.008));
    }

//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        while (sc.hasNext())
//        {
//            double input = sc.nextDouble();
//            double result = getCubeRoot(input);
//            System.out.printf("%.1f\n", result);
//        }
//        sc.close();
//    }
}

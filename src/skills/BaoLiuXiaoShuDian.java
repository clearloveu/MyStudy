package skills;

import java.text.DecimalFormat;

/**
 * @author zg
 * @create 2020-04-07 16:27
 *
 * java保留小数点后两位
 *
 */
public class BaoLiuXiaoShuDian {
    public static void main(String[] args) {
        double x1=1.126;
        System.out.println(String.format("%.2f",x1));
        DecimalFormat df = new DecimalFormat(".00");
        System.out.println(df.format(x1));
    }
}


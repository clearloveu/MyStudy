package niuke;

/**
 * @author zg
 * @create 2020-03-19 19:33
 *
 * 快手一面4.22
 *
 * 第一题，反转单向链表（略）
 * 第二题，实现2个大数之间的相乘的函数，输入2个String类型，输出String类型
 *
 */
public class KuaishouYimian1 {

    public static void main(String[] args) {
        String s1 = "120121314414241151515153351";
        String s2 = "1001234124124151553155353153";
        String res = mul(s1,s2);
        System.out.println(res);
    }

    private static String mul(String s1,String s2){
        // 存储乘数的每一位置上的数乘以被乘数的结果，用于最后的相加(这里的结果存放的顺序是从低位到高位)
        String[] temp = new String[s2.length()];

        // 模拟相乘，s2是乘数，s1是被乘数
        for (int i = s2.length()-1; i >=0; i--) {
            // s2的每个位置上的数，第一个是个位上的数
            int t = Integer.parseInt(String.valueOf(s2.charAt(i)-'0'));
            int jinwei = 0;
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = s1.length()-1; j >=0; j--) {
                int currentNum = Integer.parseInt(String.valueOf(s1.charAt(j)-'0'));
                int res = currentNum*t+jinwei;
                stringBuilder.append(res%10);
                jinwei = res/10;
            }
            if (jinwei!=0){
                stringBuilder.append(jinwei);
            }
//            stringBuilder.reverse();
            // 尾部填充 0,为了保证十位，百位乘以被乘数之后的结果和个位数的位数相同，即都从0开始
            for (int j = s2.length()-1; j > i; j--) {
                stringBuilder.insert(0,0);
            }
            temp[i] = stringBuilder.toString();
        }
        // 模拟把相乘的结果相加
        StringBuilder stringBuilder = new StringBuilder();
        int  index = -1;
        boolean flag = true;
        int jinwei = 0;
        while (flag){
            flag = false;
            index++;
            int res = 0;
            for (int i = temp.length-1; i >=0; i--) {
                if (index<temp[i].length()){
                    int t = temp[i].charAt(index)-'0';
                    res+=t;
                    flag = true;
                }
            }
            res = res+jinwei;
            if (flag) stringBuilder.append(res%10);
            jinwei = res/10;
        }
        return stringBuilder.reverse().toString();
    }
}

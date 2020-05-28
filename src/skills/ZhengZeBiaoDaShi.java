package skills;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author zg
 * @create 2020-03-19 19:33
 *
 *
 */
public class ZhengZeBiaoDaShi {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // read read[addr=0x17,mask=0x17,val=0x7],read_his[addr=0xff,mask=0xff,val=0x1],read[addr=0xf0,mask=0xff,val=0x80]
        // read是匹配关键字key，后面是要匹配的字符串
        // 匹配原则：  key[addr=(16进制数),mask=(16进制数),val=(16进制数)]
        // [之前的字符要和read相同，后面代表16进制可大可小，即可以是0x或0X，后面能匹配16进制数，1-3个数字，
        String string = in.nextLine();
        String[] strings1 = string.trim().split(" ");

        String key = strings1[0];
        String pattern = key+"\\[addr=(0[xX][0-9A-Fa-f]{1,3}),mask=(0[xX][0-9A-Fa-f]{1,3}),val=(0[xX][0-9A-Fa-f]{1,3})]";
        System.out.println(pattern);
        Pattern r = Pattern.compile(pattern);
        System.out.println(r);

        Matcher m = r.matcher(strings1[1]);

        while (m.find()){
            //要求输出 0x17 0Xff 0x7
            String t = m.group(); // 输出的是read[addr=0x17,mask=0x17,val=0x7]，下面是把t改成要求输出的格式
            t = t.substring(key.length()+1,t.length()-1);
            String[] tt = t.split(",");
            for (int i = 0; i < tt.length; i++) {
                if (i!=2) System.out.print(tt[i].substring(5));
                else System.out.print(tt[i].substring(4));
                System.out.print(" ");
            }
            System.out.print("\r\n");
        }
    }
}

package interview;

/**
 * @author zg
 * @create 2020-03-03 15:23
 *
 * 解析Integer的缓存机制
 *
 */
public class CompareInteger {


        public static void main(String[] args) {

            Integer num1 = new Integer(100);
            Integer num2 = new Integer(100);
            // java中的==是用于判断两个操作数是否相等的，如果操作数是基本数据类型，
            // 则判断值是否相等；如果操作数是对象，则判断两个对象的地址是否相等（也就是引用是否相等）
            System.out.println("num1==num2 " + (num1 == num2));       //false

            Integer num3 = 100;
            Integer num4 = 100;
            // 这里便涉及到了Integer的缓存机制问题， Integer是对小数据（-128~127）是有缓存的，再jvm初始化的时候，
            // 数据-128~127之间的数字便被缓存到了本地内存中，这样，如果初始化-128~127之间的数字，便会直接从内存中取出，而不需要再新建一个对象
            System.out.println("num3==num4 " + (num3 == num4));       //true

            Integer num5 = 128;
            Integer num6 = 128;
            System.out.println("num5==num6 " + (num5 == num6));        //false


            Integer num7 = 100;
            Integer num8 = new Integer(100);
            // 比较地址
            System.out.println("num7==num8 " + (num7 == num8));        //false

            int num9 = 100;
            Integer num10 = new Integer(100);
            Integer num11 = 100;
            //Integer是int的包装类，在和int做比较的时候，会自动拆箱成int数值类型，所以，这里便变成了数字（int）的比较，所以100肯定等于100啦
            System.out.println("num9==num10 " + (num9 == num10));   //true
            System.out.println("num9==num11 " + (num9 == num11));   //true

        }
}

package leetcode.jianZhiOffer;

/**
 * @author zg
 * @create 2020-03-16 9:34
 *
 * 把字符串转换成整数[中等]
 *
 */
public class Question67 {
    private int strToInt(String str) {
        boolean plusOrFu = true;//代表正负数，true为正数
        int currentValue = 0;
        str = str.trim();
        for(int i = 0;i<str.length();i++){
            char tempChar = str.charAt(i);
            if(i==0&&(tempChar=='-'||tempChar=='+')){
                if(tempChar=='-') plusOrFu = false;
                continue;
            }
            if((tempChar-'0'<0||tempChar-'0'>9)) break;
            //处理数字部分，考虑大数问题
            int value = (tempChar-'0');
            if(plusOrFu){
                if(currentValue>(Integer.MAX_VALUE/10)||(currentValue==(Integer.MAX_VALUE/10)&&value>Integer.MAX_VALUE%10))
                    return Integer.MAX_VALUE;
            }else{
                if((0-currentValue)<(Integer.MIN_VALUE/10)||((0-currentValue)==(Integer.MIN_VALUE/10)&&(0-value)<Integer.MIN_VALUE%10))
                    return Integer.MIN_VALUE;
            }
            currentValue = currentValue*10+value;
        }
        return plusOrFu? currentValue:(0-currentValue);
    }

    public static void main(String[] args) {
        System.out.println(new Question67().strToInt("-2147483647"));
    }
}

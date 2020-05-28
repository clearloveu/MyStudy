package leetcode.jianZhiOffer;

/**
 * @author zg
 * @create 2020-03-12 11:26
 *
 * 数字序列中某一位的数字[中等]
 *
 */
public class Question44 {

    // 有bug，未修复
    private int findNthDigit(int n) {
        if (n==0) return 0;

        // 先确定第n位对应的数字是几位数
        int index = 1; //1位数
        int startIndex = 1;
        int endIndex = 9;
        while(n>endIndex){
            index +=1;
            startIndex = endIndex+1;
            endIndex = 9*((int)Math.pow(10,index-1))*index+ endIndex;
        }

        //确定了几位数之后，确定第n位对应第几个数
        int numIndex = (int) Math.ceil((n - startIndex+1)/(double)index);
        int num = (int)Math.pow(10,index-1)+numIndex-1;
        System.out.println(num);
        // 对应该数的第几个数字
        int numYushu = (n - startIndex+1)%index;
        System.out.println(numYushu);

        String temp = String.valueOf(num);
        char temp2 = temp.charAt(temp.length()-1-numYushu);

        return temp2 - '0';

    }

    public static void main(String[] args) {
        System.out.println(new Question44().findNthDigit(1000));
    }


}

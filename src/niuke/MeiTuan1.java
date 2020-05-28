package niuke;

/**
 * @author zg
 * @create 2020-03-19 14:33
 *
 * 以字符串的形式读入两个数字，再以字符串的形式输出两个数字的和。
 * 输入描述:
 * 输入两行，表示两个数字a和b，-109 <= a , b <= 109  ，用双引号括起。
 *
 * 输出描述:
 * 输出a+b的值，用双引号括起。
 *
 * 输入例子1:
 * "-26"
 * "100"
 *
 * 输出例子1:
 * "74"
 *
 */

import java.math.BigInteger;
import java.util.Scanner;

public class MeiTuan1{
    public static void main(String[] args){
        //使用BigInteger类
        Scanner input=new Scanner(System.in);
        String num1 = input.nextLine();
        num1=num1.substring(1,num1.length()-1);
        String num2 = input.nextLine();
        num2=num2.substring(1,num2.length()-1);
        BigInteger add=new BigInteger(num1).add(new BigInteger(num2));
        System.out.println("\""+add+"\"");
    }
}


//        Scanner in = new Scanner(System.in);
//        StringBuilder a = new StringBuilder(in.nextLine());
//        StringBuilder b = new StringBuilder(in.nextLine());
//        boolean flagA = true;
//        boolean flagB = true;
//        if(a.charAt(0)=='+') {
//            a.deleteCharAt(0);
//        } else if(a.charAt(0)=='-') {
//            a.deleteCharAt(0);
//            flagA = false;
//        }
//        if(b.charAt(0)=='+') b.deleteCharAt(0);
//        else if(b.charAt(0)=='-') {
//            b.deleteCharAt(0);
//            flagA = false;
//        }
//        a.reverse();
//        b.reverse();
//        StringBuilder sb = new StringBuilder();
//        if(flagA == flagB){
//            int jinOne = 0;
//            int index = 0;
//            while(index<a.length()&&index<b.length()){
//                int tempA = a.charAt(index)-'0';
//                int tempB = a.charAt(index)-'0';
//                if(tempA+tempB+jinOne>9) {
//
//                    sb.append(tempA+tempB+jinOne-10);
//                    jinOne = 1;
//                }else {
//
//                    sb.append(tempA+tempB+jinOne);
//                    jinOne = 0;
//                }
//                index +=1;
//            }
//            while(index<a.length()){
//                int tempA = a.charAt(index)-'0';
//                if(tempA+jinOne>9) {
//                    sb.append(tempA+jinOne-10);
//                    jinOne = 1;
//                }else {
//
//                    sb.append(tempA+jinOne);
//                    jinOne = 0;
//                }
//                index +=1;
//            }
//            while(index<b.length()){
//                int tempB = b.charAt(index)-'0';
//                if(tempB+jinOne>9) {
//                    sb.append(tempB+jinOne-10);
//                    jinOne = 1;
//                }else {
//
//                    sb.append(tempB+jinOne);
//                    jinOne = 0;
//                }
//                index +=1;
//            }
//            if(jinOne==1) sb.append(1);
//            sb.reverse();
//            if(!flagA)sb.insert(0,"-");
//            System.out.println(sb.toString()) ;
//        }else{
//            System.out.println("sb.toString()") ;
//        }



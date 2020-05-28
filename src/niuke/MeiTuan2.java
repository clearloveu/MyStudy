package niuke;

import java.util.*;

/**
 * @author zg
 * @create 2020-03-19 15:26
 *
 * 给定一组个字符串，为每个字符串找出能够唯一识别该字符串的最小前缀。
 *
 * 输入描述:
 * 第一行输入一个整数 n 表示字符串个数
 * 后面n行，每行一个字符串，一共n串互不相同的字符串。（2 <= n <= 100，字符串长度不超过100）
 *
 * 输出描述:
 * 输出n行，每行一个字符串，依次是每个字符串的最小可唯一识别前缀
 *
 * 输入例子1:
 * 5
 * meituanapp
 * meituanwaimai
 * dianpingliren
 * dianpingjiehun
 * mt
 *
 * 输出例子1:
 * meituana
 * meituanw
 * dianpingl
 * dianpingj
 * mt
 *
 */
public class MeiTuan2 {
    static TriedTree root;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        while (in.hasNext()){
            String s = in.nextLine();
            if (s.equals("end"))
                break;
            list.add(s);

        }
        in.close();
        root = new TriedTree(1);



//        list.add("meituanapp");
//        list.add("meituanwaimai");
//        list.add("dianpingliren");
//        list.add("dianpingjiehun");
//        list.add("mt");


        init(list);
        search(list);
    }

    public static class TriedTree{
        int val;
        public Map<Character, TriedTree> next;
        TriedTree(int val){
            this.val = val;
            next = new HashMap<Character, TriedTree>();
        }
//        TriedTree[] triedTree= new TriedTree[26];

    }
    private static void init(List<String> list){
        for(int i=0;i<list.size();i++){
            String currentString = list.get(i);
            TriedTree head = root;
            for(int j=0;j<currentString.length();j++){
//                int index = currentString.charAt(j)-'a';
//                if(head.triedTree[index]==null){
//                    head.triedTree[index] = new TriedTree(1);
//                }else {
//                    head.triedTree[index].val +=1;
//                }
//                head =  head.triedTree[index];
                char index = currentString.charAt(j);
                if (head.next.get(index)==null){
                    head.next.put(index,new TriedTree(1));
                }else {
                    head.next.get(index).val+=1;
                }

                head = head.next.get(index);
            }
        }


    }
    private static void search(List<String> list){
        for(int i=0;i<list.size();i++){
            String currentString = list.get(i);
            TriedTree head = root;
            StringBuilder sb = new StringBuilder();
            for(int j=0;j<currentString.length();j++){
                sb.append(currentString.charAt(j));
                if(j==currentString.length()-1) {
                    System.out.println(sb.toString());
                    break;
                }
//                int index = currentString.charAt(j)-'a';
//                if(head.triedTree[index].val==1){
//                    System.out.println(sb.toString());
//                    break;
//                }else {
//                    head = head.triedTree[index];
//                }
                char index = currentString.charAt(j);
                if (head.next.get(index).val==1){
                    System.out.println(sb.toString());
                    break;
                }else head = head.next.get(index);
            }
        }
    }


}

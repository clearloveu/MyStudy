package leetcode.jianZhiOffer;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author zg
 * @create 2020-03-09 21:32
 *
 * 字符串的排列[中等]
 *
 */
public class Question38 {
    String[] answers ;
    int index = 0;
    private String[] permutation(String s) {
        //申请到足够的空间，空间是 n!
        answers = new String[comculate(s.length())];
        List<Character> list = new LinkedList<>();
        //转换成链表
        for(int i = 0;i<s.length();i++) list.add(s.charAt(i));
        //递归+回溯
        resursion(list,new StringBuilder());

        //hash去重
        Set<String> set = new HashSet<>();
        for(int i=0;i<answers.length;i++){
            set.add(answers[i]);
        }
        String[] res = new String[set.size()];
        index = 0;
        for(String temp : set){
            res[index] = temp;
            index +=1;
        }
        return res;
    }

    private void resursion(List<Character> list, StringBuilder answer){
        //递归基
        if(list.size()==0){
            answers[index]= (new StringBuilder(answer).toString());
            index +=1;
        }

        //递归体
        for(int i =0;i<list.size();i++){
            Character temp = list.get(i);
            answer.append(temp);
            list.remove(i);
            resursion(list,answer);
            list.add(i,temp);
            answer.deleteCharAt(answer.length()-1);
        }


    }

    private int comculate(int length){
        if(length==1) return 1;
        return length*comculate(length-1);
    }


    //别人的代码，使用一个visit数组去标记已经遍历过的数字，这里代替了我new了一个链表并且一直删除，增加节点的操作
    Set<String> result = new HashSet<>();
    private String[] permutation2 (String s){
        if (s == null) return new String[]{};
        boolean[] visited = new boolean[s.length()];
        process(s, "", visited);
        return result.toArray(new String[result.size()]);
    }


    private void process(String s, String letter, boolean[] visted){
        if(s.length() == letter.length()){
            result.add(letter);
            return;
        }
        for(int i = 0; i < s.length(); i++){
            char temp = s.charAt(i);
            if(visted[i]) continue;
            visted[i] = true;
            process(s, letter + String.valueOf(temp), visted);
            visted[i] = false;
        }
    }

}

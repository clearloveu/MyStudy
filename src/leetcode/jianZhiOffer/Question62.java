package leetcode.jianZhiOffer;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zg
 * @create 2020-03-14 17:01
 *
 * 圆圈中最后剩下的数字[简单]
 *
 */
public class Question62 {

    //循环链表
    private int lastRemaining(int n, int m) {
        class ListNode {
            int val;
            ListNode next ;
            public ListNode(int val){
                this.val = val;
            }
        }
        if(n==0) return 0;
        if(m==1) return n-1;
        ListNode head = new ListNode(0);
        ListNode temp = head;
        for(int i = 1;i<n;i++){
            temp.next = new ListNode(i);
            temp = temp.next;
        }
        temp.next = head;
        temp = head;
        while(temp.next!=temp){
            int tempM = m;
            while(tempM!=2) {
                temp = temp.next;
                tempM--;
            }
            temp.next = temp.next.next;
            temp = temp.next;
        }
        return temp.val;
    }

    //用List
    private int lastRemaining2(int n, int m) {
        List<Integer> list = new LinkedList<>();
        for (int i=0;i<n;i++) list.add(i);
        int index = 0;
        while (list.size()!=1){
            index = (index+m-1)%list.size();
            list.remove(index);
            if (index==list.size()) index = 0;
        }
        return  list.get(0);
    }


    //公式
    private int lastRemaining3(int n, int m) {
        if(n<1||m<1) return -1;
        int last = 0;
        for(int i=2;i<=n;i++){
            last = (last+m)%i;
        }
        return  last;
    }








}

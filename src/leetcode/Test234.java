package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zg
 * @create 2020-02-04 13:46
 *
 * 回文链表[简单]
 *
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 * 输入: 1->2
 * 输出: false
 *
 * 示例 2:
 * 输入: 1->2->2->1
 * 输出: true
 *
 *
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 *
 */
public class Test234 {
    private boolean isPalindrome(ListNode head) {
        //空链表
        if(head==null) return true;

        //链表转数组
        List list = new ArrayList<Integer>();
        while(head!=null){
            list.add(head.val);
            head = head.next;
        }
        int length = list.size();
        //偶数
        if(length%2==0){
            return isPalindromeList(list,length/2-1,length/2);
        }else{//奇数
            return isPalindromeList(list,length/2,length/2);
        }
    }

    private boolean isPalindromeList(List list, int leftIndex, int rightIndex){
        while(list.get(leftIndex).equals(list.get(rightIndex))){
            leftIndex -= 1;
            rightIndex += 1;
            if(leftIndex==-1) break;
        }
        return leftIndex == -1;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(-129);
        ListNode listNode2 = new ListNode(-129);
        listNode1.next = listNode2;
        boolean flag = new Test234().isPalindrome(listNode1);

        System.out.println(flag);

    }

     private static  class ListNode {
       int val;
       ListNode next;
       ListNode(int x) { val = x; }
     }
}

package leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author zhaoguang
 * @create 2024-05-31 00:37
 *
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 */
public class Test25 {
    public static ListNode reverseKGroup(ListNode head, int k) {
        if(k == 1 || head == null || head.next == null) return head;
        int tempK = k;
        ListNode left = head;
        ListNode mid = head.next;
        ListNode right = head.next.next;
        while(tempK > 1) {
            tempK--;
            mid.next = left;
            left = mid;
            if(right == null && tempK > 1) return reverseKGroup2(left);
            else if ( right == null && tempK <= 1) {
                head.next = null;
                return mid;
            }
            else {
                mid = right;
                right = right.next;
            }
        }
        head.next = reverseKGroup(mid, k);
        return left;
    }

    public static ListNode reverseKGroup2(ListNode head) {
        Set<ListNode>  set = new HashSet<>();
        if(head == null || head.next == null) return head;
        ListNode left = head;
        ListNode mid = head.next;
        ListNode right = head.next.next;
        set.add(left);
        head.next = null;
        while(true) {
            set.add(mid);
            mid.next = left;
            left = mid;
            if(set.contains(right)) {
                return mid;
            } else{
                mid = right;
                right.next = right;
            }
        }
    }

    public static void main(String[] args) {
        //[1,2,3,4,5]
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
//        ListNode listNode3 = new ListNode(3);
//        ListNode listNode4 = new ListNode(4);
//        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
//        listNode2.next = listNode3;
//        listNode3.next = listNode4;
//        listNode4.next = listNode5;
        System.out.println(reverseKGroup(listNode1, 2));
    }

    private static class  ListNode{
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}

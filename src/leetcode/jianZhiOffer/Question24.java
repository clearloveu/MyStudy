package leetcode.jianZhiOffer;

import leetcode.utils.ListNode;

/**
 * @author zg
 * @create 2020-04-06 17:59
 *
 * 反转链表[简单]
 *
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 *
 * 示例:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 *
 */
public class Question24 {
    private ListNode reverseList(ListNode head) {
        if (head==null) return null;
        ListNode left = head;
        ListNode right = head.next;
        head.next = null;
        while (right!=null){
            ListNode temp = right.next;
            right.next = left;
            left = right;
            right = temp;
        }
        return left;
    }
}

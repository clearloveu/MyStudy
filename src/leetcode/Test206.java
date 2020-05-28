package leetcode;

/**
 * @author zg
 * @create 2020-01-10 15:20
 *
 * 反转链表[简单]
 *
 * 反转一个单链表。
 *
 * 示例:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 *
 */
public class Test206 {
    //迭代：重点是反转需要3个指针
    private static ListNode reverseList(ListNode head) {
        if(head ==null || head.next ==null) return head;
        ListNode left = head;
        ListNode right = head.next;
        head.next = null;
        while(right!=null){
            ListNode temp = right.next;
            right.next = left;
            left = right;
            right = temp;
        }
        return left;
    }
    //递归：有点难理解
//    public ListNode reverseList(ListNode head) {
//        if (head == null || head.next == null) return head;
//        ListNode p = reverseList(head.next);
//        head.next.next = head;
//        head.next = null;
//        return p;
//    }


    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        ListNode answer = reverseList(listNode1);
        while (answer!=null){
            System.out.print(answer.val+" ");
            answer = answer.next;
        }
    }

    private static class ListNode {
       int val;
       ListNode next;
       ListNode(int x) { val = x; }
    }
}

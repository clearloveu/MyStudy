package leetcode;

/**
 * @author zg
 * @create 2019-12-19 9:44
 *
 * 删除链表的倒数第N个节点[中等]
 *
 *  给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 *  示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 *
 * 说明：给定的 n 保证是有效的。
 *
 * 进阶：你能尝试使用一趟扫描实现吗？
 *
 */
public class Test19 {
    private static ListNode removeNthFromEnd(ListNode head, int n) {

        //双指针思想
        ListNode left = head;
        ListNode right = head;
        //先让right指针先走，使left和right之间差n步
        int step = n;
        while (step!=1){
            right = right.next;
            step-=1;
        }
        //先让right先走一步，这样left是离right的第n个节点的前一个节点
        if (right.next!=null){
            right = right.next;
        }else {
            //如果right不能往前走一步，说明倒数第n个节点是首节点，直接返回第2个节点
            return head.next;
        }
        //right到最后一个节点，这样left是倒数第n个节点的前一个节点
        while (right.next!=null){
            left = left.next ;
            right = right.next;
        }
        //删除left.next，此时left.next就是倒数第n个节点
        left.next=left.next.next;
        return head;
    }


    //领扣的双指针思想和哑结点思想
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
        int n = 2;
        ListNode listNode = removeNthFromEnd(listNode1,n);
        System.out.print(listNode.val);
        while (listNode.next!=null){
            System.out.print("->"+listNode.next.val);
            listNode = listNode.next;
        }

    }


    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

}

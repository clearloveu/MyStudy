package leetcode;

/**
 * @author zg
 * @create 2020-01-07 11:02
 *
 * 排序链表[中等]
 *
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 *
 * 示例 1:
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 *
 *
 * 示例 2:
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 *
 *
 */
public class Test148_2 {
    //思路：利用快排思想
    //题解都是归并排序思想

    private static ListNode sortList(ListNode head) {
        //递归基
        if(head==null || head.next==null) return head;

        ListNode slow = head;
        //快指针一定要指向head.next，否则最后2个节点时永远死循环
        ListNode fast = head.next;
        //快慢指针寻找链路中点
        while (fast!=null&&fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode head2 = slow.next;
        //中断链路，否则则仍然是一条链路
        slow.next = null;
        ListNode newHead = sortList(head);
        ListNode newHead2 = sortList(head2);

        return merge(newHead,newHead2);
    }

    private static ListNode merge(ListNode head,ListNode head2){
        //哑结点
        ListNode h = new ListNode(0);
        ListNode index = h;
        while (head!=null&&head2!=null){
            if(head.val<head2.val){
                index.next = head;
                index = index.next;
                head = head.next;
            }
            else {
                index.next = head2;
                head2 = head2.next;
                index = index.next;
            }
        }
        if (head!=null) index.next = head;
        if (head2!=null) index.next = head2;
        return h.next;
    }


    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(4);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(1);
        ListNode listNode4 = new ListNode(3);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        ListNode answer = sortList(listNode1);
        while (answer!=null){
            System.out.println(answer.val+" ");
            answer = answer.next;
        }
    }

    private static class ListNode {
       int val;
       ListNode next;
       ListNode(int x) { val = x; }
    }

}

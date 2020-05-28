package leetcode;

/**
 * @author zg
 * @create 2020-01-02 9:33
 *
 * 环形链表II [中等]
 *
 *
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 *
 * 说明：不允许修改给定的链表。
 *
 */
public class Test142 {
    private static ListNode detectCycle(ListNode head) {
        if (head==null||head.next==null||head.next.next==null) return null;
        //双指针解决有没有环，参考Test141
        ListNode left = head;
        ListNode right = head;
        left = left.next;
        right = right.next.next;
        while (left!=right){
            if (right.next==null||right.next.next==null) return null;
            left = left.next;
            right = right.next.next;
        }
        //已经找到环了，且在环的某一节点相遇
        //下面计算环有几个节点
        int step = 1;
        left = left.next;
        right = right.next.next;
        while (left!=right){
            step +=1;
            left = left.next;
            right = right.next.next;
        }
        //环中有step个节点
        //使用双指针计算环的入口节点，双指针之间的距离是step，且双指针的步长均为1
        left = head;
        right = head;
        for (int i = 0;i<step;i++){
            right = right.next;
        }
        while (left!=right){
            left = left.next;
            right = right.next;
        }
        return left;
    }


    public static void main(String[] args) {
        ListNode listNode = new ListNode(3);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(0);
        ListNode listNode4 = new ListNode(-4);
        listNode.next = listNode2;
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode2;
        System.out.println(detectCycle(listNode).val);



    }


    private  static class ListNode {
       int val;
       ListNode next;
       ListNode(int x) {
           val = x;
           next = null;
       }
   }
}

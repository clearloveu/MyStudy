package leetcode;

import leetcode.utils.ListNode;

/**
 * @author zg
 * @create 2020-03-28 13:03
 *
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 *
 * 示例:
 *
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 */
public class Test23 {
    public static ListNode mergeKLists2(ListNode[] lists) {
        ListNode head = new ListNode(1);
        ListNode node1 = head;
        while(true) {
            int small = -1;
            for(int i = 0;i<lists.length;i++) {
                ListNode ii =  lists[i];
                if (ii == null) continue;
                if (small == -1) {
                    small = i;
                } else {
                    if(lists[small].val > ii.val) {
                        small = i;
                    }
                }
            }
            if (small == -1) break;
            head.next = lists[small];
            head = head.next;
            lists[small] = lists[small].next;
        }
        return node1.next;
    }
    private ListNode mergeKLists(ListNode[] lists) {
        // 哨兵节点
        ListNode head = new ListNode(0);
        ListNode currentNode = head;

        // 模拟归并
        int minIndex = -1;
        int minValue = Integer.MAX_VALUE;
        boolean flag = true;
        while (flag){
            flag = false;
            for (int i = 0; i < lists.length; i++) {
                ListNode temp = lists[i];
                if (temp!=null){
                    flag = true;
                    int tempValue = temp.val;
                    if (tempValue<minValue) {
                        minValue = tempValue;
                        minIndex = i;
                    }

                }

            }
            if (!flag) break;
            // 使用提供节点而不构造节点
            currentNode.next = lists[minIndex];
            currentNode = currentNode.next;
            // 当前最小节点到它的next节点
            lists[minIndex] = currentNode.next;
            currentNode.next = null;
            // 准备下次的循环
            minValue = Integer.MAX_VALUE;
        }



        return head.next;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(1);
        ListNode listNode3 = new ListNode(1);
        ListNode listNode4 = new ListNode(1);
        listNode1.next = listNode2;
        listNode3.next = listNode4;
        ListNode[] listNodes = new ListNode[2];
        listNodes[0] = listNode1;
        listNodes[1] = listNode3;
        new Test23().mergeKLists2(listNodes);
    }
}

package leetcode;

/**
 * @author zg
 * @create 2019-12-11 10:04
 *
 *  合并两个有序链表[简单]
 *
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 */
public class Test21 {

    //官方解答1：递归
    //public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    //        if (l1 == null) {
    //            return l2;
    //        }
    //        else if (l2 == null) {
    //            return l1;
    //        }
    //        else if (l1.val < l2.val) {
    //            l1.next = mergeTwoLists(l1.next, l2);
    //            return l1;
    //        }
    //        else {
    //            l2.next = mergeTwoLists(l1, l2.next);
    //            return l2;
    //        }
    //      }

    //官方解答2：迭代:我们可以用迭代的方法来实现上述算法。我们假设 l1 元素严格比 l2元素少，我们可以将 l2 中的元素逐一插入 l1 中正确的位置。



    //我的，代码太冗余
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        if (l1==null && l2==null) return null;

        ListNode listNode;

        if (l1 == null ) {
            listNode = new ListNode(l2.val);
            if (l2.next != null) {
                l2 = l2.next;
            }else l2 = null;
        }
        else if (l2 == null || l1.val<=l2.val){
            listNode = new ListNode(l1.val) ;
            if (l1.next != null) {
                l1 = l1.next;
            }else l1 = null;
        }else {
            listNode = new ListNode(l2.val) ;
            if (l2.next != null) {
                l2 = l2.next;
            }else l2 = null;
        }

        ListNode top = listNode;

        while(l1!= null || l2!=null){

            if (l1==null){
                listNode.next = new ListNode(l2.val) ;
                listNode = listNode.next;
                if (l2.next != null) {
                    l2 = l2.next;
                }else l2 = null;
                continue;
            }

            if (l2 == null || l1.val<=l2.val){
                listNode.next = new ListNode(l1.val) ;
                listNode = listNode.next;
                if (l1.next != null) {
                    l1 = l1.next;
                }else l1 = null;
            }else {
                listNode.next = new ListNode(l2.val) ;
                listNode = listNode.next;
                if (l2.next != null) {
                    l2 = l2.next;
                }else l2 = null;
            }
        }

        return top;

    }


    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(4);
        ListNode listNode4 = new ListNode(1);
        ListNode listNode5 = new ListNode(3);
        ListNode listNode6 = new ListNode(4);
        listNode1.next = listNode2;
        listNode2.next = listNode3;
        listNode4.next = listNode5;
        listNode5.next = listNode6;

        ListNode answer = mergeTwoLists(listNode1,listNode4);

        System.out.print(answer.val);
        while (answer.next!=null){
            System.out.print("->"+answer.next.val);
            answer = answer.next;
        }
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}

package leetcode;

/**
 * @author zg
 * @create 2019-12-06 18:30
 *
 * 两数相加[中等]
 *
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 *
 */
public class Test2 {

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {


        int flag= 0;//进位
        int var = l1.val+l2.val+flag;
        if (var>9){
            flag=1;
            var-=10;
        }
        ListNode listNode = new ListNode(var);
        ListNode listNode2 = listNode;

        //l1和l2共同部分的模拟数学加法
        while(l1.next!=null && l2.next!=null){
            var = l1.next.val+l2.next.val+flag;

            if (var>9){

                flag = 1;
                var-=10;
            }else
                flag = 0 ;

            l1 = l1.next;
            l2 = l2.next;
            listNode.next=new ListNode(var);
            listNode = listNode.next;
        }
        //如果l1有剩余部分，继续模拟数学加法
        while (l1.next!=null){
            var = l1.next.val+flag;
            if (var>9){
                flag = 1;
                var-=10;
            }else
                flag = 0 ;

            l1 = l1.next;
            listNode.next=new ListNode(var);
            listNode = listNode.next;
        }

        //如果l2有剩余部分，继续模拟数学加法
        while (l2.next!=null){
            var = l2.next.val+flag;
            if (var>9){
                flag = 1;
                var-=10;
            }else
                flag = 0 ;

            l2 = l2.next;
            listNode.next=new ListNode(var);
            listNode = listNode.next;
        }

        //最后可能会进1
        if (flag==1){
            listNode.next = new ListNode(flag);
        }


//        System.out.println(listNode2.val+" "+listNode2.next.val+" "+ listNode2.next.next.val);

        return listNode2;
    }


    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(4);
        ListNode listNode3 = new ListNode(3);
        ListNode listNode4 = new ListNode(5);
        ListNode listNode5 = new ListNode(6);
        ListNode listNode6 = new ListNode(4);
        listNode1.next=listNode2;
        listNode2.next=listNode3;
        listNode4.next=listNode5;
        listNode5.next=listNode6;

        ListNode answer =addTwoNumbers(listNode1,listNode4);
//        System.out.print(answer.val+" ");
//        while(answer.next!=null){
//            System.out.print(answer.next.val+" ");
//        }

    }

//    输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//    输出：7 -> 0 -> 8
//    原因：342 + 465 = 807

    //Definition for singly-linked list.
    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

}




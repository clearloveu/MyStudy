package leetcode;

/**
 * @author zg
 * @create 2020-01-10 16:12
 *
 * 相交链表[简单]
 *
 * 编写一个程序，找到两个单链表相交的起始节点。
 *
 * 注意：
 * 	如果两个链表没有交点，返回 null.
 * 	在返回结果后，两个链表仍须保持原有的结构。
 * 	可假定整个链表结构中没有循环。
 * 	程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 *
 *
 */
public class Test160 {
    //思路：寻求两个链路的特征
    // 设长的链路长度为n，短的链路长度为m，先让长的链表先走n-m步，再长的和短的一起走，看是否是同一个对象
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode A = headA,B = headB;
        int lengthA = 0 ;
        int lengthB = 0 ;
        while(A!=null){
            lengthA +=1;
            A = A.next;
        }
        while(B!=null){
            lengthB +=1;
            B = B.next;
        }
        ListNode runA = headA;
        ListNode runB = headB;
        if(lengthA>lengthB){
            while(lengthA>lengthB){
                lengthA -=1;
                runA = runA.next;
            }
        }else{
            while(lengthA<lengthB){
                lengthB -=1;
                runB = runB.next;
            }
        }
        while(runA!=null){
            if(runA==runB) return runB;
            runA = runA.next;
            runB = runB.next;
        }
        return null;
    }


    public static void main(String[] args) {
        //看领扣
    }

    private static class ListNode {
       int val;
       ListNode next;
       ListNode(int x) {
           val = x;
           next = null;
       }
    }

}

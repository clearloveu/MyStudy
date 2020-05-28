package leetcode.jianZhiOffer;

import leetcode.utils.ListNode;
import java.util.Stack;

/**
 * @author zg
 * @create 2020-02-22 10:44
 *
 * 从尾到头打印链表[简单]
 *
 */
public class Question6 {
    private int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        while(head!=null){
            stack.add(head.val);
            head = head.next ;
        }
        int[] res = new int[stack.size()];
        int index = 0;
        while(!stack.isEmpty()){
            res[index] = stack.pop();
            index +=1;
        }
        return res;
    }
}

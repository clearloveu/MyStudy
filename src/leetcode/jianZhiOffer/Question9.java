package leetcode.jianZhiOffer;

import java.util.Stack;

/**
 * @author zg
 * @create 2020-02-23 13:28
 *
 * 用两个栈实现队列[简单]
 *
 */
public class Question9 {
//    private Stack<Integer> stack1;
//    private Stack<Integer> stack2;
//    public Question9() {
//        stack1 = new Stack<>();
//        stack2 = new Stack<>();
//    }
//
//    public void appendTail(int value) {
//        while(!stack2.isEmpty()) stack1.add(stack2.pop());  //恢复原样
//        stack1.add(value);
//    }
//
//    public int deleteHead() {
//        if(stack1.isEmpty()) return -1;
//        else{
//            while(!stack1.isEmpty()) stack2.add(stack1.pop());//把栈中的数据倒过来
//            int res =  stack2.pop(); //输出1个数据
//            while(!stack2.isEmpty()) stack1.add(stack2.pop());  //恢复原样
//            return res;
//        }
//    }
    //优化，不需要每次删除后就把stack2倒入stack1中，当下次是插入时才执行，即把导入stack1的行为绑定到插入过程中
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;
    public Question9() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void appendTail(int value) {
        while(!stack2.isEmpty()) stack1.add(stack2.pop());  //恢复原样
        stack1.add(value);
    }

    public int deleteHead() {
        while(!stack1.isEmpty()) stack2.add(stack1.pop());//把栈中的数据倒过来
        if(stack2.isEmpty()) return -1;
        int res =  stack2.pop(); //输出1个数据
        return res;
    }


}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */

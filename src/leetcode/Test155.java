package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author zg
 * @create 2020-01-10 15:45
 *
 * 最小栈[简单]
 *
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 * 	push(x) -- 将元素 x 推入栈中。
 * 	pop() -- 删除栈顶的元素。
 * 	top() -- 获取栈顶元素。
 * 	getMin() -- 检索栈中的最小元素。
 *
 *
 * 示例:
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 *
 */
public class Test155 {
    //思路：普通的栈+辅助数组，辅助数组的元素数与栈中的元素数一样，数组中元素的秩与栈中元素对应，代表对应栈中的元素下的最小值
    private static Stack<Integer> stack;
    private static List<Integer> list;
    public Test155() {
        stack = new Stack<Integer>();
        list = new ArrayList<Integer>();
    }

    public void push(int x) {
        stack.push(x);
        if(list.isEmpty()||list.get(list.size()-1)>x) list.add(x);
        else list.add(list.get(list.size()-1));
    }

    public void pop() {
        stack.pop();
        list.remove(list.size()-1);
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return list.get(list.size()-1);
    }
}

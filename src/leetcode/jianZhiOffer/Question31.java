package leetcode.jianZhiOffer;

import java.util.Stack;

/**
 * @author zg
 * @create 2020-02-26 17:34
 *
 * 栈的压入、弹出序列[中等]
 *
 */
public class Question31 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        // 处理输入参数
        if(pushed.length!=popped.length) return false;
        // 处理边界值
        if(pushed.length==0) return true;

        Stack<Integer> stack = new Stack<>();
        int indexPush = 0;
        int indexPop = 0;
        // 模拟压栈弹栈
        while(indexPush<pushed.length ){
            if(stack.isEmpty() || stack.peek() != popped[indexPop]){
                stack.add(pushed[indexPush]);
                indexPush ++ ;
            }else{
                stack.pop();
                indexPop++;
            }
        }
        // 此时可能出现的情况是：压栈都压完了，弹栈需要判断下

        while(!stack.isEmpty()){
            if(stack.peek() == popped[indexPop]){
                stack.pop();
                indexPop++;
            }else break;//代表没法弹下去
        }
        return indexPop == popped.length;

    }
}

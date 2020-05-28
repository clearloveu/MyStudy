package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author zg
 * @create 2019-12-25 12:35
 *
 * 二叉树的中序遍历[中等]
 *
 * 给定一个二叉树，返回它的中序 遍历。
 *
 * 示例:
 * 输入: [1,null,2,3]
 *    1
 *     \
 *      2
 *     /
 *    3
 *
 * 输出: [1,3,2]
 *
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 */
public class Test94 {
    private static List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        //visit 当前节点，把值保存在集合中
        List<Integer> answer = new ArrayList<>();
        //第一次做不知道怎么做成这样了
//        TreeNode x =root;
//        //代表是否需要执行goAlongLeftBranch函数，如果进入右分支，就需要执行该函数
//        boolean flag = true;
//        while (true){
//            if (flag){ goAlongLeftBranch(x,stack);}
//            if (stack.empty()) break;
//            x = stack.pop();
//            answer.add(x.val);
//            if (x.right!=null) {
//                x = x.right;
//                flag = true;
//            }else flag=false;
//        }
//        return answer;

        goAlongLeftBranch(root,stack);
        while(!stack.isEmpty()){
            TreeNode x = stack.pop();
            answer.add(x.val);
            if(x.right!=null) goAlongLeftBranch(x.right,stack);
        }
        return answer;
    }

    private static void goAlongLeftBranch(TreeNode x,Stack<TreeNode> stack){
        while (x!=null){
            stack.push(x);
            x = x.left;
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode1.right = treeNode2;
        treeNode2.left = treeNode3;
        List<Integer> answer = inorderTraversal(treeNode1);
        System.out.println(answer.toString());
    }
    private static class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
     }
}

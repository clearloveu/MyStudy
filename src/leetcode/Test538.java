package leetcode;

import leetcode.utils.TreeLevelTraversal;
import leetcode.utils.TreeLevelTraversal.TreeNode;

import java.util.Stack;

/**
 * @author zg
 * @create 2020-02-13 18:02
 *
 * 把二叉搜索树转换为累加树[简单]
 *
 *
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 *
 * 例如：
 * 输入: 二叉搜索树:
 *               5
 *             /   \
 *            2     13
 *
 * 输出: 转换为累加树:
 *              18
 *             /   \
 *           20     13
 *
 */
public class Test538 {
    private int sum = 0;
    private TreeNode convertBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        goRightAlong(root,stack);

        while(!stack.isEmpty()){
            TreeNode tree = stack.pop();
            sum += tree.val;
            tree.val = sum;
            if(tree.left!=null) goRightAlong(tree.left,stack);
        }



        return root;
    }

    private static void goRightAlong(TreeNode tree,Stack<TreeNode> stack){
        while(tree!=null){
            stack.push(tree);
            tree = tree.right;
        }
    }



    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(1);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        TreeNode root = new Test538().convertBST(treeNode1);
        TreeLevelTraversal.levelOrder(root);
    }
}

package leetcode;

import java.util.*;

/**
 * @author zg
 * @create 2019-12-31 10:07
 *
 * 二叉树展开为链表[中等]
 *
 * 给定一个二叉树，原地将它展开为链表。
 *
 * 例如，给定二叉树
 *
 *     1
 *    / \
 *   2   5
 *  / \   \
 * 3   4   6
 *
 * 将其展开为：
 *
 * 1
 *  \
 *   2
 *    \
 *     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 *
 */
public class Test114 {
    //思想类似于前序遍历
    private static void flatten(TreeNode root) {

        //特例
        if (root==null) return;

        //迭代实现，参考Test94
//        Stack<TreeNode> stack = new Stack<>();
//        goLeftAlong(root,stack);

        //递归实现
        Queue<TreeNode> queue = new LinkedList<>();
        recursion(root,queue);
        //将节点顺序按照链表的顺序排序存在队列中，再从队列中取出，重新组成链表，这里明显不满足题目中原地修改的要求
        TreeNode treeNodeStart = queue.poll();
        while (!queue.isEmpty()){
            assert treeNodeStart != null;
            treeNodeStart.left = null;
            treeNodeStart.right = queue.poll();
            treeNodeStart = treeNodeStart.right;
        }

    }

    private static void goLeftAlong(TreeNode treeNode, Stack<TreeNode> stack){

    }

    private static void  recursion(TreeNode treeNode, Queue<TreeNode> queue){
        queue.add(treeNode);
        if (treeNode.left!=null) recursion(treeNode.left,queue);
        if (treeNode.right!=null) recursion(treeNode.right,queue);
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode5;
        treeNode2.left = treeNode3;
        treeNode2.right = treeNode4;
        treeNode5.right = treeNode6;
        flatten(treeNode1);
        while (treeNode1!=null){
            System.out.println(treeNode1.val+"");
            treeNode1 = treeNode1.right;
        }
    }

    private  static  class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
    }

}

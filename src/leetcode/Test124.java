package leetcode;

import leetcode.utils.TreeNode;

/**
 * @author zg
 * @create 2020-03-28 22:18
 *
 * 二叉树中的最大路径和[困难]
 *
 * 给定一个非空二叉树，返回其最大路径和。
 *
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 *
 * 示例 1:
 * 输入: [1,2,3]
 *
 *        1
 *       / \
 *      2   3
 * 输出: 6
 *
 * 示例 2:
 * 输入: [-10,9,20,null,null,15,7]
 *
 *    -10
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 输出: 42
 */
public class Test124 {
    private  int res;
    private int maxPathSum(TreeNode root) {
        res = Integer.MIN_VALUE;

        resursion(root);

        return res;

    }
    // 一定能找到，因为最长的路径也一定有一个类似于倒V的root节点，下面我们会遍历每一个节点看看以该点作为root点的最大路径长
    private int  resursion(TreeNode treeNode){
        //递归基
        if (treeNode==null) return 0;

        //左子树的最大路径和
        int leftValue = resursion(treeNode.left);
        //右子树的最大路径和
        int rightValue = resursion(treeNode.right);
        // 代表经过该点的最大路径和
        int max = Math.max(Math.max(Math.max(leftValue+treeNode.val,treeNode.val),leftValue+treeNode.val+rightValue),rightValue+treeNode.val);
        res = Math.max(max,res);
        // 返回给父节点最大的路径长，与上面不同的是，这里不能是leftValue+treeNode.val+rightValue的值，只能选一条路径
        return Math.max(Math.max(leftValue+treeNode.val,treeNode.val),rightValue+treeNode.val);

    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(-10);
        TreeNode treeNode2 = new TreeNode(9);
        TreeNode treeNode3 = new TreeNode(20);
        TreeNode treeNode4 = new TreeNode(15);
        TreeNode treeNode5 = new TreeNode(7);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode3.left = treeNode4;
        treeNode3.right = treeNode5;
        int res = new Test124().maxPathSum(treeNode1);
        System.out.println(res);
    }
}

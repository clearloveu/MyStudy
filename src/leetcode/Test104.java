package leetcode;

/**
 * @author zg
 * @create 2019-12-25 15:31
 *
 * 二叉树的最大深度[简单]
 *
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 返回它的最大深度 3 。
 *
 */
public class Test104 {
    private static int maxDepth(TreeNode root) {
        //特例，空二叉树
        if (root==null) return 0;

        return Integer.max(depth(root.left,1),depth(root.right,1));
    }

    private static int depth(TreeNode node,int nowDepth){
        //递归基
        if (node==null) return nowDepth;

        //递归体
        return Integer.max(depth(node.left,nowDepth+1),depth(node.right,nowDepth+1));
    }



    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(9);
        TreeNode treeNode3 = new TreeNode(20);
        TreeNode treeNode4 = new TreeNode(15);
        TreeNode treeNode5 = new TreeNode(7);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode3.left = treeNode4;
        treeNode3.right = treeNode5;
        System.out.println(maxDepth(treeNode1));
    }


     private static class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }


}

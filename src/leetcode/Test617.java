package leetcode;

import leetcode.utils.TreeLevelTraversal.TreeNode;

/**
 * @author zg
 * @create 2020-02-17 13:57
 *
 * 合并二叉树[中等]
 *
 * 给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。
 *
 * 你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，
 * 否则不为 NULL 的节点将直接作为新二叉树的节点。
 *
 * 示例 1:
 *
 * 输入:
 * 	Tree 1                     Tree 2
 *           1                         2
 *          / \                       / \
 *         3   2                     1   3
 *        /                           \   \
 *       5                             4   7
 * 输出:
 * 合并后的树:
 * 	     3
 * 	    / \
 * 	   4   5
 * 	  / \   \
 * 	 5   4   7
 *
 */
public class Test617 {
//        我们可以对这两棵树同时进行前序遍历，并将对应的节点进行合并。在遍历时，如果两棵树的当前节点均不为空，我们就将它们的值进行相加，
//        并对它们的左孩子和右孩子进行递归合并；如果其中有一棵树为空，那么我们返回另一颗树作为结果；如果两棵树均为空，此时返回任意一棵树均可（因为都是空）。

        public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
                if((t1!=null)&&(t2!=null)){
                        TreeNode root = new TreeNode(t1.val+t2.val);
                        recursion(t1.left,t2.left,root,true);
                        recursion(t1.right,t2.right,root,false);
                        return root;

                }else if(t1!=null){
                        return t1;
                }else if(t2!=null){
                        return t2;
                }else return null;
        }
        //领扣:递归
//        public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
//                if (t1 == null)
//                        return t2;
//                if (t2 == null)
//                        return t1;
//                t1.val += t2.val;
//                t1.left = mergeTrees(t1.left, t2.left);
//                t1.right = mergeTrees(t1.right, t2.right);
//                return t1;
//        }
        //迭代：利用栈，仿先序遍历


        public void recursion(TreeNode t1,TreeNode t2,TreeNode root,boolean flag){

                //递归体
                if((t1!=null)&&(t2!=null)){
                        TreeNode treeNode = new TreeNode(t1.val+t2.val);
                        if(flag) root.left = treeNode;
                        else root.right = treeNode;
                        recursion(t1.left,t2.left,treeNode,true);
                        recursion(t1.right,t2.right,treeNode,false);

                }else if(t1!=null){
                        TreeNode treeNode = new TreeNode(t1.val);
                        if(flag) root.left = treeNode;
                        else root.right = treeNode;
                        recursion(t1.left,null,treeNode,true);
                        recursion(t1.right,null,treeNode,false);
                }else if(t2!=null){
                        TreeNode treeNode = new TreeNode(t2.val);
                        if(flag) root.left = treeNode;
                        else root.right = treeNode;
                        recursion(null,t2.left,treeNode,true);
                        recursion(null,t2.right,treeNode,false);
                }
                //递归基
                return ;

        }

}

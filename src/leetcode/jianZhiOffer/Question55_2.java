package leetcode.jianZhiOffer;

import leetcode.utils.TreeNode;

/**
 * @author zg
 * @create 2020-03-12 20:07
 *
 * 55 - II. 平衡二叉树
 *
 */
public class Question55_2 {
    boolean flag ;
    private boolean isBalanced(TreeNode root) {
        flag = true;
        depth(root);
        return flag;
    }


    private int depth(TreeNode root){
        //递归基
        if(root==null) return 0;

        int leftTreeDepth = depth(root.left);
        int rightTreeDepth = depth(root.right);
        if(leftTreeDepth>rightTreeDepth+1 || rightTreeDepth>leftTreeDepth+1){
            flag = false;
        }
        return Math.max(leftTreeDepth,rightTreeDepth)+1;
    }
}

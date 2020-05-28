package leetcode.jianZhiOffer;

import leetcode.utils.TreeNode;

/**
 * @author zg
 * @create 2020-03-16 10:32
 *
 * 二叉树的最近公共祖先[简单]
 *
 */
public class Question68_2 {
    TreeNode answer ;
    private TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        int res = recursion(root,p,q);
        return res==-1?answer:null;
    }

    private int recursion(TreeNode root,TreeNode p ,TreeNode q){
        //递归基
        if(root==null) return 0;

        int res = 0;
        if(root == p || root== q) res +=1;
        int leftValue = recursion(root.left,p,q);
        int rightValue = recursion(root.right,p,q);
        if(leftValue==-1||rightValue==-1) return -1;
        int sum = res +leftValue +rightValue;
        //代表左子树、右子树、本身中包含p和q
        if(sum==2){
            answer = root;//找到了答案
            sum = -1;
        }
        return sum;

    }
}

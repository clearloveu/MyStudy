package leetcode.jianZhiOffer;

import leetcode.utils.TreeNode;

/**
 * @author zg
 * @create 2020-03-16 10:32
 *
 * 二叉搜索树的最近公共祖先[简单]
 *
 */
public class Question68 {
    private TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode currentTree = root;
        while(true){
            if(p.val<currentTree.val&&q.val<currentTree.val) currentTree = currentTree.left;
            else if(p.val>currentTree.val&&q.val>currentTree.val) currentTree = currentTree.right;
            else break;
        }


        return currentTree;
    }
}

package leetcode.jianZhiOffer;

import leetcode.utils.TreeNode;

/**
 * @author zg
 * @create 2020-04-06 18:06
 *
 * 面试题26. 树的子结构[中等]
 *
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 *
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 *
 * 例如:
 * 给定的树 A:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * 给定的树 B：
 *
 *    4
 *   /
 *  1
 * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
 *
 * 示例 1：
 * 输入：A = [1,2,3], B = [3,1]
 * 输出：false
 *
 * 示例 2：
 * 输入：A = [3,4,5,1,2], B = [4,1]
 * 输出：true
 *
 *
 */
public class Question26 {
    private boolean flag ;
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        flag = false;
        if (A==null&&B==null) return true;
        else if (B==null) return false;
        recrusion(A,B);
        return flag;

    }
    // 递归，遍历每一个节点
    private void recrusion(TreeNode A,TreeNode B){
        // 递归基
        if (A==null) return;

        if (A.val==B.val){
            boolean res = isValid(A,B);
            if (res) {
                flag = true;
                return;
            }
        }

        recrusion(A.left,B);
        recrusion(A.right,B);


    }
    // 判断是否是一样的子结构
    private boolean isValid(TreeNode A, TreeNode B){
        //递归基
        if (B==null) return true;
        else if (A==null) return false;

        if (A.val==B.val)
            return  isValid(A.right,B.right)&& isValid(A.left,B.left);
        else return false;

    }



}

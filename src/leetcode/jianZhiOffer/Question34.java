package leetcode.jianZhiOffer;

import leetcode.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zg
 * @create 2020-04-06 18:47
 *
 * 面试题34. 二叉树中和为某一值的路径[中等]
 *
 * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 *
 * 返回:
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 *
 */
public class Question34 {
    List<List<Integer>> res ;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        res = new ArrayList<>();
        if (root==null) return res;

        List<Integer> path = new ArrayList<>();
        path.add(root.val);
        recursion(root,sum,root.val,path);
        return res;
    }
    private void  recursion(TreeNode father,int sum,int currentSum,List<Integer> path){
        // 递归基
        if (father.left==null&&father.right==null) {
            if (currentSum==sum){
                List<Integer> temp = new ArrayList<>(path);
                res.add(temp);
            }
            return;
        }

        if (father.left!=null){
            path.add(father.left.val);
            recursion(father.left,sum,currentSum+father.left.val,path);
            path.remove(path.size()-1);
        }
        if (father.right!=null){
            path.add(father.right.val);
            recursion(father.right,sum,currentSum+father.right.val,path);
            path.remove(path.size()-1);
        }

    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(2);
        treeNode.left  = treeNode1;
        new  Question34().pathSum(treeNode,3);


    }
}

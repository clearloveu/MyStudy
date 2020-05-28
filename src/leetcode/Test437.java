package leetcode;

import leetcode.utils.TreeLevelTraversal.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zg
 * @create 2020-02-20 10:33
 *
 * 路径总和 III[简单]
 *
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 *
 * 找出路径和等于给定数值的路径总数。
 *
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 *
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 *
 * 示例：
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 *
 *       10
 *      /  \
 *     5   -3
 *    / \    \
 *   3   2   11
 *  / \   \
 * 3  -2   1
 *
 * 返回 3。和等于 8 的路径有:
 *
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3.  -3 -> 11
 */
public class Test437 {
    private static int answer ;
    private static int target;
    private int pathSum(TreeNode root, int sum) {
        answer = 0;
        target = sum;
        if(root==null) return 0;
        List<Integer> ls = new ArrayList<>();
        ls.add(root.val);
        recursion(root,ls);
        return answer;
    }
    //list代表到达treenode节点时，从root节点到treenode节点s所有节点的值
    private void recursion(TreeNode treenode , List<Integer> list){
        //判断是否能产生新的路径
        int temp = 0;
        for(int i=list.size()-1;i>=0;i--){
            temp +=list.get(i);
            if(target==temp) answer+=1;
        }

        //递归体
        if(treenode.left!=null){
            list.add(treenode.left.val);
            recursion(treenode.left,list);
            list.remove(list.size()-1);
        }
        if(treenode.right!=null){
            list.add(treenode.right.val);
            recursion(treenode.right,list);
            list.remove(list.size()-1);
        }

        //递归基,如果是叶子节点，自然就return了
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        treeNode1.left = treeNode2;
        System.out.println(new Test437().pathSum(treeNode1,3));
    }
}

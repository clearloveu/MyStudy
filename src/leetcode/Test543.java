package leetcode;

import leetcode.utils.TreeLevelTraversal;

/**
 * @author zg
 * @create 2020-02-14 16:15
 *
 * 二叉树的直径[简单]
 *
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。
 *
 * 示例 :
 * 给定二叉树
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 *
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 *
 *
 */
public class Test543 {
    static int answer ;
    private int diameterOfBinaryTree(TreeLevelTraversal.TreeNode root) {
        answer = 0;
        recursion(root);
        return answer;
    }

    private int recursion(TreeLevelTraversal.TreeNode tree){
        //递归基
        if(tree==null) return -1;

        //递归体
        int leftTreeMaxHeight = recursion(tree.left)+1; //每次递归调用回来时，高度都会+1
        int rightTreeMaxHeight = recursion(tree.right)+1;
        //经过该节点的最大长度
        if(leftTreeMaxHeight+rightTreeMaxHeight>answer) answer=leftTreeMaxHeight+rightTreeMaxHeight;
        return leftTreeMaxHeight>rightTreeMaxHeight?leftTreeMaxHeight:rightTreeMaxHeight;

    }
}

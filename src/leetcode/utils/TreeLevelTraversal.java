package leetcode.utils;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zg
 * @create 2020-02-13 18:32
 */
public class TreeLevelTraversal {
    public static  void levelOrder(TreeNode treeNode){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(treeNode);
        while (!queue.isEmpty()){
            TreeNode treeNode1 = queue.poll();
            System.out.println(treeNode1.val);
            if (treeNode1.left!=null) queue.add(treeNode1.left);
            if (treeNode1.right!=null) queue.add(treeNode1.right);
        }
    }

    public static class TreeNode {

        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int x) { val = x; }
    }
}

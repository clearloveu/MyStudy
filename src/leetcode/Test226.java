package leetcode;

/**
 * @author zg
 * @create 2020-01-31 22:38
 *
 * 翻转二叉树[简单]
 *
 *
 * 翻转一棵二叉树。
 * 示例：
 * 输入：
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 *
 * 输出：
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 *
 */
public class Test226 {
    private TreeNode invertTree(TreeNode root) {
        if (root==null) return root;
        recursion(root);
        return root;
    }
    private void recursion(TreeNode father){
        //递归基
        if(father.left==null&&father.right==null) return;

        //递归体
        if(father.left==null&&father.right!=null){
            TreeNode swap = father.right;
            father.left = swap;
            father.right = null;
            recursion(father.left);
        }
        else if(father.right!=null){
            TreeNode swap = father.right;
            father.right = father.left;
            father.left = swap;
            recursion(father.right);
            recursion(father.left);
        }else {
            TreeNode swap = father.left;
            father.right = swap;
            father.left = null;
            recursion(father.right);
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        treeNode1.right = treeNode2;
        new Test226().invertTree(treeNode1);


    }

    private static class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
    }
}

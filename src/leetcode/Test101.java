package leetcode;

/**
 * @author zg
 * @create 2019-12-16 22:09
 *
 * 对称二叉树[简单]
 *
 *  给定一个二叉树，检查它是否是镜像对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 *
 * 说明:
 *
 * 如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
 *
 */
public class Test101 {
    private static boolean isSymmetric(TreeNode root) {

        if (root==null) return true;
        return recursion(root.left,root.right);
    }


    private static boolean recursion (TreeNode left,TreeNode right){
        boolean flag ;

        if (left==null&&right==null) return true;
        else if (left!=null&&right!=null) {
            if (left.val == right.val) {
                TreeNode left_left = left.left;
                TreeNode right_right = right.right;
                flag = recursion(left_left,right_right);
                if (!flag) return false;
                else{
                    TreeNode left_right = left.right;
                    TreeNode right_left = right.left;
                    flag = recursion(left_right,right_left);
                }
                if (!flag) return false;
                else return true;
            }
        }

        return false;
    }

    //参考剑指offer第161页，递归的思路清晰,我的太混乱：重点：把一个点的左右分支的两个迭代写成&&，值得学习
    //迭代：
    //领扣：
    //队列中每两个连续的结点应该是相等的，而且它们的子树互为镜像。最初，队列中包含的是 root 以及 root。该算法的工作原理类似于 BFS，
    // 但存在一些关键差异。每次提取两个结点并比较它们的值。然后，将两个结点的左右子结点按相反的顺序插入队列中。
    // 当队列为空时，或者我们检测到树不对称（即从队列中取出两个不相等的连续结点）时，该算法结束.
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(2);
        TreeNode treeNode4 = new TreeNode(3);
        TreeNode treeNode5 = new TreeNode(4);
        TreeNode treeNode6 = new TreeNode(4);
        TreeNode treeNode7 = new TreeNode(3);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;
        System.out.println(isSymmetric(treeNode1));
    }


    //Definition for a binary tree node.
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

}




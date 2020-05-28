package leetcode;

/**
 * @author zg
 * @create 2019-12-31 10:07
 *
 * 验证二叉搜索树[中等]
 *
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * 假设一个二叉搜索树具有如下特征：
 *
 * 	节点的左子树只包含小于当前节点的数。
 * 	节点的右子树只包含大于当前节点的数。
 * 	所有左子树和右子树自身必须也是二叉搜索树。
 *
 * 示例 1:
 * 输入:
 *     2
 *    / \
 *   1   3
 * 输出: true
 *
 *
 * 示例 2:
 * 输入:
 *     5
 *    / \
 *   1   4
 *      / \
 *     3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 *      根节点的值为 5 ，但是其右子节点值为 4 。
 *
 */
public class Test98 {
    //注意点：是父节点小于右子树所有的点，大于左子树所有的点
    private static boolean isValidBST(TreeNode root) {

        //特例
        if (root ==null) return true;

        //递归基
        if (root.left==null&&root.right==null) return true;

        //递归体
        //左子树为空，右子树不为空
        if (root.left==null){
            if (root.val<findRightTreeMinValue(root)) return isValidBST(root.right);
            else return false;
        }
        //左子树不为空，右子树为空
        if (root.right==null){
            if (root.val>findLeftTreeMaxValue(root)) return isValidBST(root.left);
            else return false;
        }
        //左右子树均不为空
        if (root.val<findRightTreeMinValue(root)&&root.val>findLeftTreeMaxValue(root)) return isValidBST(root.left) && isValidBST(root.right);
        else return false;

    }

    //找到右子树中最小的值，即右子树中左分支中最左的节点
    private static int findRightTreeMinValue(TreeNode root){
        if (root.right==null) return Integer.MAX_VALUE;
        TreeNode treeNode = root.right;
        while (treeNode.left!=null){

            treeNode=treeNode.left;
        }
        return treeNode.val;
    }


    //找到左子树中最大的值，即左子树中右分支中最右的节点
    private static int findLeftTreeMaxValue(TreeNode root){
        if (root.left==null) return Integer.MIN_VALUE;
        TreeNode treeNode = root.left;
        while (treeNode.right!=null){
            treeNode = treeNode.right;
        }
        return treeNode.val;
    }



    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(1);
        TreeNode treeNode3 = new TreeNode(3);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        System.out.println(isValidBST(treeNode1));
    }

    private static class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
    }
}

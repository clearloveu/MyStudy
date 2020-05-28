package leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zg
 * @create 2019-12-29 17:50
 *
 * 从前序与中序遍历序列构造二叉树[中等]
 *
 *
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 *
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 */
public class Test105 {
    private static TreeNode buildTree(int[] preorder, int[] inorder) {
        //前序遍历:[root，左子树，右子树]
        //中序遍历：[左子树，root，右子树]

        //特例
        if (preorder.length==0) return null;

        TreeNode root = new TreeNode(preorder[0]);
        recursion(root,preorder,inorder);
        return root;
    }


    private static void recursion(TreeNode father,int[] preorder,int[] inorder){

        //父节点
        int root = preorder[0];
        //前序遍历:[root，左子树，右子树]
        //中序遍历：[左子树，root，右子树]
        //root在中序遍历中的秩（可以用HashMap代替，见Question7.java）
        int index = calculateRootIndex(root,inorder);
        int[] preorderLeftTree = Arrays.copyOfRange(preorder,1,index+1);
        int[] preorderRightTree = Arrays.copyOfRange(preorder,index+1,preorder.length);
        int[] inorderLeftTree = Arrays.copyOfRange(inorder,0,index);
        int[] inorderRightTree = Arrays.copyOfRange(inorder,index+1,inorder.length);



        //递归体
        //递归到左子树
        if (preorderLeftTree.length!=0){
            father.left = new TreeNode(preorderLeftTree[0]);
            recursion(father.left,preorderLeftTree,inorderLeftTree);
        }
        //递归到右子树
        if (preorderRightTree.length!=0){
            father.right = new TreeNode(preorderRightTree[0]);
            recursion(father.right,preorderRightTree,inorderRightTree);
        }

        //递归基不需要写，没有左、右子树就是递归基


    }


    private static int calculateRootIndex(int root,int[] inorder){
        for (int i = 0;i<inorder.length;i++){
            if(root==inorder[i]) return i;
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode root = buildTree(preorder,inorder);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode value = queue.poll();
            System.out.println(value.val+" --");
            if (value.left!= null) queue.add(value.left);
            if (value.right!=null) queue.add(value.right);
        }
    }

    private static class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
    }
}

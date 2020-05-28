package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author zg
 * @create 2020-01-06 10:10
 */
public class Test103 {
    private static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> answers = new ArrayList<>();
        if (root==null) return answers;
        //第一个栈代表从左到右遍历，第1，3，5等层
        Stack<TreeNode> stackLeft = new Stack<>();
        //第两个栈代表从右到左遍历，第2，4，6等层
        Stack<TreeNode> stackRight = new Stack<>();

        stackLeft.add(root);
        //flag%2=0代表第1，3，5，7等层，flag%2=1代表第2，4，6，8等层
        int flag = 0;
        while (!stackLeft.empty() || !stackRight.empty()){
            List<Integer> answer = new ArrayList<>();
            //代表单层，单层先左子树，后右子树
            if (flag%2==0){
                while (!stackLeft.empty()){
                    TreeNode treeNode = stackLeft.pop();
                    answer.add(treeNode.val);
                    if (treeNode.left!=null) stackRight.add(treeNode.left);
                    if (treeNode.right!=null) stackRight.add(treeNode.right);
                }
                answers.add(answer);
                flag +=1;
            }else { //代表双层,双层先右子树，后左子树
                while (!stackRight.empty()){
                    TreeNode treeNode = stackRight.pop();
                    answer.add(treeNode.val);
                    if (treeNode.right!=null) stackLeft.add(treeNode.right);
                    if (treeNode.left!=null) stackLeft.add(treeNode.left);
                }
                answers.add(answer);
                flag +=1;
            }
        }
        return answers;
    }

    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(9);
        TreeNode treeNode3 = new TreeNode(20);
        TreeNode treeNode4 = new TreeNode(15);
        TreeNode treeNode5 = new TreeNode(7);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode3.left = treeNode4;
        treeNode3.right = treeNode5;
        List<List<Integer>> answers = zigzagLevelOrder(treeNode1);
        System.out.println(answers.toString());
    }

    private static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

}

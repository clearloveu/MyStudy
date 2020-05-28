package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author zg
 * @create 2019-12-28 20:51
 *
 * 二叉树的层次遍历[中等]
 *
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 返回其层次遍历结果：
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 */
public class Test102 {

    private static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> answers = new ArrayList<>();
        //某一层的数字的集合
        List<Integer> answer  = new ArrayList<>();
        //特例，root为空指针
        if (root==null) {
            return answers;
        }
        //队列，按从左到右的顺序插入到队列中
        Queue<Object> queue = new LinkedList<>();

        queue.add(root);
        //插入分隔符，遇到这个分隔符代表一层遍历完成
        queue.add("#");
        while (!queue.isEmpty()){
            Object value = queue.poll();
            //如果是分隔符"#"
            if (value.equals("#")) {

                answers.add(answer);
                answer = new ArrayList<>();
                //如果这是最后一层的分隔符，则此时queue为空，则直接退出，否则这不是最后一层
                if (queue.isEmpty()) break;
                else {
                    //代表一层遍历完成，给队列尾部加分隔符
                    queue.add("#");
                    continue;
                }
            }
            //如果不是分隔符，一定是节点
            TreeNode value_tree = (TreeNode)value;

            answer.add(value_tree.val);
            if (value_tree.left!=null) {
                queue.add(value_tree.left);
            }
            if (value_tree.right!=null){
                queue.add(value_tree.right);
            }
        }

        return answers;
    }

    //领扣上是维护一个level的int变量，代表层数
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
        List<List<Integer>> answer = levelOrder(treeNode1);
        System.out.println(answer.toString());
    }

    private static class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
    }

}

package leetcode.jianZhiOffer;

import leetcode.utils.TreeLevelTraversal.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author zg
 * @create 2020-03-07 21:08
 *
 * 序列化二叉树[困难]            [未完成]
 *
 */
public class Question37 {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        //层次遍历
        Queue<TreeNode> queue1 = new LinkedList<>();
        Queue<TreeNode> queue2 = new LinkedList<>();
        boolean flag = true;
        queue1.add(root);
        while(flag) {
            if (!queue1.isEmpty()) {
                flag = false;
                while (!queue1.isEmpty()) {
                    TreeNode node = queue1.poll();

                    if (node == null) {
                        list.add(null);
                        queue2.add(null);
                        queue2.add(null);
                    } else {
                        list.add(node.val);
                        flag = true;
                        queue2.add(node.left);
                        queue2.add(node.right);
                    }
                }

            } else if (!queue2.isEmpty()) {
                flag = false;
                while (!queue2.isEmpty()) {
                    TreeNode node = queue2.poll();

                    if (node == null) {
                        list.add(null);
                        queue1.add(null);
                        queue1.add(null);
                    } else {
                        list.add(node.val);
                        flag = true;
                        queue1.add(node.left);
                        queue1.add(node.right);
                    }
                }

            }
        }
        return list.toString();
    }

    // 反序列化有bug
    public TreeNode deserialize(String data) {
        String[] temp = data.substring(1,data.length()-1).split(",");
        int index  = 0;
        if(temp[index].trim().equals("null")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(temp[index].trim()));
        Queue<TreeNode> queue1 = new LinkedList<>();
        queue1.add(root);
        Queue<TreeNode> queue2 = new LinkedList<>();

        while(index<temp.length){
            if (!queue1.isEmpty()) {
                while (!queue1.isEmpty()) {
                    TreeNode node = queue1.poll();
                    if (node == null) {
                        index += 2;
                        queue2.add(null);
                        queue2.add(null);
                    } else {
                        if(temp[index+1].equals("null")){
                            queue2.add(null);
                        }else {
                            TreeNode leftNode = new TreeNode(Integer.parseInt(temp[index+1].trim()));
                            node.left = leftNode;
                            queue2.add(leftNode);
                        }
                        if(temp[index+2].equals("null")){
                            queue2.add(null);
                        }else {
                            TreeNode rightNode = new TreeNode(Integer.parseInt(temp[index+2].trim()));
                            node.right = rightNode;
                            queue2.add(rightNode);
                        }
                        index += 2;
                    }
                }

            }else if (!queue2.isEmpty()) {
                while (!queue2.isEmpty()) {
                    TreeNode node = queue2.poll();
                    if (node == null) {
                        index += 2;
                        queue1.add(null);
                        queue1.add(null);
                    } else {
                        if(temp[index+1].equals("null")){
                            queue1.add(null);
                        }else {
                            TreeNode leftNode = new TreeNode(Integer.parseInt(temp[index+1].trim()));
                            node.left = leftNode;
                            queue1.add(leftNode);
                        }
                        if(temp[index+2].equals("null")){
                            queue1.add(null);
                        }else {
                            TreeNode rightNode = new TreeNode(Integer.parseInt(temp[index+2].trim()));
                            node.right = rightNode;
                            queue1.add(rightNode);
                        }
                        index += 2;
                    }
                }

            }
        }
        return root;
    }
}

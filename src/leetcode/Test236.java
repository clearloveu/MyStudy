package leetcode;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zg
 * @create 2020-01-11 16:28
 * 二叉树的最近公共祖先[中等]
 *
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 *
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q
 * 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 *
 */
public class Test236 {
    // 2024.06.10
    private static TreeNode father;
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        father = null;
        Set<TreeNode> set = Stream.of(p, q).collect(Collectors.toSet());
        findChild(root, set);
        return father;
    }
    private static int findChild(TreeNode node, Set<TreeNode> set) {
        if (node == null) return 0;
        int res = findChild(node.left, set) + findChild(node.right, set) + (set.contains(node) ? 1 :0);
        if(res > 1 && father == null) father = node;
        return res > 0?1 :0;
    }
    private static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        //层次遍历,把二叉树的数据转化为列表，其中列表中0代表该二叉树位置是null，>1代表有值，但不是p或q，-1、-2代表p或q的位置
        //再在列表中寻找对应的父节点
        //列表中的数字和树节点相对应，用HashMap存储
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<Integer> list = new ArrayList<>();
        Map<Integer,TreeNode> map = new HashMap<>();
        queue.add(root);

        int flag = 0;
        int current = 0;
        //层次遍历,把二叉树的数据转化为列表
        while (flag>-2 ){
            TreeNode currentNode = queue.poll();
            if (currentNode!=null){
                if (currentNode!=q&&currentNode!=p){
                    current +=1;
                    map.put(current,currentNode);
                    list.add(current);
                    queue.add(currentNode.left);
                    queue.add(currentNode.right);
                }else {
                    flag -=1;
                    map.put(flag,currentNode);
                    list.add(flag);
                    queue.add(currentNode.left);
                    queue.add(currentNode.right);
                }
            }else {
                list.add(0);
                queue.add(null);
                queue.add(null);
            }
        }
        int index_1 = list.indexOf(-1);
        int index_2 = list.indexOf(-2);
        int fatherNodeIndex = findFatherNode(index_1,index_2);
        assert fatherNodeIndex!=-1;
        return map.get(list.get(fatherNodeIndex));

    }

    //从列表中找到2个指定节点的最近公共祖先
    private static int findFatherNode(int index_1,int index_2){
        //为了/2可以求祖先节点，先要+1
        index_1 +=1;
        index_2 +=1;
        //第一个节点的所有祖先节点，包括自身
        List<Integer> fatherNode = new ArrayList<>();
        while (index_1!=0){
            fatherNode.add(index_1);
            index_1 = index_1/2;
        }
        //每找到第二个节点的祖先节点，就去fatherNode去寻找，如果找到，则直接返回
        while (index_2!=0){
            int index = fatherNode.indexOf(index_2);
            if (index!=-1) return fatherNode.get(index)-1;
            index_2 = index_2/2;
        }
        //-1代表异常
        return -1;
    }


    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(5);
        TreeNode treeNode3 = new TreeNode(1);
        TreeNode treeNode4 = new TreeNode(6);
        TreeNode treeNode5 = new TreeNode(2);
        TreeNode treeNode6 = new TreeNode(0);
        TreeNode treeNode7 = new TreeNode(8);
        treeNode1.left = treeNode2;
        treeNode1.right = treeNode3;
        treeNode2.left = treeNode4;
        treeNode2.right = treeNode5;
        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;
        TreeNode treeNode = lowestCommonAncestor2(treeNode1,treeNode5,treeNode2);
        System.out.println(treeNode.val);
    }


    private static class TreeNode {
       int val;
       TreeNode left;
       TreeNode right;
       TreeNode(int x) { val = x; }
    }

}

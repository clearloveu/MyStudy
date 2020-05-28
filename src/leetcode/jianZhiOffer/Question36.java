package leetcode.jianZhiOffer;

import leetcode.utils.Node;

/**
 * @author zg
 * @create 2020-03-07 21:01
 *
 * 二叉搜索树与双向链表[中等]
 *
 */
public class Question36 {
    Node pre;
    public Node treeToDoublyList(Node root) {
        if(root==null) return root;
        pre = null; //前一个遍历的节点
        Node head = root;  //头节点
        Node tail = root;  //尾节点
        //找到头节点
        while(head.left!=null) head = head.left;
        //找到尾节点
        while(tail.right!=null) tail = tail.right;
        inorder(root);//中序遍历
        //连接头尾
        tail.right = head;
        head.left = tail;
        return head;
    }
    //中序遍历即为有序的，它的遍历顺序就是双向链表的顺序
    public void inorder(Node node){
        if(node==null) return;
        inorder(node.left);
        //连接前一个节点和当前节点，这里if判断是避免pre未赋值之前会空指针
        if(pre!=null) pre.right = node;
        node.left = pre;
        pre = node;
        inorder(node.right);
    }
}

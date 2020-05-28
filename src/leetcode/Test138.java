package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zg
 * @create 2020-01-06 18:11
 *
 * 复制带随机指针的链表[中等]
 *
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 * 要求返回这个链表的深拷贝。
 *
 * 示例：
 * 输入：
 * {"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}
 *
 * 解释：
 * 节点 1 的值是 1，它的下一个指针和随机指针都指向节点 2 。
 * 节点 2 的值是 2，它的下一个指针指向 null，随机指针指向它自己。
 *
 * 提示：
 * 	你必须返回给定头的拷贝作为对克隆列表的引用。
 *
 */
public class Test138 {
    private static Node copyRandomList(Node head) {
        if (head==null) return null;
        //先构造新的列表的表头
        Node root = new Node(head.val);
        //把旧的列表的表项和新的列表的表项绑在一起，以便通过旧表项能在O(1)时间找到新表项
        Map<Node,Node> map = new HashMap<>();
        map.put(head,root);
        //保留表头的指针，以便后续操作
        Node nodeNowCurrent = root;
        Node nodePastCurrent = head;
        //先拷贝每个表项的next指针，因为next总是指向后面一个元素的
        while (nodePastCurrent.next!=null){
            nodeNowCurrent.next = new Node(nodePastCurrent.next.val);
            nodeNowCurrent = nodeNowCurrent.next;
            nodePastCurrent = nodePastCurrent.next;
            //把旧的列表的表项和新的列表的表项绑在一起，以便通过旧表项能在O(1)时间找到新表项
            map.put(nodePastCurrent,nodeNowCurrent);
        }
        //重置表头指针
        nodePastCurrent = head;
        nodeNowCurrent = root;
        //拷贝表项的random指针
        while (nodePastCurrent!=null){
            if (nodePastCurrent.random!=null) {
                //根据旧表项和map能很快找到新表项
                nodeNowCurrent.random = map.get(nodePastCurrent.random);
            }
            nodeNowCurrent = nodeNowCurrent.next;
            nodePastCurrent = nodePastCurrent.next;
        }
        return root;
    }

    public static void main(String[] args) {
//        Node node1 = new Node(1);
//        Node node2 = new Node(2);
//        node1.next = node2;
//        node1.random = node2;
//        node2.random = node2;

        Node node1 = new Node(7);
        Node node2 = new Node(13);
        Node node3 = new Node(11);
        Node node4 = new Node(10);
        Node node5 = new Node(1);
        node1.next = node2;
        node2.next = node3;
        node2.random = node1;
        node3.next = node4;
        node3.random = node3;
        node4.next = node5;
        node4.random = node3;
        node5.random = node1;

        Node head = copyRandomList(node1);
        while (head!=null) {
            System.out.print(head.val+" ");
            head = head.next;
        }
    }

    private static class  Node {
        int val;
        Node next;
        Node random;

        private Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}

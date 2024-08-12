package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zg
 * @create 2020-01-06 10:48
 *
 * 实现 Trie (前缀树)[中等]
 *
 * 实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
 *
 * 示例:
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // 返回 true
 * trie.search("app");     // 返回 false
 * trie.startsWith("app"); // 返回 true
 * trie.insert("app");
 * trie.search("app");     // 返回 true
 *
 * 说明:
 * 	你可以假设所有的输入都是由小写字母 a-z 构成的。
 * 	保证所有输入均为非空字符串。
 *
 *
 */
public class Test208_2 {
    class Node {
        Map<Character, Node> map = new HashMap<>();
        boolean isEnd = false;
        public Node () {

        }
    }
    Node head;
    public Test208_2() {
        head = new Node();
    }

    public void insert(String word) {
        Node current = head;
        for(int i = 0;i<word.length();i++) {
            char cc = word.charAt(i);
            Map<Character, Node> map = current.map;
            Node nextNode = map.get(cc);
            if(nextNode != null) {
                current = nextNode;
            } else {
                Node temp = new Node();
                map.put(cc, temp);
                current = temp;
            }
            if(i == word.length()-1) current.isEnd = true;
        }
    }

    public boolean search(String word) {
        return inner(word, true);
    }

    public boolean startsWith(String prefix) {
        return inner(prefix, false);
    }

    private boolean inner(String word, boolean isEnd) {
        Node current = head;
        for(int i = 0;i< word.length();i++) {
            Node node = current.map.get(word.charAt(i));
            if(node == null) return false;
            if(i == word.length()-1 && isEnd && !node.isEnd) return false;
            current = node;
        }
        return true;
    }
}

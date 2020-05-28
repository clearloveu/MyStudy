package leetcode;

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
public class Test208 {
    //前缀树的根路径
    static TrieTree root;
    /** Initialize your data structure here. */
    public Test208() {
        root = new TrieTree();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieTree temp = root;
        //将字符串中每个字符存到相应的位置
        for (int i = 0;i<word.length();i++){
            int int_i = word.charAt(i) - 'a';
            //如果数组中该位置没有初始化，代表之前没有操作到该位置代表的字母，则先初始化，避免空指针
            //为什么不提前在构造时先初始化，因为这样能节省时间，按需初始化，当用到数组中该位置时，才初始化
            if (temp.trieTrees[int_i]==null) init(temp,int_i);
            //如果不是最后结尾字符
            if (i!=word.length()-1) {
                //则状态不是2的情况下，都可以将状态归为1
                //原因，当状态为0时，代表初始化，没有操作到该字符，自然需要赋值为1
                //当状态为1时，无影响
                //当状态为2时，代表是某个字符串的结尾字符，该状态也可以表示某一个字符串的中间字符，无需改变
                if (temp.trieTrees[int_i].val != 2) temp.trieTrees[int_i].val = 1;
                //迭代前缀树的下一层
                temp = temp.trieTrees[int_i];
            }else {
                //是最后结尾字符，则直接赋值为2
                temp.trieTrees[int_i].val = 2;
            }
        }

    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        //从root查找，必须最后一个结尾字符对应的状态是2
        TrieTree temp = root;
        for (int i = 0;i<word.length();i++) {
            int int_i = word.charAt(i) - 'a';
            //避免出现空指针
            if (temp.trieTrees[int_i]==null) init(temp,int_i);
            if (i != word.length() - 1) {
                if (temp.trieTrees[int_i].val == 1 || temp.trieTrees[int_i].val == 2) temp = temp.trieTrees[int_i];
                else if (temp.trieTrees[int_i].val == 0) return false;
            }else if (temp.trieTrees[int_i].val!=2) return false;
        }

        return true;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        //从root查找，无需关心最后一个结尾字符对应的状态
        TrieTree temp = root;
        for (int i = 0; i < prefix.length(); i++) {
            int int_i = prefix.charAt(i) - 'a';
            if (temp.trieTrees[int_i]==null) init(temp,int_i);
            if (temp.trieTrees[int_i].val == 1 || temp.trieTrees[int_i].val == 2) temp = temp.trieTrees[int_i];
            else if (temp.trieTrees[int_i].val == 0) return false;
        }
        return true;
    }

    private static void init(TrieTree trieTree, int i){
        trieTree.trieTrees[i] = new TrieTree();
    }


    private static class TrieTree{
        //0代表初始化的值，任何字符串都不包含该字符，初始化时用到，1代表该字符在某个或几个字符串的中间，2代表该字符在某个字符串的结尾
        int val=0;
        //数组元素的序号代表字母，秩为0是a，1是b，2是c
        TrieTree[] trieTrees = new TrieTree[26];
    }
}

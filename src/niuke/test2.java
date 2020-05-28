package niuke;

import java.util.Stack;

/**
 * @author zg
 * @create 2020-03-19 19:33
 *
 * 返回二叉搜索树的第K大节点（从大到小）
 *
 */
public class test2 {
    public static void main(String[] args) {
    }



    class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;
        public  TreeNode(int val){
            this.data = val;
        }
    }
    /**
     *
     *
     * @param root 二叉查找树的根节点，不为空
     * @param k 第K大元素
     * @return 返回找到的元素节点指针
     */
    private   TreeNode findTopK(TreeNode root,int k){
        //二叉查找树，中序遍历的反转：先右子树，根，再左子树
        Stack<TreeNode> stack = new Stack<>();
        goAlongRightTree(root,stack);

        while(!stack.isEmpty()){
            TreeNode temp = stack.pop();
            k--;
            if (k==0) return temp;
            if (temp.left!=null) goAlongRightTree(temp.left,stack);
        }
        return  null;
    }
    private void  goAlongRightTree(TreeNode temp, Stack<TreeNode> stack){
        while (temp!=null){
            stack.push(temp);
            temp = temp.right;
        }
    }


}

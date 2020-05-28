package leetcode.jianZhiOffer;

/**
 * @author zg
 * @create 2020-04-06 18:26
 *
 * 面试题33. 二叉搜索树的后序遍历序列[中等]
 *
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
 *
 */
public class Question33 {

    public boolean verifyPostorder(int[] postorder) {


        return recursion(postorder,0,postorder.length-1);
    }

    private boolean recursion(int[] postorder,int left,int right){
        // 递归基
        if (right-left<=1) return true;

        // 父节点
        int root = postorder[right];
        int index = findLeftTree(postorder,root,left,right);

        // 如果在右子树中找到一个小于root节点的值，则代表不是二叉搜索树的后序遍历序列
        for (int i=index;i<=right-1;i++){
            if (postorder[i]<root) return false;
        }

        return recursion(postorder,left,index-1)&&recursion(postorder,index,right-1);
    }

    // 找到第一个大于父节点的索引，应该该索引之后都是大于父节点的，属于右子树
    private  int findLeftTree(int[] postorder,int root,int left,int right){
        for (int i = left; i <= right-1; i++) {
            if (postorder[i]>root) return i;
        }
        //代表没有找到大于父节点的值
        return right;
    }
}

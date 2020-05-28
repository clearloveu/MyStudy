package leetcode;

/**
 * @author zg
 * @create 2019-12-25 14:36
 *
 * 不同的二叉搜索树[中等]
 *
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * 示例:
 *
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 */
public class Test96 {
    private static int numTrees(int n) {
        //一个节点的情况
        if (n==1) return 1;

        //动态规划
        int[] dq = new int[n+1];
        //0个节点，0个二叉搜索数数量
        dq[0] =0;
        //1个节点，1个二叉搜索数数量
        dq[1] =1;

        for (int i=2; i<=n;i++){
            dq[i] = recursion(i,dq);
        }
        return dq[n];
    }

    private static int recursion(int currentNodeNum ,int[] dq){
        //currentNodeNum节点数的时候的二叉搜索树的数量
        int temp = 0;

        //遍历当前搜索二叉树的节点，当前遍历的节点作为根节点
        for (int i = 0;i<currentNodeNum;i++){
            //当前遍历节点的左分支的节点数
            int left = i;
            //当前遍历节点的右分支的节点数
            int right = currentNodeNum-1-i;
            //左分支节点数可以组成的二叉搜索树的数量为dq[left]，之前一定计算过了，不需要计算，同理右分支
            int leftTreeNodeNUmber = dq[left];
            int rightTreeNodeNUmber = dq[right];
            //最后计算以该节点为root节点的二叉搜索树的和时，是左分支计算出来的搜索树的数量*右分支出来的搜索树的数量
            //当左分支计算出来的搜索树的数量为0的时候，应该改成1，同理右分支
            if (leftTreeNodeNUmber==0) leftTreeNodeNUmber=1;
            if (rightTreeNodeNUmber==0) rightTreeNodeNUmber =1;
            temp = temp+ leftTreeNodeNUmber*rightTreeNodeNUmber;
        }
        return temp;
    }

    public static void main(String[] args) {
        int n = 3;
        System.out.println(numTrees(n));
    }
}

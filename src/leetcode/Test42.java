package leetcode;

/**
 * @author zg
 * @create 2020-03-28 13:30
 *
 * 接雨水[困难]
 *
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 */
public class Test42 {
    static  int res ;
    public int trap(int[] height) {
        res = 0;

        // 找到第一个不是0的值
        int firstIndex = -1;
        for (int i = 0; i < height.length; i++) {
            if (height[i]!=0) {
                firstIndex = i;
                break;
            }
        }
        if(firstIndex==-1) return 0;
        findAfter(height,firstIndex);
        return res;
    }
    //遍历，找到第一个比前一个柱子高的区间
    private void findAfter(int[] height,int firstIndex){
        int beforeHeight = height[firstIndex];
        for (int i = firstIndex+1; i < height.length; i++) {
            int currentHeight = height[i];
            if (beforeHeight<=currentHeight) {
                dealOne(height,firstIndex,i);
                beforeHeight = height[i];
                firstIndex = i;
            }
            else if (i==height.length-1) dealTwo(height,firstIndex);
        }
    }

    // 处理存在一个后柱子比前柱子大的区间
    private void dealOne(int[] height,int firstIndex,int endIndex){
        for (int i = firstIndex+1;i<endIndex;i++){
            res += height[firstIndex]-height[i];
        }
    }
    // 处理不存在一个后柱子比前柱子大的区间,找到后面柱子里最大的一个
    private void dealTwo(int[] height,int firstIndex){
        int maxValue = -1;
        int maxIndex = -1;
        for (int i = height.length-1; i > firstIndex; i--) {
            if (maxValue<height[i]) {
                maxValue = height[i];
                maxIndex = i;
            }
        }
        for (int i = firstIndex+1; i < maxIndex; i++) {
            res+=height[maxIndex] - height[i];
        }
        if (maxIndex!=height.length-1) dealTwo(height,maxIndex);


    }

    public static void main(String[] args) {
        int[] a = {0,1,0,2,1,0,1,3,2,1,2,1};
        int res =new Test42().trap(a);
        System.out.println(res);
    }

}

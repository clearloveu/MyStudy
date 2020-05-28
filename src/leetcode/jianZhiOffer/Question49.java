package leetcode.jianZhiOffer;

/**
 * @author zg
 * @create 2020-02-21 9:44
 *
 * 丑数[中等]
 *
 */
public class Question49 {
    private int nthUglyNumber(int n) {
        //暴力法：每次都去判断是否是丑数
        //动态规划
//        // dp[i] ,i代表自然数大小,dp[i]代表是否是丑数，1表示是
//        // dp[i] = max(dp[i/2],dp[i/3],dp[i/5]) 当i能被整除
//        List<Integer> dp = new ArrayList<>();
//        dp.add(-1);  //索引0放弃
//        dp.add(1);  //代表1是丑数
//        n = n -1;
//        int index = 2;
//        while(true){
//            //代表找到第n个丑数
//            if(n==0){
//                return index;
//            }
//            int temp_2 = (index%2)==0?dp.get(index/2):0;
//            int temp_3 = (index%3)==0?dp.get(index/3):0;
//            int temp_5 = (index%5)==0?dp.get(index/5):0;
//            if(temp_2==1 || temp_3==1 || temp_5==1){
//                dp.add(1);
//                n -=1;
//            }
//            else dp.add(0);
//            index +=1;
//
//        }


        // 直接找丑数
        int[] res = new int[n];
        res[0] = 1;
        int p2 = 0; //代表最小且p2*2还没有出现的丑数
        int p3 = 0; //代表最小且p3*3还没有出现的丑数
        int p5 = 0; //代表最小且p5*5还没有出现的丑数
        for (int i = 1; i < n; i++) {
            // 更新数组
            res[i] = Math.min(res[p2] * 2, Math.min(res[p3] * 3, res[p5] * 5));
            // 移动指针
            if (res[i] == res[p2] * 2) p2++;//
            if (res[i] == res[p3] * 3) p3++;
            if (res[i] == res[p5] * 5) p5++;
        }
        return res[n - 1];

    }

    public static void main(String[] args) {
        System.out.println(new Question49().nthUglyNumber(1200));
    }
}

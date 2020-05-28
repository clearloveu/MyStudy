package leetcode;

import java.util.Arrays;

/**
 * @author zg
 * @create 2020-04-24 11:25
 *
 * 俄罗斯套娃信封问题[困难]
 *
 * 给定一些标记了宽度和高度的信封，宽度和高度以整数对形式 (w, h) 出现。当另一个信封的宽度和高度都比这个信封大的时候，
 * 这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * 请计算最多能有多少个信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * 说明:
 * 不允许旋转信封。
 * 示例:
 * 输入: envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出: 3
 * 解释: 最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 *
 */
public class Test354 {
    private int maxEnvelopes(int[][] envelopes) {
        // 先根据长排序，长一样的话，再根据宽排序
        Arrays.sort(envelopes,(a,b)->{
            if (a[0]>b[0]) return 1;
            else if (a[0]<b[0]) return -1;
            else if (a[1]>b[1]) return 1;
            else if (a[1]<b[1]) return -1;
            else return 0;
        });
        // 这样排序后一个特点是：前面的信封不可能能放的下后面信封，后面信封有可能放得下前面的信封
        // 然后转化成求这个信封的最长上升子序列

        // dp[i]的含义是，在想用envelopes[i]这个信封作为最外面的信封包含前面信封时的最多信封数
        int[] dp = new int[envelopes.length];
        dp[0] = 1;
        for (int i = 1; i < envelopes.length; i++) {
            int max = 1;
            for (int j = 0; j < i; j++) {
                if (envelopes[i][0]>envelopes[j][0]&&envelopes[i][1]>envelopes[j][1])  {
                    max = Math.max(max,dp[j]+1);
                }
            }
            dp[i] = max;
        }
        int res = -1;
        // 求dp中最大的信封数
        for (int i = 0; i < envelopes.length; i++) {
            res = Math.max(res,dp[i]);
        }
        return res;
    }
}

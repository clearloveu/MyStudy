package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoguang
 * @create 2024-06-13 23:13
 *
 * 按照国际象棋的规则，皇后可以攻击与之处在同一行或同一列或同一斜线上的棋子。
 *
 * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 *
 * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
 *
 * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 */
public class Test51 {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        ArrayList<Integer> iList = new ArrayList<>();
        findQueue(n, 0, iList, res);
        return res;
    }

    private void findQueue(int n, int j, List<Integer> iList, List<List<String>> res) {
        if (j > n-1) {
            List<String> ans = new ArrayList<>();
            for (int i = 0; i < iList.size(); i++) {
                ans.add(convert(iList.get(i), n));
            }
            res.add(ans);
            return;
        }
        // 第j个皇后怎么站，i代表横坐标，j代表纵坐标
        for (int i = 0; i < n; i++) {
            // 和目前的皇后做判断,pre是皇后的横坐标，preJ是纵坐标
            boolean isTrue = true;
            for (int k = 0; k < iList.size(); k++) {
                int preI = iList.get(k);
                int preJ = k;
                if(preI == i || preJ == j || Math.abs(preI -i) == Math.abs(preJ - j)) {
                    isTrue = false;
                    break;
                }
            }
            if (isTrue) {
                iList.add(i);
                findQueue(n, j+1, iList, res);
                iList.remove(iList.size() -1);
            }
        }
    }
    private String convert(int i, int n) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int j = 0; j < i; j++) {
            stringBuilder.append(".");
        }
        stringBuilder.append("Q");
        for (int j = i+1; j < n; j++) {
            stringBuilder.append(".");
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Test51().solveNQueens(4));
    }
}

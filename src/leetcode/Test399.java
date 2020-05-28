package leetcode;

import java.util.List;

/**
 * @author zg
 * @create 2020-02-11 16:43
 *
 * 除法求值[中等]
 *
 */
public class Test399 {

    private double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // a/b相当于a和b两个点相连，判断a/c能否计算，则判断a，c两点是否相连
        // 先遍历equations，把两点关系保存起来，用邻接表保存这种关系，a/b代表a--->b，b/a代表b-->b，邻接表值代表表达式的值
        // 因为变量的顺序难以确定，在邻接表如何保存到相应位置是个问题：pass

        // 用HashMap定义这种关系，a/b，a/c，key是a，value=[b,a/b的值,c,a/c的值]，key是b，value是a，key是c，value是a
        // 但是如何计算值呢？--------->递归

        //领扣2种方法：
        //1，dfs 和 bfs
        //2，并查集解法
        return new double[2];
    }
}

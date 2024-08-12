package leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zg
 * @create 2019-12-22 15:41
 *
 * 合并区间[中等]
 *
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 *
 * 示例 2:
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 */
public class Test56 {
    // 2024.5.24
    private static int[][] merge2(int[][] intervals) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            List<Integer> temp = new ArrayList<>();
            temp.add(intervals[i][0]);
            temp.add(0);
            temp.add(i);
            list.add(temp);
            List<Integer> temp2 = new ArrayList<>();
            temp2.add(intervals[i][1]);
            temp2.add(1);
            temp2.add(i);
            list.add(temp2);
        }
        List<List<Integer>> sortList = list.stream().sorted((a, b) -> {
            if (a.get(0) > b.get(0)) return 1;
            else if (a.get(0) < b.get(0)) return -1;
            else if (a.get(1) > b.get(1)) return 1;
            else if (a.get(1) < b.get(1)) return -1;
            else return 0;
        }).collect(Collectors.toList());
        List<List<Integer>> res = new ArrayList<>();
        int start = Integer.MAX_VALUE;
        int right = 0;
        Set<Integer> qujianIndex = new HashSet<>();
        while (right < sortList.size()) {
            List<Integer> integers = sortList.get(right);
            if (integers.get(1) == 0) {
                qujianIndex.add(integers.get(2));
                start = Math.min(start, integers.get(0));
            } else {
                qujianIndex.remove(integers.get(2));
                if (qujianIndex.isEmpty()) {
                    List<Integer> objects = new ArrayList<>();
                    res.add(objects);
                    objects.add(start);
                    objects.add(integers.get(0));
                    start = Integer.MAX_VALUE;
                }
            }
            right++;
        }
        int[][] ress = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++) {
            ress[i][0] = res.get(i).get(0);
            ress[i][1] = res.get(i).get(1);
        }
        return ress;
    }








    private static int[][] merge(int[][] intervals) {

        //类似于插入排序的思想，在前面已经有序的区间中寻找合适的位置，区别是要注意区间合并，有时会合并好几个区间
        //answers是已经合并完成的区间的集合，并且按照从小到达排序
        List<List<Integer>> answers = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            //当前遍历到的区间的左坐标
            int left = intervals[i][0];
            //右左标
            int right = intervals[i][1];

            //代表当前遍历区间的左坐标最后在answers中找到的位置，-1是没找到
            int leftFound = -1;
            //代表当前遍历区间的右坐标最后在answers中找到的位置，-1是没找到
            int rightFound = -1;
            //代表当前遍历区间是否是在answers中某一个区间之前插入，true代表是，此时不需要更新answers
            boolean flag = false;
            for (int j = 0; j < answers.size(); j++) {
                //如果都找到相应的位置，则退出循环
                if (leftFound != -1 && rightFound != -1) break;
                //如果左坐标没找到位置
                if (leftFound == -1) {
                    //找到相应位置，leftFound=rightFound=j，在answers中该区间的位置前插入当前遍历区间（flag = true）
                    //例如：answers = [[1,4]] 当前遍历的区间为[0,0]
                    if (left < answers.get(j).get(0) && right < answers.get(j).get(0)) {
                        leftFound = j;
                        rightFound = j;
                        flag = true;
                    }
                    //找到相应位置，leftFound=rightFound=j，但需要更新answers中该区间
                    //例如：answers = [[1,4]] 当前遍历的区间为[0,2]
                    if (left <= answers.get(j).get(0) || left <= answers.get(j).get(1)) {
                        leftFound = j;
                        //尝试在该区间找右坐标位置
                        if (right <= answers.get(j).get(1)) rightFound = j;
                    }
                } else if (right < answers.get(j).get(0)) {  //如果比answers中遍历的区间的左坐标小，则右坐标属于前面一个区间
                    rightFound = j - 1;
                } else if (right > answers.get(j).get(1)) continue;//如果没找到位置，继续循环
                else rightFound = j;//找到位置
            }
            //左右坐标均没有找到相应的位置，则直接在answers末尾加入当前区间
            if (leftFound == -1) {
                List<Integer> answer = new ArrayList<>();
                answer.add(left);
                answer.add(right);
                answers.add(answer);
            }//左右坐标在同一个区间，更新answers中该区间
            else if (leftFound == rightFound) {
                //在answers中该区间的位置前插入当前遍历区间
                if (flag) {
                    List<Integer> answer = new ArrayList<>();
                    answer.add(left);
                    answer.add(right);
                    answers.add(leftFound, answer);
                } else { //需要更新answers中该区间
                    //更新区间的左坐标
                    int leftReally = Integer.min(answers.get(leftFound).get(0), left);
                    int rightReally = Integer.max(answers.get(rightFound).get(1), right);
                    List<Integer> answer = new ArrayList<>();
                    answer.add(leftReally);
                    answer.add(rightReally);
                    answers.set(leftFound, answer);
                }
            }//左右坐标不再同一个区间，合并区间,且右坐标找到了位置
            else if (rightFound != -1) {
                //合并区间的左坐标
                int leftReally = Integer.min(answers.get(leftFound).get(0), left);
                //合并区间的右坐标
                int rightReally = Integer.max(answers.get(rightFound).get(1), right);
                List<Integer> answer = new ArrayList<>();
                answer.add(leftReally);
                answer.add(rightReally);
                //删除从leftFound到rightFound的区间
                //暴力删O(n2)
                for (int k = leftFound; k <= rightFound; k++) {
                    answers.remove(leftFound);
                }
//                //非暴力删O(n)  有问题，没有改正！
//                int index = leftFound-1;
//                for (int k = rightFound+1;k<answers.size();k++){
//                    index +=1 ;
//                    answers.set(index,answers.get(k));
//                }
//                if (index<rightFound){
//                    for (int k = rightFound;k>index;k--){
//                        answers.remove(k);
//                    }
//                }
                answers.add(leftFound, answer);

            }//右左标没找到位置，直接删除从leftFound到末尾的区间
            else {
                //合并区间的左坐标
                int leftReally = Integer.min(answers.get(leftFound).get(0), left);
                //合并区间的右坐标
                int rightReally = right;
                List<Integer> answer = new ArrayList<>();
                answer.add(leftReally);
                answer.add(rightReally);
                //暴力删
                for (int k = answers.size() - 1; k >= leftFound; k--) {
                    answers.remove(leftFound);
                }
                answers.add(leftFound, answer);
            }
        }
        int length = answers.size();
        int[][] results = new int[length][];
        for (int i = 0; i < length; i++) {
            int[] result = new int[2];
            result[0] = answers.get(i).get(0);
            result[1] = answers.get(i).get(1);
            results[i] = result;
        }
        return results;
    }

    public static void main(String[] args) {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        int[][] intervals2 = {{1,4},{0,0}};
        int[][] intervals3 = {{2,3},{4,6},{5,7},{3,4}};
        int[][] answers = merge2(intervals);
        System.out.println(Arrays.deepToString(answers));
    }
}

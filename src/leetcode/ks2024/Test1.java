package leetcode.ks2024;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhaoguang
 * @create 2024-04-22 00:54
 */
public class Test1 {
    public static List<List<Integer>> threeSum(int[] nums) {
//        nums = Arrays.stream(nums).boxed().distinct().sorted().mapToInt(Integer::intValue).toArray();
        Arrays.sort(nums);
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> orDefault = map.getOrDefault(nums[i], new ArrayList<>());
            orDefault.add(i);
            map.put(nums[i], orDefault);
        }
        List<List<Integer>> res = new ArrayList<>();
        int lastI = 0;
        for (int i = 0; i < nums.length; i++) {
            int ii = nums[i];
            if (ii > 0) {
                break;
            }
            if (i == 0) {
                lastI = nums[i];
            } else if (lastI == nums[i]) {
                continue;
            } else {
                lastI = nums[i];
            }
            int lastJ = 0;
            for (int j = i + 1; j < nums.length; j++) {
                int jj = nums[j];
                if (j == i+1) {
                    lastJ = jj;
                } else if (lastJ == jj) {
                    continue;
                } else {
                    lastJ = jj;
                }
                if (map.containsKey(0 - ii - jj)) {
                    List<Integer> integers = map.get(0 - ii - jj);
                    int finalJ = j;
                    List<Integer> integers1 = integers.stream().filter(aa -> aa > finalJ).findFirst().map(a -> Stream.of(ii, jj, 0 - ii - jj).collect(Collectors.toList())).orElse(null);
                    if (integers1 != null) {
                        res.add(integers1);
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a = {-1,0,1,2,-1,-4};
        System.out.println(threeSum(a));
    }
    //        Map<Integer, Integer> collect = Arrays.stream(nums).boxed().collect(Collectors.toMap(a -> a, a -> a, (a, b) -> a));
//    nums = Arrays.stream(nums).boxed().distinct().sorted().mapToInt(Integer::intValue).toArray();

}

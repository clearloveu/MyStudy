package niuke;

import java.util.ArrayList;
import java.util.List;


public class Main2 {
    public static void main(String[] args) {
        int[] arr = {1,1,1,0,0,0,1,1,1,1,0};
        System.out.println(GetMaxConsecutiveOnes(arr, 2));

    }

    public static int GetMaxConsecutiveOnes (int[] arr, int k) {
        List<Integer> ver = new ArrayList<>();
        List<Integer> distance = new ArrayList<>();
        int left = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]!=0) {
                left = i;
                break;
            }
        }
        int right = -1;
        for (int i = arr.length-1; i >= 0 ; i--) {
            if (arr[i]!=0){
                right = i;
                break;
            }
        }
        boolean isOne = true;
        int len = 0;
        for (int i = left; i <=right ; i++) {
            if (isOne) {
                if (arr[i] == 1){
                    len++;
                } else {
                    ver.add(len);
                    isOne = false;
                    len = 1;
                }
            } else {
                if (arr[i] == 0){
                    len++;
                } else {
                    distance.add(len);
                    isOne = true;
                    len = 1;
                }
            }
        }
        ver.add(len);
        distance.add(arr.length - right);
        // 暴力
        int max = Math.max(ver.get(0) + k, ver.get(ver.size()-1) + k);
        for (int i = 0; i < ver.size(); i++) {
            int temp = conculate(ver, distance, i, k);
            max = Math.max(max, temp);
        }
        return max;
    }

    private static int conculate(List<Integer> ver, List<Integer> distance, int index, int k) {
        int res = 0;
        for (int i = index; i < ver.size(); i++) {
            res += ver.get(index);
            k -= distance.get(index);
            if (k>0) res +=distance.get(index);
            else {
                res += k+distance.get(index);
                break;
            }
        }
        return res;
    }

}

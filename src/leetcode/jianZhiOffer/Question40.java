package leetcode.jianZhiOffer;

import java.util.PriorityQueue;

/**
 * @author zg
 * @create 2020-03-08 20:09
 *
 * 最小的k个数[简单]
 *
 */
public class Question40 {
    private int[] getLeastNumbers(int[] arr, int k) {
        if(k==0) return new int[0];
        PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        for(int i =0;i<arr.length;i++){
            pq.add(arr[i]);
        }
        int[] res = new int[k];
        int index = 0;
        while(k!=0){
            k--;
            res[index] = pq.poll();
            index +=1;
        }
        return res;
    }
}

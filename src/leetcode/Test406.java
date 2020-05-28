package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zg
 * @create 2020-02-13 13:11
 *
 * 根据身高重建队列[中等]
 *
 * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
 *
 * 注意：
 * 总人数少于1100人。
 *
 * 示例
 * 输入:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * 输出:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 *
 */
public class Test406 {
    private int[][] reconstructQueue(int[][] people) {
        //（h，k）中k的大小只由大于等于h的数决定，所以先从最大的数开始重建队列

        //先根据身高排序，由大到小,如果身高一致，则k小的排在前面，因为在重建队列时，身高一致时，k小的一定排在k大的前面，比如：
        // ((9,0)(8,0)(8,1)) ，故把k小的排在前面，在重建队列是先排
        //这里我用了归并排序，可以使用官方API：Arrays.sort()
        mergeSort(people,0,people.length-1);
        //然后(h，k)根据k，插入合适的位置,因为此时新建队列中只有比h大的或等于h的数，k就是此时h应该在的位置
        List<List> answers = new ArrayList<List>();

        for (int[] person : people) {
            List<Integer> answer = new ArrayList<>();
            answer.add(person[0]);
            answer.add(person[1]);
            answers.add(person[1], answer);
        }
        int[][] answersArray = new int[people.length][2];
        for(int i=0;i<people.length;i++){
            answersArray[i][0] = (int) answers.get(i).get(0);
            answersArray[i][1] = (int) answers.get(i).get(1);
        }
        return answersArray;


    }

    //归并排序
    private static void mergeSort(int[][] a, int start, int end){
        if(start<end){//当子序列中只有一个元素时结束递归
            int mid=(start+end)/2;//划分子序列
            mergeSort(a, start, mid);//对左侧子序列进行递归排序
            mergeSort(a, mid+1, end);//对右侧子序列进行递归排序
            merge(a, start, mid, end);//合并
        }

    }

    //两路归并算法，两个排好序的子序列合并为一个子序列
    private static void merge(int[][] a,int left,int mid,int right){
        int[][] tmp=new int[a.length][2];//辅助数组
        int p1=left,p2=mid+1,k=left;//p1、p2是检测指针，k是存放指针

        while(p1<=mid && p2<=right){
            if(a[p1][0]>a[p2][0])
                tmp[k++]=a[p1++];
            else if(a[p1][0]<a[p2][0])
                tmp[k++]=a[p2++];
            else if(a[p1][1]<a[p2][1])      //如果h一样，则按照k排序
                tmp[k++]=a[p1++];
            else tmp[k++]=a[p2++];
        }

        while(p1<=mid) tmp[k++]=a[p1++];//如果第一个序列未检测完，直接将后面所有元素加到合并的序列中
        while(p2<=right) tmp[k++]=a[p2++];//ͬ同上

        //复制回原素组
        for (int i = left; i <=right; i++)
            a[i]=tmp[i];
    }
}

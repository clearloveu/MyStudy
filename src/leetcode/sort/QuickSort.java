package leetcode.sort;

/**
 * @author zg
 * @create 2020-01-11 15:20
 *
 * 快速排序
 *
 */
public class QuickSort {

    //这里是闭区间，即[start,end]之间进行快速排序
    public static void quickSort(int[] nums, int start, int end){
        //递归基
        if (end-start<1) return;

        //递归体
        int mi = partition(nums,start,end);
        quickSort(nums,start,mi-1);
        quickSort(nums,mi+1,end);
    }




    private static  int partition(int[] nums, int start, int end){
        //随机取一个轴点
        int randomIndex = (int)(Math.random()*(end-start)+start);
        int pivot = nums[randomIndex];
        nums[randomIndex] = nums[start];
//        nums[start] = pivot;


        //参考快速排序的思想
        while (start<end){
            while ((start<end && pivot<=nums[end])) end -=1;
            nums[start] = nums[end];
            while (start<end && pivot>=nums[start]) start +=1;
            nums[end] = nums[start];
        }
        nums[start] = pivot;
        return start;
    }

    public static void main(String[] args) {
        int[] a = { 49, 38, 65, 97, 76, 13, 27, 50 };
        int[] b = {10,6,13,41,5,6,61,6,6,6,6,16,7,18,6,10};
        quickSort(a, 0, a.length-1);
//        System.out.println("排序好的数组：");
        for (int e : a)
            System.out.print(e+" ");
    }
}

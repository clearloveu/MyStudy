package leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author zg
 * @create 2020-02-16 9:32
 *
 * 任务调度器[中等]                     [未完成]
 *
 * 给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。任务可以以任意顺序执行，
 * 并且每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
 *
 * 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 *
 * 你需要计算完成所有任务所需要的最短时间。
 *
 * 示例 1：
 * 输入: tasks = ["A","A","A","B","B","B"], n = 2
 * 输出: 8
 * 执行顺序: A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
 *
 *
 * 注：
 * 	任务的总个数为 [1, 10000]。
 * 	n 的取值范围为 [0, 100]。
 *
 */
public class Test621 {
    //以下是错误解法，正确解法参考领扣题解：https://leetcode-cn.com/problems/task-scheduler/solution/ren-wu-diao-du-qi-by-leetcode/
    private int leastInterval(char[] tasks, int n) {
        // 遍历一遍tasks,Map存储，key是任务，value是该任务的次数
        Map<Character,Integer> map=new HashMap<>();
        int[] cpu = new int[26];//存储的是cpu执行任务最新的时间
        for(char temp:tasks){
            map.put(temp,map.getOrDefault(temp,0)+1);
        }

        //优先级队列存储Map中键值对，按照value降序排列,若value相同，则按照任务执行的最新时间降序排序
        PriorityQueue<Character> pq = new PriorityQueue<Character>((a, b)-> !map.get(b).equals(map.get(a)) ?map.get(b)-map.get(a):cpu[a-'A']-cpu[b-'A']);
        for(Character temp:map.keySet()){
            pq.add(temp);
        }
        //每次都从堆中取堆顶的任务处理，因为取出来后，value-1，再次add进去，如果有和他相同的value的key2的话，则key2会在key1上面，满足如果value相同，会按取的时间长短
        int time = 0;

        while(true){
            Character key = pq.poll();
            if(map.get(key)==0) break; //代表所有任务都完成
            int lastKeyTime = cpu[key-'A'];
            if(lastKeyTime==0 || time-lastKeyTime>=n){
                time+=1;
            }else{
                int wait = n-(time-lastKeyTime)+1;
                time +=wait;
            }
            cpu[key-'A'] = time;  //更新cpu执行任务最新的时间
            map.put(key,map.get(key)-1); //更新任务的次数
            pq.add(key); //重新插入堆中

        }
        return time;
    }

    public static void main(String[] args) {
        char[] tasks = {'A','B','C','D','E','A','B','C','D','E'};
        System.out.println(new Test621().leastInterval(tasks,4));
    }
}

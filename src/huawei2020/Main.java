package huawei2020;

import java.io.*;
import java.util.*;

/**
 * @author zg
 * @create 2020-03-31 22:37
 */
public class Main {
    //key：交易的转钱方；value：转钱方转给的人
    private TreeMap<Integer, List<Integer>> map;
    //循环转账的路径最大长度
    private int maxPath ;
    //循环转账的路径最小长度
    private int minPath;
    // 答案
    private Map<Integer,List<List<Integer>>> res;
    // 循环路径的数量
    int num = 0;

    public Main(int minPath,int maxPath){
        map = new TreeMap<>();
        res = new HashMap<>();
        this.maxPath = maxPath;
        this.minPath = minPath;
        for (int i = minPath; i <= maxPath; i++) {
            List<List<Integer>> list = new ArrayList<>();
            res.put(i,list);
        }
    }

    /**
     * 处理map，找到循环转账的路径
     */
    private void dealMap(){
        //先对map排序，为了输出的顺序一致
        for (Integer key: map.keySet()) {
            List<Integer> list= map.get(key);
            Collections.sort(list);
        }

        for (Integer key: map.keySet()) {
            List<Integer> currentPath = new ArrayList<>();
            Set<Integer> set = new HashSet<>();
            searchPath(key,currentPath,key,set);
        }
//        System.out.println(num);
//        for (Integer key: res.keySet()) {
//            List<List<Integer>> lists= res.get(key);
//            for (int i = 0;i<lists.size();i++){
//                System.out.println(lists.get(i));
//            }
//        }
    }

    /**
     * dfs搜索
     * @param currentKey map中的key，代表当前的转钱方
     * @param currentPath 代表当前路径中的id序列
     * @param src 代表最开始的id
     * @param set 代表已经出现的id
     */
    private void searchPath(int currentKey,List<Integer> currentPath,int src,Set<Integer> set){
        int len = currentPath.size();
        // 递归基
        if (len!=0&&currentKey==src){
            if (len>=minPath&&len<=maxPath){
                num++;
                List<List<Integer>> list = res.get(len);
                List<Integer> temp = new ArrayList<>(currentPath);
                list.add(temp);
                res.put(len,list);
            }
            // 无论是否是有效路径，都返回
            return;
        }
        // 遍历到已经遍历过的的id，除了src
        if (set.contains(currentKey)) return;
        //如果当前路径已经超过最长路径长度，也返回
        if (len>=maxPath) return;

        List<Integer> list = map.get(currentKey);
        // 如果此时currentKey的id没有转账，则不dfs搜索
        if (list!=null){
            currentPath.add(currentKey);
            set.add(currentKey);
            for (int i = 0; i < list.size(); i++) {
                int nextIndex = list.get(i);
                if (nextIndex>=src)
                    searchPath(nextIndex,currentPath,src,set);

            }
            currentPath.remove(len);
            set.remove(currentKey);
        }

    }





    /**
     * 读入TXT文件，保存数据到map中
     * @param pathname 代表文件名
     */
    private void readFile(String pathname) {
        try (FileReader reader = new FileReader(pathname);
             BufferedReader br = new BufferedReader(reader) // 建立一个对象，它把文件内容转成计算机能读懂的语言
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                String[] strings = line.split(",");
                if (strings.length>0) {
                    int giveIndex = Integer.parseInt(strings[0]);
                    int receiveIndex = Integer.parseInt(strings[1]);
//                  int[] ints = new int[2];
//                  ints[0] = Integer.parseInt(strings[1]);
//                  ints[1] = Integer.parseInt(strings[2]);
                    List<Integer> list;
                    if (map.containsKey(giveIndex)) {
                        list = map.get(giveIndex);
                        list.add(receiveIndex);
                    } else {
                        list = new ArrayList<>();
                        list.add(receiveIndex);
                    }
                    map.put(giveIndex, list);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将答案写入TXT文件
     * @param fileName 代表文件名
     */
    private   void writeFile(String fileName) {
        File writeName = new File(fileName);
        try {
//            writeName.createNewFile(); // 创建新文件,有同名的文件的话直接覆盖
            try (FileWriter writer = new FileWriter(writeName); BufferedWriter out = new BufferedWriter(writer)) {
                out.write(num+"\r\n");
//            for (Integer key: res.keySet()) {
//                List<List<Integer>> lists= res.get(key);
//                for (int i = 0;i<lists.size();i++){
//                    System.out.println(lists.get(i));
//                }
//            }
                for (Integer key:res.keySet()) {
                    List<List<Integer>> lists= res.get(key);
                    for (int i = 0;i<lists.size();i++){
                        List<Integer> list = lists.get(i);
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int j = 0;j<list.size();j++){
                            stringBuilder.append(list.get(j)).append(",");
                        }
                        stringBuilder.deleteCharAt(stringBuilder.length()-1);
                        out.write(stringBuilder.toString()+"\r\n");
                    }
                }
                out.flush(); // 把缓存区内容压入文件
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {

        Main main = new Main(3,7);
        main.readFile("src/huawei2020/test_dataBig1.txt");
        main.dealMap();
        main.writeFile("src/huawei2020/myResult.txt");

    }

}

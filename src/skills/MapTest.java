package skills;

import java.util.*;

/**
 * @author zg
 * @create 2020-04-24 20:33
 *
 * TreeMap 按值倒序
 *
 */
public class MapTest {

    public static void main(String[]args){
        Map<String,String> map = new TreeMap<>();

        map.put("zdef","rfgh");
        map.put("asrg","zfg");
        map.put("rgd","dfgh");
        map.put("cbf","gddf");
        //将Map转为List
        List<Map.Entry<String,String>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, String>>() {
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                return  o2.getValue().compareTo(o1.getValue());
            }
        }); //重新排序
        //运用lambda表达式
        //Collections.sort(list,((o1, o2) -> o2.getValue().compareTo(o1.getValue())));
        for(Map.Entry<String,String> entry:list){
            System.out.println("key:"+entry.getKey()+",:value:"+entry.getValue());
        }
        //输出（按值倒序）
        //key:asrg,:value:zfg
        //key:zdef,:value:rfgh
        //key:cbf,:value:gddf
        //key:rgd,:value:dfgh
    }

}

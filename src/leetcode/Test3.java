package leetcode;

/**
 * @author zg
 * @create 2019-12-09 10:23
 *
 * 无重复字符的最长字串[中等]                          [较长时间，需要重新思考下]
 *
 *
 *给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1
 *
 */
public class Test3 {

    private static int lengthOfLongestSubstring(String s) {
        // 第一次尝试
//        if (s.equals("")) return 0;
//        //双指针思想(滑动窗口)
//        int begin = 0; //无重复子串的开头
//        int last = 1 ;   //无重复子串的结尾
//
//        Map<Character , Integer> map = new HashMap<>();
//        map.put(s.charAt(0),0);
//        int noRepeatLongest = 1;
//        while(last < s.length()){
//            if (last==begin) {
//                last+=1;
//                map.put(s.charAt(begin),begin);
//                continue;
//            }
//            char var = s.charAt(last);
//            if(map.get(var)==null){   //当前子串的当前字符不重复
//                map.put(var,last);
//                last+=1;
//            }else {                    //当前字符与前面的该字符重复
//                int beforeIndex = map.get(var);  //前面该字符的序号
//                int currentNoRepeat = last-begin;     //此时当前无重复子串的长度
////                if (currentNoRepeat>noRepeatLongest) noRepeatLongest= currentNoRepeat;
//                noRepeatLongest = Math.max(noRepeatLongest, currentNoRepeat);
//                int temp = begin;
//                begin = beforeIndex+1;     //快速右移到前面重复的该字符的右边一位
//                //去除掉当前无重复子串不包含的字符，以免影响后面的无重复子串的判断
//                for (int i =temp; i<begin ; i++){
//                    map.remove(s.charAt(i));
//                }
//            }
//        }
//        //经过上面的循环，此时last=s.length()才会跳出循环，last-1才是最后的字符的序号
//        if (last-begin>noRepeatLongest){
//            noRepeatLongest = last - begin  ;
//        }
//        return noRepeatLongest;



        // //滑动窗口思想
        // int answer = 0;
        // int left = 0;
        // int right = 0;
        // Map<Character,Integer> windows = new HashMap<>();//记录每个出现的字符的次数
        // boolean flag = false;//是否移动left指针，一旦出现重复字符就移动
        // while(right< s.length()){
        //     char temp = s.charAt(right);
        //     //如果在窗口[left,right]中出现字符只有一次
        //     if(windows.get(temp)==null||windows.get(temp)==0){
        //         windows.put(temp,1);
        //         answer = Math.max(right-left+1,answer);
        //     }else{  //出现重复字符
        //         windows.put(temp,2);
        //         flag = true; //移动left
        //     }
        //     while(flag){
        //         char leftChar = s.charAt(left);
        //         if(windows.get(leftChar)==2){   //找到重复字符
        //             flag = false;
        //             windows.put(leftChar,1);
        //         }else windows.put(leftChar,0);
        //         left +=1;
        //     }
        //     right +=1;
        // }
        // return answer;




        //滑动窗口思想
        if(s.length()==0) return 0;
        int answer = 0;
        int left = 0;
        int right = 0;
        //优化：map--->数组
        int[] windows = new int[128];//记录每个出现的字符的次数
        boolean flag = false;//是否移动left指针，一旦出现重复字符就移动
        while(right< s.length()){
            char temp = s.charAt(right);
            //如果在窗口[left,right]中出现字符只有一次
            if(windows[temp]==0){
                windows[temp] =1;
                answer = Math.max(right-left+1,answer);
            }else{  //出现重复字符
                windows[temp] =2;
                flag = true; //移动left
            }
            while(flag){
                char leftChar = s.charAt(left);
                if(windows[leftChar] ==2){   //找到重复字符
                    flag = false;
                    windows[leftChar] =1;
                }else windows[leftChar] =0;
                left +=1;
            }
            right +=1;
        }
        return answer;
    }


    public static void main(String[] args) {
        String s = "abba" ;
        String s2 = "abcabcbb" ;
        String s3 = "au" ;
        String s4 = "aabaab!bb";
        String s5 = "dvdf";
        String s6 = "";
        int answer = lengthOfLongestSubstring(s6);
        System.out.println(answer);
    }
}

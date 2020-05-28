package leetcode.jianZhiOffer;

/**
 * @author zg
 * @create 2020-02-21 10:22
 *
 * 替换空格[简单]
 *
 */
public class Question5 {
    public String replaceSpace(String s) {
        // 关键：String和StringBuilder的区别
        StringBuilder sb = new StringBuilder();
        for(int i =0;i<s.length();i++){
            if(s.charAt(i)!=' ') sb.append(s.charAt(i));
            else sb.append("%20");
        }
        return sb.toString();
    }
}

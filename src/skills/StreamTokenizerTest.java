package skills;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * @author zg
 * @create 2020-04-27 11:59
 */
public class StreamTokenizerTest {
    public static void main(String[] args) throws IOException {
        StreamTokenizer input = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

        // 为了从流中获取标记，可以调用StreamTokenizer的nextToken()方法。
        //调用nextToken()方法以后，如果标记是字符串，可用 String s=st.sval,如果是整数用 int n=(int) st.nval得到

        input.nextToken();  //将Scanner改成流输入，否则会超时或者超内存
        int n = (int) input.nval;  // input.navl默认解析出的格式是double

        input.nextToken();
        String s=input.sval;


        // 注意：StreamTokenizer没有像in.hasNext()的判断是否后面有值的方法，所以无法在类似华为的题目中使用
    }
}

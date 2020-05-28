package interview.IO;

import java.io.*;

/**
 * @author zg
 * @create 2020-02-29 18:31
 */
public class FileIO {


    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        File f = new File("src/interview/IO/abc.txt");           //得从src开始找
        BufferedReader reader = null;
        try{                                                              //注意2个try catch
            reader = new BufferedReader(new FileReader(f));               //字符缓冲流
            char[] temp = new char[1024];
            int res = -1;
            try{
                while((res=reader.read(temp))!=-1){                     //注意括号
                    sb.append(temp);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{                                                          //finally关闭流
            try{
                if(reader!=null) reader.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println(sb.toString());
    }
}

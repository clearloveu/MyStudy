package interview.FileSearch;

import java.io.File;

/**
 * @author zg
 * @create 2020-03-03 10:57
 */
public class fileSearch {


    public static void main(String[] args) {
        File file = new File("F:/test");
        File[] files = file.listFiles();
        readFile(files);
    }

    private static void readFile(File[] files){
        if(files==null) return;

        for (File f:files){
            if (f.isFile()) System.out.println(f.getName());
            if (f.isDirectory()) readFile(f.listFiles());
        }



    }

}

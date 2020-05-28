package leetcode;

/**
 * @author zg
 * @create 2019-12-25 11:05
 *
 * 单词搜索[中等]
 *
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 *
 *  示例:
 *
 * board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 *
 * 给定 word = "ABCCED", 返回 true.
 * 给定 word = "SEE", 返回 true.
 * 给定 word = "ABCB", 返回 false.
 *
 */
public class Test79 {
    static char[][] mesh;
    private static boolean exist(char[][] board, String word) {
        mesh = board;
        boolean flag = false;

        for (int i = 0 ; i<board.length;i++){
            for (int j = 0 ; j<board[i].length;j++){
                if (board[i][j]==word.charAt(0)) {
                    //修改此处位置的字符，以防止下面递归时再次搜索到这个位置
                    mesh[i][j] = '1';
                    flag = isValid(i,j,1,word);
                    if (flag) return true;
                    //回溯到原状态
                    mesh[i][j] = word.charAt(0);
                }
            }
        }


        return flag;
    }


    private static boolean isValid(int i ,int j ,int currentIndex,String word){
        //递归基
        if (currentIndex>=word.length()) return true;

        //递归体
        //往上方搜索
        if (i>0 && mesh[i-1][j]==word.charAt(currentIndex)){
            //修改此处位置的字符，以防止下面递归时再次搜索到这个位置
            mesh[i-1][j] = '1';
            boolean flag = isValid(i-1,j,currentIndex+1,word);
            if (flag) return true;
            //回溯到原状态
            mesh[i-1][j] = word.charAt(currentIndex);
        }
        //往下方搜索
        if (i<mesh.length-1 && mesh[i+1][j]==word.charAt(currentIndex)){
            //修改此处位置的字符，以防止下面递归时再次搜索到这个位置
            mesh[i+1][j] = '1';
            boolean flag = isValid(i+1,j,currentIndex+1,word);
            if (flag) return true;
            //回溯到原状态
            mesh[i+1][j] = word.charAt(currentIndex);
        }
        //往左边搜索
        if (j>0 && mesh[i][j-1]==word.charAt(currentIndex)){
            //修改此处位置的字符，以防止下面递归时再次搜索到这个位置
            mesh[i][j-1] = '1';
            boolean flag = isValid(i,j-1,currentIndex+1,word);
            if (flag) return true;
            //回溯到原状态
            mesh[i][j-1] = word.charAt(currentIndex);
        }
        //往右边搜索
        if (j<mesh[i].length-1 && mesh[i][j+1]==word.charAt(currentIndex)){
            //修改此处位置的字符，以防止下面递归时再次搜索到这个位置
            mesh[i][j+1] = '1';
            boolean flag = isValid(i,j+1,currentIndex+1,word);
            if (flag) return true;
            //回溯到原状态
            mesh[i][j+1] = word.charAt(currentIndex);
        }
        //4个方向都搜索失败了
        return false;
    }

    public static void main(String[] args) {
        char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "ABCCED";
        String word2 = "SEE";
        String word3 = "ABCB";
        System.out.println(exist(board,word));
    }




}




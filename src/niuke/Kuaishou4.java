package niuke;

/**
 * @author zg
 * @create 2020-03-19 19:33
 *
 * 快手笔试4.12
 *
 */
public class Kuaishou4 {
    static int res = -1;
    public static int GetMaxStaffs (char[][] pos) {

        recursion(pos,0);
        return  res;
    }

    private static void recursion(char[][] pos,int index) {
        //递归基
        if (index == pos.length * pos[0].length) {
            int count = 0;
            for (int i = 0; i < pos.length; i++) {
                for (int j = 0; j < pos[0].length; j++) {
                    if (pos[i][j] == '.') count++;
                }
            }
            res = Math.max(res, count);
            return;
        }
        int i = index / pos[0].length;
        int j = index % pos[0].length;
        if (pos[i][j] == '.') {
            // 保留该位置
            boolean flagA = false;
            boolean flagB = false;
            //向下
            if (i < pos.length - 1 && pos[i + 1][j] == '.') {
                pos[i+1][j]='*';
                flagA = true;
            }
            //向右
            if (j < pos[0].length - 1 && pos[i][j + 1] == '.') {
                pos[i][j + 1]='*';
                flagB = true;
            }
            recursion(pos,index+1);
            //恢复现场
            if (flagA) pos[i+1][j]='.';
            if (flagB) pos[i][j + 1] = '.';

            //不保留
            pos[i][j] = '*';
            recursion(pos,index+1);
            pos[i][j] = '.';
        }else recursion(pos,index+1);
    }

    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
        char[][] pos = {{'*','.','*','*','.'},{'*','.','*','*','*'},{'*','.','*','*','.'}};
        System.out.println(GetMaxStaffs(pos));

    }
}

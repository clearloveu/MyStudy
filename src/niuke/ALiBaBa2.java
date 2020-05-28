package niuke;

/**
 * @author zg
 * @create 2020-03-25 15:33
 *
 * 3.25笔试 第二题
 *
 */
public class ALiBaBa2 {
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        int n = in.nextInt();
//        int m = in.nextInt();
//        int q  = in.nextInt();

        int n = 3;
        int m = 3;
        int q = 2;
        int[][] A = {{1,0,3},{2,3,0},{0,0,1}};
        int[][] query = {{1,1},{2,1}};

//        int[][] A = new int[n][m];
//        int[][] query = new int[q][2];
//        for (int i = 0;i<n;i++){
//            for (int j=0;j<m;j++){
//                A[i][j] = in.nextInt();
//            }
//        }
//        for (int i =0;i<q;i++){
//            for (int j=0;j<2;j++){
//                query[i][j] = in.nextInt();
//            }
//        }
        //行
        for (int i=0;i<n;i++){
            int[] res = findCha(A[i]);
            if (res[0]!=Integer.MAX_VALUE){
                int start = res[1];
                int cha = res[0];
                for (int j=0;j<m;j++){
                    A[i][j] = start+j*cha;
                }
            }
        }
        //列
        for(int i=0;i<m;i++){
            int[] tempHang = new int[n];
            for (int j=0;j<n;j++){
                tempHang[j] = A[j][i];
            }
            int[] res = findCha(tempHang);
            if (res[0]!=Integer.MAX_VALUE) {
                int start = res[1];
                int cha = res[0];
                for (int j=0;j<n;j++){
                    A[j][i] = start+j*cha;
                }
            }
        }
//        //行
//        for (int i=0;i<n;i++){
//            int[] res = findCha(A[i]);
//            if (res[0]!=Integer.MAX_VALUE){
//                int start = res[1];
//                int cha = res[0];
//                for (int j=0;j<m;j++){
//                    A[i][j] = start+j*cha;
//                }
//            }
//        }
        for (int i = 0;i<q;i++){
            int hang = query[i][0]-1;
            int lie = query[i][1]-1;
            if (A[hang][lie]!=0) System.out.println(A[hang][lie]);
            else System.out.println("Unknown");
        }





    }



    public static int[] findCha(int[] hang){
        int count = 0;
        int firstNum = 0;
        int firstIndex = 0;
        int secondNum = 0;
        int secondIndex = 0;
        int[] res = new int[2];
        res[0] = Integer.MAX_VALUE;
        for (int i=0;i<hang.length;i++){
            if (hang[i]!=0) {
                if (count==0) {
                    firstNum = hang[i];
                    firstIndex = i;
                }else  if (count==1){
                    secondNum = hang[i];
                    secondIndex = i;
                }
                count++;
            }
            if (count==2) break;
        }
        if (count==2) {
            res[0] = (secondNum-firstNum)/(secondIndex-firstIndex);
            res[1] = firstNum - (firstIndex)*res[0];
        }

        return res;
    }
}

package 面试;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author faded828x
 * @date 2022/9/3
 */
public class d {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        int[][] pp = new int[3][2];
//        for(int i = 0; i < 3; i++) {
//            pp[i][0] = scanner.nextInt();
//            pp[i][1] = scanner.nextInt();
//        }
//        int[][] res = new int[3][];
//        res[0] = new int[1];
//        int max = 0;
//        int k = scanner.nextInt();
//        for(int i = 0; i < k; i++) {
//            int[][] B = new int[3][2];
//            for(int j = 0; j < 3; j++) {
//                B[j][0] = scanner.nextInt();
//                B[j][1] = scanner.nextInt();
//            }
//            if(isInside(pp, B)) {
//                int m = calculate(B);
//                if(m >= max) res = B;
//            }
//        }
//        if(res[0].length == 1) System.out.println("fail");
//        else System.out.println(res[0][0] + " " + res[0][1] + " " + res[1][0] + " " + res[1][1] + " " + res[2][0] + " " + res[2][1]);

        int L = scanner.nextInt();
        int M = scanner.nextInt();
        int[][] a = new int[M][2];
        for(int i = 0; i < M; i++) {
            a[i][0] = scanner.nextInt();
            a[i][1] = scanner.nextInt();
        }
        int[] dp = new int[L + 1];
        Arrays.sort(a, (o1, o2) -> {
            if(o1[0] != o2[0]) return o1[0] - o2[0];
            return o1[1] - o2[1];
        });
        for(int i = 1; i < L + 1; i++)
            dp[i] = M + 1;
        for(int[] aa : a) {
            int from = aa[0];
            int to = aa[1];
            int cur = dp[from - 1] + 1;
            for(int j = from; j <= to; j++) {
                dp[j] = Math.min(dp[j], cur);
            }
        }
        if(dp[L] >= M) System.out.println("fail");
        else System.out.println(dp[L]);

    }
    public static boolean isInside(int[][] A, int[][] B) {
        for(int i = 0; i < 3; i++) {
            int[] p = A[i];
            int x1 = p[0] - B[0][0];
            int y1 = p[1] - B[0][1];
            int x2 = B[1][0] - B[0][0];
            int y2 = B[1][1] - B[0][1];
            int x3 = B[2][0] - B[0][0];
            int y3 = B[2][1] - B[0][1];
            double u = (double) (x1 * y3 - x3 * y1) / (double) (x2 * y3 - x3 * y2);
            double v = (double) (x1 * y2 - x2 * y1) / (double) (x3 * y2 - x2 * y3);
            if(u < 0 || v < 0 || u + v > 1) return false;
        }
        return true;
    }
    public static int calculate(int[][] B) {
        return Math.abs(B[0][0] * B[1][1] + B[1][0] * B[2][1] + B[2][0] * B[0][1] -
                B[0][0] * B[2][1] - B[1][0] * B[0][1] - B[2][0] - B[1][1]);
    }


}



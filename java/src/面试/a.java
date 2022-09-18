package 面试;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author faded828x
 * @date 2022/8/29
 */
public class a {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int m = scanner.nextInt();
//        int k = scanner.nextInt();
//        int[][] arr = new int[n][m];
//        for(int i = 0; i < n; i++) {
//            for(int j = 0; j < m; j++) {
//                arr[i][j] = gcd(i + 1, j + 1);
//            }
//        }
//        for(int[] a : arr)
//            System.out.println(Arrays.toString(a));
//        int res = 0;
//        for(int i = 0; i <= m - k; i++) {
//            for(int j = 0; j <= n - k; j++) {
//                for(int x = i; x <= i + k - 1; x++) {
//                    for(int y = j; y <= j + k - 1; y++) {
//                        res += arr[x][y];
//                    }
//                }
//            }
//        }
//        System.out.println(res);

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int q = scanner.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for(int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        for(int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }
        Arrays.sort(a);
        Arrays.sort(b);
//        int[][] c = new int[q][2];
        for(int i = 0; i < q; i++) {
            int c1 = scanner.nextInt();
            int c2 = scanner.nextInt();
            int cnt = 1;
            int[] aa = new int[n];
            int[] bb = new int[n];
            for(int j = 0; j < n; j++) {
                aa[j] = a[j] * c1;
                bb[j] = b[j] * c2;
            }
            int res = 0;
            while(aa[n - cnt] > bb[cnt - 1]) {
                res += aa[n - cnt] - bb[cnt - 1];
                cnt++;
            }
            System.out.println(res);
        }


    }
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }




}

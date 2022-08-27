package 洛谷;

import java.util.Scanner;

/**
 * @author faded828x
 * @date 2022/8/5
 */
public class P5098 {
    // 爆搜
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] pos = new int[n][2];
        for(int i = 0; i < n; i++) {
            pos[i] = new int[2];
            pos[i][0] = scanner.nextInt();
            pos[i][1] = scanner.nextInt();
        }
        int res = 0;
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                res = Math.max(res, Math.abs(pos[i][0] - pos[j][0]) + Math.abs(pos[i][1] - pos[j][1]));
            }
        }
        System.out.println(res);
    }
}

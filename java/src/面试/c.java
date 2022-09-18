package 面试;

import java.util.Scanner;

/**
 * @author faded828x
 * @date 2022/8/31
 */
public class c {
    public static int row, col;
    public static int[][] move = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
//        String s = scanner.next();
//        String[] ss = s.split(",");
//        int[] arr = new int[ss.length];
//        for(int i = 0; i < arr.length; i++)
//            arr[i] = Integer.parseInt(ss[i]);
//        int idx = 1;
//        boolean asc = true;
//        int res = 0;
//        int tmp = 0;
//        while(idx < arr.length) {
//            if(asc) {
//                if(arr[idx] >= arr[idx - 1]) {
//                    idx++; continue;
//                }
//                asc = false;
////                res += arr[idx - 1];
//                tmp = arr[idx - 1];
//            } else {
//                if(arr[idx] <= arr[idx - 1]) {
//                    idx++; continue;
//                }
//                asc = true;
//                res += tmp - arr[idx - 1];
//            }
//            idx++;
//        }
//        System.out.println(res);


//        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.next();
        String[] ss1 = s1.split(",");
        row = Integer.parseInt(ss1[0]);
        col = Integer.parseInt(ss1[1]);
        int[][] matrix = new int[row][col];
        for(int i = 0; i < row; i++) {
            String s2 = scanner.next();
            String[] ss2 = s2.split(",");
            matrix[i] = new int[col];
            for(int j = 0; j < col; j++)
                matrix[i][j] = Integer.parseInt(ss2[j]);
        }
        boolean[][] visited = new boolean[row][col];
        int res = 0;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(matrix[i][j] == 0 || visited[i][j]) continue;
                res = Math.max(dfs(matrix, visited, i, j), res);
            }
        }
        System.out.println(res);
    }
    public static int dfs(int[][] matrix, boolean[][] visited, int x, int y) {
        int res = 1;
        visited[x][y] = true;
        for(int i = 0; i < 4; i++) {
            int xx = x + move[i][0];
            int yy = y + move[i][1];
            if(xx < 0 || xx >= row || yy < 0 || yy >= col || matrix[xx][yy] == 0 || visited[xx][yy]) continue;
            res += dfs(matrix, visited, xx, yy);
        }
        return res;
    }
}

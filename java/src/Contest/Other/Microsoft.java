package Contest.Other;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// https://leetcode-cn.com/contest/lc-vscode/
public class Microsoft {

    // 卡点AC 没打上 气死了
    public static int leastMinutes(int n) {
        if (n == 1) return 1;
        for (int i = 1; i <= 17; i++) {
            if (n <= (1 << i) && n >= ((1 << (i - 1)) + 1)) {
                return i + 1;
            }
        }
        return -1;
    }

    // AC
    public static int halfQuestions(int[] questions) {
        int len = questions.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : questions)
            map.put(n, map.getOrDefault(n, 0) + 1);
        int res = 0;
        int size = map.size();
        int[] arr = new int[size];
        int index = 0;
        for (int n : map.values())
            arr[index++] = n;
        Arrays.sort(arr);
        int count = 0;
        for (int i = size - 1; i >= 0; i--) {
            count += arr[i];
            res++;
            if (count >= len / 2)
                return res;
        }
        return -1;
    }

    // 未完 dfs
    public static int largestArea(String[] grid) {
        int[][] way = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int row = grid.length;
        int col = grid[0].length();
        int[][] girdArr = new int[row][col];
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                girdArr[i][j] = grid[i].charAt(j)-'0';
            }
        }
        boolean[][] visited = new boolean[row][col];



        return 0;
    }


    public static void main(String[] args) {
        System.out.println(leastMinutes(3));
    }
}

package BFS;
//There is an m x n grid with a ball. The ball is initially at the position [sta
//rtRow, startColumn]. You are allowed to move the ball to one of the four adjacen
//t cells in the grid (possibly out of the grid crossing the grid boundary). You c
//an apply at most maxMove moves to the ball. 
//
// Given the five integers m, n, maxMove, startRow, startColumn, return the numb
//er of paths to move the ball out of the grid boundary. Since the answer can be v
//ery large, return it modulo 109 + 7. 
//
// 
// Example 1: 
//
// 
//Input: m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
//Output: 6
// 
//
// Example 2: 
//
// 
//Input: m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
//Output: 12
// 
//
// 
// Constraints: 
//
// 
// 1 <= m, n <= 50 
// 0 <= maxMove <= 50 
// 0 <= startRow < m 
// 0 <= startColumn < n 
// 
// Related Topics 动态规划 
// 👍 146 👎 0

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class OutOfBoundaryPaths576 {
    // 思路很清晰 dp解法没看 好像也是要三重数组
    public static int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        int[][] move = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        // 第一次三重记忆化数组
        List<List<Map<Integer, Integer>>> visited = new ArrayList<>();
        for(int i = 0; i < m; i++) {
            List<Map<Integer, Integer>> list = new ArrayList<>();
            for(int j = 0; j < n; j++) {
                list.add(new HashMap<>());
            }
            visited.add(list);
        }
        return dfs(m, n, maxMove, startRow, startColumn, move, visited);
    }
    public static int dfs(int m, int n, int maxMove, int x, int y, int[][] move, List<List<Map<Integer, Integer>>> visited) {
        if(x < 0 || x == m || y < 0 || y == n) {
            return 1;
        }
        if(maxMove == 0) return 0;
        Map<Integer, Integer> map = visited.get(x).get(y);
        if(map.containsKey(maxMove))
            return map.get(maxMove);
        int res = 0;
        for(int i = 0; i < 4; i++) {
            int nextX = x + move[i][0];
            int nextY = y + move[i][1];
            res = (res + dfs(m, n, maxMove - 1, nextX, nextY, move, visited)) % 1000000007;
        }
        map.put(maxMove, res);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(findPaths(8,7,16,1,5));
    }
}
//leetcode submit region end(Prohibit modification and deletion)

package BFS;//You are given an m x n integer matrix grid, and three integers row, col, and c
//olor. Each value in the grid represents the color of the grid square at that loc
//ation. 
//
// Two squares belong to the same connected component if they have the same colo
//r and are next to each other in any of the 4 directions. 
//
// The border of a connected component is all the squares in the connected compo
//nent that are either 4-directionally adjacent to a square not in the component, 
//or on the boundary of the grid (the first or last row or column). 
//
// You should color the border of the connected component that contains the squa
//re grid[row][col] with color. 
//
// Return the final grid. 
//
// 
// Example 1: 
// Input: grid = [[1,1],[1,2]], row = 0, col = 0, color = 3
//Output: [[3,3],[3,2]]
// Example 2: 
// Input: grid = [[1,2,2],[2,3,2]], row = 0, col = 1, color = 3
//Output: [[1,3,3],[2,3,3]]
// Example 3: 
// Input: grid = [[1,1,1],[1,1,1],[1,1,1]], row = 1, col = 1, color = 2
//Output: [[2,2,2],[2,1,2],[2,2,2]]
// 
// 
// Constraints: 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 50 
// 1 <= grid[i][j], color <= 1000 
// 0 <= row < m 
// 0 <= col < n 
// 
// Related Topics 深度优先搜索 广度优先搜索 数组 矩阵 
// 👍 42 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class ColoringABorder1034 {
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        // grid[row][col]所在的连通分量
        List<int[]> connected = new LinkedList<>();
        // BFS队列
        Deque<int[]> queue = new ArrayDeque<>();
        // 起始点
        int[] start = new int[]{row, col};
        connected.add(start);
        queue.offer(start);
        int r = grid.length;
        int c = grid[0].length;
        int[][] move = new int[][]{{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        boolean[][] visited = new boolean[r][c];
        // 所在连通分量的颜色
        int co = grid[row][col];
        while (!queue.isEmpty()) {
            int[] cur = queue.remove();
            visited[cur[0]][cur[1]] = true;
            for (int i = 0; i < 4; i++) {
                int x = cur[0] + move[i][0];
                int y = cur[1] + move[i][1];
                // 在网格内 颜色相同 未访问
                if (x >= 0 && x < r && y >= 0 && y < c && grid[x][y] == co && !visited[x][y]) {
                    int[] pos = new int[]{x, y};
                    queue.offer(pos);
                    connected.add(pos);
                }
            }
        }
        // 连通分量的边界
        List<int[]> target = new LinkedList<>();
        while (!connected.isEmpty()) {
            int[] re = connected.remove(0);
            int x = re[0];
            int y = re[1];
            // 在网格边界
            if (x == 0 || x == r - 1 || y == 0 || y == c - 1) {
//                grid[x][y] = color;
                target.add(re);
            } else {
                // 四周有一块颜色不同 即为在连通分量的边界
                boolean fail = false;
                for (int i = 0; i < 4; i++) {
                    if (grid[x + move[i][0]][y + move[i][1]] != co) {
                        fail = true;
                        break;
                    }
                }
                if (fail)
                    target.add(re);
            }
        }
        // 将连通分量的边界换色
        while (!target.isEmpty()) {
            int[] re = target.remove(0);
            grid[re[0]][re[1]] = color;
        }
        return grid;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 1}, {1, 2}};
        ColoringABorder1034 coloringABorder1034 = new ColoringABorder1034();
        int[][] res = coloringABorder1034.colorBorder(grid, 0, 0, 3);
        for (int[] r : res)
            System.out.println(Arrays.toString(r));
    }
}
//leetcode submit region end(Prohibit modification and deletion)

package BFS;//You are playing a simplified PAC-MAN game on an infinite 2-D grid. You start a
//t the point [0, 0], and you are given a destination point target = [xtarget, yta
//rget], which you are trying to get to. There are several ghosts on the map with 
//their starting positions given as an array ghosts, where ghosts[i] = [xi, yi] re
//presents the starting position of the ith ghost. All inputs are integral coordin
//ates. 
//
// Each turn, you and all the ghosts may independently choose to either move 1 u
//nit in any of the four cardinal directions: north, east, south, or west or stay 
//still. All actions happen simultaneously. 
//
// You escape if and only if you can reach the target before any ghost reaches y
//ou. If you reach any square (including the target) at the same time as a ghost, 
//it does not count as an escape. 
//
// Return true if it is possible to escape, otherwise return false. 
//
// 
// Example 1: 
//
// 
//Input: ghosts = [[1,0],[0,3]], target = [0,1]
//Output: true
//Explanation: You can reach the destination (0, 1) after 1 turn, while the ghos
//ts located at (1, 0) and (0, 3) cannot catch up with you.
// 
//
// Example 2: 
//
// 
//Input: ghosts = [[1,0]], target = [2,0]
//Output: false
//Explanation: You need to reach the destination (2, 0), but the ghost at (1, 0)
// lies between you and the destination.
// 
//
// Example 3: 
//
// 
//Input: ghosts = [[2,0]], target = [1,0]
//Output: false
//Explanation: The ghost can reach the target at the same time as you.
// 
//
// Example 4: 
//
// 
//Input: ghosts = [[5,0],[-10,-2],[0,-5],[-2,-2],[-7,1]], target = [7,7]
//Output: false
// 
//
// Example 5: 
//
// 
//Input: ghosts = [[-1,0],[0,1],[-1,0],[0,1],[-1,0]], target = [0,0]
//Output: true
// 
//
// 
// Constraints: 
//
// 
// 1 <= ghosts.length <= 100 
// ghosts[i].length == 2 
// -104 <= xi, yi <= 104 
// There can be multiple ghosts in the same location. 
// target.length == 2 
// -104 <= xtarget, ytarget <= 104 
// 
// Related Topics 数组 数学 
// 👍 36 👎 0


import java.sql.SQLOutput;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class EscapeTheGhosts789 {
    // bfs失败 给的是坐标轴位置 和数组中坐标不同 换来换去很麻烦
    // 同一时刻 blocker先bfs并标记 runner后bfs,标记位不可取，无路可走即为false
    public static boolean escapeGhosts(int[][] ghosts, int[] target) {
        int left = 0, right = 0;
        int up = 0, down = 0;
        for(int[] g : ghosts) {
            right = Math.max(right, g[0]);
            left = Math.min(left, g[0]);
            down = Math.max(down, g[1]);
            up = Math.min(up, g[1]);
        }
        right = Math.max(right, target[0]);
        left = Math.min(left, target[0]);
        down = Math.max(down, target[1]);
        up = Math.min(up, target[1]);
        boolean[][] visited = new boolean[right - left + 1][down - up + 1];
        visited[0][0] = true;
        Deque<List<Integer>> queue = new ArrayDeque<>();
        queue.add(new ArrayList<>(){{add(0);add(0);}});
        Deque<List<Integer>> queue2 = new ArrayDeque<>();
        for(int[] g : ghosts) {
            int finalLeft = g[0] - left;
            int finalUp = g[1] - up;
            queue2.add(new ArrayList<>(){{add(finalLeft);add(finalUp);}});
            visited[finalLeft][finalUp] = true;
        }
        if(target[0] == left && target[1] == up) return true;
        if(visited[target[0] - left][target[1] - up]) return false;
        int[][] move = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        while(!queue.isEmpty()) {
            // BFS blockers
            int size2 = queue2.size();
            for(int i = 0; i < size2; i++) {
                List<Integer> blocker = queue2.remove();
                for(int j = 0; j < 4; j++) {
                    int nx = blocker.get(0) + move[j][0];
                    int ny = blocker.get(1) + move[j][1];
                    if(nx >= 0 && nx < right - left + 1 && ny >= 0 && ny < down - up + 1 && !visited[nx][ny]) {
                        queue2.add(new ArrayList<>(){{add(nx);add(ny);}});
                        visited[nx][ny] = true;
                    }
                }
            }
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                List<Integer> runner = queue.remove();
                for(int j = 0; j < 4; j++) {
                    int mx = runner.get(0) + move[i][0];
                    int my = runner.get(1) + move[i][1];
                    if(mx >= 0 && mx < right - left + 1 && my >= 0 && my < down - up + 1) {

//                        System.out.println(right);
//                        System.out.println(left);
//                        System.out.println(my);

                        if(mx == target[0] - left && my == target[1] - up) return !visited[mx][my];
                        if(visited[mx][my]) continue;
                        queue.add(new ArrayList<>(){{add(mx);add(my);}});
                        visited[mx][my] = true;
                    }
                }
            }
        }

        return false;
    }

    // 计算 起点和所有blocker 到target的曼哈顿距离 只有当起点的距离小于所有blocker时 才能成功逃脱
    public boolean escapeGhosts2(int[][] ghosts, int[] target) {
        int srcDis = dis(new int[]{0,0}, target);
        int minDis = Integer.MAX_VALUE;
        for(int[] g : ghosts) {
            minDis = Math.min(minDis, dis(g, target));
        }
        return srcDis < minDis;
    }
    // 曼哈顿距离
    public int dis(int[] src, int[] target) {
        return Math.abs(src[0] - target[0]) + Math.abs(src[1] - target[1]);
    }

    public static void main(String[] args) {
        int[][] ghosts = new int[][]{{-1,2},{0,1},{-2,3},{0,1},{-5,0}};
        int[] target = new int[]{-2,0};
        System.out.println(escapeGhosts(ghosts, target));
    }
}
//leetcode submit region end(Prohibit modification and deletion)

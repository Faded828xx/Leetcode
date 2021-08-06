package DFS;
//You have an undirected, connected graph of n nodes labeled from 0 to n - 1. Yo
//u are given an array graph where graph[i] is a list of all the nodes connected w
//ith node i by an edge. 
//
// Return the length of the shortest path that visits every node. You may start 
//and stop at any node, you may revisit nodes multiple times, and you may reuse ed
//ges. 
//
// 
// Example 1: 
//
// 
//Input: graph = [[1,2,3],[0],[0],[0]]
//Output: 4
//Explanation: One possible path is [1,0,2,0,3]
// 
//
// Example 2: 
//
// 
//Input: graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
//Output: 4
//Explanation: One possible path is [0,1,4,2,3]
// 
//
// 
// Constraints: 
//
// 
// n == graph.length 
// 1 <= n <= 12 
// 0 <= graph[i].length < n 
// graph[i] does not contain i. 
// If graph[a] contains b, then graph[b] contains a. 
// The input graph is always connected. 
// 
// Related Topics 位运算 广度优先搜索 图 动态规划 状态压缩 
// 👍 155 👎 0

import javax.swing.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)


class ShortestPathVisitingAllNodes847 {
    // 瞎写的 最后搬的题解
    public static int shortestPathLength(int[][] graph) {
        int start = -1;
        int min = 13;
        int len = graph.length;
        for(int i = 0; i < len; i++) {
            int[] g = graph[i];
            if (g.length < min) {
                min = g.length;
                start = i;
            }
        }
//        System.out.println(start);
        Set<Integer> visited = new HashSet<>();
        return dfs(start, graph, visited);
    }
    public static int dfs(int start, int[][] graph, Set<Integer> visited) {
        int len = graph.length;
        visited.add(start);
        if(visited.size() == len) {
            return 0;
        }
        boolean flag = false;
        int res = Integer.MAX_VALUE;
        for(int next : graph[start]) {
            if(!visited.contains(next)) {
                flag = true;
                int ne = dfs(next, graph, visited);
                if(ne == Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                }
                res = Math.min(res, ne + 1);
            }
        }
        if(!flag) {
            return Integer.MAX_VALUE;
        }
        return res;
    }

    // 官方题解
    public int shortestPathLength2(int[][] graph) {
        int n = graph.length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] seen = new boolean[n][1 << n];
        for (int i = 0; i < n; ++i) {
            queue.offer(new int[]{i, 1 << i, 0});
            seen[i][1 << i] = true;
        }

        int ans = 0;
        while (!queue.isEmpty()) {
            int[] tuple = queue.poll();
            int u = tuple[0], mask = tuple[1], dist = tuple[2];
            if (mask == (1 << n) - 1) {
                ans = dist;
                break;
            }
            // 搜索相邻的节点
            for (int v : graph[u]) {
                // 将 mask 的第 v 位置为 1
                int maskV = mask | (1 << v);
                if (!seen[v][maskV]) {
                    queue.offer(new int[]{v, maskV, dist + 1});
                    seen[v][maskV] = true;
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[][] graph = new int[][]{{1}, {0,2,4}, {1,3,4}, {2}, {1,2}};
        System.out.println(shortestPathLength(graph));
    }
}
//leetcode submit region end(Prohibit modification and deletion)

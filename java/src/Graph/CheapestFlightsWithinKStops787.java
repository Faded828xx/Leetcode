package Graph;//There are n cities connected by some number of flights. You are given an array
// flights where flights[i] = [fromi, toi, pricei] indicates that there is a fligh
//t from city fromi to city toi with cost pricei. 
//
// You are also given three integers src, dst, and k, return the cheapest price 
//from src to dst with at most k stops. If there is no such route, return -1. 
//
// 
// Example 1: 
//
// 
//Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k =
// 1
//Output: 200
//Explanation: The graph is shown.
//The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as mar
//ked red in the picture.
// 
//
// Example 2: 
//
// 
//Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k =
// 0
//Output: 500
//Explanation: The graph is shown.
//The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as mar
//ked blue in the picture.
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 100 
// 0 <= flights.length <= (n * (n - 1) / 2) 
// flights[i].length == 3 
// 0 <= fromi, toi < n 
// fromi != toi 
// 1 <= pricei <= 104 
// There will not be any multiple flights between two cities. 
// 0 <= src, dst, k < n 
// src != dst 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 动态规划 最短路 堆（优先队列） 
// 👍 324 👎 0


import java.util.Arrays;
import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)

class CheapestFlightsWithinKStops787 {

    int INF = Integer.MAX_VALUE;
    int ans = INF;

    // DFS --> TLE
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[][] g = new int[n][n];
        for(int[] gg : g)
            Arrays.fill(gg, INF / 2);
        for(int[] f : flights)
            g[f[0]][f[1]] = f[2];

        dfs(g, src, dst, k + 1, 0);
        return ans == INF ? -1 : ans;
    }

    public void dfs(int[][] g, int src, int dst, int k, int res) {
        if(k < 0) return ;
        if(src == dst) {
//            ans = Math.min(ans, res);
            ans = res;
            return ;
        }
        int n = g.length;
        for(int next = 0; next < n; next++) {
            int w = g[src][next];
            if(w == INF / 2 || res + w > ans) continue;
            dfs(g, next, dst, k - 1, res + w);
        }


    }



    // Dijkstra
    public int findCheapestPrice2(int n, int[][] flights, int src, int dst, int k) {

        int INF = Integer.MAX_VALUE;

        // 初始化图
        int[][] g = new int[n][n];
        for(int[] gg : g)
            Arrays.fill(gg, INF / 2);
        for(int[] f : flights)
            g[f[0]][f[1]] = f[2];

        int[] res = new int[n];
        int[] kArr = new int[n];
        Arrays.fill(res, INF / 2);
        res[src] = 0;


        // 堆优化
        PriorityQueue<int[]> heap = new PriorityQueue<>((l1, l2) -> l1[0] - l2[0]);
        heap.add(new int[]{0, src, 0});
        while(!heap.isEmpty()) {
            int[] top = heap.poll();
            int dis = top[0];   // src到节点的距离
            int cur = top[1];   // 节点号
            int kk = top[2];    // src到节点的步数
            if(kk == k + 2) continue;   // 超过k+1的最大步数
            if(cur == dst) return dis;  // res[cur]只在不限制下的最短路径才更新 因此返回dis

//            如果放在这更新的话 下面 dis+w 和 res[next] 比较的都是 res[next]的默认值
//            res[src] = dis;
//            kArr[src] = kk + 1;

            for(int next = 0; next < n; next++) {
                int w = g[cur][next];
                if(w == INF / 2) continue;  // 该边不存在
                if(dis + w < res[next]) {
                    heap.add(new int[]{dis + w, next, kk + 1});
                    res[next] = dis + w;    // 此时这个最短路径可能超了限制步数
                    kArr[next] = kk + 1;
                } else if(kk + 1 < kArr[next])  // 该路径不是最短 但比最短路径的步数少
                    heap.add(new int[]{dis + w, next, kk + 1});
            }

        }

        return -1;

    }


}
//leetcode submit region end(Prohibit modification and deletion)

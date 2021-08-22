package HashTable;
//有 n 个网络节点，标记为 1 到 n。
//
// 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， w
//i 是一个信号从源节点传递到目标节点的时间。 
//
// 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：times = [[1,2,1]], n = 2, k = 1
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：times = [[1,2,1]], n = 2, k = 2
//输出：-1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= k <= n <= 100 
// 1 <= times.length <= 6000 
// times[i].length == 3 
// 1 <= ui, vi <= n 
// ui != vi 
// 0 <= wi <= 100 
// 所有 (ui, vi) 对都 互不相同（即，不含重复边） 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 最短路 堆（优先队列） 
// 👍 296 👎 0

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class NetworkDelayTime743 {

    // 没AC
    public static int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] time : times) {
            int from = time[0];
            List<int[]> list = map.getOrDefault(from, new ArrayList<>());
            list.add(time);
            map.put(from, list);
        }
        boolean[] visited = new boolean[n + 1];
        // 到达节点i用时resTime[i]
        int[] resTime = new int[n + 1];
        Arrays.fill(resTime, Integer.MAX_VALUE);
        resTime[k] = 0;
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(k);
        while (!queue.isEmpty()) {
            int cur = queue.remove();
            visited[cur] = true;
            List<int[]> list = map.get(cur);
            if (list == null) continue;
            for (int[] next : list) {
                int nextNode = next[1];
                int nextTime = next[2];
                resTime[nextNode] = Math.min(resTime[nextNode], resTime[cur] + nextTime);
                if (!visited[nextNode]) {
                    queue.add(nextNode);
                }
//                int a = resTime[nextNode];
//                int b = resTime[cur] + nextTime;
//                if (a == Integer.MAX_VALUE || b <= a) {
//                    resTime[nextNode] = b;
//                    queue.add(nextNode);
//                }
            }
        }
        int res = 0;
        for (int i = 1; i < n + 1; i++)
            if (resTime[i] == Integer.MAX_VALUE && i != k) return -1;
            else res = Math.max(res, resTime[i]);
        return res;
    }

    // Dijkstra
    public static int networkDelayTime2(int[][] times, int n, int k) {
        int[][] g = new int[n + 1][n + 1];
        for(int[] t : g)
            Arrays.fill(t, Integer.MAX_VALUE / 2);
        for(int[] t : times)
            g[t[0]][t[1]] = t[2];
        boolean[] isOK = new boolean[n + 1];
        int[] res = new int[n + 1];
        Arrays.fill(res, Integer.MAX_VALUE / 2);
        res[k] = 0;
        for(int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE / 2;
            int idx = -1;
            for(int j = 1; j <= n; j++) {
                if(!isOK[j] && res[j] < min) {
                    min = res[j];
                    idx = j;
                }
            }
            if(idx == -1) return -1;
            isOK[idx] = true;
            for(int j = 1; j <= n; j++) {
                if(isOK[j]) continue;
                // 如果为MAX_VALUE 这里相加会溢出
                res[j] = Math.min(res[j], res[idx] + g[idx][j]);
            }
        }
//        for(int[] t : g)
//            System.out.println(Arrays.toString(t));
//        System.out.println(Arrays.toString(res));
//        System.out.println(Arrays.toString(isOK));
        int ans = 0;
        for(int i = 1; i <= n; i++)
            ans = Math.max(ans, res[i]);
        return ans;
    }

    public static void main(String[] args) {
        int[][] times = new int[][]{{2,1,1}, {2,3,1}, {3,4,1}};
        System.out.println(networkDelayTime2(times, 4, 2));
    }
}
//leetcode submit region end(Prohibit modification and deletion)

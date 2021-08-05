package HashTable;
//We start at some node in a directed graph, and every turn, we walk along a dir
//ected edge of the graph. If we reach a terminal node (that is, it has no outgoin
//g directed edges), we stop. 
//
// We define a starting node to be safe if we must eventually walk to a terminal
// node. More specifically, there is a natural number k, so that we must have stop
//ped at a terminal node in less than k steps for any choice of where to walk. 
//
// Return an array containing all the safe nodes of the graph. The answer should
// be sorted in ascending order. 
//
// The directed graph has n nodes with labels from 0 to n - 1, where n is the le
//ngth of graph. The graph is given in the following form: graph[i] is a list of l
//abels j such that (i, j) is a directed edge of the graph, going from node i to n
//ode j. 
//
// 
// Example 1: 
//
// 
//Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
//Output: [2,4,5,6]
//Explanation: The given graph is shown above.
// 
//
// Example 2: 
//
// 
//Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
//Output: [4]
// 
//
// 
// Constraints: 
//
// 
// n == graph.length 
// 1 <= n <= 104 
// 0 <= graph[i].length <= n 
// graph[i] is sorted in a strictly increasing order. 
// The graph may contain self-loops. 
// The number of edges in the graph will be in the range [1, 4 * 104]. 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 
// 👍 163 👎 0

import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class FindEventualSafeStates802 {
    // DFS
    public List<Integer> eventualSafeNodes(int[][] graph) {
        Set<Integer> res = new HashSet<>();
        int len = graph.length;
        int[] visited = new int[len];
        for(int i = 0; i < len; i++) {
            if(isValid(graph, i, visited))
                res.add(i);
        }
        return new ArrayList<>(res);
    }
    public boolean isValid(int[][] graph, int index, int[] visited) {
        if(visited[index] != 0) return visited[index] == 1; // 1表示访问过且无效
        visited[index] = -1;    // 防止死循环
        int[] g = graph[index];
        if(g.length == 0) { // 终点
            visited[index] = 1;
            return true;
        }
        boolean flag = true;
        for(int next : g) {
            if(!isValid(graph, next, visited)) {
                flag = false;
                break;
            }
        }
        if(flag) {
            visited[index] = 1;
            return true;
        }
        visited[index] = -1;
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

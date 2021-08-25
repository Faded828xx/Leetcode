package Graph;
//Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find
//all possible paths from node 0 to node n - 1 and return them in any order. 
//
// The graph is given as follows: graph[i] is a list of all nodes you can visit 
//from node i (i.e., there is a directed edge from node i to node graph[i][j]). 
//
// 
// Example 1: 
//
// 
//Input: graph = [[1,2],[3],[3],[]]
//Output: [[0,1,3],[0,2,3]]
//Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
// 
//
// Example 2: 
//
// 
//Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
//Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
// 
//
// Example 3: 
//
// 
//Input: graph = [[1],[]]
//Output: [[0,1]]
// 
//
// Example 4: 
//
// 
//Input: graph = [[1,2,3],[2],[3],[]]
//Output: [[0,1,2,3],[0,2,3],[0,3]]
// 
//
// Example 5: 
//
// 
//Input: graph = [[1,3],[2],[3],[]]
//Output: [[0,1,2,3],[0,3]]
// 
//
// 
// Constraints: 
//
// 
// n == graph.length 
// 2 <= n <= 15 
// 0 <= graph[i][j] < n 
// graph[i][j] != i (i.e., there will be no self-loops). 
// All the elements of graph[i] are unique. 
// The input graph is guaranteed to be a DAG. 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 回溯 
// 👍 163 👎 0


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class AllPathsFromSourceToTarget797 {

    // 更感觉像是bfs 需要多次拷贝next的结果
    Map<Integer, List<List<Integer>>> map = new HashMap<>();
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        return dfs(graph, 0);
    }
    public List<List<Integer>> dfs(int[][] graph, int cur) {
        if(map.containsKey(cur)) return map.get(cur);
        int n = graph.length;
        if(cur == n - 1) {
            List<List<Integer>> r = new ArrayList<>(){{add(new ArrayList<>(){{add(cur);}});}};
            map.put(cur, r);
            return r;
        }
        if(graph[cur].length == 0) {
            map.put(cur, null);
            return null;
        }
        List<List<Integer>> res = new ArrayList<>();
        for(int next : graph[cur]) {
            List<List<Integer>> nextList = dfs(graph, next);
            if(nextList == null) continue;
            for(List<Integer> l : nextList) {
                List<Integer> ll = new ArrayList<>(l);
                ll.add(0, cur);
                res.add(ll);
            }
        }
        map.put(cur, res);
        return res;
    }


    List<List<Integer>> res = new ArrayList<>();

    // 回溯 一个list用到底 目标值时拷贝数组
    public List<List<Integer>> allPathsSourceTarget2(int[][] graph) {
        List<Integer> list = new ArrayList<>(){{add(0);}};
        dfs(graph, 0, list);
        return res;
    }

    public void dfs(int[][] graph, int cur, List<Integer> list) {
        int n = graph.length;
        if(cur == n - 1) {
            res.add(new ArrayList<>(list));
            return ;
        }
        for(int next : graph[cur]) {
            list.add(next);
            dfs(graph, next, list);
            list.remove((Integer) next);
        }
    }


}
//leetcode submit region end(Prohibit modification and deletion)

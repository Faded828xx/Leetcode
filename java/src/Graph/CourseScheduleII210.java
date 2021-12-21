package Graph;//There are a total of numCourses courses you have to take, labeled from 0 to nu
//mCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai,
// bi] indicates that you must take course bi first if you want to take course ai.
// 
//
// 
// For example, the pair [0, 1], indicates that to take course 0 you have to fir
//st take course 1. 
// 
//
// Return the ordering of courses you should take to finish all courses. If ther
//e are many valid answers, return any of them. If it is impossible to finish all 
//courses, return an empty array. 
//
// 
// Example 1: 
//
// 
//Input: numCourses = 2, prerequisites = [[1,0]]
//Output: [0,1]
//Explanation: There are a total of 2 courses to take. To take course 1 you shou
//ld have finished course 0. So the correct course order is [0,1].
// 
//
// Example 2: 
//
// 
//Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
//Output: [0,2,1,3]
//Explanation: There are a total of 4 courses to take. To take course 3 you shou
//ld have finished both courses 1 and 2. Both courses 1 and 2 should be taken afte
//r you finished course 0.
//So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3
//].
// 
//
// Example 3: 
//
// 
//Input: numCourses = 1, prerequisites = []
//Output: [0]
// 
//
// 
// Constraints: 
//
// 
// 1 <= numCourses <= 2000 
// 0 <= prerequisites.length <= numCourses * (numCourses - 1) 
// prerequisites[i].length == 2 
// 0 <= ai, bi < numCourses 
// ai != bi 
// All the pairs [ai, bi] are distinct. 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 
// 👍 525 👎 0


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class CourseScheduleII210 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int n = numCourses;
        boolean[][] g = new boolean[n][n];
        int[] degrees = new int[n];
        for(int[] p : prerequisites) {
            degrees[p[0]]++;
            g[p[1]][p[0]] = true;
        }
        Deque<Integer> queue = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            if(degrees[i] == 0)
                queue.offer(i);
        }
//        if(queue.isEmpty()) return new int[]{};
        List<Integer> res = new LinkedList<>();
        while(!queue.isEmpty()) {
            int t = queue.poll();
            res.add(t);
            for(int i = 0; i < n; i++) {
                if(i == t || !g[t][i]) continue;
                degrees[i]--;
                if(degrees[i] == 0) queue.offer(i);
            }
        }
        // 未完成
        if(res.size() < n) return new int[]{};
        // 已完成
        int[] ans = new int[res.size()];
        for(int i = 0; i < res.size(); i++)
            ans[i] = res.get(i);
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

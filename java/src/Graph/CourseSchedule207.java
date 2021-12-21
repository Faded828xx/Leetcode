package Graph;
//There are a total of numCourses courses you have to take, labeled from 0 to nu
//mCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai,
// bi] indicates that you must take course bi first if you want to take course ai.
// 
//
// 
// For example, the pair [0, 1], indicates that to take course 0 you have to fir
//st take course 1. 
// 
//
// Return true if you can finish all courses. Otherwise, return false. 
//
// 
// Example 1: 
//
// 
//Input: numCourses = 2, prerequisites = [[1,0]]
//Output: true
//Explanation: There are a total of 2 courses to take. 
//To take course 1 you should have finished course 0. So it is possible.
// 
//
// Example 2: 
//
// 
//Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
//Output: false
//Explanation: There are a total of 2 courses to take. 
//To take course 1 you should have finished course 0, and to take course 0 you s
//hould also have finished course 1. So it is impossible.
// 
//
// 
// Constraints: 
//
// 
// 1 <= numCourses <= 105 
// 0 <= prerequisites.length <= 5000 
// prerequisites[i].length == 2 
// 0 <= ai, bi < numCourses 
// All the pairs prerequisites[i] are unique. 
// 
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 
// 👍 1059 👎 0

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class CourseSchedule207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int n = numCourses;
        boolean[][] g = new boolean[n][n];
        int[] degrees = new int[n];
        Set<Integer> s = new HashSet<>();
        for(int[] p : prerequisites) {
            // p[1] -> p[0]
            degrees[p[0]]++;
            g[p[1]][p[0]] = true;
            s.add(p[0]);
            s.add(p[1]);
        }
        Deque<Integer> queue = new ArrayDeque<>();
        for(int i = 0; i < n; i++)
            if(degrees[i] == 0)
                queue.offer(i);
        if(queue.isEmpty()) return false;
        while(!queue.isEmpty()) {
            int t = queue.poll();
            s.remove(t);
            for(int i = 0; i < n; i++) {
                if(i == t || !g[t][i]) continue;
                degrees[i]--;
                if(degrees[i] == 0)
                    queue.offer(i);
            }
        }
        return s.isEmpty();
    }

    public static void main(String[] args) {
        int[][] arr = new int[][]{{1,0}};
        CourseSchedule207 courseSchedule207 = new CourseSchedule207();
        System.out.println(courseSchedule207.canFinish(2, arr));

    }
}
//leetcode submit region end(Prohibit modification and deletion)

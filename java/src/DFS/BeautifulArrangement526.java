package DFS;
//Suppose you have n integers labeled 1 through n. A permutation of those n inte
//gers perm (1-indexed) is considered a beautiful arrangement if for every i (1 <=
// i <= n), either of the following is true: 
//
// 
// perm[i] is divisible by i. 
// i is divisible by perm[i]. 
// 
//
// Given an integer n, return the number of the beautiful arrangements that you 
//can construct. 
//
// 
// Example 1: 
//
// 
//Input: n = 2
//Output: 2
//Explanation: 
//The first beautiful arrangement is [1,2]:
//    - perm[1] = 1 is divisible by i = 1
//    - perm[2] = 2 is divisible by i = 2
//The second beautiful arrangement is [2,1]:
//    - perm[1] = 2 is divisible by i = 1
//    - i = 2 is divisible by perm[2] = 1
// 
//
// Example 2: 
//
// 
//Input: n = 1
//Output: 1
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 15 
// 
// Related Topics 位运算 数组 动态规划 回溯 状态压缩 
// 👍 201 👎 0


import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class BeautifulArrangement526 {
    public static int countArrangement(int n) {
        int[] res = new int[n + 1];
        Set<Integer> visited = new HashSet<>();
        return dfs(res, 1, visited);
    }
    public static int dfs(int[] res, int index, Set<Integer> visited) {
        int n = res.length - 1;
        if(index == n + 1) return 1;
        int ans = 0;
        for(int i = 1; i <= n; i++) {
            if((i % index == 0 || index % i == 0) && !visited.contains(i)) {
                visited.add(i);
                ans += dfs(res, index + 1, visited);
                visited.remove(i);
            }
        }
        return ans;
    }


    // 跟打表比一下
    public static void main(String[] args) {
        for(int i = 1; i <= 15; i++) {
            System.out.print(countArrangement(i) + "  ");
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

package DFS;
//Given a positive integer n, you can apply one of the following operations:
//
// 
// If n is even, replace n with n / 2. 
// If n is odd, replace n with either n + 1 or n - 1. 
// 
//
// Return the minimum number of operations needed for n to become 1. 
//
// 
// Example 1: 
//
// 
//Input: n = 8
//Output: 3
//Explanation: 8 -> 4 -> 2 -> 1
// 
//
// Example 2: 
//
// 
//Input: n = 7
//Output: 4
//Explanation: 7 -> 8 -> 4 -> 2 -> 1
//or 7 -> 6 -> 3 -> 2 -> 1
// 
//
// Example 3: 
//
// 
//Input: n = 4
//Output: 2
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 231 - 1 
// 
// Related Topics 贪心 位运算 记忆化搜索 动态规划 
// 👍 137 👎 0

import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class IntegerReplacement397 {

    Map<Integer, Integer> map = new HashMap<>();

    // dfs暴搜
    public int integerReplacement(int n) {
        return dfs(n);
    }

    public int dfs(int n) {
        if(n == Integer.MIN_VALUE) return 31;
        if(map.containsKey(n)) return map.get(n);
        if (n == 1) return 0;
        if ((n & 1) == 0) {
            map.put(n, dfs(n >> 1) + 1);
        } else {
            map.put(n, Math.min(dfs(n + 1), dfs(n - 1)) + 1);
        }
        return map.get(n);
    }

    public static void main(String[] args) {
        IntegerReplacement397 integerReplacement397 = new IntegerReplacement397();
        System.out.println(integerReplacement397.integerReplacement(Integer.MAX_VALUE));
    }
}
//leetcode submit region end(Prohibit modification and deletion)

package Math;
//给你一个整数 n ，请你找出并返回第 n 个 丑数 。
//
// 丑数 就是只包含质因数 2、3 和/或 5 的正整数。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 10
//输出：12
//解释：[1, 2, 3, 4, 5, 6, 8, 9, 10, 12] 是由前 10 个丑数组成的序列。
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：1
//解释：1 通常被视为丑数。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 1690 
// 
// Related Topics 堆 数学 动态规划 
// 👍 542 👎 0

import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class UglyNumberIi264 {
    // 最小堆 将最小元素乘2 3 5 后经set筛选入堆
    public int nthUglyNumber(int n) {
        Set<Long> set = new HashSet<>();
        int[] mul = new int[]{2,3,5};
        PriorityQueue<Long> heap = new PriorityQueue<>();
        heap.add(1L);
        long min = 0;
        while(n!=0) {
            min = heap.remove();
            for(int i=0; i<3; i++) {
                long cur = min * mul[i];
                if(set.add(cur)) {
                    heap.add(cur);
                }
            }
            n--;
        }
        return (int)min;
    }

    // 动态规划
    public int nthUglyNumber2(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;
        int p2 = 1;
        int p3 = 1;
        int p5 = 1;
        // p2 p3 p5 遍历的是dp数组 因此可以遍历到2 3 5 的任意组合
        for(int i=2; i<=n; i++) {
            dp[i] = Math.min(Math.min(dp[p2]*2,dp[p3]*3),dp[p5]*5); // 三者最小
            if(dp[i]==dp[p2]*2) {
                p2++;
            }
            if(dp[i]==dp[p3]*3) {
                p3++;
            }
            if(dp[i]==dp[p5]*5) {
                p5++;
            }
        }
        return dp[n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

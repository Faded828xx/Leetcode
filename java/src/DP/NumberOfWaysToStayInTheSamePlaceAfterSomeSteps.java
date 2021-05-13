package DP;//有一个长度为 arrLen 的数组，开始有一个指针在索引 0 处。
//
// 每一步操作中，你可以将指针向左或向右移动 1 步，或者停在原地（指针不能被移动到数组范围外）。 
//
// 给你两个整数 steps 和 arrLen ，请你计算并返回：在恰好执行 steps 次操作以后，指针仍然指向索引 0 处的方案数。 
//
// 由于答案可能会很大，请返回方案数 模 10^9 + 7 后的结果。 
//
// 
//
// 示例 1： 
//
// 输入：steps = 3, arrLen = 2
//输出：4
//解释：3 步后，总共有 4 种不同的方法可以停在索引 0 处。
//向右，向左，不动
//不动，向右，向左
//向右，不动，向左
//不动，不动，不动
// 
//
// 示例 2： 
//
// 输入：steps = 2, arrLen = 4
//输出：2
//解释：2 步后，总共有 2 种不同的方法可以停在索引 0 处。
//向右，向左
//不动，不动
// 
//
// 示例 3： 
//
// 输入：steps = 4, arrLen = 2
//输出：8
// 
//
// 
//
// 提示： 
//
// 
// 1 <= steps <= 500 
// 1 <= arrLen <= 10^6 
// 
// Related Topics 动态规划 
// 👍 63 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class NumberOfWaysToStayInTheSamePlaceAfterSomeSteps {
    // 完全自己写的 太感动了 十二点写到凌晨一点多 还超内存了 大早上改个limit就过了
    public static int numWays(int steps, int arrLen) {
        if(arrLen==1) return 1;
        int limit = Math.min(arrLen, steps/2+1);
        int[][] dp = new int[steps+1][limit];  // dp[i][j]即i步后到达j位置的方案数
        dp[1][1] = 1;
        dp[1][0] = 1;
        for(int i=2; i<=steps; i++) {
            for(int j=limit-1; j>0; j--) {
                if(j==i) dp[i][j] = 1;
                else if(j==limit-1)
                    dp[i][j] = (dp[i-1][j] + dp[i-1][j-1]) % 1000000007;
                else
                    dp[i][j] = ((dp[i-1][j-1] + dp[i-1][j]) % 1000000007 + dp[i-1][j+1]) % 1000000007;
            }
            dp[i][0] = (dp[i-1][0] + dp[i-1][1]) % 1000000007;
        }
        return dp[steps][0] % 1000000007;
    }

    // dp[i]仅与dp[i-1]有关 可将其降维 len=limit
    public static int numWays2(int steps, int arrLen) {
        if(arrLen==1) return 1;
        int limit = Math.min(arrLen, steps/2+1);
        int[] dp = new int[limit];
        dp[1] = 1;
        dp[0] = 1;
        for(int i=2; i<=steps; i++) {
            int[] dpCur = new int[limit];
            for(int j=limit-1; j>0; j--) {
                if(j==i) dpCur[j] = 1;
                else if(j==limit-1)
                    dpCur[j] = (dp[j] + dp[j-1]) % 1000000007;
                else
                    dpCur[j] = ((dp[j-1] + dp[j]) % 1000000007 + dp[j+1]) % 1000000007;
            }
            dpCur[0] = (dp[0] + dp[1]) % 1000000007;
            dp = dpCur;
        }
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println(numWays2(27, 7));
    }

}
//leetcode submit region end(Prohibit modification and deletion)

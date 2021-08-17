package DP;
//An attendance record for a student can be represented as a string where each c
//haracter signifies whether the student was absent, late, or present on that day.
// The record only contains the following three characters: 
//
// 
// 'A': Absent. 
// 'L': Late. 
// 'P': Present. 
// 
//
// Any student is eligible for an attendance award if they meet both of the foll
//owing criteria: 
//
// 
// The student was absent ('A') for strictly fewer than 2 days total. 
// The student was never late ('L') for 3 or more consecutive days. 
// 
//
// Given an integer n, return the number of possible attendance records of lengt
//h n that make a student eligible for an attendance award. The answer may be very
// large, so return it modulo 109 + 7. 
//
// 
// Example 1: 
//
// 
//Input: n = 2
//Output: 8
//Explanation: There are 8 records with length 2 that are eligible for an award:
//
//"PP", "AP", "PA", "LP", "PL", "AL", "LA", "LL"
//Only "AA" is not eligible because there are 2 absences (there need to be fewer
// than 2).
// 
//
// Example 2: 
//
// 
//Input: n = 1
//Output: 3
// 
//
// Example 3: 
//
// 
//Input: n = 10101
//Output: 183236316
// 
//
// 
// Constraints: 
//
// 
// 1 <= n <= 105 
// 
// Related Topics 动态规划 
// 👍 146 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
class StudentAttendanceRecordII552 {
    public static int checkRecord(int n) {
        int MOD = 1000000007;
        long[][] dp = new long[n + 1][3];
        dp[1][0] = 1;
        dp[1][1] = 0;
        dp[1][2] = 1;
        for(int i = 2; i <= n; i++) {
            // 以pl结尾
            dp[i][0] = dp[i - 1][2];
            // 以ll结尾
            dp[i][1] = dp[i - 1][0];
            // 以p结尾
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % MOD;
        }
//        System.out.println(dp[n][0]);
//        System.out.println(dp[n][1]);
//        System.out.println(dp[n][2]);
        // case:1
        dp[0][0] = 1;
//        return dp[n-1] * n + dp[n]  (dp[i]为三者之和)
//        long m1 = dp[n][2] % MOD;
//        long m2 = (dp[n][0] + dp[n][1] + dp[n][2]) % MOD;
//        return (int)(((m1 * n) % MOD + m2 + MOD) % MOD);

//        无A
        long res = (dp[n][0] + dp[n][1] + dp[n][2]) % MOD;
//        A在每个索引位 左边有i-1个 右边有n-i个
        for(int i = 1; i <= n; i++) {
            res = (res + (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) * (dp[n - i][0] + dp[n - i][1] + dp[n - i][2]) % MOD) % MOD;
        }
        return (int)res;
    }

    public static void main(String[] args) {
        System.out.println(checkRecord(4));
    }
}
//leetcode submit region end(Prohibit modification and deletion)

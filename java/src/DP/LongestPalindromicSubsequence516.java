package DP;
//Given a string s, find the longest palindromic subsequence's length in s.
//
// A subsequence is a sequence that can be derived from another sequence by dele
//ting some or no elements without changing the order of the remaining elements. 
//
// 
// Example 1: 
//
// 
//Input: s = "bbbab"
//Output: 4
//Explanation: One possible longest palindromic subsequence is "bbbb".
// 
//
// Example 2: 
//
// 
//Input: s = "cbbd"
//Output: 2
//Explanation: One possible longest palindromic subsequence is "bb".
// 
//
// 
// Constraints: 
//
// 
// 1 <= s.length <= 1000 
// s consists only of lowercase English letters. 
// 
// Related Topics 字符串 动态规划 
// 👍 530 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
class LongestPalindromicSubsequence516 {
    // dp[i][j] 表示 s 中索引 i～j 的最长回文子序列
    // dp[i][j] = s[i] == s[j] ? dp[i+1][j-1] + 2 : max(dp[i+1][j], dp[i][j-1])
    // 注意是 +2 因为是斜着填充dp 因此实际上 i j 表示的有所出入
    public static int longestPalindromeSubseq(String s) {
        int len = s.length();
        char[] str = s.toCharArray();
        int[][] dp = new int[len][len];
        for(int i = 0; i < len; i++)
            dp[i][i] = 1;
        for(int i = 1; i < len; i++) {
            for(int j = 0; j <= len - 1 - i; j++) {
                dp[j][j + i] = str[j] == str[j + i] ? dp[j + 1][j + i - 1] + 2 : Math.max(dp[j + 1][j + i], dp[j][j + i - 1]);
            }
        }
        return dp[0][len - 1];
    }


    public static void main(String[] args) {
        System.out.println(longestPalindromeSubseq("bbbab"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)

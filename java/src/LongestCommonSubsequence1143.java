//给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。 
//
// 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。 
//
// 
// 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。 
// 
//
// 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。 
//
// 
//
// 示例 1： 
//
// 
//输入：text1 = "abcde", text2 = "ace" 
//输出：3  
//解释：最长公共子序列是 "ace" ，它的长度为 3 。
// 
//
// 示例 2： 
//
// 
//输入：text1 = "abc", text2 = "abc"
//输出：3
//解释：最长公共子序列是 "abc" ，它的长度为 3 。
// 
//
// 示例 3： 
//
// 
//输入：text1 = "abc", text2 = "def"
//输出：0
//解释：两个字符串没有公共子序列，返回 0 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= text1.length, text2.length <= 1000 
// text1 和 text2 仅由小写英文字符组成。 
// 
// Related Topics 动态规划 
// 👍 463 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class LongestCommonSubsequence1143 {
    public static int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dp = new int[len1][len2];
        for(int i=0; i<len2; i++) { // 第一行
            if(text1.charAt(0)==text2.charAt(i)) {
                while(i<len2) {
                    dp[0][i++] = 1;
                }
            } else {
                dp[0][i] = 0;
            }
        }
        for(int i=0; i<len1; i++) { // 第一列
            if(text2.charAt(0)==text1.charAt(i)) {
                while(i<len1) {
                    dp[i++][0] = 1;
                }
            } else {
                dp[i][0] = 0;
            }
        }
        for(int i=1; i<len1; i++) {
            for(int j=1; j<len2; j++) {
                if(text1.charAt(i)==text2.charAt(j)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[len1-1][len2-1];
    }

    public static void main(String[] args) {
        int res = longestCommonSubsequence("abcde","ace");
    }
}
//leetcode submit region end(Prohibit modification and deletion)

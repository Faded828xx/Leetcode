package DP;
//Given an integer array arr and an integer difference, return the length of the
// longest subsequence in arr which is an arithmetic sequence such that the differ
//ence between adjacent elements in the subsequence equals difference. 
//
// A subsequence is a sequence that can be derived from arr by deleting some or 
//no elements without changing the order of the remaining elements. 
//
// 
// Example 1: 
//
// 
//Input: arr = [1,2,3,4], difference = 1
//Output: 4
//Explanation: The longest arithmetic subsequence is [1,2,3,4]. 
//
// Example 2: 
//
// 
//Input: arr = [1,3,5,7], difference = 1
//Output: 1
//Explanation: The longest arithmetic subsequence is any single element.
// 
//
// Example 3: 
//
// 
//Input: arr = [1,5,7,8,5,3,4,2,1], difference = -2
//Output: 4
//Explanation: The longest arithmetic subsequence is [7,5,3,1].
// 
//
// 
// Constraints: 
//
// 
// 1 <= arr.length <= 105 
// -104 <= arr[i], difference <= 104 
// 
// Related Topics 数组 哈希表 动态规划 
// 👍 98 👎 0

import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class LongestArithmeticSubsequenceOfGivenDifference1218 {

    // 超时
    public int longestSubsequence(int[] arr, int difference) {
        int len = arr.length;
        int[] dp = new int[len];
        int res = 0;
        for(int i = 0; i < len; i++) {
            dp[i] = 1;
            int pre = arr[i] - difference;
            for(int j = i - 1; j >= 0; j--) {
                if(arr[j] == pre) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    break;
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // 用map来存储dp
    public int longestSubsequence2(int[] arr, int difference) {
        int res = 0;
        Map<Integer, Integer> dp = new HashMap<>();
        for (int j : arr) {
            dp.put(j, dp.getOrDefault(j - difference, 0) + 1);
            res = Math.max(res, dp.get(j));
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

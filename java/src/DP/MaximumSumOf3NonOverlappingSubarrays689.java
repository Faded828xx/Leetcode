package DP;//Given an integer array nums and an integer k, find three non-overlapping subar
//rays of length k with maximum sum and return them. 
//
// Return the result as a list of indices representing the starting position of 
//each interval (0-indexed). If there are multiple answers, return the lexicograph
//ically smallest one. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,2,1,2,6,7,5,1], k = 2
//Output: [0,3,5]
//Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indic
//es [0, 3, 5].
//We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicogra
//phically larger.
// 
//
// Example 2: 
//
// 
//Input: nums = [1,2,1,2,1,2,1,2,1], k = 2
//Output: [0,2,4]
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 2 * 104 
// 1 <= nums[i] < 216 
// 1 <= k <= floor(nums.length / 3) 
// 
// Related Topics 数组 动态规划 
// 👍 172 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class MaximumSumOf3NonOverlappingSubarrays689 {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int len = nums.length;
        // dp[i][0]: 0～i中一个k数组的最大和 dp[i][1]和dp[i][2]分别为两个k数组和三个k数组
        int[][] dp = new int[len][3];
        // dp[i][0]指的最大k数组的起始下标
        int[] dp1 = new int[len];
        // dp[i][1]指的最大两个k数组的下标
        int[][] dp2 = new int[len][2];
        // dp[i][2]指的最大三个k数组的下标
        int[][] dp3 = new int[len][3];
        // dp[2*k-1][1]指的两个k数组的下标(0,k)
        dp2[2 * k - 1][1] = k;
        // dp[3*k-1][2]指的三个k数组的下标(0,k,2*k)
        dp3[3 * k - 1][1] = k;
        dp3[3 * k - 1][2] = 2 * k;
        // 0 ～ k-1 和
        int sum1 = 0;
        // k ~ 2*k-1 和
        int sum2 = 0;
        // 2*k ~ 3*k-1 和
        int sum3 = 0;
        for(int i = 0; i < k; i++) {
            sum1 += nums[i];
            sum2 += nums[i + k];
            sum3 += nums[i + 2 * k];
        }
        dp[k - 1][0] = sum1;
        // first
        for(int i = k; i < len - 2 * k; i++) {
            sum1 = sum1 - nums[i - k] + nums[i];
            if(sum1 > dp[i - 1][0]) {
                dp[i][0] = sum1;
                dp1[i] = i - (k - 1);
            } else {
                dp[i][0] = dp[i - 1][0];
                dp1[i] = dp1[i - 1];
            }
        }
        dp[2 * k - 1][1] = dp[k - 1][0] + sum2;
        // second
        for(int i = 2 * k; i < len - k; i++) {
            sum2 = sum2 - nums[i - k] + nums[i];
            int tmp = sum2 + dp[i - k][0];
            if(tmp > dp[i - 1][1]) {
                dp[i][1] = tmp;
                dp2[i][1] = i - (k - 1);
                dp2[i][0] = dp1[i - k];
            } else {
                dp[i][1] = dp[i - 1][1];
                dp2[i][1] = dp2[i - 1][1];
                dp2[i][0] = dp2[i - 1][0];
            }
        }
        dp[3 * k - 1][2] = dp[2 * k - 1][1] + sum3;
        // third
        for(int i = 3 * k; i < len; i++) {
            // 滑动窗口构造第三个数组之和
            sum3 = sum3 - nums[i - k] + nums[i];
            // 加上该数组前的 两个数组的最大和
            int tmp = sum3 + dp[i - k][1];
            // 更新最大和 以及 对应下标
            if(tmp > dp[i - 1][2]) {
                dp[i][2] = tmp;
                dp3[i][2] = i - (k - 1);
                dp3[i][1] = dp2[i - k][1];
                dp3[i][0] = dp2[i - k][0];
            } else {
                // 沿用之前的
                dp[i][2] = dp[i - 1][2];
                dp3[i][2] = dp3[i - 1][2];
                dp3[i][1] = dp3[i - 1][1];
                dp3[i][0] = dp3[i - 1][0];
            }
        }
        return new int[]{dp3[len - 1][0], dp3[len - 1][1], dp3[len - 1][2]};
    }

    public static void main(String[] args) {
        MaximumSumOf3NonOverlappingSubarrays689 maximumSumOf3NonOverlappingSubarrays689 = new MaximumSumOf3NonOverlappingSubarrays689();
        int[] nums = new int[]{1,2,1,2,1,2,1,2,1};
        System.out.println(Arrays.toString(maximumSumOf3NonOverlappingSubarrays689.maxSumOfThreeSubarrays(nums, 2)));
    }
}
//leetcode submit region end(Prohibit modification and deletion)

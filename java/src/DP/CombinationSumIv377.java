package DP;
//给你一个由 不同 整数组成的数组 nums ，和一个目标整数 target 。请你从 nums 中找出并返回总和为 target 的元素组合的个数。
//
// 题目数据保证答案符合 32 位整数范围。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3], target = 4
//输出：7
//解释：
//所有可能的组合为：
//(1, 1, 1, 1)
//(1, 1, 2)
//(1, 2, 1)
//(1, 3)
//(2, 1, 1)
//(2, 2)
//(3, 1)
//请注意，顺序不同的序列被视作不同的组合。
// 
//
// 示例 2： 
//
// 
//输入：nums = [9], target = 3
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// 1 <= nums[i] <= 1000 
// nums 中的所有元素 互不相同 
// 1 <= target <= 1000 
// 
//
// 
//
// 进阶：如果给定的数组中含有负数会发生什么？问题会产生何种变化？如果允许负数出现，需要向题目中添加哪些限制条件？ 
// Related Topics 动态规划 
// 👍 400 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
class CombinationSumIv377 {
    // 大神总结背包问题
    // https://leetcode-cn.com/problems/combination-sum-iv/solution/xi-wang-yong-yi-chong-gui-lu-gao-ding-bei-bao-wen-/
    // dp[i][j] i遍历nums索引 j遍历target 可以优化成dp[j]

    // nums中和为target的有序序列 总数 num可选多次 dp[i][j]可以理解为 j为target时,以nums[i]结尾的有序组合
    // 该组合由nums所有数组成 因此内层为nums 才可以使得每个target的组合是由所有数组成的
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];  // dp[i]为target==i时的组合数
        dp[0] = 1;
        for(int i=1; i<=target; i++) {
            for(int num : nums) {   // 遍历nums中所有小于i的数 以其结尾
                if(num<=i)
                    dp[i] += dp[i-num];    // 每种情况使dp[i]加上dp[i-num]
            }
        }
        return dp[target];
    }

    // nums中和为target的无序序列 总数 num可选多次
    // 由于是无序 每增加一个num需要对所有dp[j]更新 将num放在外循环
    // 力扣 518 类似完全背包问题
    public int change(int[] nums, int target) {
        int[] dp = new int[target+1];
        dp[0] = 1;
        for(int num : nums) {
            for(int i=num; i<=target; i++) {
                dp[i] += dp[i-num];
            }
        }
        return dp[target];
    }

    // nums中和为target的无序序列 总数 num只选一次或零次
    // 与上类似 0-1背包问题
    public int change1(int[] nums, int target) {
        int[] dp = new int[target+1];
        dp[0] = 1;
        for(int num : nums) {
            for(int i=target; i>=num; i--) {
                dp[i] += dp[i-num];
            }
        }
        return dp[target];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

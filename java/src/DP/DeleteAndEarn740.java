package DP;
//给你一个整数数组 nums ，你可以对它进行一些操作。
//
// 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除每个等于 nums[i] - 1 或 nums[i] +
// 1 的元素。 
//
// 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [3,4,2]
//输出：6
//解释：
//删除 4 获得 4 个点数，因此 3 也被删除。
//之后，删除 2 获得 2 个点数。总共获得 6 个点数。
// 
//
// 示例 2： 
//
// 
//输入：nums = [2,2,3,3,3,4]
//输出：9
//解释：
//删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
//之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
//总共获得 9 个点数。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 2 * 104 
// 1 <= nums[i] <= 104 
// 
// Related Topics 动态规划 
// 👍 278 👎 0

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class DeleteAndEarn740 {
    // 自己写的
    public static int deleteAndEarn(int[] nums) {
        int len = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.sort(nums);
        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int max = nums[len-1];
        int[] dp = new int[max+1];
        dp[0] = 0;
        dp[1] = map.getOrDefault(1, 0);
        int index = map.getOrDefault(1, 0);
        for(int i=2; i<=max; i++) {
            dp[i] = dp[i-1];
            if(nums[index]==i) {
                dp[i] = Math.max(dp[i - 2] + i * map.get(i), dp[i - 1]);
                index += map.get(i);
            }
        }
        return dp[max];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1};
        System.out.println(deleteAndEarn(nums));
    }
}
//leetcode submit region end(Prohibit modification and deletion)

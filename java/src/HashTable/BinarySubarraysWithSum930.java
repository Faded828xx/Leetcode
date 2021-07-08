package HashTable;
//给你一个二元数组 nums ，和一个整数 goal ，请你统计并返回有多少个和为 goal 的 非空 子数组。
//
// 子数组 是数组的一段连续部分。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,0,1,0,1], goal = 2
//输出：4
//解释：
//如下面黑体所示，有 4 个满足题目要求的子数组：
//[1,0,1,0,1]
//[1,0,1,0,1]
//[1,0,1,0,1]
//[1,0,1,0,1]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,0,0,0,0], goal = 0
//输出：15
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 104 
// nums[i] 不是 0 就是 1 
// 0 <= goal <= nums.length 
// 
// Related Topics 数组 哈希表 前缀和 滑动窗口 
// 👍 127 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class BinarySubarraysWithSum930 {
    public int numSubarraysWithSum(int[] nums, int goal) {
        int len = nums.length;
        int[] preSum = new int[len];
        preSum[0] = nums[0];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i < len; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }
        int res = 0;
        for (int n : preSum) {
            if (n == goal) res++;
            int pre = map.getOrDefault(n - goal, 0);
            res += pre;
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

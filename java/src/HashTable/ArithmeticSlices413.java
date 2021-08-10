package HashTable;
//An integer array is called arithmetic if it consists of at least three element
//s and if the difference between any two consecutive elements is the same. 
//
// 
// For example, [1,3,5,7,9], [7,7,7,7], and [3,-1,-5,-9] are arithmetic sequence
//s. 
// 
//
// Given an integer array nums, return the number of arithmetic subarrays of num
//s. 
//
// A subarray is a contiguous subsequence of the array. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,2,3,4]
//Output: 3
//Explanation: We have 3 arithmetic slices in nums: [1, 2, 3], [2, 3, 4] and [1,
//2,3,4] itself.
// 
//
// Example 2: 
//
// 
//Input: nums = [1]
//Output: 0
// 
//
// 
// Constraints: 
//
// 
// 1 <= nums.length <= 5000 
// -1000 <= nums[i] <= 1000 
// 
// Related Topics 数组 动态规划 
// 👍 268 👎 0

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class ArithmeticSlices413 {
    // 没看见条件 子数组是相邻的 想复杂了
    public int numberOfArithmeticSlices(int[] nums) {
        int len = nums.length;
        // key->index
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        map.put(0, new HashMap<>());
        for (int i = 1; i < len; i++) {
            Map<Integer, Integer> curMap = new HashMap<>();
//            适用于子数组不连续
//            for(int j = i - 1; j >= 0; j--) {
//                Map<Integer, Integer> preMap = map.get(j);
//                int diff = nums[i]- nums[j];
//                int preNum = preMap.getOrDefault(diff, -1);
//                curMap.put(diff, preNum + 1);
//            }
            Map<Integer, Integer> preMap = map.get(i - 1);
            int diff = nums[i] - nums[i - 1];
            int preNum = preMap.getOrDefault(diff, -1);
            curMap.put(diff, preNum + 1);

            map.put(i, curMap);
        }
        int res = 0;
        for (Map<Integer, Integer> m : map.values()) {
            for (int v : m.values())
                res += v;
        }
        return res;
    }

    // 同以上方法思路相同 用俩变量代替map
    public int numberOfArithmeticSlices2(int[] nums) {
        int len = nums.length;
        if(len < 3) return 0;
        int preDiff = nums[1] - nums[0];
        int preNum = 0;
        int res = 0;
        for(int i = 2; i < len; i++) {
            int diff = nums[i] - nums[i - 1];
            if(diff == preDiff)
                preNum++;
            else {
                preNum = 0;
                preDiff = diff;
            }
            res += preNum;
        }
        return res;
    }

}
//leetcode submit region end(Prohibit modification and deletion)

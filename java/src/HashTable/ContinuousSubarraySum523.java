package HashTable;
//给你一个整数数组 nums 和一个整数 k ，编写一个函数来判断该数组是否含有同时满足下述条件的连续子数组：
//
// 
// 子数组大小 至少为 2 ，且 
// 子数组元素总和为 k 的倍数。 
// 
//
// 如果存在，返回 true ；否则，返回 false 。 
//
// 如果存在一个整数 n ，令整数 x 符合 x = n * k ，则称 x 是 k 的一个倍数。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [23,2,4,6,7], k = 6
//输出：true
//解释：[2,4] 是一个大小为 2 的子数组，并且和为 6 。 
//
// 示例 2： 
//
// 
//输入：nums = [23,2,6,4,7], k = 6
//输出：true
//解释：[23, 2, 6, 4, 7] 是大小为 5 的子数组，并且和为 42 。 
//42 是 6 的倍数，因为 42 = 7 * 6 且 7 是一个整数。
// 
//
// 示例 3： 
//
// 
//输入：nums = [23,2,6,4,7], k = 13
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 105 
// 0 <= nums[i] <= 109 
// 0 <= sum(nums[i]) <= 231 - 1 
// 1 <= k <= 231 - 1 
// 
// Related Topics 数学 动态规划 
// 👍 282 👎 0

import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class ContinuousSubarraySum523 {
    // 暴力对每个子数组求和外加子数组两层循环使得复杂度为三次方
    // 用前缀和可以将对子数组求和变为常数级 整体复杂度为平方
    // 利用同余定理使得两个对k取模相同的前缀和即为要求的子数组界限 子数组长度至少为2则利用哈希表存储索引
    public static boolean checkSubarraySum(int[] nums, int k) {
        int index = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int n : nums) {
            sum += n;
            int mod = sum % k;
            if(mod==0 && index>=1) return true;
            if(map.containsKey(mod)) {
                if (index - map.get(mod) >= 2)
                    return true;
            } else
                map.put(mod, index);
            index++;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5, 0, 0, 0};
        System.out.println(checkSubarraySum(nums, 3));
    }
}
//leetcode submit region end(Prohibit modification and deletion)

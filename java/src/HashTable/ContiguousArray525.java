package HashTable;//给定一个二进制数组 nums , 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度。
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [0,1]
//输出: 2
//说明: [0, 1] 是具有相同数量0和1的最长连续子数组。 
//
// 示例 2: 
//
// 
//输入: nums = [0,1,0]
//输出: 2
//说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 105 
// nums[i] 不是 0 就是 1 
// 
// Related Topics 哈希表 
// 👍 344 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class ContiguousArray525 {
    public int findMaxLength(int[] nums) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int count0 = 0;
        int count1 = 0;
        for(int i=0; i<nums.length; i++) {
            if(nums[i]==0) count0++;
            else count1++;
            int diff = count1 - count0;
            if(map.containsKey(diff))
                res = Math.max(res, i - map.get(diff));
            else map.put(diff, i);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

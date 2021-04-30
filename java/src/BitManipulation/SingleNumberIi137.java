package BitManipulation;
//给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,2,3,2]
//输出：3
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1,0,1,0,1,99]
//输出：99
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 104 
// -231 <= nums[i] <= 231 - 1 
// nums 中，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 
// 
//
// 
//
// 进阶：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？ 
// Related Topics 位运算 
// 👍 647 👎 0

import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class SingleNumberIi137 {
    // 哈希表空间O(n)
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int num : nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        for(int n : map.keySet()) {
            if(map.get(n)==1)
                return n;
        }
        return -1;
    }

    // 位运算  对每一位 所有三数的该位之和模3必定为0 因此该位的所有数之和必定为0或1 取决于单数 由模可确定单数该位 循环32次即可
    // 题解中说对于那些不区分无符号有符号整数的语言还要对最高位判定 还不太清楚为啥
    public static int singleNumber2(int[] nums) {
        int res = 0;
        for(int i=0; i<32; i++) {
            int total = 0;
            for(int num : nums) {
                total += (num >> i) & 1;    // 这里算术还是逻辑右移都可 因为只判断32次 与补0还是1无关
            }
            int cur = total % 3;
            res |= (cur << i);
        }
        return res;
    }

    // 还有状态机解法以后有机会再完善吧 挺难的看起来
}
//leetcode submit region end(Prohibit modification and deletion)

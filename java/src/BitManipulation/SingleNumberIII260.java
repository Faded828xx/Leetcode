package BitManipulation;
//Given an integer array nums, in which exactly two elements appear only once an
//d all the other elements appear exactly twice. Find the two elements that appear
// only once. You can return the answer in any order. 
//
// You must write an algorithm that runs in linear runtime complexity and uses o
//nly constant extra space. 
//
// 
// Example 1: 
//
// 
//Input: nums = [1,2,1,3,2,5]
//Output: [3,5]
//Explanation:  [5, 3] is also a valid answer.
// 
//
// Example 2: 
//
// 
//Input: nums = [-1,0]
//Output: [-1,0]
// 
//
// Example 3: 
//
// 
//Input: nums = [0,1]
//Output: [1,0]
// 
//
// 
// Constraints: 
//
// 
// 2 <= nums.length <= 3 * 104 
// -231 <= nums[i] <= 231 - 1 
// Each integer in nums will appear twice, only two integers will appear once. 
// 
// Related Topics 位运算 数组 
// 👍 475 👎 0

//leetcode submit region begin(Prohibit modification and deletion)
class SingleNumberIII260 {
    // 遍历一遍得到目标两数的异或all 重点是接下来如何拆分得到两者
    // 对all的任意一位1(这里是最低的) 必定是两数中一个为1 另一个为0 因此将所有
    // 数分为两类 该位为1or0 显然目标两数分到不同的组 其余两两相同的数 必定同时分到一组
    // 同一组的数相异或 最后两组都只剩下格子的目标数
    public int[] singleNumber(int[] nums) {
        int all = 0;
        for(int n : nums) {
            all ^= n;
        }
        int i;
        for(i = 0; i < 31; i++) {
            if(((all >> i) & 1) == 1)
                break;
        }
        int num1 = 0;
        int num2 = 0;
        for(int n : nums) {
            if(((n >> i) & 1) == 1)
                num1 ^= n;
            else num2 ^= n;
        }
        return new int[]{num1, num2};
    }
}
//leetcode submit region end(Prohibit modification and deletion)

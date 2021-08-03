package Array;
//给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
//
// 请你找出符合题意的 最短 子数组，并输出它的长度。 
//
// 
//
// 
// 
// 示例 1： 
//
// 
//输入：nums = [2,6,4,8,10,9,15]
//输出：5
//解释：你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,4]
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：nums = [1]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 104 
// -105 <= nums[i] <= 105 
// 
//
// 
//
// 进阶：你可以设计一个时间复杂度为 O(n) 的解决方案吗？ 
// 
// 
// Related Topics 栈 贪心 数组 双指针 排序 单调栈 
// 👍 593 👎 0

import java.util.TreeMap;

//leetcode submit region begin(Prohibit modification and deletion)
class ShortestUnsortedContinuousSubarray581 {
    // TreeMap会找到重复元素
    public static int findUnsortedSubarray(int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(nums[0], 0);
        int len = nums.length;
        int left = len;
        int right = -1;
        for(int i = 1; i < len; i++) {
            if(nums[i] > nums[i - 1]) {
                map.put(nums[i], i);
                continue;
            }
            if(right != i - 1 && nums[i] == nums[i - 1]) continue;
            left = Math.min(left, map.get(map.ceilingKey(nums[i])));
//            right = i;
//            right = right == i - 1 ? i : right;
            map.put(nums[i], i);
            right = i;
        }
        if(left == len) return 0;
        return right - left + 1;
    }

    public static int findUnsortedSubarray2(int[] nums) {
        int len = nums.length;
        int maxN = nums[0];
        int minN = nums[len - 1];
        int left = -1, right = -2;
        for(int i = 1; i < len; i++) {
            if(nums[i] < maxN)
                right = i;
            else maxN = nums[i];
            if(nums[len - 1 - i] > minN)
                left = len - 1 - i;
            else minN = nums[len - 1 - i];
        }
        return right - left + 1;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{2, 6, 4, 8, 10, 9, 15};
        System.out.println(findUnsortedSubarray2(nums));
    }
}
//leetcode submit region end(Prohibit modification and deletion)

package Sort;
//给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <=
//t ，同时又满足 abs(i - j) <= k 。 
//
// 如果存在则返回 true，不存在返回 false。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3,1], k = 3, t = 0
//输出：true 
//
// 示例 2： 
//
// 
//输入：nums = [1,0,1,1], k = 1, t = 2
//输出：true 
//
// 示例 3： 
//
// 
//输入：nums = [1,5,9,1,5,9], k = 2, t = 3
//输出：false 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 2 * 104 
// -231 <= nums[i] <= 231 - 1 
// 0 <= k <= 104 
// 0 <= t <= 231 - 1 
// 
// Related Topics 排序 Ordered Map 
// 👍 360 👎 0

import java.util.TreeSet;

//leetcode submit region begin(Prohibit modification and deletion)
class ContainsDuplicateIii220 {
    // 关键在于在k个元素中找到符合条件的值 而有序集合可以在k个元素中快速找到
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();    // 有序集合作为滑动窗口存储k个元素
        int len = nums.length;
        for(int i=0; i<=len-1; i++) {
            long num = nums[i];
            Long l = set.floor(num);    // 有序集合set中不大于num的最大数
            Long r = set.ceiling(num);  // 有序集 合set中不小于num的最大数
            if(l!=null && num-l<=t) return true;    // l或r可能为空
            if(r!=null && r-num<=t) return true;
            set.add(num);
            if(i>=k) set.remove((long)nums[i-k]);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{7,1,3};
        System.out.println(containsNearbyAlmostDuplicate(nums,2,3));
    }
}
//leetcode submit region end(Prohibit modification and deletion)

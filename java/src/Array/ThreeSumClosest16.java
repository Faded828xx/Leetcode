package Array;
//给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和
//。假定每组输入只存在唯一答案。 
//
// 
//
// 示例： 
//
// 输入：nums = [-1,2,1,-4], target = 1
//输出：2
//解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
// 
//
// 
//
// 提示： 
//
// 
// 3 <= nums.length <= 10^3 
// -10^3 <= nums[i] <= 10^3 
// -10^4 <= target <= 10^4 
// 
// Related Topics 数组 双指针 排序 
// 👍 828 👎 0

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class ThreeSumClosest16 {
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int len = nums.length;
        int resSum = 0;
        int resDiff = Integer.MAX_VALUE;
        for(int i = 0; i < len - 2; i++) {
            for(int j = i + 1; j < len - 1; j++) {
                int curDiff = Integer.MAX_VALUE;
                for(int k = j + 1; k < len; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    int diff = Math.abs(sum - target);
                    if(diff <= curDiff) {
                        curDiff = diff;
                        if(diff < resDiff) {
                            resSum = sum;
                            resDiff = diff;
                        }
//                        System.out.println(resSum);
                    } else break;
                }
            }
        }
        return resSum;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,4,8,16,32,64,128};
        int target = 82;
        System.out.println(threeSumClosest(nums, target));
    }
}
//leetcode submit region end(Prohibit modification and deletion)

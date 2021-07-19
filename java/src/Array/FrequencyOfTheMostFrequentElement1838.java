package Array;
//元素的 频数 是该元素在一个数组中出现的次数。
//
// 给你一个整数数组 nums 和一个整数 k 。在一步操作中，你可以选择 nums 的一个下标，并将该下标对应元素的值增加 1 。 
//
// 执行最多 k 次操作后，返回数组中最高频元素的 最大可能频数 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,4], k = 5
//输出：3
//解释：对第一个元素执行 3 次递增操作，对第二个元素执 2 次递增操作，此时 nums = [4,4,4] 。
//4 是数组中最高频元素，频数是 3 。 
//
// 示例 2： 
//
// 
//输入：nums = [1,4,8,13], k = 5
//输出：2
//解释：存在多种最优解决方案：
//- 对第一个元素执行 3 次递增操作，此时 nums = [4,4,8,13] 。4 是数组中最高频元素，频数是 2 。
//- 对第二个元素执行 4 次递增操作，此时 nums = [1,8,8,13] 。8 是数组中最高频元素，频数是 2 。
//- 对第三个元素执行 5 次递增操作，此时 nums = [1,4,13,13] 。13 是数组中最高频元素，频数是 2 。
// 
//
// 示例 3： 
//
// 
//输入：nums = [3,9,6], k = 2
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 105 
// 1 <= nums[i] <= 105 
// 1 <= k <= 105 
// 
// Related Topics 数组 二分查找 前缀和 滑动窗口 
// 👍 54 👎 0

import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class FrequencyOfTheMostFrequentElement1838 {
    public static int maxFrequency(int[] nums, int k) {
        Arrays.sort(nums);
        int len = nums.length;
        int res = 1;
        int cur = 1;
        int sum = nums[0];
        int right = 1;
        for(int i = 0; i < len; i++) {
            for(int j = right; j < len; j++) {
                sum += nums[j];
                cur++;
                if(sum + k < (j - i + 1) * nums[j]) {
                    cur -= 2;
                    res = Math.max(res, cur + 1);
                    sum -= (nums[i] + nums[j]);
                    right = j;
                    break;
                }
                if(j == len - 1)
                    return Math.max(res, j - i + 1);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 6, 9};
        System.out.println(maxFrequency(nums, 2));
    }
}
//leetcode submit region end(Prohibit modification and deletion)

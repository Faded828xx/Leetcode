package Array;
//给你两个正整数数组 nums1 和 nums2 ，数组的长度都是 n 。
//
// 数组 nums1 和 nums2 的 绝对差值和 定义为所有 |nums1[i] - nums2[i]|（0 <= i < n）的 总和（下标从 0 开始
//）。 
//
// 你可以选用 nums1 中的 任意一个 元素来替换 nums1 中的 至多 一个元素，以 最小化 绝对差值和。 
//
// 在替换数组 nums1 中最多一个元素 之后 ，返回最小绝对差值和。因为答案可能很大，所以需要对 109 + 7 取余 后返回。 
//
// |x| 定义为： 
//
// 
// 如果 x >= 0 ，值为 x ，或者 
// 如果 x <= 0 ，值为 -x 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,7,5], nums2 = [2,3,5]
//输出：3
//解释：有两种可能的最优方案：
//- 将第二个元素替换为第一个元素：[1,7,5] => [1,1,5] ，或者
//- 将第二个元素替换为第三个元素：[1,7,5] => [1,5,5]
//两种方案的绝对差值和都是 |1-2| + (|1-3| 或者 |5-3|) + |5-5| = 3
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [2,4,6,8,10], nums2 = [2,4,6,8,10]
//输出：0
//解释：nums1 和 nums2 相等，所以不用替换元素。绝对差值和为 0
// 
//
// 示例 3： 
//
// 
//输入：nums1 = [1,10,4,4,2,7], nums2 = [9,3,5,1,7,4]
//输出：20
//解释：将第一个元素替换为第二个元素：[1,10,4,4,2,7] => [10,10,4,4,2,7]
//绝对差值和为 |10-9| + |10-3| + |4-5| + |4-1| + |2-7| + |7-4| = 20
// 
//
// 
//
// 提示： 
//
// 
// n == nums1.length 
// n == nums2.length 
// 1 <= n <= 105 
// 1 <= nums1[i], nums2[i] <= 105 
// 
// Related Topics 贪心 数组 二分查找 有序集合 
// 👍 29 👎 0



import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class MinimumAbsoluteSumDifference1818 {
    public static int minAbsoluteSumDiff(int[] nums1, int[] nums2) {
        int len = nums1.length;
        int[] nums3 = new int[len];
        int removeNum = 0;
        int removeIndex = -1;
        int add = -1;
        int[] nums4 = new int[len];
        for(int i = 0; i < len; i++) {
            nums3[i] = Math.abs(nums1[i] - nums2[i]);
            nums4[i] = nums1[i];
        }
        Arrays.sort(nums4);
        // 遍历nums2
        for(int i = 0; i < len; i++) {
            int m;
            // 找到nums1[j]使得与nums[2]差值最小 用二分优化
//            for(int j = 0; j < len; j++) {
//                if(j==i) continue;
//                int tmp = Math.abs(nums1[j] - nums2[i]);
//                if(tmp < m) {
//                    m = tmp;
//                }
//            }
            m = binarySearch(nums4, nums2[i]);
            // 更改后减小的值最大
            if(nums3[i] - m > removeNum) {
                removeNum = nums3[i] - m;
                removeIndex = i;
                add = m;
            }
        }
//        System.out.println(removeNum + " " + removeIndex);
        int res = 0;
        for(int i = 0; i < len; i++) {
            res +=  ((i == removeIndex ? add : nums3[i]) + res) % 1000000007;
        }
        return res % 1000000007;
    }
    // 在nums4中找到与target差值最小的值
    public static int binarySearch(int[] nums4, int target) {
        int left = 0;
        int right = nums4.length - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums4[mid] > target)
                right = mid - 1;
            else left = mid + 1;
        }
        int diff1;
        int diff2;
        if(left < nums4.length)
            diff1 = Math.abs(nums4[left] - target);
        else diff1 = Math.abs(nums4[left - 1] - target);
        if(right > -1)
            diff2 = Math.abs(nums4[right] - target);
        else diff2 = Math.abs(nums4[right + 1] - target);
        return Math.min(diff1, diff2);
    }


    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 10, 4, 4, 2, 7};
        int[] nums2 = new int[]{9, 3, 5, 1, 7, 4};
        System.out.println(minAbsoluteSumDiff(nums1, nums2));
    }

}
//leetcode submit region end(Prohibit modification and deletion)

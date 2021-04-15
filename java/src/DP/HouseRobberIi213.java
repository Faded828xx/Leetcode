package DP;
//你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的
//房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。 
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,3,2]
//输出：3
//解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3,1]
//输出：4
//解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
//     偷窃到的最高金额 = 1 + 3 = 4 。 
//
// 示例 3： 
//
// 
//输入：nums = [0]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 100 
// 0 <= nums[i] <= 1000 
// 
// Related Topics 动态规划 
// 👍 551 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class HouseRobberIi213 {
    public static int rob(int[] nums) {
        int len = nums.length;
        if(len==1) return nums[0];
        int[] dpFirst = new int[len];   // 首尾不可同时选中 因此计算两个dp
        int[] dpRear = new int[len];    // dpFirst指rear元素一定不取 First元素不确定 只计算到len-2 dpRear同理
        dpFirst[0] = nums[0];
        dpFirst[1] = Math.max(nums[0], nums[1]);
        dpRear[len-1] = nums[len-1];
        dpRear[len-2] = Math.max(nums[len-1], nums[len-2]);
        for(int i=2; i<len-1; i++) {
            int indexRear = len - 1 - i;
            dpFirst[i] = Math.max(dpFirst[i -1], dpFirst[i -2]+nums[i]);
            dpRear[indexRear] = Math.max(dpRear[indexRear+1], dpRear[indexRear+2]+nums[indexRear]);
        }
        return Math.max(dpFirst[len-2], dpRear[1]);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 2};
        System.out.println(rob(nums));
    }
}
//leetcode submit region end(Prohibit modification and deletion)

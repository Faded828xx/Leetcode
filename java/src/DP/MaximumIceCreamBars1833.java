package DP;
//夏日炎炎，小男孩 Tony 想买一些雪糕消消暑。
//
// 商店中新到 n 支雪糕，用长度为 n 的数组 costs 表示雪糕的定价，其中 costs[i] 表示第 i 支雪糕的现金价格。Tony 一共有 coin
//s 现金可以用于消费，他想要买尽可能多的雪糕。 
//
// 给你价格数组 costs 和现金量 coins ，请你计算并返回 Tony 用 coins 现金能够买到的雪糕的 最大数量 。 
//
// 注意：Tony 可以按任意顺序购买雪糕。 
//
// 
//
// 示例 1： 
//
// 输入：costs = [1,3,2,4,1], coins = 7
//输出：4
//解释：Tony 可以买下标为 0、1、2、4 的雪糕，总价为 1 + 3 + 2 + 1 = 7
// 
//
// 示例 2： 
//
// 输入：costs = [10,6,8,7,7,8], coins = 5
//输出：0
//解释：Tony 没有足够的钱买任何一支雪糕。
// 
//
// 示例 3： 
//
// 输入：costs = [1,6,3,1,2,5], coins = 20
//输出：6
//解释：Tony 可以买下所有的雪糕，总价为 1 + 6 + 3 + 1 + 2 + 5 = 18 。
// 
//
// 
//
// 提示： 
//
// 
// costs.length == n 
// 1 <= n <= 105 
// 1 <= costs[i] <= 105 
// 1 <= coins <= 108 
// 
// Related Topics 贪心 数组 排序 
// 👍 68 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class MaximumIceCreamBars1833 {
    // dp超时
    public int maxIceCream(int[] costs, int coins) {
        int[] dp = new int[coins + 1];
        for (int cost : costs) {
            for (int j = coins; j >= cost; j--) {
                dp[j] = Math.max(dp[j], dp[j - cost] + 1);
            }
        }
        return dp[coins];
    }
    // 背包问题有cost数组和value数组 这里value全为1 可直接用贪心
    public static int maxIceCream2(int[] costs, int coins) {
        Arrays.sort(costs);
        int res = 0;
        int len = costs.length;
        int curSum = 0;
        for(int i=0; i < len && curSum <= coins; i++) {
            res++;
            curSum += costs[i];
        }
        return curSum > coins ? res - 1 : res;
    }

    public static void main(String[] args) {
        int[] costs = new int[]{1, 6, 3, 1, 2, 5};
        System.out.println(maxIceCream2(costs, 20));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
